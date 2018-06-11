package org.trustnote.activity.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例，线程池
 * @author Taozi
 *
 */
public class SendingPool {

    private SendingPool() {
    }
    private static class Inner{
        private static SendingPool instance = new SendingPool();
    }

    public static SendingPool getInstance(){
        return Inner.instance;
    }

    private static int nThreads = 10;
  //private static ExecutorService executor = Executors.newFixedThreadPool(nThreads);

    public SendingPool addThread(Sending sending) {
        ExecutorService executor = ThreadPoolUtils.newFixedThreadPool(nThreads);
        executor.execute(sending);
        executor.shutdown();
        return getInstance();
    }



}