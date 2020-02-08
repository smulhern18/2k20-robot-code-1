package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallPathSubsystem;

public class SetIndexerCountCommand extends CommandBase {
    private BallPathSubsystem ballPathSubsystem;
    public SetIndexerCountCommand(BallPathSubsystem ballPathSubsystem,int amountOfPreloadedBalls) {
        ballPathSubsystem.setPreloadedBalls(amountOfPreloadedBalls);
    }
}

