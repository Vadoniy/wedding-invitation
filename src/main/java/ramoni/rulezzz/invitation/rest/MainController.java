package ramoni.rulezzz.invitation.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ramoni.rulezzz.invitation.dto.GuestInfoDto;
import ramoni.rulezzz.invitation.service.GuestInfoService;

import javax.validation.Valid;

/**
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */
@RestController
@RequiredArgsConstructor
public class MainController {

    private final GuestInfoService guestInfoService;

    @PostMapping("/send")
    public String inform(@Valid @RequestBody GuestInfoDto guestInfo) {
        return guestInfoService.sendInfo(guestInfo);
    }

    @GetMapping("/getList16012021")
    public String getList() {
        return guestInfoService.getList();
    }

    @GetMapping("/goodByeGuest")
    public void goodByeGuest(Long id) {
        guestInfoService.goodByeGuest(id);
    }
}
