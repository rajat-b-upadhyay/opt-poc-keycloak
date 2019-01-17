package dasniko.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CrmController {

	
	@Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private KeycloakSecurityContext keycloakSecurityContext;
    
    /*@Autowired
    private AccessToken accessToken;*/
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;
    
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
    public String customers(Model model, @AuthenticationPrincipal  Principal currentUser) {
    	log.info("keycloakSecurityContext: " + keycloakSecurityContext);
        log.info("Principal: {}", currentUser);
        
        if(currentUser!=null)
        {
	        KeycloakAuthenticationToken keycloakUser = (KeycloakAuthenticationToken) currentUser;
	        log.info("Current user roles: %s%n", keycloakUser.getAuthorities());
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {} "+authentication);
		
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		log.info("RequestAttributes: {} "+attributes);
		String user = attributes.getRequest().getRemoteUser();
		log.info("RemoteUser: {} "+user);
		Principal principal = attributes.getRequest().getUserPrincipal();
		log.info("UserPrincipal : {}"+principal);
        
		
        model.addAttribute(customerRepository.findAll());
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String customer(@PathVariable("id") Long id, Model model) {
        model.addAttribute(customerRepository.findOne(id));
        return "customer";
    }
    
    @GetMapping("/stocks")
    public String stocks(Model model) {
        
        //Call the Stocks Rest Services start
        
        Stock[] stocks = keycloakRestTemplate.getForObject("http://127.0.0.1:20002/stocks", Stock[].class);
        List<Stock> stockList = Arrays.asList(stocks);
        log.info(stockList.toString());
        
        //Call the Stocks Rest Services ends
        
        
        model.addAttribute(stockList);
        return "stocks";
    }

}
