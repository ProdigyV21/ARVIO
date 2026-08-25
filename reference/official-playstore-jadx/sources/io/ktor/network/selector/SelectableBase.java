package io.ktor.network.selector;

import java.nio.channels.SelectableChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import ka.k;
import kotlin.Metadata;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010 \u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lio/ktor/network/selector/SelectableBase;", "Lio/ktor/network/selector/Selectable;", "Ljava/nio/channels/SelectableChannel;", "channel", "<init>", "(Ljava/nio/channels/SelectableChannel;)V", "Lio/ktor/network/selector/SelectInterest;", "interest", "", "state", "Lx6/t0;", "interestOp", "(Lio/ktor/network/selector/SelectInterest;Z)V", "close", "()V", "dispose", "Ljava/nio/channels/SelectableChannel;", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "_isClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Lio/ktor/network/selector/InterestSuspensionsMap;", "suspensions", "Lio/ktor/network/selector/InterestSuspensionsMap;", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "isClosed", "()Z", "", "getInterestedOps", "()I", "interestedOps", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class SelectableBase implements Selectable {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _interestedOps$FU = AtomicIntegerFieldUpdater.newUpdater(SelectableBase.class, "_interestedOps");
    private final SelectableChannel channel;
    private final AtomicBoolean _isClosed = new AtomicBoolean(false);
    private final InterestSuspensionsMap suspensions = new InterestSuspensionsMap();
    private volatile /* synthetic */ int _interestedOps = 0;

    public SelectableBase(SelectableChannel selectableChannel) {
        this.channel = selectableChannel;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this._isClosed.compareAndSet(false, true)) {
            this._interestedOps = 0;
            InterestSuspensionsMap suspensions = getSuspensions();
            for (SelectInterest selectInterest : SelectInterest.INSTANCE.getAllInterests()) {
                k<t0> kVarRemoveSuspension = suspensions.removeSuspension(selectInterest);
                if (kVarRemoveSuspension != null) {
                    kVarRemoveSuspension.resumeWith(new c0(new ClosedChannelCancellationException()));
                }
            }
        }
    }

    @Override // io.ktor.network.selector.Selectable, ka.z0
    public void dispose() {
        close();
    }

    @Override // io.ktor.network.selector.Selectable
    public SelectableChannel getChannel() {
        return this.channel;
    }

    @Override // io.ktor.network.selector.Selectable
    /* JADX INFO: renamed from: getInterestedOps, reason: from getter */
    public int get_interestedOps() {
        return this._interestedOps;
    }

    @Override // io.ktor.network.selector.Selectable
    public InterestSuspensionsMap getSuspensions() {
        return this.suspensions;
    }

    @Override // io.ktor.network.selector.Selectable
    public void interestOp(SelectInterest interest, boolean state) {
        int i10;
        int flag = interest.getFlag();
        do {
            i10 = this._interestedOps;
        } while (!_interestedOps$FU.compareAndSet(this, i10, state ? i10 | flag : (~flag) & i10));
    }

    @Override // io.ktor.network.selector.Selectable
    public boolean isClosed() {
        return this._isClosed.get();
    }
}
