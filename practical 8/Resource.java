public class Resource {

   
    static class MyResource implements AutoCloseable {

        public MyResource() {
            System.out.println("Resource opened.");
        }

        public void useResource() {
            System.out.println("Using resource...");
            throw new RuntimeException("Something went wrong!");
        }

        @Override
        public void close() {
            System.out.println("Resource closed automatically.");
        }
    }

    public static void main(String[] args) {

        try (MyResource resource = new MyResource()) {

            resource.useResource();

        } catch (RuntimeException exception) {

            System.out.println(
                    "Original error: " + exception.getMessage()
            );
        }

        System.out.println("Program continues normally.");
    }
}