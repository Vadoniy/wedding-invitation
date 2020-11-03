package ramoni.rulezzz.invitation.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("mail")
public class MailConfiguration {

    private String from;

    private String toVadony;

    private String toNatashonka;

    private String subject;

}
