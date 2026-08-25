package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8548i = 2;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8549l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8550m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8551n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8552o;

    public /* synthetic */ z0(IptvChannel iptvChannel, IptvProgram iptvProgram, IptvProgram iptvProgram2, boolean z) {
        this.f8551n = iptvChannel;
        this.f8549l = iptvProgram;
        this.f8552o = iptvProgram2;
        this.f8550m = z;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8548i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return MediaContextMenuKt.MediaContextMenu$lambda$8((r7.a) this.f8551n, this.f8550m, (String) this.f8549l, (List) this.f8552o, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj3).intValue();
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$1$3((String) this.f8549l, this.f8550m, (r7.a) this.f8551n, (r7.a) this.f8552o, (LazyItemScope) obj, (Composer) obj2, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj3).intValue();
                return TvScreenKt.TvScreen$lambda$118$4$3((IptvChannel) this.f8551n, (IptvProgram) this.f8549l, (IptvProgram) this.f8552o, this.f8550m, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue3);
        }
    }

    public /* synthetic */ z0(String str, boolean z, r7.a aVar, r7.a aVar2) {
        this.f8549l = str;
        this.f8550m = z;
        this.f8551n = aVar;
        this.f8552o = aVar2;
    }

    public /* synthetic */ z0(r7.a aVar, boolean z, String str, z6.c cVar) {
        this.f8551n = aVar;
        this.f8550m = z;
        this.f8549l = str;
        this.f8552o = cVar;
    }
}
