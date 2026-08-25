package io.sentry.android.replay.capture;

import com.google.common.util.concurrent.r0;
import java.util.Comparator;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return r0.e(Long.valueOf(((io.sentry.rrweb.b) obj).f17801l), Long.valueOf(((io.sentry.rrweb.b) obj2).f17801l));
    }
}
