package io.ktor.util.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\t\u001a\u00020\u0007\"\u000e\b\u0000\u0010\u0005\u0018\u0001*\u00060\u0001j\u0002`\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006H\u0086\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u0011\u0010\u0003R\u0011\u0010\u0013\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListHead;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "<init>", "()V", "Lio/ktor/util/internal/Node;", "T", "Lkotlin/Function1;", "Lx6/t0;", "block", "forEach", "(Lr7/l;)V", "", "remove", "()Z", "", "describeRemove", "()Ljava/lang/Void;", "validate$ktor_utils", "validate", "isEmpty", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    @Override // io.ktor.util.internal.LockFreeLinkedListNode
    public /* bridge */ /* synthetic */ AtomicDesc describeRemove() {
        return (AtomicDesc) m6732describeRemove();
    }

    public final <T extends LockFreeLinkedListNode> void forEach(l<? super T, t0> block) {
        if (p.a((LockFreeLinkedListNode) getNext(), this)) {
            return;
        }
        p.h();
        throw null;
    }

    public final boolean isEmpty() {
        return getNext() == this;
    }

    @Override // io.ktor.util.internal.LockFreeLinkedListNode
    public final boolean remove() {
        throw new UnsupportedOperationException();
    }

    public final void validate$ktor_utils() {
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getNext();
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this;
        while (!p.a(lockFreeLinkedListNode, this)) {
            LockFreeLinkedListNode nextNode = lockFreeLinkedListNode.getNextNode();
            lockFreeLinkedListNode.validateNode$ktor_utils(lockFreeLinkedListNode2, nextNode);
            lockFreeLinkedListNode2 = lockFreeLinkedListNode;
            lockFreeLinkedListNode = nextNode;
        }
        validateNode$ktor_utils(lockFreeLinkedListNode2, (LockFreeLinkedListNode) getNext());
    }

    /* JADX INFO: renamed from: describeRemove, reason: collision with other method in class */
    public final Void m6732describeRemove() {
        throw new UnsupportedOperationException();
    }
}
