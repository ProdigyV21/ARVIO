package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0004\b\b\u0010\tJ%\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0012\u001a\u0004\u0018\u00010\u0011H&¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H&¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H&¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0017H&¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0017H&¢\u0006\u0004\b\u001f\u0010\u001eJ\u0017\u0010 \u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0017H&¢\u0006\u0004\b \u0010\u001eJ\u001d\u0010$\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u0017H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020\u0017H&¢\u0006\u0004\b%\u0010&R\u0014\u0010*\u001a\u00020'8&X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006+À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/Selectable;", "", "Landroidx/compose/foundation/text/selection/SelectionLayoutBuilder;", "builder", "Lx6/t0;", "appendSelectableInfoToBuilder", "(Landroidx/compose/foundation/text/selection/SelectionLayoutBuilder;)V", "Landroidx/compose/foundation/text/selection/Selection;", "getSelectAllSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "selection", "", "isStartHandle", "Landroidx/compose/ui/geometry/Offset;", "getHandlePosition-dBAh8RU", "(Landroidx/compose/foundation/text/selection/Selection;Z)J", "getHandlePosition", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/text/AnnotatedString;", "getText", "()Landroidx/compose/ui/text/AnnotatedString;", "", "offset", "Landroidx/compose/ui/geometry/Rect;", "getBoundingBox", "(I)Landroidx/compose/ui/geometry/Rect;", "", "getLineLeft", "(I)F", "getLineRight", "getCenterYForOffset", "Landroidx/compose/ui/text/TextRange;", "getRangeOfLineContaining--jx7JFs", "(I)J", "getRangeOfLineContaining", "getLastVisibleOffset", "()I", "", "getSelectableId", "()J", "selectableId", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Selectable {
    void appendSelectableInfoToBuilder(SelectionLayoutBuilder builder);

    Rect getBoundingBox(int offset);

    float getCenterYForOffset(int offset);

    /* JADX INFO: renamed from: getHandlePosition-dBAh8RU */
    long mo960getHandlePositiondBAh8RU(Selection selection, boolean isStartHandle);

    int getLastVisibleOffset();

    LayoutCoordinates getLayoutCoordinates();

    float getLineLeft(int offset);

    float getLineRight(int offset);

    /* JADX INFO: renamed from: getRangeOfLineContaining--jx7JFs */
    long mo961getRangeOfLineContainingjx7JFs(int offset);

    Selection getSelectAllSelection();

    long getSelectableId();

    AnnotatedString getText();
}
