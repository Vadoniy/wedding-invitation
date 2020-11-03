package ramoni.rulezzz.invitation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ramoni.rulezzz.invitation.db.model.GuestInfo;
import ramoni.rulezzz.invitation.db.repository.GuestInfoRepository;
import ramoni.rulezzz.invitation.dto.GuestInfoDto;
import ramoni.rulezzz.invitation.properties.MailConfiguration;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestInfoService {

    private final GuestInfoRepository guestInfoRepository;

    private final JavaMailSender javaMailSender;

    private final MailConfiguration mailConfiguration;

    @Value("${text.invitation.new}")
    private String newGuest;

    @Value("${text.invitation.update}")
    private String updatedGuest;

    @Transactional
    public String sendInfo(GuestInfoDto guestInfoDto) {
        return guestInfoRepository.findByContactPhone(guestInfoDto.getContactPhone())
                .map(info -> update(guestInfoDto, info))
                .orElseGet(() -> saveNew(guestInfoDto));
    }

    public String getList() {
        final var objectMapper = new ObjectMapper();
        final var guests = guestInfoRepository.findAll();
        try {
            return "Всего сейчас в списке " + guests.size() + " гостей:\n" + objectMapper.writeValueAsString(guests);
        } catch (Exception e) {
            return "Не, не работает, не будет списка";
        }
    }

    private String update(GuestInfoDto guestInfoDto, GuestInfo guestInfo) {
        final var updatedGuestInfo = GuestInfo.updateEntity(guestInfoDto, guestInfo);
        guestInfoRepository.save(updatedGuestInfo);
        sendMail(guestInfoDto);
        return String.format(updatedGuest, guestInfoDto.toString());
    }

    private String saveNew(GuestInfoDto guestInfoDto) {
        guestInfoRepository.save(GuestInfo.convertToEntity(guestInfoDto));
        sendMail(guestInfoDto);
        return String.format(newGuest, guestInfoDto.toString());
    }

    private void sendMail(GuestInfoDto guestInfoDto) {
        try {
            final var message = javaMailSender.createMimeMessage();
            final var helper = new MimeMessageHelper(message, false);
            helper.setSubject(mailConfiguration.getSubject());
            helper.setFrom(mailConfiguration.getFrom());
            helper.addTo(mailConfiguration.getToVadony());
            helper.addTo(mailConfiguration.getToNatashonka());
            helper.setText(guestInfoDto.forMail());
//            javaMailSender.send(message);
            log.info("Email was sent");
        } catch (MessagingException e) {
            log.error("Не удалось отправить сообщение.");
        }
    }
}
