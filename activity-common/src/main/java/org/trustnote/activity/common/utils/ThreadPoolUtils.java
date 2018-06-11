package org.trustnote.activity.common.utils;

import java.util.concurrent.*;

public class ThreadPoolUtils {
    private static final long CACHE_ALIVED_TIME = 60L;

    private ThreadPoolUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 构造一个固定线程数目的线程池，配置的corePoolSize与maximumPoolSize大小相同，
     * 同时使用了一个无界LinkedBlockingQueue存放阻塞任务，因此多余的任务将存在再阻塞队列，
     * 不会由RejectedExecutionHandler处理
     *
     * @param nThreads 线程数
     * @return ExceutorService
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    /**
     * 构造一个缓冲功能的线程池，配置corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，
     * keepAliveTime=60s,以及一个无容量的阻塞队列 SynchronousQueue，
     * 因此任务提交之后，将会创建新的线程执行；线程空闲超过60s将会销毁
     *
     * @return ExecutorService
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                CACHE_ALIVED_TIME, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }

    /**
     * 构造一个只支持一个线程的线程池，配置corePoolSize=maximumPoolSize=1.
     * 无界阻塞队列LinkedBlockingQueue;保证任务由一个线程串行执行
     *
     * @return ExecutorService
     */
    public static ExecutorService newSingleThreadExecutor() {
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

    }
}