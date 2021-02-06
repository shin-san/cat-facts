package au.com.elabor8.catfacts.transformer;

import au.com.elabor8.catfacts.model.*;
import au.com.elabor8.catfacts.service.WebClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.util.Arrays;
import java.util.List;

@Component
public class FactsToUserDetailsTransformer {

    private WebClientService webClientService;

    @Autowired
    public FactsToUserDetailsTransformer (WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    public UserDetails getUserDetails() throws Exception{
        UserDetails userDetails = new UserDetails();

        ObjectMapper mapper = new ObjectMapper();

        List<Fact> factsList = Arrays.asList(mapper.readValue(webClientService.getFacts().strip(), Fact[].class));

        factsList.forEach(fact -> {
            FactDetails factDetails = null;
            try {
                factDetails = webClientService.getFactDetails(fact.get_id());
            } catch (SSLException e) {
                e.printStackTrace();
            }
            userDetails.setName(new Name(factDetails.getUser().getName().getFirst(),
                    factDetails.getUser().getName().getLast()));
        });

        return userDetails;
    }
}
