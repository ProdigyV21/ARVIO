package com.arflix.tv.data.repository;

import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.ArmApi;
import com.arflix.tv.data.api.ArmEntry;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)I"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$resolved$1", f = "AnimeScoreRepository.kt", l = {TsExtractor.TS_SYNC_BYTE}, m = "invokeSuspend", v = 2)
public final class AnimeScoreRepository$resolveMalId$resolved$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Integer>, Object> {
    final /* synthetic */ String $imdbId;
    int label;
    final /* synthetic */ AnimeScoreRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimeScoreRepository$resolveMalId$resolved$1(AnimeScoreRepository animeScoreRepository, String str, d7.d<? super AnimeScoreRepository$resolveMalId$resolved$1> dVar) {
        super(2, dVar);
        this.this$0 = animeScoreRepository;
        this.$imdbId = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new AnimeScoreRepository$resolveMalId$resolved$1(this.this$0, this.$imdbId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                ArmApi armApi = this.this$0.armApi;
                String str = this.$imdbId;
                this.label = 1;
                obj = com.arflix.tv.data.api.b.a(armApi, str, null, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ArmEntry armEntry = (ArmEntry) kotlin.collections.x.o0((List) obj);
            if (armEntry != null) {
                return armEntry.getMyanimelist();
            }
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
        }
        return null;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Integer> dVar) {
        return ((AnimeScoreRepository$resolveMalId$resolved$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
