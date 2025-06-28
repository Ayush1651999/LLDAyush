package Self.StackOverflow;

public class StackOverflowDemo {

    public static void main(String[] args){
        StackOverflow system = new StackOverflow();

        User ayush = system.createUser("ayush", "ayush@ayush");
        User sakshi = system.createUser("sakshi", "sakshi@sakshi");
        User nayan = system.createUser("nayan", "nayan@nayan");

        Question question = system.askQuestion(ayush, "some title", "some description");

        Answer a1 = system.answerQuestion(sakshi, question, "Some answer");
        Answer a2 = system.answerQuestion(nayan, question, "Some other answer");

        a1.upvote(ayush);
        a1.upvote(nayan);
        a2.upvote(sakshi);

        a2.downvote(ayush);

        a1.markAccepted();


    }
}

