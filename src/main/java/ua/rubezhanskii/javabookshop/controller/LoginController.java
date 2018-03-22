package ua.rubezhanskii.javabookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CustomerJdbcTemplate;
import ua.rubezhanskii.javabookshop.herokuspecific.HerokuHelper;
import ua.rubezhanskii.javabookshop.model.Customer;
import ua.rubezhanskii.javabookshop.security.UserService;
import ua.rubezhanskii.javabookshop.security.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value="")
public class LoginController {





    @Autowired
    UserService userService;
    @Autowired
    private CustomerJdbcTemplate customerJdbcTemplate;

    @Autowired
    MessageSource messageSource;
    @Autowired
    private HerokuHelper herokuHelper;







    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        Users users = new Users();
        model.addAttribute("user", users);
        return "register";
    }


 /*   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@Valid User user,BindingResult bindingResult, ModelMap model) {


        if (bindingResult.hasErrors()) {
            return "login";
        }

        model.addAttribute("user", user);
        //userService.findByEmail(user.getEmail()).getPassword().equals(user.getPassword());
       // user.getEmail().equals(getPrincipal());
       return "login";
    }
*/
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        Users users = new Users();
        model.addAttribute("user", users);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "register";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String saveUser(@Valid Users users, BindingResult result,
                           ModelMap model) {

        model.addAttribute("user", users);
        if (result.hasErrors()) {
            return "registration";
        }


      /*  if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/

        userService.save(users);
        Customer customer=new Customer();
        customer.setLogin(users.getUsername());
       // customerJdbcTemplate.save(customer);
       herokuHelper.save(customer);
       model.addAttribute("userName",  users.getUsername());
      //  model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "register";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
          //  persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/welcome/home";
    }


  /* private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }*/

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private boolean isRememberMeAuthenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * save targetURL in session
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.setAttribute("targetUrl", "/welcome/home");
        }
    }

    /**
     * get targetURL from session
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if(session!=null){
            targetUrl = session.getAttribute("targetUrl")==null?"":session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }

}
