package androidx.compose.foundation.text.selection;

import a0.c;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0005J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0000J\b\u0010/\u001a\u00020\u0011H\u0016R\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0014\u0010\u001f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b$\u0010\u0019¨\u00060"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectableInfo;", "", "selectableId", "", "slot", "", "rawStartHandleOffset", "rawEndHandleOffset", "rawPreviousHandleOffset", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "(JIIIILandroidx/compose/ui/text/TextLayoutResult;)V", "endRunDirection", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getEndRunDirection", "()Landroidx/compose/ui/text/style/ResolvedTextDirection;", "inputText", "", "getInputText", "()Ljava/lang/String;", "rawCrossStatus", "Landroidx/compose/foundation/text/selection/CrossStatus;", "getRawCrossStatus", "()Landroidx/compose/foundation/text/selection/CrossStatus;", "getRawEndHandleOffset", "()I", "getRawPreviousHandleOffset", "getRawStartHandleOffset", "getSelectableId", "()J", "getSlot", "startRunDirection", "getStartRunDirection", "getTextLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "textLength", "getTextLength", "anchorForOffset", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "offset", "makeSingleLayoutSelection", "Landroidx/compose/foundation/text/selection/Selection;", TtmlNode.START, TtmlNode.END, "shouldRecomputeSelection", "", "other", "toString", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectableInfo {
    public static final int $stable = TextLayoutResult.$stable;
    private final int rawEndHandleOffset;
    private final int rawPreviousHandleOffset;
    private final int rawStartHandleOffset;
    private final long selectableId;
    private final int slot;
    private final TextLayoutResult textLayoutResult;

    public SelectableInfo(long j10, int i10, int i11, int i12, int i13, TextLayoutResult textLayoutResult) {
        this.selectableId = j10;
        this.slot = i10;
        this.rawStartHandleOffset = i11;
        this.rawEndHandleOffset = i12;
        this.rawPreviousHandleOffset = i13;
        this.textLayoutResult = textLayoutResult;
    }

    private final ResolvedTextDirection getEndRunDirection() {
        return SelectionLayoutKt.getTextDirectionForOffset(this.textLayoutResult, this.rawEndHandleOffset);
    }

    private final ResolvedTextDirection getStartRunDirection() {
        return SelectionLayoutKt.getTextDirectionForOffset(this.textLayoutResult, this.rawStartHandleOffset);
    }

    public final Selection.AnchorInfo anchorForOffset(int offset) {
        return new Selection.AnchorInfo(SelectionLayoutKt.getTextDirectionForOffset(this.textLayoutResult, offset), offset, this.selectableId);
    }

    public final String getInputText() {
        return this.textLayoutResult.getLayoutInput().getText().getText();
    }

    public final CrossStatus getRawCrossStatus() {
        int i10 = this.rawStartHandleOffset;
        int i11 = this.rawEndHandleOffset;
        return i10 < i11 ? CrossStatus.NOT_CROSSED : i10 > i11 ? CrossStatus.CROSSED : CrossStatus.COLLAPSED;
    }

    public final int getRawEndHandleOffset() {
        return this.rawEndHandleOffset;
    }

    public final int getRawPreviousHandleOffset() {
        return this.rawPreviousHandleOffset;
    }

    public final int getRawStartHandleOffset() {
        return this.rawStartHandleOffset;
    }

    public final long getSelectableId() {
        return this.selectableId;
    }

    public final int getSlot() {
        return this.slot;
    }

    public final TextLayoutResult getTextLayoutResult() {
        return this.textLayoutResult;
    }

    public final int getTextLength() {
        return getInputText().length();
    }

    public final Selection makeSingleLayoutSelection(int start, int end) {
        return new Selection(anchorForOffset(start), anchorForOffset(end), start > end);
    }

    public final boolean shouldRecomputeSelection(SelectableInfo other) {
        return (this.selectableId == other.selectableId && this.rawStartHandleOffset == other.rawStartHandleOffset && this.rawEndHandleOffset == other.rawEndHandleOffset) ? false : true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SelectionInfo(id=");
        sb2.append(this.selectableId);
        sb2.append(", range=(");
        sb2.append(this.rawStartHandleOffset);
        sb2.append('-');
        sb2.append(getStartRunDirection());
        sb2.append(',');
        sb2.append(this.rawEndHandleOffset);
        sb2.append('-');
        sb2.append(getEndRunDirection());
        sb2.append("), prevOffset=");
        return c.o(sb2, this.rawPreviousHandleOffset, ')');
    }
}
