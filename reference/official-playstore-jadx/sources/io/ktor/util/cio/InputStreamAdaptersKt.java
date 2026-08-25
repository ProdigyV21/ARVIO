package io.ktor.util.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\t\u001a\u00020\b*\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Ljava/io/InputStream;", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "Ld7/j;", "context", "Lka/v1;", "parent", "Lio/ktor/utils/io/ByteReadChannel;", "toByteReadChannel", "(Ljava/io/InputStream;Lio/ktor/utils/io/pool/ObjectPool;Ld7/j;Lka/v1;)Lio/ktor/utils/io/ByteReadChannel;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputStreamAdaptersKt {

    /* JADX INFO: renamed from: io.ktor.util.cio.InputStreamAdaptersKt$toByteReadChannel$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.cio.InputStreamAdaptersKt$toByteReadChannel$1", f = "InputStreamAdapters.kt", l = {34}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ObjectPool<ByteBuffer> $pool;
        final /* synthetic */ InputStream $this_toByteReadChannel;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ObjectPool<ByteBuffer> objectPool, InputStream inputStream, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$pool = objectPool;
            this.$this_toByteReadChannel = inputStream;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$pool, this.$this_toByteReadChannel, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IOException {
            ByteBuffer byteBufferBorrow;
            WriterScope writerScope;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                WriterScope writerScope2 = (WriterScope) this.L$0;
                byteBufferBorrow = this.$pool.borrow();
                writerScope = writerScope2;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                byteBufferBorrow = (ByteBuffer) this.L$1;
                writerScope = (WriterScope) this.L$0;
                try {
                    c.G(obj);
                } finally {
                    try {
                    } finally {
                    }
                }
            }
            while (true) {
                byteBufferBorrow.clear();
                int i11 = this.$this_toByteReadChannel.read(byteBufferBorrow.array(), byteBufferBorrow.arrayOffset() + byteBufferBorrow.position(), byteBufferBorrow.remaining());
                if (i11 < 0) {
                    break;
                }
                if (i11 != 0) {
                    byteBufferBorrow.position(byteBufferBorrow.position() + i11);
                    byteBufferBorrow.flip();
                    ByteWriteChannel channel = writerScope.getChannel();
                    this.L$0 = writerScope;
                    this.L$1 = byteBufferBorrow;
                    this.label = 1;
                    Object objWriteFully = channel.writeFully(byteBufferBorrow, this);
                    a aVar = a.f15033i;
                    if (objWriteFully == aVar) {
                        return aVar;
                    }
                }
            }
            return t0.f22605a;
        }
    }

    public static final ByteReadChannel toByteReadChannel(InputStream inputStream, ObjectPool<ByteBuffer> objectPool, d7.j jVar, v1 v1Var) {
        return CoroutinesKt.writer((k0) l0.a(jVar), (d7.j) v1Var, true, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new AnonymousClass1(objectPool, inputStream, null)).getChannel();
    }

    public static ByteReadChannel toByteReadChannel$default(InputStream inputStream, ObjectPool objectPool, d7.j jVar, v1 v1Var, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            objectPool = ByteBufferPoolKt.getKtorDefaultPool();
        }
        if ((i10 & 2) != 0) {
            jVar = x0.f19654c;
        }
        if ((i10 & 4) != 0) {
            v1Var = m0.c();
        }
        return toByteReadChannel(inputStream, objectPool, jVar, v1Var);
    }
}
