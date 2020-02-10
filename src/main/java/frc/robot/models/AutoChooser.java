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

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AutoChooser {
  private SendableChooser<String> chooser;
  private final static String NORMAL = "Normal :", THIEF = "Thief: ";
  HashMap<String, Command> entries;

  public AutoChooser(RobotContainer robotContainer) {
    chooser = new SendableChooser<>();
    entries = new LinkedHashMap<>();
    entries.put(NORMAL + "Crazy", new CrazyAutoCommand(robotContainer));
    entries.put(NORMAL + "Trench", new TrenchAutoCommand(robotContainer));
    entries.put(NORMAL + "Rendezvous Zigzag", new RendezvousZigzagAutoCommand(robotContainer));
    entries.put(NORMAL + "Rendezvous Beam Ride", new RendezvousBeamRideAutoCommand(robotContainer));
    entries.put(NORMAL + "Three Cell", new ThreeCellAutoCommand(robotContainer));
    entries.put(NORMAL + "Two Cell", new TwoCellAutoCommand(robotContainer));
    entries.put(THIEF + "Trench", new TrenchThiefAutoCommand(robotContainer));
    entries.put(THIEF + "Half Thief Trench", new HalfThiefTrenchAutoCommand(robotContainer));
    entries.put(THIEF + "Opposite Five", new OppositeFiveAutoCommand(robotContainer));
    entries.put(THIEF + "Opposite Three", new OppositeThreeAutoCommand(robotContainer));
    entries.put(THIEF + "Opposite Two", new OppositeTwoAutoCommand(robotContainer));
    for (String name : entries.keySet()) {
      chooser.addOption(name, name);
    }
    Constants.SubsystemConstants.DRIVER_TAB.add(chooser)
        .withPosition(2, 0)
        .withSize(2, 2);
  }

  public Command getCommand() {
    return entries.get(chooser.getSelected());
  }
}
