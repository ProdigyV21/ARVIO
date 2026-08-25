package io.ktor.network.sockets;

import androidx.media3.container.NalUnitUtil;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.LookAheadSuspendSession;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import k2.c;
import ka.j0;
import ka.k0;
import ka.x0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aM\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a?\u0010\u0011\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lka/k0;", "Lio/ktor/utils/io/ByteChannel;", "channel", "Ljava/nio/channels/WritableByteChannel;", "nioChannel", "Lio/ktor/network/selector/Selectable;", "selectable", "Lio/ktor/network/selector/SelectorManager;", "selector", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "socketOptions", "Lio/ktor/utils/io/ReaderJob;", "attachForWritingImpl", "(Lka/k0;Lio/ktor/utils/io/ByteChannel;Ljava/nio/channels/WritableByteChannel;Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectorManager;Lio/ktor/utils/io/pool/ObjectPool;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)Lio/ktor/utils/io/ReaderJob;", "attachForWritingDirectImpl", "(Lka/k0;Lio/ktor/utils/io/ByteChannel;Ljava/nio/channels/WritableByteChannel;Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)Lio/ktor/utils/io/ReaderJob;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CIOWriterKt {

    /* JADX INFO: renamed from: io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/ReaderScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/ReaderScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1", f = "CIOWriter.kt", l = {88}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<ReaderScope, d<? super t0>, Object> {
        final /* synthetic */ ByteChannel $channel;
        final /* synthetic */ WritableByteChannel $nioChannel;
        final /* synthetic */ Selectable $selectable;
        final /* synthetic */ SelectorManager $selector;
        final /* synthetic */ SocketOptions.TCPClientSocketOptions $socketOptions;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSuspendSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/LookAheadSuspendSession;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1", f = "CIOWriter.kt", l = {100, 112, 112}, m = "invokeSuspend")
        public static final class C02311 extends j implements p<LookAheadSuspendSession, d<? super t0>, Object> {
            final /* synthetic */ ReaderScope $$this$reader;
            final /* synthetic */ ByteChannel $channel;
            final /* synthetic */ WritableByteChannel $nioChannel;
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
            public C02311(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ReaderScope readerScope, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, d<? super C02311> dVar) {
                super(2, dVar);
                this.$socketOptions = tCPClientSocketOptions;
                this.$$this$reader = readerScope;
                this.$channel = byteChannel;
                this.$nioChannel = writableByteChannel;
                this.$selectable = selectable;
                this.$selector = selectorManager;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C02311 c02311 = new C02311(this.$socketOptions, this.$$this$reader, this.$channel, this.$nioChannel, this.$selectable, this.$selector, dVar);
                c02311.L$0 = obj;
                return c02311;
            }

            @Override // r7.p
            public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, d<? super t0> dVar) {
                return ((C02311) create(lookAheadSuspendSession, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Removed duplicated region for block: B:26:0x00a9  */
            /* JADX WARN: Removed duplicated region for block: B:38:0x00db  */
            /* JADX WARN: Removed duplicated region for block: B:43:0x00f9  */
            /* JADX WARN: Removed duplicated region for block: B:50:0x0121  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00a7 -> B:36:0x00d5). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00c9 -> B:24:0x00a2). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x011f -> B:41:0x00f1). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0136 -> B:57:0x0156). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x0153 -> B:57:0x0156). Please report as a decompilation issue!!! */
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
                    Method dump skipped, instruction units count: 370
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOWriterKt.AnonymousClass1.C02311.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Selectable selectable, ByteChannel byteChannel, WritableByteChannel writableByteChannel, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, SelectorManager selectorManager, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$selectable = selectable;
            this.$channel = byteChannel;
            this.$nioChannel = writableByteChannel;
            this.$socketOptions = tCPClientSocketOptions;
            this.$selector = selectorManager;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$selectable, this.$channel, this.$nioChannel, this.$socketOptions, this.$selector, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(ReaderScope readerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(readerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IOException {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    c.G(obj);
                    ReaderScope readerScope = (ReaderScope) this.L$0;
                    this.$selectable.interestOp(SelectInterest.WRITE, false);
                    ByteChannel byteChannel = this.$channel;
                    C02311 c02311 = new C02311(this.$socketOptions, readerScope, byteChannel, this.$nioChannel, this.$selectable, this.$selector, null);
                    this.label = 1;
                    Object objLookAheadSuspend = byteChannel.lookAheadSuspend(c02311, this);
                    a aVar = a.f15033i;
                    if (objLookAheadSuspend == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                this.$selectable.interestOp(SelectInterest.WRITE, false);
                if (this.$nioChannel instanceof SocketChannel) {
                    try {
                        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                            ((SocketChannel) this.$nioChannel).shutdownOutput();
                        } else {
                            ((SocketChannel) this.$nioChannel).socket().shutdownOutput();
                        }
                    } catch (ClosedChannelException unused) {
                    }
                }
                return t0.f22605a;
            } finally {
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/ReaderScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/ReaderScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1", f = "CIOWriter.kt", l = {NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI, 52, 52}, m = "invokeSuspend")
    public static final class C18391 extends j implements p<ReaderScope, d<? super t0>, Object> {
        final /* synthetic */ ByteBuffer $buffer;
        final /* synthetic */ ByteChannel $channel;
        final /* synthetic */ WritableByteChannel $nioChannel;
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
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18391(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ByteBuffer byteBuffer, ByteChannel byteChannel, Selectable selectable, ObjectPool<ByteBuffer> objectPool, WritableByteChannel writableByteChannel, SelectorManager selectorManager, d<? super C18391> dVar) {
            super(2, dVar);
            this.$socketOptions = tCPClientSocketOptions;
            this.$buffer = byteBuffer;
            this.$channel = byteChannel;
            this.$selectable = selectable;
            this.$pool = objectPool;
            this.$nioChannel = writableByteChannel;
            this.$selector = selectorManager;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18391 c18391 = new C18391(this.$socketOptions, this.$buffer, this.$channel, this.$selectable, this.$pool, this.$nioChannel, this.$selector, dVar);
            c18391.L$0 = obj;
            return c18391;
        }

        @Override // r7.p
        public final Object invoke(ReaderScope readerScope, d<? super t0> dVar) {
            return ((C18391) create(readerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:67:0x016e, code lost:
        
            if (r0.select(r6, r15, r14) == r5) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x017f, code lost:
        
            r0 = r10;
         */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b8  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00f1 A[Catch: all -> 0x005a, TRY_ENTER, TryCatch #2 {all -> 0x005a, blocks: (B:73:0x017b, B:74:0x017f, B:47:0x00f6, B:31:0x0099, B:34:0x00ba, B:37:0x00c5, B:46:0x00f1, B:49:0x00fe, B:52:0x0113, B:54:0x011b, B:57:0x0137, B:59:0x013d, B:62:0x0143, B:75:0x0189, B:76:0x018c, B:15:0x0055, B:20:0x0062, B:23:0x006e, B:25:0x0072, B:28:0x007f, B:8:0x002c, B:69:0x0171, B:71:0x0177, B:64:0x014d, B:66:0x0155), top: B:91:0x0008, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00fe A[Catch: all -> 0x005a, TryCatch #2 {all -> 0x005a, blocks: (B:73:0x017b, B:74:0x017f, B:47:0x00f6, B:31:0x0099, B:34:0x00ba, B:37:0x00c5, B:46:0x00f1, B:49:0x00fe, B:52:0x0113, B:54:0x011b, B:57:0x0137, B:59:0x013d, B:62:0x0143, B:75:0x0189, B:76:0x018c, B:15:0x0055, B:20:0x0062, B:23:0x006e, B:25:0x0072, B:28:0x007f, B:8:0x002c, B:69:0x0171, B:71:0x0177, B:64:0x014d, B:66:0x0155), top: B:91:0x0008, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x011b A[Catch: all -> 0x005a, TryCatch #2 {all -> 0x005a, blocks: (B:73:0x017b, B:74:0x017f, B:47:0x00f6, B:31:0x0099, B:34:0x00ba, B:37:0x00c5, B:46:0x00f1, B:49:0x00fe, B:52:0x0113, B:54:0x011b, B:57:0x0137, B:59:0x013d, B:62:0x0143, B:75:0x0189, B:76:0x018c, B:15:0x0055, B:20:0x0062, B:23:0x006e, B:25:0x0072, B:28:0x007f, B:8:0x002c, B:69:0x0171, B:71:0x0177, B:64:0x014d, B:66:0x0155), top: B:91:0x0008, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x013d A[Catch: all -> 0x005a, TryCatch #2 {all -> 0x005a, blocks: (B:73:0x017b, B:74:0x017f, B:47:0x00f6, B:31:0x0099, B:34:0x00ba, B:37:0x00c5, B:46:0x00f1, B:49:0x00fe, B:52:0x0113, B:54:0x011b, B:57:0x0137, B:59:0x013d, B:62:0x0143, B:75:0x0189, B:76:0x018c, B:15:0x0055, B:20:0x0062, B:23:0x006e, B:25:0x0072, B:28:0x007f, B:8:0x002c, B:69:0x0171, B:71:0x0177, B:64:0x014d, B:66:0x0155), top: B:91:0x0008, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0141  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00f1 -> B:47:0x00f6). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x013f -> B:52:0x0113). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x0153 -> B:69:0x0171). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x016e -> B:69:0x0171). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 436
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOWriterKt.C18391.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final ReaderJob attachForWritingDirectImpl(k0 k0Var, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        return CoroutinesKt.reader(k0Var, x0.f19654c.plus(new j0("cio-to-nio-writer")), byteChannel, new AnonymousClass1(selectable, byteChannel, writableByteChannel, tCPClientSocketOptions, selectorManager, null));
    }

    public static /* synthetic */ ReaderJob attachForWritingDirectImpl$default(k0 k0Var, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForWritingDirectImpl(k0Var, byteChannel, writableByteChannel, selectable, selectorManager, tCPClientSocketOptions);
    }

    public static final ReaderJob attachForWritingImpl(k0 k0Var, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool<ByteBuffer> objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        return CoroutinesKt.reader(k0Var, x0.f19654c.plus(new j0("cio-to-nio-writer")), byteChannel, new C18391(tCPClientSocketOptions, objectPool.borrow(), byteChannel, selectable, objectPool, writableByteChannel, selectorManager, null));
    }

    public static /* synthetic */ ReaderJob attachForWritingImpl$default(k0 k0Var, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i10, Object obj) {
        if ((i10 & 32) != 0) {
            tCPClientSocketOptions = null;
        }
        return attachForWritingImpl(k0Var, byteChannel, writableByteChannel, selectable, selectorManager, objectPool, tCPClientSocketOptions);
    }
}
