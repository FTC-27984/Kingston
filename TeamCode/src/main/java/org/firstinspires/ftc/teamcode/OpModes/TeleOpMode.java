package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
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

@TeleOp(name = "pooptele", group = "TeleOp")
public class TeleOpMode extends CommandOpMode {

    @Override
    public void initialize() {
        // Gamepad is Logitech Gamepad F310 Controller
        GamepadEx m_driverOp = new GamepadEx(gamepad1);
        GamepadEx m_operatorOp = new GamepadEx(gamepad2);

        DriveSubsystem m_driveSubsystem = new DriveSubsystem(hardwareMap);
        IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem(hardwareMap);
        ArmSubsystem m_armSubsystem = new ArmSubsystem(hardwareMap);

        //init commands
        Drivetrain m_driveCommand = new Drivetrain(m_driveSubsystem, m_driverOp::getLeftY, m_driverOp::getLeftX);
        IntakeIn m_intakeIn = new IntakeIn(m_intakeSubsystem);
        IntakeOut m_intakeOut = new IntakeOut(m_intakeSubsystem);
        ExtendArm m_extendArm = new ExtendArm(m_armSubsystem);
        RetractArm m_retractArm = new RetractArm(m_armSubsystem);
        RotateArm m_rotateArm = new RotateArm(m_armSubsystem);

        // Button Y on gamepad2 is used to intake
        Button intakeInButton = new GamepadButton(m_operatorOp, GamepadKeys.Button.Y);
        intakeInButton.whenPressed(m_intakeIn);

        // Button B on gamepad2 is used to outtake
        Button intakeOutButton = new GamepadButton(m_operatorOp, GamepadKeys.Button.B);
        intakeOutButton.whenPressed(m_intakeOut);

        // Button X on gamepad2 is used to extend the arm
        Button extendArmButton = new GamepadButton(m_operatorOp, GamepadKeys.Button.X);
        extendArmButton.whenPressed(m_extendArm);

        // Button A on gamepad2 is used to retract the arm
        Button retractArmButton = new GamepadButton(m_operatorOp, GamepadKeys.Button.A);
        retractArmButton.whenPressed(m_retractArm);

        // Dpad up on gamepad2 is used to rotate the arm up
        Button rotateArmUpButton = new GamepadButton(m_operatorOp, GamepadKeys.Button.DPAD_UP);
        rotateArmUpButton.whenPressed(m_rotateArm);

        // Dpad down on gamepad2 is used to rotate the arm down
        Button rotateArmDownButton = new GamepadButton(m_operatorOp, GamepadKeys.Button.DPAD_DOWN);
        rotateArmDownButton.whenPressed(m_rotateArm);

        // Joystick left on gamepad2 is used to drive the robot
        register(m_driveSubsystem);
        m_driveSubsystem.setDefaultCommand(m_driveCommand);

    }

   // Run the opmode and commands
    @Override
    public void run() {
        while (opModeIsActive()) {
            schedule();
        }
    }
}
