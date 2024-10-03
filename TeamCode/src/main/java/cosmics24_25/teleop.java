package cosmics24_25;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import cosmics24_25.subsystems.drivetrain;
import cosmics24_25.subsystems.lift;
import cosmics24_25.subsystems.grabber;
import cosmics24_25.subsystems.ostrich;
import cosmics24_25.subsystems.wrist;


@TeleOp
public class teleop extends LinearOpMode {

    float UP_SPEED = 0.5f;
    float DOWN_SPEED = 0.3f;

    int UP_POSITION = -85;
    int MID_POSITION = -50;
    int DOWN_POSITION = 0;


    @Override
    public void runOpMode() throws InterruptedException {

        //init lifty lift
        lift LIFT = new lift(hardwareMap, this);

        //init grab crab
        grabber GRABBER = new grabber(hardwareMap, this);

        //init wrist
        wrist WRIST = new wrist(hardwareMap,this);

        //init ostrich <3
        ostrich OSTRICH = new ostrich(hardwareMap, this);

        //init zoom zoom
        drivetrain dt = new drivetrain(hardwareMap);


        waitForStart();



        while (opModeIsActive())  {

            //TABLE OF CONTENTS
            //1 - Lift
            //2 - Grabber
            //3 - Wrist
            //4 - Ostrich
            //5 - Drivetrain
            //6 - Telemetry



            //LIFTY LIFT
            if (gamepad1.dpad_up)
            {   LIFT.liftMovePosition(0.5f, 500); }
            if (gamepad1.dpad_down)
            {  LIFT.liftMovePosition(0.5f, -500); }



            //GRAB CRAB
            if (gamepad2.a)
            {   GRABBER.grabberClose();}
            if (gamepad2.y)
            {   GRABBER.grabberOpen();}


            //WRISTY WRISTY WRISTY
            if (gamepad2.dpad_left)
            {   WRIST.wristHorizontal();}
            if (gamepad2.dpad_right)
            {   WRIST.wristVertical();}

            //insert full rotation here


            //*ostrich sound*
            if (gamepad2.dpad_up)
            {   OSTRICH.ostrichUp();}
            if (gamepad2.dpad_down)
            {   OSTRICH.ostrichDown();}



            //DRIVETRAIN
            dt.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.x, gamepad1.b);



            //TELEMETRY
            telemetry.addData("left x", gamepad1.left_stick_x);
            telemetry.addData("left y", gamepad1.left_stick_y);
            telemetry.addData("right x", gamepad1.right_stick_x);
            telemetry.addData("right y", gamepad1.right_stick_y);


            telemetry.update();



        }
    }
}