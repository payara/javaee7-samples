package org.javaee7.batch.samples.scheduling;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * @author arungupta
 */
@Startup
@Singleton
public class MyTimerScheduleBean extends AbstractTimerBatch {
}
