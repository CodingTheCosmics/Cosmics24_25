package cosmics24_25.subsystems;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;


public class Lift {

    //arm motors defined
    public DcMotorEx lift;


    //default power for overrides
    public static final float DEFAULT_POWER = 0.5f;

    public static final float MAX_EXTEND = 2000f;

    public static final float MAX_DOWN = 0f;


    //Proportional: higher, faster
  /*  public static final double NEW_P = 40.000000;

    //Integral: Lower, less overshoot
    public static final double NEW_I = 0.01;

    //Derivative: rate of rate of change (acceleration) Keep 0
    public static final double NEW_D = 1;n*/



    OpMode opMode;


    //arm definitions and connections to driver hub
    public Lift(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        lift = (DcMotorEx) hardwareMap.dcMotor.get("LIFT");

        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(0);

        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



       PIDCoefficients pidOrig = lift.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients using methods included with DcMotorEx class.
      // PIDCoefficients pidNew = new PIDCoefficients(NEW_P, NEW_I, NEW_D);
      // lift.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidNew);


    }


    public void goUp(double power)
    {
        lift.setPower(power);

    }



    //grabber arm move to position
    public void liftMovePosition (float power, int targetPosition)
    {
        lift.setTargetPosition(targetPosition);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //move
        //for auto? - && ((LinearOpMode)opMode).opModeIsActive()
        while (lift.isBusy())
        {
            lift.setPower(power);
        }
        lift.setPower(0);
    }


    //arm ONLY sleep
    public void LIFTSLEEP (int time)
    {
        sleep(time);
    }



    public void liftTelemetry ()
    {
        opMode.telemetry.addData("lift position", lift.getCurrentPosition());
        opMode.telemetry.addData("lift speed", lift.getVelocity());

    }

}
