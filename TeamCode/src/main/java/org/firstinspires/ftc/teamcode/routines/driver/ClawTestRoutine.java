package org.firstinspires.ftc.teamcode.routines.driver;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.routines.Routine;
import org.firstinspires.ftc.teamcode.subsystems.ClawSystem;
//@Disabled
@TeleOp(name = "ClawTest")
public class ClawTestRoutine extends Routine {
    public ClawSystem clawSystem;

    @Override
    public void onInit() {
        super.onInit();
        clawSystem = new ClawSystem(this);
    }

    @Override
    public void onStart() {
        double pow = 0.0;
        while(opModeIsActive()){
            //clawSystem.toggleClaw(gamepad1.b);
            telemetry.addData("Servo Claw", clawSystem.getClaw_servo().getPosition());

            double actualPos = clawSystem.getActualPos();
            if(gamepad1.a){
                clawSystem.holdServoPos();
            }
            if(gamepad1.b){
                clawSystem.setShoulderPow(0.0);
            }

//            if(gamepad1.a){
//                pow += 0.0001;
//            }
//            if(gamepad1.b){
//                pow -= 0.0001;
//            }

            //clawSystem.setShoulderPow(pow);
            telemetry.addData("Shoulder ACTUAL Position", clawSystem.getActualPos());
            telemetry.addData("Power", clawSystem.getPower());
            telemetry.update();
        }

    }
}
