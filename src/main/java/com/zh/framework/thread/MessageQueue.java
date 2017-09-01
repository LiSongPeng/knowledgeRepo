package com.zh.framework.thread;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * created by lihuibo on 17-9-1 上午8:05
 */
public class MessageQueue extends ArrayBlockingQueue<Message> {
    public MessageQueue(int capacity) {
        super(capacity);
    }

    public MessageQueue(int capacity, boolean fair) {
        super(capacity, fair);
    }

    public MessageQueue(int capacity, boolean fair, Collection<? extends Message> c) {
        super(capacity, fair, c);
    }
}
