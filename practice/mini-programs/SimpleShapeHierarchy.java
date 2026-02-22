abstract class Shape {
    // Abstract methods
    abstract double area();
    abstract double perimeter();

    // Concrete method
    void display() {
        System.out.println("This is a shape.");
    }
}

// Circle class
class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Rectangle class
class Rectangle extends Shape {
    private double length;
    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }

    @Override
    double perimeter() {
        return 2 * (length + width);
    }
}

// Triangle class
class Triangle extends Shape {
    private double a, b, c;

    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double area() {
        double s = (a + b + c) / 2; // semi-perimeter
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    double perimeter() {
        return a + b + c;
    }
}

// Main class
public class SimpleShapeHierarchy {
    public static void main(String[] args) {

        Shape s1 = new Circle(5);
        Shape s2 = new Rectangle(4, 6);
        Shape s3 = new Triangle(3, 4, 5);

        System.out.println("Circle Area: " + s1.area());
        System.out.println("Circle Perimeter: " + s1.perimeter());

        System.out.println();

        System.out.println("Rectangle Area: " + s2.area());
        System.out.println("Rectangle Perimeter: " + s2.perimeter());

        System.out.println();

        System.out.println("Triangle Area: " + s3.area());
        System.out.println("Triangle Perimeter: " + s3.perimeter());
    }
}