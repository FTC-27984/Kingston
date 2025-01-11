package org.firstinspires.ftc.teamcode; // Ensure this matches your directory structure

import com.qualcomm.robotcore.eventloop.opmode.Autonomous; // Required for Autonomous annotation
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // Required for LinearOpMode

@Autonomous(name = "Dummy Auto", group = "Test")
public class DummyAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Display status on the Driver Station
        telemetry.addData("Status", "Ready to Start");
        telemetry.update();

        // Wait for the game to start (driver presses INIT and then START)
        waitForStart();

        if (opModeIsActive()) {
            // Display "Running" status
            telemetry.addData("Status", "Running");
            telemetry.update();

            // Sleep for 2 seconds as a placeholder action
            sleep(2000);

            // Display "Done" status
            telemetry.addData("Status", "Done");
            telemetry.update();
        }
    }
}
