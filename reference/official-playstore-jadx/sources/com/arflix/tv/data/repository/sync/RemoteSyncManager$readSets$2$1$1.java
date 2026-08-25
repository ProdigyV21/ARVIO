package com.arflix.tv.data.repository.sync;

import d7.d;
import f7.e;
import f7.j;
import java.util.Set;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.b0;
import r7.p;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/util/Set;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$readSets$2$1$1", f = "RemoteSyncManager.kt", l = {182}, m = "invokeSuspend", v = 2)
public final class RemoteSyncManager$readSets$2$1$1<T> extends j implements p<k0, d<? super Set<? extends T>>, Object> {
    final /* synthetic */ p<RemoteSyncProvider, d<? super Set<? extends T>>, Object> $block;
    final /* synthetic */ RemoteSyncProvider $provider;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RemoteSyncManager$readSets$2$1$1(p<? super RemoteSyncProvider, ? super d<? super Set<? extends T>>, ? extends Object> pVar, RemoteSyncProvider remoteSyncProvider, d<? super RemoteSyncManager$readSets$2$1$1> dVar) {
        super(2, dVar);
        this.$block = pVar;
        this.$provider = remoteSyncProvider;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RemoteSyncManager$readSets$2$1$1(this.$block, this.$provider, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                c.G(obj);
                p<RemoteSyncProvider, d<? super Set<? extends T>>, Object> pVar = this.$block;
                RemoteSyncProvider remoteSyncProvider = this.$provider;
                this.label = 1;
                obj = pVar.invoke(remoteSyncProvider, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return (Set) obj;
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
            return b0.f19686i;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super Set<? extends T>> dVar) {
        return ((RemoteSyncManager$readSets$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
