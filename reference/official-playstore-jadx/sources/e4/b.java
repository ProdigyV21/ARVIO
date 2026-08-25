package e4;

import android.content.Context;
import android.content.res.Configuration;
import android.media.tv.TvContract;
import android.os.Build;
import c4.d;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f15029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Boolean f15030b;

    /* JADX WARN: Removed duplicated region for block: B:55:0x00de A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00df A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap a(android.app.Activity r11, io.sentry.util.thread.a r12, io.sentry.ILogger r13, io.sentry.android.core.r0 r14) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: e4.b.a(android.app.Activity, io.sentry.util.thread.a, io.sentry.ILogger, io.sentry.android.core.r0):android.graphics.Bitmap");
    }

    public static void b(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        int i10 = configuration.colorMode & 3;
        int i11 = configuration2.colorMode & 3;
        if (i10 != i11) {
            configuration3.colorMode |= i11;
        }
        int i12 = configuration.colorMode & 12;
        int i13 = configuration2.colorMode & 12;
        if (i12 != i13) {
            configuration3.colorMode |= i13;
        }
    }

    public static synchronized boolean c(Context context) {
        Boolean bool;
        Context applicationContext = context.getApplicationContext();
        Context context2 = f15029a;
        if (context2 != null && (bool = f15030b) != null && context2 == applicationContext) {
            return bool.booleanValue();
        }
        f15030b = null;
        if (d.c()) {
            f15030b = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
        } else {
            try {
                context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                f15030b = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                f15030b = Boolean.FALSE;
            }
        }
        f15029a = applicationContext;
        return f15030b.booleanValue();
    }

    public static void d(Context context, long j10) {
        if (Build.VERSION.SDK_INT >= 26) {
            TvContract.requestChannelBrowsable(context, j10);
        }
    }
}
