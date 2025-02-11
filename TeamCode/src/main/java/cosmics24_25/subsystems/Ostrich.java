package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Ostrich {

    //ostrich heh
    public Servo ostrich;


    //ostrich move
    public static final double OSTRICH_START = 0.1;

    public static final double OSTRICH_UP = 0.2;

    public static final double OSTRICH_MID = 0.5;

    public static final double OSTRICH_DOWN = 0.57;

    double currentPosition;




    OpMode opMode;


    //arm definitions and connections to driver hub
    public Ostrich(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        ostrich = hardwareMap.servo.get("ostrich");
      //  ostrich.setPosition(OSTRICH_START);
      //  currentPosition = OSTRICH_START;


    }


    // I HEART OSTRICHES
    public void ostrichUp ()
    {
        ostrich.setPosition(OSTRICH_UP);
        currentPosition = OSTRICH_UP;
    }

    public void ostrichDown ()
    {
        ostrich.setPosition(OSTRICH_DOWN);
        currentPosition = OSTRICH_DOWN;
    }

    public void ostrichMid ()
    {
        ostrich.setPosition(OSTRICH_MID);
        currentPosition = OSTRICH_MID;
    }

    public boolean ostrichIsMid () {
        return ostrich.getPosition() == OSTRICH_MID;
    }

    //ostrich move
    public void ostrichMoveDown ()
    {
        currentPosition -= 0.1;
        ostrich.setPosition(currentPosition);
    }

    public void ostrichMoveUp ()
    {
        currentPosition += 0.1;
        ostrich.setPosition(currentPosition);
    }


    public void ostrichTelemetry ()
    {
        opMode.telemetry.addData("ostrich arm position", ostrich.getPosition());
    }



    }

