abstract class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double fixedSalary;

    FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    int hours;
    double rate;

    PartTime(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

public class PayrollDemo {
    public static void main(String[] args) {

        Employee[] employees = {
            new FullTime("Rahul", 101, 50000),
            new PartTime("Priya", 102, 80, 300),
            new Intern("Amit", 103, 12000)
        };

        double totalSalary = 0;

        for (Employee employee : employees) {

            double salary = employee.monthlySalary();

            System.out.println(
                employee.name + " (ID: " + employee.id +
                ") Salary = " + salary
            );

            if (employee instanceof Intern) {
                System.out.println("Note: This employee is an Intern.");
            }

            totalSalary = totalSalary + salary;
        }

        System.out.println("Total Payroll = " + totalSalary);
    }
}