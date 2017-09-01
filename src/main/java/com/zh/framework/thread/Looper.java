package com.zh.framework.thread;

/**
 * created by lihuibo on 17-9-1 上午7:53
 */
public class Looper {

    private static ThreadLocal<Looper> sLocal = new ThreadLocal<>();

    private final MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue(40, true);
    }


    public final static void loop() {
        final Looper looper = Looper.myLooper();
        if (looper == null)
            throw new RuntimeException("调用此方法之前必须调用Looper.prepare()");
        final MessageQueue mQueue = looper.mQueue;
        Message message;
        Handler handler;
        while (!Thread.interrupted()) {
            message = mQueue.poll();
            if (message == null)
                continue;
            handler = message.target;
            handler.handleMessage(message);
        }
//        System.out.println("Looper thread quit!");
    }

    public final static void prepare() {
        Looper looper = sLocal.get();
        if (looper != null)
            throw new RuntimeException("已经调用过此方法,不能重复调用");
        sLocal.set(new Looper());
    }

    public final static Looper myLooper() {
        return sLocal.get();
    }

    public final static MessageQueue myQueue() {
        final Looper looper = myLooper();
        return looper.mQueue;
    }
}
