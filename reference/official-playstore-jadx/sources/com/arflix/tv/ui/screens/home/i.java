package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9152i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f9153l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9154m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f9155n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f9156o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9157p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9158q;

    public /* synthetic */ i(int i10, boolean z, r7.a aVar, boolean z5, int i11, int i12) {
        this.f9153l = i10;
        this.f9154m = z;
        this.f9158q = aVar;
        this.f9155n = z5;
        this.f9156o = i11;
        this.f9157p = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9152i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return HomeScreenKt.TopRankRibbon$lambda$1(this.f9153l, this.f9154m, this.f9155n, (Modifier) this.f9158q, this.f9156o, this.f9157p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ProfileDialogsKt.AvatarGridItem$lambda$10(this.f9153l, this.f9154m, (r7.a) this.f9158q, this.f9155n, this.f9156o, this.f9157p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ i(int i10, boolean z, boolean z5, Modifier modifier, int i11, int i12) {
        this.f9153l = i10;
        this.f9154m = z;
        this.f9155n = z5;
        this.f9158q = modifier;
        this.f9156o = i11;
        this.f9157p = i12;
    }
}
