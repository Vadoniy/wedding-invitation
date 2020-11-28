package ramoni.rulezzz.invitation.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс DTO для предоставления информации на FE в удобном для отображения ошибок виде.
 *
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */

@Getter
@Setter
public class ErrorList {

    private List<Map<String, String>> badFields = new ArrayList<>(2);

}
