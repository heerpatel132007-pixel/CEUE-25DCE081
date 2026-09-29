import java.lang.annotation.*;
import java.lang.reflect.Field;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column
{
    String name();
}


class Student
{
    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "age")
    String age;

    public void display()
    {
        System.out.println("Name  : " + name);
        System.out.println("Email : " + email);
        System.out.println("Age   : " + age);
    }
}


public class ColumnMapper
{
    public static void main(String[] args)
    {
        String[] headers =
        {
            "name",
            "email",
            "age"
        };

        String[] data =
        {
            "Rahul",
            "rahul@gmail.com",
            "20"
        };

        Student student = new Student();

        Field[] fields =
                student.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(Column.class))
            {
                Column column =
                        field.getAnnotation(Column.class);

                String columnName = column.name();

                for (int i = 0; i < headers.length; i++)
                {
                    if (headers[i].equals(columnName))
                    {
                        try
                        {
                            field.setAccessible(true);
                            field.set(student, data[i]);
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }
                    }
                }
            }
        }

        System.out.println("Student Details:");
        student.display();
    }
}