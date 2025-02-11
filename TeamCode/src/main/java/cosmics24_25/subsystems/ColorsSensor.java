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



    //different colors
    public boolean seesYellow() {
        return colors.green() > colors.red() && colors.green() > 100;
    }

    public boolean seesBlue () {
        return colors.blue() > colors.green() && colors.blue() > colors.red();
    }

    public boolean seesRed () {
        return colors.red() > colors.green() && colors.red() > colors.blue();
    }


    //telemetry
    public void whatColor () {
         if (colors.blue() > colors.green() && colors.blue() > colors.red()) {
             blue = true;
             opMode.telemetry.addLine("blue!");
         }

         if (colors.red() > colors.green() && colors.red() > colors.blue()) {
             red = true;
             opMode.telemetry.addLine("red!");
         }

         if (colors.green() > colors.red() && colors.green() > 100){
            yellow = true;
             opMode.telemetry.addLine("yellow!");
         }
    }



    public void colorsTelemetry () {
        opMode.telemetry.addData("green value", colors.green());
        opMode.telemetry.addData("red value", colors.red());
        opMode.telemetry.addData("blue value", colors.blue());
    }




}
