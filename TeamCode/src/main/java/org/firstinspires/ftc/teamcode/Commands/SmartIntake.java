package org.firstinspires.ftc.teamcode.Commands;


import org.firstinspires.ftc.teamcode.Subsystems.Extension;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Wrist;

public class SmartIntake{

    Intake s_Intake;
    Wrist s_Wrist;
    Extension s_Extension;

    public SmartIntake(Intake s_Intake, Wrist s_Wrist, Extension s_Extesion) {
        this.s_Intake = s_Intake;
        this.s_Wrist = s_Wrist;
        this.s_Extension = s_Extesion;
    }

}
