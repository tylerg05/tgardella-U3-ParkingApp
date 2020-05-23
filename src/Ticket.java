import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a ticket.
 */
public class Ticket {
    private LocalTime ticketCheckInTime;
    private LocalTime ticketCheckOutTime;
    private long hoursParked;
    private int vehicleID;
    private double ticketFee;
    private boolean isLost = false;
    List<Ticket> ticketArray = new ArrayList<Ticket>();
    List<Double> ticketResultsArray = new ArrayList<>();
    RandomTimeGenerator rtg = new RandomTimeGenerator();
    private final double MINIMUM_FEE = 5.00;


    public void addTicketToFile(Ticket t) throws IOException {
        File file = new File("tickets.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(t.getTicketCheckInTime() + "," + t.getTicketCheckOutTime() + "," + t.getVehicleID() + "," + t.getTicketFee() + "," + t.isLost + "\r\n");
        fr.flush();
        fr.close();
    }

    public void readTicketsFromFile() throws FileNotFoundException {
        File file = new File("tickets.txt");
        Scanner fileReader = new Scanner(file);
        double moneyFromCheckIns = 0, numberOfCheckIns = 0, moneyFromLostTickets = 0, numberOfLostTickets = 0;
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] ticketInfo = line.split(",");
            Ticket t = new Ticket();
            t.setVehicleID(Integer.parseInt(ticketInfo[2]));
            t.setTicketFee(Double.parseDouble(ticketInfo[3]));
            t.setLost(Boolean.parseBoolean(ticketInfo[4]));
            if (ticketInfo[4].equals("true")) {
                numberOfLostTickets++;
                moneyFromLostTickets+=25;
            }
            else {
                moneyFromCheckIns+=Double.parseDouble(ticketInfo[3]);
                numberOfCheckIns++;
            }
            ticketArray.add(t);
        }
        ticketResultsArray.add(moneyFromCheckIns);
        ticketResultsArray.add(numberOfCheckIns);
        ticketResultsArray.add(moneyFromLostTickets);
        ticketResultsArray.add(numberOfLostTickets);
    }

    public void addTicketToList(Ticket t) {
        ticketArray.add(t);
    }

    public void calculateTicketFee() {
        double perHourCharge = 0;
        double finalFee = 0;
        if (isLost) {
            finalFee = 25.00;
        }
        else if (getHoursParked() >= 3) {
            perHourCharge = (getHoursParked() - 3) * 1.00;
            finalFee = MINIMUM_FEE + perHourCharge;
        }
        setTicketFee(finalFee);
    }

    public void setHoursParked() {
        this.hoursParked = (12 - getTicketCheckInTime().getHour()) + getTicketCheckOutTime().getHour();
    }

    public long getHoursParked() {
        return hoursParked;
    }

    public void setTicketCheckInTime(LocalTime ticketCheckInTime) {
        this.ticketCheckInTime = rtg.generateTimeAtCheckIn();
    }

    public void setTicketCheckOutTime(LocalTime ticketCheckOutTime) {
        this.ticketCheckOutTime = rtg.generateTimeAtCheckOut();
    }

    public LocalTime getTicketCheckInTime() {
        return ticketCheckInTime;
    }

    public LocalTime getTicketCheckOutTime() {
        return ticketCheckOutTime;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setVehicleID() {
        Random rand = new Random();
        this.vehicleID = new Random().nextInt(101) + 50;
    }

    public double getTicketFee() {
        return ticketFee;
    }

    public void setTicketFee(double ticketFee) {
        this.ticketFee = ticketFee;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }
}
