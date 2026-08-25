package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10564i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10565l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x6.o f10566m;

    public /* synthetic */ i1(Object obj, x6.o oVar, int i10) {
        this.f10564i = i10;
        this.f10565l = obj;
        this.f10566m = oVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f10564i) {
            case 0:
                return SettingsScreenKt.CatalogsSettings$lambda$6$1((r7.a) this.f10565l, (r7.a) this.f10566m, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return SettingsScreenKt.IptvSettings$lambda$8$2((String) this.f10565l, (r7.l) this.f10566m, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }
}
