package Copy.StackOverflow;

import java.util.*;

enum VoteType {
    UPVOTE, DOWNVOTE
}

class User {
    private final int id;
    private final String name;
    private int reputation = 0;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getReputation() { return reputation; }
    public void addReputation(int delta) { reputation += delta; }
}

class Tag {
    private final String name;

    public Tag(String name) { this.name = name; }
    public String getName() { return name; }
}

class Vote {
    private final User voter;
    private final VoteType type;

    public Vote(User voter, VoteType type) {
        this.voter = voter;
        this.type = type;
    }

    public VoteType getType() { return type; }
    public User getVoter() { return voter; }
}

class Answer {
    private static int idCounter = 0;
    private final int id;
    private final String content;
    private final User author;
    private final List<Vote> votes = new ArrayList<>();
    private boolean isAccepted = false;

    public Answer(String content, User author) {
        this.id = idCounter++;
        this.content = content;
        this.author = author;
    }

    public void upvote(User user) {
        votes.add(new Vote(user, VoteType.UPVOTE));
        author.addReputation(10);
    }

    public void downvote(User user) {
        votes.add(new Vote(user, VoteType.DOWNVOTE));
        author.addReputation(-2);
    }

    public void markAccepted() { this.isAccepted = true; }
    public boolean isAccepted() { return isAccepted; }
    public String getContent() { return content; }
    public User getAuthor() { return author; }
}

class Question {
    private static int idCounter = 0;
    private final int id;
    private final String title;
    private final String body;
    private final User author;
    private final List<Tag> tags;
    private final List<Answer> answers = new ArrayList<>();
    private final List<Vote> votes = new ArrayList<>();

    public Question(String title, String body, User author, List<Tag> tags) {
        this.id = idCounter++;
        this.title = title;
        this.body = body;
        this.author = author;
        this.tags = tags;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void upvote(User user) {
        votes.add(new Vote(user, VoteType.UPVOTE));
        author.addReputation(5);
    }

    public void downvote(User user) {
        votes.add(new Vote(user, VoteType.DOWNVOTE));
        author.addReputation(-2);
    }

    public List<Answer> getAnswers() { return answers; }
    public String getTitle() { return title; }
    public User getAuthor() { return author; }
}

class StackOverflowService {
    private final Map<Integer, User> users = new HashMap<>();
    private final List<Question> questions = new ArrayList<>();

    public User registerUser(String name) {
        User user = new User(users.size(), name);
        users.put(user.getId(), user);
        return user;
    }

    public Question postQuestion(String title, String body, User author, List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagNames) {
            tags.add(new Tag(tagName));
        }
        Question question = new Question(title, body, author, tags);
        questions.add(question);
        return question;
    }

    public Answer postAnswer(Question question, String content, User author) {
        Answer answer = new Answer(content, author);
        question.addAnswer(answer);
        return answer;
    }

    public List<Question> searchByTitle(String keyword) {
        List<Question> result = new ArrayList<>();
        for (Question q : questions) {
            if (q.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(q);
            }
        }
        return result;
    }
}

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverflowService service = new StackOverflowService();

        User ayush = service.registerUser("Ayush");
        User priya = service.registerUser("Priya");
        User ankit = service.registerUser("Ankit");

        Question q1 = service.postQuestion("How to reverse a string in Java?", "I want to understand different ways to reverse a string.", ayush, Arrays.asList("java", "string"));

        Answer a1 = service.postAnswer(q1, "You can use StringBuilder's reverse() method.", priya);
        Answer a2 = service.postAnswer(q1, "Use a char array and loop backwards.", ankit);

        a1.upvote(ayush);  // Ayush likes Priya's answer
        a2.upvote(ayush);  // Ayush also likes Ankit's answer
        a1.upvote(ankit);  // Ankit upvotes Priya

        q1.upvote(priya);  // Priya upvotes the question

        a1.markAccepted();  // Ayush accepts Priya's answer

        System.out.println("Answers to question: " + q1.getTitle());
        for (Answer ans : q1.getAnswers()) {
            System.out.println("- " + ans.getContent() + " by " + ans.getAuthor().getName() + (ans.isAccepted() ? " [Accepted]" : ""));
        }

        System.out.println("Priya's reputation: " + priya.getReputation());
        System.out.println("Ankit's reputation: " + ankit.getReputation());
        System.out.println("Ayush's reputation: " + ayush.getReputation());
    }
}
