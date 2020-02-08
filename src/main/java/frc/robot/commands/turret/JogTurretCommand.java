package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class JogTurretCommand extends CommandBase {
  private final TurretSubsystem turretSubsystem;

  JogTurretCommand(TurretSubsystem turretSubsytem) {
    addRequirements(turretSubsytem);

    this.turretSubsystem = turretSubsytem;

  }

//    @Override
//    public void execute(){ //requires knowledge of how to use buttons
//        turretSubsystem.manualRotateTurret();
//    }
}
