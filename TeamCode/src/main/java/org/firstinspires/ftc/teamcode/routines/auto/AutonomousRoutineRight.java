package org.firstinspires.ftc.teamcode.routines.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.routines.Routine;
import org.firstinspires.ftc.teamcode.subsystems.ClawSystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSystem;
import org.firstinspires.ftc.teamcode.subsystems.ViperSystem;

@Autonomous(name = "AutonomousRight")
public class AutonomousRoutineRight extends Routine {
	public ViperSystem viperSystem;
	public ClawSystem clawSystem;
	public DriveSystem driveSystem;
	double motorPower = 0.5;

	@Override
	public void onInit() {
		super.onInit();
//		viperSystem = new ViperSystem(this);
//		clawSystem = new ClawSystem(this);
//		driveSystem = new DriveSystem(this);
	}
	@Override
	public void onStart() {
		super.onStart();
		viperSystem = new ViperSystem(this);
		clawSystem = new ClawSystem(this);
		driveSystem = new DriveSystem(this);
		try
		{
			Thread.sleep(1000);

		}catch(InterruptedException e){

		}
		clawSystem.setShoulderPos(0.29);
		driveSystem.getDrive_motor_fl().setPower(-motorPower);
		driveSystem.getDrive_motor_fr().setPower(motorPower);
		driveSystem.getDrive_motor_bl().setPower(-motorPower);
		driveSystem.getDrive_motor_br().setPower(motorPower);
		try
		{
			Thread.sleep(1800);

		}catch(InterruptedException e){

		}
		//------
		driveSystem.getDrive_motor_fl().setPower(0);
		driveSystem.getDrive_motor_fr().setPower(0);
		driveSystem.getDrive_motor_bl().setPower(0);
		driveSystem.getDrive_motor_br().setPower(0);

		try
		{
			Thread.sleep(1000);

		}catch(InterruptedException e){

		}
		clawSystem.setClawPos(0.55);


		driveSystem.getDrive_motor_fl().setPower(motorPower);
		driveSystem.getDrive_motor_fr().setPower(-motorPower);
		driveSystem.getDrive_motor_bl().setPower(motorPower);
		driveSystem.getDrive_motor_br().setPower(-motorPower);
		try
		{
			Thread.sleep(1800);

		}catch(InterruptedException e){

		}
		//------
		driveSystem.getDrive_motor_fl().setPower(0);
		driveSystem.getDrive_motor_fr().setPower(0);
		driveSystem.getDrive_motor_bl().setPower(0);
		driveSystem.getDrive_motor_br().setPower(0);

		try
		{
			Thread.sleep(500);

		}catch(InterruptedException e){

		}/*
		driveSystem.getDrive_motor_fl().setPower(-motorPower);
		driveSystem.getDrive_motor_fr().setPower(-motorPower);
		driveSystem.getDrive_motor_bl().setPower(motorPower);
		driveSystem.getDrive_motor_br().setPower(motorPower);
		try
		{
			Thread.sleep(5000);

		}catch(InterruptedException e){

		}*/

		driveSystem.getDrive_motor_fl().setPower(0);
		driveSystem.getDrive_motor_fr().setPower(0);
		driveSystem.getDrive_motor_bl().setPower(0);
		driveSystem.getDrive_motor_br().setPower(0);
		try
		{
			Thread.sleep(40000);

		}catch(InterruptedException e){

		}
	}

	@Override
	public void onExit() {
		super.onExit();
	}
}
