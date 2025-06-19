# Elevator System - Sleeping Barber Problem

## Overview
Elevator System is a Java-based multithreaded application that demonstrates the classic Sleeping Barber Problem through an elevator simulation. The system efficiently manages passenger requests using concurrent programming concepts, where the elevator acts as the "barber" and passengers represent "customers." The elevator remains idle when no requests are pending and automatically wakes up to process new requests, showcasing synchronization mechanisms and thread safety in Java.

## Features
- *Smart Request Processing*: Uses priority queues to handle upward and downward floor requests efficiently
- *Energy Efficient Design*: Elevator sleeps when idle and wakes up automatically for new requests
- *Concurrent Operations*: Multiple passengers can request floors simultaneously without conflicts
- *Emergency Override*: Immediate emergency stop functionality that clears all pending requests
- *Thread Safety*: Synchronized methods prevent race conditions and ensure safe concurrent access
- *Graceful Shutdown*: Clean system termination with proper resource cleanup

## Installation
- *Ensure Java is installed*
bash
java -version

- *Download the source code*
bash
# Download ESD.java file to your local directory

- *Compile the application*
bash
javac ESD.java

- *Run the application*
bash
java ESD


## Usage
- *Floor Requests*: Enter comma-separated floor numbers (e.g., 1,5,3,7)
- *Emergency Stop*: Type em to trigger emergency mode
- *System Shutdown*: Type s to gracefully shut down the elevator

### Example Session

Enter floor requests (comma separated) or type 's'/'em': 3,7,1
Thread-1 Requested Floor: 3
Thread-2 Requested Floor: 7
Thread-3 Requested Floor: 1
Elevator moving from Floor 0 to Floor 1
Elevator reached Floor 1
Elevator moving from Floor 1 to Floor 3
Elevator reached Floor 3
Elevator moving from Floor 3 to Floor 7
Elevator reached Floor 7
No requests. Elevator is sleeping.

Enter floor requests (comma separated) or type 's'/'em': em
EMERGENCY! Elevator stopping immediately.
EMERGENCY! Stopping all operations.
Emergency resolved. Elevator is ready to take new requests.

Enter floor requests (comma separated) or type 's'/'em': s
Elevator System is sleeping
Elevator system shutting down.


## Team
- *Athithya S* - CB.EN.U4CSE22405
- *Vishaarad S* - CB.EN.U4CSE22451  
- *Yuvaraj Ayyanar A* - CB.EN.U4CSE22455
