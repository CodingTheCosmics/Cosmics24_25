package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Wrist {

    //wristttttt
    public Servo wrist;


    //wrist moves!
    double wristVERTICAL = 1;
    double wristHORIZONTAL = 0;


    double currentPosition;



    OpMode opMode;


    //arm definitions and connections to driver hub
    public Wrist(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        wrist = hardwareMap.servo.get("wrist");
        wrist.setPosition(wristVERTICAL);
        currentPosition = wristVERTICAL;


    }


    //wrist go up and down
    public void wristVertical ()
    {
        wrist.setPosition(wristVERTICAL);
        currentPosition = wristVERTICAL;
        wrist.setPosition(currentPosition);
    }


    public void wristHorizontal ()
    {
        wrist.setPosition(wristHORIZONTAL);
        currentPosition = wristHORIZONTAL;
        wrist.setPosition(currentPosition);
    }

    //move continuously
    public void rotateLeft ()
    {
        currentPosition += 0.1;
        wrist.setPosition(currentPosition);
    }

    //move continuously backwards
    public void rotateRight ()
    {
        currentPosition -= 0.1;
        wrist.setPosition(currentPosition);
    }


}
