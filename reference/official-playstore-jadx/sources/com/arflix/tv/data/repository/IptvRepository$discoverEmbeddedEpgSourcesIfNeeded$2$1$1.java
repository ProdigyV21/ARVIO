package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$discoverEmbeddedEpgSourcesIfNeeded$2$1$1", f = "IptvRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class IptvRepository$discoverEmbeddedEpgSourcesIfNeeded$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends String>>, Object> {
    final /* synthetic */ IptvPlaylistEntry $playlist;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$discoverEmbeddedEpgSourcesIfNeeded$2$1$1(IptvRepository iptvRepository, IptvPlaylistEntry iptvPlaylistEntry, d7.d<? super IptvRepository$discoverEmbeddedEpgSourcesIfNeeded$2$1$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$playlist = iptvPlaylistEntry;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$discoverEmbeddedEpgSourcesIfNeeded$2$1$1(this.this$0, this.$playlist, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.this$0.discoverM3uHeaderEpgUrls(this.$playlist.getM3uUrl());
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<String>> dVar) {
        return ((IptvRepository$discoverEmbeddedEpgSourcesIfNeeded$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
