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
public class RedAuto1_1 extends LinearOpMode {

    public static final double TIME = 0.5;
    public static final float POWER = 0.8f;

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


        //predefined poses/vectors
        Pose2d startPose = new Pose2d(-30.75, -58.25, Math.toRadians(180));

        Vector2d bucketVector = new Vector2d(-58, -55.5);

        Pose2d bucketPose = new Pose2d(-57, -55.5, Math.toRadians(-135));

        Pose2d fieldPose1 = new Pose2d(-50, -38.25, Math.toRadians(90));
        Pose2d fieldPose2 = new Pose2d(-42, -22.25, Math.toRadians(180));
        Pose2d fieldPose3 = new Pose2d(-53, -22, Math.toRadians(180));

        Pose2d parkPose = new Pose2d(-30, 6.75, Math.toRadians(180));




        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                //lift and drive to bucket

                .addDisplacementMarker(() -> lift.liftUpHigh(POWER))
                .splineTo(bucketVector, Math.toRadians(-135))
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)


                //sample dropped into bucket (1)


                //lift down and drive to field
                .UNSTABLE_addDisplacementMarkerOffset(2,() -> lift.liftDown())
                .lineToLinearHeading(fieldPose1)


                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())
                .waitSeconds(TIME)


                //sample picked up from field (2)


                //drive to bucket
                .addTemporalMarker(() -> lift.liftUpHigh(POWER))
                .waitSeconds(TIME*0.75)

                .lineToLinearHeading(bucketPose)
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)


                //sample dropped into bucket (2)


                //lift down and drive to field
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .lineToLinearHeading(fieldPose2)

                .addTemporalMarker(() -> wrist.wristVertical())
                .waitSeconds(TIME*0.5)

                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())
                .waitSeconds(TIME)


                //picked up new sample (3)


                //drive to bucket
                .addTemporalMarker(() -> lift.liftUpHigh(POWER))
                .addTemporalMarker(() -> wrist.wristHorizontal())
                .lineToLinearHeading(bucketPose)

                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)


                //sample dropped into bucket (3)


                //drive to field
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .lineToLinearHeading(fieldPose3)


                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())
                .waitSeconds(TIME)


                //picked up new sample (4)


                //drive to bucket
                .addTemporalMarker(() -> lift.liftUpHigh(POWER))

                .lineToLinearHeading(bucketPose)
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)

                //lift down
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .lineToLinearHeading(parkPose)

                //dropped sample into bucket (4)

                .build();

        waitForStart();
        grabber.grabberClose();
        ostrich.ostrichUp();
        wrist.wristHorizontal();


        if (!isStopRequested())
            lift.liftTelemetry();
        telemetry.update();

        drive.followTrajectorySequence(trajSeq);


        PoseStorage.currentPose = drive.getPoseEstimate();

    }
}
