// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  static CANSparkMax rightShooter;
  static CANSparkMax leftShooter;
  public Shooter() {
    rightShooter = new CANSparkMax(Constants.RIGHT_SHOOTER_ID, MotorType.kBrushless);
    leftShooter = new CANSparkMax(Constants.LEFT_SHOOTER_ID, MotorType.kBrushless);
  }

  public void shot(double speed){
    rightShooter.set(-speed);
    leftShooter.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
