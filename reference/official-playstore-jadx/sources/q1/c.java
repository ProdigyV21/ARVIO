package q1;

import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.widget.RemoteViews;
import androidx.core.app.h1;
import androidx.core.app.t;
import androidx.core.app.y0;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f21323a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public MediaSessionCompat$Token f21324b;

    @Override // androidx.core.app.y0
    public final void apply(t tVar) {
        if (Build.VERSION.SDK_INT >= 34) {
            a.d(((h1) tVar).f1994b, a.b(b.a(a.a(), null, 0, null, Boolean.FALSE), this.f21323a, this.f21324b));
        } else {
            a.d(((h1) tVar).f1994b, a.b(a.a(), this.f21323a, this.f21324b));
        }
    }

    @Override // androidx.core.app.y0
    public final RemoteViews makeBigContentView(t tVar) {
        return null;
    }

    @Override // androidx.core.app.y0
    public final RemoteViews makeContentView(t tVar) {
        return null;
    }
}
