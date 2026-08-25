package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9487i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f9488l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f9489m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f9490n;

    public /* synthetic */ e1(boolean z, r7.a aVar, r7.a aVar2, int i10) {
        this.f9487i = i10;
        this.f9488l = z;
        this.f9489m = aVar;
        this.f9490n = aVar2;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f9487i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$1$1(this.f9488l, this.f9489m, this.f9490n, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj3).intValue();
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$1$2(this.f9488l, this.f9489m, this.f9490n, (LazyItemScope) obj, (Composer) obj2, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj3).intValue();
                return SettingsScreenKt.HomeServerSettings$lambda$0$1(this.f9488l, this.f9489m, this.f9490n, (ColumnScope) obj, (Composer) obj2, iIntValue3);
            default:
                int iIntValue4 = ((Integer) obj3).intValue();
                return SettingsScreenKt.StremioAddonsSettings$lambda$6$0(this.f9488l, this.f9489m, this.f9490n, (ColumnScope) obj, (Composer) obj2, iIntValue4);
        }
    }
}
