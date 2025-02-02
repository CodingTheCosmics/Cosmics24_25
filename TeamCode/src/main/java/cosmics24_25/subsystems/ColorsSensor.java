package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

@Disabled

public class ColorsSensor {

    public ColorSensor colors;

    public boolean blue;
    public boolean red;
    public boolean yellow;



    OpMode opMode;




    //color sensor definitions and connections to driver hub
    public ColorsSensor(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        colors = hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, "colors");
    }

    public void whatColor () {
         if (colors.blue() > colors.red()) {
             blue = true;
         }

         else if (colors.red() > colors.blue()) {
             red = true;
         }

         else {
            yellow = true;
         }
    }






    public void colorSensorTelemetry()
    {
        opMode.telemetry.addData("blue!", blue = true);
        opMode.telemetry.addData("red!", red = true);
        opMode.telemetry.addData("yellow!", yellow = true);
        opMode.telemetry.addData("light?", ((OpticalDistanceSensor) colors).getLightDetected());
    }


}
