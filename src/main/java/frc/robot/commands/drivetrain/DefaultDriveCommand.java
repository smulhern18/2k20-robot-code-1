package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.input.AttackThree;
import frc.robot.input.AttackThree.AttackThreeAxis;

/**
 * Default Drive Command
 */
public class DefaultDriveCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;

    private final AttackThree leftStick; //REMINDER subject to change channel number
    private final AttackThree rightStick;


    public DefaultDriveCommand(DrivetrainSubsystem drivetrain, AttackThree leftStick, AttackThree rightStick) { // Constructor
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.drivetrain = drivetrain;

        // Command dependent on having a drive train.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {
    }

    /**
     * Called every time the scheduler runs while the command is scheduled.
     * Takes the joystick values and makes the robot move accordingly.
     */
    @Override
    public void execute() {
        drivetrain.drive(leftStick.getAxis(AttackThreeAxis.Y), rightStick.getAxis(AttackThreeAxis.Y));
    }

    /**
     * At the end, stop the drivetrain.
     */
    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
