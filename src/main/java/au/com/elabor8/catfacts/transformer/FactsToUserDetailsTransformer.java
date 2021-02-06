package au.com.elabor8.catfacts.transformer;

import au.com.elabor8.catfacts.model.*;
import au.com.elabor8.catfacts.service.WebClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FactsToUserDetailsTransformer {

    private WebClientService webClientService;

    @Autowired
    public FactsToUserDetailsTransformer (WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    public List<UserDetails> getUserDetails() throws Exception {

        // Had to use object mapper to assist with deserializing JSON array
        ObjectMapper mapper = new ObjectMapper();

        // Turn list of facts into POJO
        List<Fact> factsList = Arrays.asList(
                mapper.readValue(webClientService.getFacts().strip(), Fact[].class));

        // Create a Hash Map to store user details with userID as key
        HashMap<String, UserDetails> userDetailsHashMap = new HashMap<>();

        // Iterate through the list
        factsList.forEach(fact -> {
            FactDetails factDetails = null;
            try {
                factDetails = webClientService.getFactDetails(fact.get_id());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // prepare the variables for a cleaner approach
            String userId = fact.getUser();
            assert factDetails != null;
            int userVote = factDetails.getStatus().getSentCount();
            String firstName = factDetails.getUser().getName().getFirst();
            String lastName = factDetails.getUser().getName().getLast();

            // Add user details under its user ID if it does not exist
            if (Objects.isNull(userDetailsHashMap.get(userId))) {
                userDetailsHashMap.put(userId, new UserDetails(
                        new Name(firstName, lastName),userVote));
            } else {
                // add the status votes into the stored votes
                int storedUserVote = userDetailsHashMap.get(userId).getVotes();
                userDetailsHashMap.get(userId).setVotes(storedUserVote+userVote);
            }
        });

        return new ArrayList<>(userDetailsHashMap.values());
    }
}
