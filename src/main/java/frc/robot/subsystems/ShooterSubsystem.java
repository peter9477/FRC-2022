// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private final CANSparkMax m_shooterMotor;
  private final CANSparkMax m_feederMotor;
  private final CANSparkMax m_winchMotor;
  private RelativeEncoder m_winchEncoder;

  private double m_shootPow = 0;
  private double m_feedPow = 0;
  private double m_winchAngle = 0;
  private double m_winchPower = 0;
  double winchEncoderStart = 0;

  /** Creates a new ShooterSubsystem. 
   * @param shooterMotor Set this to the shooter motor
   * @param feederMotor Set this to the feeder motor
   * @param winchMotor set this to the winch motor
  */
  public ShooterSubsystem(int shooterMotor, int feederMotor, int winchMotor) {
    m_shooterMotor = new CANSparkMax(shooterMotor, MotorType.kBrushed);

    m_feederMotor = new CANSparkMax(feederMotor, MotorType.kBrushless);

    m_winchMotor = new CANSparkMax(winchMotor, MotorType.kBrushless);

    m_winchEncoder = m_winchMotor.getEncoder();
  }

  /**
   * Spins the shooter wheel at a specified power level.
   * @param shootPow Speed to spin the wheel. -1 is full backwards, 1 is full forwards.
   */
  public void spinShooter(double shootPow) {
    m_shootPow = shootPow;
  }

  public void spinFeeder(double feedPow) {
    m_feedPow = feedPow;
  }

  public void turnWinch(double winchAngle) {
    m_winchAngle = winchAngle;
    m_winchPower = 0.8;
    winchEncoderStart = m_winchEncoder.getPosition();
  }

  @Override
  public void periodic() {
    m_shooterMotor.set(m_shootPow);
    m_feederMotor.set(m_feedPow);
    m_winchMotor.set(m_winchPower);
    if (m_winchEncoder.getPosition() - winchEncoderStart == m_winchAngle) { 
      // Change this to divide or multiply so it is a specifc angle
      m_winchPower = 0;
    }
    // m_shootPow = 0;
    // m_feedPow = 0;
  }
}
