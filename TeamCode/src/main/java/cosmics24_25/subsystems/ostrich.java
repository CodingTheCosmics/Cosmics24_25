package cosmics24_25.subsystems;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class ostrich {

    //ostrich heh
    public Servo ostrich;


    //ostrich move
    double ostrichUP = 0.7;
    double ostrichDOWN = 0;




    OpMode opMode;


    //arm definitions and connections to driver hub
    public ostrich(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        ostrich = hardwareMap.servo.get("ostrich");
        ostrich.setPosition(ostrichDOWN);


    }


    // I HEART OSTRICHES
    public void ostrichUp () {ostrich.setPosition(ostrichUP);}

    public void ostrichDown () {ostrich.setPosition(ostrichDOWN);}

    //ostrich move


    }

