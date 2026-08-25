package androidx.compose.ui.node;

import androidx.compose.ui.layout.LayoutCoordinates;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "Lx6/t0;", "onPlaced", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "onRemeasured-ozmzZPI", "(J)V", "onRemeasured", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LayoutAwareModifierNode extends DelegatableNode {
    void onPlaced(LayoutCoordinates coordinates);

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    void mo236onRemeasuredozmzZPI(long size);
}
