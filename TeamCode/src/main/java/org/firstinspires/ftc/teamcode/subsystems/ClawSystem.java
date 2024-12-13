package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.routines.Routine;

public class ClawSystem extends Subsystem
{
    private Servo shoulder_servo_l;

    private Servo shoulder_servo_r;

    private Servo claw_servo_l;
    private Servo claw_servo_r;


    //public Servo getShoulder_servo() {
    //    return shoulder_servo;
    //}

    public Servo getShoulder_servo_l() { return shoulder_servo_l; }
    public Servo getShoulder_servo_r() { return shoulder_servo_r; }
    public Servo getClaw_servo_l() {
        return claw_servo_l;
    }

    public Servo getClaw_servo_r() {
        return claw_servo_r;
    }

    final double OPEN = 0;
    final double CLOSE = 0.75;// TEST THEM OUT AFTERNOON

    private boolean openChanged = false;

    private  boolean isOpen = true;
    public void setClawPos(double pos){
        this.claw_servo_l.setPosition(pos);
        this.claw_servo_r.setPosition(pos);
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
    public void shoulderOnTick(boolean rightBump, boolean leftBump){
        if(rightBump == leftBump){
            return;
        }
        if(rightBump){
            setShoulderPos(this.shoulder_servo_l.getPosition() + 0.005);
        }
        if(leftBump){
            setShoulderPos(this.shoulder_servo_l.getPosition() - 0.005);
        }

    }





    public ClawSystem(Routine routine) {
        super(routine);
        claw_servo_l = routine.hardwareMap.get(Servo.class, "claw_servo_l");
        claw_servo_r = routine.hardwareMap.get(Servo.class, "claw_servo_r");
        shoulder_servo_r = routine.hardwareMap.get(Servo.class, "shoulder_servo_r");
        shoulder_servo_l = routine.hardwareMap.get(Servo.class, "shoulder_servo_l");
        claw_servo_r.setDirection(Servo.Direction.REVERSE);
        shoulder_servo_l.setPosition(0);
        shoulder_servo_r.setPosition(0);


        this.toggleClaw(true);

    }
}