package be.projecttycoon.model.level;

/**
 * Created by thomas on 17/02/16.
 */
public class Finished implements LevelState {
    private final Level context;

    public Finished(Level context){
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
        context.setState(new Cermonie(context));
    }

    @Override
    public void cermonieFinished() {
        throw new IllegalStateException();
    }
}
