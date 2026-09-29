import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank
{
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength
{
    int value();
}


class SignupForm
{
    @NotBlank
    @MaxLength(20)
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    SignupForm(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
}


class FormChecker
{
    public static List<String> validate(Object object)
    {
        List<String> errors = new ArrayList<>();

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            field.setAccessible(true);

            try
            {
                Object value = field.get(object);

         
                if (field.isAnnotationPresent(NotBlank.class))
                {
                    if (value == null || value.toString().trim().isEmpty())
                    {
                        errors.add(field.getName() + " must not be blank");
                    }
                }

               
                if (field.isAnnotationPresent(MaxLength.class))
                {
                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int maximumLength = annotation.value();

                    if (value != null &&
                        value.toString().length() > maximumLength)
                    {
                        errors.add(field.getName()
                                + " is too long. Maximum length is "
                                + maximumLength);
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        return errors;
    }
}


public class FormValidator
{
    public static void main(String[] args)
    {
        SignupForm form =
                new SignupForm("", "verylongemailaddress123456789@example.com");

        List<String> errors = FormChecker.validate(form);

        System.out.println("Validation Errors:");

        for (String error : errors)
        {
            System.out.println(error);
        }
    }
}