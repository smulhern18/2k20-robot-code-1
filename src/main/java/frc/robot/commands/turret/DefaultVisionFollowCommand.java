package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class DefaultVisionFollowCommand extends CommandBase {
    private final TurretSubsystem turretSubsystem;
    private final VisionSubsystem visionSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;//need for odometry

    DefaultVisionFollowCommand(TurretSubsystem turretSubsystem, VisionSubsystem visionSubsystem, DrivetrainSubsystem drivetrainSubsystem){
        addRequirements(turretSubsystem, visionSubsystem, drivetrainSubsystem);

        this.turretSubsystem = turretSubsystem;
        this.visionSubsystem = visionSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;

    }

    @Override
    public void execute() {
        if(visionSubsystem.getTargetFound()){
            turretSubsystem.setTargetPosition(visionSubsystem.getAngleToTarget());
        }else{//currently unsure with where to point shooter with no vision
            turretSubsystem.setTargetPosition(-1.0 * drivetrainSubsystem.getYawDegrees());//check logic later with real turret
        }

    }

    @Override
    public void end(boolean interrupted) {
        turretSubsystem.setTargetPosition(0);
    }
}
