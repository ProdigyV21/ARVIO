package androidx.compose.ui.layout;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0000\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u000b"}, d2 = {"FirstBaseline", "Landroidx/compose/ui/layout/HorizontalAlignmentLine;", "getFirstBaseline", "()Landroidx/compose/ui/layout/HorizontalAlignmentLine;", "LastBaseline", "getLastBaseline", "merge", "", "Landroidx/compose/ui/layout/AlignmentLine;", "position1", "position2", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlignmentLineKt {
    private static final HorizontalAlignmentLine FirstBaseline = new HorizontalAlignmentLine(AlignmentLineKt$FirstBaseline$1.INSTANCE);
    private static final HorizontalAlignmentLine LastBaseline = new HorizontalAlignmentLine(AlignmentLineKt$LastBaseline$1.INSTANCE);

    public static final HorizontalAlignmentLine getFirstBaseline() {
        return FirstBaseline;
    }

    public static final HorizontalAlignmentLine getLastBaseline() {
        return LastBaseline;
    }

    public static final int merge(AlignmentLine alignmentLine, int i10, int i11) {
        return ((Number) alignmentLine.getMerger$ui_release().invoke(Integer.valueOf(i10), Integer.valueOf(i11))).intValue();
    }
}
