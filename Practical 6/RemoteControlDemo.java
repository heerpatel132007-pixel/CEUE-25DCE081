interface Switchable {

    void on();

    void off();

    default void toggle() {
        System.out.println("Toggling device...");
    }
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

// Functional interface
interface SwitchPermission {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControlDemo {

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("---- Devices ----");

        for (Switchable device : devices) {
            device.on();
            device.toggle();
            device.off();
            System.out.println();
        }

        // Anonymous class
        SwitchPermission anonymousPermission = new SwitchPermission() {

            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        // Lambda expression
        SwitchPermission lambdaPermission =
                (device, hour) -> hour >= 6 && hour <= 22;

        int hour = 10;

        System.out.println("Current hour: " + hour);

        System.out.println(
            "Anonymous class permission: "
            + anonymousPermission.maySwitchOn(devices[0], hour)
        );

        System.out.println(
            "Lambda permission: "
            + lambdaPermission.maySwitchOn(devices[1], hour)
        );
    }
}