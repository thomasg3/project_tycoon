package be.projecttycoon;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by kiwi on 19/02/2016.
 */


public class MyScheduler {
    public Scheduler scheduler;


    public  MyScheduler(){
        try{
            // Grab the MyScheduler instance from the Factory
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            //scheduler.shutdown(), to disable scheduler...
        }
        catch(SchedulerException e){
            e.printStackTrace();
        }
    }


    public Scheduler getScheduler(){
        return scheduler;
    }


}
