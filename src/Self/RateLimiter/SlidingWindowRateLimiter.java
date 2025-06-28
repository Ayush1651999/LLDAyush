package Self.RateLimiter;

import java.util.*;

public class SlidingWindowRateLimiter {
    private final int maxRequests;
    private final long timeWindowMillis;
    private final Deque<Long> requestTimestamps;

    public SlidingWindowRateLimiter(int maxRequests, int timeWindowSeconds) {
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowSeconds * 1000L;
        this.requestTimestamps = new ArrayDeque<>();
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Remove timestamps outside the sliding window
        while (!requestTimestamps.isEmpty() &&
                currentTime - requestTimestamps.peekFirst() > timeWindowMillis) {
            requestTimestamps.pollFirst();
        }

        if (requestTimestamps.size() < maxRequests) {
            requestTimestamps.offerLast(currentTime);
            return true;
        } else {
            return false;
        }
    }

    // Example usage
    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(5, 10); // 5 requests per 10 seconds

        for (int i = 0; i < 20; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rejected"));
            Thread.sleep(1000); // 1 request per second
        }
    }
}