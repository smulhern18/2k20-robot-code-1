package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class TurretCommand extends PIDCommand {
  static PIDController controller = new PIDController(Constants.TurretConstants.P, Constants.TurretConstants.I, Constants.TurretConstants.D);
TurretSubsystem turretSubsystem;
double setpoint;
  public TurretCommand(RobotContainer robotContainer, double setpoint) {
    super(
        controller,
        robotContainer.turretSubsystem::getCurrentPositionDegrees,
        () ->TurretSubsystem.convertDegreesToPot(setpoint),
        robotContainer.turretSubsystem::set,
        robotContainer.turretSubsystem
    );
    this.setpoint = setpoint;
    TurretSubsystem turretSubsystem = robotContainer.turretSubsystem;
  }

  @Override
  public void execute() {
    super.execute();
    System.out.println(controller.getPositionError());
//    System.out.println(controller.calculate(turretSubsystem.getCurrentPositionDegrees(), TurretSubsystem.convertDegreesToPot(setpoint)));
  }
}
