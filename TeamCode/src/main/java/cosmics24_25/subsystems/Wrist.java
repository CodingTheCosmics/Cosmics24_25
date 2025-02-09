package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Wrist {

    //wristttttt
    public Servo wrist;


    //wrist moves!
    public static final double WRIST_VERTICAL = 0;
    public static final double WRIST_HORIZONTAL = 1;


    double currentPosition;



    OpMode opMode;


    //arm definitions and connections to driver hub
    public Wrist(HardwareMap hardwareMap, OpMode opMode)
    {

        this.opMode = opMode;

        wrist = hardwareMap.servo.get("wrist");
      //  wrist.setPosition(WRIST_VERTICAL);
      //  currentPosition = WRIST_VERTICAL;


    }


    //wrist go up and down
    public void wristVertical ()
    {
        wrist.setPosition(WRIST_VERTICAL);
        currentPosition = WRIST_VERTICAL;
        wrist.setPosition(currentPosition);
    }


    public void wristHorizontal ()
    {
        wrist.setPosition(WRIST_HORIZONTAL);
        currentPosition = WRIST_HORIZONTAL;
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
