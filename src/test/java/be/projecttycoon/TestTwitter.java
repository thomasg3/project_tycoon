package be.projecttycoon;

import be.projecttycoon.social.TwitterUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import twitter4j.TwitterException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiwi on 12/02/2016.
 */


public class TestTwitter {

    //Twitter channel is https://twitter.com/test_azzy
    public static void main(String[] args) throws TwitterException{
        TwitterUpload uploader=new TwitterUpload();
        List<String> hashTags=new ArrayList<String>();
        hashTags.add("#panda");
        hashTags.add("#red");
        hashTags.add("#giphy");
        System.out.println(uploader.postImageAndText(new File("D:\\test.gif"),"/giphy red panda",hashTags));

        hashTags.clear();
        hashTags.add("#test");
        System.out.println(uploader.postImageAndText(new File("D:\\test.png"),"/giphy test",hashTags));
    }
}
