package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.layout.BoxScope;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11757i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f11758l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11759m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11760n;

    public /* synthetic */ t(int i10, int i11, Object obj, Object obj2) {
        this.f11757i = i11;
        this.f11758l = i10;
        this.f11759m = obj;
        this.f11760n = obj2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11757i) {
            case 0:
                return ChannelRowKt.ChannelRow_ZSrRDQQ$lambda$14$0(this.f11758l, (r7.a) this.f11759m, (r7.a) this.f11760n);
            default:
                return Boolean.valueOf(EpgGridKt.ProgramsRow_n3xTMnA$lambda$0$6$2$0(this.f11758l, (List) this.f11759m, (BoxScope) this.f11760n));
        }
    }
}
