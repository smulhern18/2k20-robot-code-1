# 190's Infinite Recharge Robot Code

![Java CI](https://github.com/Team-190/2k20-robot-code/workflows/Java%20CI/badge.svg)

This repository is the code for FRC Team 190's robot code. We will try to keep the README updated with cool features.

## Auto
Link to auto path pictures: 
[Autos](src/main/java/frc/robot/commands/auto/docs/autos.md)
Many autos are planned, which allow for adaptation depending on the strategic situation.

## Current Features
 - Vision
 - Trajectory Following
 - Basic Subsystem Implementations
 - LEDs!
 
## Vision

The current vision uses a Logitech C920 to do vision processing on a Raspberry Pi 4. These systems allow quick processing of a very complex vision task. First, the vision isolates the vision target through lowering the exposure, and then finds the four vertices of the trapezoid of the target. Then it applies perspective and point to map the two dimensional coordinates that it sees, to points in three dimensional states allowing us to draw distance and angle estimations in relation to the target and the powerport. This will be greatly beneficial as it would allow automatic aiming which would increase our accuracy greatly in match. 

## Trajectory Following

The robot implements the new WPILib trajectory following api. First the drive train was characterized, and those numbers were used to run the Ramsete Command which allowed the following of curves defined between way points on its path. The trajectory makes use of the NavX and encoders to accurately know its position which allows it to follow the prescribed path precisely, even with possible disturbance.

## Subsystems

The subsystems have begun to be implemented, but much of the actual code is dependent on the mechanical portions.

## LEDs!

LEDs are always a fun addition to our robot and allow it to "look slick" and stand out from the others. Furthermore, it can be crucial to indicating to the drive team the state of the robot.


