import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class Machine {

    Scanner keyboard = new Scanner(System.in);

    Ticket t = new Ticket();

    public void parkingGarage() throws IOException {
        checkInMenu();
        checkOutMenu();
        t.setVehicleID();
        if (t.isLost()) {
            t.setTicketFee(25.00);
        }
        else {
            t.setHoursParked();
            t.calculateTicketFee();
        }
        ticketReceipt();
        t.addTicketToFile(t);
    }

    public void bestValueParking() {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================");
    }

    public void checkInMenu() throws FileNotFoundException {
        bestValueParking();
        System.out.println("1 – Check/In");
        System.out.println("3 – Close Garage");
        System.out.println("=> ");
        String userInput = keyboard.nextLine();
        userInputCheckIn(userInput);
    }

    public void userInputCheckIn(String userInput) throws FileNotFoundException {
        if (userInput.equals("1")) {
            checkIn();
        }
        else if (userInput.equals("3")) {
            closeGarage();
        }
        else {
            System.out.println("Incorrect input. Try again.");
            checkInMenu();
        }
    }

    public void checkIn() {
        RandomTimeGenerator rtg = new RandomTimeGenerator();
        LocalTime checkInTime =  rtg.generateTimeAtCheckIn();
        t.setTicketCheckInTime(checkInTime);
    }

    public void checkOutMenu() {
        bestValueParking();
        System.out.println("1 – Check/Out");
        System.out.println("2 – Lost Ticket");
        System.out.println("=> ");
        String userInput = keyboard.nextLine();
        userInputCheckOut(userInput);
    }

    public void userInputCheckOut(String userInput) {
        if (userInput.equals("1")) {
            checkOut();
        }
        else if (userInput.equals("2")) {
            lostTicket();
        }
        else {
            System.out.println("Incorrect input. Try again.");
            checkOutMenu();
        }
    }

    public void checkOut() {
        RandomTimeGenerator rtg = new RandomTimeGenerator();
        LocalTime checkOutTime =  rtg.generateTimeAtCheckOut();
        t.setTicketCheckOutTime(checkOutTime);
    }

    public void lostTicket() {
        t.setLost(true);
    }

    public void ticketReceipt() {
        bestValueParking();
        System.out.println("Receipt for a vehicle id " + t.getVehicleID());
        System.out.println();
        if(t.isLost()) {
            System.out.println("Lost ticket");
        }
        else {
            System.out.println(t.getHoursParked() + " hours parked " + t.getTicketCheckInTime() + "am - " + t.getTicketCheckOutTime() + "pm");
        }
        System.out.println("$" + t.getTicketFee());
    }

    public void closeGarage() throws FileNotFoundException {
        bestValueParking();
        t.readTicketsFromFile();
        double total = t.ticketResultsArray.get(0) + t.ticketResultsArray.get(2);
        System.out.println("Activity to Date");
        System.out.println();
        System.out.println("$" + t.ticketResultsArray.get(0) + " was collected from " + t.ticketResultsArray.get(1) + " check ins");
        System.out.println("$" + t.ticketResultsArray.get(2) + " was collected from " + t.ticketResultsArray.get(3) + " lost tickets");
        System.out.println("$" + total + " was collected overall");
        System.exit(0);
    }

}
