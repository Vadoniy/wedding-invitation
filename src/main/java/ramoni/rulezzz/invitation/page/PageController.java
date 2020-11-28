package ramoni.rulezzz.invitation.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {
    @GetMapping({"/", "/index"})
    public String getIndexPage() {
        return "index";
    }
}
