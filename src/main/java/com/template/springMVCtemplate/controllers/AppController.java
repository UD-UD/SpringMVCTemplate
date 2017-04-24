package com.template.springMVCtemplate.controllers;

import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.services.SampleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
public class AppController {

    @Autowired
    private SampleModelService sampleModelService;

    /**
     * The following RequestMapping says that if there is a request whose URL ends with "/" or "/list"
     * and the Request is a "GET Request" then below method will handle such request.
     * @param modelMap is a MAP implementation that will hold the data for the view that will rendered on
     *                  completing the request.
     * @return the name(allSample) of the view ,{here(allSample.jsp)}, contained in views folder{webapp/web-inf/views} which will render
     * the data in the model map.
     *
     * addAttribute() : put a value in the map to be rendered in UI.
     *
     * @Todo : create views to handle these response
     */

    @RequestMapping(value = {"/","/list"},method = RequestMethod.GET)
    public String listAllSampleModel(ModelMap modelMap){
        List<SampleModel> sampleModelList= sampleModelService.findAll();
        modelMap.addAttribute("allSampleList",sampleModelList);
        return "allSample";
    }

    /**
     *Below two methods we can see that they have the same request Address but the Request Type is different
     *Get method only returns a form view for the new SampleModel.
     */

    @RequestMapping(value="/new",method = RequestMethod.GET)
    public String createNewSampleModel(ModelMap modelMap){
        SampleModel sampleModel=new SampleModel();
        modelMap.addAttribute("sampleModel",sampleModel);
        return "newSample";
    }

    /**
     *This method handles the POST request when user fills the form for SampleModel and clicks on submit method.
     * @param sampleModel here @Valid ask spring to validate the sample object created at user end.Validation is
     *                    done using the constraint set in the domain class Sample Model
     * @param result ->  BindingResult contains the outcome of this validation and any error that might have
     *                   occurred during this validation. Notice that BindingResult must come right after the
     *                   validated object else spring won’t be able to validate and an exception been thrown.
     * @param model ->contains the model data.
     * @return  a success view.
     *
     * Note that in case of validation failure, default generalized error messages are shown on screen which
     * may not be desirable. Instead, you can override this behavior providing internationalized messages
     * specific to each field. To do that, we need to configure MessageSource(messages.properties in resource folder from App.Config) in application configuration
     * class and provide properties files containing actual messages which we will do next.
     */
    @RequestMapping(value="/new",method = RequestMethod.POST)
    public String saveSampleModel(@Valid SampleModel sampleModel, BindingResult result, ModelMap model){

        if(result.hasErrors()) {
            return "newSample";
        }

        sampleModelService.saveModel(sampleModel);

        return "success";
    }

    /**
     *
     * @param ssoId  : It uses @PathVariable attribute which is used to get the value from the url.
     *               Note: variable name in @pathVariable {ssoid} should be same as that of in method parameters.
     */

    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        return "";
    }

    /**
     * @ModelAttribute : initializes a variable ("for example "section") which will contain data and will be rendered
     * in in UI
     * @return a model object containing data.
     */

    @ModelAttribute("sections")
    public List<String> initializeSections() {

        List<String> sections = new ArrayList<String>();
        sections.add("Graduate");
        sections.add("Post Graduate");
        sections.add("Research");
        return sections;
    }


}
