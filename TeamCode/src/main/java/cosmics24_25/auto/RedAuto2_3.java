package cosmics24_25.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.OdometryDrive;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.PoseStorage;
import cosmics24_25.subsystems.Wrist;


@Autonomous
public class RedAuto2_3 extends LinearOpMode {

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

        Pose2d startPose = new Pose2d(18, -58.25, Math.toRadians(90));



        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)

                //hang specimen and park


                .strafeTo(new Vector2d(40, -58.25))
                .waitSeconds(30)


                .build();

        waitForStart();
        grabber.grabberOpen();
        ostrich.ostrichUp();
        wrist.wristHorizontal();


        if (!isStopRequested())
            lift.liftTelemetry();
        telemetry.update();

        drive.followTrajectorySequence(trajSeq);


        PoseStorage.currentPose = drive.getPoseEstimate();

    }
}
