// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake_subsystem;

public class AutoIntake extends Command {
  Intake_subsystem m_intake;
  private double timer;
  private boolean stop_check;
  /** Creates a new AutoIntake. */
  public AutoIntake(Intake_subsystem m_intake) {
    this.m_intake = m_intake;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = 0;
    stop_check = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.scoop(Constants.INTAKE_SPEED);
    if (timer == 75){
      stop_check = true;
    }
    if (stop_check == true){
      m_intake.scoop(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.scoop(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop_check;
  }
}
