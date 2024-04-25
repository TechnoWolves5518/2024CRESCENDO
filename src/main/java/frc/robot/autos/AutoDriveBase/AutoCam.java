// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.AutoDriveBase;

import org.photonvision.PhotonUtils;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.CamConstants;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class AutoCam extends Command {
  SwerveSubsystem swerve;  
  Camera camera;

  public AutoCam(SwerveSubsystem swerve, Camera camera) {
    this.swerve = swerve;
    this.camera = camera;
    addRequirements(swerve);
    addRequirements(camera);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    var result = CamConstants.cam.getLatestResult();
    if (result.hasTargets()) {
      double range = PhotonUtils.calculateDistanceToTargetMeters(CamConstants.cameraHeightMeters, CamConstants.goalDistanceMeters, CamConstants.cameraAngleRadians, Units.degreesToRadians(result.getBestTarget().getYaw()));
      double forwardSpeed = -CamConstants.driveController.calculate(range, CamConstants.goalDistanceMeters);
      var rotationSpeed = -CamConstants.driveController.calculate(result.getBestTarget().getYaw(), 0);
      swerve.drive(new Translation2d(forwardSpeed,0), rotationSpeed, true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerve.drive(new Translation2d(0, 0), 0, true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
