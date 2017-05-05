package com.template.springMVCtemplate.controllers;

import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.model.User;
import com.template.springMVCtemplate.model.UserProfile;
import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;
import com.template.springMVCtemplate.services.MamytoManyServices;
import com.template.springMVCtemplate.services.SampleModelService;
import com.template.springMVCtemplate.services.UserProfileService;
import com.template.springMVCtemplate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by ud on 21/4/17.
 *
 * @Controller : To initialize a class as Controller so that it can handle various request.
 * @RequestMapping : this annotation is used to call particular methods on particular request.
 *                   defining this in class level ask spring to prefix the value of "Request mapping
 *                   at class level" with that of method level.
 *
 *                   For example :
 *                   If the method is mapped to foo/bar than foo/bar is the address. But if the class is mapped to baz
 *                   and the method is mapped to foo/bar than the method's address is baz/foo/bar
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    private SampleModelService sampleModelService;

    @Autowired
    private MamytoManyServices mamytoManyServices;

    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    MessageSource messageSource;

    /**
     * The following RequestMapping says that if there is a request whose URL ends with "/" or "/list"
     * and the Request is a "GET Request" then below method will handle such request.
     *
     * @param modelMap is a MAP implementation that will hold the data for the view that will rendered on
     *                 completing the request.
     * @return the name(allSample) of the view ,{here(allSample.jsp)}, contained in views folder{webapp/web-inf/views} which will render
     * the data in the model map.
     * <p>
     * addAttribute() : put a value in the map to be rendered in UI.
     * @Todo : create views to handle these response
     */

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listAllSampleModel(ModelMap modelMap) {
        List<SampleModel> sampleModelList = sampleModelService.findAll();
        modelMap.addAttribute("allSampleList", sampleModelList);
        return "allSample";
    }

    /**
     * Below two methods we can see that they have the same request Address but the Request Type is different
     * Get method only returns a form view for the new SampleModel.
     */

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewSampleModel(ModelMap modelMap) {
        SampleModel sampleModel = new SampleModel();
        modelMap.addAttribute("sampleModel", sampleModel);
        return "newSample";
    }

    /**
     * This method handles the POST request when user fills the form for SampleModel and clicks on submit method.
     *
     * @param sampleModel here @Valid ask spring to validate the sample object created at user end.Validation is
     *                    done using the constraint set in the domain class Sample Model
     * @param result      ->  BindingResult contains the outcome of this validation and any error that might have
     *                    occurred during this validation. Notice that BindingResult must come right after the
     *                    validated object else spring won’t be able to validate and an exception been thrown.
     * @param model       ->contains the model data.
     * @return a success view.
     * <p>
     * Note that in case of validation failure, default generalized error messages are shown on screen which
     * may not be desirable. Instead, you can override this behavior providing internationalized messages
     * specific to each field. To do that, we need to configure MessageSource(messages.properties in resource folder from App.Config) in application configuration
     * class and provide properties files containing actual messages which we will do next.
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveSampleModel(@Valid SampleModel sampleModel, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "newSample";
        }

        sampleModelService.saveModel(sampleModel);

        return "success";
    }

    /**
     * @param ssoId : It uses @PathVariable attribute which is used to get the value from the url.
     *              Note: variable name in @pathVariable {ssoid} should be same as that of in method parameters.
     */

    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        SampleModel sampleModel = sampleModelService.findById(Integer.parseInt(ssoId));
        model.addAttribute("sampleModel", sampleModel);
        model.addAttribute("edit", true);
        return "newSample";
    }


    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateSampleModel(@Valid SampleModel sampleModel, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "newSample";
        }
        sampleModelService.update(sampleModel);

        return "success";
    }

    @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer ssoId) {
        sampleModelService.delete(ssoId.toString());
        return "redirect:/list";
    }

    /**
     * @return a model object containing data.
     * @ModelAttribute : initializes a variable ("for example "section") which will contain data and will be rendered
     * in in UI
     */

    @ModelAttribute("techSkills")
    public List<ModelForManyToManyUnidirectional> initializeSections() {

        return mamytoManyServices.findAll();
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Below Code for User Login And Registration
     */

    @RequestMapping(value = {"/userlist"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }

    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
         * and applying it on field [sso] of Model class [User].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }

    @RequestMapping(value = { "/edit-reguser-{ssoId}" }, method = RequestMethod.GET)
    public String editregUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    @RequestMapping(value = { "/edit-reguser-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/


        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }

    @RequestMapping(value = { "/delete-reguser-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
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

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
