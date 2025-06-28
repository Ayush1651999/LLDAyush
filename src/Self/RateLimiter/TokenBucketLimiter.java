package Self.RateLimiter;

import java.util.Scanner;

public class TokenBucketLimiter {
    int tokens;
    private final int timeLimit = 20;
    private final int tokenIncrement = 3;
    private final int tokenDecrement = -5;

    private synchronized void updateBucketBy(int delta) {
        this.tokens += delta;
    }

    private synchronized void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void start() throws InterruptedException {
        maintainBucket();

        Scanner scanner = new Scanner(System.in);
        String inputUrl;

        System.out.println("Enter text (type 'exit' to stop):");

        while (true) {
            inputUrl = scanner.nextLine();
            if ("exit".equalsIgnoreCase(inputUrl)) {
                break;
            } else {
                if(tokens > 0){
                    System.out.println("URL of the API received : " + inputUrl);
                    System.out.println("Tokens remaining = " + tokens);

                    updateBucketBy(this.tokenDecrement);
                } else {
                    System.out.println("Please try again later!!");
                }
            }
        }
    }

    private void maintainBucket() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < this.timeLimit; i++) {
                updateBucketBy(this.tokenIncrement);
                if (tokens > 10) setTokens(10);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread was interrupted");
                }
            }
        });
        thread.start();
    }
}
