package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0007"}, d2 = {"height", "Landroidx/compose/ui/Modifier;", "intrinsicSize", "Landroidx/compose/foundation/layout/IntrinsicSize;", "requiredHeight", "requiredWidth", "width", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IntrinsicKt {
    public static final Modifier height(Modifier modifier, IntrinsicSize intrinsicSize) {
        return modifier.then(new IntrinsicHeightElement(intrinsicSize, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new IntrinsicKt$height$$inlined$debugInspectorInfo$1(intrinsicSize) : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier requiredHeight(Modifier modifier, IntrinsicSize intrinsicSize) {
        return modifier.then(new IntrinsicHeightElement(intrinsicSize, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new IntrinsicKt$requiredHeight$$inlined$debugInspectorInfo$1(intrinsicSize) : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier requiredWidth(Modifier modifier, IntrinsicSize intrinsicSize) {
        return modifier.then(new IntrinsicWidthElement(intrinsicSize, false, InspectableValueKt.isDebugInspectorInfoEnabled() ? new IntrinsicKt$requiredWidth$$inlined$debugInspectorInfo$1(intrinsicSize) : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier width(Modifier modifier, IntrinsicSize intrinsicSize) {
        return modifier.then(new IntrinsicWidthElement(intrinsicSize, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new IntrinsicKt$width$$inlined$debugInspectorInfo$1(intrinsicSize) : InspectableValueKt.getNoInspectorInfo()));
    }
}
