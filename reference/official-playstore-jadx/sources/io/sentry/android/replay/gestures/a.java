package io.sentry.android.replay.gestures;

import android.view.MotionEvent;
import android.view.Window;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.capture.l;
import io.sentry.android.replay.r;
import io.sentry.android.replay.s;
import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends io.sentry.android.replay.util.c {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y6 f16964l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile ReplayIntegration f16965m;

    public a(y6 y6Var, ReplayIntegration replayIntegration, Window.Callback callback) {
        super(callback);
        this.f16964l = y6Var;
        this.f16965m = replayIntegration;
    }

    @Override // io.sentry.android.replay.util.c, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        l lVar;
        if (motionEvent != null) {
            MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
            try {
                ReplayIntegration replayIntegration = this.f16965m;
                if (replayIntegration != null && replayIntegration.f16860u.get()) {
                    r rVar = replayIntegration.A;
                    if ((((s) rVar.f16996a) == s.STARTED || ((s) rVar.f16996a) == s.RESUMED) && (lVar = replayIntegration.f16862w) != null) {
                        lVar.a(motionEventObtainNoHistory);
                    }
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
