package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {

    //hehe grabcrab
    public Servo GRABBER;

    //grabcrab positions
    double grabberCLOSE = 1;

    double grabberOPEN = 0.5;


    OpMode opMode;


    //arm definitions and connections to driver hub
    public Grabber(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        //defnitions and set position
        GRABBER = hardwareMap.servo.get("grabber");
        GRABBER.setPosition(grabberCLOSE);



    }

    //GRAB CRAB GRABS :D

    public void grabberClose () {GRABBER.setPosition(grabberCLOSE);}

    public void grabberOpen () {GRABBER.setPosition(grabberOPEN);}


    }

