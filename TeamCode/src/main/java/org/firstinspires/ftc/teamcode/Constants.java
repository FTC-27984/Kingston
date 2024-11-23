package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Constants {

    public static final class DriveTrainConstants {

        public static final String frontLeftMotor = "frontLeft0";
        public static final String frontRightMotor = "frontRight1";
        public static final String backLeftMotor = "backLeft2";
        public static final String backRightMotor = "backRight3";

        public static final double strafingBalancer = 1.1;

        public static final double ticksPerRevolution = 537.6; //Ticks per revolution on the NeveRest Orbital 20 Gearmotor
    }

    public static final class WristConstants {
        public static final String leftWrist2 = "leftWrist2";
        public static final String rightWrist3 = "rightWrist3";
        public static final double intakeAngle = 0.10;
        public static final double transferAngle = 0;
        public static final double barAngle = 0.50;
        public static final Servo.Direction wristInvertL = Servo.Direction.FORWARD;
        public static final Servo.Direction wristInvertR = Servo.Direction.REVERSE;
    }

    public static final class IntakeConstants {

        public static final String leftIntake0 = "leftIntake0";
        public static final String rightIntake1 = "rightIntake1";
    }

}
