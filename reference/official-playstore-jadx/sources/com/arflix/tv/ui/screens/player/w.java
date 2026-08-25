package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9783i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f9784l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9785m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f9786n;

    public /* synthetic */ w(int i10, Object obj, MutableState mutableState, int i11) {
        this.f9783i = i11;
        this.f9784l = i10;
        this.f9785m = obj;
        this.f9786n = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9783i) {
            case 0:
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$1$0$1$0(this.f9784l, (r7.l) this.f9785m, (MutableIntState) this.f9786n);
            default:
                return SettingsScreenKt.IptvSettings$lambda$8$1$0$1$0$0(this.f9784l, (MutableState) this.f9785m, this.f9786n);
        }
    }
}
