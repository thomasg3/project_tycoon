package be.projecttycoon.model.level;

import be.projecttycoon.MyScheduler;
import be.projecttycoon.model.level.jobs.CloseLevelJob;
import org.quartz.*;

import java.util.Date;

/**
 * Created by thomas on 17/02/16.
 */
public class Open implements LevelState {
    private final Level context;
    private MyScheduler scheduler=new MyScheduler();

    public Open(Level context){

        this.context = context;

        long currentTime=System.currentTimeMillis();
        // define the job and tie it to our CloseLevelJob class
        JobDetail job = JobBuilder.newJob(CloseLevelJob.class)
                .withIdentity("CloseLevel"+currentTime, "closer")
                .build();
        job.getJobDataMap().put("level", context);

        context.setTimestampStart(currentTime);
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("trigger"+currentTime, "closerTrigger")
                .startAt(new Date(currentTime + context.getMinutesToClose()*60000L))
                .forJob("CloseLevel"+currentTime, "closer")
                .build();

            //Start countdown for game, to close...
        try {
            System.out.println("job scheduled... " + context.getId() +" @"+currentTime);
            this.scheduler.getScheduler().scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void open() {
        throw new IllegalStateException();
    }

    @Override
    public void close() {
        this.context.setState(Finished.class.getSimpleName());
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
        return false;
    }

    @Override
    public boolean questionsAreOpen() {
        return true;
    }

    @Override
    public boolean questionsAreVisible() {
        return true;
    }
}
