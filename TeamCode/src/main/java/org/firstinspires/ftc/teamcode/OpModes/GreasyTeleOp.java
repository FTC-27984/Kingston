package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Wrist;

@TeleOp(name = "greasy", group = "TeleOp")
public class GreasyTeleOp extends LinearOpMode {
    DriveTrain s_DriveTrain;
    Wrist s_Wrist;
    Intake s_Intake;


    @Override
    public void runOpMode() {
        telemetry.update();

        s_DriveTrain = new DriveTrain(hardwareMap);
        s_Wrist = new Wrist(hardwareMap);
        s_Intake = new Intake(hardwareMap);


        waitForStart();

        while (opModeIsActive()) {

            s_DriveTrain.drive(
                    -gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x
            );

            if (gamepad1.options) {
                s_DriveTrain.resetYaw();
            }

            if (gamepad1.right_trigger > 0) {
                s_Intake.setIntakePower(1);
                s_Wrist.setAngle(Constants.WristConstants.intakeAngle);
            } else if (gamepad1.left_trigger > 0) {
                s_Intake.setIntakePower(-1);
                s_Wrist.setAngle(Constants.WristConstants.intakeAngle);
            } else {
                s_Intake.setIntakePower(0);
            }

            if (gamepad1.right_bumper){
                s_Wrist.setAngle(Constants.WristConstants.barAngle);
                //Will eventually say shoot out horizontal slides :p
            } else if (gamepad1.left_bumper){
                s_Wrist.setAngle(Constants.WristConstants.barAngle);
                //Will pull in horizontal slides
            }

            s_DriveTrain.periodic(telemetry);
            telemetry.update();
        }
    }
}
