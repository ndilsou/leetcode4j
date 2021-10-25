package org.nasoungadoy.leetcode4j.solutions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

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

public class TwitterSlow {
    private static final int FEED_SIZE = 10;
    /**
     * Initialize your data structure here.
     */
    private final Map<Integer, User> users;
    private final AtomicInteger clock;

    public TwitterSlow() {
        users = new HashMap<>();
        clock = new AtomicInteger();
    }

    private User getOrCreateUser(int userId) {
        return users.computeIfAbsent(userId, User::new);
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        var user = getOrCreateUser(userId);
        user.post(new Tweet(tweetId, user.id, nextTick()));
    }

    private int nextTick() {
        return clock.incrementAndGet();
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        var user = getOrCreateUser(userId);
        return user.getNewsFeed(FEED_SIZE);
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        var follower = getOrCreateUser(followerId);
        var followee = getOrCreateUser(followeeId);
        followee.accept(follower);
        follower.follow(followee);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        var follower = users.get(followerId);
        follower.unfollow(followeeId);
        var followee = users.get(followeeId);
        followee.unsubscribe(followerId);

    }

    static class User {
        final int id;
        private final LinkedList<Tweet> newsFeed;
        private final Map<Integer, User> followers;
        private final Set<Integer> followees;
        private final List<Tweet> tweets;

        User(int id) {
            this.id = id;
            newsFeed = new LinkedList<>();
            followers = new HashMap<>();
            followees = new HashSet<>();
            tweets = new ArrayList<>();
        }

        List<Integer> getNewsFeed(int count) {
            return newsFeed.stream()
                    .limit(count)
                    .map(t -> t.id)
                    .collect(toList());
        }

        void send(Tweet t) {
            newsFeed.push(t);
        }

        void post(Tweet t) {
            tweets.add(t);
            send(t);
            followers.values().forEach(u -> u.send(t));
        }

        int newsFeedSize() {
            return newsFeed.size();
        }

        void unfollow(int userId) {
            newsFeed.removeIf(t -> t.userId == userId);
        }

        public List<Tweet> getHistory() {
            return tweets;
        }

        public void follow(User user) {
            if (followees.contains(user.id)) return;

            followees.add(user.id);
            newsFeed.addAll(user.getHistory());
            newsFeed.sort(Collections.reverseOrder(Tweet.byTimestamp()));

        }

        public void unsubscribe(int followerId) {
            followers.remove(followerId);
        }

        public void accept(User follower) {
            followers.put(follower.id, follower);
        }
    }

    static class Tweet implements Comparable<Tweet> {
        final int id;
        final int userId;
        final long ts;

        Tweet(int id, int userId, int ts) {
            this.id = id;
            this.userId = userId;
            this.ts = ts;
        }

        static Comparator<Tweet> byTimestamp() {
            return (a, b) -> (int) (a.ts - b.ts);

        }

        @Override
        public int compareTo(Tweet that) {
            return (int) (that.ts - this.ts);
        }
    }
}
