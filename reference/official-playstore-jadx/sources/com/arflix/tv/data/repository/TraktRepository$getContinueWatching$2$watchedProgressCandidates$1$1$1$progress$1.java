package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktShowProgress;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TraktShowProgress;", "currentAuth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1", f = "TraktRepository.kt", l = {1552}, m = "invokeSuspend", v = 2)
public final class TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1 extends f7.j implements r7.p<String, d7.d<? super TraktShowProgress>, Object> {
    final /* synthetic */ boolean $includeSpecials;
    final /* synthetic */ int $traktId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1(TraktRepository traktRepository, int i10, boolean z, d7.d<? super TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1> dVar) {
        super(2, dVar);
        this.this$0 = traktRepository;
        this.$traktId = i10;
        this.$includeSpecials = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1 traktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1 = new TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1(this.this$0, this.$traktId, this.$includeSpecials, dVar);
        traktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1.L$0 = obj;
        return traktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1;
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
        TraktApi traktApi = this.this$0.traktApi;
        String str2 = this.this$0.clientId;
        String strValueOf = String.valueOf(this.$traktId);
        String strValueOf2 = String.valueOf(this.$includeSpecials);
        String strValueOf3 = String.valueOf(this.$includeSpecials);
        this.L$0 = null;
        this.label = 1;
        Object showProgress = traktApi.getShowProgress(str, str2, "2", strValueOf, "false", strValueOf2, strValueOf3, this);
        e7.a aVar = e7.a.f15033i;
        return showProgress == aVar ? aVar : showProgress;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super TraktShowProgress> dVar) {
        return ((TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1$1$progress$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
