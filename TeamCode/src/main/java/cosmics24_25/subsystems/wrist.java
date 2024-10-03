package cosmics24_25.subsystems;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class wrist {

    //wristttttt
    public Servo wrist;


    //wrist moves!
    double wristVERTICAL = 0;
    double wristHORIZONTAL = 0.5;

    OpMode opMode;


    //arm definitions and connections to driver hub
    public wrist(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        wrist = hardwareMap.servo.get("wrist");
        wrist.setPosition(wristVERTICAL);


    }


    //wrist go up and down
    public void wristVertical ()
    {   wrist.setPosition(wristVERTICAL);}


    public void wristHorizontal ()
    {   wrist.setPosition(wristHORIZONTAL);}


    //wrist move all around!
    //public void wristCircle ()
    //{ wrist.setPosition();}
}
