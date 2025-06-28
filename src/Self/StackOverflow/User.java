package Self.StackOverflow;

import java.util.Map;

public class User {
    private String username;
    private String emailId;
    private int id;
    int reputation;

    public User(int id, String username, String emailId) {
        this.id = id;
        this.username = username;
        this.emailId = emailId;
        this.reputation = 0;
    }

    public int getId() { return id;
    }

    public void changeReputation(int delta) {
        this.reputation += delta;
    }
}
