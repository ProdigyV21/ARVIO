package com.arflix.tv.data.telegram;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramBufferPolicy;", "", "<init>", "()V", "LOW_STORAGE_PREFETCH_BYTES", "", "MIN_PREFETCH_BYTES", "MAX_PREFETCH_BYTES", "DEFAULT_PREFETCH_BYTES", "LOW_STORAGE_THRESHOLD_BYTES", "TARGET_BUFFER_SECONDS", "DEFAULT_ESTIMATED_DURATION_SECONDS", "prefetchBytes", "totalSize", "usableSpace", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramBufferPolicy {
    public static final int $stable = 0;
    private static final long DEFAULT_ESTIMATED_DURATION_SECONDS = 5400;
    public static final long DEFAULT_PREFETCH_BYTES = 8388608;
    public static final TelegramBufferPolicy INSTANCE = new TelegramBufferPolicy();
    public static final long LOW_STORAGE_PREFETCH_BYTES = 2097152;
    public static final long LOW_STORAGE_THRESHOLD_BYTES = 524288000;
    public static final long MAX_PREFETCH_BYTES = 20971520;
    public static final long MIN_PREFETCH_BYTES = 4194304;
    private static final long TARGET_BUFFER_SECONDS = 60;

    private TelegramBufferPolicy() {
    }

    public final long prefetchBytes(long totalSize, long usableSpace) {
        long jO;
        if (usableSpace < LOW_STORAGE_THRESHOLD_BYTES) {
            jO = 2097152;
        } else if (totalSize <= 0) {
            jO = DEFAULT_PREFETCH_BYTES;
        } else {
            long j10 = totalSize / DEFAULT_ESTIMATED_DURATION_SECONDS;
            if (j10 < 1) {
                j10 = 1;
            }
            jO = qb.d.o(j10 * TARGET_BUFFER_SECONDS, 4194304L, MAX_PREFETCH_BYTES);
        }
        return totalSize > 0 ? Math.min(jO, totalSize) : jO;
    }
}
