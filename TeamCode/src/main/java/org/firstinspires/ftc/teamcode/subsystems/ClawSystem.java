package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.routines.Routine;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public class ClawSystem extends Subsystem
{
    private Servo shoulder_servo_l;

    private Servo shoulder_servo_r;

    private Servo claw_servo;

    private Servo ext_servo;


    /*public Servo getShoulder_servo() {
        return shoulder_servo;
    }*/

    public Servo getShoulder_servo_l() { return shoulder_servo_l; }
    public Servo getShoulder_servo_r() { return shoulder_servo_r; }
    public Servo getClaw_servo() {
        return claw_servo;
    }
    public Servo getExt_servo(){
        return ext_servo;
    }

    final double OPEN = 0.30;
    final double CLOSE = 0.75;// TEST THEM OUT AFTERNOON

    final double EXTENDED = 0.25;

    final double RETRACTED = 0.65;

    final double FRONT_LIMIT = 0.06;
    final double BACK_LIMIT = 0.85;

    final double CENTER_POS = 0.35;// Test these 4 tomorrow
    final double BASKET_POS = 0.65;

    final double SPEC_POS = 0.775;

    final double CHAMBER_POS = 0.5;

    private boolean openChanged = false;

    private  boolean isOpen = true;
    public void setClawPos(double pos){
        this.claw_servo.setPosition(pos);
    }

    public void setShoulderPos(double pos){
        this.shoulder_servo_l.setPosition(pos);
        this.shoulder_servo_r.setPosition(pos);
    }



    public void openClaw(){
        this.setClawPos(this.OPEN);
    }

    public void closeClaw(){
        this.setClawPos(this.CLOSE);
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
    public void shoulderOnTick(boolean chamberButton, boolean specimenButton, boolean leftSlowBump, boolean rightSlowBump){
        if(leftSlowBump == rightSlowBump){
            return;
        }
        if(chamberButton){
            setShoulderPos(CHAMBER_POS);
        }
        if(specimenButton){
            setShoulderPos(SPEC_POS);
        }

        if(rightSlowBump && (this.shoulder_servo_l.getPosition() < BACK_LIMIT) ){
            setShoulderPos(this.shoulder_servo_l.getPosition() + 0.001);
            setShoulderPos(this.shoulder_servo_r.getPosition() + 0.001);

        }
        if(leftSlowBump  && (this.shoulder_servo_l.getPosition() > FRONT_LIMIT)){
            setShoulderPos(this.shoulder_servo_l.getPosition() - 0.001);
            setShoulderPos(this.shoulder_servo_r.getPosition() - 0.001);

        }

    }

    public void presetPosition(boolean leftArrow, boolean rightArrow){
        if(leftArrow){
            setShoulderPos(CENTER_POS);

        }
        if(rightArrow){
            setShoulderPos(BASKET_POS);

        }

    }

    public void extensionOnTick(boolean x){
        if(x){
            ext_servo.setPosition(EXTENDED);
        }
        else{
            ext_servo.setPosition(RETRACTED);
        }
    }





    public ClawSystem(Routine routine) {
        super(routine);
        claw_servo = routine.hardwareMap.get(Servo.class, "claw_servo");
        shoulder_servo_r = routine.hardwareMap.get(Servo.class, "shoulder_servo_r");
        shoulder_servo_l = routine.hardwareMap.get(Servo.class, "shoulder_servo_l");
        ext_servo = routine.hardwareMap.get(Servo.class, "ext_servo");
        shoulder_servo_l.setPosition(FRONT_LIMIT);
        shoulder_servo_r.setPosition(FRONT_LIMIT);


        this.toggleClaw(true);

    }
}