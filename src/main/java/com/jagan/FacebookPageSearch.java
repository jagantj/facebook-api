package com.jagan;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;

import java.util.List;

/**
 * Created by Jagan on 07/11/2016.
 */
public class FacebookPageSearch {

    public static void main(String[] args) {

        FacebookClient.AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken("1119322921489035", "7899dd52c9bf14de8d15bedb44c3a8ac");

        FacebookClient fbClient = new DefaultFacebookClient(accessToken.getAccessToken());

        /** Search Page **/
        Connection<Page> pageResults =  fbClient.fetchConnection("search", Page.class, Parameter.with("q", "Rabobank Netherlands"),
                Parameter.with("type", "page"));

        int noOfPagesSearched = 0;
        for(List<Page> pages : pageResults) {
            for (Page page : pages) {

                System.out.println("fb.com/" + page.getId());

                    int noOfPosts = 0;
                    Connection<Post> postFeed = fbClient.fetchConnection(page.getId()+"/feed", Post.class);
                     for (List<Post> posts : postFeed) {
                         System.out.println(posts.size());
                         if(noOfPosts > 2) {
                             break;
                         }
                         int noOfPost = 0;
                         for (Post post : posts) {
                             if(noOfPost > 2) {
                                 break;
                             }
                            System.out.println(post.getCreatedTime()
                                     + " " + page.getName() + " " + post.getMessage());
                             noOfPost ++;
                          }
                         noOfPosts ++;
                     }


                noOfPagesSearched ++ ;

                if(noOfPagesSearched > 2) {
                    break;
                }
            }
        }
        System.out.println(noOfPagesSearched + " pages searched");

    }

}
