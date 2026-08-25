package androidx.compose.runtime;

import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u00002\u00020\u0001B_\b\u0000\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u001a\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f0\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\"\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00018\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\n\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u001f\u001a\u0004\b \u0010!R6\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f0\u000b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\u0010\u001a\u00020\u000f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0010\u0010'\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Landroidx/compose/runtime/MovableContentStateReference;", "", "Landroidx/compose/runtime/MovableContent;", "content", "parameter", "Landroidx/compose/runtime/ControlledComposition;", "composition", "Landroidx/compose/runtime/SlotTable;", "slotTable", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "", "Lx6/x;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "invalidations", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "locals", "<init>", "(Landroidx/compose/runtime/MovableContent;Ljava/lang/Object;Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/SlotTable;Landroidx/compose/runtime/Anchor;Ljava/util/List;Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "Landroidx/compose/runtime/MovableContent;", "getContent$runtime_release", "()Landroidx/compose/runtime/MovableContent;", "Ljava/lang/Object;", "getParameter$runtime_release", "()Ljava/lang/Object;", "Landroidx/compose/runtime/ControlledComposition;", "getComposition$runtime_release", "()Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "Landroidx/compose/runtime/Anchor;", "getAnchor$runtime_release", "()Landroidx/compose/runtime/Anchor;", "Ljava/util/List;", "getInvalidations$runtime_release", "()Ljava/util/List;", "setInvalidations$runtime_release", "(Ljava/util/List;)V", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "getLocals$runtime_release", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MovableContentStateReference {
    public static final int $stable = 8;
    private final Anchor anchor;
    private final ControlledComposition composition;
    private final MovableContent<Object> content;
    private List<? extends x> invalidations;
    private final PersistentCompositionLocalMap locals;
    private final Object parameter;
    private final SlotTable slotTable;

    public MovableContentStateReference(MovableContent<Object> movableContent, Object obj, ControlledComposition controlledComposition, SlotTable slotTable, Anchor anchor, List<? extends x> list, PersistentCompositionLocalMap persistentCompositionLocalMap) {
        this.content = movableContent;
        this.parameter = obj;
        this.composition = controlledComposition;
        this.slotTable = slotTable;
        this.anchor = anchor;
        this.invalidations = list;
        this.locals = persistentCompositionLocalMap;
    }

    /* JADX INFO: renamed from: getAnchor$runtime_release, reason: from getter */
    public final Anchor getAnchor() {
        return this.anchor;
    }

    /* JADX INFO: renamed from: getComposition$runtime_release, reason: from getter */
    public final ControlledComposition getComposition() {
        return this.composition;
    }

    public final MovableContent<Object> getContent$runtime_release() {
        return this.content;
    }

    public final List<x> getInvalidations$runtime_release() {
        return this.invalidations;
    }

    /* JADX INFO: renamed from: getLocals$runtime_release, reason: from getter */
    public final PersistentCompositionLocalMap getLocals() {
        return this.locals;
    }

    /* JADX INFO: renamed from: getParameter$runtime_release, reason: from getter */
    public final Object getParameter() {
        return this.parameter;
    }

    /* JADX INFO: renamed from: getSlotTable$runtime_release, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    public final void setInvalidations$runtime_release(List<? extends x> list) {
        this.invalidations = list;
    }
}
