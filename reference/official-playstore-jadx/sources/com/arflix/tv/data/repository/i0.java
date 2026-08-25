package com.arflix.tv.data.repository;

import androidx.compose.foundation.lazy.LazyListScope;
import com.arflix.tv.data.api.TmdbMediaItem;
import com.arflix.tv.ui.screens.details.DetailsScreenKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7648i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7649l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7650m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7651n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7652o;

    public /* synthetic */ i0(TraktRepository traktRepository, String str, Integer num, boolean z) {
        this.f7650m = traktRepository;
        this.f7651n = str;
        this.f7652o = num;
        this.f7649l = z;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7648i) {
            case 0:
                return TraktRepository.searchTmdbWatchlistMatch$lambda$1((TraktRepository) this.f7650m, (String) this.f7651n, (Integer) this.f7652o, this.f7649l, (TmdbMediaItem) obj);
            default:
                return DetailsScreenKt.DetailsContent$lambda$16$0$7$0((List) this.f7650m, (Map) this.f7651n, this.f7649l, (r7.l) this.f7652o, (LazyListScope) obj);
        }
    }

    public /* synthetic */ i0(List list, Map map, boolean z, r7.l lVar) {
        this.f7650m = list;
        this.f7651n = map;
        this.f7649l = z;
        this.f7652o = lVar;
    }
}
