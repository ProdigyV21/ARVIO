package androidx.media3.common.util;

import android.os.Looper;
import android.os.Message;
import androidx.activity.p;
import androidx.media3.common.FlagSet;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes3.dex */
public final class ListenerSet<T> {
    private static final int MSG_ITERATION_FINISHED = 1;
    private final Clock clock;
    private final ArrayDeque<Runnable> flushingEvents;
    private final IterationFinishedEvent<T> iterationFinishedEvent;
    private final HandlerWrapper iterationFinishedHandler;
    private final CopyOnWriteArraySet<ListenerHolder<T>> listeners;
    private final ArrayDeque<Runnable> queuedEvents;
    private boolean released;
    private final Object releasedLock;
    private final Thread thread;
    private boolean throwsWhenUsingWrongThread;

    public interface Event<T> {
        void invoke(T t2);
    }

    public interface IterationFinishedEvent<T> {
        void invoke(T t2, FlagSet flagSet);
    }

    public static final class ListenerHolder<T> {
        private FlagSet.Builder flagsBuilder = new FlagSet.Builder();
        public final T listener;
        private boolean needsIterationFinishedEvent;
        private boolean released;

        public ListenerHolder(T t2) {
            this.listener = t2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void release(IterationFinishedEvent<T> iterationFinishedEvent) {
            this.released = true;
            if (iterationFinishedEvent == null || !this.needsIterationFinishedEvent) {
                return;
            }
            this.needsIterationFinishedEvent = false;
            iterationFinishedEvent.invoke(this.listener, this.flagsBuilder.build());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ListenerHolder.class != obj.getClass()) {
                return false;
            }
            return this.listener.equals(((ListenerHolder) obj).listener);
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        public void invoke(int i10, Event<T> event) {
            if (this.released) {
                return;
            }
            if (i10 != -1) {
                this.flagsBuilder.add(i10);
            }
            this.needsIterationFinishedEvent = true;
            event.invoke(this.listener);
        }

        public void iterationFinished(IterationFinishedEvent<T> iterationFinishedEvent) {
            if (this.released || !this.needsIterationFinishedEvent) {
                return;
            }
            FlagSet flagSetBuild = this.flagsBuilder.build();
            this.flagsBuilder = new FlagSet.Builder();
            this.needsIterationFinishedEvent = false;
            iterationFinishedEvent.invoke(this.listener, flagSetBuild);
        }
    }

    public ListenerSet(Looper looper) {
        this(looper.getThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        IterationFinishedEvent<T> iterationFinishedEvent = this.iterationFinishedEvent;
        iterationFinishedEvent.getClass();
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().iterationFinished(iterationFinishedEvent);
            HandlerWrapper handlerWrapper = this.iterationFinishedHandler;
            handlerWrapper.getClass();
            if (handlerWrapper.hasMessages(1)) {
                break;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$queueEvent$0(CopyOnWriteArraySet copyOnWriteArraySet, int i10, Event event) {
        Iterator it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            ((ListenerHolder) it.next()).invoke(i10, event);
        }
    }

    private void verifyCurrentThread() {
        if (this.throwsWhenUsingWrongThread) {
            ac.b.s(Thread.currentThread() == this.thread);
        }
    }

    public void add(T t2) {
        t2.getClass();
        synchronized (this.releasedLock) {
            try {
                if (this.released) {
                    return;
                }
                this.listeners.add(new ListenerHolder<>(t2));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void clear() {
        verifyCurrentThread();
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().release(this.iterationFinishedEvent);
        }
        this.listeners.clear();
    }

    public ListenerSet<T> copy(Looper looper, IterationFinishedEvent<T> iterationFinishedEvent) {
        return copy(looper, this.clock, iterationFinishedEvent);
    }

    public void flushEvents() {
        verifyCurrentThread();
        if (this.queuedEvents.isEmpty()) {
            return;
        }
        if (this.iterationFinishedEvent != null) {
            HandlerWrapper handlerWrapper = this.iterationFinishedHandler;
            handlerWrapper.getClass();
            if (!handlerWrapper.hasMessages(1)) {
                HandlerWrapper handlerWrapper2 = this.iterationFinishedHandler;
                handlerWrapper2.sendMessageAtFrontOfQueue(handlerWrapper2.obtainMessage(1));
            }
        }
        boolean zIsEmpty = this.flushingEvents.isEmpty();
        this.flushingEvents.addAll(this.queuedEvents);
        this.queuedEvents.clear();
        if (zIsEmpty) {
            while (!this.flushingEvents.isEmpty()) {
                this.flushingEvents.peekFirst().run();
                this.flushingEvents.removeFirst();
            }
        }
    }

    public void queueEvent(Event<T> event) {
        queueEvent(-1, event);
    }

    public void release() {
        verifyCurrentThread();
        synchronized (this.releasedLock) {
            this.released = true;
        }
        Iterator<ListenerHolder<T>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().release(this.iterationFinishedEvent);
        }
        this.listeners.clear();
    }

    public void remove(T t2) {
        verifyCurrentThread();
        for (ListenerHolder<T> listenerHolder : this.listeners) {
            if (listenerHolder.listener.equals(t2)) {
                listenerHolder.release(this.iterationFinishedEvent);
                this.listeners.remove(listenerHolder);
            }
        }
    }

    public void sendEvent(Event<T> event) {
        sendEvent(-1, event);
    }

    @Deprecated
    public void setThrowsWhenUsingWrongThread(boolean z) {
        this.throwsWhenUsingWrongThread = z;
    }

    public int size() {
        verifyCurrentThread();
        return this.listeners.size();
    }

    public ListenerSet(Thread thread) {
        this(new CopyOnWriteArraySet(), null, thread, null, null, true);
    }

    public ListenerSet<T> copy(Looper looper) {
        return copy(looper, this.clock, this.iterationFinishedEvent);
    }

    public void queueEvent(int i10, Event<T> event) {
        verifyCurrentThread();
        this.queuedEvents.add(new p(i10, 2, new CopyOnWriteArraySet(this.listeners), event));
    }

    public void sendEvent(int i10, Event<T> event) {
        queueEvent(i10, event);
        flushEvents();
    }

    public ListenerSet(Looper looper, Clock clock, IterationFinishedEvent<T> iterationFinishedEvent) {
        this(new CopyOnWriteArraySet(), looper, looper.getThread(), clock, iterationFinishedEvent, true);
    }

    public ListenerSet<T> copy(Clock clock) {
        HandlerWrapper handlerWrapper = this.iterationFinishedHandler;
        if (handlerWrapper != null) {
            return copy(handlerWrapper.getLooper(), clock, this.iterationFinishedEvent);
        }
        return new ListenerSet<>(this.listeners, null, this.thread, clock, null, this.throwsWhenUsingWrongThread);
    }

    private ListenerSet(CopyOnWriteArraySet<ListenerHolder<T>> copyOnWriteArraySet, Looper looper, Thread thread, Clock clock, IterationFinishedEvent<T> iterationFinishedEvent, boolean z) {
        this.clock = clock;
        this.thread = thread;
        this.listeners = copyOnWriteArraySet;
        this.iterationFinishedEvent = iterationFinishedEvent;
        this.releasedLock = new Object();
        this.flushingEvents = new ArrayDeque<>();
        this.queuedEvents = new ArrayDeque<>();
        if (looper != null && clock != null && iterationFinishedEvent != null) {
            this.iterationFinishedHandler = clock.createHandler(looper, new c(this, 0));
        } else {
            this.iterationFinishedHandler = null;
        }
        this.throwsWhenUsingWrongThread = z;
    }

    public ListenerSet<T> copy(Looper looper, Clock clock, IterationFinishedEvent<T> iterationFinishedEvent) {
        ac.b.s(clock != null || iterationFinishedEvent == null);
        return new ListenerSet<>(this.listeners, looper, looper.getThread(), clock, iterationFinishedEvent, this.throwsWhenUsingWrongThread);
    }
}
