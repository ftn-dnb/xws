package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.User;

public class ReviewerDTO {

    private String id;
    private String name;
    private int score;

    public ReviewerDTO() {
    }

    public ReviewerDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getFirstName() + " " + user.getLastName();
    }

    public ReviewerDTO(User user, int score) {
        this(user);
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
