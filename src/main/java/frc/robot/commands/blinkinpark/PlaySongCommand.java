package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BlinkinParkSubsystem;
import frc.robot.subsystems.BlinkinParkSubsystem.Song;

public class PlaySongCommand extends CommandBase{

    private Song song;
    private BlinkinParkSubsystem blinkinPark;

    public PlaySongCommand(Song song, BlinkinParkSubsystem blinkinPark){
        this.song = song;
        this.blinkinPark = blinkinPark;
        addRequirements(blinkinPark);
    }
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        blinkinPark.playSong(song);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}