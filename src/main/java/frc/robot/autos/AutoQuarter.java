// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import javax.xml.namespace.QName;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.in;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoQuarter extends Command {
  IntakeSubsystem intake;
  private int timer;
  private boolean stop_check;
  /** Creates a new AutoQuarter. */
  public AutoQuarter(IntakeSubsystem intake) {
    this.intake = intake;
    addRequirements(intake);
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
    intake.scoop(-Constants.INTAKE_SPEED);
    timer ++;
    if (timer == 13){
      stop_check = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.scoop(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop_check;
  }
}
