package frc.robot.models;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
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

public class AutoChooser {
  private SendableChooser<Command> chooser;
  private final static String NORMAL = "Normal :", THIEF = "Thief: ";

  public AutoChooser(RobotContainer robotContainer) {
    chooser = new SendableChooser<>();
    chooser.addOption(NORMAL + "Crazy", new CrazyAutoCommand(robotContainer));
    chooser.addOption(NORMAL + "Trench", new TrenchAutoCommand(robotContainer));
    chooser.addOption(NORMAL + "Rendezvous Zigzag", new RendezvousZigzagAutoCommand(robotContainer));
    chooser.addOption(NORMAL + "Rendezvous Beam Ride", new RendezvousBeamRideAutoCommand(robotContainer));
    chooser.addOption(NORMAL + "Three Cell", new ThreeCellAutoCommand(robotContainer));
    chooser.addOption(NORMAL + "Two Cell", new TwoCellAutoCommand(robotContainer));
    chooser.addOption(THIEF + "Trench", new TrenchThiefAutoCommand(robotContainer));
    chooser.addOption(THIEF + "Half Thief Trench", new HalfThiefTrenchAutoCommand(robotContainer));
    chooser.addOption(THIEF + "Opposite Five", new OppositeFiveAutoCommand(robotContainer));
    chooser.addOption(THIEF + "Opposite Three", new OppositeThreeAutoCommand(robotContainer));
    chooser.addOption(THIEF + "Opposite Two", new OppositeTwoAutoCommand(robotContainer));
    Constants.SubsystemConstants.DRIVER_TAB.add(chooser)
        .withPosition(2, 0)
        .withSize(2, 2);
  }

  public Command getCommand() {
    return (Command)chooser.getSelected();
  }
}
