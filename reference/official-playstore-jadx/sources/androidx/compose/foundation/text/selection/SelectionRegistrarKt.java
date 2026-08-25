package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0000\"\u001c\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {"LocalSelectionRegistrar", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "getLocalSelectionRegistrar", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "hasSelection", "", "selectableId", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionRegistrarKt {
    private static final ProvidableCompositionLocal<SelectionRegistrar> LocalSelectionRegistrar = CompositionLocalKt.compositionLocalOf$default(null, SelectionRegistrarKt$LocalSelectionRegistrar$1.INSTANCE, 1, null);

    public static final ProvidableCompositionLocal<SelectionRegistrar> getLocalSelectionRegistrar() {
        return LocalSelectionRegistrar;
    }

    public static final boolean hasSelection(SelectionRegistrar selectionRegistrar, long j10) {
        Map<Long, Selection> subselections;
        if (selectionRegistrar == null || (subselections = selectionRegistrar.getSubselections()) == null) {
            return false;
        }
        return subselections.containsKey(Long.valueOf(j10));
    }
}
