package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.OdometryDrive;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.Wrist;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
//@Disabled
public class BlueAuto1_2 extends LinearOpMode {

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


        Pose2d startPose = new Pose2d(15.75, 58.25, Math.toRadians(90));

        Pose2d specimenPose = new Pose2d(15.75, 40.25, Math.toRadians(90));

        Pose2d bucketPose = new Pose2d(57, 55.5, Math.toRadians(45));

        Pose2d fieldPose1 = new Pose2d(50, 35.5, Math.toRadians(270));
        Pose2d fieldPose2 = new Pose2d(42, 21.5, Math.toRadians(0));
        Pose2d fieldPose3 = new Pose2d(53, 23.25, Math.toRadians(0));

        Pose2d parkPose = fieldPose3;

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                //hang specimen and cycle to buckets

                //hang specimen
                .addTemporalMarker(() -> lift.liftUpMedium())
                .lineToLinearHeading(specimenPose)
                .waitSeconds(TIME)



                //drive to pick up sample
                .addTemporalMarker(() -> grabber.grabberOpen())
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .addTemporalMarker(() -> lift.liftReset())
                .lineToLinearHeading(fieldPose1)

                //sample pick up
                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())

                //drive to bucket
                .addTemporalMarker(() -> lift.liftUpHigh())
                .lineToLinearHeading(bucketPose)
                .waitSeconds(TIME*0.25)

                //drop sample
                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME*0.25)



                //drive to next field position
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .addTemporalMarker(() -> lift.liftReset())
                .lineToLinearHeading(fieldPose2)

                //pick up new sample (2)
                .addTemporalMarker(() -> wrist.wristVertical())
                .waitSeconds(TIME*0.5)

                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())

                //drive to bucket
                .addTemporalMarker(() -> lift.liftUpHigh())
                .lineToLinearHeading(bucketPose)
                .waitSeconds(TIME*0.25)

                //drop sample (2)
                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME*0.25)



                //drive to next field position
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .addTemporalMarker(() -> lift.liftReset())
                .lineToLinearHeading(fieldPose3)

                //pick up sample (3)
                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())

                //drive to bucket
                .addTemporalMarker(() -> lift.liftUpHigh())
                .lineToLinearHeading(bucketPose)
                .waitSeconds(TIME)

                //drop sample (3)
                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME*0.25)



                //park
                .lineToLinearHeading(parkPose)


                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}