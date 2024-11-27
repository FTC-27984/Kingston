package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class Extension {

    Servo extension;

    public Extension(HardwareMap hardwareMap) {
        extension = hardwareMap.get(Servo.class, Constants.ExtensionConstants.extension);
    }

    public void setAngle(double angle) {
        extension.setPosition(angle);
    }
    
    public double getAngle() {
        return extension.getPosition();
    }

}
