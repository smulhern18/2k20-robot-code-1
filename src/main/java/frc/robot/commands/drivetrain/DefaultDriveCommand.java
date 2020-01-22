package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.input.AttackThree;
import frc.robot.input.AttackThree.AttackThreeAxis;

/**
 * Default Drive Command
 */
public class DefaultDriveCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;

    private final AttackThree leftStick;
    private final AttackThree rightStick;


    public DefaultDriveCommand(DrivetrainSubsystem drivetrain, AttackThree leftStick, AttackThree rightStick) {
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    /**
     * Called every time the scheduler runs while the command is scheduled.
     * Takes the joystick values and makes the robot move accordingly.
     */
    @Override
    public void execute() {
        double left = leftStick.getAxis(AttackThreeAxis.Y);
        double right = rightStick.getAxis(AttackThreeAxis.Y);
        drivetrain.drive(left, right);
    }

    /**
     * At the end, stop the drivetrain.
     */
    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0);
    }
}
