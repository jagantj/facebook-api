package com.jagan;

import com.restfb.*;
import com.restfb.types.*;
import com.restfb.types.User;


import java.util.List;

/**
 * Created by Jagan on 05/11/2016.
 */
public class FacebookSearch {

    public static void main(String[] args)  {

        String accessToken = "EAACEdEose0cBAE4BR6yqIMmMGLaQ1FaFfZBbkSp3KG3u0mR4MYIB70OBmfBgqxg6n3DJVCIiB6pAEfOIRbTpEp8fuT7ZB6bYFolUecpasP7nIT8iAcfQFamhPZA8aHpayXYVb0HPey7jO5umkFNWB2ZAsFuNOYusGnJKjEC7XQZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);


        User user = fbClient.fetchObject("me", User.class);
        System.out.println(user.getBirthday());

        /** Search posts is deprecated in facebook now **/
//        Connection<Post> postResults =  fbClient.fetchConnection("search", Post.class, Parameter.with("q", "Rabobank"),
//                Parameter.with("type", "post"));

        /** Search User **/
        Connection<User> searchResults =  fbClient.fetchConnection("search", User.class, Parameter.with("q", "Rabobank"),
                Parameter.with("type", "user"));

        for(List<User> page: searchResults) {
            for(User auser: page) {
                System.out.println("user names available in facebook ==>" + auser.getName());

            }
        }
       /** Search Page **/
        Connection<Page> pageResults =  fbClient.fetchConnection("search", Page.class, Parameter.with("q", "Rabobank"),
                Parameter.with("type", "page"));

        int noOfPagesSearched = 0;
        for(List<Page> pages : pageResults) {
            for (Page page : pages) {
                System.out.println("page name available in facebook ==>" + page.getName());
                System.out.println("fb.com/" + page.getId());
                noOfPagesSearched ++ ;
            }
        }
        System.out.println(noOfPagesSearched + " pages searched");

       /** Similarly we can also search facebook groups **/

    }
}
