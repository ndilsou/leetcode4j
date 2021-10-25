package org.nasoungadoy.leetcode4j.solutions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself.
Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.

Example 1:

Input
`
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
`
Output
`
[null, null, [5], null, null, [6, 5], null, [5]]
`
Explanation
`
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
`
Constraints:
    - 1 <= userId, followerId, followeeId <= 500
    - 0 <= tweetId <= 104
    - All the tweets have unique IDs.
    - At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 */

public class Twitter {
    private static final int FEED_SIZE = 10;
    /**
     * Initialize your data structure here.
     */
    private final Map<Integer, Set<Integer>> followers;
    private final Map<Integer, LinkedList<Tweet>> tweets;
    private final AtomicInteger clock;

    public Twitter() {
        clock = new AtomicInteger();
        followers = new HashMap<>();
        tweets = new HashMap<>();
    }


    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        followers.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        tweets.computeIfAbsent(userId, k -> new LinkedList<>())
                .add(new Tweet(tweetId, nextTick()));
    }

    private int nextTick() {
        return clock.incrementAndGet();
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        var feed = new LinkedList<Integer>();
        if (!followers.containsKey(userId)) {
            return feed;
        }
        var pq = new PriorityQueue<>(Collections.reverseOrder(Tweet.byTimestamp()));
        var indices = new HashMap<Integer, AtomicInteger>();

        for (var id : followers.get(userId)) {
            var userTweets = tweets.computeIfAbsent(id, k -> new LinkedList<>());
            if (userTweets.isEmpty()) {
                continue;
            }
            var idx = indices.computeIfAbsent(id, k -> new AtomicInteger(userTweets.size()));

            pq.add(userTweets.get(idx.decrementAndGet()));
            indices.put(id, idx);
            if (pq.size() > FEED_SIZE) {
                var t = pq.poll();
                feed.add(t.id);
            }
        }

        while (feed.size() < FEED_SIZE && !pq.isEmpty()) {
            for (var e : indices.entrySet()) {
                if (e.getValue().get() <= 0) {
                    continue;
                }
                var t = tweets.get(e.getKey()).get(e.getValue().decrementAndGet());
                pq.add(t);
            }
            var t = pq.poll();
            feed.add(t.id);
        }

        return feed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        followers.computeIfAbsent(followerId, k -> new HashSet<>()).add(followerId);
        followers.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        followers.get(followerId).remove(followeeId);
    }


    static class Tweet {
        final int id;
        final long ts;

        Tweet(int id, int ts) {
            this.id = id;
            this.ts = ts;
        }

        static Comparator<Tweet> byTimestamp() {
            return (a, b) -> (int) (a.ts - b.ts);

        }

        @Override
        public String toString() {
            return "Tweet{" +
                    "id=" + id +
                    ", ts=" + ts +
                    '}';
        }
    }
}
