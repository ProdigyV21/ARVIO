package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.repository.ContinueWatchingItem;
import d7.d;
import f7.e;
import f7.j;
import java.util.List;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.z;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$getContinueWatching$2$1$1", f = "RemoteSyncManager.kt", l = {198}, m = "invokeSuspend", v = 2)
public final class RemoteSyncManager$getContinueWatching$2$1$1 extends j implements p<k0, d<? super List<? extends ContinueWatchingItem>>, Object> {
    final /* synthetic */ boolean $forceRefresh;
    final /* synthetic */ RemoteSyncProvider $provider;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteSyncManager$getContinueWatching$2$1$1(RemoteSyncProvider remoteSyncProvider, boolean z, d<? super RemoteSyncManager$getContinueWatching$2$1$1> dVar) {
        super(2, dVar);
        this.$provider = remoteSyncProvider;
        this.$forceRefresh = z;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RemoteSyncManager$getContinueWatching$2$1$1(this.$provider, this.$forceRefresh, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                c.G(obj);
                RemoteSyncProvider remoteSyncProvider = this.$provider;
                boolean z = this.$forceRefresh;
                this.label = 1;
                obj = remoteSyncProvider.getContinueWatching(z, this);
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
            return (List) obj;
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
            return z.f19728i;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super List<ContinueWatchingItem>> dVar) {
        return ((RemoteSyncManager$getContinueWatching$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
