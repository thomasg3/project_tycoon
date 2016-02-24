package be.projecttycoon.model.level;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thomas on 17/02/16.
 */
public class Closed implements LevelState {
    private final Level context;

    public Closed(Level context){
        this.context = context;
    }
    @Override
    public void open() {
        context.setState(Open.class.getSimpleName());
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
        return false;
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
        return false;
    }
}
