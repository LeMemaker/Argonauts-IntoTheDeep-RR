package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.routines.Routine;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public class ClawSystem extends Subsystem
{

    //weight of misumi: 111.6 g
    //weight of axon: 21 g * 2
    //weight of 2 claws: 13.45 + 13.6 g
    //weight of mount 4.75 g
    //length of misumi: 16.5 in, com = 8.25 (assuming uniform mass) = 0.23 m
    //servo + claws mounted ~16 in away =  0.406 m
    //weight of servo claw complex = 42 + 13.45 + 13.6 + 4.75 = 73.8 g
    // torque needed: 0.5464 cos theta

    //power value: 0.13

    //We should test using cont rotation, and adjusting power levels. We can tune by trying to hold at 0 position at seeing what power value allows the servo to hold, aka seeing what power = 0.5646 N*m
    private Servo shoulder_servo_l;

    private CRServo shoulder_cont_l;

    private Servo shoulder_servo_r;

    private CRServo shoulder_cont_r;

    private AnalogInput shoulder_enc_r;

    private AnalogInput shoulder_enc_l;

    private Servo claw_servo;

    public boolean isHasReached;

    final double torqueConst = 0.13;



    /*public Servo getShoulder_servo() {
        return shoulder_servo;
    }*/

    public Servo getShoulder_servo_l() { return shoulder_servo_l; }
    public Servo getShoulder_servo_r() { return shoulder_servo_r; }
    public Servo getClaw_servo() {
        return claw_servo;
    }
    private double setPos;

    private double deltaPos;
    final double OPEN = 0.55;
    final double CLOSE = 0.75;// TEST THEM OUT AFTERNOON

    final double EXTENDED = 0.25;

    final double RETRACTED = 0.65;

    final double FRONT_LIMIT = 0.01;
    final double BACK_LIMIT = 0.85;

    final double SAMPLE_POS = 0.0544;// Test these 4 tomorrow
    final double EXT_SAMPLE_POS = 0.0833;

    final double SPEC_POS = 0.775;

    final double CHAMBER_POS = 0.3;

    private boolean openChanged = false;

    private  boolean isOpen = true;

    private double currentShoulderPos = 0.0;

    private double pastShoulderPos = 0.0;

    private double shoulderAccel = 0.0;


    private double currentTime = 0.0;

    private double pastTime = 0.0;

    private double shoulderVel = 0.0;

    private double pastShoulderVel = 0.0;
    private double actualShoulderVel = 0.0;
    public void setClawPos(double pos){
        this.claw_servo.setPosition(pos);
    }

    public double getSetPos(){
        return setPos;
    }

    public double getActualPosL(){
        return 360 - ((shoulder_enc_l.getVoltage() / 3.3 * 360) - 26.3 + 90);
    }

    public double getActualPosR(){
        return (shoulder_enc_r.getVoltage() / 3.3 * 360);
    }

    public double getActualPos(){
        return getActualPosL();
    }

    public double getDeltaPos(){
        return deltaPos;
    }

    public void setShoulderPos(double pos){
        this.shoulder_servo_l.setPosition(pos);
        this.shoulder_servo_r.setPosition(pos);
    }

    public void setShoulderPow(double pow){
        this.shoulder_cont_l.setPower(pow);
        this.shoulder_cont_r.setPower(pow);
    }



    public void slowShoulder(double pos){
        isHasReached = false;
        double cur = this.shoulder_servo_l.getPosition();
        for(double i=cur; Math.abs(i-pos)>=0.005; i+=(pos-cur)/500.0)
        {
            setShoulderPos(i);
            isHasReached = true;
        }
        return;

    }

    public void openClaw(){
        this.setClawPos(this.OPEN);
    }

    public void closeClaw(){
        this.setClawPos(this.CLOSE);
    }

    public double powerToTorque(double pow){
        return pow * 0.5464 / torqueConst;
    }

    public void holdServoPos(){
        this.setShoulderPow(torqueConst*Math.cos(this.getActualPosL()));
    }

    public void setShoulderVel(double _shoulderVel){
        this.shoulderVel = _shoulderVel;
    }

    public double getShoulderVel(){
        return shoulderVel;
    }

    public double getPower(){
        return this.shoulder_cont_l.getPower();
    }

    public void toggleClaw(boolean toggle){

        if(toggle && !openChanged){
            if(isOpen){
                closeClaw();
                isOpen = false;
            }

            else {
                openClaw();
                isOpen = true;
            }
            openChanged = true;
        }
        else if (!toggle){
            openChanged = false;
        }

    }
    //lower bound 0.425, upper bound 0.0
    public void shoulderOnTick(boolean leftSlowBump, boolean rightSlowBump){

        if(leftSlowBump == rightSlowBump){
            return;
        }


        if(rightSlowBump){
            setShoulderPos(this.shoulder_servo_l.getPosition() + 0.001);
            setShoulderPos(this.shoulder_servo_r.getPosition() + 0.001);

        }
        if(leftSlowBump){
            setShoulderPos(this.shoulder_servo_l.getPosition() - 0.001);
            setShoulderPos(this.shoulder_servo_r.getPosition() - 0.001);

        }

    }

    public double getActualShoulderVelocity(double runTime){
        pastTime = currentTime;
        currentTime = runTime;

        pastShoulderPos = currentShoulderPos;
        currentShoulderPos = this.getActualPosL();

        double deltaTime = currentTime - runTime;
        double deltaPos = currentShoulderPos - pastShoulderPos;

        pastShoulderVel = actualShoulderVel;

        actualShoulderVel = deltaPos/deltaTime;
        getShoulderAcceleration(deltaTime);
        return this.actualShoulderVel;
    }

    private double getShoulderAcceleration(double deltaTime){
        double deltaVel = actualShoulderVel - pastShoulderVel;

        shoulderAccel = deltaVel/deltaTime;
        return shoulderAccel;
    }

    public void holdVelocity(){
        double shoulderError = this.actualShoulderVel - this.shoulderVel;


    }
    public void presetPosition(boolean leftArrow, boolean rightArrow, boolean chamberButton, boolean specimenButton){

        if(leftArrow)
            setPos = SAMPLE_POS;

        if(rightArrow)
            setPos = EXT_SAMPLE_POS;

        if(chamberButton)
            setPos = CHAMBER_POS;

        if(specimenButton)
            setPos = SPEC_POS;

        if(leftArrow || rightArrow || chamberButton || specimenButton){
            isHasReached = false;
            deltaPos = (setPos - this.getShoulder_servo_l().getPosition()) / Math.abs((setPos - this.getShoulder_servo_l().getPosition())) * 0.005;
        }


        //slowShoulder(setPos);

    }








    public ClawSystem(Routine routine) {
        super(routine);
        claw_servo = routine.hardwareMap.get(Servo.class, "claw_servo");
        shoulder_servo_r = routine.hardwareMap.get(Servo.class, "shoulder_servo_r");
        shoulder_servo_l = routine.hardwareMap.get(Servo.class, "shoulder_servo_l");

//        shoulder_cont_r = routine.hardwareMap.get(CRServo.class, "shoulder_cont_r");
//        shoulder_cont_l = routine.hardwareMap.get(CRServo.class, "shoulder_cont_l");

        shoulder_enc_r = routine.hardwareMap.get(AnalogInput.class, "shoulder_enc_r");
        shoulder_enc_l = routine.hardwareMap.get(AnalogInput.class, "shoulder_enc_l");
        //shoulder_servo_l.setPosition(FRONT_LIMIT);
        //shoulder_servo_r.setPosition(FRONT_LIMIT);

        currentShoulderPos = this.getActualPosL();

//206.3 at 180

        this.toggleClaw(true);

    }
}