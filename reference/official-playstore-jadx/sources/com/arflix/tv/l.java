package com.arflix.tv;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.ui.screens.details.DetailsScreenKt;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import ka.k0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7757i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7758l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7759m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7760n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7761o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7762p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7763q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7764r;

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, int i10) {
        this.f7757i = i10;
        this.f7758l = obj;
        this.f7759m = obj2;
        this.f7760n = obj3;
        this.f7761o = obj4;
        this.f7762p = obj5;
        this.f7763q = obj6;
        this.f7764r = obj7;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7757i) {
            case 0:
                return MainActivityKt.ArflixApp$lambda$20$0$0$0((k0) this.f7758l, (TraktRepository) this.f7759m, (WatchHistoryRepository) this.f7760n, (WatchlistRepository) this.f7761o, (IptvRepository) this.f7762p, (ProfileManager) this.f7763q, (ProfileRepository) this.f7764r);
            default:
                return DetailsScreenKt.DetailsScreen$lambda$97$9$0$0((Episode) this.f7758l, (MutableState) this.f7759m, (State) this.f7760n, (DetailsViewModel) this.f7761o, (MutableState) this.f7762p, (MutableIntState) this.f7763q, (MutableState) this.f7764r);
        }
    }
}
