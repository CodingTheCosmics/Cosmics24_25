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

    public void colorLight () {

    }


    public void whatColor () {
         if (colors.blue() > colors.green() && colors.green() > colors.red()) {
             blue = true;
             opMode.telemetry.addLine("blue!");
         }

         if (colors.red() > colors.green() && colors.green() > colors.blue()) {
             red = true;
             opMode.telemetry.addLine("red!");
         }

         if (colors.green() > colors.blue() && colors.red() > colors.blue()){
            yellow = true;
             opMode.telemetry.addLine("yellow!");
         }
    }








}
