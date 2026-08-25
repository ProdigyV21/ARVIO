package androidx.compose.ui.modifier;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u000b\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000f\u001a\u00020\b\"\u0004\b\u0000\u0010\f2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0007\u001a\u00028\u0000H\u0090\u0002¢\u0006\u0004\b\r\u0010\u000eJ&\u0010\u0012\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\f2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0090\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001c\u0010\u0016\u001a\u00020\u00132\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0090\u0002¢\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R/\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\n¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/modifier/SingleLocalMap;", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "Landroidx/compose/ui/modifier/ModifierLocal;", "key", "<init>", "(Landroidx/compose/ui/modifier/ModifierLocal;)V", "", "value", "Lx6/t0;", "forceValue$ui_release", "(Ljava/lang/Object;)V", "forceValue", "T", "set$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "set", "get$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "get", "", "contains$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;)Z", "contains", "Landroidx/compose/ui/modifier/ModifierLocal;", "<set-?>", "value$delegate", "Landroidx/compose/runtime/MutableState;", "getValue", "()Ljava/lang/Object;", "setValue", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SingleLocalMap extends ModifierLocalMap {
    public static final int $stable = 0;
    private final ModifierLocal<?> key;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;

    public SingleLocalMap(ModifierLocal<?> modifierLocal) {
        super(null);
        this.key = modifierLocal;
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    private final Object getValue() {
        return this.value.getValue();
    }

    private final void setValue(Object obj) {
        this.value.setValue(obj);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public boolean contains$ui_release(ModifierLocal<?> key) {
        return key == this.key;
    }

    public final void forceValue$ui_release(Object value) {
        setValue(value);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public <T> T get$ui_release(ModifierLocal<T> key) {
        if (key != this.key) {
            throw new IllegalStateException("Check failed.");
        }
        T t2 = (T) getValue();
        if (t2 == null) {
            return null;
        }
        return t2;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    /* JADX INFO: renamed from: set$ui_release */
    public <T> void mo4706set$ui_release(ModifierLocal<T> key, T value) {
        if (key != this.key) {
            throw new IllegalStateException("Check failed.");
        }
        setValue(value);
    }
}
