package org.javaee7.batch.samples.scheduling;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Alternative;

/**
 * @author Roberto Cortez
 */
@Alternative
@Stateless
@Local(MyManagedScheduledBatch.class)
public class MyManagedScheduledBatchAlternative extends MyManagedScheduledBatchBean {
   
    @Override
    protected MyJob createJob() {
        return new MyJobAlternative();
    }
}
