// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot.autos;



import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.AutoDriveBase.AutoAltFoward;
import frc.robot.autos.AutoDriveBase.AutoFoward;
import frc.robot.autos.CommandGroups.AutoTwoShot;
import frc.robot.autos.CommandGroups.SpeakerShot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;



/** Add your docs here. */
public class AutoSelector {
      private final SendableChooser<Command> chooser = new SendableChooser<>();


      public AutoSelector(SwerveSubsystem swerve, IntakeSubsystem intake, Shooter m_shot){
            chooser.setDefaultOption("Default(Oneshot)", new SequentialCommandGroup(new SpeakerShot(intake, m_shot), new AutoAltFoward(swerve)));

            chooser.addOption("Two Shot", new AutoTwoShot(intake, m_shot, swerve));
            SmartDashboard.putData(chooser);
      }
      public Command getSelected(){
            return chooser.getSelected();
      }
}
