package cosmics24_25.subsystems;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class lift {

    //arm motors defined
    public DcMotorEx LIFT;


    //default power for overrides
    public static float DEFAULT_POWER = 0.5f;


    //Proportional: higher, faster
    public static final double NEW_P = 40.000000;

    //Integral: Lower, less overshoot
    public static final double NEW_I = 0.01;

    //Derivative: rate of rate of change (acceleration) Keep 0
    public static final double NEW_D = 0;



    OpMode opMode;


    //arm definitions and connections to driver hub
    public lift(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        LIFT = (DcMotorEx) hardwareMap.dcMotor.get("LIFT");

        LIFT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LIFT.setTargetPosition(0);
        LIFT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //LIFT.setPower(0);




      /* PIDCoefficients pidOrig = LIFT.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients using methods included with DcMotorEx class.
       PIDCoefficients pidNew = new PIDCoefficients(NEW_P, NEW_I, NEW_D);
       LIFT.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidNew); */


    }


    public void goUp(float power) {LIFT.setPower(power);}



    //grabber arm move to position
    public void liftMovePosition (float power, int targetPosition) {

        LIFT.setTargetPosition(targetPosition);

        LIFT.setPower(power);

    }


    //arm ONLY sleep
    public void LIFTSLEEP (int time) {
        sleep(time);
    }



    public void armTelemetry () {
        opMode.telemetry.addData("lift position", LIFT.getCurrentPosition());

    }

}
