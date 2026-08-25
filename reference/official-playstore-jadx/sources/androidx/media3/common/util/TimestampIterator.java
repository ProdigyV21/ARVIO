package androidx.media3.common.util;

/* JADX INFO: loaded from: classes3.dex */
public interface TimestampIterator {
    TimestampIterator copyOf();

    long getLastTimestampUs();

    boolean hasNext();

    long next();
}
