package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;

public class ExtendArm extends CommandBase {
    private final ArmSubsystem armSubsystem;

    public ExtendArm(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void execute() {
        armSubsystem.moveExtension(1, 2500 ); // target position is set to low basket height?? change later pls

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
