// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DrivetrainSub;
import frc.robot.subsystems.IndexerSub;
import frc.robot.subsystems.ShooterSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
    //IO
    //Controllers
    XboxController rc_driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
    XboxController rc_operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
  //Shooter
    private final ShooterSub rc_shootersub = new ShooterSub();
    //commands
      //default command
      private final Command rc_idleshooter = new RunCommand(rc_shootersub::idleShooter, rc_shootersub);
  //Drivetrain
    private final DrivetrainSub rc_drivetrainsub = new DrivetrainSub();
      //default command
      private final Command rc_drive = new RunCommand(()-> rc_drivetrainsub.drive(rc_driverController.getRightTriggerAxis(), 
                                                                                  rc_driverController.getLeftTriggerAxis(), 
                                                                                  rc_driverController.getLeftX()), rc_drivetrainsub);
  //Indexer
  private final IndexerSub rc_indexersub = new IndexerSub();
    //Index
    private final Command rc_indexup = new RunCommand(()-> rc_indexersub.index(Constants.INDEXER_OUTPUT), rc_indexersub);
    private final Command rc_indexdown = new RunCommand(()-> rc_indexersub.index(-Constants.INDEXER_OUTPUT), rc_indexersub);
    //Shoot

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //set default commands
    rc_shootersub.setDefaultCommand(rc_idleshooter);
    rc_drivetrainsub.setDefaultCommand(rc_drive);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Indexer
      //Index Up
      new JoystickButton(rc_operatorController, XboxController.Button.kRightBumper.value).whileHeld(rc_indexup);
      //Index Down
      new JoystickButton(rc_operatorController, XboxController.Button.kLeftBumper.value).whileHeld(rc_indexdown);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  */
}