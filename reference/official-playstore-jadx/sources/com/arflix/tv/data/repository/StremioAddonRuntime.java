package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.RuntimeKind;
import com.arflix.tv.data.model.StreamSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001Bg\u0012.\u0010\t\u001a*\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002\u0012.\u0010\u000b\u001a*\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0004\b\f\u0010\rJ,\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u000f\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u0010\u0010\u0011J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013R<\u0010\t\u001a*\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0014R<\u0010\u000b\u001a*\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/repository/StremioAddonRuntime;", "Lcom/arflix/tv/data/repository/AddonRuntime;", "Lkotlin/Function3;", "Lcom/arflix/tv/data/model/Addon;", "Lcom/arflix/tv/data/repository/MovieRuntimeRequest;", "Ld7/d;", "", "Lcom/arflix/tv/data/model/StreamSource;", "", "movieResolver", "Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;", "episodeResolver", "<init>", "(Lr7/q;Lr7/q;)V", "addons", "request", "resolveMovieStreams", "(Ljava/util/List;Lcom/arflix/tv/data/repository/MovieRuntimeRequest;Ld7/d;)Ljava/lang/Object;", "resolveEpisodeStreams", "(Ljava/util/List;Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;Ld7/d;)Ljava/lang/Object;", "Lr7/q;", "Lcom/arflix/tv/data/model/RuntimeKind;", "kind", "Lcom/arflix/tv/data/model/RuntimeKind;", "getKind", "()Lcom/arflix/tv/data/model/RuntimeKind;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StremioAddonRuntime implements AddonRuntime {
    public static final int $stable = 8;
    private final r7.q<Addon, EpisodeRuntimeRequest, d7.d<? super List<StreamSource>>, Object> episodeResolver;
    private final RuntimeKind kind = RuntimeKind.STREMIO;
    private final r7.q<Addon, MovieRuntimeRequest, d7.d<? super List<StreamSource>>, Object> movieResolver;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.StremioAddonRuntime$resolveEpisodeStreams$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.StremioAddonRuntime$resolveEpisodeStreams$2", f = "AddonRuntimeImplementations.kt", l = {30}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
        final /* synthetic */ List<Addon> $addons;
        final /* synthetic */ EpisodeRuntimeRequest $request;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ StremioAddonRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(List<Addon> list, StremioAddonRuntime stremioAddonRuntime, EpisodeRuntimeRequest episodeRuntimeRequest, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$addons = list;
            this.this$0 = stremioAddonRuntime;
            this.$request = episodeRuntimeRequest;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$addons, this.this$0, this.$request, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<Addon> list = this.$addons;
                StremioAddonRuntime stremioAddonRuntime = this.this$0;
                EpisodeRuntimeRequest episodeRuntimeRequest = this.$request;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(3, null, k0Var, new StremioAddonRuntime$resolveEpisodeStreams$2$1$1(stremioAddonRuntime, (Addon) it.next(), episodeRuntimeRequest, null)));
                }
                this.L$0 = null;
                this.label = 1;
                obj = ka.m0.f(arrayList, this);
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
            return kotlin.collections.s.V((Iterable) obj);
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.StremioAddonRuntime$resolveMovieStreams$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.StremioAddonRuntime$resolveMovieStreams$2", f = "AddonRuntimeImplementations.kt", l = {21}, m = "invokeSuspend", v = 2)
    public static final class C10402 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
        final /* synthetic */ List<Addon> $addons;
        final /* synthetic */ MovieRuntimeRequest $request;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ StremioAddonRuntime this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C10402(List<Addon> list, StremioAddonRuntime stremioAddonRuntime, MovieRuntimeRequest movieRuntimeRequest, d7.d<? super C10402> dVar) {
            super(2, dVar);
            this.$addons = list;
            this.this$0 = stremioAddonRuntime;
            this.$request = movieRuntimeRequest;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C10402 c10402 = new C10402(this.$addons, this.this$0, this.$request, dVar);
            c10402.L$0 = obj;
            return c10402;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<Addon> list = this.$addons;
                StremioAddonRuntime stremioAddonRuntime = this.this$0;
                MovieRuntimeRequest movieRuntimeRequest = this.$request;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(3, null, k0Var, new StremioAddonRuntime$resolveMovieStreams$2$1$1(stremioAddonRuntime, (Addon) it.next(), movieRuntimeRequest, null)));
                }
                this.L$0 = null;
                this.label = 1;
                obj = ka.m0.f(arrayList, this);
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
            return kotlin.collections.s.V((Iterable) obj);
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
            return ((C10402) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StremioAddonRuntime(r7.q<? super Addon, ? super MovieRuntimeRequest, ? super d7.d<? super List<StreamSource>>, ? extends Object> qVar, r7.q<? super Addon, ? super EpisodeRuntimeRequest, ? super d7.d<? super List<StreamSource>>, ? extends Object> qVar2) {
        this.movieResolver = qVar;
        this.episodeResolver = qVar2;
    }

    @Override // com.arflix.tv.data.repository.AddonRuntime
    public RuntimeKind getKind() {
        return this.kind;
    }

    @Override // com.arflix.tv.data.repository.AddonRuntime
    public Object resolveEpisodeStreams(List<Addon> list, EpisodeRuntimeRequest episodeRuntimeRequest, d7.d<? super List<StreamSource>> dVar) {
        return ka.l0.c(new AnonymousClass2(list, this, episodeRuntimeRequest, null), dVar);
    }

    @Override // com.arflix.tv.data.repository.AddonRuntime
    public Object resolveMovieStreams(List<Addon> list, MovieRuntimeRequest movieRuntimeRequest, d7.d<? super List<StreamSource>> dVar) {
        return ka.l0.c(new C10402(list, this, movieRuntimeRequest, null), dVar);
    }
}
