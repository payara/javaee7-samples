package org.javaee7.batch.samples.scheduling;

import static java.util.Calendar.SECOND;
import static java.util.concurrent.TimeUnit.MINUTES;
import static jakarta.batch.runtime.BatchStatus.COMPLETED;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.LastExecution;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.concurrent.Trigger;

/**
 * @author arungupta
 */
@Stateless
@Local(MyManagedScheduledBatch.class)
public class MyManagedScheduledBatchBean implements MyManagedScheduledBatch {
    
    @Resource
    private ManagedScheduledExecutorService executor;

    @Override
    public void runJob() {
        executor.schedule(createJob(), new Trigger() {

            @Override
            public Date getNextRunTime(LastExecution lastExecutionInfo, Date taskScheduledTime) {
                if (MyJob.executedBatchs.size() >= 3) {
                    return null;
                }

                Calendar cal = Calendar.getInstance();

                if (lastExecutionInfo == null) {
                    cal.setTime(taskScheduledTime);
                } else {
                    cal.setTime(lastExecutionInfo.getRunStart());
                }

                cal.add(SECOND, 10);
                return cal.getTime();
            }

            @Override
            public boolean skipRun(LastExecution lastExecutionInfo, Date scheduledRunTime) {
                List<Long> executedBatchs = MyJob.executedBatchs;

                for (Long executedBatch : executedBatchs) {
                    if (!BatchRuntime.getJobOperator().getJobExecution(executedBatch).getBatchStatus().equals(
                        COMPLETED)) {
                        return true;
                    }
                }

                return false;
            }

        });
    }

    public void runJob2() {
        executor.scheduleWithFixedDelay(new MyJob(), 1, 2, MINUTES);
    }

    protected MyJob createJob() {
        return new MyJob();
    }
}
