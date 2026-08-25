package io.sentry.android.core.internal.util;

import android.content.Context;
import io.sentry.ILogger;
import io.sentry.android.core.r0;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final class j {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Charset f16615g = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f16616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0 f16617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ILogger f16618c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f16619d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f16620e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runtime f16621f;

    public j(Context context, ILogger iLogger, r0 r0Var) {
        Runtime runtime = Runtime.getRuntime();
        this.f16616a = context;
        ac.b.V(r0Var, "The BuildInfoProvider is required.");
        this.f16617b = r0Var;
        ac.b.V(iLogger, "The Logger is required.");
        this.f16618c = iLogger;
        this.f16619d = new String[]{"/sbin/su", "/data/local/xbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/bin/su", "/system/app/Superuser.apk", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su", "/su/bin", "/system/xbin/daemonsu"};
        this.f16620e = new String[]{"com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "com.koushikdutta.superuser", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.noshufou.android.su"};
        ac.b.V(runtime, "The Runtime is required.");
        this.f16621f = runtime;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a() {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.util.j.a():boolean");
    }
}
