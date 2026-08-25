package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import s7.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0003\n\u0002\u0010+\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\u00060\u0004j\u0002`\u0005:\u0001cB\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u0018H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0096\u0002¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b#\u0010\u001dJ\u0015\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0016¢\u0006\u0004\b%\u0010&J\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000$2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b%\u0010'J%\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0016¢\u0006\u0004\b*\u0010+J\u000f\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b/\u0010\u0013J\u001f\u0010/\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b/\u00100J%\u00101\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00182\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016¢\u0006\u0004\b1\u00102J\u001d\u00101\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016¢\u0006\u0004\b1\u0010\u0017J\u000f\u00103\u001a\u00020\nH\u0016¢\u0006\u0004\b3\u0010\u0007J\u0017\u00104\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b4\u0010\u0013J\u001d\u00105\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016¢\u0006\u0004\b5\u0010\u0017J\u0017\u00106\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b6\u0010\u001bJ\u001d\u00107\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016¢\u0006\u0004\b7\u0010\u0017J \u00108\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b8\u00109J\u001d\u0010:\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018¢\u0006\u0004\b:\u0010;J-\u0010@\u001a\u00020\u00182\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010<\u001a\u00020\u00182\u0006\u0010=\u001a\u00020\u0018H\u0000¢\u0006\u0004\b>\u0010?J5\u0010F\u001a\u00028\u0001\"\u0004\b\u0001\u0010A2\u001d\u0010E\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000C\u0012\u0004\u0012\u00028\u00010B¢\u0006\u0002\bDH\u0082\b¢\u0006\u0004\bF\u0010GJ5\u0010H\u001a\u00028\u0001\"\u0004\b\u0001\u0010A2\u001d\u0010E\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000C\u0012\u0004\u0012\u00028\u00010B¢\u0006\u0002\bDH\u0082\b¢\u0006\u0004\bH\u0010GJ)\u0010I\u001a\u00020\u00112\u0018\u0010E\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0004\u0012\u00020\u00110BH\u0002¢\u0006\u0004\bI\u0010JJ0\u0010K\u001a\u00028\u0001\"\u0004\b\u0001\u0010A2\u0018\u0010E\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0004\u0012\u00028\u00010BH\u0082\b¢\u0006\u0004\bK\u0010GJ:\u0010N\u001a\u00020\n2\b\b\u0002\u0010L\u001a\u00020\u00112\u001e\u0010E\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000M\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000M0BH\u0082\b¢\u0006\u0004\bN\u0010OJ:\u0010P\u001a\u00020\u00112\b\b\u0002\u0010L\u001a\u00020\u00112\u001e\u0010E\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000M\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000M0BH\u0082\b¢\u0006\u0004\bP\u0010QR$\u0010S\u001a\u00020\b2\u0006\u0010R\u001a\u00020\b8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bU\u0010VR\u0014\u0010Y\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bW\u0010XR \u0010]\u001a\b\u0012\u0004\u0012\u00028\u00000C8@X\u0080\u0004¢\u0006\f\u0012\u0004\b\\\u0010\u0007\u001a\u0004\bZ\u0010[R\u0014\u0010_\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b^\u0010XR \u0010b\u001a\b\u0012\u0004\u0012\u00028\u00000\r8AX\u0080\u0004¢\u0006\f\u0012\u0004\ba\u0010\u0007\u001a\u0004\b`\u0010\u000f¨\u0006d"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateList;", "T", "Landroidx/compose/runtime/snapshots/StateObject;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "<init>", "()V", "Landroidx/compose/runtime/snapshots/StateRecord;", "value", "Lx6/t0;", "prependStateRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "", "toList", "()Ljava/util/List;", "element", "", "contains", "(Ljava/lang/Object;)Z", "", "elements", "containsAll", "(Ljava/util/Collection;)Z", "", "index", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "lastIndexOf", "", "listIterator", "()Ljava/util/ListIterator;", "(I)Ljava/util/ListIterator;", "fromIndex", "toIndex", "subList", "(II)Ljava/util/List;", "", "toString", "()Ljava/lang/String;", "add", "(ILjava/lang/Object;)V", "addAll", "(ILjava/util/Collection;)Z", "clear", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "removeRange", "(II)V", TtmlNode.START, TtmlNode.END, "retainAllInRange$runtime_release", "(Ljava/util/Collection;II)I", "retainAllInRange", "R", "Lkotlin/Function1;", "Landroidx/compose/runtime/snapshots/SnapshotStateList$StateListStateRecord;", "Lx6/n;", "block", "writable", "(Lr7/l;)Ljava/lang/Object;", "withCurrent", "mutateBoolean", "(Lr7/l;)Z", "mutate", "structural", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "update", "(ZLr7/l;)V", "conditionalUpdate", "(ZLr7/l;)Z", "<set-?>", "firstStateRecord", "Landroidx/compose/runtime/snapshots/StateRecord;", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "getStructure$runtime_release", "()I", "structure", "getReadable$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotStateList$StateListStateRecord;", "getReadable$runtime_release$annotations", "readable", "getSize", ContentDisposition.Parameters.Size, "getDebuggerDisplayValue", "getDebuggerDisplayValue$annotations", "debuggerDisplayValue", "StateListStateRecord", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotStateList<T> implements StateObject, List<T>, RandomAccess, e {
    public static final int $stable = 0;
    private StateRecord firstStateRecord;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\n\b\u0000\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0017\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fR(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0006R\"\u0010\u0012\u001a\u00020\u00118\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00118\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateList$StateListStateRecord;", "T", "Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "list", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;)V", "value", "Lx6/t0;", "assign", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "create", "()Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "getList$runtime_release", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "setList$runtime_release", "", "modification", "I", "getModification$runtime_release", "()I", "setModification$runtime_release", "(I)V", "structuralChange", "getStructuralChange$runtime_release", "setStructuralChange$runtime_release", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class StateListStateRecord<T> extends StateRecord {
        public static final int $stable = 8;
        private PersistentList<? extends T> list;
        private int modification;
        private int structuralChange;

        public StateListStateRecord(PersistentList<? extends T> persistentList) {
            this.list = persistentList;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            synchronized (SnapshotStateListKt.sync) {
                this.list = ((StateListStateRecord) value).list;
                this.modification = ((StateListStateRecord) value).modification;
                this.structuralChange = ((StateListStateRecord) value).structuralChange;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new StateListStateRecord(this.list);
        }

        public final PersistentList<T> getList$runtime_release() {
            return this.list;
        }

        /* JADX INFO: renamed from: getModification$runtime_release, reason: from getter */
        public final int getModification() {
            return this.modification;
        }

        /* JADX INFO: renamed from: getStructuralChange$runtime_release, reason: from getter */
        public final int getStructuralChange() {
            return this.structuralChange;
        }

        public final void setList$runtime_release(PersistentList<? extends T> persistentList) {
            this.list = persistentList;
        }

        public final void setModification$runtime_release(int i10) {
            this.modification = i10;
        }

        public final void setStructuralChange$runtime_release(int i10) {
            this.structuralChange = i10;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotStateList$addAll$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke", "(Ljava/util/List;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<List<T>, Boolean> {
        final /* synthetic */ Collection<T> $elements;
        final /* synthetic */ int $index;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(int i10, Collection<? extends T> collection) {
            super(1);
            this.$index = i10;
            this.$elements = collection;
        }

        @Override // r7.l
        public final Boolean invoke(List<T> list) {
            return Boolean.valueOf(list.addAll(this.$index, this.$elements));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke", "(Ljava/util/List;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06051 extends r implements l<List<T>, Boolean> {
        final /* synthetic */ Collection<T> $elements;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C06051(Collection<? extends T> collection) {
            super(1);
            this.$elements = collection;
        }

        @Override // r7.l
        public final Boolean invoke(List<T> list) {
            return Boolean.valueOf(list.retainAll(this.$elements));
        }
    }

    public SnapshotStateList() {
        PersistentList persistentListPersistentListOf = ExtensionsKt.persistentListOf();
        StateListStateRecord stateListStateRecord = new StateListStateRecord(persistentListPersistentListOf);
        if (Snapshot.INSTANCE.isInSnapshot()) {
            StateListStateRecord stateListStateRecord2 = new StateListStateRecord(persistentListPersistentListOf);
            stateListStateRecord2.setSnapshotId$runtime_release(1);
            stateListStateRecord.setNext$runtime_release(stateListStateRecord2);
        }
        this.firstStateRecord = stateListStateRecord;
    }

    private final boolean conditionalUpdate(boolean structural, l<? super PersistentList<? extends T>, ? extends PersistentList<? extends T>> block) {
        int modification;
        PersistentList<T> list$runtime_release;
        boolean z;
        Snapshot current;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<? extends T> persistentList = (PersistentList) block.invoke(list$runtime_release);
            z = false;
            if (p.a(persistentList, list$runtime_release)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    try {
                        if (stateListStateRecord3.getModification() == modification) {
                            stateListStateRecord3.setList$runtime_release(persistentList);
                            if (structural) {
                                stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                            }
                            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                            z = true;
                        }
                    } finally {
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return true;
    }

    public static /* synthetic */ boolean conditionalUpdate$default(SnapshotStateList snapshotStateList, boolean z, l lVar, int i10, Object obj) {
        int modification;
        PersistentList<T> list$runtime_release;
        boolean z5;
        Snapshot current;
        if ((i10 & 1) != 0) {
            z = true;
        }
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) snapshotStateList.getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<? extends T> persistentList = (PersistentList) lVar.invoke(list$runtime_release);
            z5 = false;
            if (p.a(persistentList, list$runtime_release)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) snapshotStateList.getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current);
                synchronized (SnapshotStateListKt.sync) {
                    try {
                        if (stateListStateRecord3.getModification() == modification) {
                            stateListStateRecord3.setList$runtime_release(persistentList);
                            if (z) {
                                stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                            }
                            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                            z5 = true;
                        }
                    } finally {
                    }
                }
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!z5);
        return true;
    }

    public static /* synthetic */ void getDebuggerDisplayValue$annotations() {
    }

    public static /* synthetic */ void getReadable$runtime_release$annotations() {
    }

    private final <R> R mutate(l<? super List<T>, ? extends R> block) {
        int modification;
        PersistentList<T> list$runtime_release;
        R r4;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList.Builder<T> builder = list$runtime_release.builder();
            r4 = (R) block.invoke(builder);
            PersistentList<T> persistentListBuild = builder.build();
            if (p.a(persistentListBuild, list$runtime_release)) {
                break;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListBuild);
                        z = true;
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return r4;
    }

    private final boolean mutateBoolean(l<? super List<T>, Boolean> block) {
        int modification;
        PersistentList<T> list$runtime_release;
        Object objInvoke;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList.Builder<T> builder = list$runtime_release.builder();
            objInvoke = block.invoke(builder);
            PersistentList<T> persistentListBuild = builder.build();
            if (p.a(persistentListBuild, list$runtime_release)) {
                break;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListBuild);
                        z = true;
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return ((Boolean) objInvoke).booleanValue();
    }

    private final void update(boolean structural, l<? super PersistentList<? extends T>, ? extends PersistentList<? extends T>> block) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<? extends T> persistentList = (PersistentList) block.invoke(list$runtime_release);
            if (p.a(persistentList, list$runtime_release)) {
                return;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    try {
                        if (stateListStateRecord3.getModification() == modification) {
                            stateListStateRecord3.setList$runtime_release(persistentList);
                            z = true;
                            if (structural) {
                                stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                            }
                            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        } else {
                            z = false;
                        }
                    } finally {
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
    }

    public static /* synthetic */ void update$default(SnapshotStateList snapshotStateList, boolean z, l lVar, int i10, Object obj) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z5;
        if ((i10 & 1) != 0) {
            z = true;
        }
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) snapshotStateList.getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<? extends T> persistentList = (PersistentList) lVar.invoke(list$runtime_release);
            if (p.a(persistentList, list$runtime_release)) {
                return;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) snapshotStateList.getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current);
                synchronized (SnapshotStateListKt.sync) {
                    try {
                        if (stateListStateRecord3.getModification() == modification) {
                            stateListStateRecord3.setList$runtime_release(persistentList);
                            if (z) {
                                stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                            }
                            stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                    } finally {
                    }
                }
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!z5);
    }

    private final <R> R withCurrent(l<? super StateListStateRecord<T>, ? extends R> block) {
        return (R) block.invoke(SnapshotKt.current((StateListStateRecord) getFirstStateRecord()));
    }

    private final <R> R writable(l<? super StateListStateRecord<T>, ? extends R> block) {
        Snapshot current;
        R r4;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            r4 = (R) block.invoke(SnapshotKt.writableRecord(stateListStateRecord, this, current));
        }
        SnapshotKt.notifyWrite(current, this);
        return r4;
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(T element) {
        int modification;
        PersistentList<T> list$runtime_release;
        boolean z;
        Snapshot current;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentListAdd = list$runtime_release.add(element);
            z = false;
            if (p.a(persistentListAdd, list$runtime_release)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListAdd);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends T> elements) {
        return mutateBoolean(new AnonymousClass1(index, elements));
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        Snapshot current;
        StateListStateRecord stateListStateRecord = (StateListStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord, this, current);
            synchronized (SnapshotStateListKt.sync) {
                stateListStateRecord2.setList$runtime_release(ExtensionsKt.persistentListOf());
                stateListStateRecord2.setModification$runtime_release(stateListStateRecord2.getModification() + 1);
                stateListStateRecord2.setStructuralChange$runtime_release(stateListStateRecord2.getStructuralChange() + 1);
            }
        }
        SnapshotKt.notifyWrite(current, this);
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        return getReadable$runtime_release().getList$runtime_release().contains(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        return getReadable$runtime_release().getList$runtime_release().containsAll(elements);
    }

    @Override // java.util.List
    public T get(int index) {
        return getReadable$runtime_release().getList$runtime_release().get(index);
    }

    public final List<T> getDebuggerDisplayValue() {
        return ((StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord())).getList$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public final StateListStateRecord<T> getReadable$runtime_release() {
        return (StateListStateRecord) SnapshotKt.readable((StateListStateRecord) getFirstStateRecord(), this);
    }

    public int getSize() {
        return getReadable$runtime_release().getList$runtime_release().size();
    }

    public final int getStructure$runtime_release() {
        return ((StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord())).getStructuralChange();
    }

    @Override // java.util.List
    public int indexOf(Object element) {
        return getReadable$runtime_release().getList$runtime_release().indexOf(element);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return getReadable$runtime_release().getList$runtime_release().isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object element) {
        return getReadable$runtime_release().getList$runtime_release().lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return new StateListIterator(this, 0);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final /* synthetic */ StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return b.a(this, stateRecord, stateRecord2, stateRecord3);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        value.setNext$runtime_release(getFirstStateRecord());
        this.firstStateRecord = (StateListStateRecord) value;
    }

    @Override // java.util.List
    public final /* bridge */ T remove(int i10) {
        return removeAt(i10);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        int modification;
        PersistentList<T> list$runtime_release;
        boolean z;
        Snapshot current;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentListRemoveAll = list$runtime_release.removeAll((Collection<? extends T>) elements);
            z = false;
            if (p.a(persistentListRemoveAll, list$runtime_release)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListRemoveAll);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return true;
    }

    public T removeAt(int index) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z;
        T t2 = get(index);
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentListRemoveAt = list$runtime_release.removeAt(index);
            if (p.a(persistentListRemoveAt, list$runtime_release)) {
                return t2;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListRemoveAt);
                        z = true;
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return t2;
    }

    public final void removeRange(int fromIndex, int toIndex) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList.Builder<T> builder = list$runtime_release.builder();
            builder.subList(fromIndex, toIndex).clear();
            PersistentList<T> persistentListBuild = builder.build();
            if (p.a(persistentListBuild, list$runtime_release)) {
                return;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListBuild);
                        z = true;
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        return mutateBoolean(new C06051(elements));
    }

    public final int retainAllInRange$runtime_release(Collection<? extends T> elements, int start, int end) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z;
        int size = size();
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList.Builder<T> builder = list$runtime_release.builder();
            builder.subList(start, end).retainAll(elements);
            PersistentList<T> persistentListBuild = builder.build();
            if (p.a(persistentListBuild, list$runtime_release)) {
                break;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListBuild);
                        z = true;
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return size - size();
    }

    @Override // java.util.List
    public T set(int index, T element) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z;
        T t2 = get(index);
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentList = list$runtime_release.set(index, element);
            if (p.a(persistentList, list$runtime_release)) {
                return t2;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentList);
                        z = true;
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return t2;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.List
    public List<T> subList(int fromIndex, int toIndex) {
        if (!(fromIndex >= 0 && fromIndex <= toIndex && toIndex <= size())) {
            PreconditionsKt.throwIllegalArgumentException("fromIndex or toIndex are out of bounds");
        }
        return new SubList(this, fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return o.a(this);
    }

    public final List<T> toList() {
        return getReadable$runtime_release().getList$runtime_release();
    }

    public String toString() {
        return "SnapshotStateList(value=" + ((StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord())).getList$runtime_release() + ")@" + hashCode();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> elements) {
        int modification;
        PersistentList<T> list$runtime_release;
        boolean z;
        Snapshot current;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentListAddAll = list$runtime_release.addAll(elements);
            z = false;
            if (p.a(persistentListAddAll, list$runtime_release)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListAddAll);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int index) {
        return new StateListIterator(this, index);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object element) {
        int modification;
        PersistentList<T> list$runtime_release;
        boolean z;
        Snapshot current;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentListRemove = list$runtime_release.remove(element);
            z = false;
            if (p.a(persistentListRemove, list$runtime_release)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListRemove);
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) o.b(this, tArr);
    }

    @Override // java.util.List
    public void add(int index, T element) {
        int modification;
        PersistentList<T> list$runtime_release;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateListKt.sync) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) getFirstStateRecord());
                modification = stateListStateRecord.getModification();
                list$runtime_release = stateListStateRecord.getList$runtime_release();
            }
            PersistentList<T> persistentListAdd = list$runtime_release.add(index, element);
            if (p.a(persistentListAdd, list$runtime_release)) {
                return;
            }
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, current);
                synchronized (SnapshotStateListKt.sync) {
                    if (stateListStateRecord3.getModification() == modification) {
                        stateListStateRecord3.setList$runtime_release(persistentListAdd);
                        z = true;
                        stateListStateRecord3.setStructuralChange$runtime_release(stateListStateRecord3.getStructuralChange() + 1);
                        stateListStateRecord3.setModification$runtime_release(stateListStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
    }
}
