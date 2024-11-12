package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {

    //hehe grabcrab
    public Servo grabber;

    //grabcrab positions
    public static final double GRABBER_CLOSE = 1;

    public static final double GRABBER_OPEN = 0.5;


    OpMode opMode;


    //arm definitions and connections to driver hub
    public Grabber(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        //defnitions and set position
        grabber = hardwareMap.servo.get("grabber");
        grabber.setPosition(GRABBER_CLOSE);


    }

    //GRAB CRAB GRABS :D

    public void grabberClose () {
        grabber.setPosition(GRABBER_CLOSE);}

    public void grabberOpen () {
        grabber.setPosition(GRABBER_OPEN);}


    }

