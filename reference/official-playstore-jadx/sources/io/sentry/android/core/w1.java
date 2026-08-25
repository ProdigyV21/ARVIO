package io.sentry.android.core;

import android.content.BroadcastReceiver;

/* JADX INFO: loaded from: classes5.dex */
public final class w1 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.c1 f16802a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SentryAndroidOptions f16803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f16804c = new io.sentry.android.core.internal.util.g(60000, 0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final char[] f16805d = new char[64];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ SystemEventsBreadcrumbsIntegration f16806e;

    public w1(SystemEventsBreadcrumbsIntegration systemEventsBreadcrumbsIntegration, io.sentry.c1 c1Var, SentryAndroidOptions sentryAndroidOptions) {
        this.f16806e = systemEventsBreadcrumbsIntegration;
        this.f16802a = c1Var;
        this.f16803b = sentryAndroidOptions;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x008e, code lost:
    
        r2 = r13;
     */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onReceive(android.content.Context r13, android.content.Intent r14) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.w1.onReceive(android.content.Context, android.content.Intent):void");
    }
}
