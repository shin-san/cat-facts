package au.com.elabor8.catfacts.model;

public class UserDetails {

    public Name name;
    public int votes;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
