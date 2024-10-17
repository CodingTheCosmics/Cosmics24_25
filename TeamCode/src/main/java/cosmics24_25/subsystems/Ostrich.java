package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Ostrich {

    //ostrich heh
    public Servo ostrich;


    //ostrich move
    double ostrichSTART = 0.25;
    double ostrichUP = 0.4;

    double ostrichMID = 0.6;
    double ostrichDOWN = 0.9;

    double currentPosition;




    OpMode opMode;


    //arm definitions and connections to driver hub
    public Ostrich(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        ostrich = hardwareMap.servo.get("ostrich");
        ostrich.setPosition(ostrichSTART);
        ostrichSTART = currentPosition;


    }


    // I HEART OSTRICHES
    public void ostrichUp ()
    {
        ostrich.setPosition(ostrichUP);
        currentPosition = ostrichUP;
        ostrich.setPosition(currentPosition);
    }

    public void ostrichDown ()
    {
        ostrich.setPosition(ostrichDOWN);
        currentPosition = ostrichDOWN;
        ostrich.setPosition(currentPosition);
    }

    public void ostrichMid ()
    {
        ostrich.setPosition(ostrichMID);
        currentPosition = ostrichMID;
        ostrich.setPosition(currentPosition);
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

