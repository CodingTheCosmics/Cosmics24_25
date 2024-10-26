package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import cosmics24_25.subsystems.OdometryDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.PoseStorage;
import cosmics24_25.subsystems.Wrist;


@Autonomous
public class BlueAuto1_1 extends LinearOpMode {

        @Override
        public void runOpMode() throws InterruptedException {
            //init lifty lifty lifty
            Lift lift = new Lift(hardwareMap, this);

            //init grab crab
            Grabber grabber = new Grabber(hardwareMap, this);

            //init wrist
            Wrist wrist = new Wrist(hardwareMap, this);

            //init ostrich <3
            Ostrich ostrich = new Ostrich(hardwareMap, this);

            //init drivetrain
            OdometryDrive drive = new OdometryDrive(hardwareMap);

            Pose2d startPose = new Pose2d(15, 65, 0);

            drive.setPoseEstimate(startPose);

            TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                    .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                    //lift here
                    .addTemporalMarker(() -> grabber.grabberOpen())
                    //lift down
                    .waitSeconds(0.5)

                    //sample dropped into bucket

                    .splineTo(new Vector2d(55, 50), Math.toRadians(-90))
                    .addTemporalMarker(() -> wrist.wristHorizontal())
                    .addTemporalMarker(() -> ostrich.ostrichDown())
                    .addTemporalMarker(() -> grabber.grabberClose())
                    .addTemporalMarker(() -> ostrich.ostrichUp())
                    .waitSeconds(0.5)

                    //picked up new sample (2)

                    .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                    //lift here
                    .addTemporalMarker(() -> grabber.grabberOpen())
                    //lift down
                    .waitSeconds(0.5)

                    //sample dropped into bucket (2)

                    .splineTo(new Vector2d(40, 25), Math.toRadians(0))
                    .addTemporalMarker(() -> ostrich.ostrichDown())
                    .addTemporalMarker(() -> grabber.grabberClose())
                    .addTemporalMarker(() -> ostrich.ostrichUp())
                    .waitSeconds(0.5)

                    //picked up new sample (3)

                    .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                    //lift here
                    .addTemporalMarker(() -> grabber.grabberOpen())
                    //lift down
                    .waitSeconds(0.5)

                    //sample dropped into bucket (3)

                    .splineTo(new Vector2d(55, 25), Math.toRadians(0))
                    .addTemporalMarker(() -> ostrich.ostrichDown())
                    .addTemporalMarker(() -> grabber.grabberClose())
                    .addTemporalMarker(() -> ostrich.ostrichUp())
                    .waitSeconds(0.5)

                    //picked up new sample (4)

                    .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                    //lift here
                    .addTemporalMarker(() -> grabber.grabberOpen())
                    //lift down
                    .waitSeconds(0.5)

                    //dropped sample into bucket (4)

                    .build();

            waitForStart();

            if (!isStopRequested())
                drive.followTrajectorySequence(trajSeq);

            PoseStorage.currentPose = drive.getPoseEstimate();
        }
    }
