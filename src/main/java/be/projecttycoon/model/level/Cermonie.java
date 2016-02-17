package be.projecttycoon.model.level;

/**
 * Created by thomas on 17/02/16.
 */
public class Cermonie implements LevelState {
    private final Level context;

    public Cermonie(Level context){
        this.context = context;
    }

    @Override
    public void cermonieFinished() {
        context.setState(new Concluded(context));
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
    public boolean documentsAreOpen() {
        return true;
    }

    @Override
    public boolean teamsCanSeePoints() {
        return false;
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
