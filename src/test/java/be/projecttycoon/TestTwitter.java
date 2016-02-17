package be.projecttycoon;

import be.projecttycoon.social.TwitterUpload;
import twitter4j.TwitterException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiwi on 12/02/2016.
 */


public class TestTwitter {

    //Twitter channel is https://twitter.com/test_azzy
    public static void main(String[] args) throws TwitterException, InterruptedException {
        TwitterUpload uploader=new TwitterUpload();

        List<String> hashTags=new ArrayList<String>();
        hashTags.add("#test");

        String path = uploader.postImageAndText(new File("D:\\test.gif"),"test why u no work",hashTags);

        System.out.println("You can see the media path ....^");
        System.out.println("The tweet path is " + path);
    }
}
