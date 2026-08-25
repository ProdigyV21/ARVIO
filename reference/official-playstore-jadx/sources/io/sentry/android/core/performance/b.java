package io.sentry.android.core.performance;

import android.os.Looper;
import com.google.android.gms.cast.MediaTrack;
import io.sentry.k1;
import io.sentry.r1;
import io.sentry.z4;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z4 f16697b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public z4 f16698c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k1 f16699d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public k1 f16700e = null;

    public b(String str) {
        this.f16696a = str;
    }

    public static k1 a(k1 k1Var, String str, z4 z4Var) {
        k1 k1VarB = k1Var.b(str, z4Var, r1.SENTRY);
        k1VarB.j(Long.valueOf(io.sentry.android.core.internal.util.e.d(Looper.getMainLooper().getThread())), "thread.id");
        k1VarB.j(MediaTrack.ROLE_MAIN, "thread.name");
        Boolean bool = Boolean.TRUE;
        k1VarB.j(bool, "ui.contributes_to_ttid");
        k1VarB.j(bool, "ui.contributes_to_ttfd");
        return k1VarB;
    }
}
