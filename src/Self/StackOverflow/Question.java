package Self.StackOverflow;

public class Question {
    int id;
    String title;
    String description;
    int userId;

    public Question(int id, String title, String description, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = user.getId();
    }

    public int getId() { return id; }
}
