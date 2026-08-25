package io.ktor.utils.io.jvm.javaio;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/ProhibitParking;", "Lio/ktor/utils/io/jvm/javaio/Parking;", "Ljava/lang/Thread;", "<init>", "()V", "", "fail", "()Ljava/lang/Void;", "token", "()Ljava/lang/Thread;", "", "timeNanos", "Lx6/t0;", "park", "(J)V", "unpark", "(Ljava/lang/Thread;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ProhibitParking implements Parking<Thread> {
    public static final ProhibitParking INSTANCE = new ProhibitParking();

    private ProhibitParking() {
    }

    private final Void fail() {
        throw new UnsupportedOperationException("Parking is prohibited on this thread. Most likely you are using blocking operation on the wrong thread/dispatcher that doesn't allow blocking. Consider wrapping you blocking code withContext(Dispatchers.IO) {...}.");
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void park(long timeNanos) {
        fail();
        throw new KotlinNothingValueException();
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public Thread token() {
        fail();
        throw new KotlinNothingValueException();
    }

    @Override // io.ktor.utils.io.jvm.javaio.Parking
    public void unpark(Thread token) {
        DefaultParking.INSTANCE.unpark(token);
    }
}
