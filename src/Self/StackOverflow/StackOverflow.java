package Self.StackOverflow;

import java.util.*;

public class StackOverflow {

    private Map<Integer, User> userDB = new HashMap<>();
    private Map<Integer, Question> questionDB = new HashMap<>();
    private Map<Integer, Answer> answerDB = new HashMap<>();

    public User createUser(String username, String emailId) {
        int id = userDB.size();
        User user = new User(id, username, emailId);
        userDB.put(id, user);

        return user;
    }

    public Question askQuestion(User user, String title, String description) {
        int id = questionDB.size();
        Question question = new Question(id, title, description, user);
        questionDB.put(id, question);
        return question;
    }

    public Answer answerQuestion(User user, Question question, String content) {
        int id = answerDB.size();
        Answer answer = new Answer(id, content, user, question);
        answerDB.put(id, answer);
        return answer;
    }
}
