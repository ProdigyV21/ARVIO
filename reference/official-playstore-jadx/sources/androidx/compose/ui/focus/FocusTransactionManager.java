package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\u0003J6\u0010\f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\u0010\b\n\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0086\b¢\u0006\u0004\b\f\u0010\rJ6\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\u0010\b\n\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0086\b¢\u0006\u0004\b\u000e\u0010\rR \u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R \u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R,\u0010\u001f\u001a\u0004\u0018\u00010\u0011*\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Landroidx/compose/ui/focus/FocusTransactionManager;", "", "<init>", "()V", "Lx6/t0;", "beginTransaction", "commitTransaction", "cancelTransaction", "T", "Lkotlin/Function0;", "onCancelled", "block", "withNewTransaction", "(Lr7/a;Lr7/a;)Ljava/lang/Object;", "withExistingTransaction", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/focus/FocusStateImpl;", "states", "Ljava/util/Map;", "Landroidx/compose/runtime/collection/MutableVector;", "cancellationListener", "Landroidx/compose/runtime/collection/MutableVector;", "", "ongoingTransaction", "Z", "value", "getUncommittedFocusState", "(Landroidx/compose/ui/focus/FocusTargetNode;)Landroidx/compose/ui/focus/FocusStateImpl;", "setUncommittedFocusState", "(Landroidx/compose/ui/focus/FocusTargetNode;Landroidx/compose/ui/focus/FocusStateImpl;)V", "uncommittedFocusState", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusTransactionManager {
    public static final int $stable = 8;
    private boolean ongoingTransaction;
    private final Map<FocusTargetNode, FocusStateImpl> states = new LinkedHashMap();
    private final MutableVector<r7.a<t0>> cancellationListener = new MutableVector<>(new r7.a[16], 0);

    /* JADX INFO: Access modifiers changed from: private */
    public final void beginTransaction() {
        this.ongoingTransaction = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelTransaction() {
        MutableVector<r7.a<t0>> mutableVector = this.cancellationListener;
        int size = mutableVector.getSize();
        if (size > 0) {
            r7.a<t0>[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                content[i10].invoke();
                i10++;
            } while (i10 < size);
        }
        this.cancellationListener.clear();
        this.states.clear();
        this.ongoingTransaction = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitTransaction() {
        Iterator<FocusTargetNode> it = this.states.keySet().iterator();
        while (it.hasNext()) {
            it.next().commitFocusState$ui_release();
        }
        this.states.clear();
        this.ongoingTransaction = false;
    }

    public static /* synthetic */ Object withExistingTransaction$default(FocusTransactionManager focusTransactionManager, r7.a aVar, r7.a aVar2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = null;
        }
        if (aVar != null) {
            focusTransactionManager.cancellationListener.add(aVar);
        }
        if (focusTransactionManager.ongoingTransaction) {
            return aVar2.invoke();
        }
        try {
            focusTransactionManager.beginTransaction();
            return aVar2.invoke();
        } finally {
            focusTransactionManager.commitTransaction();
        }
    }

    public static /* synthetic */ Object withNewTransaction$default(FocusTransactionManager focusTransactionManager, r7.a aVar, r7.a aVar2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = null;
        }
        try {
            if (focusTransactionManager.ongoingTransaction) {
                focusTransactionManager.cancelTransaction();
            }
            focusTransactionManager.beginTransaction();
            if (aVar != null) {
                focusTransactionManager.cancellationListener.add(aVar);
            }
            Object objInvoke = aVar2.invoke();
            focusTransactionManager.commitTransaction();
            return objInvoke;
        } catch (Throwable th) {
            focusTransactionManager.commitTransaction();
            throw th;
        }
    }

    public final FocusStateImpl getUncommittedFocusState(FocusTargetNode focusTargetNode) {
        return this.states.get(focusTargetNode);
    }

    public final void setUncommittedFocusState(FocusTargetNode focusTargetNode, FocusStateImpl focusStateImpl) {
        Map<FocusTargetNode, FocusStateImpl> map = this.states;
        if (focusStateImpl == null) {
            throw new IllegalStateException("requires a non-null focus state");
        }
        map.put(focusTargetNode, focusStateImpl);
    }

    public final <T> T withExistingTransaction(r7.a<t0> onCancelled, r7.a<? extends T> block) {
        if (onCancelled != null) {
            this.cancellationListener.add(onCancelled);
        }
        if (this.ongoingTransaction) {
            return (T) block.invoke();
        }
        try {
            beginTransaction();
            return (T) block.invoke();
        } finally {
            commitTransaction();
        }
    }

    public final <T> T withNewTransaction(r7.a<t0> onCancelled, r7.a<? extends T> block) {
        try {
            if (this.ongoingTransaction) {
                cancelTransaction();
            }
            beginTransaction();
            if (onCancelled != null) {
                this.cancellationListener.add(onCancelled);
            }
            T t2 = (T) block.invoke();
            commitTransaction();
            return t2;
        } catch (Throwable th) {
            commitTransaction();
            throw th;
        }
    }
}
