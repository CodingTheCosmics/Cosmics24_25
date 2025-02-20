package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import cosmics24_25.subsystems.OdometryDrive;

@Autonomous
//@Disabled
public class RedAuto2_1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OdometryDrive drive = new OdometryDrive(hardwareMap);

        Pose2d startPose = new Pose2d(7, -65, Math.toRadians(-90));

        //TODO FIX ALL ANGLES

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                //cycle specimen

                //go to bar
                .splineTo(new Vector2d(7, -35), Math.toRadians(-90))
                .waitSeconds(1)
                //insert marker to hang specimen (1)

                //go to samples on field
                .strafeTo(new Vector2d(45, -40))
                .waitSeconds(1)
                //insert marker to pick up new sample (2)

                //go to human player zone
                .splineTo(new Vector2d(35, -65), Math.toRadians(-180))
                .waitSeconds(1)
                //insert marker to drop sample into human player zone (2)

                //go back to sample on field
                .strafeTo(new Vector2d(45, -25))
                .waitSeconds(1)
                //insert marker to get new sample (3)

                //go back to human player station
                .strafeTo(new Vector2d(35, -65))
                .waitSeconds(1)
                //insert marker to drop new sample (3) and pick up specimen (2)

                //go to bar
                .splineTo(new Vector2d(7, -35), Math.toRadians(-90))
                .waitSeconds(1)
                //insert marker to hang specimen (2)

                //go to sample on field
                .strafeTo(new Vector2d(35, -35))
                .splineTo(new Vector2d(55, -25), Math.toRadians(90))
                .waitSeconds(1)
                //insert marker to pick up sample (4)

                //go to human player station
                .strafeTo(new Vector2d(35, -65))
                .waitSeconds(1)
                //insert marker to drop off sample (4) and pick up specimen (3)

                //go to bar
                .splineTo(new Vector2d(7, -35), Math.toRadians(-90))
                .waitSeconds(1)
                //insert marker to hang specimen (3)

                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}