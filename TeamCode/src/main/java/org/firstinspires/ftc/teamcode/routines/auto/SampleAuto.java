package org.firstinspires.ftc.teamcode.routines.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.routines.Routine;
import org.firstinspires.ftc.teamcode.subsystems.ClawSystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSystem;
import org.firstinspires.ftc.teamcode.subsystems.ViperSystem;


@Autonomous(name = "Basket-Auto")
public class SampleAuto extends Routine {
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
						.stopAndAdd(new setShoulderPos(clawSystem, 0.03))
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(.75)
						.strafeToLinearHeading(new Vector2d(8, 45), -Math.PI/4)
//						.strafeTo(new Vector2d(8, 45))
//						.turn(-Math.PI/4)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.waitSeconds(0.5)
						.stopAndAdd(new setViperPower(viperSystem, 1))
						.waitSeconds(1)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.53))
						.waitSeconds(0.8)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.waitSeconds(0.5)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.stopAndAdd(new setViperPower(viperSystem, -1))
						.waitSeconds(0.7)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.07))//test position
						.strafeToLinearHeading(new Vector2d(24, 36), 0.0)//needs testing, lining up with first sample
						.stopAndAdd(new setShoulderPos(clawSystem, 0.03))//test position
						.waitSeconds(0.5)
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(0.25)
						.strafeToLinearHeading(new Vector2d(8, 45), -Math.PI/4)
//						.strafeTo(new Vector2d(8, 45))
//						.turn(-Math.PI/4)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.waitSeconds(0.5)
						.stopAndAdd(new setViperPower(viperSystem, 1))
						.waitSeconds(1)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.53))
						.waitSeconds(0.8)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.waitSeconds(0.5)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.stopAndAdd(new setViperPower(viperSystem, -1))
						.waitSeconds(0.7)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.07))//test position
						.strafeToLinearHeading(new Vector2d(24, 46), 0.0)//needs testing, lining up with first sample
						.stopAndAdd(new setShoulderPos(clawSystem, 0.03))//test position
						.waitSeconds(0.5)
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(0.25)
						.strafeToLinearHeading(new Vector2d(8, 45), -Math.PI/4)
//						.strafeTo(new Vector2d(8, 45))
//						.turn(-Math.PI/4)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.waitSeconds(0.5)
						.stopAndAdd(new setViperPower(viperSystem, 1))
						.waitSeconds(1)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.53))
						.waitSeconds(0.8)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.waitSeconds(0.5)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.stopAndAdd(new setViperPower(viperSystem, -1))
						.waitSeconds(0.7)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.07))//test position
						.strafeToLinearHeading(new Vector2d(28, 46), Math.PI/4-Math.PI/16)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.03))//test position
						.waitSeconds(0.5)
						.stopAndAdd(new toggleClaw(clawSystem, false))
						.waitSeconds(0.25)
						.strafeToLinearHeading(new Vector2d(8, 45), -Math.PI/4)
//						.strafeTo(new Vector2d(8, 45))
//						.turn(-Math.PI/4)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.waitSeconds(0.5)
						.stopAndAdd(new setViperPower(viperSystem, 1))
						.waitSeconds(1)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.stopAndAdd(new setShoulderPos(clawSystem, 0.53))
						.waitSeconds(0.8)
						.stopAndAdd(new toggleClaw(clawSystem, true))
						.waitSeconds(0.5)
						.stopAndAdd(new setShoulderPos(clawSystem, 0.4))
						.stopAndAdd(new setViperPower(viperSystem, -1))
						.waitSeconds(0.7)//Test it out tmr
						.stopAndAdd(new setViperPower(viperSystem, 0))
						.turn(Math.PI/4)
						.waitSeconds(5)
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

	public class setViperPower implements Action {
		ViperSystem viperSystem;
		double power;

		public setViperPower(ViperSystem system, double power) {
			this.viperSystem = system;
			this.power = power;
		}

		@Override
		public boolean run(@NonNull TelemetryPacket telemetryPacket) {
			viperSystem.getViper_motor_l().setPower(power);
			viperSystem.getViper_motor_r().setPower(power);
			return false;
		}
	}


}


