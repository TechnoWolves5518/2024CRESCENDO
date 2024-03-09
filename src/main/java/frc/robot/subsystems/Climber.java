// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  static CANSparkMax rightClimber;
  static CANSparkMax leftClimber;
  public Climber() {
    rightClimber = new CANSparkMax(Constants.RIGHT_CLIMBER_ID, MotorType.kBrushless);
    leftClimber = new CANSparkMax(Constants.LEFT_CLIMBER_ID, MotorType.kBrushless);
  }

  public void climb(double speed){
    rightClimber.set(speed);
    leftClimber.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
