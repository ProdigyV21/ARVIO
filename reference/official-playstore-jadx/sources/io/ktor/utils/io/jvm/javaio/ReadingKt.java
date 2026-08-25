package io.ktor.utils.io.jvm.javaio;

import com.arflix.tv.util.Constants;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import ka.k0;
import ka.n1;
import ka.x0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a)\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\r\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\r\u0010\u000e\u001a-\u0010\r\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u000f0\tH\u0007¢\u0006\u0004\b\u0010\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Ljava/io/InputStream;", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "", "limit", "copyTo", "(Ljava/io/InputStream;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "Ld7/j;", "context", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/utils/io/ByteReadChannel;", "toByteReadChannel", "(Ljava/io/InputStream;Ld7/j;Lio/ktor/utils/io/pool/ObjectPool;)Lio/ktor/utils/io/ByteReadChannel;", "", "toByteReadChannelWithArrayPool", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReadingKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.javaio.ReadingKt$copyTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.jvm.javaio.ReadingKt", f = "Reading.kt", l = {29}, m = "copyTo")
    public static final class AnonymousClass1 extends c {
        int I$0;
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReadingKt.copyTo(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$1", f = "Reading.kt", l = {61}, m = "invokeSuspend")
    public static final class C20501 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ObjectPool<ByteBuffer> $pool;
        final /* synthetic */ InputStream $this_toByteReadChannel;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C20501(ObjectPool<ByteBuffer> objectPool, InputStream inputStream, d<? super C20501> dVar) {
            super(2, dVar);
            this.$pool = objectPool;
            this.$this_toByteReadChannel = inputStream;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C20501 c20501 = new C20501(this.$pool, this.$this_toByteReadChannel, dVar);
            c20501.L$0 = obj;
            return c20501;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((C20501) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IOException {
            ByteBuffer byteBufferBorrow;
            WriterScope writerScope;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
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
                    k2.c.G(obj);
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

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$2", f = "Reading.kt", l = {Constants.WATCHED_THRESHOLD}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ObjectPool<byte[]> $pool;
        final /* synthetic */ InputStream $this_toByteReadChannel;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ObjectPool<byte[]> objectPool, InputStream inputStream, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$pool = objectPool;
            this.$this_toByteReadChannel = inputStream;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$pool, this.$this_toByteReadChannel, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IOException {
            byte[] bArrBorrow;
            WriterScope writerScope;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                WriterScope writerScope2 = (WriterScope) this.L$0;
                bArrBorrow = this.$pool.borrow();
                writerScope = writerScope2;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                bArrBorrow = (byte[]) this.L$1;
                writerScope = (WriterScope) this.L$0;
                try {
                    k2.c.G(obj);
                } catch (Throwable th) {
                    try {
                        writerScope.getChannel().close(th);
                        this.$pool.recycle(bArrBorrow);
                    } catch (Throwable th2) {
                        this.$pool.recycle(bArrBorrow);
                        this.$this_toByteReadChannel.close();
                        throw th2;
                    }
                }
            }
            while (true) {
                int i11 = this.$this_toByteReadChannel.read(bArrBorrow, 0, bArrBorrow.length);
                if (i11 < 0) {
                    this.$pool.recycle(bArrBorrow);
                    break;
                }
                if (i11 != 0) {
                    ByteWriteChannel channel = writerScope.getChannel();
                    this.L$0 = writerScope;
                    this.L$1 = bArrBorrow;
                    this.label = 1;
                    Object objWriteFully = channel.writeFully(bArrBorrow, 0, i11, this);
                    a aVar = a.f15033i;
                    if (objWriteFully == aVar) {
                        return aVar;
                    }
                }
            }
            this.$this_toByteReadChannel.close();
            return t0.f22605a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x009b -> B:35:0x00a0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(java.io.InputStream r17, io.ktor.utils.io.ByteWriteChannel r18, long r19, d7.d<? super java.lang.Long> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.ReadingKt.copyTo(java.io.InputStream, io.ktor.utils.io.ByteWriteChannel, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(InputStream inputStream, ByteWriteChannel byteWriteChannel, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyTo(inputStream, byteWriteChannel, j10, dVar);
    }

    public static final ByteReadChannel toByteReadChannel(InputStream inputStream, d7.j jVar, ObjectPool<ByteBuffer> objectPool) {
        return CoroutinesKt.writer((k0) n1.f19624i, jVar, true, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new C20501(objectPool, inputStream, null)).getChannel();
    }

    public static ByteReadChannel toByteReadChannel$default(InputStream inputStream, d7.j jVar, ObjectPool objectPool, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = x0.f19655d;
        }
        return toByteReadChannel(inputStream, jVar, objectPool);
    }

    public static final ByteReadChannel toByteReadChannelWithArrayPool(InputStream inputStream, d7.j jVar, ObjectPool<byte[]> objectPool) {
        return CoroutinesKt.writer((k0) n1.f19624i, jVar, true, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new AnonymousClass2(objectPool, inputStream, null)).getChannel();
    }

    public static ByteReadChannel toByteReadChannelWithArrayPool$default(InputStream inputStream, d7.j jVar, ObjectPool objectPool, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = x0.f19655d;
        }
        if ((i10 & 2) != 0) {
            objectPool = ByteArrayPoolKt.getByteArrayPool();
        }
        return toByteReadChannelWithArrayPool(inputStream, jVar, objectPool);
    }
}
