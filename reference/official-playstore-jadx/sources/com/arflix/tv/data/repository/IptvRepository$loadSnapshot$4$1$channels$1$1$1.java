package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/Object;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$loadSnapshot$4$1$channels$1$1$1", f = "IptvRepository.kt", l = {1664, 1680}, m = "invokeSuspend", v = 2)
public final class IptvRepository$loadSnapshot$4$1$channels$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Object>, Object> {
    final /* synthetic */ r7.p<List<IptvChannel>, d7.d<? super t0>, Object> $onChannelsReady;
    final /* synthetic */ r7.l<IptvLoadProgress, t0> $onProgress;
    final /* synthetic */ IptvPlaylistEntry $playlist;
    final /* synthetic */ int $playlistIndex;
    final /* synthetic */ List<IptvChannel>[] $playlistResults;
    final /* synthetic */ Object $playlistResultsLock;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$loadSnapshot$4$1$channels$1$1$1(IptvRepository iptvRepository, IptvPlaylistEntry iptvPlaylistEntry, r7.l<? super IptvLoadProgress, t0> lVar, Object obj, List<IptvChannel>[] listArr, int i10, r7.p<? super List<IptvChannel>, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super IptvRepository$loadSnapshot$4$1$channels$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$playlist = iptvPlaylistEntry;
        this.$onProgress = lVar;
        this.$playlistResultsLock = obj;
        this.$playlistResults = listArr;
        this.$playlistIndex = i10;
        this.$onChannelsReady = pVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$loadSnapshot$4$1$channels$1$1$1 iptvRepository$loadSnapshot$4$1$channels$1$1$1 = new IptvRepository$loadSnapshot$4$1$channels$1$1$1(this.this$0, this.$playlist, this.$onProgress, this.$playlistResultsLock, this.$playlistResults, this.$playlistIndex, this.$onChannelsReady, dVar);
        iptvRepository$loadSnapshot$4$1$channels$1$1$1.L$0 = obj;
        return iptvRepository$loadSnapshot$4$1$channels$1$1$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ea, code lost:
    
        if (r0.invoke(r5, r29) == r2) goto L41;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvRepository$loadSnapshot$4$1$channels$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<Object> dVar) {
        return ((IptvRepository$loadSnapshot$4$1$channels$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
