package io.ktor.network.sockets;

import androidx.compose.material3.d;
import com.google.common.util.concurrent.r0;
import d7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.network.selector.SelectableBase;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.pool.ObjectPool;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import ka.k0;
import ka.m0;
import ka.t;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\u000e\b\u0000\u0010\u0003 \u0001*\u00020\u0001*\u00020\u00022\u00020\u00042\u00020\u00052\u00020\u0006B3\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010JG\u0010\u001a\u001a\u00028\u0001\"\b\b\u0001\u0010\u0012*\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00152\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b \u0010!J%\u0010$\u001a\u0004\u0018\u00010\u001c2\b\u0010\"\u001a\u0004\u0018\u00010\u001c2\b\u0010#\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\u00020&2\u0006\u0010\u0007\u001a\u00020\u0015¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020)2\u0006\u0010\u0007\u001a\u00020\u0015¢\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u001fH\u0016¢\u0006\u0004\b,\u0010!J\u000f\u0010-\u001a\u00020\u001fH\u0016¢\u0006\u0004\b-\u0010!R\u001a\u0010\u0007\u001a\u00028\u00008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u00101\u001a\u0004\b2\u00103R\u001f\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u00104\u001a\u0004\b5\u00106R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\"\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0\u00168\u0002X\u0082\u0004¢\u0006\f\n\u0004\b;\u0010<\u0012\u0004\b=\u0010!R\"\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\u00168\u0002X\u0082\u0004¢\u0006\f\n\u0004\b>\u0010<\u0012\u0004\b?\u0010!R\u001a\u0010A\u001a\u00020@8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\"\u0010H\u001a\u00020E*\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR*\u0010M\u001a\u0004\u0018\u00010\u001c*\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u00168BX\u0082\u0004¢\u0006\f\u0012\u0004\bK\u0010L\u001a\u0004\bI\u0010JR\u0014\u0010Q\u001a\u00020N8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bO\u0010P¨\u0006R"}, d2 = {"Lio/ktor/network/sockets/NIOSocketImpl;", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "S", "Lio/ktor/network/sockets/ReadWriteSocket;", "Lio/ktor/network/selector/SelectableBase;", "Lka/k0;", "channel", "Lio/ktor/network/selector/SelectorManager;", "selector", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "socketOptions", "<init>", "(Ljava/nio/channels/SelectableChannel;Lio/ktor/network/selector/SelectorManager;Lio/ktor/utils/io/pool/ObjectPool;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)V", "Lka/v1;", "J", "", ContentDisposition.Parameters.Name, "Lio/ktor/utils/io/ByteChannel;", "Ljava/util/concurrent/atomic/AtomicReference;", "ref", "Lkotlin/Function0;", "producer", "attachFor", "(Ljava/lang/String;Lio/ktor/utils/io/ByteChannel;Ljava/util/concurrent/atomic/AtomicReference;Lr7/a;)Lka/v1;", "", "actualClose", "()Ljava/lang/Throwable;", "Lx6/t0;", "checkChannels", "()V", "e1", "e2", "combine", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Lio/ktor/utils/io/WriterJob;", "attachForReading", "(Lio/ktor/utils/io/ByteChannel;)Lio/ktor/utils/io/WriterJob;", "Lio/ktor/utils/io/ReaderJob;", "attachForWriting", "(Lio/ktor/utils/io/ByteChannel;)Lio/ktor/utils/io/ReaderJob;", "dispose", "close", "Ljava/nio/channels/SelectableChannel;", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "Lio/ktor/network/selector/SelectorManager;", "getSelector", "()Lio/ktor/network/selector/SelectorManager;", "Lio/ktor/utils/io/pool/ObjectPool;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "closeFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "readerJob", "Ljava/util/concurrent/atomic/AtomicReference;", "getReaderJob$annotations", "writerJob", "getWriterJob$annotations", "Lka/t;", "socketContext", "Lka/t;", "getSocketContext", "()Lka/t;", "", "getCompletedOrNotStarted", "(Ljava/util/concurrent/atomic/AtomicReference;)Z", "completedOrNotStarted", "getException", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/lang/Throwable;", "getException$annotations", "(Ljava/util/concurrent/atomic/AtomicReference;)V", "exception", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class NIOSocketImpl<S extends SelectableChannel & ByteChannel> extends SelectableBase implements ReadWriteSocket, k0 {
    private final S channel;
    private final AtomicBoolean closeFlag;
    private final ObjectPool<ByteBuffer> pool;
    private final AtomicReference<ReaderJob> readerJob;
    private final SelectorManager selector;
    private final t socketContext;
    private final SocketOptions.TCPClientSocketOptions socketOptions;
    private final AtomicReference<WriterJob> writerJob;

    /* JADX INFO: renamed from: io.ktor.network.sockets.NIOSocketImpl$attachFor$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\n\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u000e\b\u0001\u0010\u0004 \u0001*\u00020\u0002*\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"Lka/v1;", "J", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "S", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        final /* synthetic */ NIOSocketImpl<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(NIOSocketImpl<? extends S> nIOSocketImpl) {
            super(1);
            this.this$0 = nIOSocketImpl;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) throws IllegalAccessException, InvocationTargetException {
            this.this$0.checkChannels();
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.NIOSocketImpl$attachForReading$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002 \u0001*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/WriterJob;", "S", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18411 extends r implements a<WriterJob> {
        final /* synthetic */ io.ktor.utils.io.ByteChannel $channel;
        final /* synthetic */ NIOSocketImpl<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18411(NIOSocketImpl<? extends S> nIOSocketImpl, io.ktor.utils.io.ByteChannel byteChannel) {
            super(0);
            this.this$0 = nIOSocketImpl;
            this.$channel = byteChannel;
        }

        @Override // r7.a
        public final WriterJob invoke() {
            if (this.this$0.getPool() != null) {
                NIOSocketImpl<S> nIOSocketImpl = this.this$0;
                io.ktor.utils.io.ByteChannel byteChannel = this.$channel;
                ReadableByteChannel readableByteChannel = (ReadableByteChannel) nIOSocketImpl.getChannel();
                NIOSocketImpl<S> nIOSocketImpl2 = this.this$0;
                return CIOReaderKt.attachForReadingImpl(nIOSocketImpl, byteChannel, readableByteChannel, nIOSocketImpl2, nIOSocketImpl2.getSelector(), this.this$0.getPool(), ((NIOSocketImpl) this.this$0).socketOptions);
            }
            NIOSocketImpl<S> nIOSocketImpl3 = this.this$0;
            io.ktor.utils.io.ByteChannel byteChannel2 = this.$channel;
            ReadableByteChannel readableByteChannel2 = (ReadableByteChannel) nIOSocketImpl3.getChannel();
            NIOSocketImpl<S> nIOSocketImpl4 = this.this$0;
            return CIOReaderKt.attachForReadingDirectImpl(nIOSocketImpl3, byteChannel2, readableByteChannel2, nIOSocketImpl4, nIOSocketImpl4.getSelector(), ((NIOSocketImpl) this.this$0).socketOptions);
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.NIOSocketImpl$attachForWriting$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002 \u0001*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/ReaderJob;", "S", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18421 extends r implements a<ReaderJob> {
        final /* synthetic */ io.ktor.utils.io.ByteChannel $channel;
        final /* synthetic */ NIOSocketImpl<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18421(NIOSocketImpl<? extends S> nIOSocketImpl, io.ktor.utils.io.ByteChannel byteChannel) {
            super(0);
            this.this$0 = nIOSocketImpl;
            this.$channel = byteChannel;
        }

        @Override // r7.a
        public final ReaderJob invoke() {
            NIOSocketImpl<S> nIOSocketImpl = this.this$0;
            io.ktor.utils.io.ByteChannel byteChannel = this.$channel;
            WritableByteChannel writableByteChannel = (WritableByteChannel) nIOSocketImpl.getChannel();
            NIOSocketImpl<S> nIOSocketImpl2 = this.this$0;
            return CIOWriterKt.attachForWritingDirectImpl(nIOSocketImpl, byteChannel, writableByteChannel, nIOSocketImpl2, nIOSocketImpl2.getSelector(), ((NIOSocketImpl) this.this$0).socketOptions);
        }
    }

    public /* synthetic */ NIOSocketImpl(SelectableChannel selectableChannel, SelectorManager selectorManager, ObjectPool objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i10, h hVar) {
        this(selectableChannel, selectorManager, objectPool, (i10 & 8) != 0 ? null : tCPClientSocketOptions);
    }

    private final Throwable actualClose() {
        try {
            ((ByteChannel) getChannel()).close();
            super.close();
            this.selector.notifyClosed(this);
            return null;
        } catch (Throwable th) {
            this.selector.notifyClosed(this);
            return th;
        }
    }

    private final <J extends v1> J attachFor(String name, io.ktor.utils.io.ByteChannel channel, AtomicReference<J> ref, a<? extends J> producer) throws ClosedChannelException {
        if (this.closeFlag.get()) {
            ClosedChannelException closedChannelException = new ClosedChannelException();
            channel.close(closedChannelException);
            throw closedChannelException;
        }
        J j10 = (J) producer.invoke();
        while (!ref.compareAndSet(null, j10)) {
            if (ref.get() != null) {
                IllegalStateException illegalStateException = new IllegalStateException(d.m(name, " channel has already been set"));
                j10.cancel(null);
                throw illegalStateException;
            }
        }
        if (!this.closeFlag.get()) {
            channel.attachJob(j10);
            j10.invokeOnCompletion(new AnonymousClass1(this));
            return j10;
        }
        ClosedChannelException closedChannelException2 = new ClosedChannelException();
        j10.cancel(null);
        channel.close(closedChannelException2);
        throw closedChannelException2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkChannels() throws IllegalAccessException, InvocationTargetException {
        if (this.closeFlag.get() && getCompletedOrNotStarted(this.readerJob) && getCompletedOrNotStarted(this.writerJob)) {
            Throwable exception = getException(this.readerJob);
            Throwable exception2 = getException(this.writerJob);
            Throwable thCombine = combine(combine(exception, exception2), actualClose());
            if (thCombine == null) {
                getSocketContext().complete();
            } else {
                getSocketContext().i(thCombine);
            }
        }
    }

    private final Throwable combine(Throwable e12, Throwable e22) throws IllegalAccessException, InvocationTargetException {
        if (e12 == null) {
            return e22;
        }
        if (e22 == null || e12 == e22) {
            return e12;
        }
        r0.a(e12, e22);
        return e12;
    }

    private final boolean getCompletedOrNotStarted(AtomicReference<? extends v1> atomicReference) {
        v1 v1Var = atomicReference.get();
        return v1Var == null || v1Var.isCompleted();
    }

    private final Throwable getException(AtomicReference<? extends v1> atomicReference) {
        CancellationException cancellationException;
        v1 v1Var = atomicReference.get();
        if (v1Var != null) {
            if (!v1Var.isCancelled()) {
                v1Var = null;
            }
            if (v1Var != null && (cancellationException = v1Var.getCancellationException()) != null) {
                return cancellationException.getCause();
            }
        }
        return null;
    }

    private static /* synthetic */ void getException$annotations(AtomicReference atomicReference) {
    }

    private static /* synthetic */ void getReaderJob$annotations() {
    }

    private static /* synthetic */ void getWriterJob$annotations() {
    }

    @Override // io.ktor.network.sockets.AReadable
    public final WriterJob attachForReading(io.ktor.utils.io.ByteChannel channel) {
        return (WriterJob) attachFor("reading", channel, this.writerJob, new C18411(this, channel));
    }

    @Override // io.ktor.network.sockets.AWritable
    public final ReaderJob attachForWriting(io.ktor.utils.io.ByteChannel channel) {
        return (ReaderJob) attachFor("writing", channel, this.readerJob, new C18421(this, channel));
    }

    @Override // io.ktor.network.selector.SelectableBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IllegalAccessException, InvocationTargetException {
        ByteWriteChannel channel;
        if (this.closeFlag.compareAndSet(false, true)) {
            ReaderJob readerJob = this.readerJob.get();
            if (readerJob != null && (channel = readerJob.getChannel()) != null) {
                ByteWriteChannelKt.close(channel);
            }
            WriterJob writerJob = this.writerJob.get();
            if (writerJob != null) {
                writerJob.cancel((CancellationException) null);
            }
            checkChannels();
        }
    }

    @Override // io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable, ka.z0
    public void dispose() throws IllegalAccessException, InvocationTargetException {
        close();
    }

    @Override // io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable
    public S getChannel() {
        return this.channel;
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return getSocketContext();
    }

    public final ObjectPool<ByteBuffer> getPool() {
        return this.pool;
    }

    public final SelectorManager getSelector() {
        return this.selector;
    }

    public NIOSocketImpl(S s10, SelectorManager selectorManager, ObjectPool<ByteBuffer> objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        super(s10);
        this.channel = s10;
        this.selector = selectorManager;
        this.pool = objectPool;
        this.socketOptions = tCPClientSocketOptions;
        this.closeFlag = new AtomicBoolean();
        this.readerJob = new AtomicReference<>();
        this.writerJob = new AtomicReference<>();
        this.socketContext = m0.c();
    }

    @Override // io.ktor.network.sockets.ASocket
    public t getSocketContext() {
        return this.socketContext;
    }
}
