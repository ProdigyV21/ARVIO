package io.sentry.android.core.internal.gestures;

import android.content.res.Resources;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import androidx.core.view.ScrollingView;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements io.sentry.internal.gestures.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.util.k f16523a;

    public a(io.sentry.util.k kVar) {
        this.f16523a = kVar;
    }

    @Override // io.sentry.internal.gestures.a
    public final io.sentry.internal.gestures.c a(View view, io.sentry.internal.gestures.b bVar) {
        if (androidx.compose.material3.d.B(view)) {
            if (bVar == io.sentry.internal.gestures.b.CLICKABLE && view.isClickable() && view.getVisibility() == 0) {
                try {
                    return new io.sentry.internal.gestures.c(view, qb.l.p(view), k.b(view));
                } catch (Resources.NotFoundException unused) {
                    return null;
                }
            }
            if (bVar == io.sentry.internal.gestures.b.SCROLLABLE) {
                boolean z = false;
                if (((!((Boolean) this.f16523a.a()).booleanValue() ? false : ScrollingView.class.isAssignableFrom(view.getClass())) || AbsListView.class.isAssignableFrom(view.getClass()) || ScrollView.class.isAssignableFrom(view.getClass())) && view.getVisibility() == 0) {
                    z = true;
                }
                if (z) {
                    try {
                        return new io.sentry.internal.gestures.c(view, qb.l.p(view), k.b(view));
                    } catch (Resources.NotFoundException unused2) {
                    }
                }
            }
        }
        return null;
    }
}
