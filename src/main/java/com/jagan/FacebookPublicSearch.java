package com.jagan;

import facebook4j.*;
import facebook4j.auth.AccessToken;

/**
 * Created by Jagan on 05/11/2016.
 */
public class FacebookPublicSearch {

    public static void main(String[] args) throws FacebookException {

        String accessToken = "EAACEdEose0cBAE4BR6yqIMmMGLaQ1FaFfZBbkSp3KG3u0mR4MYIB70OBmfBgqxg6n3DJVCIiB6pAEfOIRbTpEp8fuT7ZB6bYFolUecpasP7nIT8iAcfQFamhPZA8aHpayXYVb0HPey7jO5umkFNWB2ZAsFuNOYusGnJKjEC7XQZDZD";

        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("1119322921489035", "7899dd52c9bf14de8d15bedb44c3a8ac");
        facebook.setOAuthAccessToken(new AccessToken(accessToken));

        /** facebook public search is deprecated " **/
        ResponseList<Post> results = facebook.searchPosts("watermelon");
        System.out.println(results);

    }
}
