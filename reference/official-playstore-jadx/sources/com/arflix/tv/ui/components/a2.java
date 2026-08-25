package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.tv.live.ChannelLogoKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7802i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f7803l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7804m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7805n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7806o;

    public /* synthetic */ a2(long j10, int i10, String str, int i11) {
        this.f7806o = str;
        this.f7803l = j10;
        this.f7804m = i10;
        this.f7805n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7802i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ScreensaverKt.ScreensaverHost$lambda$9(this.f7803l, (r7.q) this.f7806o, this.f7804m, this.f7805n, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ChannelLogoKt.SmallTag_iJQMabo$lambda$1((String) this.f7806o, this.f7803l, this.f7804m, this.f7805n, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ a2(long j10, r7.q qVar, int i10, int i11) {
        this.f7803l = j10;
        this.f7806o = qVar;
        this.f7804m = i10;
        this.f7805n = i11;
    }
}
