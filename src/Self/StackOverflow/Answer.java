package Self.StackOverflow;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    private boolean isAccepted;
    int id;
    String content;
    User author;
    int questionId;
    private List<Vote> voteList = new ArrayList<>();
    int votes;

    public Answer(int id, String content, User user, Question question) {
        this.id = id;
        this.content = content;
        this.author = user;
        this.questionId = question.getId();
        this.isAccepted = false;
    }

    public void upvote(User user){
        this.voteList.add(new Vote(user.getId(), VoteType.UPVOTE));
        this.votes++;
        this.author.changeReputation(5);
    }

    public void downvote(User user) {
        this.voteList.add(new Vote(user.getId(), VoteType.DOWNVOTE));
        this.votes--;
        this.author.changeReputation(-5);
    }

    public void markAccepted() {
        this.isAccepted = true;
    }
}
