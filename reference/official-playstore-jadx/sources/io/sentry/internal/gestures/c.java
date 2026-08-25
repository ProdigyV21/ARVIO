package io.sentry.internal.gestures;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f17369a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17370b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f17371c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f17372d = "old_view_system";

    public c(View view, String str, String str2) {
        this.f17369a = new WeakReference(view);
        this.f17370b = str;
        this.f17371c = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return ac.b.C(this.f17370b, cVar.f17370b) && ac.b.C(this.f17371c, cVar.f17371c) && ac.b.C(null, null);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17369a, this.f17371c, null});
    }
}
