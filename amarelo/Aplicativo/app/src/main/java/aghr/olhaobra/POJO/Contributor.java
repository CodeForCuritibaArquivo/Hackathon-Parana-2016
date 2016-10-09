package aghr.olhaobra.POJO;

/**
 * Created by Hadryel on 09/10/2016.
 */

public class Contributor {

    String login;
    String html_url;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}