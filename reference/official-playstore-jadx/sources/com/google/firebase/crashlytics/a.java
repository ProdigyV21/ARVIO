package com.google.firebase.crashlytics;

import android.os.Bundle;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import m5.a;
import m5.b;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements BreadcrumbSource, AnalyticsEventLogger, a.InterfaceC0243a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ AnalyticsDeferredProxy f14346i;

    public /* synthetic */ a(AnalyticsDeferredProxy analyticsDeferredProxy) {
        this.f14346i = analyticsDeferredProxy;
    }

    @Override // m5.a.InterfaceC0243a
    public void h(b bVar) {
        this.f14346i.lambda$init$2(bVar);
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
    public void logEvent(String str, Bundle bundle) {
        this.f14346i.lambda$getAnalyticsEventLogger$1(str, bundle);
    }

    @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource
    public void registerBreadcrumbHandler(BreadcrumbHandler breadcrumbHandler) {
        this.f14346i.lambda$getDeferredBreadcrumbSource$0(breadcrumbHandler);
    }
}
