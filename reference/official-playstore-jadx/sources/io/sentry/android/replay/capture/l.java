package io.sentry.android.replay.capture;

import android.view.MotionEvent;
import io.sentry.android.replay.y;
import io.sentry.protocol.v;
import io.sentry.z6;
import java.util.Date;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lio/sentry/android/replay/capture/l;", "", "io/sentry/android/replay/capture/h", "io/sentry/android/replay/capture/k", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface l {
    void a(MotionEvent motionEvent);

    void b(i9.b bVar);

    void c(boolean z, ab.h hVar);

    void d();

    v e();

    void f(int i10);

    void g(y yVar);

    int h();

    l i();

    void j(int i10, v vVar, z6 z6Var);

    void k(Date date);

    void pause();

    void stop();
}
