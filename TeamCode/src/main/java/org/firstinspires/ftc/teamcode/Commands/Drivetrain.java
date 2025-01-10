package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class Drivetrain extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;

    public Drivetrain(DriveSubsystem driveSubsystem, DoubleSupplier leftY, DoubleSupplier leftX) {
        this.driveSubsystem = driveSubsystem;
        this.m_forward = leftY;
        this.m_rotation = leftX;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(0.5, 0.5, 0); //change yaw value later pls
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
