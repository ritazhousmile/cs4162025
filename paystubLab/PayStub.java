/**
 * pay stub lab
 * @author Huan Rita Zhou
 * @version 1.0
 */
import java.util.Scanner;

public class PayStub {
    public String name;
    public int anniversaryMonth;
    public int anniversaryYear;
    public int hoursWorked;
    public String jobTitle;
    public double hourlyPayRate;
    public int monthsWorked;
    public double vacation_Hours;
    public double grossPay;
    public double retirementWithholding;
    public double taxWithholding;
    public double net_Pay;

    // Default constructor
    public PayStub() {
        name = "";
        anniversaryMonth = 0;
        anniversaryYear = 0;
        hoursWorked = 0;
        jobTitle = "";
        hourlyPayRate = 0.0;
    }

    // Parameterized constructor
    public PayStub(String name, int anniversaryMonth, int anniversaryYear, int hoursWorked, String jobTitle, double hourlyPayRate) {
        this.name = name;
        this.anniversaryMonth = anniversaryMonth;
        this.anniversaryYear = anniversaryYear;
        this.hoursWorked = hoursWorked;
        this.jobTitle = jobTitle;
        this.hourlyPayRate = hourlyPayRate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMonth() {
        return anniversaryMonth;
    }

    public int getYear() {
        return anniversaryYear;
    }

    public int getHours() {
        return hoursWorked;
    }

    public String getTitle() {
        return jobTitle;
    }

    public double getPayRate() {
        return hourlyPayRate;
    }

    // Method to calculate number of months worked
    public int numMonthsWorked() {
        int currentMonth = 1;
        int currentYear = 2019;
        monthsWorked = (currentYear - anniversaryYear) * 12 + (currentMonth - anniversaryMonth);
        return monthsWorked;
    }

    // Method to calculate vacation hours
    public double vacationHours() {
        vacation_Hours = numMonthsWorked() * 8.25;
        return Math.round(vacation_Hours * 100.0) / 100.0;
    }

    // Method to calculate gross pay
    public double grossPay() {
        grossPay = hoursWorked * hourlyPayRate;
        return grossPay;
    }

    // Method to calculate retirement withholding
    public double retHold() {
        retirementWithholding = grossPay * 0.052;
        return Math.round(retirementWithholding * 100.0) / 100.0;
    }

    // Method to calculate tax withholding
    public double tax() {
        double taxableIncome = grossPay - retHold();
        taxWithholding = taxableIncome * 0.28;
        return Math.round(taxWithholding * 100.0) / 100.0;
    }

    // Method to calculate net pay
    public double netPay() {
        net_Pay = grossPay - retHold() - tax();
        return Math.round(net_Pay * 100.0) / 100.0;
    }

    // Method to print the company logo
    public void drawLogo() {
        System.out.println("      Gekko & Co.");
        System.out.println();
        System.out.println("          \"$\"");
        System.out.println("          ~~~");
        System.out.println("         /  \\ `.");
        System.out.println("        /    \\  /");
        System.out.println("       /_ _ _ \\/");
    }

    // Method to print employee and pay information
    public void printInfo() {
        System.out.println("==========================================");
        drawLogo();
        System.out.println("------------------------------------------");
        System.out.println("Pay Period:     1/2019");
        System.out.printf("Name:           %s\n", name);
        System.out.printf("Title:          %s\n", jobTitle);
        System.out.printf("Anniversary:    %d/%d\n", anniversaryMonth, anniversaryYear);
        System.out.printf("Months Worked:  %d months\n", numMonthsWorked());
        System.out.printf("Vacation hours: %.2f\n", vacationHours());
        System.out.println("------------------------------------------");
        System.out.printf("Gross Pay:     $%7.2f\n", grossPay());
        System.out.printf("Retirement:    $%7.2f\n", retHold());
        System.out.printf("Tax:           $%7.2f\n", tax());
        System.out.println("------------------------");
        System.out.printf("Net Pay:       $%7.2f\n", netPay());
        System.out.println("==========================================");
    }

    // Main method for input and testing
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reading input
        System.out.print("Enter your Fullname: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your Anniversary Month(1-12): ");
        int anniversaryMonth = scanner.nextInt();
        
        System.out.print("Enter your Anniversary Year: ");
        int anniversaryYear = scanner.nextInt();
        
        System.out.print("Enter your hours worked this pay period(0-350): ");
        int hoursWorked = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter your Job Title: ");
        String jobTitle = scanner.nextLine();
        
        System.out.print("Enter your pay rate: ");
        double hourlyPayRate = scanner.nextDouble();
        
        // Creating the PayStub object with input data
        PayStub employee = new PayStub(name, anniversaryMonth, anniversaryYear, hoursWorked, jobTitle, hourlyPayRate);
        
        // Printing the employee information and paystub details
        employee.printInfo();

        scanner.close();
    }


}
