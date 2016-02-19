package be.projecttycoon.model.level.jobs;

import be.projecttycoon.model.level.Level;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by kiwi on 19/02/2016.
 */
public class CloseLevelJob  implements Job{


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Level level = (Level)jobExecutionContext.getJobDetail().getJobDataMap().get("level");
        level.setState("Closed");
        System.out.println("Game with id "+level.getId() + " is now closed");
    }
}
