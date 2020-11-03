package ramoni.rulezzz.invitation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @Value("${text.exception}")
    private String entschuldigung;

    @ExceptionHandler(Exception.class)
    public String handleExceptions(Exception exception) {
        log.error(exception.getLocalizedMessage());
        return entschuldigung;
    }
}
