package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktWatchedShow;
import com.arflix.tv.data.repository.TraktRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/api/TraktWatchedShow;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$watchedShowsDeferred$1", f = "TraktRepository.kt", l = {1434}, m = "invokeSuspend", v = 2)
public final class TraktRepository$getContinueWatching$2$watchedShowsDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends TraktWatchedShow>>, Object> {
    final /* synthetic */ String[] $authHolder;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$watchedShowsDeferred$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/TraktWatchedShow;", "currentAuth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$watchedShowsDeferred$1$1", f = "TraktRepository.kt", l = {1435}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<String, d7.d<? super List<? extends TraktWatchedShow>>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TraktRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TraktRepository traktRepository, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = traktRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
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
            TraktRepository traktRepository = this.this$0;
            this.L$0 = null;
            this.label = 1;
            Object allWatchedShows = traktRepository.getAllWatchedShows(str, this);
            e7.a aVar = e7.a.f15033i;
            return allWatchedShows == aVar ? aVar : allWatchedShows;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<? super List<TraktWatchedShow>> dVar) {
            return ((AnonymousClass1) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$getContinueWatching$2$watchedShowsDeferred$1(TraktRepository traktRepository, String[] strArr, d7.d<? super TraktRepository$getContinueWatching$2$watchedShowsDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = traktRepository;
        this.$authHolder = strArr;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktRepository$getContinueWatching$2$watchedShowsDeferred$1(this.this$0, this.$authHolder, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        String[] strArr = this.$authHolder;
        TraktRepository traktRepository = this.this$0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(traktRepository, null);
        this.label = 1;
        Object objInvokeSuspend$traktCallWithAuthRetry = TraktRepository.C10782.invokeSuspend$traktCallWithAuthRetry(strArr, traktRepository, "watched shows", anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objInvokeSuspend$traktCallWithAuthRetry == aVar ? aVar : objInvokeSuspend$traktCallWithAuthRetry;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<TraktWatchedShow>> dVar) {
        return ((TraktRepository$getContinueWatching$2$watchedShowsDeferred$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
