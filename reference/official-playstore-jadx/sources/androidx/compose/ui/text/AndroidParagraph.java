package androidx.compose.ui.text;

import a0.c;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.selection.WordBoundary;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.media3.extractor.text.ttml.TtmlNode;
import f4.f;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import x6.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bBi\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010\u0012\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00110\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\n\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010#\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001fH\u0016ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0017\u0010&\u001a\u00020%2\u0006\u0010$\u001a\u00020\u0004H\u0016¢\u0006\u0004\b&\u0010'J,\u00100\u001a\u00020-2\u0006\u0010)\u001a\u00020(2\u0006\u0010+\u001a\u00020*2\b\b\u0001\u0010,\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u001f\u00104\u001a\u0002032\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004H\u0016¢\u0006\u0004\b4\u00105J\u0017\u00106\u001a\u00020%2\u0006\u0010$\u001a\u00020\u0004H\u0016¢\u0006\u0004\b6\u0010'J\u001d\u00109\u001a\u00020(2\u0006\u0010$\u001a\u00020\u0004H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108J\u0017\u0010;\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\b;\u0010<J\u0017\u0010=\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\b=\u0010<J\u0017\u0010>\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\b>\u0010<J\u0017\u0010@\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0000¢\u0006\u0004\b?\u0010<J\u0017\u0010B\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0000¢\u0006\u0004\bA\u0010<J\u0017\u0010D\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0000¢\u0006\u0004\bC\u0010<J\u0017\u0010E\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\bE\u0010<J\u0017\u0010F\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\bF\u0010<J\u0017\u0010G\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\bG\u0010<J\u0017\u0010H\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\bH\u0010IJ\u001f\u0010K\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u0006H\u0016¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u0004H\u0016¢\u0006\u0004\bM\u0010NJ\u0017\u0010O\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0016¢\u0006\u0004\bO\u0010IJ\u001f\u0010Q\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u0006H\u0016¢\u0006\u0004\bQ\u0010RJ\u0017\u0010T\u001a\u00020S2\u0006\u0010$\u001a\u00020\u0004H\u0016¢\u0006\u0004\bT\u0010UJ\u0017\u0010V\u001a\u00020S2\u0006\u0010$\u001a\u00020\u0004H\u0016¢\u0006\u0004\bV\u0010UJ6\u0010a\u001a\u00020-2\u0006\u0010X\u001a\u00020W2\u0006\u0010Z\u001a\u00020Y2\b\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010^\u001a\u0004\u0018\u00010]H\u0016ø\u0001\u0000¢\u0006\u0004\b_\u0010`JH\u0010a\u001a\u00020-2\u0006\u0010X\u001a\u00020W2\u0006\u0010Z\u001a\u00020Y2\b\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010^\u001a\u0004\u0018\u00010]2\b\u0010c\u001a\u0004\u0018\u00010b2\u0006\u0010e\u001a\u00020dH\u0016ø\u0001\u0000¢\u0006\u0004\bf\u0010gJP\u0010a\u001a\u00020-2\u0006\u0010X\u001a\u00020W2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020\u001b2\b\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010^\u001a\u0004\u0018\u00010]2\b\u0010c\u001a\u0004\u0018\u00010b2\u0006\u0010e\u001a\u00020dH\u0016ø\u0001\u0000¢\u0006\u0004\bk\u0010lJ\u0019\u0010p\u001a\b\u0012\u0004\u0012\u00020o0n*\u00020mH\u0002¢\u0006\u0004\bp\u0010qJ\u0017\u0010a\u001a\u00020-2\u0006\u0010X\u001a\u00020WH\u0002¢\u0006\u0004\ba\u0010rJQ\u0010{\u001a\u00020m2\u0006\u0010s\u001a\u00020\u00042\u0006\u0010t\u001a\u00020\u00042\b\u0010v\u001a\u0004\u0018\u00010u2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u00042\u0006\u0010x\u001a\u00020\u00042\u0006\u0010y\u001a\u00020\u00042\u0006\u0010z\u001a\u00020\u0004H\u0002¢\u0006\u0004\b{\u0010|R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010}\u001a\u0004\b~\u0010\u007fR\u001a\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\u000f\n\u0005\b\u0005\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001a\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\u000f\n\u0005\b\u0007\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R \u0010\t\u001a\u00020\b8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u000f\n\u0005\b\t\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0017\u0010\u0089\u0001\u001a\u00020m8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R(\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0000X\u0081\u0004¢\u0006\u0018\n\u0006\b\u008c\u0001\u0010\u008d\u0001\u0012\u0006\b\u0090\u0001\u0010\u0091\u0001\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R'\u0010\u0092\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0\u00108\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R \u0010\u009a\u0001\u001a\u00030\u0096\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0006\b\u0097\u0001\u0010\u0098\u0001\u001a\u0005\b9\u0010\u0099\u0001R\u0017\u0010\u009d\u0001\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0017\u0010\u009f\u0001\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009c\u0001R\u0017\u0010¡\u0001\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b \u0001\u0010\u009c\u0001R\u0017\u0010£\u0001\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¢\u0001\u0010\u009c\u0001R\u0017\u0010¥\u0001\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¤\u0001\u0010\u009c\u0001R\u0017\u0010§\u0001\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¦\u0001\u0010\u009c\u0001R\u0017\u0010©\u0001\u001a\u00020\u00068VX\u0096\u0004¢\u0006\b\u001a\u0006\b¨\u0001\u0010\u0085\u0001R \u0010®\u0001\u001a\u00030ª\u00018@X\u0081\u0004¢\u0006\u0010\u0012\u0006\b\u00ad\u0001\u0010\u0091\u0001\u001a\u0006\b«\u0001\u0010¬\u0001R\u0017\u0010°\u0001\u001a\u00020\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\b¯\u0001\u0010\u0082\u0001R \u0010µ\u0001\u001a\u00030±\u00018@X\u0081\u0004¢\u0006\u0010\u0012\u0006\b´\u0001\u0010\u0091\u0001\u001a\u0006\b²\u0001\u0010³\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006¶\u0001"}, d2 = {"Landroidx/compose/ui/text/AndroidParagraph;", "Landroidx/compose/ui/text/Paragraph;", "Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "paragraphIntrinsics", "", "maxLines", "", "ellipsis", "Landroidx/compose/ui/unit/Constraints;", "constraints", "<init>", "(Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;IZJLkotlin/jvm/internal/h;)V", "", "text", "Landroidx/compose/ui/text/TextStyle;", "style", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/SpanStyle;", "spanStyles", "Landroidx/compose/ui/text/Placeholder;", "placeholders", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "Landroidx/compose/ui/unit/Density;", "density", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Ljava/util/List;IZJLandroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/internal/h;)V", "", "vertical", "getLineForVerticalPosition", "(F)I", "Landroidx/compose/ui/geometry/Offset;", "position", "getOffsetForPosition-k-4lQ0M", "(J)I", "getOffsetForPosition", "offset", "Landroidx/compose/ui/geometry/Rect;", "getBoundingBox", "(I)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/text/TextRange;", "range", "", "array", "arrayStart", "Lx6/t0;", "fillBoundingBoxes-8ffj60Q", "(J[FI)V", "fillBoundingBoxes", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/graphics/Path;", "getPathForRange", "(II)Landroidx/compose/ui/graphics/Path;", "getCursorRect", "getWordBoundary--jx7JFs", "(I)J", "getWordBoundary", "lineIndex", "getLineLeft", "(I)F", "getLineRight", "getLineTop", "getLineAscent$ui_text_release", "getLineAscent", "getLineBaseline$ui_text_release", "getLineBaseline", "getLineDescent$ui_text_release", "getLineDescent", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "(I)I", "visibleEnd", "getLineEnd", "(IZ)I", "isLineEllipsized", "(I)Z", "getLineForOffset", "usePrimaryDirection", "getHorizontalPosition", "(IZ)F", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getParagraphDirection", "(I)Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Shadow;", "shadow", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "paint", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "Landroidx/compose/ui/graphics/Brush;", "brush", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "Landroidx/compose/ui/text/android/TextLayout;", "", "Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "getShaderBrushSpans", "(Landroidx/compose/ui/text/android/TextLayout;)[Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "(Landroidx/compose/ui/graphics/Canvas;)V", "alignment", "justificationMode", "Landroid/text/TextUtils$TruncateAt;", "ellipsize", "hyphens", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "constructTextLayout", "(IILandroid/text/TextUtils$TruncateAt;IIIII)Landroidx/compose/ui/text/android/TextLayout;", "Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "getParagraphIntrinsics", "()Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "I", "getMaxLines", "()I", "Z", "getEllipsis", "()Z", "J", "getConstraints-msEJaDk", "()J", TtmlNode.TAG_LAYOUT, "Landroidx/compose/ui/text/android/TextLayout;", "", "charSequence", "Ljava/lang/CharSequence;", "getCharSequence$ui_text_release", "()Ljava/lang/CharSequence;", "getCharSequence$ui_text_release$annotations", "()V", "placeholderRects", "Ljava/util/List;", "getPlaceholderRects", "()Ljava/util/List;", "Landroidx/compose/ui/text/android/selection/WordBoundary;", "wordBoundary$delegate", "Lx6/s;", "()Landroidx/compose/ui/text/android/selection/WordBoundary;", "wordBoundary", "getWidth", "()F", "width", "getHeight", "height", "getMaxIntrinsicWidth", "maxIntrinsicWidth", "getMinIntrinsicWidth", "minIntrinsicWidth", "getFirstBaseline", "firstBaseline", "getLastBaseline", "lastBaseline", "getDidExceedMaxLines", "didExceedMaxLines", "Ljava/util/Locale;", "getTextLocale$ui_text_release", "()Ljava/util/Locale;", "getTextLocale$ui_text_release$annotations", "textLocale", "getLineCount", "lineCount", "Landroidx/compose/ui/text/platform/AndroidTextPaint;", "getTextPaint$ui_text_release", "()Landroidx/compose/ui/text/platform/AndroidTextPaint;", "getTextPaint$ui_text_release$annotations", "textPaint", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidParagraph implements Paragraph {
    public static final int $stable = 8;
    private final CharSequence charSequence;
    private final long constraints;
    private final boolean ellipsis;
    private final TextLayout layout;
    private final int maxLines;
    private final AndroidParagraphIntrinsics paragraphIntrinsics;
    private final List<Rect> placeholderRects;

    /* JADX INFO: renamed from: wordBoundary$delegate, reason: from kotlin metadata */
    private final s wordBoundary;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ResolvedTextDirection.values().length];
            try {
                iArr[ResolvedTextDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ResolvedTextDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i10, boolean z, long j10, h hVar) {
        this(androidParagraphIntrinsics, i10, z, j10);
    }

    private final TextLayout constructTextLayout(int alignment, int justificationMode, TextUtils.TruncateAt ellipsize, int maxLines, int hyphens, int breakStrategy, int lineBreakStyle, int lineBreakWordStyle) {
        return new TextLayout(this.charSequence, getWidth(), getTextPaint$ui_text_release(), alignment, ellipsize, this.paragraphIntrinsics.getTextDirectionHeuristic(), 1.0f, 0.0f, AndroidParagraphHelper_androidKt.isIncludeFontPaddingEnabled(this.paragraphIntrinsics.getStyle()), true, maxLines, breakStrategy, lineBreakStyle, lineBreakWordStyle, hyphens, justificationMode, null, null, this.paragraphIntrinsics.getLayoutIntrinsics(), 196736, null);
    }

    public static /* synthetic */ void getCharSequence$ui_text_release$annotations() {
    }

    private final ShaderBrushSpan[] getShaderBrushSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return new ShaderBrushSpan[0];
        }
        ShaderBrushSpan[] shaderBrushSpanArr = (ShaderBrushSpan[]) ((Spanned) textLayout.getText()).getSpans(0, textLayout.getText().length(), ShaderBrushSpan.class);
        return shaderBrushSpanArr.length == 0 ? new ShaderBrushSpan[0] : shaderBrushSpanArr;
    }

    public static /* synthetic */ void getTextLocale$ui_text_release$annotations() {
    }

    public static /* synthetic */ void getTextPaint$ui_text_release$annotations() {
    }

    private final WordBoundary getWordBoundary() {
        return (WordBoundary) this.wordBoundary.getValue();
    }

    private final void paint(Canvas canvas) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.save();
            nativeCanvas.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        this.layout.paint(nativeCanvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.restore();
        }
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public void mo5041fillBoundingBoxes8ffj60Q(long range, float[] array, int arrayStart) {
        this.layout.fillBoundingBoxes(TextRange.m5190getMinimpl(range), TextRange.m5189getMaximpl(range), array, arrayStart);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getBidiRunDirection(int offset) {
        return this.layout.isRtlCharAt(offset) ? ResolvedTextDirection.Rtl : ResolvedTextDirection.Ltr;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getBoundingBox(int offset) {
        if (offset >= 0 && offset < this.charSequence.length()) {
            RectF boundingBox = this.layout.getBoundingBox(offset);
            return new Rect(boundingBox.left, boundingBox.top, boundingBox.right, boundingBox.bottom);
        }
        StringBuilder sbS = c.s(offset, "offset(", ") is out of bounds [0,");
        sbS.append(this.charSequence.length());
        sbS.append(')');
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    /* JADX INFO: renamed from: getCharSequence$ui_text_release, reason: from getter */
    public final CharSequence getCharSequence() {
        return this.charSequence;
    }

    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getConstraints() {
        return this.constraints;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getCursorRect(int offset) {
        if (offset >= 0 && offset <= this.charSequence.length()) {
            float primaryHorizontal$default = TextLayout.getPrimaryHorizontal$default(this.layout, offset, false, 2, null);
            int lineForOffset = this.layout.getLineForOffset(offset);
            return new Rect(primaryHorizontal$default, this.layout.getLineTop(lineForOffset), primaryHorizontal$default, this.layout.getLineBottom(lineForOffset));
        }
        StringBuilder sbS = c.s(offset, "offset(", ") is out of bounds [0,");
        sbS.append(this.charSequence.length());
        sbS.append(']');
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean getDidExceedMaxLines() {
        return this.layout.getDidExceedMaxLines();
    }

    public final boolean getEllipsis() {
        return this.ellipsis;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getFirstBaseline() {
        return getLineBaseline$ui_text_release(0);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHeight() {
        return this.layout.getHeight();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        return usePrimaryDirection ? TextLayout.getPrimaryHorizontal$default(this.layout, offset, false, 2, null) : TextLayout.getSecondaryHorizontal$default(this.layout, offset, false, 2, null);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLastBaseline() {
        return getLineBaseline$ui_text_release(getLineCount() - 1);
    }

    public final float getLineAscent$ui_text_release(int lineIndex) {
        return this.layout.getLineAscent(lineIndex);
    }

    public final float getLineBaseline$ui_text_release(int lineIndex) {
        return this.layout.getLineBaseline(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineBottom(int lineIndex) {
        return this.layout.getLineBottom(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineCount() {
        return this.layout.getLineCount();
    }

    public final float getLineDescent$ui_text_release(int lineIndex) {
        return this.layout.getLineDescent(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineEnd(int lineIndex, boolean visibleEnd) {
        return visibleEnd ? this.layout.getLineVisibleEnd(lineIndex) : this.layout.getLineEnd(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForVerticalPosition(float vertical) {
        return this.layout.getLineForVertical((int) vertical);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineHeight(int lineIndex) {
        return this.layout.getLineHeight(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineTop(int lineIndex) {
        return this.layout.getLineTop(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMaxIntrinsicWidth() {
        return this.paragraphIntrinsics.getMaxIntrinsicWidth();
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMinIntrinsicWidth() {
        return this.paragraphIntrinsics.getMinIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public int mo5043getOffsetForPositionk4lQ0M(long position) {
        return this.layout.getOffsetForHorizontal(this.layout.getLineForVertical((int) Offset.m3237getYimpl(position)), Offset.m3236getXimpl(position));
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getParagraphDirection(int offset) {
        return this.layout.getParagraphDirection(this.layout.getLineForOffset(offset)) == 1 ? ResolvedTextDirection.Ltr : ResolvedTextDirection.Rtl;
    }

    public final AndroidParagraphIntrinsics getParagraphIntrinsics() {
        return this.paragraphIntrinsics;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Path getPathForRange(int start, int end) {
        if (start >= 0 && start <= end && end <= this.charSequence.length()) {
            android.graphics.Path path = new android.graphics.Path();
            this.layout.getSelectionPath(start, end, path);
            return AndroidPath_androidKt.asComposePath(path);
        }
        StringBuilder sbV = androidx.compose.foundation.c.v("start(", start, ") or end(", end, ") is out of range [0..");
        sbV.append(this.charSequence.length());
        sbV.append("], or start > end!");
        throw new IllegalArgumentException(sbV.toString().toString());
    }

    @Override // androidx.compose.ui.text.Paragraph
    public List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final Locale getTextLocale$ui_text_release() {
        return this.paragraphIntrinsics.getTextPaint().getTextLocale();
    }

    public final AndroidTextPaint getTextPaint$ui_text_release() {
        return this.paragraphIntrinsics.getTextPaint();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getWidth() {
        return Constraints.m5646getMaxWidthimpl(this.constraints);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public long mo5044getWordBoundaryjx7JFs(int offset) {
        return TextRangeKt.TextRange(getWordBoundary().getWordStart(offset), getWordBoundary().getWordEnd(offset));
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean isLineEllipsized(int lineIndex) {
        return this.layout.isLineEllipsized(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-LG529CI, reason: not valid java name */
    public void mo5045paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) {
        int iM5444getBlendMode0nO6VwU = getTextPaint$ui_text_release().m5444getBlendMode0nO6VwU();
        AndroidTextPaint textPaint$ui_text_release = getTextPaint$ui_text_release();
        textPaint$ui_text_release.m5447setColor8_81llA(color);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        textPaint$ui_text_release.setDrawStyle(drawStyle);
        textPaint$ui_text_release.m5445setBlendModes9anfk8(blendMode);
        paint(canvas);
        getTextPaint$ui_text_release().m5445setBlendModes9anfk8(iM5444getBlendMode0nO6VwU);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-RPmYEkk, reason: not valid java name */
    public void mo5046paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration) {
        AndroidTextPaint textPaint$ui_text_release = getTextPaint$ui_text_release();
        textPaint$ui_text_release.m5447setColor8_81llA(color);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        paint(canvas);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-hn5TExg, reason: not valid java name */
    public void mo5047painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) {
        int iM5444getBlendMode0nO6VwU = getTextPaint$ui_text_release().m5444getBlendMode0nO6VwU();
        AndroidTextPaint textPaint$ui_text_release = getTextPaint$ui_text_release();
        textPaint$ui_text_release.m5446setBrush12SF9DM(brush, SizeKt.Size(getWidth(), getHeight()), alpha);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        textPaint$ui_text_release.setDrawStyle(drawStyle);
        textPaint$ui_text_release.m5445setBlendModes9anfk8(blendMode);
        paint(canvas);
        getTextPaint$ui_text_release().m5445setBlendModes9anfk8(iM5444getBlendMode0nO6VwU);
    }

    public /* synthetic */ AndroidParagraph(String str, TextStyle textStyle, List list, List list2, int i10, boolean z, long j10, FontFamily.Resolver resolver, Density density, h hVar) {
        this(str, textStyle, list, list2, i10, z, j10, resolver, density);
    }

    private AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i10, boolean z, long j10) {
        CharSequence charSequence;
        AndroidParagraph androidParagraph;
        List<Rect> list;
        Rect rect;
        float horizontalPosition;
        float lineBaseline;
        int heightPx;
        float lineTop;
        float heightPx2;
        float lineBaseline2;
        this.paragraphIntrinsics = androidParagraphIntrinsics;
        this.maxLines = i10;
        this.ellipsis = z;
        this.constraints = j10;
        if (Constraints.m5647getMinHeightimpl(j10) != 0 || Constraints.m5648getMinWidthimpl(j10) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        if (i10 >= 1) {
            TextStyle style = androidParagraphIntrinsics.getStyle();
            if (AndroidParagraph_androidKt.shouldAttachIndentationFixSpan(style, z)) {
                charSequence = AndroidParagraph_androidKt.attachIndentationFixSpan(androidParagraphIntrinsics.getCharSequence());
            } else {
                charSequence = androidParagraphIntrinsics.getCharSequence();
            }
            this.charSequence = charSequence;
            int iM5053toLayoutAlignaXe7zB0 = AndroidParagraph_androidKt.m5053toLayoutAlignaXe7zB0(style.m5233getTextAligne0LSkKk());
            boolean zM5578equalsimpl0 = TextAlign.m5578equalsimpl0(style.m5233getTextAligne0LSkKk(), TextAlign.INSTANCE.m5584getJustifye0LSkKk());
            int iM5055toLayoutHyphenationFrequency3fSNIE = AndroidParagraph_androidKt.m5055toLayoutHyphenationFrequency3fSNIE(style.getParagraphStyle().getHyphens());
            int iM5054toLayoutBreakStrategyxImikfE = AndroidParagraph_androidKt.m5054toLayoutBreakStrategyxImikfE(LineBreak.m5502getStrategyfcGXIks(style.m5230getLineBreakrAG3T2k()));
            int iM5056toLayoutLineBreakStylehpcqdu8 = AndroidParagraph_androidKt.m5056toLayoutLineBreakStylehpcqdu8(LineBreak.m5503getStrictnessusljTpc(style.m5230getLineBreakrAG3T2k()));
            int iM5057toLayoutLineBreakWordStylewPN0Rpw = AndroidParagraph_androidKt.m5057toLayoutLineBreakWordStylewPN0Rpw(LineBreak.m5504getWordBreakjp8hJ3c(style.m5230getLineBreakrAG3T2k()));
            TextUtils.TruncateAt truncateAt = z ? TextUtils.TruncateAt.END : null;
            TextLayout textLayoutConstructTextLayout = constructTextLayout(iM5053toLayoutAlignaXe7zB0, zM5578equalsimpl0 ? 1 : 0, truncateAt, i10, iM5055toLayoutHyphenationFrequency3fSNIE, iM5054toLayoutBreakStrategyxImikfE, iM5056toLayoutLineBreakStylehpcqdu8, iM5057toLayoutLineBreakWordStylewPN0Rpw);
            if (z && textLayoutConstructTextLayout.getHeight() > Constraints.m5645getMaxHeightimpl(j10) && i10 > 1) {
                int iNumberOfLinesThatFitMaxHeight = AndroidParagraph_androidKt.numberOfLinesThatFitMaxHeight(textLayoutConstructTextLayout, Constraints.m5645getMaxHeightimpl(j10));
                if (iNumberOfLinesThatFitMaxHeight < 0 || iNumberOfLinesThatFitMaxHeight == i10) {
                    androidParagraph = this;
                } else {
                    int i11 = iNumberOfLinesThatFitMaxHeight < 1 ? 1 : iNumberOfLinesThatFitMaxHeight;
                    androidParagraph = this;
                    textLayoutConstructTextLayout = androidParagraph.constructTextLayout(iM5053toLayoutAlignaXe7zB0, zM5578equalsimpl0 ? 1 : 0, truncateAt, i11, iM5055toLayoutHyphenationFrequency3fSNIE, iM5054toLayoutBreakStrategyxImikfE, iM5056toLayoutLineBreakStylehpcqdu8, iM5057toLayoutLineBreakWordStylewPN0Rpw);
                }
                androidParagraph.layout = textLayoutConstructTextLayout;
            } else {
                androidParagraph = this;
                androidParagraph.layout = textLayoutConstructTextLayout;
            }
            androidParagraph.getTextPaint$ui_text_release().m5446setBrush12SF9DM(style.getBrush(), SizeKt.Size(androidParagraph.getWidth(), androidParagraph.getHeight()), style.getAlpha());
            boolean z5 = false;
            for (ShaderBrushSpan shaderBrushSpan : androidParagraph.getShaderBrushSpans(androidParagraph.layout)) {
                shaderBrushSpan.m5465setSizeuvyYCjk(SizeKt.Size(androidParagraph.getWidth(), androidParagraph.getHeight()));
            }
            CharSequence charSequence2 = androidParagraph.charSequence;
            if (charSequence2 instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence2;
                Object[] spans = spanned.getSpans(0, charSequence2.length(), PlaceholderSpan.class);
                ArrayList arrayList = new ArrayList(spans.length);
                int length = spans.length;
                int i12 = 0;
                while (i12 < length) {
                    PlaceholderSpan placeholderSpan = (PlaceholderSpan) spans[i12];
                    int spanStart = spanned.getSpanStart(placeholderSpan);
                    int spanEnd = spanned.getSpanEnd(placeholderSpan);
                    int lineForOffset = androidParagraph.layout.getLineForOffset(spanStart);
                    boolean z10 = lineForOffset >= androidParagraph.maxLines ? true : z5;
                    boolean z11 = (androidParagraph.layout.getLineEllipsisCount(lineForOffset) <= 0 || spanEnd <= androidParagraph.layout.getLineEllipsisOffset(lineForOffset)) ? z5 : true;
                    boolean z12 = spanEnd > androidParagraph.layout.getLineEnd(lineForOffset) ? true : z5;
                    if (z11 || z12 || z10) {
                        rect = null;
                    } else {
                        int i13 = WhenMappings.$EnumSwitchMapping$0[androidParagraph.getBidiRunDirection(spanStart).ordinal()];
                        if (i13 != 1) {
                            if (i13 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            horizontalPosition = androidParagraph.getHorizontalPosition(spanStart, true) - placeholderSpan.getWidthPx();
                        } else {
                            horizontalPosition = androidParagraph.getHorizontalPosition(spanStart, true);
                        }
                        float widthPx = placeholderSpan.getWidthPx() + horizontalPosition;
                        TextLayout textLayout = androidParagraph.layout;
                        switch (placeholderSpan.getVerticalAlign()) {
                            case 0:
                                lineBaseline = textLayout.getLineBaseline(lineForOffset);
                                heightPx = placeholderSpan.getHeightPx();
                                lineTop = lineBaseline - heightPx;
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            case 1:
                                lineTop = textLayout.getLineTop(lineForOffset);
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            case 2:
                                lineBaseline = textLayout.getLineBottom(lineForOffset);
                                heightPx = placeholderSpan.getHeightPx();
                                lineTop = lineBaseline - heightPx;
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            case 3:
                                lineTop = ((textLayout.getLineBottom(lineForOffset) + textLayout.getLineTop(lineForOffset)) - placeholderSpan.getHeightPx()) / 2;
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            case 4:
                                heightPx2 = placeholderSpan.getFontMetrics().ascent;
                                lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                                lineTop = lineBaseline2 + heightPx2;
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            case 5:
                                lineTop = (textLayout.getLineBaseline(lineForOffset) + placeholderSpan.getFontMetrics().descent) - placeholderSpan.getHeightPx();
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            case 6:
                                Paint.FontMetricsInt fontMetrics = placeholderSpan.getFontMetrics();
                                heightPx2 = ((fontMetrics.ascent + fontMetrics.descent) - placeholderSpan.getHeightPx()) / 2;
                                lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                                lineTop = lineBaseline2 + heightPx2;
                                rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                break;
                            default:
                                throw new IllegalStateException("unexpected verticalAlignment");
                        }
                    }
                    arrayList.add(rect);
                    i12++;
                    z5 = false;
                }
                list = arrayList;
            } else {
                list = z.f19728i;
            }
            androidParagraph.placeholderRects = list;
            androidParagraph.wordBoundary = f.o(3, new AndroidParagraph$wordBoundary$2(androidParagraph));
            return;
        }
        throw new IllegalArgumentException("maxLines should be greater than 0");
    }

    private AndroidParagraph(String str, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<Placeholder>> list2, int i10, boolean z, long j10, FontFamily.Resolver resolver, Density density) {
        this(new AndroidParagraphIntrinsics(str, textStyle, list, list2, resolver, density), i10, z, j10, null);
    }
}
