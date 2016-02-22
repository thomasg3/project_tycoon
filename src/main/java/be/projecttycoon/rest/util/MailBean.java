package be.projecttycoon.rest.util;

import be.projecttycoon.model.Team;

import java.util.List;
import java.util.Set;

/**
 * Created by kiwi on 18/02/2016.
 */
public class MailBean {
    private String subject;
    private String message;
    private List<Team> recipients;


    public List<Team> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Team> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
