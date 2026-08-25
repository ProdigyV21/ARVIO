package androidx.compose.runtime.changelist;

import ab.k;
import androidx.compose.material3.d;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import q7.g;
import r7.l;
import s7.a;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u000f\b\u0000\u0018\u0000 [2\u00020\u0001:\u0003[\\]B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\u0003J\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\rJ>\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0011H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u000e\u0010\u0013J\r\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0003J\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017J-\u0010\u001a\u001a\u00020\b2\u001b\u0010\u0019\u001a\u0017\u0012\b\u0012\u00060\u0018R\u00020\u0000\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0004\b\u001a\u0010\u001bJ-\u0010\u001d\u001a\u00020\b2\u001b\u0010\u001c\u001a\u0017\u0012\b\u0012\u00060\u0018R\u00020\u0000\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0004\b\u001d\u0010\u001bJ)\u0010$\u001a\u00020\b2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%J\u000f\u0010'\u001a\u00020&H\u0017¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020&2\u0006\u0010)\u001a\u00020&H\u0016¢\u0006\u0004\b*\u0010+J\u001f\u0010/\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,H\u0002¢\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020\b2\u0006\u0010.\u001a\u00020,H\u0002¢\u0006\u0004\b1\u00102J\u0017\u00103\u001a\u00020\b2\u0006\u0010.\u001a\u00020,H\u0002¢\u0006\u0004\b3\u00102J\u0017\u00105\u001a\u00020,2\u0006\u00104\u001a\u00020,H\u0002¢\u0006\u0004\b5\u00106J\u0013\u00107\u001a\u00020&*\u00020&H\u0002¢\u0006\u0004\b7\u0010+J\u000f\u00108\u001a\u00020\nH\u0002¢\u0006\u0004\b8\u00109J\u001a\u0010=\u001a\u00020,2\u0006\u0010;\u001a\u00020:H\u0002ø\u0001\u0000¢\u0006\u0004\b<\u00106J\u001e\u0010@\u001a\u00020,2\n\u0010;\u001a\u0006\u0012\u0002\b\u00030>H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u00106J\u001f\u0010A\u001a\u00020&*\u00060\u0018R\u00020\u00002\u0006\u0010)\u001a\u00020&H\u0002¢\u0006\u0004\bA\u0010BJ\u001d\u0010D\u001a\u00020&*\u0004\u0018\u00010C2\u0006\u0010)\u001a\u00020&H\u0002¢\u0006\u0004\bD\u0010EJ'\u0010H\u001a\u00020&\"\u0004\b\u0000\u0010F*\b\u0012\u0004\u0012\u00028\u00000G2\u0006\u0010)\u001a\u00020&H\u0002¢\u0006\u0004\bH\u0010IR\u001e\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010P\u001a\u00020O8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010NR\u001e\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010C0J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010NR\u0016\u0010V\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010NR\u0016\u0010W\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010NR\u0011\u0010Z\u001a\u00020,8F¢\u0006\u0006\u001a\u0004\bX\u0010Y\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006^"}, d2 = {"Landroidx/compose/runtime/changelist/Operations;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "", "isEmpty", "()Z", "isNotEmpty", "Lx6/t0;", "clear", "Landroidx/compose/runtime/changelist/Operation;", "operation", "pushOp", "(Landroidx/compose/runtime/changelist/Operation;)V", "push", "Lkotlin/Function1;", "Landroidx/compose/runtime/changelist/Operations$WriteScope;", "Lx6/n;", "args", "(Landroidx/compose/runtime/changelist/Operation;Lr7/l;)V", "pop", "other", "popInto", "(Landroidx/compose/runtime/changelist/Operations;)V", "Landroidx/compose/runtime/changelist/Operations$OpIterator;", "sink", "drain", "(Lr7/l;)V", "action", "forEach", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "executeAndFlushAllPendingOperations", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "", "toString", "()Ljava/lang/String;", "linePrefix", "toDebugString", "(Ljava/lang/String;)Ljava/lang/String;", "", "currentSize", "requiredSize", "determineNewSize", "(II)I", "ensureIntArgsSizeAtLeast", "(I)V", "ensureObjectArgsSizeAtLeast", "paramCount", "createExpectedArgMask", "(I)I", "indent", "peekOperation", "()Landroidx/compose/runtime/changelist/Operation;", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "topIntIndexOf-w8GmfQM", "topIntIndexOf", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "topObjectIndexOf-31yXWZQ", "topObjectIndexOf", "currentOpToDebugString", "(Landroidx/compose/runtime/changelist/Operations$OpIterator;Ljava/lang/String;)Ljava/lang/String;", "", "formatOpArgumentToString", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "T", "", "toCollectionString", "(Ljava/lang/Iterable;Ljava/lang/String;)Ljava/lang/String;", "", "opCodes", "[Landroidx/compose/runtime/changelist/Operation;", "opCodesSize", "I", "", "intArgs", "[I", "intArgsSize", "objectArgs", "[Ljava/lang/Object;", "objectArgsSize", "pushedIntMask", "pushedObjectMask", "getSize", "()I", ContentDisposition.Parameters.Size, "Companion", "OpIterator", "WriteScope", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Operations extends OperationsDebugStringFormattable {
    public static final int InitialCapacity = 16;
    private static final int MaxResizeAmount = 1024;
    private int intArgsSize;
    private int objectArgsSize;
    private int opCodesSize;
    private int pushedIntMask;
    private int pushedObjectMask;
    public static final int $stable = 8;
    private Operation[] opCodes = new Operation[16];
    private int[] intArgs = new int[16];
    private Object[] objectArgs = new Object[16];

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/changelist/Operations$OpIterator;", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "(Landroidx/compose/runtime/changelist/Operations;)V", "intIdx", "", "objIdx", "opIdx", "operation", "Landroidx/compose/runtime/changelist/Operation;", "getOperation", "()Landroidx/compose/runtime/changelist/Operation;", "getInt", "parameter", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "getInt-w8GmfQM", "(I)I", "getObject", "T", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "getObject-31yXWZQ", "(I)Ljava/lang/Object;", LinkHeader.Rel.Next, "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class OpIterator implements OperationArgContainer {
        private int intIdx;
        private int objIdx;
        private int opIdx;

        public OpIterator() {
        }

        @Override // androidx.compose.runtime.changelist.OperationArgContainer
        /* JADX INFO: renamed from: getInt-w8GmfQM */
        public int mo3067getIntw8GmfQM(int parameter) {
            return Operations.this.intArgs[this.intIdx + parameter];
        }

        @Override // androidx.compose.runtime.changelist.OperationArgContainer
        /* JADX INFO: renamed from: getObject-31yXWZQ */
        public <T> T mo3068getObject31yXWZQ(int parameter) {
            return (T) Operations.this.objectArgs[this.objIdx + parameter];
        }

        public final Operation getOperation() {
            return Operations.this.opCodes[this.opIdx];
        }

        public final boolean next() {
            if (this.opIdx >= Operations.this.opCodesSize) {
                return false;
            }
            Operation operation = getOperation();
            this.intIdx = operation.getInts() + this.intIdx;
            this.objIdx = operation.getObjects() + this.objIdx;
            int i10 = this.opIdx + 1;
            this.opIdx = i10;
            return i10 < Operations.this.opCodesSize;
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\r\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ,\u0010\u0012\u001a\u00020\n\"\u0004\b\u0000\u0010\u000e2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\t\u001a\u00028\u0000ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0016\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0019\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0011\u0010#\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/runtime/changelist/Operations$WriteScope;", "", "Landroidx/compose/runtime/changelist/Operations;", "stack", "constructor-impl", "(Landroidx/compose/runtime/changelist/Operations;)Landroidx/compose/runtime/changelist/Operations;", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "parameter", "", "value", "Lx6/t0;", "setInt-A6tL2VI", "(Landroidx/compose/runtime/changelist/Operations;II)V", "setInt", "T", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "setObject-DKhxnng", "(Landroidx/compose/runtime/changelist/Operations;ILjava/lang/Object;)V", "setObject", "", "toString-impl", "(Landroidx/compose/runtime/changelist/Operations;)Ljava/lang/String;", "toString", "hashCode-impl", "(Landroidx/compose/runtime/changelist/Operations;)I", "hashCode", "other", "", "equals-impl", "(Landroidx/compose/runtime/changelist/Operations;Ljava/lang/Object;)Z", "equals", "Landroidx/compose/runtime/changelist/Operations;", "Landroidx/compose/runtime/changelist/Operation;", "getOperation-impl", "(Landroidx/compose/runtime/changelist/Operations;)Landroidx/compose/runtime/changelist/Operation;", "operation", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @g
    public static final class WriteScope {
        private final Operations stack;

        private /* synthetic */ WriteScope(Operations operations) {
            this.stack = operations;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ WriteScope m3073boximpl(Operations operations) {
            return new WriteScope(operations);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static Operations m3074constructorimpl(Operations operations) {
            return operations;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m3075equalsimpl(Operations operations, Object obj) {
            return (obj instanceof WriteScope) && p.a(operations, ((WriteScope) obj).getStack());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m3076equalsimpl0(Operations operations, Operations operations2) {
            return p.a(operations, operations2);
        }

        /* JADX INFO: renamed from: getOperation-impl, reason: not valid java name */
        public static final Operation m3077getOperationimpl(Operations operations) {
            return operations.peekOperation();
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m3078hashCodeimpl(Operations operations) {
            return operations.hashCode();
        }

        /* JADX INFO: renamed from: setInt-A6tL2VI, reason: not valid java name */
        public static final void m3079setIntA6tL2VI(Operations operations, int i10, int i11) {
            int i12 = 1 << i10;
            if (!((operations.pushedIntMask & i12) == 0)) {
                PreconditionsKt.throwIllegalStateException("Already pushed argument " + m3077getOperationimpl(operations).mo3003intParamNamew8GmfQM(i10));
            }
            operations.pushedIntMask |= i12;
            operations.intArgs[operations.m3071topIntIndexOfw8GmfQM(i10)] = i11;
        }

        /* JADX INFO: renamed from: setObject-DKhxnng, reason: not valid java name */
        public static final <T> void m3080setObjectDKhxnng(Operations operations, int i10, T t2) {
            int i11 = 1 << i10;
            if (!((operations.pushedObjectMask & i11) == 0)) {
                PreconditionsKt.throwIllegalStateException("Already pushed argument " + m3077getOperationimpl(operations).mo3004objectParamName31yXWZQ(i10));
            }
            operations.pushedObjectMask |= i11;
            operations.objectArgs[operations.m3072topObjectIndexOf31yXWZQ(i10)] = t2;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m3081toStringimpl(Operations operations) {
            return "WriteScope(stack=" + operations + ')';
        }

        public boolean equals(Object obj) {
            return m3075equalsimpl(this.stack, obj);
        }

        public int hashCode() {
            return m3078hashCodeimpl(this.stack);
        }

        public String toString() {
            return m3081toStringimpl(this.stack);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ Operations getStack() {
            return this.stack;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.changelist.Operations$toCollectionString$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/CharSequence;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1<T> extends r implements l<T, CharSequence> {
        final /* synthetic */ String $linePrefix;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str) {
            super(1);
            this.$linePrefix = str;
        }

        @Override // r7.l
        public final CharSequence invoke(T t2) {
            return Operations.this.formatOpArgumentToString(t2, this.$linePrefix);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int createExpectedArgMask(int paramCount) {
        if (paramCount == 0) {
            return 0;
        }
        return (-1) >>> (32 - paramCount);
    }

    private final String currentOpToDebugString(OpIterator opIterator, String str) {
        Operation operation = opIterator.getOperation();
        if (operation.getInts() == 0 && operation.getObjects() == 0) {
            return operation.getName();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(operation.getName());
        sb2.append('(');
        String strIndent = indent(str);
        int ints = operation.getInts();
        boolean z = true;
        for (int i10 = 0; i10 < ints; i10++) {
            int iM3031constructorimpl = Operation.IntParameter.m3031constructorimpl(i10);
            String strMo3003intParamNamew8GmfQM = operation.mo3003intParamNamew8GmfQM(iM3031constructorimpl);
            if (z) {
                z = false;
            } else {
                sb2.append(", ");
            }
            sb2.append('\n');
            sb2.append(strIndent);
            sb2.append(strMo3003intParamNamew8GmfQM);
            sb2.append(" = ");
            sb2.append(opIterator.mo3067getIntw8GmfQM(iM3031constructorimpl));
        }
        int objects = operation.getObjects();
        for (int i11 = 0; i11 < objects; i11++) {
            int iM3042constructorimpl = Operation.ObjectParameter.m3042constructorimpl(i11);
            String strMo3004objectParamName31yXWZQ = operation.mo3004objectParamName31yXWZQ(iM3042constructorimpl);
            if (z) {
                z = false;
            } else {
                sb2.append(", ");
            }
            sb2.append('\n');
            sb2.append(strIndent);
            sb2.append(strMo3004objectParamName31yXWZQ);
            sb2.append(" = ");
            sb2.append(formatOpArgumentToString(opIterator.mo3068getObject31yXWZQ(iM3042constructorimpl), strIndent));
        }
        sb2.append('\n');
        sb2.append(str);
        sb2.append(")");
        return sb2.toString();
    }

    private final int determineNewSize(int currentSize, int requiredSize) {
        int i10 = currentSize + (currentSize <= 1024 ? currentSize : 1024);
        return i10 < requiredSize ? requiredSize : i10;
    }

    private final void ensureIntArgsSizeAtLeast(int requiredSize) {
        int[] iArr = this.intArgs;
        int length = iArr.length;
        if (requiredSize > length) {
            this.intArgs = Arrays.copyOf(iArr, determineNewSize(length, requiredSize));
        }
    }

    private final void ensureObjectArgsSizeAtLeast(int requiredSize) {
        Object[] objArr = this.objectArgs;
        int length = objArr.length;
        if (requiredSize > length) {
            this.objectArgs = Arrays.copyOf(objArr, determineNewSize(length, requiredSize));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatOpArgumentToString(Object obj, String str) {
        if (obj == null) {
            return "null";
        }
        boolean z = obj instanceof Object[];
        a kVar = z.f19728i;
        if (z) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length != 0) {
                kVar = new k(objArr, 2);
            }
            return toCollectionString(kVar, str);
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            if (iArr.length != 0) {
                kVar = new k(iArr, 3);
            }
            return toCollectionString(kVar, str);
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            if (jArr.length != 0) {
                kVar = new k(jArr, 4);
            }
            return toCollectionString(kVar, str);
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            if (fArr.length != 0) {
                kVar = new k(fArr, 5);
            }
            return toCollectionString(kVar, str);
        }
        if (!(obj instanceof double[])) {
            return obj instanceof Iterable ? toCollectionString((Iterable) obj, str) : obj instanceof OperationsDebugStringFormattable ? ((OperationsDebugStringFormattable) obj).toDebugString(str) : obj.toString();
        }
        double[] dArr = (double[]) obj;
        if (dArr.length != 0) {
            kVar = new k(dArr, 6);
        }
        return toCollectionString(kVar, str);
    }

    private final String indent(String str) {
        return d.m(str, "    ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Operation peekOperation() {
        return this.opCodes[this.opCodesSize - 1];
    }

    private final <T> String toCollectionString(Iterable<? extends T> iterable, String str) {
        return x.u0(iterable, ", ", "[", "]", new AnonymousClass1(str), 24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: topIntIndexOf-w8GmfQM, reason: not valid java name */
    public final int m3071topIntIndexOfw8GmfQM(int parameter) {
        return (this.intArgsSize - peekOperation().getInts()) + parameter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: topObjectIndexOf-31yXWZQ, reason: not valid java name */
    public final int m3072topObjectIndexOf31yXWZQ(int parameter) {
        return (this.objectArgsSize - peekOperation().getObjects()) + parameter;
    }

    public final void clear() {
        this.opCodesSize = 0;
        this.intArgsSize = 0;
        Arrays.fill(this.objectArgs, 0, this.objectArgsSize, (Object) null);
        this.objectArgsSize = 0;
    }

    public final void drain(l<? super OpIterator, t0> sink) {
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            do {
                sink.invoke(opIterator);
            } while (opIterator.next());
        }
        clear();
    }

    public final void executeAndFlushAllPendingOperations(Applier<?> applier, SlotWriter slots, RememberManager rememberManager) {
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            do {
                opIterator.getOperation().execute(opIterator, applier, slots, rememberManager);
            } while (opIterator.next());
        }
        clear();
    }

    public final void forEach(l<? super OpIterator, t0> action) {
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            do {
                action.invoke(opIterator);
            } while (opIterator.next());
        }
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getOpCodesSize() {
        return this.opCodesSize;
    }

    public final boolean isEmpty() {
        return getOpCodesSize() == 0;
    }

    public final boolean isNotEmpty() {
        return getOpCodesSize() != 0;
    }

    public final void pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop(), because the stack is empty.");
        }
        Operation[] operationArr = this.opCodes;
        int i10 = this.opCodesSize - 1;
        this.opCodesSize = i10;
        Operation operation = operationArr[i10];
        operationArr[i10] = null;
        int objects = operation.getObjects();
        for (int i11 = 0; i11 < objects; i11++) {
            Object[] objArr = this.objectArgs;
            int i12 = this.objectArgsSize - 1;
            this.objectArgsSize = i12;
            objArr[i12] = null;
        }
        int ints = operation.getInts();
        for (int i13 = 0; i13 < ints; i13++) {
            int[] iArr = this.intArgs;
            int i14 = this.intArgsSize - 1;
            this.intArgsSize = i14;
            iArr[i14] = 0;
        }
    }

    public final void popInto(Operations other) {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop(), because the stack is empty.");
        }
        Operation[] operationArr = this.opCodes;
        int i10 = this.opCodesSize - 1;
        this.opCodesSize = i10;
        Operation operation = operationArr[i10];
        operationArr[i10] = null;
        other.pushOp(operation);
        int i11 = this.objectArgsSize;
        int i12 = other.objectArgsSize;
        int objects = operation.getObjects();
        for (int i13 = 0; i13 < objects; i13++) {
            i12--;
            i11--;
            Object[] objArr = other.objectArgs;
            Object[] objArr2 = this.objectArgs;
            objArr[i12] = objArr2[i11];
            objArr2[i11] = null;
        }
        int i14 = this.intArgsSize;
        int i15 = other.intArgsSize;
        int ints = operation.getInts();
        for (int i16 = 0; i16 < ints; i16++) {
            i15--;
            i14--;
            int[] iArr = other.intArgs;
            int[] iArr2 = this.intArgs;
            iArr[i15] = iArr2[i14];
            iArr2[i14] = 0;
        }
        this.objectArgsSize -= operation.getObjects();
        this.intArgsSize -= operation.getInts();
    }

    public final void push(Operation operation) {
        if (!(operation.getInts() == 0 && operation.getObjects() == 0)) {
            PreconditionsKt.throwIllegalArgumentException("Cannot push " + operation + " without arguments because it expects " + operation.getInts() + " ints and " + operation.getObjects() + " objects.");
        }
        pushOp(operation);
    }

    public final void pushOp(Operation operation) {
        this.pushedIntMask = 0;
        this.pushedObjectMask = 0;
        int i10 = this.opCodesSize;
        Operation[] operationArr = this.opCodes;
        if (i10 == operationArr.length) {
            this.opCodes = (Operation[]) Arrays.copyOf(operationArr, i10 + (i10 <= 1024 ? i10 : 1024));
        }
        ensureIntArgsSizeAtLeast(operation.getInts() + this.intArgsSize);
        ensureObjectArgsSizeAtLeast(operation.getObjects() + this.objectArgsSize);
        Operation[] operationArr2 = this.opCodes;
        int i11 = this.opCodesSize;
        this.opCodesSize = i11 + 1;
        operationArr2[i11] = operation;
        this.intArgsSize = operation.getInts() + this.intArgsSize;
        this.objectArgsSize = operation.getObjects() + this.objectArgsSize;
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder sb2 = new StringBuilder();
        if (isNotEmpty()) {
            OpIterator opIterator = new OpIterator();
            int i10 = 1;
            while (true) {
                sb2.append(linePrefix);
                int i11 = i10 + 1;
                sb2.append(i10);
                sb2.append(". ");
                sb2.append(currentOpToDebugString(opIterator, linePrefix));
                sb2.append('\n');
                if (!opIterator.next()) {
                    break;
                }
                i10 = i11;
            }
        }
        return sb2.toString();
    }

    @e
    public String toString() {
        return super.toString();
    }

    public final void push(Operation operation, l<? super WriteScope, t0> args) {
        pushOp(operation);
        args.invoke(WriteScope.m3073boximpl(WriteScope.m3074constructorimpl(this)));
        if (this.pushedIntMask == createExpectedArgMask(operation.getInts()) && this.pushedObjectMask == createExpectedArgMask(operation.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = operation.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & this.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(operation.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = operation.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & this.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(operation.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(operation);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }
}
