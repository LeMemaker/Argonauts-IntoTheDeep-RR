package org.firstinspires.ftc.teamcode.routines.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;

import org.firstinspires.ftc.teamcode.routines.Routine;
import org.firstinspires.ftc.teamcode.subsystems.ClawSystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSystem;
import org.firstinspires.ftc.teamcode.subsystems.ViperSystem;


@Autonomous(name = "Autonomous")
public class AutonomousRoutine extends Routine {
	public ViperSystem viperSystem;
	public ClawSystem clawSystem;
	public DriveSystem driveSystem;
	@Override
	public void onInit() {
		super.onInit();
		viperSystem = new ViperSystem(this);
		clawSystem = new ClawSystem(this);
		driveSystem = new DriveSystem(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		clawSystem.setShoulderPos(0.025);

		clawSystem.toggleClaw(true);
		try{
			Thread.sleep(1000);
		}
		catch (InterruptedException e){

		}
		clawSystem.setShoulderPos(0.255);

		Pose2d beginPose = new Pose2d(0, 0, 0);
		MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

		waitForStart();

		Actions.runBlocking(
				drive.actionBuilder(beginPose)
						.splineTo(new Vector2d(27, 8), 0)
						.build());


		clawSystem.toggleClaw(false);

		Actions.runBlocking(
				drive.actionBuilder(beginPose)
						.splineTo(new Vector2d(-5,0),0)
						.build());

	}

	@Override
	public void onExit() {
		super.onExit();
	}
}
