package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8492i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x6.o f8493l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8494m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8495n;

    public /* synthetic */ x2(Object obj, x6.o oVar, int i10, int i11) {
        this.f8492i = i11;
        this.f8495n = obj;
        this.f8493l = oVar;
        this.f8494m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8492i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.BestMatchStrip$lambda$2((SourcePresentation) this.f8495n, (r7.a) this.f8493l, this.f8494m, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return AppUpdateModalKt.ModalScrim$lambda$3((r7.a) this.f8493l, (r7.q) this.f8495n, this.f8494m, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsRowsKt.MobileSettingsCategory$lambda$1((String) this.f8495n, (r7.q) this.f8493l, this.f8494m, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ x2(r7.a aVar, r7.q qVar, int i10) {
        this.f8492i = 1;
        this.f8493l = aVar;
        this.f8495n = qVar;
        this.f8494m = i10;
    }
}
