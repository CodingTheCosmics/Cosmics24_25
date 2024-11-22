package cosmics24_25;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.ArrayList;
import java.util.List;

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

    List<LynxModule> allHubs;

    GamepadEx gamepad1Ex;
    GamepadEx gamepad2Ex;


    public static final double TIME = 0.5;
    public static final float POWER = 0.65f;



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
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


      //  drive.setPoseEstimate(PoseStorage.currentPose);



        //trajectories for odometry

        gamepad1Ex = new GamepadEx(gamepad1);
        gamepad2Ex = new GamepadEx(gamepad2);


        waitForStart();


        while (opModeIsActive()) {

            //TABLE OF CONTENTS
            //1 - Lift
            //2 - Grabber
            //3 - Wrist
            //4 - Ostrich
            //5 - Drivetrain
            //6 - Telemetry

            gamepad1Ex.readButtons();
            gamepad2Ex.readButtons();



            //LIFT
            //LIFTY LIFT
           /* if (gamepad1Ex.getButton(GamepadKeys.Button.Y) || gamepad2Ex.getButton(GamepadKeys.Button.Y)) {

                grabber.grabberClose();
                wrist.wristHorizontal();

                lift.liftUpHigh(POWER);
                lift.liftSleep(5000);


                //grabber.grabberOpen();

            } */

            lift.liftPower(-gamepad2Ex.getRightY()); //|| -gamepad2Ex.getLeftY());



            //GRABBER
            //GRAB CRAB
            if (gamepad2Ex.getButton(GamepadKeys.Button.X)) {
                grabber.grabberClose();
            }
            if (gamepad2Ex.getButton(GamepadKeys.Button.B)) {
                grabber.grabberOpen();
            }



            //WRIST
            //WRISTY WRISTY WRISTY
            if (gamepad2Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                wrist.wristHorizontal();
            }
            if (gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                wrist.wristVertical();
            }



            //OSTRICH
            //*ostrich sound*
            if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_UP)) {
                ostrich.ostrichUp();
            }
            if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                ostrich.ostrichDown();
            }
            if (gamepad2Ex.getButton(GamepadKeys.Button.DPAD_LEFT) || gamepad2Ex.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
                ostrich.ostrichMid();
            }




            //DRIVETRAIN
           dt.move(gamepad1Ex.getLeftX(), -gamepad1Ex.getLeftY(), gamepad1Ex.getRightX(),
                   gamepad1Ex.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON), gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER));



           // drive.update();



                //TELEMETRY

                ostrich.ostrichTelemetry();
                lift.liftTelemetry();
                how_far.howFarTelemetry();

                telemetry.update();


            }
        }
    }