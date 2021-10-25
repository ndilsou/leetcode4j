package org.nasoungadoy.leetcode4j.solutions;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TwitterTest {

    @Test
    void canBuild() {
        Twitter twitter = new Twitter();
        assertNotNull(twitter);
    }

    @Test
    void baseScenario() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        assertIterableEquals(List.of(5), twitter.getNewsFeed(1));
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        assertIterableEquals(List.of(6, 5), twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        assertIterableEquals(List.of(5), twitter.getNewsFeed(1)); // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }

    @Test
    void newFollowersGetPastTweets() {
//        ["Twitter","postTweet","getNewsFeed","follow","getNewsFeed","unfollow","getNewsFeed"]
//[[],[1,1],[1],[2,1],[2],[2,1],[2]]
        //[null,null,[1],null,[1],null,[]]

        Twitter twitter = new Twitter();
        twitter.postTweet(1, 1); // User 1 posts a new tweet (id = 5).
        assertIterableEquals(List.of(1), twitter.getNewsFeed(1));
        twitter.follow(2, 1);    // User 1 follows user 2.
        assertIterableEquals(List.of(1), twitter.getNewsFeed(2));
        twitter.unfollow(2, 1);  // User 1 unfollows user 2.
        assertIterableEquals(List.of(), twitter.getNewsFeed(2)); // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }

    //["Twitter","postTweet","follow","follow","getNewsFeed"]
//        [[],[2,5],[1,2],[1,2],[1]]
    // [null,null,null,null,[5]]
    @Test
    void followSameUserTwice() {
        var twitter = new Twitter();
        twitter.postTweet(2, 5);
        twitter.follow(1, 2);
        twitter.follow(1, 2);
        assertIterableEquals(List.of(5), twitter.getNewsFeed(1));
    }

    @Test
    void userWithNoFollowees() {
        var twitter = new Twitter();
        twitter.postTweet(2, 5);
        twitter.postTweet(2, 3);
        twitter.postTweet(2, 1);
        assertIterableEquals(List.of(), twitter.getNewsFeed(1));
    }

    @Test
    void getSelfFeed() {
        //["Twitter","postTweet","postTweet","getNewsFeed"]
        //[[],[1,5],[1,3],[1]]
        //[null,null,null,[3,5]]

        var twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        var feed = twitter.getNewsFeed(1);
        assertIterableEquals(List.of(3, 5), feed);
    }

    //["Twitter","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","getNewsFeed","follow","getNewsFeed","unfollow","getNewsFeed"]
    //      [[],[1,5],[2,3],[1,101],[2,13],[2,10],[1,2],[1,94],[2,505],[1,333],[2,22],[1,11],[1,205],[2,203],[1,201],[2,213],[1,200],[2,202],[1,204],[2,208],[2,233],[1,222],[2,211],[1],[1,2],[1],[1,2],[1]]
//[null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,[222,204,200,201,205,11,333,94,2,101],null,[211,222,233,208,204,202,200,213,201,203],null,[222,204,200,201,205,11,333,94,2,101]]
    @Test
    void largeFeed() {
        var twitter = new Twitter();
        List<List<Integer>> arr = List.of(List.of(1, 5), List.of(2, 3), List.of(1, 101), List.of(2, 13), List.of(2, 10),
                List.of(1, 2), List.of(1, 94), List.of(2, 505), List.of(1, 333), List.of(2, 22), List.of(1, 11),
                List.of(1, 205), List.of(2, 203), List.of(1, 201), List.of(2, 213), List.of(1, 200), List.of(2, 202),
                List.of(1, 204), List.of(2, 208), List.of(2, 233), List.of(1, 222), List.of(2, 211));
        arr.forEach((List<Integer> a) -> twitter.postTweet(a.get(0), a.get(1)));
        List<Integer> want, got;

        want = List.of(222, 204, 200, 201, 205, 11, 333, 94, 2, 101);
        got = twitter.getNewsFeed(1);
        assertIterableEquals(want, got);

        twitter.follow(1, 2);

        want = List.of(211,222,233,208,204,202,200,213,201,203);
        got = twitter.getNewsFeed(1);
        assertIterableEquals(want, got);

        twitter.unfollow(1, 2);

        want = List.of(222,204,200,201,205,11,333,94,2,101);
        got = twitter.getNewsFeed(1);
        assertIterableEquals(want, got);

    }

//    @Test
//    void userUnfollow() {
//        var u = new Twitter.User(1);
//        u.send(new Twitter.Tweet(1, 2, 1));
//        u.send(new Twitter.Tweet(2, 3, 2));
//        u.send(new Twitter.Tweet(3, 2, 3));
//        assertEquals(3, u.newsFeedSize());
//
//        u.unfollow(2);
//        assertEquals(u.newsFeedSize(), 1);
//    }
}