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

    public static final double TIME = 0.5;

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


            //predefined poses
            Pose2d startPose = new Pose2d(6, 65, 0);

            Vector2d bucketVector = new Vector2d(55, 60);

            Pose2d bucketPose = new Pose2d(55, 60, Math.toRadians(45));

            Pose2d fieldPose1 = new Pose2d(50, 45, Math.toRadians(270));
            Pose2d fieldPose2 = new Pose2d(40, 29, Math.toRadians(0));
            Pose2d fieldPose3 = new Pose2d(50, 30, Math.toRadians(0));




            drive.setPoseEstimate(startPose);

            TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                    //lift and drive to bucket
                    .splineTo(bucketVector, Math.toRadians(45))
                    .addTemporalMarker(2, () -> lift.liftMovePosition(0.5f, 500))
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberOpen())
                    .waitSeconds(TIME)

                    //lift down
                    .waitSeconds(TIME)


                    //sample dropped into bucket (1)


                    //drive to field
                    .lineToLinearHeading(fieldPose1)

                    .addTemporalMarker(() -> ostrich.ostrichDown())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberClose())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> ostrich.ostrichUp())
                    .waitSeconds(TIME)


                    //sample picked up from field (2)


                    //drive to bucket
                    .lineToLinearHeading(bucketPose)

                    //lift here
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberOpen())
                    .waitSeconds(TIME)

                    //lift down
                    .waitSeconds(TIME)


                    //sample dropped into bucket (2)


                    //drive to field
                    .lineToLinearHeading(fieldPose2)

                    .addTemporalMarker(() -> wrist.wristHorizontal())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> ostrich.ostrichDown())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberClose())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> ostrich.ostrichUp())
                    .waitSeconds(TIME)


                    //picked up new sample (3)


                    //drive to bucket
                    .lineToLinearHeading(bucketPose)

                    //lift here
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberOpen())
                    .waitSeconds(TIME)

                    //lift down
                    .waitSeconds(TIME)


                    //sample dropped into bucket (3)


                    //drive to field
                    .lineToLinearHeading(fieldPose3)

                    .addTemporalMarker(() -> ostrich.ostrichDown())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberClose())
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> ostrich.ostrichUp())
                    .waitSeconds(TIME)


                    //picked up new sample (4)


                    //drive to bucket
                    .lineToLinearHeading(bucketPose)

                    //lift here
                    .waitSeconds(TIME)

                    .addTemporalMarker(() -> grabber.grabberOpen())
                    .waitSeconds(TIME)

                    //lift down
                    .waitSeconds(TIME)

                    //dropped sample into bucket (4)

                    .build();

            waitForStart();
                grabber.grabberClose();
                ostrich.ostrichUp();
                wrist.wristVertical();


            if (!isStopRequested())

                drive.followTrajectorySequence(trajSeq);

            PoseStorage.currentPose = drive.getPoseEstimate();
        }
    }
