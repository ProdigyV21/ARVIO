package androidx.media3.common;

import android.view.SurfaceView;

/* JADX INFO: loaded from: classes3.dex */
public interface DebugViewProvider {
    public static final DebugViewProvider NONE = new androidx.credentials.d(23);

    SurfaceView getDebugPreviewSurfaceView(int i10, int i11);
}
