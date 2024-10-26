package GAP24_25;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import GAP24_25.claw.Claw;


@TeleOp
public class TeleopGAP extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        //init claw
        Claw claw = new Claw(hardwareMap, this);


        waitForStart();


        while (opModeIsActive()) {

            //claw opens
            if (gamepad1.b && gamepad2.b) {
                claw.clawOpen();
            }

            if (gamepad1.x && gamepad2.x) {
                claw.clawClose();
            }


            claw.clawTelemetry();


            telemetry.update();




        }
    }
}