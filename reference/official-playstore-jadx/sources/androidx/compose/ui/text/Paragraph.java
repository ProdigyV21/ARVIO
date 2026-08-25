package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0012\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0013\u0010\u000fJ\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0014\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0019\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u0017H&¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b\u001d\u0010\u0016J\u001f\u0010\u001f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0017H&¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020!2\u0006\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020!2\u0006\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b$\u0010#J\u0017\u0010&\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\rH&¢\u0006\u0004\b&\u0010'J\u001a\u0010,\u001a\u00020\u00022\u0006\u0010)\u001a\u00020(H&ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b-\u0010\u000bJ,\u00106\u001a\u0002032\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u0002002\b\b\u0001\u00102\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u001d\u00109\u001a\u00020.2\u0006\u0010\b\u001a\u00020\u0002H&ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108J<\u0010D\u001a\u0002032\u0006\u0010;\u001a\u00020:2\b\b\u0002\u0010=\u001a\u00020<2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010@H&ø\u0001\u0000¢\u0006\u0004\bB\u0010CJR\u0010D\u001a\u0002032\u0006\u0010;\u001a\u00020:2\b\b\u0002\u0010=\u001a\u00020<2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010E2\b\b\u0002\u0010H\u001a\u00020GH&ø\u0001\u0000¢\u0006\u0004\bI\u0010JJZ\u0010D\u001a\u0002032\u0006\u0010;\u001a\u00020:2\u0006\u0010L\u001a\u00020K2\b\b\u0002\u0010M\u001a\u00020\r2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010E2\b\b\u0002\u0010H\u001a\u00020GH&ø\u0001\u0000¢\u0006\u0004\bN\u0010OR\u0014\u0010R\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0014\u0010T\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\bS\u0010QR\u0014\u0010V\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\bU\u0010QR\u0014\u0010X\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\bW\u0010QR\u0014\u0010Z\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\bY\u0010QR\u0014\u0010\\\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b[\u0010QR\u0014\u0010_\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b]\u0010^R\u0014\u0010b\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001c\u0010f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0c8&X¦\u0004¢\u0006\u0006\u001a\u0004\bd\u0010e\u0082\u0001\u0001gø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006hÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/text/Paragraph;", "", "", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/graphics/Path;", "getPathForRange", "(II)Landroidx/compose/ui/graphics/Path;", "offset", "Landroidx/compose/ui/geometry/Rect;", "getCursorRect", "(I)Landroidx/compose/ui/geometry/Rect;", "lineIndex", "", "getLineLeft", "(I)F", "getLineRight", "getLineTop", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "(I)I", "", "visibleEnd", "getLineEnd", "(IZ)I", "isLineEllipsized", "(I)Z", "getLineForOffset", "usePrimaryDirection", "getHorizontalPosition", "(IZ)F", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getParagraphDirection", "(I)Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "vertical", "getLineForVerticalPosition", "(F)I", "Landroidx/compose/ui/geometry/Offset;", "position", "getOffsetForPosition-k-4lQ0M", "(J)I", "getOffsetForPosition", "getBoundingBox", "Landroidx/compose/ui/text/TextRange;", "range", "", "array", "arrayStart", "Lx6/t0;", "fillBoundingBoxes-8ffj60Q", "(J[FI)V", "fillBoundingBoxes", "getWordBoundary--jx7JFs", "(I)J", "getWordBoundary", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Shadow;", "shadow", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "paint", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "Landroidx/compose/ui/graphics/Brush;", "brush", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "getWidth", "()F", "width", "getHeight", "height", "getMinIntrinsicWidth", "minIntrinsicWidth", "getMaxIntrinsicWidth", "maxIntrinsicWidth", "getFirstBaseline", "firstBaseline", "getLastBaseline", "lastBaseline", "getDidExceedMaxLines", "()Z", "didExceedMaxLines", "getLineCount", "()I", "lineCount", "", "getPlaceholderRects", "()Ljava/util/List;", "placeholderRects", "Landroidx/compose/ui/text/AndroidParagraph;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Paragraph {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q */
    void mo5041fillBoundingBoxes8ffj60Q(long range, float[] array, int arrayStart);

    ResolvedTextDirection getBidiRunDirection(int offset);

    Rect getBoundingBox(int offset);

    Rect getCursorRect(int offset);

    boolean getDidExceedMaxLines();

    float getFirstBaseline();

    float getHeight();

    float getHorizontalPosition(int offset, boolean usePrimaryDirection);

    float getLastBaseline();

    float getLineBottom(int lineIndex);

    int getLineCount();

    int getLineEnd(int lineIndex, boolean visibleEnd);

    int getLineForOffset(int offset);

    int getLineForVerticalPosition(float vertical);

    float getLineHeight(int lineIndex);

    float getLineLeft(int lineIndex);

    float getLineRight(int lineIndex);

    int getLineStart(int lineIndex);

    float getLineTop(int lineIndex);

    float getLineWidth(int lineIndex);

    float getMaxIntrinsicWidth();

    float getMinIntrinsicWidth();

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M */
    int mo5043getOffsetForPositionk4lQ0M(long position);

    ResolvedTextDirection getParagraphDirection(int offset);

    Path getPathForRange(int start, int end);

    List<Rect> getPlaceholderRects();

    float getWidth();

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs */
    long mo5044getWordBoundaryjx7JFs(int offset);

    boolean isLineEllipsized(int lineIndex);

    /* JADX INFO: renamed from: paint-LG529CI */
    void mo5045paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode);

    /* JADX INFO: renamed from: paint-RPmYEkk */
    void mo5046paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration);

    /* JADX INFO: renamed from: paint-hn5TExg */
    void mo5047painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode);
}
