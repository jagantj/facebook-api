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

        String accessToken = "EAACEdEose0cBAMRiJsKRtFyGTFQ8Mt3WsZBBZCLFE5ySeDgXhB8lb8AGXLsX3Cw3DqQzZCYGvAJZAhI8UXaqoPOX4l5lbRvQv7aU0rYZByov2IAOWcRtP4DfJrScTn9QfnIz6SoXhDTqJn731I2riMXGFkuyDJHk2CkSxBw5mhwZDZD";
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
        Connection<Page> pageResults =  fbClient.fetchConnection("search", Page.class, Parameter.with("q", "Rabobank Netherlands"),
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
