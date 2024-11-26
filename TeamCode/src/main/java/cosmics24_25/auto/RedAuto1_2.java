package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import cosmics24_25.subsystems.OdometryDrive;

@Autonomous
@Disabled
public class RedAuto1_2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OdometryDrive drive = new OdometryDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-15, -65, Math.toRadians(180));

        //TODO FIX ANGLES

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                //hang specimen and cycle to buckets

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}