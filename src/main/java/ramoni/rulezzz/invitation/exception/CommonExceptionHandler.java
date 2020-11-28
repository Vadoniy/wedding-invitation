package ramoni.rulezzz.invitation.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    private static final String INVALID_VALUE = "invalid";

    @Value("${text.exception}")
    private String entschuldigung;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception exception) throws JsonProcessingException {
        String responseMessage = null;

        if (exception instanceof MethodArgumentNotValidException) {
            final var om = new ObjectMapper();
            final var mnve = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors();
            final var errorList = new ErrorList();
            mnve.forEach(fieldError ->
                    errorList.getBadFields().add(Map.of(fieldError.getField(), INVALID_VALUE))
            );

            log.error(exception.getLocalizedMessage());
            responseMessage = om.writeValueAsString(errorList);
        }
        return new ResponseEntity<>(responseMessage != null ? responseMessage : entschuldigung, HttpStatus.BAD_REQUEST);
    }

}
