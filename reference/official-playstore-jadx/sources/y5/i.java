package y5;

import android.net.Uri;
import com.google.android.gms.cast.CredentialsData;
import d7.k;
import java.net.URL;
import ka.m0;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c1.e f22902c = new c1.e("firebase_sessions_enabled");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c1.e f22903d = new c1.e("firebase_sessions_sampling_rate");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c1.e f22904e = new c1.e("firebase_sessions_restart_timeout");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c1.e f22905f = new c1.e("firebase_sessions_cache_duration");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c1.e f22906g = new c1.e("firebase_sessions_cache_updated_time");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f22907a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f22908b;

    public i(w5.b bVar, d7.j jVar) {
        this.f22907a = bVar;
        this.f22908b = jVar;
    }

    public static final URL a(i iVar) {
        Uri.Builder builderAppendPath = new Uri.Builder().scheme("https").authority("firebase-settings.crashlytics.com").appendPath("spi").appendPath("v2").appendPath("platforms").appendPath(CredentialsData.CREDENTIALS_TYPE_ANDROID).appendPath("gmp");
        w5.b bVar = (w5.b) iVar.f22907a;
        Uri.Builder builderAppendPath2 = builderAppendPath.appendPath(bVar.f22382a).appendPath("settings");
        w5.a aVar = bVar.f22383b;
        return new URL(builderAppendPath2.appendQueryParameter("build_version", aVar.f22377c).appendQueryParameter("display_version", aVar.f22376b).build().toString());
    }

    public boolean b() {
        d dVar = (d) this.f22908b;
        if (dVar == null) {
            p.i("sessionConfigs");
            throw null;
        }
        Long l10 = dVar.f22889e;
        if (dVar != null) {
            Integer num = dVar.f22888d;
            return l10 == null || num == null || (System.currentTimeMillis() - l10.longValue()) / ((long) 1000) >= ((long) num.intValue());
        }
        p.i("sessionConfigs");
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object c(c1.e r5, java.lang.Object r6, f7.c r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof y5.h
            if (r0 == 0) goto L13
            r0 = r7
            y5.h r0 = (y5.h) r0
            int r1 = r0.f22901m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f22901m = r1
            goto L18
        L13:
            y5.h r0 = new y5.h
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.f22899i
            int r1 = r0.f22901m
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r7)     // Catch: java.io.IOException -> L25
            goto L5a
        L25:
            r5 = move-exception
            goto L47
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            k2.c.G(r7)
            java.lang.Object r7 = r4.f22907a     // Catch: java.io.IOException -> L25
            z0.g r7 = (z0.g) r7     // Catch: java.io.IOException -> L25
            androidx.navigation.compose.v r1 = new androidx.navigation.compose.v     // Catch: java.io.IOException -> L25
            r3 = 0
            r1.<init>(r6, r5, r4, r3)     // Catch: java.io.IOException -> L25
            r0.f22901m = r2     // Catch: java.io.IOException -> L25
            java.lang.Object r5 = androidx.work.impl.t.o(r7, r1, r0)     // Catch: java.io.IOException -> L25
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L5a
            return r6
        L47:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Failed to update cache config value: "
            r6.<init>(r7)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            java.lang.String r6 = "SettingsCache"
            android.util.Log.w(r6, r5)
        L5a:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: y5.i.c(c1.e, java.lang.Object, f7.c):java.lang.Object");
    }

    public i(z0.g gVar) throws Throwable {
        this.f22907a = gVar;
        m0.s(k.f14688i, new androidx.work.impl.constraints.controllers.c(this, null, 8));
    }
}
