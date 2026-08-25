package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import x6.o;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class SelectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0 implements OffsetProvider, j {
    private final /* synthetic */ r7.a function;

    public SelectionContainerKt$sam$androidx_compose_foundation_text_selection_OffsetProvider$0(r7.a aVar) {
        this.function = aVar;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof OffsetProvider) && (obj instanceof j)) {
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

    @Override // androidx.compose.foundation.text.selection.OffsetProvider
    /* JADX INFO: renamed from: provide-F1C5BW0 */
    public final /* synthetic */ long mo802provideF1C5BW0() {
        return ((Offset) this.function.invoke()).getPackedValue();
    }
}
