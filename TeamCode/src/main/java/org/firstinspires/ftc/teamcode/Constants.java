package org.firstinspires.ftc.teamcode;

public final class Constants {

    // Drive Motors Config
    public static final String LEFT_BACK_DRIVE = "left_back_drive";  // Config name for left back drive motor
    public static final String RIGHT_BACK_DRIVE = "right_back_drive"; // Config name for right back drive motor

    // Arm Motors Config
    public static final String ARM_EXTENSION_MOTOR = "arm_extension"; // Config name for arm extension motor
    public static final String ARM_ROTATION_MOTOR = "arm_rotation"; // Config name for arm rotation motor

    // Intake Servo Config
    public static final String INTAKE_SERVO = "intake"; // Config name for intake servo


    
    // General Constants
    public static final double TELEOP_LOOP_DELAY = 0.1; // Delay between loops in seconds
    public static final int TELEMETRY_UPDATE_INTERVAL = 100; // Milliseconds between telemetry updates
    
    // Drive System Constants
    public static final double DRIVE_MAX_SPEED = 1.0; // Maximum drive motor power
    public static final double DRIVE_MIN_SPEED = 0.2; // Minimum drive motor power

    //Arm Subsystem Constants
    public static final int ARM_POSITION_INIT = 300;
    public static final int ARM_POSITION_INTAKE = 450;
    public static final int ARM_POSITION_WALL_GRAB = 1100;
    public static final int ARM_POSITION_WALL_UNHOOK = 1700;
    public static final int ARM_POSITION_HOVER_HIGH = 2600;
    public static final int ARM_POSITION_CLIP_HIGH = 2100;
    public static final int ARM_POSITION_LOW_BASKET = 2500;

    // Arm Extension Constants
    public static final double ARM_EXTEND_POWER = 0.8;  // Power for extending the arm
    public static final double ARM_RETRACT_POWER = -0.8; // Power for retracting the arm
    public static final int ARM_MAX_POSITION = 2000;    // Maximum encoder count for fully extended arm
    public static final int ARM_MIN_POSITION = 0;       // Minimum encoder count for fully retracted arm
    public static final int ARM_MOTOR_RUNTIME_LIMIT = 2000; // Runtime limit for arm motor (milliseconds)

    // Arm Rotation Constants
    public static final double ROTATION_GEAR_RATIO = 5.0; // e.g., rotationGearRatio
    public static final int ROTATION_TICKS_PER_REVOLUTION = 1120; // e.g., rotationTicksPerRevolution
    public static final double ROTATION_MIN_ANGLE = 0.0; // e.g., rotationMinAngle
    public static final double ROTATION_MAX_ANGLE = 180.0; // e.g., rotationMaxAngle
    public static final double ARM_ROTATION_POWER = 0.5; // Default rotation motor power

    // Intake Subsystem Constants
    public static final double INTAKE_IN_POWER = 1.0;  // Power for intake to pull objects in
    public static final double INTAKE_OUT_POWER = -1.0; // Power for intake to push objects out
    public static final double INTAKE_OFF_POWER = 0.0;  // Power to stop intake

    // PID Controller Constants (if used)
    // public static final double PID_P = 0.0; // Proportional gain
    // public static final double PID_I = 0.0; // Integral gain
    // public static final double PID_D = 0.0; // Derivative gain
}
