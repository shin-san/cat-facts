package au.com.elabor8.catfacts.controller;

import au.com.elabor8.catfacts.model.UserDetails;
import au.com.elabor8.catfacts.transformer.FactsToUserDetailsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CatFactsController {

    private FactsToUserDetailsTransformer factsToUserDetailsTransformer;

    @Autowired
    public CatFactsController(FactsToUserDetailsTransformer factsToUserDetailsTransformer) {
        this.factsToUserDetailsTransformer = factsToUserDetailsTransformer;
    }

    @GetMapping("/getUsers")
    private UserDetails getUsers() throws Exception {
        return factsToUserDetailsTransformer.getUserDetails();
    }
}
