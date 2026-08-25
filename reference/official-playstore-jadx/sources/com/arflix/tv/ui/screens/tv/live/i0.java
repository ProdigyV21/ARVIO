package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11507i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f11508l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11509m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11510n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f11511o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f11512p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.a f11513q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.l f11514r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.a f11515s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f11516t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f11517u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f11518v;

    public /* synthetic */ i0(List list, boolean z, boolean z5, String str, int i10, r7.a aVar, r7.a aVar2, r7.l lVar, r7.a aVar3, r7.a aVar4, int i11) {
        this.f11507i = 2;
        this.f11509m = list;
        this.f11508l = z;
        this.f11512p = z5;
        this.f11510n = str;
        this.f11517u = i10;
        this.f11513q = aVar;
        this.f11515s = aVar2;
        this.f11514r = lVar;
        this.f11511o = aVar3;
        this.f11516t = aVar4;
        this.f11518v = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f11507i) {
            case 0:
                return FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$0(this.f11508l, (EnrichedChannel) this.f11509m, (IptvNowNext) this.f11510n, (IptvProgram) this.f11511o, this.f11512p, this.f11513q, this.f11514r, this.f11515s, (Modifier) this.f11516t, this.f11517u, this.f11518v, (Composer) obj, ((Integer) obj2).intValue());
            case 1:
                return FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$16(this.f11508l, (EnrichedChannel) this.f11509m, (IptvNowNext) this.f11510n, (IptvProgram) this.f11511o, this.f11512p, this.f11513q, this.f11514r, this.f11515s, (Modifier) this.f11516t, this.f11517u, this.f11518v, (Composer) obj, ((Integer) obj2).intValue());
            default:
                return SettingsScreenKt.HomeServerSettings$lambda$2((List) this.f11509m, this.f11508l, this.f11512p, (String) this.f11510n, this.f11517u, this.f11513q, this.f11515s, this.f11514r, (r7.a) this.f11511o, (r7.a) this.f11516t, this.f11518v, (Composer) obj, ((Integer) obj2).intValue());
        }
    }

    public /* synthetic */ i0(boolean z, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, IptvProgram iptvProgram, boolean z5, r7.a aVar, r7.l lVar, r7.a aVar2, Modifier modifier, int i10, int i11, int i12) {
        this.f11507i = i12;
        this.f11508l = z;
        this.f11509m = enrichedChannel;
        this.f11510n = iptvNowNext;
        this.f11511o = iptvProgram;
        this.f11512p = z5;
        this.f11513q = aVar;
        this.f11514r = lVar;
        this.f11515s = aVar2;
        this.f11516t = modifier;
        this.f11517u = i10;
        this.f11518v = i11;
    }
}
