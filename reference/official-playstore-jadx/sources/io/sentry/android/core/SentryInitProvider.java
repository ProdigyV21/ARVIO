package io.sentry.android.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import io.sentry.s4;
import io.sentry.u5;
import io.sentry.w5;

/* JADX INFO: loaded from: classes5.dex */
public final class SentryInitProvider extends v0 {
    @Override // android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        if (SentryInitProvider.class.getName().equals(providerInfo.authority)) {
            throw new IllegalStateException("An applicationId is required to fulfill the manifest placeholder.");
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        Bundle bundle;
        v vVar = new v(3);
        Context context = getContext();
        if (context == null) {
            vVar.q(w5.FATAL, "App. Context from ContentProvider is null", new Object[0]);
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Build.VERSION.SDK_INT >= 33 ? (ApplicationInfo) q0.f16730d.a(context) : (ApplicationInfo) q0.f16731e.a(context);
            bundle = applicationInfo != null ? applicationInfo.metaData : null;
        } catch (Throwable th) {
            vVar.l(w5.ERROR, "Failed to read auto-init from android manifest metadata.", th);
        }
        boolean zB = bundle != null ? b1.b(bundle, vVar, "io.sentry.auto-init", true) : true;
        if (zB && !q0.c(context)) {
            k1.c(context, vVar, new o0(6));
            u5.d().a("AutoInit");
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public final void shutdown() {
        s4.a();
    }
}
