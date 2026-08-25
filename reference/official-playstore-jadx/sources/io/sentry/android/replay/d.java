package io.sentry.android.replay;

import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.v3;
import j$.util.DesugarCollections;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements v3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f16930c = f4.f.o(3, a.f16864l);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashSet f16931d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f16932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f16933b = DesugarCollections.synchronizedMap(new b());

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("status_code");
        hashSet.add("method");
        hashSet.add("response_content_length");
        hashSet.add("request_content_length");
        hashSet.add("http.response_content_length");
        hashSet.add("http.request_content_length");
        f16931d = hashSet;
    }

    public d(SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.setBeforeBreadcrumb(new a8.e(this, sentryAndroidOptions.getBeforeBreadcrumb(), 24));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:147:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a5  */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, x6.s] */
    @Override // io.sentry.v3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.rrweb.b a(io.sentry.f r10) {
        /*
            Method dump skipped, instruction units count: 747
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.d.a(io.sentry.f):io.sentry.rrweb.b");
    }
}
