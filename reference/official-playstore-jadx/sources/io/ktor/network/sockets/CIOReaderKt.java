package io.ktor.network.sockets;

import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import ka.j0;
import ka.k0;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a?\u0010\u0011\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u001f\u0010\u0015\u001a\u00020\u0014*\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a#\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lka/k0;", "Lio/ktor/utils/io/ByteChannel;", "channel", "Ljava/nio/channels/ReadableByteChannel;", "nioChannel", "Lio/ktor/network/selector/Selectable;", "selectable", "Lio/ktor/network/selector/SelectorManager;", "selector", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "socketOptions", "Lio/ktor/utils/io/WriterJob;", "attachForReadingImpl", "(Lka/k0;Lio/ktor/utils/io/ByteChannel;Ljava/nio/channels/ReadableByteChannel;Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectorManager;Lio/ktor/utils/io/pool/ObjectPool;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)Lio/ktor/utils/io/WriterJob;", "attachForReadingDirectImpl", "(Lka/k0;Lio/ktor/utils/io/ByteChannel;Ljava/nio/channels/ReadableByteChannel;Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)Lio/ktor/utils/io/WriterJob;", "Lio/ktor/utils/io/ByteWriteChannel;", "", "readFrom", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/channels/ReadableByteChannel;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "selectForRead", "(Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectorManager;Ld7/d;)Ljava/lang/Object;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CIOReaderKt {

    /* JADX INFO: renamed from: io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1", f = "CIOReader.kt", l = {98, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, 111, 98, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, 111}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ByteChannel $channel;
        final /* synthetic */ ReadableByteChannel $nioChannel;
        final /* synthetic */ Selectable $selectable;
        final /* synthetic */ SelectorManager $selector;
        final /* synthetic */ SocketOptions.TCPClientSocketOptions $socketOptions;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Selectable selectable, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, SelectorManager selectorManager, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$selectable = selectable;
            this.$socketOptions = tCPClientSocketOptions;
            this.$channel = byteChannel;
            this.$nioChannel = readableByteChannel;
            this.$selector = selectorManager;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$selectable, this.$socketOptions, this.$channel, this.$nioChannel, this.$selector, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:72:0x01b4, code lost:
        
            if (r13 != r3) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x01bd, code lost:
        
            if (((java.lang.Number) r13).intValue() != 0) goto L76;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00fc A[Catch: all -> 0x0085, TryCatch #2 {all -> 0x0085, blocks: (B:76:0x01bf, B:37:0x00f4, B:39:0x00fc, B:41:0x0106, B:44:0x011d, B:46:0x0125, B:48:0x012b, B:49:0x012e, B:52:0x0143, B:55:0x0157, B:58:0x0160, B:80:0x01cb, B:81:0x01ce, B:83:0x01d6, B:92:0x01fd, B:77:0x01c5, B:78:0x01c8, B:18:0x0080, B:23:0x009d, B:26:0x00b6, B:29:0x00c2, B:31:0x00ce, B:34:0x00db, B:7:0x0029, B:74:0x01b7, B:68:0x018b, B:71:0x01a1, B:59:0x0163, B:63:0x017a, B:65:0x0182, B:67:0x0188, B:12:0x004a, B:15:0x0067), top: B:108:0x0006, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0125 A[Catch: all -> 0x0085, TryCatch #2 {all -> 0x0085, blocks: (B:76:0x01bf, B:37:0x00f4, B:39:0x00fc, B:41:0x0106, B:44:0x011d, B:46:0x0125, B:48:0x012b, B:49:0x012e, B:52:0x0143, B:55:0x0157, B:58:0x0160, B:80:0x01cb, B:81:0x01ce, B:83:0x01d6, B:92:0x01fd, B:77:0x01c5, B:78:0x01c8, B:18:0x0080, B:23:0x009d, B:26:0x00b6, B:29:0x00c2, B:31:0x00ce, B:34:0x00db, B:7:0x0029, B:74:0x01b7, B:68:0x018b, B:71:0x01a1, B:59:0x0163, B:63:0x017a, B:65:0x0182, B:67:0x0188, B:12:0x004a, B:15:0x0067), top: B:108:0x0006, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0143 A[Catch: all -> 0x0085, PHI: r0 r4 r5 r6 r7
          0x0143: PHI (r0v24 io.ktor.network.selector.SelectorManager) = (r0v15 io.ktor.network.selector.SelectorManager), (r0v25 io.ktor.network.selector.SelectorManager) binds: [B:23:0x009d, B:50:0x013f] A[DONT_GENERATE, DONT_INLINE]
          0x0143: PHI (r4v14 io.ktor.network.selector.Selectable) = (r4v5 io.ktor.network.selector.Selectable), (r4v15 io.ktor.network.selector.Selectable) binds: [B:23:0x009d, B:50:0x013f] A[DONT_GENERATE, DONT_INLINE]
          0x0143: PHI (r5v15 java.nio.channels.ReadableByteChannel) = (r5v6 java.nio.channels.ReadableByteChannel), (r5v16 java.nio.channels.ReadableByteChannel) binds: [B:23:0x009d, B:50:0x013f] A[DONT_GENERATE, DONT_INLINE]
          0x0143: PHI (r6v13 io.ktor.utils.io.ByteChannel) = (r6v4 io.ktor.utils.io.ByteChannel), (r6v14 io.ktor.utils.io.ByteChannel) binds: [B:23:0x009d, B:50:0x013f] A[DONT_GENERATE, DONT_INLINE]
          0x0143: PHI (r7v14 io.ktor.network.util.Timeout) = (r7v5 io.ktor.network.util.Timeout), (r7v15 io.ktor.network.util.Timeout) binds: [B:23:0x009d, B:50:0x013f] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {all -> 0x0085, blocks: (B:76:0x01bf, B:37:0x00f4, B:39:0x00fc, B:41:0x0106, B:44:0x011d, B:46:0x0125, B:48:0x012b, B:49:0x012e, B:52:0x0143, B:55:0x0157, B:58:0x0160, B:80:0x01cb, B:81:0x01ce, B:83:0x01d6, B:92:0x01fd, B:77:0x01c5, B:78:0x01c8, B:18:0x0080, B:23:0x009d, B:26:0x00b6, B:29:0x00c2, B:31:0x00ce, B:34:0x00db, B:7:0x0029, B:74:0x01b7, B:68:0x018b, B:71:0x01a1, B:59:0x0163, B:63:0x017a, B:65:0x0182, B:67:0x0188, B:12:0x004a, B:15:0x0067), top: B:108:0x0006, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0156  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x015f  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0182 A[Catch: all -> 0x002e, TryCatch #3 {all -> 0x002e, blocks: (B:7:0x0029, B:74:0x01b7, B:68:0x018b, B:71:0x01a1, B:59:0x0163, B:63:0x017a, B:65:0x0182, B:67:0x0188, B:12:0x004a, B:15:0x0067), top: B:108:0x0006, outer: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0186  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x01c9  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x0125 -> B:37:0x00f4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0129 -> B:37:0x00f4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x015d -> B:49:0x012e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x015f -> B:37:0x00f4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x0182 -> B:76:0x01bf). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x0186 -> B:76:0x01bf). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x01b4 -> B:74:0x01b7). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 560
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1", f = "CIOReader.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS, TsExtractor.TS_STREAM_TYPE_MHAS, 56}, m = "invokeSuspend")
    public static final class C18371 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ByteBuffer $buffer;
        final /* synthetic */ ByteChannel $channel;
        final /* synthetic */ ReadableByteChannel $nioChannel;
        final /* synthetic */ ObjectPool<ByteBuffer> $pool;
        final /* synthetic */ Selectable $selectable;
        final /* synthetic */ SelectorManager $selector;
        final /* synthetic */ SocketOptions.TCPClientSocketOptions $socketOptions;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18371(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ByteChannel byteChannel, Selectable selectable, ByteBuffer byteBuffer, ObjectPool<ByteBuffer> objectPool, ReadableByteChannel readableByteChannel, SelectorManager selectorManager, d<? super C18371> dVar) {
            super(2, dVar);
            this.$socketOptions = tCPClientSocketOptions;
            this.$channel = byteChannel;
            this.$selectable = selectable;
            this.$buffer = byteBuffer;
            this.$pool = objectPool;
            this.$nioChannel = readableByteChannel;
            this.$selector = selectorManager;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18371 c18371 = new C18371(this.$socketOptions, this.$channel, this.$selectable, this.$buffer, this.$pool, this.$nioChannel, this.$selector, dVar);
            c18371.L$0 = obj;
            return c18371;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((C18371) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00c4 A[Catch: all -> 0x001a, TryCatch #1 {all -> 0x001a, blocks: (B:8:0x0014, B:67:0x018b, B:30:0x00a1, B:33:0x00bc, B:35:0x00c4, B:38:0x00e4, B:51:0x0129, B:53:0x012e, B:55:0x0135, B:64:0x0161, B:41:0x00ea, B:50:0x0124, B:68:0x0192, B:69:0x0195, B:20:0x006b, B:23:0x0078, B:25:0x007c, B:28:0x0089, B:43:0x00f7, B:45:0x00ff, B:48:0x0120, B:15:0x0046), top: B:82:0x0008, inners: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00e8  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00e6 -> B:33:0x00bc). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0122 -> B:43:0x00f7). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x0188 -> B:9:0x0017). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 445
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt.C18371.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.CIOReaderKt$readFrom$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.sockets.CIOReaderKt", f = "CIOReader.kt", l = {TsExtractor.TS_STREAM_TYPE_E_AC3}, m = "readFrom")
    public static final class C18381 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18381(d<? super C18381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOReaderKt.readFrom(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.CIOReaderKt$readFrom$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "buffer", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<ByteBuffer, t0> {
        final /* synthetic */ i0 $count;
        final /* synthetic */ ReadableByteChannel $nioChannel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(i0 i0Var, ReadableByteChannel readableByteChannel) {
            super(1);
            this.$count = i0Var;
            this.$nioChannel = readableByteChannel;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ByteBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(ByteBuffer byteBuffer) {
            this.$count.f19744i = this.$nioChannel.read(byteBuffer);
        }
    }

    public static final WriterJob attachForReadingDirectImpl(k0 k0Var, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        return CoroutinesKt.writer(k0Var, x0.f19654c.plus(new j0("cio-from-nio-reader")), byteChannel, new AnonymousClass1(selectable, tCPClientSocketOptions, byteChannel, readableByteChannel, selectorManager, null));
    }

    public static /* synthetic */ WriterJob attachForReadingDirectImpl$default(k0 k0Var, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForReadingDirectImpl(k0Var, byteChannel, readableByteChannel, selectable, selectorManager, tCPClientSocketOptions);
    }

    public static final WriterJob attachForReadingImpl(k0 k0Var, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool<ByteBuffer> objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        return CoroutinesKt.writer(k0Var, x0.f19654c.plus(new j0("cio-from-nio-reader")), byteChannel, new C18371(tCPClientSocketOptions, byteChannel, selectable, objectPool.borrow(), objectPool, readableByteChannel, selectorManager, null));
    }

    public static /* synthetic */ WriterJob attachForReadingImpl$default(k0 k0Var, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i10, Object obj) {
        if ((i10 & 32) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForReadingImpl(k0Var, byteChannel, readableByteChannel, selectable, selectorManager, objectPool, tCPClientSocketOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFrom(io.ktor.utils.io.ByteWriteChannel r7, java.nio.channels.ReadableByteChannel r8, d7.d<? super java.lang.Integer> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.network.sockets.CIOReaderKt.C18381
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.network.sockets.CIOReaderKt$readFrom$1 r0 = (io.ktor.network.sockets.CIOReaderKt.C18381) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            io.ktor.network.sockets.CIOReaderKt$readFrom$1 r0 = new io.ktor.network.sockets.CIOReaderKt$readFrom$1
            r0.<init>(r9)
            goto L12
        L1a:
            java.lang.Object r9 = r4.result
            int r0 = r4.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            java.lang.Object r7 = r4.L$0
            kotlin.jvm.internal.i0 r7 = (kotlin.jvm.internal.i0) r7
            k2.c.G(r9)
            goto L52
        L2b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L33:
            k2.c.G(r9)
            kotlin.jvm.internal.i0 r9 = new kotlin.jvm.internal.i0
            r9.<init>()
            io.ktor.network.sockets.CIOReaderKt$readFrom$2 r3 = new io.ktor.network.sockets.CIOReaderKt$readFrom$2
            r3.<init>(r9, r8)
            r4.L$0 = r9
            r4.label = r1
            r2 = 0
            r5 = 1
            r6 = 0
            r1 = r7
            java.lang.Object r7 = io.ktor.utils.io.ByteWriteChannel.DefaultImpls.write$default(r1, r2, r3, r4, r5, r6)
            e7.a r8 = e7.a.f15033i
            if (r7 != r8) goto L51
            return r8
        L51:
            r7 = r9
        L52:
            int r7 = r7.f19744i
            java.lang.Integer r8 = new java.lang.Integer
            r8.<init>(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt.readFrom(io.ktor.utils.io.ByteWriteChannel, java.nio.channels.ReadableByteChannel, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object selectForRead(Selectable selectable, SelectorManager selectorManager, d<? super t0> dVar) {
        SelectInterest selectInterest = SelectInterest.READ;
        selectable.interestOp(selectInterest, true);
        Object objSelect = selectorManager.select(selectable, selectInterest, dVar);
        return objSelect == a.f15033i ? objSelect : t0.f22605a;
    }
}
