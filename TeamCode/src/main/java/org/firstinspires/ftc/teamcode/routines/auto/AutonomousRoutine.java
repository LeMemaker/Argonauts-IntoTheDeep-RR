package org.firstinspires.ftc.teamcode.routines.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
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

import java.util.Vector;


@Autonomous(name = "Autonomous")
public class AutonomousRoutine extends Routine {
	public ViperSystem viperSystem;
	public ClawSystem clawSystem;
	public DriveSystem driveSystem;

	public Pose2d beginPose;

	public MecanumDrive drive;

	private Action spec1;


	@Override
	public void onInit() {
		super.onInit();
		viperSystem = new ViperSystem(this);
		clawSystem = new ClawSystem(this);
		driveSystem = new DriveSystem(this);

		beginPose = new Pose2d(0, 0, 0);
		drive = new MecanumDrive(hardwareMap, beginPose);



	}

	@Override
	public void onStart() {
		super.onStart();

		Actions.runBlocking(
				drive.actionBuilder(beginPose)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.0253))
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(1)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.257))
						.splineTo(new Vector2d(27, 8), 0)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.lineToXConstantHeading(18)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.07))//test position
						.strafeTo(new Vector2d(24, -36))//needs testing, lining up with first specimen
						.stopAndAdd(new setShoulderPos(clawSystem, 0.02))//test position
						.waitSeconds(0.5)
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(0.25)
						.lineToXConstantHeading(18)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.77))//hovering over the zone
						.waitSeconds(1.5)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.02))//test position
						.strafeTo(new Vector2d(24, -46))//needs testing, lining up with second specimen
						.waitSeconds(0.5)
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(0.25)
						.lineToXConstantHeading(18)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.77))//hovering over the zone
						.waitSeconds(1.5)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.07))//test position
						.lineToXConstantHeading(54)
						.strafeTo(new Vector2d(54, -49.5))
						.strafeTo(new Vector2d(10, -49.5))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.75))//test position
						.strafeTo(new Vector2d(16, -36))
						.waitSeconds(0.5)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.87))
						.strafeTo(new Vector2d(12, -36))
						.waitSeconds(0.5)
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(1)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.257))
						.splineTo(new Vector2d(27, 8), 0)
						.stopAndAdd(new toggleClaw(clawSystem, true))
//						.waitSeconds(1)
//						.turn(-Math.PI/6)//trying to get third sample
//						.splineTo(new Vector2d(25, -47),-Math.PI/6)
//						.stopAndAdd(new setShoulderPos(clawSystem, 0.02))//test position
//						.stopAndAdd(new toggleClaw(clawSystem, false))
//						.waitSeconds(0.5)
//						.turn(Math.PI/6)//correcting back from third sample
//						.lineToXConstantHeading(18)
//						.stopAndAdd(new setShoulderPos(clawSystem, 0.8))//hovering over the zone
//						.waitSeconds(2)
//						.stopAndAdd(new toggleClaw(clawSystem, true))
//						.stopAndAdd(new setShoulderPos(clawSystem, 0.775))//test position
//						.strafeTo(new Vector2d(10, -36))
						.build()
		);
	}

	@Override
	public void onExit() {
		super.onExit();
	}

	public class setShoulderPos implements Action {
		ClawSystem clawSystem;
		double targetPos;

		public setShoulderPos(ClawSystem system, double pos){
			this.clawSystem = system;
			this.targetPos = pos;
		}

		@Override
		public boolean run(@NonNull TelemetryPacket telemetryPacket){
			clawSystem.setShoulderPos(this.targetPos);
			return false;
		}
	}

	public class toggleClaw implements Action {
		ClawSystem clawSystem;
		boolean open;

		public toggleClaw(ClawSystem system, boolean open) {
			this.clawSystem = system;
			this.open = open;
		}

		@Override
		public boolean run(@NonNull TelemetryPacket telemetryPacket) {
			if(open){
				clawSystem.openClaw();
			}
			else {
				clawSystem.closeClaw();
			}
			return false;
		}
	}


}


