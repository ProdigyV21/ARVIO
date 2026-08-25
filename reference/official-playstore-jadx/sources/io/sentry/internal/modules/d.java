package io.sentry.internal.modules;

import io.sentry.ILogger;
import io.sentry.w5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d implements a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f17378d = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ILogger f17379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.util.b f17380b = new io.sentry.util.b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Map f17381c = null;

    public d(ILogger iLogger) {
        this.f17379a = iLogger;
    }

    @Override // io.sentry.internal.modules.a
    public final Map a() {
        if (this.f17381c == null) {
            io.sentry.util.a aVarA = this.f17380b.a();
            try {
                if (this.f17381c == null) {
                    this.f17381c = b();
                }
                aVarA.close();
            } catch (Throwable th) {
                try {
                    aVarA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        return this.f17381c;
    }

    public abstract Map b();

    public final TreeMap c(InputStream inputStream) {
        ILogger iLogger = this.f17379a;
        TreeMap treeMap = new TreeMap();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, f17378d));
            try {
                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                    int iLastIndexOf = line.lastIndexOf(58);
                    treeMap.put(line.substring(0, iLastIndexOf), line.substring(iLastIndexOf + 1));
                }
                iLogger.q(w5.DEBUG, "Extracted %d modules from resources.", Integer.valueOf(treeMap.size()));
                bufferedReader.close();
                return treeMap;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e5) {
            iLogger.l(w5.ERROR, "Error extracting modules.", e5);
            return treeMap;
        } catch (RuntimeException e6) {
            iLogger.k(w5.ERROR, e6, "%s file is malformed.", "sentry-external-modules.txt");
            return treeMap;
        }
    }
}
