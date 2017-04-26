package com.template.springMVCtemplate.controllers;

import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.services.SampleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by ud on 21/4/17.
 *
 * @RestController : First of all, we are using Spring 4′s new @RestController annotation. This annotation eliminates
 * the need of annotating each method with @ResponseBody. Under the hood, @RestController is itself annotated with
 * @ResponseBody, and can be considered as combination of @Controller and @ResponseBody.
 *
 * @RequestBody : If a method parameter is annotated with @RequestBody, Spring will bind the incoming HTTP request
 * body(for the URL mentioned in @RequestMapping for that method) to that parameter. While doing that, Spring will
 * [behind the scenes] use HTTP Message converters to convert the HTTP request body into domain object
 * [deserialize request body to domain object], based on ACCEPT or Content-Type header present in request.
 *
 * @ResponseBody : If a method is annotated with @ResponseBody, Spring will bind the return value to outgoing HTTP
 * response body. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the return
 * value to HTTP response body [serialize the object to response body], based on Content-Type present in request HTTP
 * header. As already mentioned, in Spring 4, you may stop using this annotation.
 *
 * ResponseEntity is a real deal. It represents the entire HTTP response. Good thing about it is that you can control
 * anything that goes into it. You can specify status code, headers, and body. It comes with several constructors to
 * carry the information you want to sent in HTTP Response.
 *
 * @PathVariable This annotation indicates that a method parameter should be bound to a URI template variable [the one in '{}'].
 *
 * MediaType : With @RequestMapping annotation, you can additionally, specify the MediaType to be produced or consumed
 * (using produces or consumes attributes) by that particular controller method, to further narrow down the mapping.
 * produces : Indicates the method will produce a JSON or XML or any other form of data
 * Consume :Indicates that the method will take as input data in the given format.


 */
@RestController
public class RestFullController {

    @Autowired
    private SampleModelService sampleModelService;

    @RequestMapping(value="/samples/",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SampleModel>> listAll(){
        List<SampleModel> models=sampleModelService.findAll();
        if(models.isEmpty()){
            return new ResponseEntity<List<SampleModel>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SampleModel>>(models,HttpStatus.OK);
    }

    @RequestMapping(value="/samples/{id}",method =RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleModel> getModel(@PathVariable("id") int id)
    {
        SampleModel model=sampleModelService.findById(id);
        if(model==null){
            return new ResponseEntity<SampleModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SampleModel>(model,HttpStatus.OK);
    }

    @RequestMapping(value="/samples/",method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody SampleModel model, UriComponentsBuilder builder){
        sampleModelService.saveModel(model);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setLocation(builder.path("/samples/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
    }
}
