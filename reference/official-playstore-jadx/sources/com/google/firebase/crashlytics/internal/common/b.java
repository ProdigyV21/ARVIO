package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14352i;

    public /* synthetic */ b(int i10) {
        this.f14352i = i10;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f14352i) {
            case 0:
                return CrashlyticsAppQualitySessionsStore.lambda$static$1((File) obj, (File) obj2);
            default:
                return SessionReportingCoordinator.lambda$getSortedCustomAttributes$0((CrashlyticsReport.CustomAttribute) obj, (CrashlyticsReport.CustomAttribute) obj2);
        }
    }
}
