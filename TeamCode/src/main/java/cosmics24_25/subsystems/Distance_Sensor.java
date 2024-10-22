package cosmics24_25.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class Distance_Sensor {

    public DistanceSensor howFar;

    OpMode opMode;


    //arm definitions and connections to driver hub
    public Distance_Sensor(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        howFar = (DistanceSensor) hardwareMap.opticalDistanceSensor.get("howFar");
    }


    public void howFarTelemetry()
    {
        opMode.telemetry.addData("how far?", howFar.getDistance(DistanceUnit.CM));
    }


}
