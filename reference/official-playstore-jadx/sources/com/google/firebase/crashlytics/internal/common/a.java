package com.google.firebase.crashlytics.internal.common;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14351a;

    public /* synthetic */ a(int i10) {
        this.f14351a = i10;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        switch (this.f14351a) {
            case 0:
                return CrashlyticsAppQualitySessionsStore.lambda$static$0(file, str);
            default:
                return CrashlyticsController.lambda$static$0(file, str);
        }
    }
}
