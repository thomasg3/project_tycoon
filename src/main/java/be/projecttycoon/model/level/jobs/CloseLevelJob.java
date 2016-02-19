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
        System.out.println("Message will be printed in "+ jobExecutionContext.getNextFireTime());
        System.out.println("Hello Quartz!");
    }
}
