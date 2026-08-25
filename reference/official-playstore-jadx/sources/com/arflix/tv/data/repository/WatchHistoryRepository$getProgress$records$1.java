package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.WatchHistoryRecord;
import com.arflix.tv.data.model.MediaType;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/WatchHistoryRecord;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.WatchHistoryRepository$getProgress$records$1", f = "WatchHistoryRepository.kt", l = {369}, m = "invokeSuspend", v = 2)
public final class WatchHistoryRepository$getProgress$records$1 extends f7.j implements r7.p<String, d7.d<? super List<? extends WatchHistoryRecord>>, Object> {
    final /* synthetic */ Integer $episode;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ Integer $season;
    final /* synthetic */ int $tmdbId;
    final /* synthetic */ String $userId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WatchHistoryRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchHistoryRepository$getProgress$records$1(WatchHistoryRepository watchHistoryRepository, String str, int i10, MediaType mediaType, Integer num, Integer num2, d7.d<? super WatchHistoryRepository$getProgress$records$1> dVar) {
        super(2, dVar);
        this.this$0 = watchHistoryRepository;
        this.$userId = str;
        this.$tmdbId = i10;
        this.$mediaType = mediaType;
        this.$season = num;
        this.$episode = num2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        WatchHistoryRepository$getProgress$records$1 watchHistoryRepository$getProgress$records$1 = new WatchHistoryRepository$getProgress$records$1(this.this$0, this.$userId, this.$tmdbId, this.$mediaType, this.$season, this.$episode, dVar);
        watchHistoryRepository$getProgress$records$1.L$0 = obj;
        return watchHistoryRepository$getProgress$records$1;
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
        String strCurrentProfileQuery = this.this$0.currentProfileQuery();
        String strI = a0.c.i(this.$tmdbId, "eq.");
        String strConcat = "eq.".concat(this.$mediaType == MediaType.MOVIE ? "movie" : "tv");
        Integer num = this.$season;
        String strI2 = num != null ? a0.c.i(num.intValue(), "eq.") : null;
        Integer num2 = this.$episode;
        String strI3 = num2 != null ? a0.c.i(num2.intValue(), "eq.") : null;
        this.L$0 = null;
        this.label = 1;
        Object objM = com.arflix.tv.data.api.f.m(supabaseApi, str, null, strC, strCurrentProfileQuery, strI, strConcat, null, strI2, strI3, null, null, null, this, 3586, null);
        e7.a aVar = e7.a.f15033i;
        return objM == aVar ? aVar : objM;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super List<WatchHistoryRecord>> dVar) {
        return ((WatchHistoryRepository$getProgress$records$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
