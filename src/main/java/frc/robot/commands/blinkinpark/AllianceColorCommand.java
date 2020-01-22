package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

import frc.robot.subsystems.BlinkinParkSubsystem;
import frc.robot.subsystems.BlinkinParkSubsystem.Song;


public class AllianceColorCommand extends CommandBase{
    private BlinkinParkSubsystem blinkin;
    private Alliance alliance;

    AllianceColorCommand(BlinkinParkSubsystem blinkin){
        this.blinkin = blinkin;
        addRequirements(blinkin);
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {
      if(alliance != null){
        setColor();
      }
    }

    @Override
    public void execute() {
      Alliance newAlliance = DriverStation.getInstance().getAlliance();
      if (newAlliance != alliance) {
        alliance = newAlliance;
        setColor();
      }
    }

    @Override
    public void end(boolean interrupted) {
    }

    private void setColor() {
        if (alliance == Alliance.Blue) {
        blinkin.playSong(Song.LightChaseBlue);
        } else if (alliance == Alliance.Red) {
        blinkin.playSong(Song.LightChaseRed);
        } else {
        blinkin.playSong(Song.LightChaseGray);
        }
    }
  

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
