package android.support.v4.media.session;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.RenderNode;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class q {
    public static /* synthetic */ BlendModeColorFilter a(int i10, BlendMode blendMode) {
        return new BlendModeColorFilter(i10, blendMode);
    }

    public static /* synthetic */ RenderNode b() {
        return new RenderNode("Compose");
    }

    public static /* synthetic */ MediaSession c(Context context) {
        return new MediaSession(context, "CastMediaSession", null);
    }

    public static /* synthetic */ MediaSession d(Context context, String str, Bundle bundle) {
        return new MediaSession(context, str, bundle);
    }

    public static /* synthetic */ WindowInsets.Builder e() {
        return new WindowInsets.Builder();
    }

    public static /* synthetic */ WindowInsets.Builder f(WindowInsets windowInsets) {
        return new WindowInsets.Builder(windowInsets);
    }

    public static /* synthetic */ void g() {
    }
}
