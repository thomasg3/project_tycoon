package be.projecttycoon.model.level;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

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
        context.setState(Concluded.class.getSimpleName());

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
