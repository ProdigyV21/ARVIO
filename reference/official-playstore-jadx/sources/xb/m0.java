package xb;

import java.io.Closeable;
import java.io.Flushable;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Lxb/m0;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface m0 extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    q0 timeout();

    void write(j jVar, long j10);
}
