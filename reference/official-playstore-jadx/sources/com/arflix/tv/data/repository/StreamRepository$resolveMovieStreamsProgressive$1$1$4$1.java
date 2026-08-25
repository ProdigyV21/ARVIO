package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$resolveMovieStreamsProgressive$1$1$4$1", f = "StreamRepository.kt", l = {2365, 4385, 2383}, m = "invokeSuspend", v = 2)
public final class StreamRepository$resolveMovieStreamsProgressive$1$1$4$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ ma.u<ProgressiveStreamResult> $$this$callbackFlow;
    final /* synthetic */ Addon $addon;
    final /* synthetic */ List<StreamSource> $aggregatedStreams;
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ kotlin.jvm.internal.i0 $completed;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ ua.a $mutex;
    final /* synthetic */ String $profileId;
    final /* synthetic */ int $totalAddons;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public StreamRepository$resolveMovieStreamsProgressive$1$1$4$1(StreamRepository streamRepository, Addon addon, String str, ua.a aVar, List<StreamSource> list, kotlin.jvm.internal.i0 i0Var, int i10, String str2, String str3, ma.u<? super ProgressiveStreamResult> uVar, d7.d<? super StreamRepository$resolveMovieStreamsProgressive$1$1$4$1> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$addon = addon;
        this.$imdbId = str;
        this.$mutex = aVar;
        this.$aggregatedStreams = list;
        this.$completed = i0Var;
        this.$totalAddons = i10;
        this.$profileId = str2;
        this.$cacheKey = str3;
        this.$$this$callbackFlow = uVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$resolveMovieStreamsProgressive$1$1$4$1(this.this$0, this.$addon, this.$imdbId, this.$mutex, this.$aggregatedStreams, this.$completed, this.$totalAddons, this.$profileId, this.$cacheKey, this.$$this$callbackFlow, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x013b  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$resolveMovieStreamsProgressive$1$1$4$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((StreamRepository$resolveMovieStreamsProgressive$1$1$4$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
