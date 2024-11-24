package cosmics24_25.auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import cosmics24_25.subsystems.OdometryDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Disabled
public class BlueAuto1_3 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OdometryDrive drive = new OdometryDrive(hardwareMap);

        Pose2d startPose = new Pose2d(18, 58.25, 0);

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                //hang specimen and park
                .strafeTo(new Vector2d(34, 58.25))
                .waitSeconds(30)

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}
