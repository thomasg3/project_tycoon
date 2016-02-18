package be.projecttycoon.model.level;

/**
 * Created by thomas on 17/02/16.
 */
public class Concluded implements LevelState {
    private final Level context;

    public Concluded(Level context){
        this.context = context;
    }

    @Override
    public void open() {
        throw new IllegalStateException();
    }

    @Override
    public void close() {
        throw new IllegalStateException();
    }

    @Override
    public void pointPush() {
        throw new IllegalStateException();
    }

    @Override
    public void cermonieFinished() {
        throw new IllegalStateException();
    }

    @Override
    public boolean documentsAreOpen() {
        return true;
    }

    @Override
    public boolean teamsCanSeePoints() {
        return true;
    }

    @Override
    public boolean questionsAreOpen() {
        return false;
    }

    @Override
    public boolean questionsAreVisible() {
        return true;
    }
}
