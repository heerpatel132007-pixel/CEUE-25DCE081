@FunctionalInterface
interface Notifier {
    void send(String message);
}

// Marker interface
interface Urgent {
}

public class NotificationDemo {

    public static void main(String[] args) {

        // Email sender
        Notifier emailSender = message ->
                System.out.println("Email: " + message);

        // SMS sender
        Notifier smsSender = message ->
                System.out.println("SMS: " + message);

        // Store senders in an array
        Notifier[] senders = {
            emailSender,
            smsSender
        };

        String message = "Your account has been updated.";

        System.out.println("---- Broadcasting Message ----");

        for (Notifier sender : senders) {
            sender.send(message);
        }

        // Urgent email sender
        class UrgentEmailSender implements Notifier, Urgent {

            public void send(String message) {
                System.out.println("Urgent Email: " + message);
            }
        }

        // Normal SMS sender
        class NormalSmsSender implements Notifier {

            public void send(String message) {
                System.out.println("Normal SMS: " + message);
            }
        }

        Notifier[] urgentSenders = {
            new UrgentEmailSender(),
            new NormalSmsSender()
        };

        System.out.println();
        System.out.println("---- Urgent Notification ----");

        for (Notifier sender : urgentSenders) {

            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}