package cinema;


import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    private static char[][] cinemaHall;
    static Scanner scanner = new Scanner(System.in);
    private static int currentIncome;
    private static int totalIncome;
    private static int numberOfPurchasedTicket;

    public static void main(String[] args) {
        createCinema();
        int command;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    printCinemaHall();
                    break;
                case 2:
                    takeTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error Wrong Command");
                    break;
            }
        } while (command != 0);
    }

    public static void createCinema() {
        System.out.println("Enter the number of rows: ");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int numberOfSeats = scanner.nextInt();

        cinemaHall = new char[numberOfRows][numberOfSeats];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                cinemaHall[i][j] = 'S';
            }
        }
    }
    public static void printCinemaHall() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinemaHall[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinemaHall.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(" " + cinemaHall[i][j]);
            }
            System.out.println();
        }
    }
    public static void takeTicket() {
        try{

            System.out.println("Enter a row number: ");
            int seatRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seatNumber = scanner.nextInt();
            int totalNumberOfSeats = cinemaHall.length * cinemaHall[0].length;

            if(cinemaHall[seatRow - 1][seatNumber - 1] == 'B'){
            System.out.println("That ticket has already been purchased!");
            takeTicket();
        }



        int priceTicket;
        if (totalNumberOfSeats <= 60) {
            priceTicket = 10;
        } else {
            int frontHalfRow = cinemaHall.length / 2;
            if (seatRow <= frontHalfRow) {
                priceTicket = 10;
            } else {
                priceTicket = 8;
            }
        }
            numberOfPurchasedTicket++;
            currentIncome += priceTicket;
            System.out.println("Ticket price: $" + priceTicket);
            cinemaHall[seatRow - 1][seatNumber - 1] = 'B';
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            takeTicket();
        }
    }
    public static void statistics() {
        int numOfSeats = cinemaHall.length * cinemaHall[0].length;
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTicket);
        double percentageOccupancy = ((double)numberOfPurchasedTicket / numOfSeats) * 100;
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println("Percentage: " + df.format(percentageOccupancy)+"%");
        System.out.println("Current income: $" + currentIncome);
        totalIncome(numOfSeats);



    }
    public static void totalIncome(int seats){
        if (seats <= 60) {
            totalIncome = seats * 10;
        } else {
            int frontHalfRow = cinemaHall.length / 2;
            int firstHalfRowIncome = (frontHalfRow * cinemaHall[0].length) * 10;
            int backHalfRowIncome = (cinemaHall.length - frontHalfRow) * (cinemaHall[0].length * 8);
            totalIncome = firstHalfRowIncome + backHalfRowIncome;
        }
        System.out.println("Total income: $"+totalIncome);
    }

    }

