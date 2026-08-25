package io.sentry.android.core.performance;

import android.view.Window;
import io.sentry.android.core.internal.gestures.l;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.media3.exoplayer.source.preload.b f16724l;

    public i(Window.Callback callback, androidx.media3.exoplayer.source.preload.b bVar) {
        super(callback);
        this.f16724l = bVar;
    }

    @Override // io.sentry.android.core.internal.gestures.l, android.view.Window.Callback
    public final void onContentChanged() {
        super.onContentChanged();
        this.f16724l.run();
    }
}
