package androidx.compose.ui.text;

import a0.c;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0017\u0010\u0016J\u0015\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0019\u0010\u0016J\u0015\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n¢\u0006\u0004\b\u001b\u0010\rJ\u0015\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010 \u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u000e¢\u0006\u0004\b \u0010!J\u0015\u0010#\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\n¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\n¢\u0006\u0004\b%\u0010$J\u0018\u0010*\u001a\u00020\n2\u0006\u0010'\u001a\u00020&ø\u0001\u0000¢\u0006\u0004\b(\u0010)J\u0015\u0010,\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\n¢\u0006\u0004\b,\u0010-J\u001b\u00101\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u0015\u00102\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\n¢\u0006\u0004\b2\u0010-J\u001d\u00106\u001a\u0002052\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\n¢\u0006\u0004\b6\u00107J$\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001a\u0010<\u001a\u00020\u000e2\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\nH\u0016¢\u0006\u0004\b>\u0010?J\u000f\u0010A\u001a\u00020@H\u0016¢\u0006\u0004\bA\u0010BR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010C\u001a\u0004\bD\u0010ER\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010F\u001a\u0004\bG\u0010HR\u001d\u0010\u0007\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010I\u001a\u0004\bJ\u0010KR\u0017\u0010L\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0017\u0010P\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\bP\u0010M\u001a\u0004\bQ\u0010OR\u001f\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0R8\u0006¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bU\u0010VR\u0011\u0010Y\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bW\u0010XR\u0011\u0010[\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bZ\u0010XR\u0011\u0010]\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\\\u0010XR\u0011\u0010_\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b^\u0010?\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006`"}, d2 = {"Landroidx/compose/ui/text/TextLayoutResult;", "", "Landroidx/compose/ui/text/TextLayoutInput;", "layoutInput", "Landroidx/compose/ui/text/MultiParagraph;", "multiParagraph", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "<init>", "(Landroidx/compose/ui/text/TextLayoutInput;Landroidx/compose/ui/text/MultiParagraph;JLkotlin/jvm/internal/h;)V", "", "lineIndex", "getLineStart", "(I)I", "", "visibleEnd", "getLineEnd", "(IZ)I", "isLineEllipsized", "(I)Z", "", "getLineTop", "(I)F", "getLineBottom", "getLineLeft", "getLineRight", "offset", "getLineForOffset", "vertical", "getLineForVerticalPosition", "(F)I", "usePrimaryDirection", "getHorizontalPosition", "(IZ)F", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getParagraphDirection", "(I)Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "Landroidx/compose/ui/geometry/Offset;", "position", "getOffsetForPosition-k-4lQ0M", "(J)I", "getOffsetForPosition", "Landroidx/compose/ui/geometry/Rect;", "getBoundingBox", "(I)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/text/TextRange;", "getWordBoundary--jx7JFs", "(I)J", "getWordBoundary", "getCursorRect", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/graphics/Path;", "getPathForRange", "(II)Landroidx/compose/ui/graphics/Path;", "copy-O0kMr_c", "(Landroidx/compose/ui/text/TextLayoutInput;J)Landroidx/compose/ui/text/TextLayoutResult;", "copy", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/ui/text/TextLayoutInput;", "getLayoutInput", "()Landroidx/compose/ui/text/TextLayoutInput;", "Landroidx/compose/ui/text/MultiParagraph;", "getMultiParagraph", "()Landroidx/compose/ui/text/MultiParagraph;", "J", "getSize-YbymL2g", "()J", "firstBaseline", "F", "getFirstBaseline", "()F", "lastBaseline", "getLastBaseline", "", "placeholderRects", "Ljava/util/List;", "getPlaceholderRects", "()Ljava/util/List;", "getDidOverflowHeight", "()Z", "didOverflowHeight", "getDidOverflowWidth", "didOverflowWidth", "getHasVisualOverflow", "hasVisualOverflow", "getLineCount", "lineCount", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextLayoutResult {
    public static final int $stable = 8;
    private final float firstBaseline;
    private final float lastBaseline;
    private final TextLayoutInput layoutInput;
    private final MultiParagraph multiParagraph;
    private final List<Rect> placeholderRects;
    private final long size;

    public /* synthetic */ TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j10, h hVar) {
        this(textLayoutInput, multiParagraph, j10);
    }

    /* JADX INFO: renamed from: copy-O0kMr_c$default, reason: not valid java name */
    public static /* synthetic */ TextLayoutResult m5162copyO0kMr_c$default(TextLayoutResult textLayoutResult, TextLayoutInput textLayoutInput, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textLayoutInput = textLayoutResult.layoutInput;
        }
        if ((i10 & 2) != 0) {
            j10 = textLayoutResult.size;
        }
        return textLayoutResult.m5163copyO0kMr_c(textLayoutInput, j10);
    }

    public static /* synthetic */ int getLineEnd$default(TextLayoutResult textLayoutResult, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            z = false;
        }
        return textLayoutResult.getLineEnd(i10, z);
    }

    /* JADX INFO: renamed from: copy-O0kMr_c, reason: not valid java name */
    public final TextLayoutResult m5163copyO0kMr_c(TextLayoutInput layoutInput, long size) {
        return new TextLayoutResult(layoutInput, this.multiParagraph, size, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextLayoutResult)) {
            return false;
        }
        TextLayoutResult textLayoutResult = (TextLayoutResult) other;
        return p.a(this.layoutInput, textLayoutResult.layoutInput) && p.a(this.multiParagraph, textLayoutResult.multiParagraph) && IntSize.m5842equalsimpl0(this.size, textLayoutResult.size) && this.firstBaseline == textLayoutResult.firstBaseline && this.lastBaseline == textLayoutResult.lastBaseline && p.a(this.placeholderRects, textLayoutResult.placeholderRects);
    }

    public final ResolvedTextDirection getBidiRunDirection(int offset) {
        return this.multiParagraph.getBidiRunDirection(offset);
    }

    public final Rect getBoundingBox(int offset) {
        return this.multiParagraph.getBoundingBox(offset);
    }

    public final Rect getCursorRect(int offset) {
        return this.multiParagraph.getCursorRect(offset);
    }

    public final boolean getDidOverflowHeight() {
        return this.multiParagraph.getDidExceedMaxLines() || ((float) IntSize.m5843getHeightimpl(this.size)) < this.multiParagraph.getHeight();
    }

    public final boolean getDidOverflowWidth() {
        return ((float) IntSize.m5844getWidthimpl(this.size)) < this.multiParagraph.getWidth();
    }

    public final float getFirstBaseline() {
        return this.firstBaseline;
    }

    public final boolean getHasVisualOverflow() {
        return getDidOverflowWidth() || getDidOverflowHeight();
    }

    public final float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        return this.multiParagraph.getHorizontalPosition(offset, usePrimaryDirection);
    }

    public final float getLastBaseline() {
        return this.lastBaseline;
    }

    public final TextLayoutInput getLayoutInput() {
        return this.layoutInput;
    }

    public final float getLineBottom(int lineIndex) {
        return this.multiParagraph.getLineBottom(lineIndex);
    }

    public final int getLineCount() {
        return this.multiParagraph.getLineCount();
    }

    public final int getLineEnd(int lineIndex, boolean visibleEnd) {
        return this.multiParagraph.getLineEnd(lineIndex, visibleEnd);
    }

    public final int getLineForOffset(int offset) {
        return this.multiParagraph.getLineForOffset(offset);
    }

    public final int getLineForVerticalPosition(float vertical) {
        return this.multiParagraph.getLineForVerticalPosition(vertical);
    }

    public final float getLineLeft(int lineIndex) {
        return this.multiParagraph.getLineLeft(lineIndex);
    }

    public final float getLineRight(int lineIndex) {
        return this.multiParagraph.getLineRight(lineIndex);
    }

    public final int getLineStart(int lineIndex) {
        return this.multiParagraph.getLineStart(lineIndex);
    }

    public final float getLineTop(int lineIndex) {
        return this.multiParagraph.getLineTop(lineIndex);
    }

    public final MultiParagraph getMultiParagraph() {
        return this.multiParagraph;
    }

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public final int m5164getOffsetForPositionk4lQ0M(long position) {
        return this.multiParagraph.m5072getOffsetForPositionk4lQ0M(position);
    }

    public final ResolvedTextDirection getParagraphDirection(int offset) {
        return this.multiParagraph.getParagraphDirection(offset);
    }

    public final Path getPathForRange(int start, int end) {
        return this.multiParagraph.getPathForRange(start, end);
    }

    public final List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public final long m5166getWordBoundaryjx7JFs(int offset) {
        return this.multiParagraph.m5073getWordBoundaryjx7JFs(offset);
    }

    public int hashCode() {
        return this.placeholderRects.hashCode() + c.b(this.lastBaseline, c.b(this.firstBaseline, (IntSize.m5845hashCodeimpl(this.size) + ((this.multiParagraph.hashCode() + (this.layoutInput.hashCode() * 31)) * 31)) * 31, 31), 31);
    }

    public final boolean isLineEllipsized(int lineIndex) {
        return this.multiParagraph.isLineEllipsized(lineIndex);
    }

    public String toString() {
        return "TextLayoutResult(layoutInput=" + this.layoutInput + ", multiParagraph=" + this.multiParagraph + ", size=" + ((Object) IntSize.m5847toStringimpl(this.size)) + ", firstBaseline=" + this.firstBaseline + ", lastBaseline=" + this.lastBaseline + ", placeholderRects=" + this.placeholderRects + ')';
    }

    private TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j10) {
        this.layoutInput = textLayoutInput;
        this.multiParagraph = multiParagraph;
        this.size = j10;
        this.firstBaseline = multiParagraph.getFirstBaseline();
        this.lastBaseline = multiParagraph.getLastBaseline();
        this.placeholderRects = multiParagraph.getPlaceholderRects();
    }
}
