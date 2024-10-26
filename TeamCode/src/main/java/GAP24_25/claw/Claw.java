package GAP24_25.claw;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;



public class Claw {

    //claw or whatever
    public Servo claw;

    //claw positions
    double CLAW_CLOSE = 0.75;

    double CLAW_OPEN = 0;


    OpMode opMode;

    public Claw(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        //defnitions and set position
        claw = hardwareMap.servo.get("claw");
        claw.setPosition(CLAW_CLOSE);


    }

    //do the thing
    public void clawClose ()
        {
            claw.setPosition(CLAW_CLOSE);
        }

    public void clawOpen()
        {
            claw.setPosition(CLAW_OPEN);
        }


    public void clawTelemetry ()
    {
        opMode.telemetry.addData("claw position", claw.getPosition());

    }

}
