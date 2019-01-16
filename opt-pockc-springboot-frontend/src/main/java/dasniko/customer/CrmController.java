package dasniko.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CrmController {

	
	@Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private KeycloakSecurityContext securityContext;
    @Autowired
    private AccessToken accessToken;
    protected Logger log = LoggerFactory.getLogger(CrmController.class.getSimpleName());
    
    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping("/customers")
    public String customers(Model model, Principal principal) {
        log.info("AccessToken: " + securityContext.getTokenString());
        log.info("Principal: {}", principal.toString());
        log.info("User: {} / {}", accessToken.getPreferredUsername(), accessToken.getName());
        log.info("Principal: {}", principal.getName());
        model.addAttribute(customerRepository.findAll());
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String customer(@PathVariable("id") Long id, Model model) {
        model.addAttribute(customerRepository.findOne(id));
        return "customer";
    }

}
