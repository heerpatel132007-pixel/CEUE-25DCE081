import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import exception.MaxLength;

public class MiniBankValidator
{
    public static String[] validate(Object object)
    {
        List<String> errors =
                new ArrayList<>();

        Field[] fields =
                object.getClass()
                        .getDeclaredFields();


        for (Field field : fields)
        {
            field.setAccessible(true);

            try
            {
                // Check Positive
                if (field.isAnnotationPresent(
                        Positive.class))
                {
                    Positive positive =
                            field.getAnnotation(
                                    Positive.class);

                    Object value =
                            field.get(object);


                    if (value instanceof Number)
                    {
                        double number =
                                ((Number) value)
                                .doubleValue();


                        if (number <= 0)
                        {
                            errors.add(
                                    field.getName()
                                    + " "
                                    + positive.message());
                        }
                    }
                }


                // Check MaxLength
                if (field.isAnnotationPresent(
                        MaxLength.class))
                {
                    MaxLength maxLength =
                            field.getAnnotation(
                                    MaxLength.class);

                    Object value =
                            field.get(object);


                    if (value != null)
                    {
                        String text =
                                value.toString();


                        if (text.length() >
                                maxLength.value())
                        {
                            errors.add(
                                    field.getName()
                                    + " exceeds maximum length "
                                    + maxLength.value());
                        }
                    }
                }
            }
            catch (Exception exception)
            {
                System.out.println(
                        exception);
            }
        }


        return errors.toArray(
                new String[0]);
    }
}