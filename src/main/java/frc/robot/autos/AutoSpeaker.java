// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class AutoSpeaker extends Command {
  Shooter m_shot;
  private double timer;
  private boolean stop_check;
  /** Creates a new AutoSpeaker. */
  public AutoSpeaker(Shooter m_shot) {
    this.m_shot = m_shot;
    addRequirements(m_shot);
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
    m_shot.shot(Constants.SHOOT_SPEED);
    timer++;
    if (timer == 150){
      stop_check = true;
    }
    if (stop_check == true) {
      m_shot.shot(0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shot.shot(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop_check;
  }
}
