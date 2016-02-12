package be.projecttycoon.social;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * Created by kiwi on 12/02/2016.
 */
public class TwitterUpload {

    private String userAccessToken;
    private String userAccessSecret;
    private String consumerKey;
    private String keySecret;

    public TwitterUpload() throws TwitterException{
        /*todo move to a better place!!!!! + change to production twitter channel*/
        String USERACCESSTOKEN ="4900466249-X52eco5aovAl3IxQIX39Fi4jMIJzYmfC1v40CqX";
        String USERACCESSSECRET ="vgV88rRJlWRomATz759hn1B1fPfjBLWUF5gxOU9TMhns0";
        String CONSUMERKEY ="eBAQiiMn6XSseEcabLUd4X1eM";
        String KEYSECRET ="j7SldD2HZvj9zJQ8EPDwPpFRlLGU6SBDuVA2afVzp0uJrxVkaD";

        this.setConsumerKey(CONSUMERKEY);
        this.setKeySecret(KEYSECRET);
        this.setUserAccessSecret(USERACCESSSECRET);
        this.setUserAccessToken(USERACCESSTOKEN);

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(this.getConsumerKey(),this.getKeySecret());
        RequestToken requestToken = twitter.getOAuthRequestToken();


        /* todo Moet aangepast worden... ->*/
        System.out.println("Open the following URL and grant access to your account: "+ requestToken.getAuthorizationURL());
        System.out.print("Enter the PIN and hit enter after you granted access.[PIN]:");

        String pin=null;
        try {
            pin = new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        catch(IOException e){
            System.out.println("Reading pin failed!!");
            e.printStackTrace();
        }
        /* todo <- Moet aangepast worden... */

        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
        this.setUserAccessToken(accessToken.getToken());
        this.setUserAccessSecret(accessToken.getTokenSecret());
    }


    /**
     *
     * @param file is a file (of an image) that you wish to post. If null is passed, no image will be posted, only the message will
     *                 be posted
     * @param message is a simple string containing a message
     * @param hashtags is a collection of strings starting with a number sign (#) and followed by a tag i.e. #lol
     * @return the url to the created post on twitter
     * @throws TwitterException
     */
    public String postImageAndText(File file, String message, Collection<String> hashtags) throws TwitterException{
        return getUploader().upload(file, generateMessage(message,hashtags));
    }




    private String generateMessage(String message, Collection<String> hashtags){
        StringBuilder strbuilder=new StringBuilder(message);
        if(hashtags != null) {
            for (String hashtag : hashtags) {
                strbuilder.append(" ");
                strbuilder.append(hashtag);
            }
        }
        return strbuilder.toString();
    }


    private ImageUpload getUploader(){
        Configuration conf = new ConfigurationBuilder()
                .setOAuthConsumerKey(this.getConsumerKey())
                .setOAuthConsumerSecret(this.getKeySecret())
                .setOAuthAccessToken(this.getUserAccessToken())
                .setOAuthAccessTokenSecret(this.getUserAccessSecret()).build();

        OAuthAuthorization auth = new OAuthAuthorization(conf);
        return new ImageUploadFactory(conf).getInstance(auth);
    }


    private String getUserAccessToken() {
        return userAccessToken;
    }

    private void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    private String getUserAccessSecret() {
        return userAccessSecret;
    }

    private void setUserAccessSecret(String userAccessSecret) {
        this.userAccessSecret = userAccessSecret;
    }

    private String getConsumerKey() {
        return consumerKey;
    }

    private void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    private String getKeySecret() {
        return keySecret;
    }

    private void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }



}
