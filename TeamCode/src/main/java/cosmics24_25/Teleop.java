package cosmics24_25;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.OdometryDrive;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.PoseStorage;
import cosmics24_25.subsystems.Wrist;
import cosmics24_25.subsystems.ColorsSensor;


@TeleOp
public class Teleop extends LinearOpMode {


    public static final double TIME = 0.4;
    public static final float POWER = 1f;



    @Override
    public void runOpMode()  {

        //init lifty lift
        Lift lift = new Lift(hardwareMap, this);

        //init grab crab
        Grabber grabber = new Grabber(hardwareMap, this);

        //init wrist
        Wrist wrist = new Wrist(hardwareMap, this);

        //init ostrich <3
        Ostrich ostrich = new Ostrich(hardwareMap, this);

        //init HOW FAR????
      //  distanceSensor how_far = new distanceSensor(hardwareMap, this);

        //init colors!
        ColorsSensor colors = new ColorsSensor(hardwareMap, this);

        //init new zoom zoom
        OdometryDrive drive = new OdometryDrive(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        drive.setPoseEstimate(PoseStorage.currentPose);


        Pose2d bucketPoseBlue = new Pose2d(54, 54.5, Math.toRadians(45));
        Pose2d bucketPoseRed = new Pose2d(-54, -54.5, Math.toRadians(-135));
        Pose2d backUpBlue = new Pose2d(51, 50.5, Math.toRadians(45));
        Pose2d backUpRed = new Pose2d(-51, -50.5, Math.toRadians(-135));




      //  gamepad1Ex = new GamepadEx(gamepad1);
      //  gamepad2Ex = new GamepadEx(gamepad2);



        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive()) {

            //TABLE OF CONTENTS
            //1 - Lift
            //2 - Grabber
            //3 - Wrist
            //4 - Ostrich
            //5 - Drivetrain
            //6 - Telemetry



            //LIFT
            if (gamepad2.right_stick_button) {
                lift.liftReset();
            }

            lift.liftPower(gamepad2.left_stick_y);

            if (gamepad2.b) {
                lift.liftUpHigh();
            }
            if (gamepad2.x) {
                lift.liftUpMedium();
            }




            //GRABBER
           // if (gamepad2.b) {
          //      grabber.grabberClose();
         //   }

          //  if (gamepad2.x) {
         //       grabber.grabberOpen();
         //   }




            //WRIST
            if (gamepad2.left_bumper) {
                wrist.wristHorizontal();
            }
            if (gamepad2.right_bumper) {
                wrist.wristVertical();
            }



            //OSTRICH
            if (gamepad2.dpad_up) {
                ostrich.ostrichUp();
            }
            if (gamepad2.dpad_down) {
                ostrich.ostrichDown();
            }
            if (gamepad2.dpad_left || gamepad2.dpad_right) {
                ostrich.ostrichMid();
            }


            //automation of what's above
            if (gamepad2.y) {
                ostrich.ostrichUp();
                //sleep(500);

                grabber.grabberOpen();
                //slee(500);
            }

            if (gamepad2.a) {
                ostrich.ostrichMid();
                //sleep(500);

                grabber.grabberOpen();
                //sleep(500);
            }

            if (ostrich.ostrichIsMid() && colors.seesYellow()) {
                colors.colorsTelemetry();

                ostrich.ostrichDown();
                //sleep(500);

                grabber.grabberClose();
                //sleep(500);

                ostrich.ostrichUp();

            }



            //DRIVETRAIN
            Pose2d poseEstimate = drive.getPoseEstimate();

            Vector2d input = new Vector2d(
                    -gamepad1.left_stick_y*0.65,
                    -gamepad1.left_stick_x*0.65
            ).rotated(-poseEstimate.getHeading());

            drive.setWeightedDrivePower(
                    new Pose2d(
                            input.getX(),
                            input.getY(),
                            -gamepad1.right_stick_x*0.5
                    )
            );


            //drive to da bloo spot
            if (gamepad1.x) {

                TrajectorySequence bucketTraj = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .addDisplacementMarker(() -> lift.liftUpHigh())
                        .lineToLinearHeading(bucketPoseBlue)
                        .waitSeconds(TIME)

                        .addTemporalMarker(() -> grabber.grabberOpen())
                        .waitSeconds(TIME)

                        .build();

                drive.followTrajectorySequence(bucketTraj);
                PoseStorage.currentPose = drive.getPoseEstimate();
            }


            //drive to da red sot
            if (gamepad1.b) {

                TrajectorySequence bucketTraj = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .addDisplacementMarker(() -> lift.liftUpHigh())
                        .lineToLinearHeading(bucketPoseBlue)
                        .waitSeconds(TIME)

                        .addTemporalMarker(() -> grabber.grabberOpen())
                        .waitSeconds(TIME)

                        .build();

                drive.followTrajectorySequence(bucketTraj);
                PoseStorage.currentPose = drive.getPoseEstimate();
            }

            drive.update();


          /*  //reset blue
            if (gamepad1.x) {
                drive.setPoseEstimate(new Pose2d(58.25, 0, Math.toRadians(0)));
                drive.update();
            }

            //reset red
            if (gamepad1.b) {
                drive.setPoseEstimate(new Pose2d(-58.25, 0, Math.toRadians(0)));
                drive.update();
            }
            drive.update(); */




                //TELEMETRY
                ostrich.ostrichTelemetry();
                lift.liftTelemetry();
              //  how_far.howFarTelemetry();
                colors.whatColor();
                colors.colorsTelemetry();
                telemetry.addData("x", poseEstimate.getX());
                telemetry.addData("y", poseEstimate.getY());
                telemetry.addData("heading", poseEstimate.getHeading());

                telemetry.update();



            }
        }
    }