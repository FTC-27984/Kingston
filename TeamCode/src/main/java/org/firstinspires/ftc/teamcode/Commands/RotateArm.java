package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;

public class RotateArm extends CommandBase {
    private final ArmSubsystem armSubsystem;

    public RotateArm(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        armSubsystem.manualRotationControl(1); // change later pls
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.stopRotation();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
