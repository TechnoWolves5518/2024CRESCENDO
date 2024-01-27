// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Intake extends SubsystemBase {
  
  /** Creates a new Intake. */
  private CANSparkMax leadMotor;
  private int runSpeed;

  // Constructor for the Intake Class.
  // Parameters:
  //  int deviceIDLeadMotor --> CAN bus address for the first motor.
  //  int speed  --> Speed of the lead motor during intake.  During discharge the speed will be -speed.
  public Intake(int deviceIDLeadMotor, int speed) {
    this.leadMotor = new CANSparkMax(deviceIDLeadMotor, MotorType.kBrushless);
    this.runSpeed = speed;
  }
  
  public void enableIntake() {
    leadMotor.set(runSpeed);
  }
  
  public void enableEject() {
    leadMotor.set(-runSpeed);
  }

  public void off() {
    leadMotor.stopMotor();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
