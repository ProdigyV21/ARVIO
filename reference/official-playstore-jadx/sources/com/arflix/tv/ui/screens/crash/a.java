package com.arflix.tv.ui.screens.crash;

import androidx.compose.runtime.Composer;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8684i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8685l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8686m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f8687n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ String f8688o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ CrashReportActivity f8689p;

    public /* synthetic */ a(String str, String str2, long j10, String str3, CrashReportActivity crashReportActivity, int i10) {
        this.f8684i = i10;
        this.f8685l = str;
        this.f8686m = str2;
        this.f8687n = j10;
        this.f8688o = str3;
        this.f8689p = crashReportActivity;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8684i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return CrashReportActivity.onCreate$lambda$0(this.f8685l, this.f8686m, this.f8687n, this.f8688o, this.f8689p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return CrashReportActivity.onCreate$lambda$0$0(this.f8685l, this.f8686m, this.f8687n, this.f8688o, this.f8689p, (Composer) obj, iIntValue2);
        }
    }
}
