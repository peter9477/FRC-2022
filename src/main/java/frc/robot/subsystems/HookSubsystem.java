// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HookSubsystem extends SubsystemBase {
  private final CANSparkMax m_winchMotor;

  private double m_pow = 0;

  /** Creates a new HookSubsystem. */
  public HookSubsystem(int winchMotor) {
    m_winchMotor = new CANSparkMax(winchMotor, MotorType.kBrushless);
    m_winchMotor.setIdleMode(IdleMode.kBrake);
  }

  /**
   * Extends the hook.
   */
  public void extend(double extendPow) {
    m_pow = extendPow;
  }

  /**
   * Retracts the hook.
   */
  public void retract(double retractPow) {
    m_pow = retractPow;
  }

  /**
   * Stops the hook.
   */
  public void stop() {
      m_pow = 0; 
  }

  @Override
  public void periodic() {
    m_winchMotor.set(m_pow);
  }
}