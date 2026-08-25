package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8292i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8293l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f8294m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8295n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8296o;

    public /* synthetic */ q(int i10, Modifier modifier, int i11, int i12, int i13) {
        this.f8292i = i13;
        this.f8293l = i10;
        this.f8294m = modifier;
        this.f8295n = i11;
        this.f8296o = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8292i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AvatarRegistryKt.AvatarIcon$lambda$0(this.f8293l, this.f8294m, this.f8295n, this.f8296o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SkeletonLoaderKt.SkeletonHomePage$lambda$1(this.f8293l, this.f8294m, this.f8295n, this.f8296o, (Composer) obj, iIntValue2);
        }
    }
}
