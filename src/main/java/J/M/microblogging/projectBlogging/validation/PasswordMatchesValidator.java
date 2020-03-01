package J.M.microblogging.projectBlogging.validation;

import J.M.microblogging.projectBlogging.dto.LogInUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        LogInUserDto user = (LogInUserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
