package com.arflix.tv.data.telegram;

import com.arflix.tv.data.model.StreamSource;
import f7.j;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.telegram.TelegramSourceResolver$resolve$results$1", f = "TelegramSourceResolver.kt", l = {72}, m = "invokeSuspend", v = 2)
public final class TelegramSourceResolver$resolve$results$1 extends j implements p<k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ Integer $episode;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ boolean $isMovie;
    final /* synthetic */ Integer $season;
    final /* synthetic */ String $title;
    final /* synthetic */ Integer $year;
    int label;
    final /* synthetic */ TelegramSourceResolver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramSourceResolver$resolve$results$1(TelegramSourceResolver telegramSourceResolver, String str, Integer num, Integer num2, Integer num3, String str2, boolean z, d7.d<? super TelegramSourceResolver$resolve$results$1> dVar) {
        super(2, dVar);
        this.this$0 = telegramSourceResolver;
        this.$title = str;
        this.$year = num;
        this.$season = num2;
        this.$episode = num3;
        this.$imdbId = str2;
        this.$isMovie = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TelegramSourceResolver$resolve$results$1(this.this$0, this.$title, this.$year, this.$season, this.$episode, this.$imdbId, this.$isMovie, dVar);
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
        TelegramSourceResolver telegramSourceResolver = this.this$0;
        String str = this.$title;
        Integer num = this.$year;
        Integer num2 = this.$season;
        Integer num3 = this.$episode;
        String str2 = this.$imdbId;
        boolean z = this.$isMovie;
        this.label = 1;
        Object objResolveInternal = telegramSourceResolver.resolveInternal(str, num, num2, num3, str2, z, this);
        e7.a aVar = e7.a.f15033i;
        return objResolveInternal == aVar ? aVar : objResolveInternal;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((TelegramSourceResolver$resolve$results$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
