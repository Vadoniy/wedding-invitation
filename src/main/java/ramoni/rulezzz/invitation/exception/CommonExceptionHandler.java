package ramoni.rulezzz.invitation.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    private static final String INVALID_VALUE = "invalid";

    private static final String INVALID_FIELD_PATTERN = "{\"%s\":\"%s\"}";

    @Value("${text.exception}")
    private String entschuldigung;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception exception) throws JsonProcessingException {
        String responseMessage = null;

        if (exception instanceof MethodArgumentNotValidException) {
            final var stringBuilder = new StringBuilder("{\"badField\":[");
            final var mnve = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors();
            mnve.forEach(fieldError -> addErrorFieldString(stringBuilder, fieldError.getField()));
            stringBuilder.append("]");
            log.error(exception.getLocalizedMessage());
            responseMessage = stringBuilder.toString();
        }
        return new ResponseEntity<>(responseMessage != null ? responseMessage : entschuldigung, HttpStatus.BAD_REQUEST);
    }

    private StringBuilder addErrorFieldString(StringBuilder stringBuilder, String fieldName) {
        if (stringBuilder.toString().endsWith("}")) {
            stringBuilder.append(",");
        }
        stringBuilder.append(String.format(INVALID_FIELD_PATTERN, fieldName, INVALID_VALUE));
        return stringBuilder;
    }
}
