package io.ktor.network.selector;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import d7.d;
import d7.j;
import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import ka.k;
import ka.l;
import ka.m;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import t7.a;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\b&\u0018\u00002\u00020\u0001:\u00018B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H$¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ+\u0010\u0012\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000eH\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0004H\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0004H\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010 \u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eH\u0004¢\u0006\u0004\b \u0010!J!\u0010 \u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0004¢\u0006\u0004\b \u0010\"R\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\"\u0010)\u001a\u00020(8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\u00020(8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b/\u0010*\u001a\u0004\b0\u0010,\"\u0004\b1\u0010.R,\u00107\u001a\u0004\u0018\u00010\u0004*\u00020\u000e2\b\u00102\u001a\u0004\u0018\u00010\u00048B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, d2 = {"Lio/ktor/network/selector/SelectorManagerSupport;", "Lio/ktor/network/selector/SelectorManager;", "<init>", "()V", "Lio/ktor/network/selector/Selectable;", "selectable", "Lx6/t0;", "publishInterest", "(Lio/ktor/network/selector/Selectable;)V", "Lio/ktor/network/selector/SelectInterest;", "interest", "select", "(Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectInterest;Ld7/d;)Ljava/lang/Object;", "", "Ljava/nio/channels/SelectionKey;", "selectedKeys", "", UserMetadata.KEYDATA_FILENAME, "handleSelectedKeys", "(Ljava/util/Set;Ljava/util/Set;)V", "key", "handleSelectedKey", "(Ljava/nio/channels/SelectionKey;)V", "Ljava/nio/channels/Selector;", "selector", "applyInterest", "(Ljava/nio/channels/Selector;Lio/ktor/network/selector/Selectable;)V", "attachment", "notifyClosedImpl", "(Ljava/nio/channels/Selector;Ljava/nio/channels/SelectionKey;Lio/ktor/network/selector/Selectable;)V", "", "cause", "cancelAllSuspensions", "(Lio/ktor/network/selector/Selectable;Ljava/lang/Throwable;)V", "(Ljava/nio/channels/Selector;Ljava/lang/Throwable;)V", "Ljava/nio/channels/spi/SelectorProvider;", "provider", "Ljava/nio/channels/spi/SelectorProvider;", "getProvider", "()Ljava/nio/channels/spi/SelectorProvider;", "", "pending", "I", "getPending", "()I", "setPending", "(I)V", "cancelled", "getCancelled", "setCancelled", "newValue", "getSubject", "(Ljava/nio/channels/SelectionKey;)Lio/ktor/network/selector/Selectable;", "setSubject", "(Ljava/nio/channels/SelectionKey;Lio/ktor/network/selector/Selectable;)V", "subject", "ClosedSelectorCancellationException", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class SelectorManagerSupport implements SelectorManager {
    private int cancelled;
    private int pending;
    private final SelectorProvider provider = SelectorProvider.provider();

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/network/selector/SelectorManagerSupport$ClosedSelectorCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "()V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ClosedSelectorCancellationException extends CancellationException {
        public ClosedSelectorCancellationException() {
            super("Closed selector");
        }
    }

    private final Selectable getSubject(SelectionKey selectionKey) {
        Object objAttachment = selectionKey.attachment();
        if (objAttachment instanceof Selectable) {
            return (Selectable) objAttachment;
        }
        return null;
    }

    private final void setSubject(SelectionKey selectionKey, Selectable selectable) {
        selectionKey.attach(selectable);
    }

    public final void applyInterest(Selector selector, Selectable selectable) {
        try {
            SelectableChannel channel = selectable.getChannel();
            SelectionKey selectionKeyKeyFor = channel.keyFor(selector);
            int i10 = selectable.get_interestedOps();
            if (selectionKeyKeyFor == null) {
                if (i10 != 0) {
                    channel.register(selector, i10, selectable);
                }
            } else if (selectionKeyKeyFor.interestOps() != i10) {
                selectionKeyKeyFor.interestOps(i10);
            }
            if (i10 != 0) {
                this.pending++;
            }
        } catch (Throwable th) {
            SelectionKey selectionKeyKeyFor2 = selectable.getChannel().keyFor(selector);
            if (selectionKeyKeyFor2 != null) {
                selectionKeyKeyFor2.cancel();
            }
            cancelAllSuspensions(selectable, th);
        }
    }

    public final void cancelAllSuspensions(Selectable attachment, Throwable cause) {
        InterestSuspensionsMap suspensions = attachment.getSuspensions();
        for (SelectInterest selectInterest : SelectInterest.INSTANCE.getAllInterests()) {
            k<t0> kVarRemoveSuspension = suspensions.removeSuspension(selectInterest);
            if (kVarRemoveSuspension != null) {
                kVarRemoveSuspension.resumeWith(new c0(cause));
            }
        }
    }

    public final int getCancelled() {
        return this.cancelled;
    }

    @Override // io.ktor.network.selector.SelectorManager, ka.k0
    public abstract /* synthetic */ j getCoroutineContext();

    public final int getPending() {
        return this.pending;
    }

    @Override // io.ktor.network.selector.SelectorManager
    public final SelectorProvider getProvider() {
        return this.provider;
    }

    public final void handleSelectedKey(SelectionKey key) {
        k<t0> kVarRemoveSuspension;
        try {
            int i10 = key.readyOps();
            int iInterestOps = key.interestOps();
            Selectable subject = getSubject(key);
            if (subject == null) {
                key.cancel();
                this.cancelled++;
                return;
            }
            InterestSuspensionsMap suspensions = subject.getSuspensions();
            int[] flags = SelectInterest.INSTANCE.getFlags();
            int length = flags.length;
            for (int i11 = 0; i11 < length; i11++) {
                if ((flags[i11] & i10) != 0 && (kVarRemoveSuspension = suspensions.removeSuspension(i11)) != null) {
                    kVarRemoveSuspension.resumeWith(t0.f22605a);
                }
            }
            int i12 = (~i10) & iInterestOps;
            if (i12 != iInterestOps) {
                key.interestOps(i12);
            }
            if (i12 != 0) {
                this.pending++;
            }
        } catch (Throwable th) {
            key.cancel();
            this.cancelled++;
            Selectable subject2 = getSubject(key);
            if (subject2 != null) {
                cancelAllSuspensions(subject2, th);
                setSubject(key, null);
            }
        }
    }

    public final void handleSelectedKeys(Set<SelectionKey> selectedKeys, Set<? extends SelectionKey> keys) {
        int size = selectedKeys.size();
        this.pending = keys.size() - size;
        this.cancelled = 0;
        if (size > 0) {
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                handleSelectedKey(it.next());
                it.remove();
            }
        }
    }

    public final void notifyClosedImpl(Selector selector, SelectionKey key, Selectable attachment) {
        cancelAllSuspensions(attachment, new ClosedChannelException());
        setSubject(key, null);
        selector.wakeup();
    }

    public abstract void publishInterest(Selectable selectable);

    @Override // io.ktor.network.selector.SelectorManager
    public final Object select(Selectable selectable, SelectInterest selectInterest, d<? super t0> dVar) throws IOException {
        int i10 = selectable.get_interestedOps();
        int flag = selectInterest.getFlag();
        if (selectable.isClosed()) {
            SelectorManagerSupportKt.selectableIsClosed();
            throw new KotlinNothingValueException();
        }
        if ((i10 & flag) == 0) {
            SelectorManagerSupportKt.selectableIsInvalid(i10, flag);
            throw new KotlinNothingValueException();
        }
        l lVar = new l(1, a.A(dVar));
        lVar.s();
        lVar.c(SelectorManagerSupport$select$2$1.INSTANCE);
        selectable.getSuspensions().addSuspension(selectInterest, lVar);
        if (!(l.f19606q.get(lVar) instanceof m)) {
            publishInterest(selectable);
        }
        Object objR = lVar.r();
        return objR == e7.a.f15033i ? objR : t0.f22605a;
    }

    public final void setCancelled(int i10) {
        this.cancelled = i10;
    }

    public final void setPending(int i10) {
        this.pending = i10;
    }

    public final void cancelAllSuspensions(Selector selector, Throwable cause) {
        if (cause == null) {
            cause = new ClosedSelectorCancellationException();
        }
        for (SelectionKey selectionKey : selector.keys()) {
            try {
                if (selectionKey.isValid()) {
                    selectionKey.interestOps(0);
                }
            } catch (CancelledKeyException unused) {
            }
            Object objAttachment = selectionKey.attachment();
            Selectable selectable = objAttachment instanceof Selectable ? (Selectable) objAttachment : null;
            if (selectable != null) {
                cancelAllSuspensions(selectable, cause);
            }
            selectionKey.cancel();
        }
    }
}
