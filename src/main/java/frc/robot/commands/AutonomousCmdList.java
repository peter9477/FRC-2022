package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.DrivebaseSubsystem;


public class AutonomousCmdList extends SequentialCommandGroup {
    private ShuffleboardTab tab = Shuffleboard.getTab("Drive");
    private NetworkTableEntry autoSpeed =
        tab.add("Autonomous Speed", 0.5)
           .getEntry();
    private NetworkTableEntry autoDistance =
       tab.add("Autonomous Distance (inches)", 12)
          .getEntry();
           
    public AutonomousCmdList(DrivebaseSubsystem m_drivebase) {
        super();
        double speed = autoSpeed.getDouble(0.5);
        double distance = autoDistance.getDouble(12.0);
        this.addCommands(new DriveDistance(m_drivebase, distance, speed));
    }
    
    
}
