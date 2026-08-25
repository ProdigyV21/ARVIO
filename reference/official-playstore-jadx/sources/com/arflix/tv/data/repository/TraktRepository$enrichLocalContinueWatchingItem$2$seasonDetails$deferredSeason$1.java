package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TmdbSeasonDetails;
import com.arflix.tv.util.AppLogger;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1", f = "TraktRepository.kt", l = {2555}, m = "invokeSuspend", v = 2)
public final class TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ String $apiKey;
    final /* synthetic */ ContinueWatchingItem $item;
    final /* synthetic */ ka.r<TmdbSeasonDetails> $newDeferred;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1(TraktRepository traktRepository, ContinueWatchingItem continueWatchingItem, String str, ka.r<TmdbSeasonDetails> rVar, d7.d<? super TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1> dVar) {
        super(2, dVar);
        this.this$0 = traktRepository;
        this.$item = continueWatchingItem;
        this.$apiKey = str;
        this.$newDeferred = rVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1(this.this$0, this.$item, this.$apiKey, this.$newDeferred, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1 traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1;
        TmdbApi tmdbApi;
        int id;
        int iIntValue;
        String str;
        Exception exc;
        TmdbSeasonDetails tmdbSeasonDetails;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            try {
                tmdbApi = this.this$0.tmdbApi;
                id = this.$item.getId();
                iIntValue = this.$item.getSeason().intValue();
                str = this.$apiKey;
                this.label = 1;
                traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1 = this;
            } catch (Exception e5) {
                e = e5;
                traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1 = this;
                exc = e;
                AppLogger.INSTANCE.e("TraktRepository", "Silently returning null", exc);
                tmdbSeasonDetails = null;
            }
            try {
                obj = com.arflix.tv.data.api.g.n(tmdbApi, id, iIntValue, str, null, traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1, 8, null);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } catch (Exception e6) {
                e = e6;
                exc = e;
                AppLogger.INSTANCE.e("TraktRepository", "Silently returning null", exc);
                tmdbSeasonDetails = null;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                k2.c.G(obj);
                traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1 = this;
            } catch (Exception e10) {
                exc = e10;
                traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1 = this;
                AppLogger.INSTANCE.e("TraktRepository", "Silently returning null", exc);
                tmdbSeasonDetails = null;
            }
        }
        tmdbSeasonDetails = (TmdbSeasonDetails) obj;
        traktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1.$newDeferred.R(tmdbSeasonDetails);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TraktRepository$enrichLocalContinueWatchingItem$2$seasonDetails$deferredSeason$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
