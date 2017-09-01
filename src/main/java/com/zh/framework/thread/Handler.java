package com.zh.framework.thread;

/**
 * created by lihuibo on 17-9-1 上午8:10
 */
public abstract class Handler {
    private MessageQueue mQueue;

    public Handler() {
        mQueue = Looper.myLooper().myQueue();
    }

    public final void dispatchMessage(Message message) {
        this.handleMessage(message);
    }

    public abstract void handleMessage(Message message);

    public void sendMessage(Message message) {
        if (mQueue == null)
            throw new RuntimeException("mQueue队列为空");
        if (message != null) {
            message.target = this;
            mQueue.add(message);
        }
    }
}
