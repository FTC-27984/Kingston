package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import com.arcrobotics.ftclib.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

    // Arm Rotation Fields
    private final DcMotor rotationMotor;
    // Arm Extension Fields
    private final DcMotor extensionMotor;
    private long extensionMotorStartTime;

    public ArmSubsystem(HardwareMap hardwareMap) {
        // Initialize rotation motor
        rotationMotor = hardwareMap.get(DcMotor.class, "rotationMotor");
        rotationMotor.setDirection(DcMotor.Direction.FORWARD);
        rotationMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Initialize extension motor
        extensionMotor = hardwareMap.get(DcMotor.class, Constants.ARM_EXTENSION_MOTOR);  // Using constant for motor name
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extensionMotor.setDirection(DcMotor.Direction.FORWARD);
        extensionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extensionMotorStartTime = 0;
    }

    // Arm Rotation Methods
    public void setTargetAngle(double targetAngle) {
        targetAngle = Math.max(Constants.ROTATION_MIN_ANGLE, Math.min(Constants.ROTATION_MAX_ANGLE, targetAngle));
        int targetPosition = (int) ((targetAngle / 360.0) * Constants.ROTATION_TICKS_PER_REVOLUTION * Constants.ROTATION_GEAR_RATIO);
        rotationMotor.setTargetPosition(targetPosition);
        rotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotationMotor.setPower(0.5); // Adjust power as necessary
    }

    public double getCurrentAngle() {
        int currentTicks = rotationMotor.getCurrentPosition();
        return (currentTicks / (double) Constants.ROTATION_TICKS_PER_REVOLUTION) * 360.0 / Constants.ROTATION_GEAR_RATIO;
    }

    public void enforceRotationLimits() {
        double currentAngle = getCurrentAngle();
        if (currentAngle <= Constants.ROTATION_MIN_ANGLE && rotationMotor.getPower() < 0) {
            rotationMotor.setPower(0);
        } else if (currentAngle >= Constants.ROTATION_MAX_ANGLE && rotationMotor.getPower() > 0) {
            rotationMotor.setPower(0);
        }
    }

    public void stopRotation() {
        rotationMotor.setPower(0);
    }

    public void manualRotationControl(double power) {
        enforceRotationLimits();
        rotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotationMotor.setPower(power);
    }

    public void holdRotationPosition() {
        rotationMotor.setTargetPosition(rotationMotor.getCurrentPosition());
        rotationMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotationMotor.setPower(0.2);
    }

    // Arm Extension Methods
    public void moveExtension(double power, int targetPosition) {
        if (power > 0 && extensionMotor.getCurrentPosition() < targetPosition ||
                power < 0 && extensionMotor.getCurrentPosition() > targetPosition) {
            extensionMotor.setPower(power);
            if (extensionMotorStartTime == 0) extensionMotorStartTime = System.currentTimeMillis();
        } else {
            extensionMotor.setPower(0);
            extensionMotorStartTime = 0;
        }
    }

    public void stopExtension() {
        extensionMotor.setPower(0);
        extensionMotorStartTime = 0;
    }

    public boolean isExtensionOverLimit() {
        return extensionMotorStartTime != 0 && (System.currentTimeMillis() - extensionMotorStartTime > Constants.ARM_MOTOR_RUNTIME_LIMIT);  // Using constant for runtime limit
    }

    public void resetExtension() {
        extensionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void periodic(Telemetry telemetry) {
        // This method will be called once per scheduler run
        telemetry.addData("Rotation Power", rotationMotor.getPower());
        telemetry.addData("Rotation Position", rotationMotor.getCurrentPosition());
        telemetry.addData("Rotation Angle", getCurrentAngle());
        telemetry.addData("Extension Power", extensionMotor.getPower());
        telemetry.addData("Extension Position", extensionMotor.getCurrentPosition());
    }
}