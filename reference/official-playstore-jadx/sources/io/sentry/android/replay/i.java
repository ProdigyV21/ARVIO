package io.sentry.android.replay;

import java.io.Closeable;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lio/sentry/android/replay/i;", "Ljava/io/Closeable;", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface i extends Closeable {
    void d();

    void g(y yVar);

    void pause();

    void reset();

    void start();

    void stop();
}
