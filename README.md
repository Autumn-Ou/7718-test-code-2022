# 7718 test code 2022
 Test of a new framework and code for the FRC team 7718 before the Long Island regional.

## motor ID's
### Drive
Right Front : 0, Left Front : 1, Right Back : 2, Left Back : 3

### Cargo
Arm : 4, Intake : 5

### Lift
Lift : 6

## Config file info
The configuration file "robot.conf" is used to change CAN IDs without needing to rebuild and push the code everytime you want to change an ID. The file should be placed on the rio in the /etc/ folder, this can be done using WinSCP https://winscp.net/eng/download.php, to put the file in place or change it connect to the rio over USB A to B and open WinSCP and transfer the file into the priorly mentioned folder. Please note if the configuration file is not found the rio will put a warning in the console and use the backup hard coded IDs for all devices.
