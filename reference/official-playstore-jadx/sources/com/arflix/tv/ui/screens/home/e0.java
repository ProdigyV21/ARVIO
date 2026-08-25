package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.profile.PinEntryDialogKt;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.profile.ProfileViewModel;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9123i = 3;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9124l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9125m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9126n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f9127o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9128p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9129q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f9130r;

    public /* synthetic */ e0(ExoPlayer exoPlayer, EnrichedChannel enrichedChannel, boolean z, r7.a aVar, Modifier modifier, int i10, int i11) {
        this.f9129q = exoPlayer;
        this.f9124l = enrichedChannel;
        this.f9125m = z;
        this.f9126n = aVar;
        this.f9130r = modifier;
        this.f9127o = i10;
        this.f9128p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9123i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return HomeScreenKt.HeroSection$lambda$2((MediaItem) this.f9129q, (String) this.f9124l, (String) this.f9126n, this.f9125m, (Modifier) this.f9130r, this.f9127o, this.f9128p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PinEntryDialogKt.PinEntryDialog$lambda$14((String) this.f9124l, (r7.l) this.f9129q, (r7.a) this.f9130r, this.f9125m, (String) this.f9126n, this.f9127o, this.f9128p, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$14((ProfileViewModel) this.f9129q, (r7.a) this.f9124l, (r7.a) this.f9126n, (r7.a) this.f9130r, this.f9125m, this.f9127o, this.f9128p, (Composer) obj, iIntValue3);
            default:
                int iIntValue4 = ((Integer) obj2).intValue();
                return MiniPlayerKt.VideoCard$lambda$2((ExoPlayer) this.f9129q, (EnrichedChannel) this.f9124l, this.f9125m, (r7.a) this.f9126n, (Modifier) this.f9130r, this.f9127o, this.f9128p, (Composer) obj, iIntValue4);
        }
    }

    public /* synthetic */ e0(MediaItem mediaItem, String str, String str2, boolean z, Modifier modifier, int i10, int i11) {
        this.f9129q = mediaItem;
        this.f9124l = str;
        this.f9126n = str2;
        this.f9125m = z;
        this.f9130r = modifier;
        this.f9127o = i10;
        this.f9128p = i11;
    }

    public /* synthetic */ e0(ProfileViewModel profileViewModel, r7.a aVar, r7.a aVar2, r7.a aVar3, boolean z, int i10, int i11) {
        this.f9129q = profileViewModel;
        this.f9124l = aVar;
        this.f9126n = aVar2;
        this.f9130r = aVar3;
        this.f9125m = z;
        this.f9127o = i10;
        this.f9128p = i11;
    }

    public /* synthetic */ e0(String str, r7.l lVar, r7.a aVar, boolean z, String str2, int i10, int i11) {
        this.f9124l = str;
        this.f9129q = lVar;
        this.f9130r = aVar;
        this.f9125m = z;
        this.f9126n = str2;
        this.f9127o = i10;
        this.f9128p = i11;
    }
}
