package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7826i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7827l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7828m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7829n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7830o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7831p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7832q;

    public /* synthetic */ b0(String str, String str2, boolean z, Modifier modifier, int i10, int i11) {
        this.f7830o = str;
        this.f7831p = str2;
        this.f7827l = z;
        this.f7832q = modifier;
        this.f7828m = i10;
        this.f7829n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7826i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ContextMenuKt.SeasonContextMenu$lambda$1(this.f7827l, this.f7828m, (r7.a) this.f7830o, (r7.a) this.f7831p, (r7.a) this.f7832q, this.f7829n, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SourceInfoOverlayKt.PlayerInfoBar$lambda$1((String) this.f7830o, (String) this.f7831p, this.f7827l, (Modifier) this.f7832q, this.f7828m, this.f7829n, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ b0(boolean z, int i10, r7.a aVar, r7.a aVar2, r7.a aVar3, int i11) {
        this.f7827l = z;
        this.f7828m = i10;
        this.f7830o = aVar;
        this.f7831p = aVar2;
        this.f7832q = aVar3;
        this.f7829n = i11;
    }
}
