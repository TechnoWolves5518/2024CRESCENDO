// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  static CANSparkMax rightClimber;
  static CANSparkMax leftClimber;
  public Climber() {
    rightClimber = new CANSparkMax(14, MotorType.kBrushless);
    leftClimber = new CANSparkMax(13, MotorType.kBrushless);
  }

  public static void climb(double speed){
    rightClimber.set(speed);
    leftClimber.follow(rightClimber);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
