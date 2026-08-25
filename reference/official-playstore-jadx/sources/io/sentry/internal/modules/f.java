package io.sentry.internal.modules;

import android.content.Context;
import androidx.activity.n;
import io.sentry.ILogger;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.w5;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f17383e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f17384f;

    public f(List list, ILogger iLogger) {
        super(iLogger);
        this.f17384f = list;
    }

    @Override // io.sentry.internal.modules.d
    public final Map b() {
        int i10 = this.f17383e;
        ILogger iLogger = this.f17379a;
        Object obj = this.f17384f;
        switch (i10) {
            case 0:
                TreeMap treeMap = new TreeMap();
                try {
                    InputStream resourceAsStream = ((ClassLoader) obj).getResourceAsStream("sentry-external-modules.txt");
                    try {
                        if (resourceAsStream == null) {
                            iLogger.q(w5.INFO, "%s file was not found.", "sentry-external-modules.txt");
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                        } else {
                            TreeMap treeMapC = c(resourceAsStream);
                            resourceAsStream.close();
                            treeMap = treeMapC;
                        }
                    } catch (Throwable th) {
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            break;
                        }
                        throw th;
                    }
                    break;
                } catch (IOException e5) {
                    iLogger.l(w5.INFO, "Access to resources failed.", e5);
                } catch (SecurityException e6) {
                    iLogger.l(w5.INFO, "Access to resources denied.", e6);
                }
                return treeMap;
            case 1:
                TreeMap treeMap2 = new TreeMap();
                try {
                    InputStream inputStreamOpen = ((Context) obj).getAssets().open("sentry-external-modules.txt");
                    try {
                        TreeMap treeMapC2 = c(inputStreamOpen);
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                        }
                        return treeMapC2;
                    } catch (Throwable th3) {
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                            break;
                        }
                        throw th3;
                    }
                } catch (FileNotFoundException unused) {
                    iLogger.q(w5.INFO, "%s file was not found.", "sentry-external-modules.txt");
                    return treeMap2;
                } catch (IOException e10) {
                    iLogger.l(w5.ERROR, "Error extracting modules.", e10);
                    return treeMap2;
                }
            default:
                TreeMap treeMap3 = new TreeMap();
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    Map mapA = ((a) it.next()).a();
                    if (mapA != null) {
                        treeMap3.putAll(mapA);
                    }
                }
                return treeMap3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ILogger iLogger) {
        super(iLogger);
        ClassLoader classLoader = f.class.getClassLoader();
        this.f17384f = f4.f.e(classLoader);
    }

    public f(Context context, SentryAndroidOptions sentryAndroidOptions) {
        super(sentryAndroidOptions.getLogger());
        Context applicationContext = context.getApplicationContext();
        this.f17384f = applicationContext != null ? applicationContext : context;
        try {
            sentryAndroidOptions.getExecutorService().submit(new n(this, 20));
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "AssetsModulesLoader submit failed", th);
        }
    }
}
