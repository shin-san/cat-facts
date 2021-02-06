package au.com.elabor8.catfacts.service;

import au.com.elabor8.catfacts.config.WebClientConfig;
import au.com.elabor8.catfacts.model.FactDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class WebClientService {

    @Autowired
    WebClientConfig webClientConfig;

    public String getFacts() throws Exception {
        return webClientConfig.getWebClient().get()
                .uri("https://cat-fact.herokuapp.com/facts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class).block();
    }

    public FactDetails getFactDetails(String id) throws Exception {
        return webClientConfig.getWebClient().get()
                .uri("https://cat-fact.herokuapp.com/facts/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(FactDetails.class).block();
    }
}
