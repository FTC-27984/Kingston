package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants; // Import Constants

public class IntakeSubsystem extends SubsystemBase {
    // Intake servo
    private final CRServo intake;

    // Telemetry object
    private Telemetry telemetry;
    public IntakeSubsystem(HardwareMap hardwareMap) {
        // Initialize intake servo
        intake = hardwareMap.get(CRServo.class, Constants.INTAKE_SERVO); // Using constant for servo name

        // Set intake direction (based on original code)
        intake.setDirection(CRServo.Direction.FORWARD);

        // Log that the intake subsystem is initialized
        telemetry.addData("Intake Subsystem", "Initialized");
        telemetry.update();
    }

    /**
     * Periodic method called every scheduler cycle
     */
    @Override
    public void periodic() {
        // Add telemetry data for intake power
        telemetry.addData("Intake Power", intake.getPower());
        telemetry.update();
    }

    /**
     * Set intake to pull pixels in
     */
    public void intakeIn() {
        intake.setPower(Constants.INTAKE_IN_POWER); // Using constant for power
        telemetry.addData("Intake Status", "Pulling In");
        telemetry.addData("Power", Constants.INTAKE_IN_POWER);
        telemetry.update();
    }

    /**
     * Set intake to push pixels out
     */
    public void intakeOut() {
        intake.setPower(Constants.INTAKE_OUT_POWER); // Using constant for power
        telemetry.addData("Intake Status", "Pushing Out");
        telemetry.addData("Power", Constants.INTAKE_OUT_POWER);
        telemetry.update();
    }

    /**
     * Stop the intake
     */
    public void intakeStop() {
        intake.setPower(Constants.INTAKE_OFF_POWER); // Using constant for power
        telemetry.addData("Intake Status", "Stopped");
        telemetry.addData("Power", Constants.INTAKE_OFF_POWER);
        telemetry.update();
    }

    /**
     * Get current intake power
     * @return Current power setting of the intake
     */
    public double getCurrentPower() {
        return intake.getPower();
    }
}
