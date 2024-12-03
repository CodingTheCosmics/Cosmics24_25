package cosmics24_25.tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.List;

import cosmics24_25.subsystems.Drivetrain;
import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.OdometryDrive;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.PoseStorage;
import cosmics24_25.subsystems.Wrist;
import cosmics24_25.subsystems.distanceSensor;

@Disabled
@TeleOp
public class TeleopTest extends LinearOpMode {

    public static final double TIME = 0.4;

    public void runOpMode() {

        OdometryDrive drive = new OdometryDrive(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        drive.setPoseEstimate(PoseStorage.currentPose);

        Pose2d bucketPose = new Pose2d(57, 55.5, Math.toRadians(45));
        Pose2d startPose = PoseStorage.currentPose;




        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive()) {

                Pose2d poseEstimate = drive.getPoseEstimate();

                Vector2d input = new Vector2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x
                ).rotated(-poseEstimate.getHeading());

                drive.setWeightedDrivePower(
                        new Pose2d(
                                input.getX(),
                                input.getY(),
                                -gamepad1.right_stick_x
                        )
                );

                if (gamepad1.a) {

                    TrajectorySequence bucketTraj = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                            //.addDisplacementMarker(() -> lift.liftUpHigh())
                            .lineToLinearHeading(bucketPose)
                            .waitSeconds(TIME)

                            // .addTemporalMarker(() -> grabber.grabberOpen())
                             .waitSeconds(TIME)

                            .build();

                    drive.followTrajectorySequence(bucketTraj);
                    PoseStorage.currentPose = drive.getPoseEstimate();
                }

                drive.update();

            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());

                telemetry.update();


        }
    }
}