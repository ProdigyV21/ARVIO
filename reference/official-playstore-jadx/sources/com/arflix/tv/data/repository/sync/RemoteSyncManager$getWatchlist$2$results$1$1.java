package com.arflix.tv.data.repository.sync;

import androidx.media3.container.MdtaMetadataEntry;
import d7.d;
import f7.e;
import f7.j;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchlist$2$results$1$1", f = "RemoteSyncManager.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64}, m = "invokeSuspend", v = 2)
public final class RemoteSyncManager$getWatchlist$2$results$1$1 extends j implements p<k0, d<? super RemoteWatchlistResult>, Object> {
    final /* synthetic */ RemoteSyncProvider $provider;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteSyncManager$getWatchlist$2$results$1$1(RemoteSyncProvider remoteSyncProvider, d<? super RemoteSyncManager$getWatchlist$2$results$1$1> dVar) {
        super(2, dVar);
        this.$provider = remoteSyncProvider;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new RemoteSyncManager$getWatchlist$2$results$1$1(this.$provider, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                c.G(obj);
                RemoteSyncProvider remoteSyncProvider = this.$provider;
                this.label = 1;
                obj = remoteSyncProvider.getWatchlist(this);
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
            return (RemoteWatchlistResult) obj;
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super RemoteWatchlistResult> dVar) {
        return ((RemoteSyncManager$getWatchlist$2$results$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
