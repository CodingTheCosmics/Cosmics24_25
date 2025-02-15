package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import cosmics24_25.subsystems.OdometryDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.PoseStorage;
import cosmics24_25.subsystems.Wrist;


@Autonomous
public class RedAuto1_1 extends LinearOpMode {

    private static final double TIME = 0.5;
    private static final double OFFSET = 3;

    //predefined poses/vector
    private final Pose2d START_POSE = new Pose2d(-30.75, -58.25, Math.toRadians(180));

    private static final Vector2d BUCKET_VECTOR = new Vector2d(-56, -50);

    private static final Pose2d BUCKET_POSE = new Pose2d(-55, -51, Math.toRadians(-135));

    private static final Pose2d FIELD_POSE_1 = new Pose2d(-52, -43, Math.toRadians(90));
    private static final Pose2d FIELD_POSE_2 = new Pose2d(-42, -22, Math.toRadians(180));
    private static final Pose2d FIELD_POSE_3 = new Pose2d(-51, -23, Math.toRadians(180));

    private static final Pose2d PARK_POSE = FIELD_POSE_3;

    TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(
            new TranslationalVelocityConstraint(15),
            new AngularVelocityConstraint(1.5)
    ));


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





        drive.setPoseEstimate(START_POSE);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(START_POSE)

                //cycle to buckets

                //lift and drive to bucket

                .addDisplacementMarker(() -> lift.liftUpHigh())

                //slow down girlfriend
                .setVelConstraint(slowConstraint)

                .splineTo(BUCKET_VECTOR, Math.toRadians(-135))
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)


                //sample dropped into bucket (1)


                //lift down and drive to field
                .UNSTABLE_addDisplacementMarkerOffset(OFFSET,() -> lift.liftDown())
                .lineToLinearHeading(FIELD_POSE_1)
                .waitSeconds(TIME/2)


                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())


                //sample picked up from field (2)


                //drive to bucket
                .addTemporalMarker(() -> lift.liftReset())
                .addTemporalMarker(() -> lift.liftUpHigh())
                .waitSeconds(TIME*0.75)

                .lineToLinearHeading(BUCKET_POSE)
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)


                //sample dropped into bucket (2)


                //lift down and drive to field
                .UNSTABLE_addDisplacementMarkerOffset(OFFSET, () -> lift.liftDown())
                .lineToLinearHeading(FIELD_POSE_2)
                .waitSeconds(TIME/2)


                .addTemporalMarker(() -> wrist.wristVertical())
                .waitSeconds(TIME*0.5)

                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())
                .addTemporalMarker(() -> wrist.wristHorizontal())


                //picked up new sample (3)


                //drive to bucket
                .addTemporalMarker(() -> lift.liftReset())
                .addTemporalMarker(() -> lift.liftUpHigh())
                .lineToLinearHeading(BUCKET_POSE)

                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)


                //sample dropped into bucket (3)


                //drive to field
                .UNSTABLE_addDisplacementMarkerOffset(OFFSET, () -> lift.liftDown())
                .lineToLinearHeading(FIELD_POSE_3)
                .waitSeconds(TIME/2)



                .addTemporalMarker(() -> ostrich.ostrichMid())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> wrist.wristVertical())
                .waitSeconds(TIME/2)

                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME/2)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())
                .addTemporalMarker(() -> wrist.wristHorizontal())



                //picked up new sample (4)


                //drive to bucket
                .addTemporalMarker(() -> lift.liftReset())
                .addTemporalMarker(() -> lift.liftUpHigh())

                .lineToLinearHeading(BUCKET_POSE)
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME)

                //lift down
                .UNSTABLE_addDisplacementMarkerOffset(OFFSET, () -> lift.liftDown())
                .lineToLinearHeading(PARK_POSE)



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
