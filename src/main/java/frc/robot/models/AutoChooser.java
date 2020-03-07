package frc.robot.models;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.ShootThreeAutoCommand;
import frc.robot.commands.auto.normal.crazy.CrazyAutoCommand;
import frc.robot.commands.auto.normal.rendezvous.RendezvousBeamRideAutoCommand;
import frc.robot.commands.auto.normal.rendezvous.RendezvousZigzagAutoCommand;
import frc.robot.commands.auto.normal.threecell.ThreeCellAutoCommand;
import frc.robot.commands.auto.normal.trench.TrenchAutoCommand;
import frc.robot.commands.auto.normal.twocell.TwoCellAutoCommand;
import frc.robot.commands.auto.thief.half.HalfThiefTrenchAutoCommand;
import frc.robot.commands.auto.thief.oppositefive.OppositeFiveAutoCommand;
import frc.robot.commands.auto.thief.oppositethree.OppositeThreeAutoCommand;
import frc.robot.commands.auto.thief.oppositetwo.OppositeTwoAutoCommand;
import frc.robot.commands.auto.thief.trench.TrenchThiefAutoCommand;

/**
 * SendableChooser for choosing autos
 */
public class AutoChooser extends SendableChooser<Command> {
  private final static String NORMAL = "Normal :", THIEF = "Thief: ";

  public AutoChooser(RobotContainer robotContainer) {
    super();

//    addOption(NORMAL + "Crazy", new CrazyAutoCommand(robotContainer));
//    addOption(NORMAL + "Trench", new TrenchAutoCommand(robotContainer));
//    addOption(NORMAL + "Rendezvous Zigzag", new RendezvousZigzagAutoCommand(robotContainer));
//    addOption(NORMAL + "Rendezvous Beam Ride", new RendezvousBeamRideAutoCommand(robotContainer));
//    addOption(NORMAL + "Three Cell", new ThreeCellAutoCommand(robotContainer));
//    addOption(NORMAL + "Two Cell", new TwoCellAutoCommand(robotContainer));
    addOption(THIEF + "Half", new HalfThiefTrenchAutoCommand(robotContainer));
//    addOption(THIEF + "Half Thief Trench", new HalfThiefTrenchAutoCommand(robotContainer));
//    addOption(THIEF + "Opposite Five", new OppositeFiveAutoCommand(robotContainer));
//    addOption(THIEF + "Opposite Three", new OppositeThreeAutoCommand(robotContainer));
//    addOption(THIEF + "Opposite Two", new OppositeTwoAutoCommand(robotContainer));
    setDefaultOption("Preload", new ShootThreeAutoCommand(robotContainer));
    Constants.SubsystemConstants.DRIVER_TAB.add(this)
        .withPosition(2, 0)
        .withSize(2, 2);
  }
}
