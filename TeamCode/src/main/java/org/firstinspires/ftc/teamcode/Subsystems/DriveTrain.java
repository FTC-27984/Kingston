package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Constants.DriveTrainConstants;

public class DriveTrain {

    private DriveMotor frontLeft0;
    private DriveMotor frontRight1;
    private DriveMotor backLeft2;
    private DriveMotor backRight3;

    private BNO055IMU imu;

    private Orientation angles;
    private double yawOffset = 0;

    public DriveTrain(HardwareMap hardwareMap) {
        frontLeft0 = new DriveMotor(hardwareMap, DriveTrainConstants.frontLeftMotor);
        frontRight1 = new DriveMotor(hardwareMap, DriveTrainConstants.frontRightMotor);
        backLeft2 = new DriveMotor(hardwareMap, DriveTrainConstants.backLeftMotor);
        backRight3 = new DriveMotor(hardwareMap, DriveTrainConstants.backRightMotor);

        frontLeft0.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight1.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft2.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight3.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
    }

    public void drive(double ySpeed, double xSpeed, double rotation, boolean fieldOriented) {

        double rotY = ySpeed;
        double rotX = xSpeed * DriveTrainConstants.strafingBalancer;

        if (fieldOriented) {
            double botHeading = getHeading();

            // Rotate the movement direction counter to the bot's rotation
            rotX = xSpeed * Math.cos(-botHeading) - ySpeed * Math.sin(-botHeading);
            rotY = xSpeed * Math.sin(-botHeading) + ySpeed * Math.cos(-botHeading);
        }

        double denominator = Math.max(Math.abs(ySpeed) + Math.abs(xSpeed) + Math.abs(rotation), 1);
        double frontLeftPower = (rotY + rotX + rotation) / denominator;
        double backLeftPower = (rotY - rotX + rotation) / denominator;
        double frontRightPower = (rotY - rotX - rotation) / denominator;
        double backRightPower = (rotY + rotX - rotation) / denominator;

        frontLeft0.setPower(frontLeftPower);
        frontRight1.setPower(frontRightPower);
        backLeft2.setPower(backLeftPower);
        backRight3.setPower(backRightPower);
    }

    /**
     * Raw heading of the robot before yaw offset is applied
     *
     * @return heading of the robot
     */
    public double getRawHeading() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }

    /**
     * Updates the yaw offset to the current heading
     */
    public void resetYaw() {
        yawOffset = getRawHeading();
    }

    /**
     * Returns the updated yaw after the yaw offset is applied
     *
     * @return adjusted heading of the robot
     */
    public double getHeading() {
        double heading = getRawHeading() - yawOffset;

        if (heading > 180) heading -= 360;
        if (heading < -180) heading += 360;

        return heading;
    }

    public void periodic(Telemetry telemetry) {
        telemetry.addLine("Drive train");
        telemetry.addData("Heading: ", getHeading());

        telemetry.addData("Front Left Power: ", frontLeft0.getPower());
        telemetry.addData("Front Left RPM: ", frontLeft0.getRPM());

        telemetry.addData("Front Right Power: ", frontRight1.getPower());
        telemetry.addData("Front Right RPM: ", frontRight1.getRPM());

        telemetry.addData("Back Left Power: ", backLeft2.getPower());
        telemetry.addData("Back Left RPM: ", backLeft2.getRPM());

        telemetry.addData("Back Right Power: ", backRight3.getPower());
        telemetry.addData("Back Right RPM: ", backRight3.getRPM());
    }
}
