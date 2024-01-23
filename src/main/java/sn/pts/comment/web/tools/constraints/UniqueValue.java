package sn.pts.comment.web.tools.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import sn.pts.comment.web.tools.validators.UniqueFieldValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFieldValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Field value must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> entityClass();

    boolean ignoreCase() default false;

    /**
     * The ID of the entity being updated. This attribute is optional and should only be set when updating an existing entity.
     */
    long idValue() default -1L;
}