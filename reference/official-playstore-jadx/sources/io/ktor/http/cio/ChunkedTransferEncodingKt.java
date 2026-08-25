package io.ktor.http.cio;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.pool.DefaultPool;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.charset.Charset;
import k2.c;
import ka.k0;
import ka.n1;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\u001a\u001f\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\t\u001a#\u0010\u0005\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\r\u001a+\u0010\u0005\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0007H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u000e\u001a'\u0010\u0014\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0016\u001a\u0013\u0010\u0017\u001a\u00020\f*\u00020\u0001H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a5\u0010 \u001a\u00020\u001b*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0082@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\"\u0014\u0010!\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\"\"\u0014\u0010#\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b#\u0010\"\"\u001e\u0010'\u001a\f\u0012\b\u0012\u00060%j\u0002`&0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(\"\u0014\u0010*\u001a\u00020)8\u0002X\u0082T¢\u0006\u0006\n\u0004\b*\u0010+\"\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.\"\u0014\u0010/\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010.*\n\u00100\"\u00020\u00032\u00020\u0003*\n\u00101\"\u00020\u00122\u00020\u0012\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/WriterJob;", "Lio/ktor/http/cio/DecoderJob;", "decodeChunked", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;)Lio/ktor/utils/io/WriterJob;", "", "contentLength", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;J)Lio/ktor/utils/io/WriterJob;", "Lio/ktor/utils/io/ByteWriteChannel;", "out", "Lx6/t0;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "output", "Ld7/j;", "coroutineContext", "Lio/ktor/utils/io/ReaderJob;", "Lio/ktor/http/cio/EncoderJob;", "encodeChunked", "(Lio/ktor/utils/io/ByteWriteChannel;Ld7/j;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "rethrowCloseCause", "(Lio/ktor/utils/io/ByteReadChannel;)V", "Lio/ktor/utils/io/bits/Memory;", "memory", "", "startIndex", "endIndex", "writeChunk-yRinSxo", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;IILd7/d;)Ljava/lang/Object;", "writeChunk", "MAX_CHUNK_SIZE_LENGTH", "I", "CHUNK_BUFFER_POOL_SIZE", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "ChunkSizeBufferPool", "Lio/ktor/utils/io/pool/ObjectPool;", "", "CrLfShort", "S", "", "CrLf", "[B", "LastChunkBytes", "DecoderJob", "EncoderJob", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChunkedTransferEncodingKt {
    private static final int CHUNK_BUFFER_POOL_SIZE = 2048;
    private static final ObjectPool<StringBuilder> ChunkSizeBufferPool = new DefaultPool<StringBuilder>() { // from class: io.ktor.http.cio.ChunkedTransferEncodingKt$ChunkSizeBufferPool$1
        @Override // io.ktor.utils.io.pool.DefaultPool
        public StringBuilder clearInstance(StringBuilder instance) {
            instance.setLength(0);
            return instance;
        }

        @Override // io.ktor.utils.io.pool.DefaultPool
        public StringBuilder produceInstance() {
            return new StringBuilder(128);
        }
    };
    private static final byte[] CrLf;
    private static final short CrLfShort = 3338;
    private static final byte[] LastChunkBytes;
    private static final int MAX_CHUNK_SIZE_LENGTH = 128;

    /* JADX INFO: renamed from: io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$1", f = "ChunkedTransferEncoding.kt", l = {47}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ByteReadChannel $input;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ByteReadChannel byteReadChannel, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$input = byteReadChannel;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$input, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                WriterScope writerScope = (WriterScope) this.L$0;
                ByteReadChannel byteReadChannel = this.$input;
                ByteWriteChannel channel = writerScope.getChannel();
                this.label = 1;
                Object objDecodeChunked = ChunkedTransferEncodingKt.decodeChunked(byteReadChannel, channel, this);
                a aVar = a.f15033i;
                if (objDecodeChunked == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.ChunkedTransferEncodingKt", f = "ChunkedTransferEncoding.kt", l = {77, 87, 93}, m = "decodeChunked")
    public static final class AnonymousClass3 extends f7.c {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChunkedTransferEncodingKt.decodeChunked(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/ReaderScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/ReaderScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$2", f = "ChunkedTransferEncoding.kt", l = {126}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ReaderScope, d<? super t0>, Object> {
        final /* synthetic */ ByteWriteChannel $output;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteWriteChannel byteWriteChannel, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$output = byteWriteChannel;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$output, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ReaderScope readerScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(readerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ReaderScope readerScope = (ReaderScope) this.L$0;
                ByteWriteChannel byteWriteChannel = this.$output;
                ByteReadChannel channel = readerScope.getChannel();
                this.label = 1;
                Object objEncodeChunked = ChunkedTransferEncodingKt.encodeChunked(byteWriteChannel, channel, this);
                a aVar = a.f15033i;
                if (objEncodeChunked == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.ChunkedTransferEncodingKt", f = "ChunkedTransferEncoding.kt", l = {181, 137, 185, TsExtractor.TS_PACKET_SIZE, 142}, m = "encodeChunked")
    public static final class C18213 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C18213(d<? super C18213> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChunkedTransferEncodingKt.encodeChunked((ByteWriteChannel) null, (ByteReadChannel) null, this);
        }
    }

    static {
        Charset charset = kotlin.text.a.f19924a;
        CrLf = kotlin.jvm.internal.p.a(charset, charset) ? "\r\n".getBytes(charset) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), "\r\n", 0, 2);
        LastChunkBytes = kotlin.jvm.internal.p.a(charset, charset) ? "0\r\n\r\n".getBytes(charset) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), "0\r\n\r\n", 0, 5);
    }

    public static /* synthetic */ void DecoderJob$annotations() {
    }

    public static /* synthetic */ void EncoderJob$annotations() {
    }

    @x6.e
    public static final WriterJob decodeChunked(k0 k0Var, ByteReadChannel byteReadChannel) {
        return decodeChunked(k0Var, byteReadChannel, -1L);
    }

    public static final Object encodeChunked(ByteWriteChannel byteWriteChannel, d7.j jVar, d<? super ReaderJob> dVar) {
        return CoroutinesKt.reader((k0) n1.f19624i, jVar, false, (p<? super ReaderScope, ? super d<? super t0>, ? extends Object>) new AnonymousClass2(byteWriteChannel, null));
    }

    private static final void rethrowCloseCause(ByteReadChannel byteReadChannel) throws Throwable {
        Throwable closedCause = byteReadChannel instanceof ByteChannel ? byteReadChannel.getClosedCause() : null;
        if (closedCause != null) {
            throw closedCause;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00bf, code lost:
    
        if (io.ktor.utils.io.ByteWriteChannelKt.writeFully(r8, r9, r0) != r6) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: writeChunk-yRinSxo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m6681writeChunkyRinSxo(io.ktor.utils.io.ByteWriteChannel r7, java.nio.ByteBuffer r8, int r9, int r10, d7.d<? super java.lang.Integer> r11) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ChunkedTransferEncodingKt.m6681writeChunkyRinSxo(io.ktor.utils.io.ByteWriteChannel, java.nio.ByteBuffer, int, int, d7.d):java.lang.Object");
    }

    public static final WriterJob decodeChunked(k0 k0Var, ByteReadChannel byteReadChannel, long j10) {
        return CoroutinesKt.writer$default(k0Var, k0Var.getCoroutineContext(), false, (p) new AnonymousClass1(byteReadChannel, null), 2, (Object) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0133, code lost:
    
        if (r0 == r10) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0174, code lost:
    
        if (r0 == r10) goto L76;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bd A[Catch: all -> 0x003f, TRY_LEAVE, TryCatch #7 {all -> 0x003f, blocks: (B:16:0x003a, B:41:0x00b7, B:43:0x00bd, B:74:0x0160), top: B:87:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d7 A[Catch: all -> 0x00df, TRY_LEAVE, TryCatch #6 {all -> 0x00df, blocks: (B:47:0x00d3, B:49:0x00d7), top: B:97:0x00d3 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fe A[Catch: all -> 0x013a, TRY_LEAVE, TryCatch #3 {all -> 0x013a, blocks: (B:53:0x00e3, B:56:0x00fe), top: B:91:0x00e3 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0160 A[Catch: all -> 0x003f, TRY_ENTER, TRY_LEAVE, TryCatch #7 {all -> 0x003f, blocks: (B:16:0x003a, B:41:0x00b7, B:43:0x00bd, B:74:0x0160), top: B:87:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v31, types: [d7.d, io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3] */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [io.ktor.utils.io.core.Buffer] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [d7.d, io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3] */
    /* JADX WARN: Type inference failed for: r1v13, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v23, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v4, types: [d7.d, io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [d7.d, io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [io.ktor.utils.io.ByteReadChannel] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v9, types: [io.ktor.utils.io.core.Buffer, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0133 -> B:29:0x0072). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object encodeChunked(io.ktor.utils.io.ByteWriteChannel r17, io.ktor.utils.io.ByteReadChannel r18, d7.d<? super x6.t0> r19) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ChunkedTransferEncodingKt.encodeChunked(io.ktor.utils.io.ByteWriteChannel, io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    public static final Object decodeChunked(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
        Object objDecodeChunked = decodeChunked(byteReadChannel, byteWriteChannel, -1L, dVar);
        return objDecodeChunked == a.f15033i ? objDecodeChunked : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00eb, code lost:
    
        if (r14 == r6) goto L49;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3 A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:14:0x003c, B:50:0x00ee, B:52:0x00f6, B:31:0x009b, B:33:0x00a3, B:35:0x00a9, B:37:0x00af, B:43:0x00c1, B:46:0x00d4, B:47:0x00d8, B:40:0x00b9, B:63:0x0133, B:64:0x013a, B:65:0x013b, B:66:0x0142, B:59:0x010f, B:60:0x0116, B:61:0x0117, B:62:0x0132, B:21:0x005c, B:24:0x0070), top: B:74:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013b A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:14:0x003c, B:50:0x00ee, B:52:0x00f6, B:31:0x009b, B:33:0x00a3, B:35:0x00a9, B:37:0x00af, B:43:0x00c1, B:46:0x00d4, B:47:0x00d8, B:40:0x00b9, B:63:0x0133, B:64:0x013a, B:65:0x013b, B:66:0x0142, B:59:0x010f, B:60:0x0116, B:61:0x0117, B:62:0x0132, B:21:0x005c, B:24:0x0070), top: B:74:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v20 */
    /* JADX WARN: Type inference failed for: r15v21 */
    /* JADX WARN: Type inference failed for: r15v22 */
    /* JADX WARN: Type inference failed for: r15v23 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4, types: [java.lang.Appendable, java.lang.Object, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6, types: [java.lang.CharSequence, java.lang.Object, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r15v7, types: [java.lang.Appendable, java.lang.Object, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r15v8, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x00eb -> B:50:0x00ee). Please report as a decompilation issue!!! */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object decodeChunked(io.ktor.utils.io.ByteReadChannel r11, io.ktor.utils.io.ByteWriteChannel r12, long r13, d7.d<? super x6.t0> r15) {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ChunkedTransferEncodingKt.decodeChunked(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, d7.d):java.lang.Object");
    }
}
