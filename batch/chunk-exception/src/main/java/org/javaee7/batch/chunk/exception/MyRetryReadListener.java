package org.javaee7.batch.chunk.exception;

import jakarta.batch.api.chunk.listener.RetryReadListener;
import jakarta.inject.Named;

/**
 * @author Roberto Cortez
 */
@Named
public class MyRetryReadListener implements RetryReadListener {
    @Override
    public void onRetryReadException(Exception ex) throws Exception {
        ChunkExceptionRecorder.retryReadExecutions++;
        ChunkExceptionRecorder.chunkExceptionsCountDownLatch.countDown();
        System.out.println("MyRetryReadListener.onRetryReadException " + ex.getMessage());
    }
}
