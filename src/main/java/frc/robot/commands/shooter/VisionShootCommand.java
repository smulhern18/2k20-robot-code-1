package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class VisionShootCommand extends CommandBase {
  private VisionSubsystem visionSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private BallPathSubsystem ballPathSubsystem;

  public VisionShootCommand(RobotContainer robotContainer) {
    visionSubsystem = robotContainer.visionSubsystem;
    shooterSubsystem = robotContainer.shooterSubsystem;
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(shooterSubsystem, ballPathSubsystem);
  }

  @Override
  public void execute() {
    double targetRPM = shooterSubsystem.inchesToRPM(visionSubsystem.getDistanceToTarget());
    shooterSubsystem.shoot(targetRPM);
    if(shooterSubsystem.atTargetRPM(targetRPM)){
      ballPathSubsystem.runAll();
    } else {
      ballPathSubsystem.stopAll();
    }
  }

  @Override
  public boolean isFinished() {
    return ballPathSubsystem.getBallsInRobot() == 0;
  }

  @Override
  public void end(boolean interrupted){
    shooterSubsystem.stop();
  } 
}
