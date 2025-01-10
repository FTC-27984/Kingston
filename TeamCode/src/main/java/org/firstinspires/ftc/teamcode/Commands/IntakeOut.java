package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeOut extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeOut(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.intakeOut();
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.intakeStop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
