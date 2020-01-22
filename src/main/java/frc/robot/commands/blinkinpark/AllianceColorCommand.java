package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

import frc.robot.subsystems.BlinkinParkSubsystem;
import frc.robot.subsystems.BlinkinParkSubsystem.Song;


public class AllianceColorCommand extends CommandBase{
    private BlinkinParkSubsystem blinkin;
    private

    AllianceColorCommand(BlinkinParkSubsystem blinkin){
        this.blinkin = blinkin;
        addRequirements(blinkin);
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
    }

    private void setColor() {
        if (alliance == Alliance.Blue) {
        blinkinPark.playSong(Song.LightChaseBlue);
        } else if (alliance == Alliance.Red) {
        blinkinPark.playSong(Song.LightChaseRed);
        } else {
        blinkinPark.playSong(Song.LightChaseGray);
        }
    }
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
