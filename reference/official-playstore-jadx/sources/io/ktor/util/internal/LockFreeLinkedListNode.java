package io.ktor.util.internal;

import io.ktor.http.LinkHeader;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import r7.a;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0016\u0018\u00002\u00020\u0001:\u0004NOPQB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\t2\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u00042\u000e\b\u0004\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0081\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u00072\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u0004¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u0004¢\u0006\u0004\b\u000f\u0010\u0010J)\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012\"\f\b\u0000\u0010\u0011*\u00060\u0000j\u0002`\u00042\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0015\u001a\u00020\u00072\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u00042\u000e\b\u0004\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J7\u0010\u0019\u001a\u00020\u00072\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u00042\u0016\u0010\u0018\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u0004\u0012\u0004\u0012\u00020\u00070\u0017H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJG\u0010\u001b\u001a\u00020\u00072\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u00042\u0016\u0010\u0018\u001a\u0012\u0012\b\u0012\u00060\u0000j\u0002`\u0004\u0012\u0004\u0012\u00020\u00070\u00172\u000e\b\u0004\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001e\u001a\u00020\u00072\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u00042\n\u0010\u001d\u001a\u00060\u0000j\u0002`\u0004H\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ/\u0010\"\u001a\u00020!2\n\u0010\u0005\u001a\u00060\u0000j\u0002`\u00042\n\u0010\u001d\u001a\u00060\u0000j\u0002`\u00042\u0006\u0010 \u001a\u00020\tH\u0001¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0007H\u0016¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u000e¢\u0006\u0004\b&\u0010\u0003J\u0011\u0010(\u001a\u0004\u0018\u00010'H\u0016¢\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0004¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\f\u0012\b\u0012\u00060\u0000j\u0002`\u00040,¢\u0006\u0004\b-\u0010.J\u001a\u0010/\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u0011\u0018\u0001H\u0086\b¢\u0006\u0004\b/\u00100J1\u00101\u001a\u0004\u0018\u00018\u0000\"\u0006\b\u0000\u0010\u0011\u0018\u00012\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0017H\u0086\bø\u0001\u0000¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u000eH\u0001¢\u0006\u0004\b3\u0010\u0003J'\u00107\u001a\u00020\u000e2\n\u00104\u001a\u00060\u0000j\u0002`\u00042\n\u0010\u001d\u001a\u00060\u0000j\u0002`\u0004H\u0000¢\u0006\u0004\b5\u00106J\u000f\u00109\u001a\u000208H\u0016¢\u0006\u0004\b9\u0010:J\u000f\u0010<\u001a\u00020;H\u0002¢\u0006\u0004\b<\u0010=J\u001b\u0010>\u001a\u00020\u000e2\n\u0010\u001d\u001a\u00060\u0000j\u0002`\u0004H\u0002¢\u0006\u0004\b>\u0010\u0010J\u001b\u0010?\u001a\u00020\u000e2\n\u0010\u001d\u001a\u00060\u0000j\u0002`\u0004H\u0002¢\u0006\u0004\b?\u0010\u0010J\u0013\u0010@\u001a\u00060\u0000j\u0002`\u0004H\u0002¢\u0006\u0004\b@\u0010+J\u0013\u0010A\u001a\u00060\u0000j\u0002`\u0004H\u0002¢\u0006\u0004\bA\u0010+J-\u0010E\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u00042\n\u0010B\u001a\u00060\u0000j\u0002`\u00042\b\u0010D\u001a\u0004\u0018\u00010CH\u0002¢\u0006\u0004\bE\u0010FR\u0011\u0010G\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bG\u0010%R\u0011\u0010\u001d\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\bH\u00100R\u0015\u0010J\u001a\u00060\u0000j\u0002`\u00048F¢\u0006\u0006\u001a\u0004\bI\u0010+R\u0011\u00104\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\bK\u00100R\u0015\u0010M\u001a\u00060\u0000j\u0002`\u00048F¢\u0006\u0006\u001a\u0004\bL\u0010+\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006R"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListNode;", "", "<init>", "()V", "Lio/ktor/util/internal/Node;", "node", "Lkotlin/Function0;", "", "condition", "Lio/ktor/util/internal/LockFreeLinkedListNode$CondAddOp;", "makeCondAddOp", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lr7/a;)Lio/ktor/util/internal/LockFreeLinkedListNode$CondAddOp;", "addOneIfEmpty", "(Lio/ktor/util/internal/LockFreeLinkedListNode;)Z", "Lx6/t0;", "addLast", "(Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "T", "Lio/ktor/util/internal/LockFreeLinkedListNode$AddLastDesc;", "describeAddLast", "(Lio/ktor/util/internal/LockFreeLinkedListNode;)Lio/ktor/util/internal/LockFreeLinkedListNode$AddLastDesc;", "addLastIf", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lr7/a;)Z", "Lkotlin/Function1;", "predicate", "addLastIfPrev", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lr7/l;)Z", "addLastIfPrevAndIf", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lr7/l;Lr7/a;)Z", LinkHeader.Rel.Next, "addNext", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)Z", "condAdd", "", "tryCondAddNext", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode$CondAddOp;)I", "remove", "()Z", "helpRemove", "Lio/ktor/util/internal/AtomicDesc;", "describeRemove", "()Lio/ktor/util/internal/AtomicDesc;", "removeFirstOrNull", "()Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "describeRemoveFirst", "()Lio/ktor/util/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "removeFirstIfIsInstanceOf", "()Ljava/lang/Object;", "removeFirstIfIsInstanceOfOrPeekIf", "(Lr7/l;)Ljava/lang/Object;", "helpDelete", "prev", "validateNode$ktor_utils", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "validateNode", "", "toString", "()Ljava/lang/String;", "Lio/ktor/util/internal/Removed;", "removed", "()Lio/ktor/util/internal/Removed;", "finishAdd", "finishRemove", "markPrev", "findHead", "_prev", "Lio/ktor/util/internal/OpDescriptor;", "op", "correctPrev", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/OpDescriptor;)Lio/ktor/util/internal/LockFreeLinkedListNode;", "isRemoved", "getNext", "getNextNode", "nextNode", "getPrev", "getPrevNode", "prevNode", "AbstractAtomicDesc", "AddLastDesc", "CondAddOp", "RemoveFirstDesc", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class LockFreeLinkedListNode {
    static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");
    static final /* synthetic */ AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
    volatile /* synthetic */ Object _next = this;
    volatile /* synthetic */ Object _prev = this;
    private volatile /* synthetic */ Object _removedRef = null;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0001\"B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\u0010\n\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0010\u001a\u00020\u000f2\n\u0010\n\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\n\u0010\n\u001a\u00060\u0006j\u0002`\u00072\n\u0010\f\u001a\u00060\u0006j\u0002`\u0007H$¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0014\u001a\u00020\u000b2\n\u0010\n\u001a\u00060\u0006j\u0002`\u00072\n\u0010\f\u001a\u00060\u0006j\u0002`\u0007H$¢\u0006\u0004\b\u0014\u0010\u0013J'\u0010\u0016\u001a\u00020\u00152\n\u0010\n\u001a\u00060\u0006j\u0002`\u00072\n\u0010\f\u001a\u00060\u0006j\u0002`\u0007H$¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u001b\u001a\u00020\u00152\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00182\b\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001f\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00078$X¤\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010!\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00078$X¤\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001e¨\u0006#"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lio/ktor/util/internal/AtomicDesc;", "<init>", "()V", "Lio/ktor/util/internal/OpDescriptor;", "op", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "takeAffectedNode", "(Lio/ktor/util/internal/OpDescriptor;)Lio/ktor/util/internal/LockFreeLinkedListNode;", "affected", "", LinkHeader.Rel.Next, "failure", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Ljava/lang/Object;", "", "retry", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Z", "onPrepare", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "updatedNext", "Lx6/t0;", "finishOnSuccess", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "Lio/ktor/util/internal/AtomicOp;", "prepare", "(Lio/ktor/util/internal/AtomicOp;)Ljava/lang/Object;", "complete", "(Lio/ktor/util/internal/AtomicOp;Ljava/lang/Object;)V", "getAffectedNode", "()Lio/ktor/util/internal/LockFreeLinkedListNode;", "affectedNode", "getOriginalNext", "originalNext", "PrepareOp", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class AbstractAtomicDesc extends AtomicDesc {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc$PrepareOp;", "Lio/ktor/util/internal/OpDescriptor;", LinkHeader.Rel.Next, "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "op", "Lio/ktor/util/internal/AtomicOp;", "desc", "Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/AtomicOp;Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;)V", "perform", "", "affected", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class PrepareOp extends OpDescriptor {
            public final AbstractAtomicDesc desc;
            public final LockFreeLinkedListNode next;
            public final AtomicOp<LockFreeLinkedListNode> op;

            /* JADX WARN: Multi-variable type inference failed */
            public PrepareOp(LockFreeLinkedListNode lockFreeLinkedListNode, AtomicOp<? super LockFreeLinkedListNode> atomicOp, AbstractAtomicDesc abstractAtomicDesc) {
                this.next = lockFreeLinkedListNode;
                this.op = atomicOp;
                this.desc = abstractAtomicDesc;
            }

            @Override // io.ktor.util.internal.OpDescriptor
            public Object perform(Object affected) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) affected;
                Object objOnPrepare = this.desc.onPrepare(lockFreeLinkedListNode, this.next);
                if (objOnPrepare == null) {
                    Object obj = this.op.isDecided() ? this.next : this.op;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
                    while (!atomicReferenceFieldUpdater.compareAndSet(lockFreeLinkedListNode, this, obj) && atomicReferenceFieldUpdater.get(lockFreeLinkedListNode) == this) {
                    }
                    return null;
                }
                if (objOnPrepare == LockFreeLinkedListKt.REMOVE_PREPARED) {
                    Removed removed = this.next.removed();
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = LockFreeLinkedListNode._next$FU;
                    while (!atomicReferenceFieldUpdater2.compareAndSet(lockFreeLinkedListNode, this, removed)) {
                        if (atomicReferenceFieldUpdater2.get(lockFreeLinkedListNode) != this) {
                            return objOnPrepare;
                        }
                    }
                    lockFreeLinkedListNode.helpDelete();
                    return objOnPrepare;
                }
                this.op.tryDecide(objOnPrepare);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = LockFreeLinkedListNode._next$FU;
                LockFreeLinkedListNode lockFreeLinkedListNode2 = this.next;
                while (!atomicReferenceFieldUpdater3.compareAndSet(lockFreeLinkedListNode, this, lockFreeLinkedListNode2)) {
                    if (atomicReferenceFieldUpdater3.get(lockFreeLinkedListNode) != this) {
                        return objOnPrepare;
                    }
                }
                return objOnPrepare;
            }
        }

        @Override // io.ktor.util.internal.AtomicDesc
        public final void complete(AtomicOp<?> op, Object failure) {
            boolean z = failure == null;
            LockFreeLinkedListNode this$0 = getThis$0();
            if (this$0 == null) {
                if (z) {
                    throw new IllegalStateException("Check failed.");
                }
                return;
            }
            LockFreeLinkedListNode queue = getQueue();
            if (queue == null) {
                if (z) {
                    throw new IllegalStateException("Check failed.");
                }
                return;
            }
            Object objUpdatedNext = z ? updatedNext(this$0, queue) : queue;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this$0, op, objUpdatedNext)) {
                if (atomicReferenceFieldUpdater.get(this$0) != op) {
                    return;
                }
            }
            if (z) {
                finishOnSuccess(this$0, queue);
            }
        }

        public Object failure(LockFreeLinkedListNode affected, Object next) {
            return null;
        }

        public abstract void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next);

        /* JADX INFO: renamed from: getAffectedNode */
        public abstract LockFreeLinkedListNode getThis$0();

        /* JADX INFO: renamed from: getOriginalNext */
        public abstract LockFreeLinkedListNode getQueue();

        public abstract Object onPrepare(LockFreeLinkedListNode affected, LockFreeLinkedListNode next);

        @Override // io.ktor.util.internal.AtomicDesc
        public final Object prepare(AtomicOp<?> op) {
            while (true) {
                LockFreeLinkedListNode lockFreeLinkedListNodeTakeAffectedNode = takeAffectedNode(op);
                Object obj = lockFreeLinkedListNodeTakeAffectedNode._next;
                if (obj == op || op.isDecided()) {
                    return null;
                }
                if (obj instanceof OpDescriptor) {
                    ((OpDescriptor) obj).perform(lockFreeLinkedListNodeTakeAffectedNode);
                } else {
                    Object objFailure = failure(lockFreeLinkedListNodeTakeAffectedNode, obj);
                    if (objFailure != null) {
                        return objFailure;
                    }
                    if (retry(lockFreeLinkedListNodeTakeAffectedNode, obj)) {
                        continue;
                    } else {
                        PrepareOp prepareOp = new PrepareOp((LockFreeLinkedListNode) obj, op, this);
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
                        while (true) {
                            if (atomicReferenceFieldUpdater.compareAndSet(lockFreeLinkedListNodeTakeAffectedNode, obj, prepareOp)) {
                                Object objPerform = prepareOp.perform(lockFreeLinkedListNodeTakeAffectedNode);
                                if (objPerform != LockFreeLinkedListKt.REMOVE_PREPARED) {
                                    return objPerform;
                                }
                            } else if (atomicReferenceFieldUpdater.get(lockFreeLinkedListNodeTakeAffectedNode) != obj) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        public boolean retry(LockFreeLinkedListNode affected, Object next) {
            return false;
        }

        public LockFreeLinkedListNode takeAffectedNode(OpDescriptor op) {
            return getThis$0();
        }

        public abstract Object updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next);
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\f\b\u0000\u0010\u0003*\u00060\u0001j\u0002`\u00022\u00020\u0004B\u001b\u0012\n\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\u000b\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\n\u001a\u00020\tH\u0004¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0011\u001a\u00020\u00102\n\u0010\r\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0014¢\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\n\u0010\r\u001a\u00060\u0001j\u0002`\u00022\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0002H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0015\u001a\u00020\u000e2\n\u0010\r\u001a\u00060\u0001j\u0002`\u00022\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0002H\u0014¢\u0006\u0004\b\u0015\u0010\u0014J'\u0010\u0017\u001a\u00020\u00162\n\u0010\r\u001a\u00060\u0001j\u0002`\u00022\n\u0010\u000f\u001a\u00060\u0001j\u0002`\u0002H\u0014¢\u0006\u0004\b\u0017\u0010\bR\u0018\u0010\u0005\u001a\u00060\u0001j\u0002`\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u0014\u0010\u0006\u001a\u00028\u00008\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0018R\u001c\u0010\u001b\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00028DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001d\u001a\u00060\u0001j\u0002`\u00028DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001a¨\u0006\u001e"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListNode$AddLastDesc;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "T", "Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "node", "<init>", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "Lio/ktor/util/internal/OpDescriptor;", "op", "takeAffectedNode", "(Lio/ktor/util/internal/OpDescriptor;)Lio/ktor/util/internal/LockFreeLinkedListNode;", "affected", "", LinkHeader.Rel.Next, "", "retry", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Z", "onPrepare", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "updatedNext", "Lx6/t0;", "finishOnSuccess", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "getAffectedNode", "()Lio/ktor/util/internal/LockFreeLinkedListNode;", "affectedNode", "getOriginalNext", "originalNext", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class AddLastDesc<T extends LockFreeLinkedListNode> extends AbstractAtomicDesc {
        private static final /* synthetic */ AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(AddLastDesc.class, Object.class, "_affectedNode");
        private volatile /* synthetic */ Object _affectedNode;
        public final T node;
        public final LockFreeLinkedListNode queue;

        public AddLastDesc(LockFreeLinkedListNode lockFreeLinkedListNode, T t2) {
            this.queue = lockFreeLinkedListNode;
            this.node = t2;
            if (t2._next != t2 || t2._prev != t2) {
                throw new IllegalStateException("Check failed.");
            }
            this._affectedNode = null;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            this.node.finishAdd(this.queue);
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /* JADX INFO: renamed from: getAffectedNode */
        public final LockFreeLinkedListNode getThis$0() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /* JADX INFO: renamed from: getOriginalNext, reason: from getter */
        public final LockFreeLinkedListNode getQueue() {
            return this.queue;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _affectedNode$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, affected) && atomicReferenceFieldUpdater.get(this) == null) {
            }
            return null;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public boolean retry(LockFreeLinkedListNode affected, Object next) {
            return next != this.queue;
        }

        /* JADX WARN: Code restructure failed: missing block: B:6:0x000f, code lost:
        
            return r0;
         */
        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final io.ktor.util.internal.LockFreeLinkedListNode takeAffectedNode(io.ktor.util.internal.OpDescriptor r5) {
            /*
                r4 = this;
            L0:
                io.ktor.util.internal.LockFreeLinkedListNode r0 = r4.queue
                java.lang.Object r0 = r0._prev
                io.ktor.util.internal.LockFreeLinkedListNode r0 = (io.ktor.util.internal.LockFreeLinkedListNode) r0
                java.lang.Object r1 = r0._next
                io.ktor.util.internal.LockFreeLinkedListNode r2 = r4.queue
                if (r1 != r2) goto Ld
                goto Lf
            Ld:
                if (r1 != r5) goto L10
            Lf:
                return r0
            L10:
                boolean r3 = r1 instanceof io.ktor.util.internal.OpDescriptor
                if (r3 == 0) goto L1a
                io.ktor.util.internal.OpDescriptor r1 = (io.ktor.util.internal.OpDescriptor) r1
                r1.perform(r0)
                goto L0
            L1a:
                io.ktor.util.internal.LockFreeLinkedListNode r0 = io.ktor.util.internal.LockFreeLinkedListNode.access$correctPrev(r2, r0, r5)
                if (r0 == 0) goto L0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.internal.LockFreeLinkedListNode.AddLastDesc.takeAffectedNode(io.ktor.util.internal.OpDescriptor):io.ktor.util.internal.LockFreeLinkedListNode");
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            T t2 = this.node;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._prev$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(t2, t2, affected) && atomicReferenceFieldUpdater.get(t2) == t2) {
            }
            T t10 = this.node;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = LockFreeLinkedListNode._next$FU;
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            while (!atomicReferenceFieldUpdater2.compareAndSet(t10, t10, lockFreeLinkedListNode) && atomicReferenceFieldUpdater2.get(t10) == t10) {
            }
            return this.node;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b!\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J%\u0010\u000b\u001a\u00020\n2\n\u0010\u0007\u001a\u00060\u0002j\u0002`\u00032\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR\u001e\u0010\u000e\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListNode$CondAddOp;", "Lio/ktor/util/internal/AtomicOp;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "newNode", "<init>", "(Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "affected", "", "failure", "Lx6/t0;", "complete", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Ljava/lang/Object;)V", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "oldNext", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {
        public final LockFreeLinkedListNode newNode;
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.newNode = lockFreeLinkedListNode;
        }

        @Override // io.ktor.util.internal.AtomicOp
        public void complete(LockFreeLinkedListNode affected, Object failure) {
            boolean z = failure == null;
            LockFreeLinkedListNode lockFreeLinkedListNode = z ? this.newNode : this.oldNext;
            if (lockFreeLinkedListNode != null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(affected, this, lockFreeLinkedListNode)) {
                    if (atomicReferenceFieldUpdater.get(affected) != this) {
                        return;
                    }
                }
                if (z) {
                    this.newNode.finishAdd(this.oldNext);
                }
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\t\u001a\u00020\bH\u0004¢\u0006\u0004\b\n\u0010\u000bJ%\u0010\u000f\u001a\u0004\u0018\u00010\r2\n\u0010\f\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0014¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\u00020\u00122\n\u0010\f\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0004¢\u0006\u0004\b\u0015\u0010\u0016J)\u0010\u0017\u001a\u0004\u0018\u00010\r2\n\u0010\f\u001a\u00060\u0003j\u0002`\u00042\n\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004H\u0004¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\r2\n\u0010\f\u001a\u00060\u0003j\u0002`\u00042\n\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004H\u0004¢\u0006\u0004\b\u0019\u0010\u0018J'\u0010\u001b\u001a\u00020\u001a2\n\u0010\f\u001a\u00060\u0003j\u0002`\u00042\n\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004H\u0004¢\u0006\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0005\u001a\u00060\u0003j\u0002`\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u0017\u0010\"\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010%\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u001c\u0010'\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b&\u0010$¨\u0006("}, d2 = {"Lio/ktor/util/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "T", "Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "queue", "<init>", "(Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "Lio/ktor/util/internal/OpDescriptor;", "op", "takeAffectedNode", "(Lio/ktor/util/internal/OpDescriptor;)Lio/ktor/util/internal/LockFreeLinkedListNode;", "affected", "", LinkHeader.Rel.Next, "failure", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Ljava/lang/Object;", "node", "", "validatePrepared", "(Ljava/lang/Object;)Z", "retry", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Ljava/lang/Object;)Z", "onPrepare", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)Ljava/lang/Object;", "updatedNext", "Lx6/t0;", "finishOnSuccess", "(Lio/ktor/util/internal/LockFreeLinkedListNode;Lio/ktor/util/internal/LockFreeLinkedListNode;)V", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "getResult", "()Ljava/lang/Object;", "getResult$annotations", "()V", "result", "getAffectedNode", "()Lio/ktor/util/internal/LockFreeLinkedListNode;", "affectedNode", "getOriginalNext", "originalNext", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class RemoveFirstDesc<T> extends AbstractAtomicDesc {
        private static final /* synthetic */ AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_affectedNode");
        private static final /* synthetic */ AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_originalNext");
        private volatile /* synthetic */ Object _affectedNode = null;
        private volatile /* synthetic */ Object _originalNext = null;
        public final LockFreeLinkedListNode queue;

        public RemoveFirstDesc(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.queue = lockFreeLinkedListNode;
        }

        public static /* synthetic */ void getResult$annotations() {
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object failure(LockFreeLinkedListNode affected, Object next) {
            if (affected == this.queue) {
                return LockFreeLinkedListKt.getLIST_EMPTY();
            }
            return null;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            affected.finishRemove(next);
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /* JADX INFO: renamed from: getAffectedNode */
        public final LockFreeLinkedListNode getThis$0() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        /* JADX INFO: renamed from: getOriginalNext */
        public final LockFreeLinkedListNode getQueue() {
            return (LockFreeLinkedListNode) this._originalNext;
        }

        public final T getResult() {
            return (T) getThis$0();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final Object onPrepare(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            if (affected instanceof LockFreeLinkedListHead) {
                throw new IllegalStateException("Check failed.");
            }
            if (!validatePrepared(affected)) {
                return LockFreeLinkedListKt.REMOVE_PREPARED;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _affectedNode$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, affected) && atomicReferenceFieldUpdater.get(this) == null) {
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _originalNext$FU;
            while (!atomicReferenceFieldUpdater2.compareAndSet(this, null, next) && atomicReferenceFieldUpdater2.get(this) == null) {
            }
            return null;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final boolean retry(LockFreeLinkedListNode affected, Object next) {
            if (!(next instanceof Removed)) {
                return false;
            }
            affected.helpDelete();
            return true;
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final LockFreeLinkedListNode takeAffectedNode(OpDescriptor op) {
            return (LockFreeLinkedListNode) this.queue.getNext();
        }

        @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final Object updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
            return next.removed();
        }

        public boolean validatePrepared(T node) {
            return true;
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.internal.LockFreeLinkedListNode$makeCondAddOp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007"}, d2 = {"io/ktor/util/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lio/ktor/util/internal/LockFreeLinkedListNode$CondAddOp;", "prepare", "", "affected", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 176)
    public static final class C19521 extends CondAddOp {
        final /* synthetic */ a<Boolean> $condition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C19521(LockFreeLinkedListNode lockFreeLinkedListNode, a<Boolean> aVar) {
            super(lockFreeLinkedListNode);
            this.$condition = aVar;
        }

        @Override // io.ktor.util.internal.AtomicOp
        public Object prepare(LockFreeLinkedListNode affected) {
            if (((Boolean) this.$condition.invoke()).booleanValue()) {
                return null;
            }
            return LockFreeLinkedListKt.getCONDITION_FALSE();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0017, code lost:
    
        r7.markPrev();
        r3 = io.ktor.util.internal.LockFreeLinkedListNode._next$FU;
        r2 = ((io.ktor.util.internal.Removed) r2).ref;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0024, code lost:
    
        if (r3.compareAndSet(r1, r7, r2) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002b, code lost:
    
        if (r3.get(r1) == r7) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.ktor.util.internal.LockFreeLinkedListNode correctPrev(io.ktor.util.internal.LockFreeLinkedListNode r7, io.ktor.util.internal.OpDescriptor r8) {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            java.lang.Object r2 = r7._next
            if (r2 != r8) goto L7
            return r7
        L7:
            boolean r3 = r2 instanceof io.ktor.util.internal.OpDescriptor
            if (r3 == 0) goto L11
            io.ktor.util.internal.OpDescriptor r2 = (io.ktor.util.internal.OpDescriptor) r2
            r2.perform(r7)
            goto L2
        L11:
            boolean r3 = r2 instanceof io.ktor.util.internal.Removed
            if (r3 == 0) goto L36
            if (r1 == 0) goto L2f
            r7.markPrev()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = io.ktor.util.internal.LockFreeLinkedListNode._next$FU
            io.ktor.util.internal.Removed r2 = (io.ktor.util.internal.Removed) r2
            io.ktor.util.internal.LockFreeLinkedListNode r2 = r2.ref
        L20:
            boolean r4 = r3.compareAndSet(r1, r7, r2)
            if (r4 == 0) goto L27
            goto L2d
        L27:
            java.lang.Object r4 = r3.get(r1)
            if (r4 == r7) goto L20
        L2d:
            r7 = r1
            goto L1
        L2f:
            java.lang.Object r7 = r7._prev
            io.ktor.util.internal.LockFreeLinkedListNode r7 = io.ktor.util.internal.LockFreeLinkedListKt.unwrap(r7)
            goto L2
        L36:
            java.lang.Object r3 = r6._prev
            boolean r4 = r3 instanceof io.ktor.util.internal.Removed
            if (r4 == 0) goto L3d
            return r0
        L3d:
            if (r2 == r6) goto L46
            r1 = r2
            io.ktor.util.internal.LockFreeLinkedListNode r1 = (io.ktor.util.internal.LockFreeLinkedListNode) r1
            r5 = r1
            r1 = r7
            r7 = r5
            goto L2
        L46:
            if (r3 != r7) goto L49
            return r0
        L49:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = io.ktor.util.internal.LockFreeLinkedListNode._prev$FU
        L4b:
            boolean r4 = r2.compareAndSet(r6, r3, r7)
            if (r4 == 0) goto L58
            java.lang.Object r2 = r7._prev
            boolean r2 = r2 instanceof io.ktor.util.internal.Removed
            if (r2 != 0) goto L2
            return r0
        L58:
            java.lang.Object r4 = r2.get(r6)
            if (r4 == r3) goto L4b
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.internal.LockFreeLinkedListNode.correctPrev(io.ktor.util.internal.LockFreeLinkedListNode, io.ktor.util.internal.OpDescriptor):io.ktor.util.internal.LockFreeLinkedListNode");
    }

    private final LockFreeLinkedListNode findHead() {
        LockFreeLinkedListNode nextNode = this;
        while (!(nextNode instanceof LockFreeLinkedListHead)) {
            nextNode = nextNode.getNextNode();
            if (nextNode == this) {
                throw new IllegalStateException("Cannot loop to this while looking for list head");
            }
        }
        return nextNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAdd(LockFreeLinkedListNode next) {
        while (true) {
            Object obj = next._prev;
            if ((obj instanceof Removed) || getNext() != next) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _prev$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(next, obj, this)) {
                if (atomicReferenceFieldUpdater.get(next) != obj) {
                    break;
                }
            }
            if (getNext() instanceof Removed) {
                next.correctPrev((LockFreeLinkedListNode) obj, null);
                return;
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishRemove(LockFreeLinkedListNode next) {
        helpDelete();
        next.correctPrev(LockFreeLinkedListKt.unwrap(this._prev), null);
    }

    private final LockFreeLinkedListNode markPrev() {
        while (true) {
            Object obj = this._prev;
            if (obj instanceof Removed) {
                return ((Removed) obj).ref;
            }
            Removed removed = (obj == this ? findHead() : (LockFreeLinkedListNode) obj).removed();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _prev$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, removed)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            return (LockFreeLinkedListNode) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Removed removed() {
        Removed removed = (Removed) this._removedRef;
        if (removed != null) {
            return removed;
        }
        Removed removed2 = new Removed(this);
        _removedRef$FU.lazySet(this, removed2);
        return removed2;
    }

    public final void addLast(LockFreeLinkedListNode node) {
        while (!((LockFreeLinkedListNode) getPrev()).addNext(node, this)) {
        }
    }

    public final boolean addLastIf(LockFreeLinkedListNode node, a<Boolean> condition) {
        int iTryCondAddNext;
        C19521 c19521 = new C19521(node, condition);
        do {
            iTryCondAddNext = ((LockFreeLinkedListNode) getPrev()).tryCondAddNext(node, this, c19521);
            if (iTryCondAddNext == 1) {
                return true;
            }
        } while (iTryCondAddNext != 2);
        return false;
    }

    public final boolean addLastIfPrev(LockFreeLinkedListNode node, l<? super LockFreeLinkedListNode, Boolean> predicate) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        do {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) getPrev();
            if (!((Boolean) predicate.invoke(lockFreeLinkedListNode)).booleanValue()) {
                return false;
            }
        } while (!lockFreeLinkedListNode.addNext(node, this));
        return true;
    }

    public final boolean addLastIfPrevAndIf(LockFreeLinkedListNode node, l<? super LockFreeLinkedListNode, Boolean> predicate, a<Boolean> condition) {
        int iTryCondAddNext;
        C19521 c19521 = new C19521(node, condition);
        do {
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getPrev();
            if (!((Boolean) predicate.invoke(lockFreeLinkedListNode)).booleanValue()) {
                return false;
            }
            iTryCondAddNext = lockFreeLinkedListNode.tryCondAddNext(node, this, c19521);
            if (iTryCondAddNext == 1) {
                return true;
            }
        } while (iTryCondAddNext != 2);
        return false;
    }

    public final boolean addNext(LockFreeLinkedListNode node, LockFreeLinkedListNode next) {
        _prev$FU.lazySet(node, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        atomicReferenceFieldUpdater.lazySet(node, next);
        while (!atomicReferenceFieldUpdater.compareAndSet(this, next, node)) {
            if (atomicReferenceFieldUpdater.get(this) != next) {
                return false;
            }
        }
        node.finishAdd(next);
        return true;
    }

    public final boolean addOneIfEmpty(LockFreeLinkedListNode node) {
        _prev$FU.lazySet(node, this);
        _next$FU.lazySet(node, this);
        while (getNext() == this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, this, node)) {
                if (atomicReferenceFieldUpdater.get(this) != this) {
                    break;
                }
            }
            node.finishAdd(this);
            return true;
        }
        return false;
    }

    public final <T extends LockFreeLinkedListNode> AddLastDesc<T> describeAddLast(T node) {
        return new AddLastDesc<>(this, node);
    }

    public AtomicDesc describeRemove() {
        if (isRemoved()) {
            return null;
        }
        return new AbstractAtomicDesc() { // from class: io.ktor.util.internal.LockFreeLinkedListNode.describeRemove.1
            private static final /* synthetic */ AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(AnonymousClass1.class, Object.class, "_originalNext");
            private volatile /* synthetic */ Object _originalNext = null;

            @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
            public Object failure(LockFreeLinkedListNode affected, Object next) {
                if (next instanceof Removed) {
                    return LockFreeLinkedListKt.getALREADY_REMOVED();
                }
                return null;
            }

            @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
            public void finishOnSuccess(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
                LockFreeLinkedListNode.this.finishRemove(next);
            }

            @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
            /* JADX INFO: renamed from: getAffectedNode, reason: from getter */
            public LockFreeLinkedListNode getThis$0() {
                return LockFreeLinkedListNode.this;
            }

            @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
            /* JADX INFO: renamed from: getOriginalNext */
            public LockFreeLinkedListNode getQueue() {
                return (LockFreeLinkedListNode) this._originalNext;
            }

            @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
            public Object onPrepare(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _originalNext$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, next) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                return null;
            }

            @Override // io.ktor.util.internal.LockFreeLinkedListNode.AbstractAtomicDesc
            public Removed updatedNext(LockFreeLinkedListNode affected, LockFreeLinkedListNode next) {
                return next.removed();
            }
        };
    }

    public final RemoveFirstDesc<LockFreeLinkedListNode> describeRemoveFirst() {
        return new RemoveFirstDesc<>(this);
    }

    public final Object getNext() {
        while (true) {
            Object obj = this._next;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        return LockFreeLinkedListKt.unwrap(getNext());
    }

    public final Object getPrev() {
        while (true) {
            Object obj = this._prev;
            if (obj instanceof Removed) {
                return obj;
            }
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
            if (lockFreeLinkedListNode.getNext() == this) {
                return obj;
            }
            correctPrev(lockFreeLinkedListNode, null);
        }
    }

    public final LockFreeLinkedListNode getPrevNode() {
        return LockFreeLinkedListKt.unwrap(getPrev());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        r0.markPrev();
        r5 = io.ktor.util.internal.LockFreeLinkedListNode._next$FU;
        r4 = ((io.ktor.util.internal.Removed) r4).ref;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
    
        if (r5.compareAndSet(r3, r0, r4) == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        if (r5.get(r3) == r0) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void helpDelete() {
        /*
            r8 = this;
            io.ktor.util.internal.LockFreeLinkedListNode r0 = r8.markPrev()
            java.lang.Object r1 = r8._next
            io.ktor.util.internal.Removed r1 = (io.ktor.util.internal.Removed) r1
            io.ktor.util.internal.LockFreeLinkedListNode r1 = r1.ref
            r2 = 0
        Lb:
            r3 = r2
        Lc:
            java.lang.Object r4 = r1.getNext()
            boolean r5 = r4 instanceof io.ktor.util.internal.Removed
            if (r5 == 0) goto L1c
            r1.markPrev()
            io.ktor.util.internal.Removed r4 = (io.ktor.util.internal.Removed) r4
            io.ktor.util.internal.LockFreeLinkedListNode r1 = r4.ref
            goto Lc
        L1c:
            java.lang.Object r4 = r0.getNext()
            boolean r5 = r4 instanceof io.ktor.util.internal.Removed
            if (r5 == 0) goto L45
            if (r3 == 0) goto L3e
            r0.markPrev()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = io.ktor.util.internal.LockFreeLinkedListNode._next$FU
            io.ktor.util.internal.Removed r4 = (io.ktor.util.internal.Removed) r4
            io.ktor.util.internal.LockFreeLinkedListNode r4 = r4.ref
        L2f:
            boolean r6 = r5.compareAndSet(r3, r0, r4)
            if (r6 == 0) goto L36
            goto L3c
        L36:
            java.lang.Object r6 = r5.get(r3)
            if (r6 == r0) goto L2f
        L3c:
            r0 = r3
            goto Lb
        L3e:
            java.lang.Object r0 = r0._prev
            io.ktor.util.internal.LockFreeLinkedListNode r0 = io.ktor.util.internal.LockFreeLinkedListKt.unwrap(r0)
            goto Lc
        L45:
            if (r4 == r8) goto L51
            r3 = r4
            io.ktor.util.internal.LockFreeLinkedListNode r3 = (io.ktor.util.internal.LockFreeLinkedListNode) r3
            if (r3 != r1) goto L4d
            return
        L4d:
            r7 = r3
            r3 = r0
            r0 = r7
            goto Lc
        L51:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = io.ktor.util.internal.LockFreeLinkedListNode._next$FU
        L53:
            boolean r5 = r4.compareAndSet(r0, r8, r1)
            if (r5 == 0) goto L5a
            return
        L5a:
            java.lang.Object r5 = r4.get(r0)
            if (r5 == r8) goto L53
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.internal.LockFreeLinkedListNode.helpDelete():void");
    }

    public final void helpRemove() {
        Object next = getNext();
        Removed removed = next instanceof Removed ? (Removed) next : null;
        if (removed == null) {
            throw new IllegalStateException("Must be invoked on a removed node");
        }
        finishRemove(removed.ref);
    }

    public final boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public final CondAddOp makeCondAddOp(LockFreeLinkedListNode node, a<Boolean> condition) {
        return new C19521(node, condition);
    }

    public boolean remove() {
        while (true) {
            Object next = getNext();
            if ((next instanceof Removed) || next == this) {
                return false;
            }
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            Removed removed = lockFreeLinkedListNode.removed();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, next, removed)) {
                if (atomicReferenceFieldUpdater.get(this) != next) {
                    break;
                }
            }
            finishRemove(lockFreeLinkedListNode);
            return true;
        }
    }

    public final <T> T removeFirstIfIsInstanceOf() {
        if (((LockFreeLinkedListNode) getNext()) == this) {
            return null;
        }
        p.h();
        throw null;
    }

    public final <T> T removeFirstIfIsInstanceOfOrPeekIf(l<? super T, Boolean> predicate) {
        if (((LockFreeLinkedListNode) getNext()) == this) {
            return null;
        }
        p.h();
        throw null;
    }

    public final LockFreeLinkedListNode removeFirstOrNull() {
        while (true) {
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getNext();
            if (lockFreeLinkedListNode == this) {
                return null;
            }
            if (lockFreeLinkedListNode.remove()) {
                return lockFreeLinkedListNode;
            }
            lockFreeLinkedListNode.helpDelete();
        }
    }

    public String toString() {
        return l0.f19747a.b(getClass()).r() + '@' + hashCode();
    }

    public final int tryCondAddNext(LockFreeLinkedListNode node, LockFreeLinkedListNode next, CondAddOp condAdd) {
        _prev$FU.lazySet(node, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        atomicReferenceFieldUpdater.lazySet(node, next);
        condAdd.oldNext = next;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, next, condAdd)) {
            if (atomicReferenceFieldUpdater.get(this) != next) {
                return 0;
            }
        }
        return condAdd.perform(this) == null ? 1 : 2;
    }

    public final void validateNode$ktor_utils(LockFreeLinkedListNode prev, LockFreeLinkedListNode next) {
        if (prev != this._prev) {
            throw new IllegalStateException("Check failed.");
        }
        if (next != this._next) {
            throw new IllegalStateException("Check failed.");
        }
    }
}
