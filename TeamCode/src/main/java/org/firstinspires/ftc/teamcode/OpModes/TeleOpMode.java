package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Drivetrain;
import org.firstinspires.ftc.teamcode.Commands.IntakeIn;
import org.firstinspires.ftc.teamcode.Commands.IntakeOut;
import org.firstinspires.ftc.teamcode.Commands.ExtendArm;
import org.firstinspires.ftc.teamcode.Commands.RetractArm;
import org.firstinspires.ftc.teamcode.Commands.RotateArm;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

@TeleOp(name = "TeleOpMode", group = "TeleOp")
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initialize subsystems
        DriveSubsystem m_driveSubsystem = new DriveSubsystem(hardwareMap);
        IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem(hardwareMap);
        ArmSubsystem m_armSubsystem = new ArmSubsystem(hardwareMap);

        // Initialize commands
        Drivetrain m_driveCommand = new Drivetrain(m_driveSubsystem,
                () -> -gamepad1.left_stick_y,
                () -> gamepad1.left_stick_x);
        IntakeIn m_intakeIn = new IntakeIn(m_intakeSubsystem);
        IntakeOut m_intakeOut = new IntakeOut(m_intakeSubsystem);
        ExtendArm m_extendArm = new ExtendArm(m_armSubsystem);
        RetractArm m_retractArm = new RetractArm(m_armSubsystem);
        RotateArm m_rotateArmUp = new RotateArm(m_armSubsystem);
        RotateArm m_rotateArmDown = new RotateArm(m_armSubsystem);

        // Wait for the game to start
        waitForStart();

        while (opModeIsActive()) {
            // Execute drive command
            m_driveCommand.execute();

            // Button Y on gamepad2 is used to intake
            if (gamepad2.y) {
                m_intakeIn.execute();
            } else if (gamepad2.b) {
                m_intakeOut.execute();
            } else {
                m_intakeSubsystem.intakeStop(); // Ensuring intake stops when neither is pressed
            }

            // Extend/Retract arm
            if (gamepad2.x) {
                m_extendArm.execute();
            } else if (gamepad2.a) {
                m_retractArm.execute();
            } else {
                m_armSubsystem.stopExtension(); // Stop arm when no button is pressed
            }

            // Rotate arm
            if (gamepad2.dpad_up) {
                m_rotateArmUp.execute();
            } else if (gamepad2.dpad_down) {
                m_rotateArmDown.execute();
            } else {
                m_armSubsystem.stopRotation(); // Stop rotation when no D-Pad button is pressed
            }

            // Telemetry (optional for debugging)
            telemetry.addData("Drive Command", "Running");
            telemetry.update();
        }
    }
}