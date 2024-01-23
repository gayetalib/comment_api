package sn.pts.comment.web.tools.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sn.pts.comment.web.tools.constraints.PatternConstraint;
import sn.pts.comment.web.tools.constraints.PatternType;

public class PatternValidator implements ConstraintValidator<PatternConstraint, String> {
    private PatternType type;

    @Override
    public void initialize(PatternConstraint constraintAnnotation) {
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.matches(type.getPattern());
    }
}