import java.util.*;
class Elevator {
    private int currentFloor = 0;
    private boolean moving = false;
    private boolean emergencyTriggered = false;
    private boolean running = true;
    private final PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    private final PriorityQueue<Integer> downQueue = new PriorityQueue<>(Collections.reverseOrder());
    public synchronized void request(int floor) {
        if (emergencyTriggered) {
            System.out.println("Ignoring request due to emergency mode.");
            return;
        }
        if (floor > currentFloor) {
            upQueue.offer(floor);
        } else {
            downQueue.offer(floor);
        }
        System.out.println(Thread.currentThread().getName() + " Requested Floor: " + floor); 
        if (!moving) {
            moving = true;
            notify();
        }
    }
    private synchronized void move() {
        while (running) {
            while (!emergencyTriggered && upQueue.isEmpty() && downQueue.isEmpty() && running) {
                try {
                    moving = false;
                    System.out.println("No requests. Elevator is sleeping.");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!running) {
                System.out.println("Elevator system shutting down.");
                break;
            }
            if (emergencyTriggered) {
                handleEmergency();
                continue;
            }
            Integer nextFloor = getNextFloor();
            if (nextFloor != null) {
                processFloor(nextFloor);
            }
        }
    }    
    private Integer getNextFloor() {
        if (!upQueue.isEmpty()) {
            return upQueue.poll();
        } else if (!downQueue.isEmpty()) {
            return downQueue.poll();
        }
        return null;
    }
      private void processFloor(int floor) {
System.out.println("Elevator moving from Floor " + currentFloor + " to Floor " + floor);
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Elevator interrupted!");
        }
        currentFloor = floor;
        System.out.println("Elevator reached Floor " + currentFloor);
        synchronized (this) {
            notifyAll();
        }
    }
    public synchronized void emergencyStop() {
        emergencyTriggered = true;
        upQueue.clear();
        downQueue.clear();
        System.out.println("EMERGENCY! Elevator stopping immediately.");
        notify();
    }
    private synchronized void handleEmergency() {
        System.out.println("EMERGENCY! Stopping all operations.");
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        emergencyTriggered = false;
        System.out.println("Emergency resolved. Elevator is ready to take new requests.");
        notifyAll();}
 public void startElevator() {
        new Thread(this::move).start();
    }
    public synchronized void stopElevator() {
        running = false;
        notifyAll();
    }
}
class Passenger extends Thread {
    private final Elevator elevator;
    private final int floor;
    public Passenger(Elevator elevator, int floor) {
        this.elevator = elevator;
        this.floor = floor;
    }
    public void run() {
        synchronized (elevator) {
            elevator.request(floor);
        }
    }
}
public class ESD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Elevator elevator = new Elevator();
        elevator.startElevator();
        while (true) {
            System.out.print("Enter floor requests (comma separated) or type 's'/'em': ");
            String input = scanner.nextLine();            
            if (input.equalsIgnoreCase("s")) {
                System.out.println("Elevator System is sleeping");
                elevator.stopElevator();
                break;
            } else if (input.equalsIgnoreCase("em")) {
                elevator.emergencyStop();
                continue;
            }
            String[] tokens = input.split(",");
            for (String token : tokens) {
                int floor = Integer.parseInt(token.trim());
                new Passenger(elevator, floor).start();
            }
        }
        scanner.close();
    }
}
