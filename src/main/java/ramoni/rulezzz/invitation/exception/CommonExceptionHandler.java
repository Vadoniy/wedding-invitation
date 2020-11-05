package ramoni.rulezzz.invitation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @Value("${text.exception}")
    private String entschuldigung;

    @Value("${text.exception-field}")
    private String exceptionField;

    @Value("${text.phone-field}")
    private String phoneField;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception exception) {
        String responseMessage = null;
        if (exception instanceof MethodArgumentNotValidException) {
            final var mnve = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldError();
            final var fieldName = Optional.ofNullable(mnve).map(FieldError::getField).orElse(null);
            if ("contactPhone".equals(fieldName)) {
                responseMessage = phoneField;
            } else if ("name".equals(fieldName)) {
                responseMessage = String.format(exceptionField, "имя");
            }
            log.error(exception.getLocalizedMessage());
        }
        return new ResponseEntity<>(responseMessage != null ? responseMessage : entschuldigung, HttpStatus.NOT_FOUND);
    }
}
