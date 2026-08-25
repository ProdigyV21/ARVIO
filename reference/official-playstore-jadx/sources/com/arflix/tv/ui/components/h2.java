package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8046i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8047l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8048m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8049n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f8050o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8051p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8052q;

    public /* synthetic */ h2(SidebarItem sidebarItem, boolean z, boolean z5, boolean z10, int i10, int i11) {
        this.f8052q = sidebarItem;
        this.f8048m = z;
        this.f8049n = z5;
        this.f8050o = z10;
        this.f8047l = i10;
        this.f8051p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8046i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SidebarKt.SidebarIcon$lambda$5((SidebarItem) this.f8052q, this.f8048m, this.f8049n, this.f8050o, this.f8047l, this.f8051p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PlayerScreenKt.LangPanelItem$lambda$1((String) this.f8052q, this.f8047l, this.f8048m, this.f8049n, this.f8050o, this.f8051p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ h2(String str, int i10, boolean z, boolean z5, boolean z10, int i11) {
        this.f8052q = str;
        this.f8047l = i10;
        this.f8048m = z;
        this.f8049n = z5;
        this.f8050o = z10;
        this.f8051p = i11;
    }
}
