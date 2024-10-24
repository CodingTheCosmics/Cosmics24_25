package cosmics24_25.subsystems;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Disabled

public class distanceSensor {

    public DistanceSensor howFar;

    OpMode opMode;


    //arm definitions and connections to driver hub
    public distanceSensor(HardwareMap hardwareMap, OpMode opMode) {

        this.opMode = opMode;

        howFar = hardwareMap.get(DistanceSensor.class, "howFar");
    }


    public void howFarTelemetry()
    {
        opMode.telemetry.addData("how far?", howFar.getDistance(DistanceUnit.CM));
    }


}
