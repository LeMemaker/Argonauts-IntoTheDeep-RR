package org.firstinspires.ftc.teamcode.routines.driver;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.routines.Routine;
import org.firstinspires.ftc.teamcode.subsystems.ClawSystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSystem;
import org.firstinspires.ftc.teamcode.subsystems.System;
import org.firstinspires.ftc.teamcode.subsystems.ViperSystem;

@TeleOp(name = "Driver")
public class DriverRoutine extends Routine {
	public DriveSystem driveSystem;


	@Override
	public void onInit() {
		super.onInit();
//		viperSystem = new ViperSystem(this);
//		clawSystem = new ClawSystem(this);
		driveSystem = new DriveSystem(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		driveSystem = new DriveSystem(this);
		try
		{
			Thread.sleep(1000);

		}catch(InterruptedException e){

		}
		while(opModeIsActive()){


			driveSystem.driveOnTickVector(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

			telemetry.addData("left_stick_x", gamepad1.left_stick_x);
			telemetry.addData("left_stick_y", gamepad1.left_stick_x);
			telemetry.addData("right_stick_x", gamepad1.right_stick_x);


			telemetry.update();
		}

	}

}
