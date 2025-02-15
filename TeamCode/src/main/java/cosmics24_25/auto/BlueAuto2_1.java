package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
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
@Disabled
public class BlueAuto2_1 extends LinearOpMode {

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


        Pose2d startPose = new Pose2d(-15.75, 58.25, Math.toRadians(90));

        Pose2d specimenPose1 = new Pose2d(-15.75, 40.25, Math.toRadians(90));
        Pose2d specimenPose2 = new Pose2d(-18.75, 40.25, Math.toRadians(90));
        Pose2d specimenPose3 = new Pose2d(-21.75, 40.25, Math.toRadians(90));

        Pose2d fieldPose1 = new Pose2d(-50, 38.25, Math.toRadians(270));
        Pose2d fieldPose2 = new Pose2d(-42, 22.25, Math.toRadians(0));
       // Pose2d fieldPose3 = new Pose2d(-53, 22, Math.toRadians(0));

        Pose2d observationPose = new Pose2d(-58, 50.25, Math.toRadians(-90));

        Pose2d parkPose = new Pose2d(-40, 58.25, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);


        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                //cycle specimen

                //go to bar and hang specimen
                .addTemporalMarker(() -> lift.liftUpMedium())
                .lineToLinearHeading(specimenPose1)

                //drive to pickup sample
                .addTemporalMarker(() -> grabber.grabberOpen())
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .lineToLinearHeading(fieldPose1)
                .addTemporalMarker(() -> lift.liftReset())

                //pick up sample
                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())

                //drive to observation zone
                .lineToLinearHeading(observationPose)

                //drop sample
                .addTemporalMarker(() -> ostrich.ostrichDown())
                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME*0.25)
                .addTemporalMarker(() -> ostrich.ostrichUp())



                //drive to sample
                .lineToLinearHeading(fieldPose2)

                //pick up new sample (2)
                .addTemporalMarker(() -> wrist.wristVertical())
                .waitSeconds(TIME*0.5)

                .addTemporalMarker(() -> ostrich.ostrichDown())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> grabber.grabberClose())
                .waitSeconds(TIME)

                .addTemporalMarker(() -> ostrich.ostrichUp())

                //drive to observation zone
                .lineToLinearHeading(observationPose)

                //drop sample (2)
                .addTemporalMarker(() -> ostrich.ostrichDown())
                .addTemporalMarker(() -> grabber.grabberOpen())
                .waitSeconds(TIME*0.25)

                //pick up specimen (2)
                .strafeLeft(3)
                .addTemporalMarker(() -> grabber.grabberClose())
                .addTemporalMarker(() -> ostrich.ostrichUp())



                //drive to hang specimen (2)
                .addTemporalMarker(() -> lift.liftUpMedium())
                .lineToLinearHeading(specimenPose2)
                .addTemporalMarker(() -> lift.liftReset())

                //drive to pickup sample
                .addTemporalMarker(() -> grabber.grabberOpen())
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .addTemporalMarker(() -> lift.liftReset())
                .lineToLinearHeading(observationPose)



                //pick up specimen (3)
                .addTemporalMarker(() -> ostrich.ostrichDown())
                .addTemporalMarker(() -> grabber.grabberClose())
                .addTemporalMarker(() -> ostrich.ostrichUp())

                //drive to hang specimen (3)
                .addTemporalMarker(() -> lift.liftUpMedium())
                .lineToLinearHeading(specimenPose3)
                .addTemporalMarker(() -> lift.liftReset())



                //park
                .addTemporalMarker(() -> grabber.grabberOpen())
                .UNSTABLE_addDisplacementMarkerOffset(2, () -> lift.liftDown())
                .lineToLinearHeading(parkPose)
                .addTemporalMarker(() -> lift.liftReset())




                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}