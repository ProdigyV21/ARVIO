package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"aspectRatio", "Landroidx/compose/ui/Modifier;", "ratio", "", "matchHeightConstraintsFirst", "", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AspectRatioKt {
    public static final Modifier aspectRatio(Modifier modifier, float f10, boolean z) {
        return modifier.then(new AspectRatioElement(f10, z, InspectableValueKt.isDebugInspectorInfoEnabled() ? new AspectRatioKt$aspectRatio$$inlined$debugInspectorInfo$1(f10, z) : InspectableValueKt.getNoInspectorInfo()));
    }

    public static /* synthetic */ Modifier aspectRatio$default(Modifier modifier, float f10, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return aspectRatio(modifier, f10, z);
    }
}
