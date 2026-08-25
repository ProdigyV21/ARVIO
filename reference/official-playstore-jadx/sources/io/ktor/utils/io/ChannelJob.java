package io.ktor.utils.io;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import d7.j;
import ga.m;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.CancellationException;
import ka.o;
import ka.q;
import ka.v1;
import ka.z0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import sa.b;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0097\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001c\u0010\u000f\u001a\u00020\u00132\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0097\u0001¢\u0006\u0004\b\u000f\u0010\u0014J\"\u0010\u000f\u001a\u00020\u000e2\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u0016H\u0096\u0001¢\u0006\u0004\b\u000f\u0010\u0017J8\u0010\u001d\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u0019\u001a\u00028\u00002\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ*\u0010\"\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u001f*\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0096\u0003¢\u0006\u0004\b\"\u0010#J\u0014\u0010$\u001a\u00060\u0015j\u0002`\u0016H\u0097\u0001¢\u0006\u0004\b$\u0010%JM\u0010.\u001a\u00020-2\b\b\u0002\u0010&\u001a\u00020\u00132\b\b\u0002\u0010'\u001a\u00020\u00132'\u0010,\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000e0(j\u0002`+H\u0097\u0001¢\u0006\u0004\b.\u0010/J9\u0010.\u001a\u00020-2'\u0010,\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000e0(j\u0002`+H\u0096\u0001¢\u0006\u0004\b.\u00100J\u0013\u00101\u001a\u00020\u000eH\u0096Aø\u0001\u0000¢\u0006\u0004\b1\u00102J\u001c\u00104\u001a\u0002032\n\u0010!\u001a\u0006\u0012\u0002\b\u00030 H\u0096\u0001¢\u0006\u0004\b4\u00105J\u0018\u00107\u001a\u0002032\u0006\u00106\u001a\u000203H\u0096\u0003¢\u0006\u0004\b7\u00108J\u0018\u00107\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u0003H\u0097\u0003¢\u0006\u0004\b7\u0010:J\u0010\u0010;\u001a\u00020\u0013H\u0096\u0001¢\u0006\u0004\b;\u0010<J\u000f\u0010>\u001a\u00020=H\u0016¢\u0006\u0004\b>\u0010?R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010@R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010A\u001a\u0004\bB\u0010CR\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00030D8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020\u00138\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bH\u0010<R\u0014\u0010I\u001a\u00020\u00138\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bI\u0010<R\u0014\u0010J\u001a\u00020\u00138\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bJ\u0010<R\u0018\u0010!\u001a\u0006\u0012\u0002\b\u00030 8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0014\u0010P\u001a\u00020M8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0016\u0010S\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bQ\u0010R\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006T"}, d2 = {"Lio/ktor/utils/io/ChannelJob;", "Lio/ktor/utils/io/ReaderJob;", "Lio/ktor/utils/io/WriterJob;", "Lka/v1;", "delegate", "Lio/ktor/utils/io/ByteChannel;", "channel", "<init>", "(Lka/v1;Lio/ktor/utils/io/ByteChannel;)V", "Lka/q;", "child", "Lka/o;", "attachChild", "(Lka/q;)Lka/o;", "Lx6/t0;", "cancel", "()V", "", "cause", "", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "R", "initial", "Lkotlin/Function2;", "Ld7/j$a;", "operation", "fold", "(Ljava/lang/Object;Lr7/p;)Ljava/lang/Object;", "E", "Ld7/j$b;", "key", "get", "(Ld7/j$b;)Ld7/j$a;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lka/z0;", "invokeOnCompletion", "(ZZLr7/l;)Lka/z0;", "(Lr7/l;)Lka/z0;", "join", "(Ld7/d;)Ljava/lang/Object;", "Ld7/j;", "minusKey", "(Ld7/j$b;)Ld7/j;", "context", "plus", "(Ld7/j;)Ld7/j;", "other", "(Lka/v1;)Lka/v1;", TtmlNode.START, "()Z", "", "toString", "()Ljava/lang/String;", "Lka/v1;", "Lio/ktor/utils/io/ByteChannel;", "getChannel", "()Lio/ktor/utils/io/ByteChannel;", "Lga/m;", "getChildren", "()Lga/m;", "children", "isActive", "isCancelled", "isCompleted", "getKey", "()Ld7/j$b;", "Lsa/b;", "getOnJoin", "()Lsa/b;", "onJoin", "getParent", "()Lka/v1;", "parent", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ChannelJob implements ReaderJob, WriterJob, v1 {
    private final ByteChannel channel;
    private final v1 delegate;

    public ChannelJob(v1 v1Var, ByteChannel byteChannel) {
        this.delegate = v1Var;
        this.channel = byteChannel;
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public o attachChild(q child) {
        return this.delegate.attachChild(child);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    @e
    public /* synthetic */ void cancel() {
        this.delegate.cancel();
    }

    @Override // io.ktor.utils.io.ReaderJob, d7.j
    public <R> R fold(R initial, p<? super R, ? super j.a, ? extends R> operation) {
        return (R) this.delegate.fold(initial, operation);
    }

    @Override // io.ktor.utils.io.ReaderJob, d7.j
    public <E extends j.a> E get(j.b<E> key) {
        return (E) this.delegate.get(key);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public CancellationException getCancellationException() {
        return this.delegate.getCancellationException();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public m<v1> getChildren() {
        return this.delegate.getChildren();
    }

    @Override // io.ktor.utils.io.ReaderJob, d7.j.a
    public j.b<?> getKey() {
        return this.delegate.getKey();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public b getOnJoin() {
        return this.delegate.getOnJoin();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public v1 getParent() {
        return this.delegate.getParent();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public z0 invokeOnCompletion(l<? super Throwable, t0> handler) {
        return this.delegate.invokeOnCompletion(handler);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public boolean isActive() {
        return this.delegate.isActive();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public boolean isCompleted() {
        return this.delegate.isCompleted();
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public Object join(d<? super t0> dVar) {
        return this.delegate.join(dVar);
    }

    @Override // io.ktor.utils.io.ReaderJob, d7.j
    public j minusKey(j.b<?> key) {
        return this.delegate.minusKey(key);
    }

    @Override // io.ktor.utils.io.ReaderJob, d7.j
    public j plus(j context) {
        return this.delegate.plus(context);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public boolean start() {
        return this.delegate.start();
    }

    public String toString() {
        return "ChannelJob[" + this.delegate + ']';
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public void cancel(CancellationException cause) {
        this.delegate.cancel(cause);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    public z0 invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, l<? super Throwable, t0> handler) {
        return this.delegate.invokeOnCompletion(onCancelling, invokeImmediately, handler);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    @e
    public v1 plus(v1 other) {
        return this.delegate.plus(other);
    }

    @Override // io.ktor.utils.io.ReaderJob, ka.v1
    @e
    public /* synthetic */ boolean cancel(Throwable cause) {
        return this.delegate.cancel(cause);
    }

    @Override // io.ktor.utils.io.ReaderJob, io.ktor.utils.io.WriterJob
    public ByteChannel getChannel() {
        return this.channel;
    }
}
