package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.JikanAnimeData;
import com.arflix.tv.data.api.JikanAnimeResponse;
import com.arflix.tv.data.api.JikanApi;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)D"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$score$1", f = "AnimeScoreRepository.kt", l = {82}, m = "invokeSuspend", v = 2)
public final class AnimeScoreRepository$resolveScore$score$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Double>, Object> {
    final /* synthetic */ int $malId;
    int label;
    final /* synthetic */ AnimeScoreRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimeScoreRepository$resolveScore$score$1(AnimeScoreRepository animeScoreRepository, int i10, d7.d<? super AnimeScoreRepository$resolveScore$score$1> dVar) {
        super(2, dVar);
        this.this$0 = animeScoreRepository;
        this.$malId = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new AnimeScoreRepository$resolveScore$score$1(this.this$0, this.$malId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                JikanApi jikanApi = this.this$0.jikanApi;
                int i11 = this.$malId;
                this.label = 1;
                obj = jikanApi.getAnime(i11, this);
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
            JikanAnimeData data = ((JikanAnimeResponse) obj).getData();
            if (data != null) {
                return data.getScore();
            }
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
        }
        return null;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Double> dVar) {
        return ((AnimeScoreRepository$resolveScore$score$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
