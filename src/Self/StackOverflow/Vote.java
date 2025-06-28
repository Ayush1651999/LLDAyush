package Self.StackOverflow;

public class Vote {
    private final VoteType voteType;
    private final int userId;

    public Vote(int id, VoteType voteType) {
        this.userId = id;
        this.voteType = voteType;
    }
}
