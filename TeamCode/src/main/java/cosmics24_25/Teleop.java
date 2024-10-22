package cosmics24_25;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;


import cosmics24_25.subsystems.Drivetrain;
import cosmics24_25.subsystems.Lift;
import cosmics24_25.subsystems.Grabber;
import cosmics24_25.subsystems.Ostrich;
import cosmics24_25.subsystems.Wrist;
import cosmics24_25.subsystems.Distance_Sensor;


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
        Distance_Sensor howFar = new Distance_Sensor(hardwareMap, this);


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
            if (gamepad1.y || gamepad2.y) {
                lift.liftMovePosition(0.75f, 1900);
            }
           if (gamepad1.a || gamepad2.a) {
                lift.goUp(0f);
            }

            lift.goUp(-gamepad2.left_stick_y);



            //GRABBER
            //GRAB CRAB
            if (gamepad1.x || gamepad2.x) {
                grabber.grabberClose();
            }
            if (gamepad1.b || gamepad2.b) {
                grabber.grabberOpen();
            }



            //WRIST
            //WRISTY WRISTY WRISTY
            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                wrist.wristHorizontal();
            }
            if (gamepad1.right_bumper || gamepad2.right_bumper) {
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
            if (gamepad1.dpad_up || gamepad2.dpad_up) {
                ostrich.ostrichUp();
            }
            if (gamepad1.dpad_down || gamepad2.dpad_down) {
                ostrich.ostrichDown();
            }

            //full rotation
            if (gamepad1.dpad_left || gamepad2.dpad_left) {
                ostrich.ostrichMid();
            }
            if (gamepad1.dpad_right || gamepad2.dpad_right) {
                ostrich.ostrichMid();
            }



            //DRIVETRAIN
            dt.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button);


                //TELEMETRY
                telemetry.addData("left x", gamepad1.left_stick_x);
                telemetry.addData("left y", gamepad1.left_stick_y);
                telemetry.addData("right x", gamepad1.right_stick_x);
                telemetry.addData("right y", gamepad1.right_stick_y);

                telemetry.addData("dpad up", gamepad1.dpad_up);
                telemetry.addData("dpad down", gamepad1.dpad_down);

                ostrich.ostrichTelemetry();
                lift.liftTelemetry();
                howFar.howFarTelemetry();

                telemetry.update();


            }
        }
    }