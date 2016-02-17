package be.projecttycoon.model.level;

/**
 * Created by thomas on 17/02/16.
 */
public interface LevelState {
    void open();
    void close();
    void pointPush();
    void cermonieFinished();

    boolean teamsCanSeePoints();
    boolean documentsAreOpen();
    boolean questionsAreOpen();
    boolean questionsAreVisible();

}
