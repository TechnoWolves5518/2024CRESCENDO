// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.CommandGroups;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.AutoQuarter;
import frc.robot.autos.AutoDriveBase.AutoAltFoward;
import frc.robot.autos.AutoDriveBase.AutoReverse;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTwoShot extends SequentialCommandGroup {
  IntakeSubsystem m_intake;
  Shooter m_shot;
  SwerveSubsystem m_swerve;
  /** Creates a new AutoTwoShoot. */
  public AutoTwoShot(IntakeSubsystem m_intake, Shooter m_shot, SwerveSubsystem m_swerve) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SpeakerShot(m_intake, m_shot),
      new FowardIntake(m_swerve, m_intake),
      new AutoQuarter(m_intake),
      new AutoReverse(m_swerve),
      new SpeakerShot(m_intake, m_shot),
      new AutoAltFoward(m_swerve)
    );
  } 
}
