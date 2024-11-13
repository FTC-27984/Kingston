package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants.DriveTrainConstants;

public class DriveTrain {

    private DcMotor frontLeft0;
    private DcMotor frontRight1;
    private DcMotor backLeft2;
    private DcMotor backRight3;

    private BNO055IMU imu;

    private Orientation angles;
    public DriveTrain(HardwareMap hardwareMap) {
        frontLeft0 = hardwareMap.get(DcMotor.class, DriveTrainConstants.frontLeftMotor);
        frontRight1 = hardwareMap.get(DcMotor.class, DriveTrainConstants.frontRightMotor);
        backLeft2 = hardwareMap.get(DcMotor.class, DriveTrainConstants.backLeftMotor);
        backRight3 = hardwareMap.get(DcMotor.class, DriveTrainConstants.backRightMotor);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
    }


    public double getHeading() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        return angles.firstAngle;  // Returns heading in radians
    }
}
