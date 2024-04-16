// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.autos.AutoSelector;
import frc.robot.commands.Climb;
import frc.robot.commands.LeftSwerveSlow;
import frc.robot.commands.Reverse;
import frc.robot.commands.ReverseSwerveSlow;
import frc.robot.commands.RightSwerveSlow;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShotReverse;
import frc.robot.commands.SubShoot;
import frc.robot.commands.SwerveSlow;
import frc.robot.commands.in;
import frc.robot.commands.outake;
import frc.robot.commands.swervedrive.drivebase.AbsoluteFieldDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final Climber m_climb = new Climber();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final Shooter m_shot = new Shooter();
  private final AutoSelector autoSelector;
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController driverXbox = new CommandXboxController(0);
  final CommandXboxController schmo = new CommandXboxController(1);  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    autoSelector = new AutoSelector(drivebase, m_intake, m_shot);
    // Configure the trigger bindings
    configureBindings();

    AbsoluteFieldDrive closedAbsoluteFieldDrive = new AbsoluteFieldDrive(drivebase,
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                OperatorConstants.LEFT_X_DEADBAND),
                                                                   () -> -MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                OperatorConstants.RIGHT_X_DEADBAND)
                                                                                                );

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> -MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverXbox.getRightX());


    drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
  }

 
  private void configureBindings()
  {
    schmo.leftBumper().whileTrue(new Reverse (m_climb));
    
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    driverXbox.rightTrigger().whileTrue(new Shoot(m_shot));
    driverXbox.leftTrigger().whileTrue(new SubShoot(m_shot));
    driverXbox.pov(90).whileTrue(new ShotReverse(m_shot));
    driverXbox.pov(180).whileTrue(new outake(m_intake));
    driverXbox.pov(0).whileTrue(new in(m_intake));
    driverXbox.rightBumper().whileTrue(new Climb(m_climb));
    /* 
    driverXbox.pov(0).whileTrue(new SwerveSlow(drivebase));
    driverXbox.pov(90).whileTrue(new RightSwerveSlow(drivebase));
    driverXbox.pov(180).whileTrue(new ReverseSwerveSlow(drivebase));
    driverXbox.pov(270).whileTrue(new LeftSwerveSlow(drivebase));
    */
    driverXbox.a().onTrue((Commands.runOnce(drivebase::zeroGyro)));
    driverXbox.x().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
    driverXbox.b().whileTrue(
        Commands.deferredProxy(() -> drivebase.driveToPose(
                                   new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                              ));
    // driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(
  )
  {
    // An example command will be run in autonomous
    return autoSelector.getSelected();
    //return null;
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }
/* 
  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
  */
}