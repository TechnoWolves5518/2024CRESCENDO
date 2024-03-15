// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.AutoDriveBase;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class AutoReverse extends Command {
  private final SwerveSubsystem swerve;
  private double timer;
  private boolean stop_check;
  /** Creates a new AutoFoward. */
  public AutoReverse(SwerveSubsystem swerve) {
    this.swerve = swerve;
    addRequirements(swerve);

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
    swerve.drive(new Translation2d(-3, 0), 0, true);
    timer++;
    if (timer == 100){
      stop_check = true;

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerve.drive(new Translation2d(0, 0), 0, true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop_check;
  }
}
