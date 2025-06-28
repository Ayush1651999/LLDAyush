package Self.RateLimiter;

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        TokenBucketLimiter limiter = new TokenBucketLimiter();
        limiter.start();
    }
}
