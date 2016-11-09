package com.jagan;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.Files;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;

/**
 * Created by Jagan on 07/11/2016.
 */
public class FacebookPageSearch {

    public static void main(String[] args) throws IOException {

        FacebookClient.AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken("1119322921489035", "7899dd52c9bf14de8d15bedb44c3a8ac");

        FacebookClient fbClient = new DefaultFacebookClient(accessToken.getAccessToken());
        File file = new File("c:/Tools/test.txt");
        List<String> feeds = Lists.newArrayList();

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
                        // System.out.println(posts.size());
                         if(noOfPosts > 2) {
                             break;
                         }
                         int noOfPost = 0;
                         for (Post post : posts) {
                             if(noOfPost > 2) {
                                 break;
                             }

                             System.out.println(post.getCreatedTime()
                                     + " " + page.getName() + " " + post.getMessage() );
                             feeds.add(post.getCreatedTime().toString());
                             feeds.add(page.getName());
                             feeds.add(post.getMessage());
                             feeds.add(System.getProperty("line.separator"));


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
        writeTofile(feeds, file);
        System.out.println(noOfPagesSearched + " pages searched");

    }

    public static void writeTofile(List<String> feeds, File file) {


         CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
        try {
            sink.writeLines(feeds, " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
