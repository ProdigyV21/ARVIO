package h2;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.tv.material3.l3;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    public static final Modifier a(Modifier modifier, ScrollableState scrollableState, Orientation orientation, b bVar, boolean z, boolean z5) {
        Modifier.Companion companion = Modifier.INSTANCE;
        return modifier.then(InspectableValueKt.inspectableWrapper(companion, InspectableValueKt.isDebugInspectorInfoEnabled() ? new l3(orientation, scrollableState, z, z5, bVar) : InspectableValueKt.getNoInspectorInfo(), ScrollableKt.scrollable$default(companion, scrollableState, orientation, null, z, z5, null, null, new d(bVar, z), 96, null)));
    }
}
