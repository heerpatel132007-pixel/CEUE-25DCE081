abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }
}

class Triangle extends Shape {
    double base, height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    double area() {
        return 0.5 * base * height;
    }
}

public class Main {
    public static void main(String[] args) {

        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(8, 5)
        };

        double total = 0;
        double largest = 0;

        // One loop handles every shape
        for (Shape shape : shapes) {

            double currentArea = shape.area();

            System.out.printf("Area = %.2f%n", currentArea);

            total = total + currentArea;

            if (currentArea > largest) {
                largest = currentArea;
            }
        }

        System.out.printf("Total Area = %.2f%n", total);
        System.out.printf("Largest Area = %.2f%n", largest);
    }
}