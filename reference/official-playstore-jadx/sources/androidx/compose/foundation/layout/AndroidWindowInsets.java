package androidx.compose.foundation.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.q3;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0010\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0011\u0010\u000fJ\u001f\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0096\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0004H\u0016¢\u0006\u0004\b \u0010!R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010$R+\u0010-\u001a\u00020%2\u0006\u0010&\u001a\u00020%8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R+\u0010/\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u001b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010(\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u00063"}, d2 = {"Landroidx/compose/foundation/layout/AndroidWindowInsets;", "Landroidx/compose/foundation/layout/WindowInsets;", "", LinkHeader.Parameters.Type, "", ContentDisposition.Parameters.Name, "<init>", "(ILjava/lang/String;)V", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getLeft", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;)I", "getTop", "(Landroidx/compose/ui/unit/Density;)I", "getRight", "getBottom", "Landroidx/core/view/q3;", "windowInsetsCompat", "typeMask", "Lx6/t0;", "update$foundation_layout_release", "(Landroidx/core/view/q3;I)V", "update", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "toString", "()Ljava/lang/String;", "I", "getType$foundation_layout_release", "Ljava/lang/String;", "Landroidx/core/graphics/c;", "<set-?>", "insets$delegate", "Landroidx/compose/runtime/MutableState;", "getInsets$foundation_layout_release", "()Landroidx/core/graphics/c;", "setInsets$foundation_layout_release", "(Landroidx/core/graphics/c;)V", "insets", "isVisible$delegate", "isVisible", "()Z", "setVisible", "(Z)V", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidWindowInsets implements WindowInsets {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: insets$delegate, reason: from kotlin metadata */
    private final MutableState insets = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(androidx.core.graphics.c.f2085e, null, 2, null);

    /* JADX INFO: renamed from: isVisible$delegate, reason: from kotlin metadata */
    private final MutableState isVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
    private final String name;
    private final int type;

    public AndroidWindowInsets(int i10, String str) {
        this.type = i10;
        this.name = str;
    }

    private final void setVisible(boolean z) {
        this.isVisible.setValue(Boolean.valueOf(z));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AndroidWindowInsets) && this.type == ((AndroidWindowInsets) other).type;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        return getInsets$foundation_layout_release().f2089d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final androidx.core.graphics.c getInsets$foundation_layout_release() {
        return (androidx.core.graphics.c) this.insets.getValue();
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        return getInsets$foundation_layout_release().f2086a;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        return getInsets$foundation_layout_release().f2088c;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        return getInsets$foundation_layout_release().f2087b;
    }

    /* JADX INFO: renamed from: getType$foundation_layout_release, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isVisible() {
        return ((Boolean) this.isVisible.getValue()).booleanValue();
    }

    public final void setInsets$foundation_layout_release(androidx.core.graphics.c cVar) {
        this.insets.setValue(cVar);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.name);
        sb2.append('(');
        sb2.append(getInsets$foundation_layout_release().f2086a);
        sb2.append(", ");
        sb2.append(getInsets$foundation_layout_release().f2087b);
        sb2.append(", ");
        sb2.append(getInsets$foundation_layout_release().f2088c);
        sb2.append(", ");
        return a0.c.o(sb2, getInsets$foundation_layout_release().f2089d, ')');
    }

    public final void update$foundation_layout_release(q3 windowInsetsCompat, int typeMask) {
        if (typeMask == 0 || (typeMask & this.type) != 0) {
            setInsets$foundation_layout_release(windowInsetsCompat.f2300a.f(this.type));
            setVisible(windowInsetsCompat.f2300a.p(this.type));
        }
    }
}
