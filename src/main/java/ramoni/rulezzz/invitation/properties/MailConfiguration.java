package ramoni.rulezzz.invitation.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */
@Getter
@Setter
@Component
@EnableSwagger2
@ConfigurationProperties("mail")
public class MailConfiguration {

    private String from;

    private String toVadony;

    private String toNatashonka;

    private String subject;

}
