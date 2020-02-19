package frc.robot.commands.colorwheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.RobotContainer;
import frc.robot.models.Color;
import frc.robot.subsystems.ColorWheelSubsystem;

public class PositionalCommand extends CommandBase {
	String currentColor;
	Color goalColor;
	ColorWheelSubsystem colorWheelSubsystem;
	RobotContainer robotContainer;

	public PositionalCommand(RobotContainer robotContainer, Color goalColor) {
		this.colorWheelSubsystem = robotContainer.colorWheelSubsystem;
		this.robotContainer = robotContainer;
		this.goalColor = goalColor;
		addRequirements(colorWheelSubsystem);
	}

	@Override
	public void initialize(){
		currentColor = ColorWheelConstants.UNKNOWN;
	}

	@Override
	public void execute(){
		currentColor = colorWheelSubsystem.detectColor();
		colorWheelSubsystem.rotateWheel();
	}

	@Override
	public boolean isFinished(){
		return goalColor.value.equals("red") && currentColor.equals("blue") || goalColor.value.equals("blue") && currentColor.equals("red") ||
				goalColor.value.equals("yellow") && currentColor.equals("green") || goalColor.value.equals("green") && currentColor.equals("yellow"); // based on compliment
	}

	@Override
	public void end(boolean interrupted){
		colorWheelSubsystem.stopWheel();
	}
}
