package androidx.core.app;

import android.os.Build;
import android.widget.RemoteViews;

/* JADX INFO: loaded from: classes3.dex */
public final class l0 extends y0 {
    @Override // androidx.core.app.y0
    public final void apply(t tVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            ((h1) tVar).f1994b.setStyle(k0.a());
        }
    }

    @Override // androidx.core.app.y0
    public final boolean displayCustomViewInline() {
        return true;
    }

    @Override // androidx.core.app.y0
    public final String getClassName() {
        return "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";
    }

    @Override // androidx.core.app.y0
    public final RemoteViews makeBigContentView(t tVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            return null;
        }
        this.mBuilder.getClass();
        this.mBuilder.getClass();
        return null;
    }

    @Override // androidx.core.app.y0
    public final RemoteViews makeContentView(t tVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            return null;
        }
        this.mBuilder.getClass();
        return null;
    }

    @Override // androidx.core.app.y0
    public final RemoteViews makeHeadsUpContentView(t tVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            return null;
        }
        this.mBuilder.getClass();
        this.mBuilder.getClass();
        return null;
    }
}
