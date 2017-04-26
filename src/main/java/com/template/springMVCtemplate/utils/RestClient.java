/*

package com.template.springMVCtemplate.utils;

import com.template.springMVCtemplate.model.SampleModel;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;


*/
/**
 * Created by ud on 26/4/17.
 *//*


public class RestClient {
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringMVCTemplate/";

*/
/* GET *//*


    @SuppressWarnings("unchecked")
    private void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/samples/", List.class);

        //Get a particular object.
        SampleModel model=restTemplate.getForObject(REST_SERVICE_URI+"/samples/"+"1",SampleModel.class);

        //Add a sample model
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/samples/", model, SampleModel.class);
        //uri will contain the path for the new object

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }

    public static void main(String args[]){
        new RestClient().listAllUsers();
    }



}

*/
