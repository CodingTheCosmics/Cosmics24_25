package cosmics24_25;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

import cosmics24_25.subsystems.Drivetrain;
import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.OdometryDrive;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.PoseStorage;
import cosmics24_25.subsystems.Wrist;
import cosmics24_25.subsystems.distanceSensor;


@TeleOp
public class Teleop extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        //init lifty lift
        Lift lift = new Lift(hardwareMap, this);

        //init grab crab
        Grabber grabber = new Grabber(hardwareMap, this);

        //init wrist
        Wrist wrist = new Wrist(hardwareMap, this);

        //init ostrich <3
        Ostrich ostrich = new Ostrich(hardwareMap, this);

        //init zoom zoom
        Drivetrain dt = new Drivetrain(hardwareMap);

        //init HOW FAR????
        distanceSensor how_far = new distanceSensor(hardwareMap, this);

        //init new zoom zoom
        OdometryDrive drive = new OdometryDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);

        //trajectories for odometry
        TrajectorySequence goToBucket = drive.trajectorySequenceBuilder(PoseStorage.currentPose)
            .splineTo(new Vector2d(55, 55), Math.toRadians(45))
            //lift here
            .addTemporalMarker(() -> grabber.grabberOpen())
            //lift down
            .waitSeconds(0.5)

            .build();


        waitForStart();


        while (opModeIsActive()) {

            //TABLE OF CONTENTS
            //1 - Lift
            //2 - Grabber
            //3 - Wrist
            //4 - Ostrich
            //5 - Drivetrain
            //6 - Telemetry


            //LIFT
            //LIFTY LIFT
            if (gamepad2.y) {
                lift.liftMovePosition(0.75f, 1900);
            }
           if (gamepad2.a) {
                lift.goUp(0f);
            }

            lift.goUp(-gamepad2.left_stick_y);



            //GRABBER
            //GRAB CRAB
            if (gamepad2.x) {
                grabber.grabberClose();
            }
            if (gamepad2.b) {
                grabber.grabberOpen();
            }



            //WRIST
            //WRISTY WRISTY WRISTY
            if (gamepad2.left_bumper) {
                wrist.wristHorizontal();
            }
            if (gamepad2.right_bumper) {
                wrist.wristVertical();
            }

            //full rotation
            if (gamepad1.left_trigger >= 0.5f) {
                wrist.rotateLeft();
            }
            if (gamepad1.right_trigger >= 0.5f) {
                wrist.rotateLeft();
            }


            //OSTRICH
            //*ostrich sound*
            if (gamepad2.dpad_up) {
                ostrich.ostrichUp();
            }
            if (gamepad2.dpad_down) {
                ostrich.ostrichDown();
            }

            //full rotation
            if (gamepad2.dpad_left) {
                ostrich.ostrichMid();
            }
            if (gamepad2.dpad_right) {
                ostrich.ostrichMid();
            }



            //DRIVETRAIN
            dt.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad1.left_bumper);

            drive.update();
            Pose2d myPose = drive.getPoseEstimate();

            if (gamepad1.a) {
                drive.followTrajectorySequence(goToBucket);
            }


                //TELEMETRY
                telemetry.addData("x", myPose.getX());
                telemetry.addData("y", myPose.getY());
                telemetry.addData("heading", myPose.getHeading());

                ostrich.ostrichTelemetry();
                lift.liftTelemetry();
                how_far.howFarTelemetry();

                telemetry.update();


            }
        }
    }