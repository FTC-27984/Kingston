package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;

@TeleOp(name = "greasy", group = "teamCode")
public class GreasyTeleOp extends LinearOpMode {
    DriveTrain s_DriveTrain;

    @Override
    public void runOpMode() {
        telemetry.update();

        s_DriveTrain = new DriveTrain(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            s_DriveTrain.drive(-gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    true);

            if (gamepad1.options) {
                s_DriveTrain.resetYaw();
            }

            telemetry.update();
        }
    }
}
