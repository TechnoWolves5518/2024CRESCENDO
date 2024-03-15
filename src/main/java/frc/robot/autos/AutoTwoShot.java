// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.AutoDriveBase.AutoFoward;
import frc.robot.autos.AutoDriveBase.AutoReverse;
import frc.robot.subsystems.Intake_subsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTwoShot extends SequentialCommandGroup {
  Intake_subsystem m_intake;
  Shooter m_shot;
  SwerveSubsystem m_swerve;
  /** Creates a new AutoTwoShoot. */
  public AutoTwoShot(Intake_subsystem m_intake, Shooter m_shot, SwerveSubsystem m_swerve) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SpeakerShot(m_intake, m_shot),
      new AutoFoward(m_swerve),
      new AutoIntake(m_intake),
      new AutoReverse(m_swerve),
      new SpeakerShot(m_intake, m_shot)
    );
  } 
}
