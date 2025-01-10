package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants; // Import Constants

public class DriveSubsystem extends SubsystemBase {

    // Declare drive motors
    private DcMotor leftBackDrive;
    private DcMotor rightBackDrive;

    // Declare telemetry
    private Telemetry telemetry;

    // Constructor
    public DriveSubsystem(HardwareMap hardwareMap) {
        // Initialize the hardware variables using constants for motor names
        leftBackDrive = hardwareMap.get(DcMotor.class, Constants.LEFT_BACK_DRIVE);  // Using constant for motor name
        rightBackDrive = hardwareMap.get(DcMotor.class, Constants.RIGHT_BACK_DRIVE); // Using constant for motor name

        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motor directions (based on original code)
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set zero power behavior to brake for better control
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void periodic() {
        // This method will be called once per scheduler run
        telemetry.addData("Left Back Power", leftBackDrive.getPower());
        telemetry.addData("Right Back Power", rightBackDrive.getPower());
        telemetry.addData("Left Back Position", leftBackDrive.getCurrentPosition());
        telemetry.addData("Right Back Position", rightBackDrive.getCurrentPosition());
        telemetry.update(); // Update telemetry data
    }

    // TeleOp method
    // This method is called in the teleop loop in the OpMode
    // It takes the gamepad input and drives the robot
    // The mode parameter is used to switch between tank and arcade drive
    //DRIVE Split Arcade

    public void drive(double drive, double turn) {
        double leftBackPower = Range.clip(drive + turn, -1.0, 1.0) ;
        double rightBackPower = Range.clip(drive - turn, -1.0, 1.0) ;

        // Set power to motors
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);

        // Add telemetry for drive calculations
        telemetry.addData("Left Back Calculated Power", leftBackPower);
        telemetry.addData("Right Back Calculated Power", rightBackPower);
        telemetry.update();
    }

    public void stop() {
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Add telemetry for stop method
        telemetry.addData("Motors", "Stopped");
        telemetry.update();
    }
}
