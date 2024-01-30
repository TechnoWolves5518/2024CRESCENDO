// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsytems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooter extends SubsystemBase {
  /** Creates a new shooter. */
  public static CANSparkMax oneShooter = new CANSparkMax(23, MotorType.kBrushless);
  public static CANSparkMax twoShooter = new CANSparkMax(24, MotorType.kBrushless);

  public static void rot(double speed){
    oneShooter.set(speed);
    twoShooter.follow(oneShooter);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}