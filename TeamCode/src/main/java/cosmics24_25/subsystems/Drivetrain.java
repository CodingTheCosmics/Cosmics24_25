package cosmics24_25.subsystems;

import static android.os.SystemClock.sleep;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivetrain {

    public DcMotorEx backRight;
    public DcMotorEx frontRight;
    public DcMotorEx backLeft;
    public DcMotorEx frontLeft;
    public IMU imu;


    public Drivetrain(HardwareMap hardwareMap) {


        //driver hub connections
        backRight = (DcMotorEx) hardwareMap.dcMotor.get("BR");
        frontRight = (DcMotorEx) hardwareMap.dcMotor.get("FR");
        backLeft = (DcMotorEx) hardwareMap.dcMotor.get("BL");
        frontLeft = (DcMotorEx) hardwareMap.dcMotor.get("FL");


        //directions
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        ));

        imu.initialize(parameters);

    }





//teleop movement for mecanum drivetrain
  public void move(double x, double y, double rx, boolean reset, boolean goFast) {

      //reset button
      if (reset) {
          imu.resetYaw();
      }

      //unit definitions
      double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

      //key algorithm to translate into field coordinates
      double rotX = y * Math.cos(-botHeading) - x * Math.sin(-botHeading);
      double rotY = y * Math.sin(-botHeading) + x * Math.cos(-botHeading);


      //decrease power
      double reductionFactor = 0.4;
      double normalPower = 0.65;


      //to go faster
      if (goFast) {
          //actual power
          frontRight.setPower((-rotY - rotX - rx) * normalPower);
          backRight.setPower((-rotY + rotX + rx) * normalPower);
          frontLeft.setPower((rotY - rotX + rx) * normalPower);
          backLeft.setPower((rotY + rotX - rx) * normalPower);

      }

      else {
          //actual power
          frontRight.setPower((-rotY - rotX - rx) * reductionFactor);
          backRight.setPower((-rotY + rotX + rx) * reductionFactor);
          frontLeft.setPower((rotY - rotX + rx) * reductionFactor);
          backLeft.setPower((rotY + rotX - rx) * reductionFactor);

      }



  }



    //running w/ encoders
    public void runWithEncoders (int lb, int lf, int rb, int rf, float power) {
        //stop and reset encoders
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set target position
        frontRight.setTargetPosition(lb);
        backRight.setTargetPosition(lf);
        frontLeft.setTargetPosition(rb);
        backLeft.setTargetPosition(rf);

        //run to position
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //move
        while (frontRight.isBusy() && backRight.isBusy() &&
                frontLeft.isBusy() && backLeft.isBusy())
        {
            frontRight.setPower(power);
            backRight.setPower(power);
            frontLeft.setPower(power);
            backLeft.setPower(power);
        }

        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
    }

    public void STOP (int time) {

        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);

        sleep(time);
    }

    public void SLEEP (int time) {sleep(time);}

    public void moveWOEncoder (float lf, float lb, float rf, float rb, int time) {

        frontRight.setPower(lf);
        backRight.setPower(lb);
        frontLeft.setPower(rf);
        backLeft.setPower(rb);

        sleep(time);
    }

    public void imu () {



    }


    }