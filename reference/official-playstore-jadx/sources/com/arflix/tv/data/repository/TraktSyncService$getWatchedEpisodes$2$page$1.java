package com.arflix.tv.data.repository;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.WatchedEpisodeRecord;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/WatchedEpisodeRecord;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$getWatchedEpisodes$2$page$1", f = "TraktSyncService.kt", l = {AnalyticsListener.EVENT_AUDIO_DECODER_RELEASED}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$getWatchedEpisodes$2$page$1 extends f7.j implements r7.p<String, d7.d<? super List<? extends WatchedEpisodeRecord>>, Object> {
    final /* synthetic */ kotlin.jvm.internal.i0 $offset;
    final /* synthetic */ int $pageSize;
    final /* synthetic */ String $userId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$getWatchedEpisodes$2$page$1(TraktSyncService traktSyncService, String str, kotlin.jvm.internal.i0 i0Var, int i10, d7.d<? super TraktSyncService$getWatchedEpisodes$2$page$1> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$userId = str;
        this.$offset = i0Var;
        this.$pageSize = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$getWatchedEpisodes$2$page$1 traktSyncService$getWatchedEpisodes$2$page$1 = new TraktSyncService$getWatchedEpisodes$2$page$1(this.this$0, this.$userId, this.$offset, this.$pageSize, dVar);
        traktSyncService$getWatchedEpisodes$2$page$1.L$0 = obj;
        return traktSyncService$getWatchedEpisodes$2$page$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        SupabaseApi supabaseApi = this.this$0.supabaseApi;
        String strC = androidx.compose.material3.d.C("eq.", this.$userId);
        String strC2 = androidx.compose.material3.d.C("eq.", this.this$0.activeProfileId());
        int i11 = this.$offset.f19744i;
        int i12 = this.$pageSize;
        this.L$0 = null;
        this.label = 1;
        Object objN = com.arflix.tv.data.api.f.n(supabaseApi, str, null, strC, strC2, null, null, i11, i12, this, 50, null);
        e7.a aVar = e7.a.f15033i;
        return objN == aVar ? aVar : objN;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super List<WatchedEpisodeRecord>> dVar) {
        return ((TraktSyncService$getWatchedEpisodes$2$page$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
