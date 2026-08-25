package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.CloudSyncStatus;
import com.arflix.tv.ui.screens.watchlist.HomeLibraryUiState;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7814i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7815l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7816m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7817n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f7818o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f7819p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f7820q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7821r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f7822s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f7823t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f7824u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f7825v;

    public /* synthetic */ b(SidebarItem sidebarItem, boolean z, int i10, Profile profile, int i11, String str, CloudSyncStatus cloudSyncStatus, boolean z5, Modifier modifier, int i12, int i13) {
        this.f7821r = sidebarItem;
        this.f7818o = z;
        this.f7815l = i10;
        this.f7822s = profile;
        this.f7816m = i11;
        this.f7823t = str;
        this.f7824u = cloudSyncStatus;
        this.f7819p = z5;
        this.f7825v = modifier;
        this.f7817n = i12;
        this.f7820q = i13;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7814i) {
            case 0:
                return AppTopBarKt.AppTopBar$lambda$2((SidebarItem) this.f7821r, this.f7818o, this.f7815l, (Profile) this.f7822s, this.f7816m, (String) this.f7823t, (CloudSyncStatus) this.f7824u, this.f7819p, (Modifier) this.f7825v, this.f7817n, this.f7820q, (Composer) obj, ((Integer) obj2).intValue());
            default:
                return WatchlistScreenKt.ProviderTabs$lambda$1((List) this.f7821r, this.f7815l, this.f7816m, (HomeLibraryUiState) this.f7822s, (List) this.f7823t, this.f7817n, this.f7818o, this.f7819p, (r7.l) this.f7824u, (r7.l) this.f7825v, this.f7820q, (Composer) obj, ((Integer) obj2).intValue());
        }
    }

    public /* synthetic */ b(List list, int i10, int i11, HomeLibraryUiState homeLibraryUiState, List list2, int i12, boolean z, boolean z5, r7.l lVar, r7.l lVar2, int i13) {
        this.f7821r = list;
        this.f7815l = i10;
        this.f7816m = i11;
        this.f7822s = homeLibraryUiState;
        this.f7823t = list2;
        this.f7817n = i12;
        this.f7818o = z;
        this.f7819p = z5;
        this.f7824u = lVar;
        this.f7825v = lVar2;
        this.f7820q = i13;
    }
}
