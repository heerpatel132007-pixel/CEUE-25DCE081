package exception;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {

    @NotBlank
    @MaxLength(20)
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    SignupForm(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class FormChecker {

    static List<String> validate(Object object) {

        List<String> errors = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null ||
                            value.toString().trim().isEmpty()) {

                        errors.add(field.getName()
                                + " must not be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength max =
                            field.getAnnotation(MaxLength.class);

                    if (value != null &&
                            value.toString().length() > max.value()) {

                        errors.add(field.getName()
                                + " is too long");
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return errors;
    }
}