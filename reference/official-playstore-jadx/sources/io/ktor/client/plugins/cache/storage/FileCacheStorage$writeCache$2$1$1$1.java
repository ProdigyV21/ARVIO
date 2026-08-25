package io.ktor.client.plugins.cache.storage;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteChannel;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2$1$1$1", f = "FileCacheStorage.kt", l = {96, 98}, m = "invokeSuspend")
public final class FileCacheStorage$writeCache$2$1$1$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ List<CachedResponseData> $caches;
    final /* synthetic */ ByteChannel $channel;
    Object L$0;
    int label;
    final /* synthetic */ FileCacheStorage this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileCacheStorage$writeCache$2$1$1$1(ByteChannel byteChannel, List<CachedResponseData> list, FileCacheStorage fileCacheStorage, d<? super FileCacheStorage$writeCache$2$1$1$1> dVar) {
        super(2, dVar);
        this.$channel = byteChannel;
        this.$caches = list;
        this.this$0 = fileCacheStorage;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new FileCacheStorage$writeCache$2$1$1$1(this.$channel, this.$caches, this.this$0, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0031, code lost:
    
        if (r6.writeInt(r0, r5) == r3) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L20
            if (r0 == r2) goto L1c
            if (r0 != r1) goto L14
            java.lang.Object r0 = r5.L$0
            java.util.Iterator r0 = (java.util.Iterator) r0
            k2.c.G(r6)
            goto L3b
        L14:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L1c:
            k2.c.G(r6)
            goto L34
        L20:
            k2.c.G(r6)
            io.ktor.utils.io.ByteChannel r6 = r5.$channel
            java.util.List<io.ktor.client.plugins.cache.storage.CachedResponseData> r0 = r5.$caches
            int r0 = r0.size()
            r5.label = r2
            java.lang.Object r6 = r6.writeInt(r0, r5)
            if (r6 != r3) goto L34
            goto L55
        L34:
            java.util.List<io.ktor.client.plugins.cache.storage.CachedResponseData> r6 = r5.$caches
            java.util.Iterator r6 = r6.iterator()
            r0 = r6
        L3b:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L56
            java.lang.Object r6 = r0.next()
            io.ktor.client.plugins.cache.storage.CachedResponseData r6 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r6
            io.ktor.client.plugins.cache.storage.FileCacheStorage r2 = r5.this$0
            io.ktor.utils.io.ByteChannel r4 = r5.$channel
            r5.L$0 = r0
            r5.label = r1
            java.lang.Object r6 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$writeCache(r2, r4, r6, r5)
            if (r6 != r3) goto L3b
        L55:
            return r3
        L56:
            io.ktor.utils.io.ByteChannel r6 = r5.$channel
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((FileCacheStorage$writeCache$2$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
