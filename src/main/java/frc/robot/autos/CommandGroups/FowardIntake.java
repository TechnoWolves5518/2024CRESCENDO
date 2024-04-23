// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.CommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.autos.AutoIn;
import frc.robot.autos.AutoDriveBase.AutoFoward;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FowardIntake extends ParallelDeadlineGroup {
  /** Creates a new FowardIntake. */
  public FowardIntake(SwerveSubsystem swerve, IntakeSubsystem intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    super(new AutoFoward(swerve));
    addCommands(new AutoIn(intake));
  }
}
