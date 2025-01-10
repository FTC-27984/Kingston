package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;

public class RetractArm extends CommandBase {
    private final ArmSubsystem armSubsystem;

    public RetractArm(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        armSubsystem.moveExtension(-1, 300); // target position is set to initial position?? change later pls
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.stopExtension();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
