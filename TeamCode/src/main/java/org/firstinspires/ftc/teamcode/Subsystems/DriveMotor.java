package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class DriveMotor {

    private final DcMotor motor;
    private long lastTime;
    private int lastPosition;

    public DriveMotor(HardwareMap hardwareMap, String motorName) {
        motor = hardwareMap.get(DcMotor.class, motorName);
        runConfig();
        lastTime = System.nanoTime();
        lastPosition = motor.getCurrentPosition();
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        motor.setDirection(direction);
    }

    public void setPower(double power) {
        motor.setPower(power);
    }

    public double getPower() {
        return motor.getPower();
    }

    public double getRPM() {
        // Get the current motor position and time
        int currentPosition = motor.getCurrentPosition();
        long currentTime = System.nanoTime();

        // Calculate the time elapsed in seconds
        double timeElapsed = (currentTime - lastTime) / 1e9; // Convert to seconds

        // Calculate the ticks per second (double)
        double ticksPerSecond = (currentPosition - lastPosition) / timeElapsed;

        // Calculate the RPM (double)
        double rpm = (ticksPerSecond * 60) / Constants.DriveTrainConstants.ticksPerRevolution;

        lastTime = currentTime;
        lastPosition = currentPosition;

        return rpm;
    }

    public void runConfig() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
