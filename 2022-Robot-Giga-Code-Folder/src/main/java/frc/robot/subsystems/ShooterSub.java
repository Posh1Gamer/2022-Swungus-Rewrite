// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSub extends SubsystemBase {
  WPI_TalonFX shooterMainWheelLeft = new WPI_TalonFX(Constants.SHOOTER_MAIN_WHEEL_LEFT_ID);
  /** Creates a new ExampleSubsystem. */
  public ShooterSub() {
    shooterMainWheelLeft.configFactoryDefault();
    //shooterMainWheelLeft.configClosedloopRamp(.25);
    shooterMainWheelLeft.configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);
    shooterMainWheelLeft.configVelocityMeasurementWindow(1);
    shooterMainWheelLeft.config_kF(0, Constants.SHOOTER_MAIN_WHEEL_KF);
    shooterMainWheelLeft.config_kP(0, Constants.SHOOTER_MAIN_WHEEL_KP);
    shooterMainWheelLeft.config_kD(0, 0);
    shooterMainWheelLeft.config_kI(0, 0);
  }
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new RobotContainer.rc_idleshooter());
}

  public void idle() {
    shooterMainWheelLeft.set(ControlMode.Velocity, Constants.SHOOTER_IDLE_VELOCITY);
  }

  public void stop() {
    shooterMainWheelLeft.set(TalonFXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
  SmartDashboard.putNumber("ShooterVelocity", shooterMainWheelLeft.getSelectedSensorVelocity());
  // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
