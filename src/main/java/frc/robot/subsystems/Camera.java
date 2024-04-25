// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CamConstants;

public class Camera extends SubsystemBase {
  /** Creates a new Camera. */
  private final PhotonCamera cam = CamConstants.cam;
  final double CameraHeight = CamConstants.cameraHeightMeters;
  final double TargetHeight = CamConstants.scoringAprilTagHeightMeters;
  final double cameraPitchRadians = CamConstants.cameraAngleRadians;
  public boolean hasTargets = false;
  public boolean isTargeting = true;
  private double targetAngle = CamConstants.targetangle;
  private double targetDistance = CamConstants.goalDistanceMeters;
  private double range;
  double forwardSpeed;
  double x_pitch = CamConstants.xPitch;

  public boolean hasTargets() {
    return hasTargets;
  }
   
  public double getTargetAngle() {
    return targetAngle;
  }

  public double getTargetDistance() {
    return targetDistance;
  }

  public void pipelineIndex() {
    cam.setPipelineIndex(0);
  }

  @Override
  public void periodic() {
    var targeted = cam.getLatestResult();

    if(targeted.hasTargets() == true){
      hasTargets = true;
      var targetangle = targeted.getBestTarget().getYaw();
      range = PhotonUtils.calculateDistanceToTargetMeters(
                    CameraHeight,
                    TargetHeight,
                    cameraPitchRadians ,
                    Units.degreesToRadians(targetangle));
      
    }
    else{
      hasTargets = false;
      range = -2;
    }
    // This method will be called once per scheduler run
  }
}
