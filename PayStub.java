import java.time.Month;

/**
 * @author huan zhou
 * @version 02/16/2025
 */

public class PayStub {
    /*
    Employee name, 
    Month and year the employee was hired. 
    Hours worked in the pay period (between 0 and 350), 
    Job title, and 
    Hourly pay rate. 
     */
    String name;
    int month;
    int year;
    int hoursWorked;
    String title;
    double hourlyRate;

    public PayStub(){

    };

    public PayStub( String name, int anniversaryMonth, int anniversaryYear, int hours, String title, double payRate ){
        month = anniversaryMonth;
        this.name = name;
        year = anniversaryYear;
        hoursWorked = hours;
        this.title = title;
        hourlyRate = payRate;
    }



    
}
