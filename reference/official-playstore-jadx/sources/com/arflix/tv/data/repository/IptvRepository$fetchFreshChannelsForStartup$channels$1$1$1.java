package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1", f = "IptvRepository.kt", l = {2739}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvChannel>>, Object> {
    final /* synthetic */ IptvPlaylistEntry $playlist;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1(IptvRepository iptvRepository, IptvPlaylistEntry iptvPlaylistEntry, d7.d<? super IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$playlist = iptvPlaylistEntry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 invokeSuspend$lambda$0(IptvLoadProgress iptvLoadProgress) {
        return t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1(this.this$0, this.$playlist, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objFetchChannelsForPlaylistWithRetries;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            IptvRepository iptvRepository = this.this$0;
            IptvPlaylistEntry iptvPlaylistEntry = this.$playlist;
            f fVar = new f(4);
            this.label = 1;
            objFetchChannelsForPlaylistWithRetries = iptvRepository.fetchChannelsForPlaylistWithRetries(iptvPlaylistEntry, fVar, this);
            e7.a aVar = e7.a.f15033i;
            if (objFetchChannelsForPlaylistWithRetries == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            objFetchChannelsForPlaylistWithRetries = obj;
        }
        Iterable<IptvChannel> iterable = (Iterable) objFetchChannelsForPlaylistWithRetries;
        IptvPlaylistEntry iptvPlaylistEntry2 = this.$playlist;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        for (IptvChannel iptvChannel : iterable) {
            arrayList.add(IptvChannel.copy$default(iptvChannel, androidx.compose.foundation.c.t(iptvPlaylistEntry2.getId(), ":", iptvChannel.getId()), null, null, iptvChannel.getGroup(), null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, 524278, null));
        }
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvChannel>> dVar) {
        return ((IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
