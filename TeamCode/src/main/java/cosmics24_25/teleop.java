package cosmics24_25;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import cosmics24_25.subsystems.drivetrain;
//TODO call hardware map


@TeleOp
public class teleop extends LinearOpMode {

    float UP_SPEED = 0.5f;
    float DOWN_SPEED = 0.3f;

    int UP_POSITION = -85;
    int MID_POSITION = -50;
    int DOWN_POSITION = 0;


    @Override
    public void runOpMode() throws InterruptedException {

        //initialized the arm w/ hardware map
  /*      Arm arm = new Arm(hardwareMap, this);

        //initialized the endgame subsystems w/hardware map
        Endgame endgame = new Endgame(hardwareMap, this);

        //initialized the grabber w/ hardware map
        Grabber grabber = new Grabber(hardwareMap);*/

        //initialized the drivetrain w/ hardware map
        drivetrain dt = new drivetrain(hardwareMap);


        waitForStart();



        while (opModeIsActive())  {

            //TABLE OF CONTENTS
            //8 - Drivetrain
            //9 - Telemetry




            //DRIVETRAIN
            dt.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_y, gamepad1.a, gamepad1.b);



            //TELEMETRY
            telemetry.addData("left x", gamepad1.left_stick_x);
            telemetry.addData("left y", gamepad1.left_stick_y);
            telemetry.addData("right x", gamepad1.right_stick_x);
            telemetry.addData("right y", gamepad1.right_stick_y);


            telemetry.update();



        }
    }
}