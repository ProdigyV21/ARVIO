package com.arflix.tv.ui.screens.login;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9353i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f9354l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9355m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f9356n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f9357o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f9358p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Modifier f9359q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f9360r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f9361s;

    public /* synthetic */ d(String str, boolean z, boolean z5, boolean z10, r7.a aVar, Modifier modifier, int i10, int i11) {
        this.f9354l = str;
        this.f9355m = z;
        this.f9356n = z5;
        this.f9357o = z10;
        this.f9358p = aVar;
        this.f9359q = modifier;
        this.f9360r = i10;
        this.f9361s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9353i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                String str = this.f9354l;
                boolean z = this.f9355m;
                boolean z5 = this.f9356n;
                boolean z10 = this.f9357o;
                return LoginScreenKt.GradientButton$lambda$1(this.f9358p, str, z, z5, z10, this.f9359q, this.f9360r, this.f9361s, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ProfileDialogsKt.DialogButton$lambda$9(this.f9354l, this.f9355m, this.f9356n, this.f9357o, this.f9358p, this.f9359q, this.f9360r, this.f9361s, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ d(r7.a aVar, String str, boolean z, boolean z5, boolean z10, Modifier modifier, int i10, int i11) {
        this.f9358p = aVar;
        this.f9354l = str;
        this.f9355m = z;
        this.f9356n = z5;
        this.f9357o = z10;
        this.f9359q = modifier;
        this.f9360r = i10;
        this.f9361s = i11;
    }
}
