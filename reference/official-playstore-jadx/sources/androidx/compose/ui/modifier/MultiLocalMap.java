package androidx.compose.ui.modifier;

import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import kotlin.Metadata;
import kotlin.collections.h0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B?\u00126\u0010\u0006\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u0002\"\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0004\b\u0007\u0010\bJ,\u0010\u000f\u001a\u00020\f\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u000b\u001a\u00028\u0000H\u0090\u0002¢\u0006\u0004\b\r\u0010\u000eJ&\u0010\u0012\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0090\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001c\u0010\u0016\u001a\u00020\u00132\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0090\u0002¢\u0006\u0004\b\u0014\u0010\u0015R&\u0010\u0018\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/modifier/MultiLocalMap;", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "", "Lx6/x;", "Landroidx/compose/ui/modifier/ModifierLocal;", "", "entries", "<init>", "([Lx6/x;)V", "T", "key", "value", "Lx6/t0;", "set$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "set", "get$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "get", "", "contains$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;)Z", "contains", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "map", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MultiLocalMap extends ModifierLocalMap {
    public static final int $stable = 0;
    private final SnapshotStateMap<ModifierLocal<?>, Object> map;

    public MultiLocalMap(x... xVarArr) {
        super(null);
        SnapshotStateMap<ModifierLocal<?>, Object> snapshotStateMapMutableStateMapOf = SnapshotStateKt.mutableStateMapOf();
        this.map = snapshotStateMapMutableStateMapOf;
        snapshotStateMapMutableStateMapOf.putAll(h0.C0(xVarArr));
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public boolean contains$ui_release(ModifierLocal<?> key) {
        return this.map.containsKey(key);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public <T> T get$ui_release(ModifierLocal<T> key) {
        T t2 = (T) this.map.get(key);
        if (t2 == null) {
            return null;
        }
        return t2;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    /* JADX INFO: renamed from: set$ui_release */
    public <T> void mo4706set$ui_release(ModifierLocal<T> key, T value) {
        this.map.put(key, value);
    }
}
