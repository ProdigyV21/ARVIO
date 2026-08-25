package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.graphics.vector.ImageVector;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8245i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8246l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8247m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8248n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8249o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8250p;

    public /* synthetic */ p(ImageVector imageVector, String str, boolean z, boolean z5, int i10) {
        this.f8249o = imageVector;
        this.f8250p = str;
        this.f8246l = z;
        this.f8247m = z5;
        this.f8248n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8245i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AudioTrackSelectorKt.AudioTrackItem$lambda$1((AudioTrack) this.f8249o, this.f8246l, this.f8247m, (r7.a) this.f8250p, this.f8248n, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return QuickActionMenuKt.QuickActionTile$lambda$1((ImageVector) this.f8249o, (String) this.f8250p, this.f8246l, this.f8247m, this.f8248n, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ p(AudioTrack audioTrack, boolean z, boolean z5, r7.a aVar, int i10) {
        this.f8249o = audioTrack;
        this.f8246l = z;
        this.f8247m = z5;
        this.f8250p = aVar;
        this.f8248n = i10;
    }
}
