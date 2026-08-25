package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/foundation/layout/UnionInsetsConsumingModifier;", "Landroidx/compose/foundation/layout/InsetsConsumingModifier;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "(Landroidx/compose/foundation/layout/WindowInsets;)V", "calculateInsets", "modifierLocalInsets", "equals", "", "other", "", "hashCode", "", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class UnionInsetsConsumingModifier extends InsetsConsumingModifier {
    private final WindowInsets insets;

    public UnionInsetsConsumingModifier(WindowInsets windowInsets) {
        super(null);
        this.insets = windowInsets;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifier
    public WindowInsets calculateInsets(WindowInsets modifierLocalInsets) {
        return WindowInsetsKt.union(this.insets, modifierLocalInsets);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof UnionInsetsConsumingModifier) {
            return p.a(((UnionInsetsConsumingModifier) other).insets, this.insets);
        }
        return false;
    }

    public int hashCode() {
        return this.insets.hashCode();
    }
}
