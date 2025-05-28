package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.routines.Routine;

public class DriveSystem extends Subsystem
{

    private DcMotor drive_motor_L;
    private DcMotor drive_motor_R;

    public DcMotor getDrive_motor_L()
    {
        return drive_motor_L;
    }

    public DcMotor getDrive_motor_R()
    {
        return drive_motor_R;
    }

    public void driveOnTick(double x, double y, double rx) {

        x = -x;

        getDrive_motor_L().setPower((y - x - rx));
        getDrive_motor_R().setPower((y + x - rx));
    }



    public void driveOnTickVector(double x, double y, double rx){
//        x = -x;
//        rx = -rx;
        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);

        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        double leftFront = power * cos/max + rx;
        double rightFront = power * sin/max - rx;

        if ((power + Math.abs(rx)) > 1) {
            leftFront   /= power + Math.abs(rx);
            rightFront /= power + Math.abs(rx);
        }

        getDrive_motor_L().setPower(leftFront);
        getDrive_motor_R().setPower(-rightFront);
    }

    public DriveSystem(Routine routine)
    {
        super(routine);
        drive_motor_L = routine.hardwareMap.get(DcMotor.class, "drive_motor_L");
        drive_motor_R = routine.hardwareMap.get(DcMotor.class, "drive_motor_R");

        drive_motor_L.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drive_motor_R.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        drive_motor_fr.setDirection(DcMotorSimple.Direction.REVERSE);
//        drive_motor_bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
