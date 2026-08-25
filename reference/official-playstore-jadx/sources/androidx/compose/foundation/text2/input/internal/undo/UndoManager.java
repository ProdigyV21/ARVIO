package androidx.compose.foundation.text2.input.internal.undo;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u0000 !*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001!B1\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00028\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00028\u0000¢\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0013R\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010 \u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text2/input/internal/undo/UndoManager;", "T", "", "", "initialUndoStack", "initialRedoStack", "", "capacity", "<init>", "(Ljava/util/List;Ljava/util/List;I)V", "undoableAction", "Lx6/t0;", "record", "(Ljava/lang/Object;)V", "undo", "()Ljava/lang/Object;", "redo", "clearHistory", "()V", "I", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "undoStack", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "redoStack", "", "getCanUndo$foundation_release", "()Z", "canUndo", "getCanRedo$foundation_release", "canRedo", "getSize", "()I", ContentDisposition.Parameters.Size, "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UndoManager<T> {
    private final int capacity;
    private SnapshotStateList<T> redoStack;
    private SnapshotStateList<T> undoStack;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J7\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0005\u0012\u0004\u0012\u00020\u00010\u0004\"\u0006\b\u0001\u0010\u0006\u0018\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/undo/UndoManager$Companion;", "", "()V", "createSaver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text2/input/internal/undo/UndoManager;", "T", "itemSaver", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final <T> Saver<UndoManager<T>, Object> createSaver(Saver<T, Object> itemSaver) {
            p.h();
            throw null;
        }

        private Companion() {
        }
    }

    public UndoManager() {
        this(null, null, 0, 7, null);
    }

    public final void clearHistory() {
        this.undoStack.clear();
        this.redoStack.clear();
    }

    public final boolean getCanRedo$foundation_release() {
        return !this.redoStack.isEmpty();
    }

    public final boolean getCanUndo$foundation_release() {
        return !this.undoStack.isEmpty();
    }

    public final int getSize() {
        return this.redoStack.size() + this.undoStack.size();
    }

    public final void record(T undoableAction) {
        this.redoStack.clear();
        while (getSize() > this.capacity - 1) {
            x.N0(this.undoStack);
        }
        this.undoStack.add(undoableAction);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final T redo() {
        if (!getCanRedo$foundation_release()) {
            throw new IllegalStateException("It's an error to call redo while there is nothing to redo. Please first check `canRedo` value before calling the `redo` function.");
        }
        T t2 = (T) x.O0(this.redoStack);
        this.undoStack.add(t2);
        return t2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final T undo() {
        if (!getCanUndo$foundation_release()) {
            throw new IllegalStateException("It's an error to call undo while there is nothing to undo. Please first check `canUndo` value before calling the `undo` function.");
        }
        T t2 = (T) x.O0(this.undoStack);
        this.redoStack.add(t2);
        return t2;
    }

    public UndoManager(List<? extends T> list, List<? extends T> list2, int i10) {
        this.capacity = i10;
        SnapshotStateList<T> snapshotStateList = new SnapshotStateList<>();
        snapshotStateList.addAll(list);
        this.undoStack = snapshotStateList;
        SnapshotStateList<T> snapshotStateList2 = new SnapshotStateList<>();
        snapshotStateList2.addAll(list2);
        this.redoStack = snapshotStateList2;
        if (i10 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        if (getSize() <= i10) {
            return;
        }
        throw new IllegalArgumentException(("Initial list of undo and redo operations have a size=(" + getSize() + ") greater than the given capacity=(" + i10 + ").").toString());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ UndoManager(List list, List list2, int i10, int i11, h hVar) {
        int i12 = i11 & 1;
        z zVar = z.f19728i;
        this(i12 != 0 ? zVar : list, (i11 & 2) != 0 ? zVar : list2, (i11 & 4) != 0 ? 100 : i10);
    }
}
