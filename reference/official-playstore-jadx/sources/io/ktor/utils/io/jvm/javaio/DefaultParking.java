package io.ktor.utils.io.jvm.javaio;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/DefaultParking;", "Lio/ktor/utils/io/jvm/javaio/Parking;", "Ljava/lang/Thread;", "<init>", "()V", "token", "()Ljava/lang/Thread;", "", "timeNanos", "Lx6/t0;", "park", "(J)V", "unpark", "(Ljava/lang/Thread;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultParking implements Parking<Thread> {
    public static final DefaultParking INSTANCE = new DefaultParking();

    private DefaultParking() {
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void park(long timeNanos) {
        if (timeNanos < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        LockSupport.parkNanos(timeNanos);
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public Thread token() {
        return Thread.currentThread();
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void unpark(Thread token) {
        LockSupport.unpark(token);
    }
}
