package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class BlueAuto2_1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(15, 65, 0);

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                .waitSeconds(1)
                //insert marker to drop preloaded sample into bucket (1)

                .splineTo(new Vector2d(55, 50), Math.toRadians(-90))
                .waitSeconds(1)
                //insert marker to pick up new sample (2)

                .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                .waitSeconds(1)
                //insert marker to drop sample into bucket (2)

                .splineTo(new Vector2d(40, 25), Math.toRadians(0))
                .waitSeconds(1)
                //insert marker to pick up new sample (3)

                .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                .waitSeconds(1)
                //insert marker to drop sample into bucket (3)

                .splineTo(new Vector2d(55, 25), Math.toRadians(0))
                .waitSeconds(1)
                //insert marker to pick up new sample (4)

                .splineTo(new Vector2d(55, 55), Math.toRadians(45))
                .waitSeconds(1)
                //insert marker to drop sample into bucket (4)

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}