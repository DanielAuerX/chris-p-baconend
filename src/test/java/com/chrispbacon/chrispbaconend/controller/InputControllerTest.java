package com.chrispbacon.chrispbaconend.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class InputControllerTest {

    @Test
    void tenTokenPerMinute() {
        Refill refill = Refill.intervally(10, Duration.ofMinutes(1));  //refills bucket every minute with 10 toekn
        Bandwidth limit = Bandwidth.classic(10, refill);        // capacity of bucket and rate of refill
        Bucket bucket = Bucket.builder()
                .addLimit(limit)
                .build();

        for (int i = 1; i <= 10; i++) {
            assertTrue(bucket.tryConsume(1));
        }
        assertFalse(bucket.tryConsume(1));
    }

    @Test
    void oneTokenPerTwoSeconds() {
        Bandwidth limit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(2)));
        Bucket bucket = Bucket.builder()
                .addLimit(limit)
                .build();
        assertTrue(bucket.tryConsume(1));     // first request
        Executors.newScheduledThreadPool(1)   // schedule another request for 2 seconds later
                .schedule(() -> assertTrue(bucket.tryConsume(1)), 2, TimeUnit.SECONDS);
    }


    /*
    Suppose we have a rate limit of 10 requests per minute.
    At the same time, we may wish to avoid spikes that would exhaust all the tokens in the first 5 seconds.
    Bucket4j allows us to set multiple limits (Bandwidth) on the same bucket.
    Let’s add another limit that allows only 5 requests in a 20-second time window:
     */
    @Test
    void tenRequestsPerMinuteAndNotExhaustAllTokenInTheFirstFiveSeconds() {
        Bucket bucket = Bucket.builder()
                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofSeconds(20))))
                .build();

        for (int i = 1; i <= 5; i++) {
            assertTrue(bucket.tryConsume(1));
        }
        assertFalse(bucket.tryConsume(1));
    }
}