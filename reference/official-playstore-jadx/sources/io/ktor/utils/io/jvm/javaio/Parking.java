package io.ktor.utils.io.jvm.javaio;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001J\u000f\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/Parking;", "", "T", "token", "()Ljava/lang/Object;", "", "timeNanos", "Lx6/t0;", "park", "(J)V", "unpark", "(Ljava/lang/Object;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Parking<T> {
    void park(long timeNanos);

    T token();

    void unpark(T token);
}
