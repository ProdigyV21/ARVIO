package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.util.HandlerWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class SystemHandlerWrapper implements HandlerWrapper {
    private static final int MAX_POOL_SIZE = 50;
    private static final List<SystemMessage> messagePool = new ArrayList(50);
    private final Handler handler;

    public static final class SystemMessage implements HandlerWrapper.Message {
        private SystemHandlerWrapper handler;
        private Message message;

        private SystemMessage() {
        }

        private void recycle() {
            this.message = null;
            this.handler = null;
            SystemHandlerWrapper.recycleMessage(this);
        }

        @Override // androidx.media3.common.util.HandlerWrapper.Message
        public HandlerWrapper getTarget() {
            SystemHandlerWrapper systemHandlerWrapper = this.handler;
            systemHandlerWrapper.getClass();
            return systemHandlerWrapper;
        }

        public boolean sendAtFrontOfQueue(Handler handler) {
            Message message = this.message;
            message.getClass();
            boolean zSendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(message);
            recycle();
            return zSendMessageAtFrontOfQueue;
        }

        @Override // androidx.media3.common.util.HandlerWrapper.Message
        public void sendToTarget() {
            Message message = this.message;
            message.getClass();
            message.sendToTarget();
            recycle();
        }

        public SystemMessage setMessage(Message message, SystemHandlerWrapper systemHandlerWrapper) {
            this.message = message;
            this.handler = systemHandlerWrapper;
            return this;
        }
    }

    public SystemHandlerWrapper(Handler handler) {
        this.handler = handler;
    }

    private static SystemMessage obtainSystemMessage() {
        SystemMessage systemMessage;
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                systemMessage = list.isEmpty() ? new SystemMessage() : list.remove(list.size() - 1);
            } catch (Throwable th) {
                throw th;
            }
        }
        return systemMessage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recycleMessage(SystemMessage systemMessage) {
        List<SystemMessage> list = messagePool;
        synchronized (list) {
            try {
                if (list.size() < 50) {
                    list.add(systemMessage);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public Looper getLooper() {
        return this.handler.getLooper();
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean hasMessages(int i10) {
        ac.b.j(i10 != 0);
        return this.handler.hasMessages(i10);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i10) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i10), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean post(Runnable runnable) {
        return this.handler.post(runnable);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean postAtFrontOfQueue(Runnable runnable) {
        return this.handler.postAtFrontOfQueue(runnable);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean postDelayed(Runnable runnable, long j10) {
        return this.handler.postDelayed(runnable, j10);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public void removeCallbacks(Runnable runnable) {
        this.handler.removeCallbacks(runnable);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public void removeCallbacksAndMessages(Object obj) {
        this.handler.removeCallbacksAndMessages(obj);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public void removeMessages(int i10) {
        ac.b.j(i10 != 0);
        this.handler.removeMessages(i10);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendEmptyMessage(int i10) {
        return this.handler.sendEmptyMessage(i10);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendEmptyMessageAtTime(int i10, long j10) {
        return this.handler.sendEmptyMessageAtTime(i10, j10);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendEmptyMessageDelayed(int i10, int i11) {
        return this.handler.sendEmptyMessageDelayed(i10, i11);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public boolean sendMessageAtFrontOfQueue(HandlerWrapper.Message message) {
        return ((SystemMessage) message).sendAtFrontOfQueue(this.handler);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i10, Object obj) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i10, obj), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i10, int i11, int i12) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i10, i11, i12), this);
    }

    @Override // androidx.media3.common.util.HandlerWrapper
    public HandlerWrapper.Message obtainMessage(int i10, int i11, int i12, Object obj) {
        return obtainSystemMessage().setMessage(this.handler.obtainMessage(i10, i11, i12, obj), this);
    }
}
