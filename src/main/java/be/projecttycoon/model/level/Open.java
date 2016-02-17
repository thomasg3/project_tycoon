package be.projecttycoon.model.level;

/**
 * Created by thomas on 17/02/16.
 */
public class Open implements LevelState {
    private final Level context;

    public Open(Level context){
        this.context = context;
    }

    @Override
    public void open() {
        throw new IllegalStateException();
    }

    @Override
    public void close() {
        this.context.setState(new Finished(context));
    }

    @Override
    public void pointPush() {
        throw new IllegalStateException();
    }

    @Override
    public void cermonieFinished() {
        throw new IllegalStateException();
    }
}
