package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class DefaultVisionFollowCommand extends CommandBase {
    private final TurretSubsystem turretSubsystem;
    private final VisionSubsystem visionSubsystem;

    DefaultVisionFollowCommand(TurretSubsystem turretSubsystem, VisionSubsystem visionSubsystem){
        addRequirements(turretSubsystem, visionSubsystem);

        this.turretSubsystem = turretSubsystem;
        this.visionSubsystem = visionSubsystem;

    }

    @Override
    public void execute() {
        if(visionSubsystem.getTargetFound()){
            turretSubsystem.setTargetPosition(visionSubsystem.getAngleToTarget());
        }else{//currently unsure with where to point shooter with no vision

        }

    }

    @Override
    public void end(boolean interrupted) {
        turretSubsystem.setTargetPosition(0);
    }
}
