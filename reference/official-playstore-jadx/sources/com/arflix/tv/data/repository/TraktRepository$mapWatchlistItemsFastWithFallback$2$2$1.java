package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktWatchlistItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/x;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$mapWatchlistItemsFastWithFallback$2$2$1", f = "TraktRepository.kt", l = {4806, 2987}, m = "invokeSuspend", v = 2)
public final class TraktRepository$mapWatchlistItemsFastWithFallback$2$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ TraktWatchlistItem $item;
    final /* synthetic */ ua.f $semaphore;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$mapWatchlistItemsFastWithFallback$2$2$1(ua.f fVar, int i10, TraktRepository traktRepository, TraktWatchlistItem traktWatchlistItem, d7.d<? super TraktRepository$mapWatchlistItemsFastWithFallback$2$2$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.$index = i10;
        this.this$0 = traktRepository;
        this.$item = traktWatchlistItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktRepository$mapWatchlistItemsFastWithFallback$2$2$1(this.$semaphore, this.$index, this.this$0, this.$item, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        ua.f fVar;
        TraktRepository traktRepository;
        TraktWatchlistItem traktWatchlistItem;
        int i10;
        int i11;
        ua.f fVar2;
        Throwable th;
        Integer num;
        int i12 = this.label;
        e7.a aVar = e7.a.f15033i;
        try {
            if (i12 == 0) {
                k2.c.G(obj);
                fVar = this.$semaphore;
                int i13 = this.$index;
                traktRepository = this.this$0;
                traktWatchlistItem = this.$item;
                this.L$0 = fVar;
                this.L$1 = traktRepository;
                this.L$2 = traktWatchlistItem;
                this.I$0 = i13;
                this.I$1 = 0;
                this.label = 1;
                if (fVar.a(this) != aVar) {
                    i10 = i13;
                    i11 = 0;
                }
                return aVar;
            }
            if (i12 != 1) {
                if (i12 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                num = (Integer) this.L$1;
                fVar2 = (ua.f) this.L$0;
                try {
                    k2.c.G(obj);
                    x6.x xVar = new x6.x(num, obj);
                    fVar2.release();
                    return xVar;
                } catch (Throwable th2) {
                    th = th2;
                    fVar2.release();
                    throw th;
                }
            }
            i11 = this.I$1;
            i10 = this.I$0;
            traktWatchlistItem = (TraktWatchlistItem) this.L$2;
            traktRepository = (TraktRepository) this.L$1;
            ua.f fVar3 = (ua.f) this.L$0;
            k2.c.G(obj);
            fVar = fVar3;
            Integer num2 = new Integer(i10);
            this.L$0 = fVar;
            this.L$1 = num2;
            this.L$2 = null;
            this.I$0 = i11;
            this.I$1 = 0;
            this.label = 2;
            Object objHydrateWatchlistItem = traktRepository.hydrateWatchlistItem(traktWatchlistItem, i10, this);
            if (objHydrateWatchlistItem != aVar) {
                fVar2 = fVar;
                obj = objHydrateWatchlistItem;
                num = num2;
                x6.x xVar2 = new x6.x(num, obj);
                fVar2.release();
                return xVar2;
            }
            return aVar;
        } catch (Throwable th3) {
            fVar2 = fVar;
            th = th3;
            fVar2.release();
            throw th;
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((TraktRepository$mapWatchlistItemsFastWithFallback$2$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
