import java.lang.annotation.*;
import java.lang.reflect.Method;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run
{
}

class MyTests
{
    @Run
    public void testAddition()
    {
        System.out.println("testAddition is running");
    }

    @Run
    public void testSubtraction()
    {
        System.out.println("testSubtraction is running");
    }

    public void normalMethod()
    {
        System.out.println("Normal method is running");
    }

    @Run
    public void testMultiplication()
    {
        System.out.println("testMultiplication is running");
    }
}


public class MiniTestRunner
{
    public static void main(String[] args)
    {
        MyTests tests = new MyTests();

        int count = 0;

        Method[] methods =
                tests.getClass().getDeclaredMethods();

        for (Method method : methods)
        {
            if (method.isAnnotationPresent(Run.class))
            {
                try
                {
                    method.invoke(tests);
                    count++;
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }

        System.out.println("Number of methods executed: " + count);
    }
}