package androidx.compose.ui.focus;

import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import r7.l;
import x6.o;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class FocusPropertiesKt$sam$androidx_compose_ui_focus_FocusPropertiesScope$0 implements FocusPropertiesScope, j {
    private final /* synthetic */ l function;

    public FocusPropertiesKt$sam$androidx_compose_ui_focus_FocusPropertiesScope$0(l lVar) {
        this.function = lVar;
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesScope
    public final /* synthetic */ void apply(FocusProperties focusProperties) {
        this.function.invoke(focusProperties);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof FocusPropertiesScope) && (obj instanceof j)) {
            return p.a(getFunctionDelegate(), ((j) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.j
    public final o<?> getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }
}
