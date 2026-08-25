package androidx.compose.runtime.changelist;

import androidx.compose.material3.d;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.q0;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\u0003J)\u0010\u0010\u001a\u00020\b2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J-\u0010\u0019\u001a\u00020\b2\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00122\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\b¢\u0006\u0004\b\u001b\u0010\u0003J@\u0010\"\u001a\u00020\b\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u001d2\u0006\u0010\u001e\u001a\u00028\u00002\u001d\u0010!\u001a\u0019\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u001f¢\u0006\u0002\b ¢\u0006\u0004\b\"\u0010#J\u0017\u0010&\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010*R\u0011\u0010.\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006/"}, d2 = {"Landroidx/compose/runtime/changelist/FixupList;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "", "isEmpty", "()Z", "isNotEmpty", "Lx6/t0;", "clear", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "executeAndFlushAllPendingFixups", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Lkotlin/Function0;", "", "factory", "", "insertIndex", "Landroidx/compose/runtime/Anchor;", "groupAnchor", "createAndInsertNode", "(Lr7/a;ILandroidx/compose/runtime/Anchor;)V", "endNodeInsert", "V", "T", "value", "Lkotlin/Function2;", "Lx6/n;", "block", "updateNode", "(Ljava/lang/Object;Lr7/p;)V", "", "linePrefix", "toDebugString", "(Ljava/lang/String;)Ljava/lang/String;", "Landroidx/compose/runtime/changelist/Operations;", "operations", "Landroidx/compose/runtime/changelist/Operations;", "pendingOperations", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FixupList extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    private final Operations operations = new Operations();
    private final Operations pendingOperations = new Operations();

    public final void clear() {
        this.pendingOperations.clear();
        this.operations.clear();
    }

    public final void createAndInsertNode(a<? extends Object> factory, int insertIndex, Anchor groupAnchor) {
        int i10;
        int i11;
        int i12;
        Operations operations;
        Operations operations2 = this.operations;
        Operation.InsertNodeFixup insertNodeFixup = Operation.InsertNodeFixup.INSTANCE;
        operations2.pushOp(insertNodeFixup);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations2);
        int i13 = 0;
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), factory);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(0), insertIndex);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), groupAnchor);
        if (operations2.pushedIntMask == operations2.createExpectedArgMask(insertNodeFixup.getInts()) && operations2.pushedObjectMask == operations2.createExpectedArgMask(insertNodeFixup.getObjects())) {
            i10 = 0;
            i11 = 1;
        } else {
            StringBuilder sb2 = new StringBuilder();
            int ints = insertNodeFixup.getInts();
            i10 = 0;
            i11 = 1;
            int i14 = 0;
            while (i13 < ints) {
                if (((1 << i13) & operations2.pushedIntMask) != 0) {
                    if (i14 > 0) {
                        sb2.append(", ");
                    }
                    operations = operations2;
                    sb2.append(insertNodeFixup.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i13)));
                    i14++;
                } else {
                    operations = operations2;
                }
                i13++;
                operations2 = operations;
            }
            Operations operations3 = operations2;
            String string = sb2.toString();
            StringBuilder sb3 = new StringBuilder();
            int objects = insertNodeFixup.getObjects();
            int i15 = 0;
            int i16 = 0;
            while (i16 < objects) {
                if (((1 << i16) & operations3.pushedObjectMask) != 0) {
                    if (i14 > 0) {
                        sb3.append(", ");
                    }
                    i12 = objects;
                    sb3.append(insertNodeFixup.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i16)));
                    i15++;
                } else {
                    i12 = objects;
                }
                i16++;
                objects = i12;
            }
            String string2 = sb3.toString();
            StringBuilder sb4 = new StringBuilder("Error while pushing ");
            sb4.append(insertNodeFixup);
            sb4.append(". Not all arguments were provided. Missing ");
            sb4.append(i14);
            sb4.append(" int arguments (");
            d.A(sb4, string, ") and ", i15, " object arguments (");
            d.z(sb4, string2, ").");
        }
        Operations operations4 = this.pendingOperations;
        Operation.PostInsertNodeFixup postInsertNodeFixup = Operation.PostInsertNodeFixup.INSTANCE;
        operations4.pushOp(postInsertNodeFixup);
        Operations operationsM3074constructorimpl2 = Operations.WriteScope.m3074constructorimpl(operations4);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl2, Operation.IntParameter.m3031constructorimpl(i10), insertIndex);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl2, Operation.ObjectParameter.m3042constructorimpl(i10), groupAnchor);
        if (((operations4.pushedIntMask == operations4.createExpectedArgMask(postInsertNodeFixup.getInts()) && operations4.pushedObjectMask == operations4.createExpectedArgMask(postInsertNodeFixup.getObjects())) ? i11 : i10) == 0) {
            StringBuilder sb5 = new StringBuilder();
            int ints2 = postInsertNodeFixup.getInts();
            int i17 = i10;
            int i18 = i17;
            while (i17 < ints2) {
                if (((i11 << i17) & operations4.pushedIntMask) != 0) {
                    if (i18 > 0) {
                        sb5.append(", ");
                    }
                    sb5.append(postInsertNodeFixup.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i17)));
                    i18++;
                }
                i17++;
            }
            String string3 = sb5.toString();
            StringBuilder sb6 = new StringBuilder();
            int objects2 = postInsertNodeFixup.getObjects();
            int i19 = i10;
            int i20 = i19;
            while (i19 < objects2) {
                if (((i11 << i19) & operations4.pushedObjectMask) != 0) {
                    if (i18 > 0) {
                        sb6.append(", ");
                    }
                    sb6.append(postInsertNodeFixup.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i19)));
                    i20++;
                }
                i19++;
            }
            String string4 = sb6.toString();
            StringBuilder sb7 = new StringBuilder("Error while pushing ");
            sb7.append(postInsertNodeFixup);
            sb7.append(". Not all arguments were provided. Missing ");
            sb7.append(i18);
            sb7.append(" int arguments (");
            d.A(sb7, string3, ") and ", i20, " object arguments (");
            d.z(sb7, string4, ").");
        }
    }

    public final void endNodeInsert() {
        if (!this.pendingOperations.isNotEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Cannot end node insertion, there are no pending operations that can be realized.");
        }
        this.pendingOperations.popInto(this.operations);
    }

    public final void executeAndFlushAllPendingFixups(Applier<?> applier, SlotWriter slots, RememberManager rememberManager) {
        if (!this.pendingOperations.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("FixupList has pending fixup operations that were not realized. Were there mismatched insertNode() and endNodeInsert() calls?");
        }
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager);
    }

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("FixupList instance containing " + getSize() + " operations");
        if (sb2.length() > 0) {
            sb2.append(":\n" + this.operations.toDebugString(linePrefix));
        }
        return sb2.toString();
    }

    public final <V, T> void updateNode(V value, p<? super T, ? super V, t0> block) {
        Operations operations = this.operations;
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        operations.pushOp(updateNode);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), value);
        int iM3042constructorimpl = Operation.ObjectParameter.m3042constructorimpl(1);
        q0.e(2, block);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, iM3042constructorimpl, block);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateNode.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = updateNode.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateNode.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = updateNode.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(updateNode.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(updateNode);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }
}
