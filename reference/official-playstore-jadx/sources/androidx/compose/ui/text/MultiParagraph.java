package androidx.compose.ui.text;

import a0.c;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.h;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.DelegatingFontLoaderForDeprecatedUsage_androidKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidMultiParagraphDraw_androidKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B-\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bB[\b\u0017\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\n\u0010\u0018B[\b\u0017\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u001bB+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u001eB[\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u001fJ<\u0010+\u001a\u00020(2\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*JP\u0010+\u001a\u00020(2\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,2\b\b\u0002\u0010/\u001a\u00020.ø\u0001\u0000¢\u0006\u0004\b0\u00101JX\u0010+\u001a\u00020(2\u0006\u0010!\u001a\u00020 2\u0006\u00103\u001a\u0002022\b\b\u0002\u00104\u001a\u00020\b2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,2\b\b\u0002\u0010/\u001a\u00020.ø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001d\u0010:\u001a\u0002092\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004¢\u0006\u0004\b:\u0010;J\u0015\u0010=\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\b¢\u0006\u0004\b=\u0010>J\u0018\u0010C\u001a\u00020\u00042\u0006\u0010@\u001a\u00020?ø\u0001\u0000¢\u0006\u0004\bA\u0010BJ\u0015\u0010F\u001a\u00020E2\u0006\u0010D\u001a\u00020\u0004¢\u0006\u0004\bF\u0010GJ*\u0010O\u001a\u00020J2\u0006\u0010I\u001a\u00020H2\u0006\u0010K\u001a\u00020J2\b\b\u0001\u0010L\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\bM\u0010NJ\u001d\u0010Q\u001a\u00020\b2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u0006¢\u0006\u0004\bQ\u0010RJ\u0015\u0010T\u001a\u00020S2\u0006\u0010D\u001a\u00020\u0004¢\u0006\u0004\bT\u0010UJ\u0015\u0010V\u001a\u00020S2\u0006\u0010D\u001a\u00020\u0004¢\u0006\u0004\bV\u0010UJ\u001b\u0010Y\u001a\u00020H2\u0006\u0010D\u001a\u00020\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bW\u0010XJ\u0015\u0010Z\u001a\u00020E2\u0006\u0010D\u001a\u00020\u0004¢\u0006\u0004\bZ\u0010GJ\u0015\u0010[\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u0004¢\u0006\u0004\b[\u0010\\J\u0015\u0010^\u001a\u00020\b2\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\b^\u0010_J\u0015\u0010`\u001a\u00020\b2\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\b`\u0010_J\u0015\u0010a\u001a\u00020\b2\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\ba\u0010_J\u0015\u0010b\u001a\u00020\b2\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\bb\u0010_J\u0015\u0010c\u001a\u00020\b2\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\bc\u0010_J\u0015\u0010d\u001a\u00020\b2\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\bd\u0010_J\u0015\u0010e\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\be\u0010\\J\u001f\u0010g\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u00042\b\b\u0002\u0010f\u001a\u00020\u0006¢\u0006\u0004\bg\u0010hJ\u0015\u0010i\u001a\u00020\u00062\u0006\u0010]\u001a\u00020\u0004¢\u0006\u0004\bi\u0010jJ\u0017\u0010k\u001a\u00020(2\u0006\u0010D\u001a\u00020\u0004H\u0002¢\u0006\u0004\bk\u0010lJ\u0017\u0010m\u001a\u00020(2\u0006\u0010D\u001a\u00020\u0004H\u0002¢\u0006\u0004\bm\u0010lJ\u0017\u0010n\u001a\u00020(2\u0006\u0010]\u001a\u00020\u0004H\u0002¢\u0006\u0004\bn\u0010lR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010o\u001a\u0004\bp\u0010qR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010r\u001a\u0004\bs\u0010tR\u0017\u0010u\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bu\u0010v\u001a\u0004\bw\u0010xR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010y\u001a\u0004\bz\u0010{R\u0017\u0010|\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b|\u0010y\u001a\u0004\b}\u0010{R\u0017\u0010~\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b~\u0010r\u001a\u0004\b\u007f\u0010tR$\u0010\u0080\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0\u00108\u0006¢\u0006\u0010\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R&\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010\u00108\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0085\u0001\u0010\u0081\u0001\u001a\u0006\b\u0086\u0001\u0010\u0083\u0001R\u0013\u0010\u0088\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010{R\u0013\u0010\u008a\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u0010{R\u0013\u0010\u008c\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u0010{R\u0013\u0010\u008e\u0001\u001a\u00020\b8F¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010{R\u0016\u0010\r\u001a\u00020\f8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0091\u0001"}, d2 = {"Landroidx/compose/ui/text/MultiParagraph;", "", "Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "intrinsics", "", "maxLines", "", "ellipsis", "", "width", "<init>", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;IZF)V", "Landroidx/compose/ui/text/AnnotatedString;", "annotatedString", "Landroidx/compose/ui/text/TextStyle;", "style", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "placeholders", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "resourceLoader", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;IZFLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;)V", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;FLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IZ)V", "Landroidx/compose/ui/unit/Constraints;", "constraints", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;JIZLkotlin/jvm/internal/h;)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IZLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/graphics/Canvas;", "canvas", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Shadow;", "shadow", "Landroidx/compose/ui/text/style/TextDecoration;", "decoration", "Lx6/t0;", "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "paint", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "Landroidx/compose/ui/graphics/Brush;", "brush", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/graphics/Path;", "getPathForRange", "(II)Landroidx/compose/ui/graphics/Path;", "vertical", "getLineForVerticalPosition", "(F)I", "Landroidx/compose/ui/geometry/Offset;", "position", "getOffsetForPosition-k-4lQ0M", "(J)I", "getOffsetForPosition", "offset", "Landroidx/compose/ui/geometry/Rect;", "getBoundingBox", "(I)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/text/TextRange;", "range", "", "array", "arrayStart", "fillBoundingBoxes-8ffj60Q", "(J[FI)[F", "fillBoundingBoxes", "usePrimaryDirection", "getHorizontalPosition", "(IZ)F", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getParagraphDirection", "(I)Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "getWordBoundary--jx7JFs", "(I)J", "getWordBoundary", "getCursorRect", "getLineForOffset", "(I)I", "lineIndex", "getLineLeft", "(I)F", "getLineRight", "getLineTop", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "visibleEnd", "getLineEnd", "(IZ)I", "isLineEllipsized", "(I)Z", "requireIndexInRange", "(I)V", "requireIndexInRangeInclusiveEnd", "requireLineIndexInRange", "Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "getIntrinsics", "()Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "I", "getMaxLines", "()I", "didExceedMaxLines", "Z", "getDidExceedMaxLines", "()Z", "F", "getWidth", "()F", "height", "getHeight", "lineCount", "getLineCount", "placeholderRects", "Ljava/util/List;", "getPlaceholderRects", "()Ljava/util/List;", "Landroidx/compose/ui/text/ParagraphInfo;", "paragraphInfoList", "getParagraphInfoList$ui_text_release", "getMinIntrinsicWidth", "minIntrinsicWidth", "getMaxIntrinsicWidth", "maxIntrinsicWidth", "getFirstBaseline", "firstBaseline", "getLastBaseline", "lastBaseline", "getAnnotatedString", "()Landroidx/compose/ui/text/AnnotatedString;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MultiParagraph {
    public static final int $stable = 8;
    private final boolean didExceedMaxLines;
    private final float height;
    private final MultiParagraphIntrinsics intrinsics;
    private final int lineCount;
    private final int maxLines;
    private final List<ParagraphInfo> paragraphInfoList;
    private final List<Rect> placeholderRects;
    private final float width;

    /* JADX INFO: renamed from: androidx.compose.ui.text.MultiParagraph$getPathForRange$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/ParagraphInfo;", "paragraphInfo", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/ParagraphInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<ParagraphInfo, t0> {
        final /* synthetic */ int $end;
        final /* synthetic */ Path $path;
        final /* synthetic */ int $start;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Path path, int i10, int i11) {
            super(1);
            this.$path = path;
            this.$start = i10;
            this.$end = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ParagraphInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(ParagraphInfo paragraphInfo) {
            h.g(this.$path, paragraphInfo.toGlobal(paragraphInfo.getParagraph().getPathForRange(paragraphInfo.toLocalIndex(this.$start), paragraphInfo.toLocalIndex(this.$end))), 0L, 2, null);
        }
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j10, Density density, FontFamily.Resolver resolver, List list, int i10, boolean z, kotlin.jvm.internal.h hVar) {
        this(annotatedString, textStyle, j10, density, resolver, (List<AnnotatedString.Range<Placeholder>>) list, i10, z);
    }

    private final AnnotatedString getAnnotatedString() {
        return this.intrinsics.getAnnotatedString();
    }

    public static /* synthetic */ int getLineEnd$default(MultiParagraph multiParagraph, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            z = false;
        }
        return multiParagraph.getLineEnd(i10, z);
    }

    /* JADX INFO: renamed from: paint-RPmYEkk$default, reason: not valid java name */
    public static /* synthetic */ void m5069paintRPmYEkk$default(MultiParagraph multiParagraph, Canvas canvas, long j10, Shadow shadow, TextDecoration textDecoration, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        multiParagraph.m5075paintRPmYEkk(canvas, j10, (i10 & 4) != 0 ? null : shadow, (i10 & 8) != 0 ? null : textDecoration);
    }

    /* JADX INFO: renamed from: paint-hn5TExg$default, reason: not valid java name */
    public static /* synthetic */ void m5070painthn5TExg$default(MultiParagraph multiParagraph, Canvas canvas, Brush brush, float f10, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i10, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            f10 = Float.NaN;
        }
        multiParagraph.m5076painthn5TExg(canvas, brush, f10, (i11 & 8) != 0 ? null : shadow, (i11 & 16) != 0 ? null : textDecoration, (i11 & 32) != 0 ? null : drawStyle, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    private final void requireIndexInRange(int offset) {
        if (offset < 0 || offset >= getAnnotatedString().getText().length()) {
            StringBuilder sbS = c.s(offset, "offset(", ") is out of bounds [0, ");
            sbS.append(getAnnotatedString().length());
            sbS.append(')');
            throw new IllegalArgumentException(sbS.toString().toString());
        }
    }

    private final void requireIndexInRangeInclusiveEnd(int offset) {
        if (offset < 0 || offset > getAnnotatedString().getText().length()) {
            StringBuilder sbS = c.s(offset, "offset(", ") is out of bounds [0, ");
            sbS.append(getAnnotatedString().length());
            sbS.append(']');
            throw new IllegalArgumentException(sbS.toString().toString());
        }
    }

    private final void requireLineIndexInRange(int lineIndex) {
        if (lineIndex < 0 || lineIndex >= this.lineCount) {
            throw new IllegalArgumentException(c.o(c.s(lineIndex, "lineIndex(", ") is out of bounds [0, "), this.lineCount, ')').toString());
        }
    }

    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public final float[] m5071fillBoundingBoxes8ffj60Q(long range, float[] array, int arrayStart) {
        requireIndexInRange(TextRange.m5190getMinimpl(range));
        requireIndexInRangeInclusiveEnd(TextRange.m5189getMaximpl(range));
        i0 i0Var = new i0();
        i0Var.f19744i = arrayStart;
        MultiParagraphKt.m5077findParagraphsByRangeSbBc2M(this.paragraphInfoList, range, new MultiParagraph$fillBoundingBoxes$1(range, array, i0Var, new h0()));
        return array;
    }

    public final ResolvedTextDirection getBidiRunDirection(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == getAnnotatedString().length() ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.getParagraph().getBidiRunDirection(paragraphInfo.toLocalIndex(offset));
    }

    public final Rect getBoundingBox(int offset) {
        requireIndexInRange(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.toGlobal(paragraphInfo.getParagraph().getBoundingBox(paragraphInfo.toLocalIndex(offset)));
    }

    public final Rect getCursorRect(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == getAnnotatedString().length() ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.toGlobal(paragraphInfo.getParagraph().getCursorRect(paragraphInfo.toLocalIndex(offset)));
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final float getFirstBaseline() {
        if (this.paragraphInfoList.isEmpty()) {
            return 0.0f;
        }
        return this.paragraphInfoList.get(0).getParagraph().getFirstBaseline();
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        requireIndexInRangeInclusiveEnd(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == getAnnotatedString().length() ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.getParagraph().getHorizontalPosition(paragraphInfo.toLocalIndex(offset), usePrimaryDirection);
    }

    public final MultiParagraphIntrinsics getIntrinsics() {
        return this.intrinsics;
    }

    public final float getLastBaseline() {
        if (this.paragraphInfoList.isEmpty()) {
            return 0.0f;
        }
        ParagraphInfo paragraphInfo = (ParagraphInfo) x.w0(this.paragraphInfoList);
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLastBaseline());
    }

    public final float getLineBottom(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLineBottom(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final int getLineEnd(int lineIndex, boolean visibleEnd) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().getLineEnd(paragraphInfo.toLocalLineIndex(lineIndex), visibleEnd));
    }

    public final int getLineForOffset(int offset) {
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset >= getAnnotatedString().length() ? t7.a.w(this.paragraphInfoList) : offset < 0 ? 0 : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.toGlobalLineIndex(paragraphInfo.getParagraph().getLineForOffset(paragraphInfo.toLocalIndex(offset)));
    }

    public final int getLineForVerticalPosition(float vertical) {
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(vertical <= 0.0f ? 0 : vertical >= this.height ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByY(this.paragraphInfoList, vertical));
        return paragraphInfo.getLength() == 0 ? paragraphInfo.getStartLineIndex() : paragraphInfo.toGlobalLineIndex(paragraphInfo.getParagraph().getLineForVerticalPosition(paragraphInfo.toLocalYPosition(vertical)));
    }

    public final float getLineHeight(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineHeight(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final float getLineLeft(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineLeft(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final float getLineRight(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineRight(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final int getLineStart(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().getLineStart(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final float getLineTop(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLineTop(paragraphInfo.toLocalLineIndex(lineIndex)));
    }

    public final float getLineWidth(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex));
        return paragraphInfo.getParagraph().getLineWidth(paragraphInfo.toLocalLineIndex(lineIndex));
    }

    public final float getMaxIntrinsicWidth() {
        return this.intrinsics.getMaxIntrinsicWidth();
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    public final float getMinIntrinsicWidth() {
        return this.intrinsics.getMinIntrinsicWidth();
    }

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public final int m5072getOffsetForPositionk4lQ0M(long position) {
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(Offset.m3237getYimpl(position) <= 0.0f ? 0 : Offset.m3237getYimpl(position) >= this.height ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByY(this.paragraphInfoList, Offset.m3237getYimpl(position)));
        return paragraphInfo.getLength() == 0 ? paragraphInfo.getStartIndex() : paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().mo5043getOffsetForPositionk4lQ0M(paragraphInfo.m5082toLocalMKHz9U(position)));
    }

    public final ResolvedTextDirection getParagraphDirection(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == getAnnotatedString().length() ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.getParagraph().getParagraphDirection(paragraphInfo.toLocalIndex(offset));
    }

    public final List<ParagraphInfo> getParagraphInfoList$ui_text_release() {
        return this.paragraphInfoList;
    }

    public final Path getPathForRange(int start, int end) {
        if (start < 0 || start > end || end > getAnnotatedString().getText().length()) {
            StringBuilder sbV = androidx.compose.foundation.c.v("Start(", start, ") or End(", end, ") is out of range [0..");
            sbV.append(getAnnotatedString().getText().length());
            sbV.append("), or start > end!");
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        if (start == end) {
            return AndroidPath_androidKt.Path();
        }
        Path Path = AndroidPath_androidKt.Path();
        MultiParagraphKt.m5077findParagraphsByRangeSbBc2M(this.paragraphInfoList, TextRangeKt.TextRange(start, end), new AnonymousClass2(Path, start, end));
        return Path;
    }

    public final List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public final long m5073getWordBoundaryjx7JFs(int offset) {
        requireIndexInRangeInclusiveEnd(offset);
        ParagraphInfo paragraphInfo = this.paragraphInfoList.get(offset == getAnnotatedString().length() ? t7.a.w(this.paragraphInfoList) : MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset));
        return paragraphInfo.m5081toGlobalGEjPoXI(paragraphInfo.getParagraph().mo5044getWordBoundaryjx7JFs(paragraphInfo.toLocalIndex(offset)));
    }

    public final boolean isLineEllipsized(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        return this.paragraphInfoList.get(MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex)).getParagraph().isLineEllipsized(lineIndex);
    }

    /* JADX INFO: renamed from: paint-LG529CI, reason: not valid java name */
    public final void m5074paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        canvas.save();
        List<ParagraphInfo> list = this.paragraphInfoList;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            ParagraphInfo paragraphInfo = list.get(i10);
            paragraphInfo.getParagraph().mo5045paintLG529CI(canvas, color, shadow, decoration, drawStyle, blendMode);
            canvas.translate(0.0f, paragraphInfo.getParagraph().getHeight());
        }
        canvas.restore();
    }

    @e
    /* JADX INFO: renamed from: paint-RPmYEkk, reason: not valid java name */
    public final /* synthetic */ void m5075paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration decoration) {
        canvas.save();
        List<ParagraphInfo> list = this.paragraphInfoList;
        int size = list.size();
        int i10 = 0;
        while (i10 < size) {
            ParagraphInfo paragraphInfo = list.get(i10);
            Canvas canvas2 = canvas;
            long j10 = color;
            paragraphInfo.getParagraph().mo5046paintRPmYEkk(canvas2, j10, shadow, decoration);
            canvas2.translate(0.0f, paragraphInfo.getParagraph().getHeight());
            i10++;
            canvas = canvas2;
            color = j10;
        }
        canvas.restore();
    }

    /* JADX INFO: renamed from: paint-hn5TExg, reason: not valid java name */
    public final void m5076painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        AndroidMultiParagraphDraw_androidKt.m5434drawMultiParagraph7AXcY_I(this, canvas, brush, alpha, shadow, decoration, drawStyle, blendMode);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j10, int i10, boolean z, kotlin.jvm.internal.h hVar) {
        this(multiParagraphIntrinsics, j10, i10, z);
    }

    private MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j10, int i10, boolean z) {
        boolean z5;
        int iM5645getMaxHeightimpl;
        this.intrinsics = multiParagraphIntrinsics;
        this.maxLines = i10;
        if (Constraints.m5648getMinWidthimpl(j10) == 0 && Constraints.m5647getMinHeightimpl(j10) == 0) {
            ArrayList arrayList = new ArrayList();
            List<ParagraphIntrinsicInfo> infoList$ui_text_release = multiParagraphIntrinsics.getInfoList$ui_text_release();
            int size = infoList$ui_text_release.size();
            int i11 = 0;
            float f10 = 0.0f;
            int i12 = 0;
            while (i12 < size) {
                ParagraphIntrinsicInfo paragraphIntrinsicInfo = infoList$ui_text_release.get(i12);
                ParagraphIntrinsics intrinsics = paragraphIntrinsicInfo.getIntrinsics();
                int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
                if (Constraints.m5641getHasBoundedHeightimpl(j10)) {
                    iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10) - ParagraphKt.ceilToInt(f10);
                    if (iM5645getMaxHeightimpl < 0) {
                        iM5645getMaxHeightimpl = 0;
                    }
                } else {
                    iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
                }
                Paragraph paragraphM5085Paragraph_EkL_Y = ParagraphKt.m5085Paragraph_EkL_Y(intrinsics, ConstraintsKt.Constraints$default(0, iM5646getMaxWidthimpl, 0, iM5645getMaxHeightimpl, 5, null), this.maxLines - i11, z);
                float height = paragraphM5085Paragraph_EkL_Y.getHeight() + f10;
                int lineCount = paragraphM5085Paragraph_EkL_Y.getLineCount() + i11;
                arrayList.add(new ParagraphInfo(paragraphM5085Paragraph_EkL_Y, paragraphIntrinsicInfo.getStartIndex(), paragraphIntrinsicInfo.getEndIndex(), i11, lineCount, f10, height));
                if (paragraphM5085Paragraph_EkL_Y.getDidExceedMaxLines() || (lineCount == this.maxLines && i12 != t7.a.w(this.intrinsics.getInfoList$ui_text_release()))) {
                    z5 = true;
                    i11 = lineCount;
                    f10 = height;
                    break;
                } else {
                    i12++;
                    i11 = lineCount;
                    f10 = height;
                }
            }
            z5 = false;
            this.height = f10;
            this.lineCount = i11;
            this.didExceedMaxLines = z5;
            this.paragraphInfoList = arrayList;
            this.width = Constraints.m5646getMaxWidthimpl(j10);
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i13 = 0; i13 < size2; i13++) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i13);
                List<Rect> placeholderRects = paragraphInfo.getParagraph().getPlaceholderRects();
                ArrayList arrayList3 = new ArrayList(placeholderRects.size());
                int size3 = placeholderRects.size();
                for (int i14 = 0; i14 < size3; i14++) {
                    Rect rect = placeholderRects.get(i14);
                    arrayList3.add(rect != null ? paragraphInfo.toGlobal(rect) : null);
                }
                x.b0(arrayList2, arrayList3);
            }
            if (arrayList2.size() < this.intrinsics.getPlaceholders().size()) {
                int size4 = this.intrinsics.getPlaceholders().size() - arrayList2.size();
                ArrayList arrayList4 = new ArrayList(size4);
                for (int i15 = 0; i15 < size4; i15++) {
                    arrayList4.add(null);
                }
                arrayList2 = x.I0(arrayList2, arrayList4);
            }
            this.placeholderRects = arrayList2;
            return;
        }
        throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j10, int i10, boolean z, int i11, kotlin.jvm.internal.h hVar) {
        this(multiParagraphIntrinsics, j10, (i11 & 4) != 0 ? Integer.MAX_VALUE : i10, (i11 & 8) != 0 ? false : z, null);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, int i10, boolean z, float f10, int i11, kotlin.jvm.internal.h hVar) {
        this(multiParagraphIntrinsics, (i11 & 2) != 0 ? Integer.MAX_VALUE : i10, (i11 & 4) != 0 ? false : z, f10);
    }

    @e
    public MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, int i10, boolean z, float f10) {
        this(multiParagraphIntrinsics, ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(f10), 0, 0, 13, null), i10, z, null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, List list, int i10, boolean z, float f10, Density density, Font.ResourceLoader resourceLoader, int i11, kotlin.jvm.internal.h hVar) {
        this(annotatedString, textStyle, (List<AnnotatedString.Range<Placeholder>>) ((i11 & 4) != 0 ? z.f19728i : list), (i11 & 8) != 0 ? Integer.MAX_VALUE : i10, (i11 & 16) != 0 ? false : z, f10, density, resourceLoader);
    }

    @e
    public MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, List<AnnotatedString.Range<Placeholder>> list, int i10, boolean z, float f10, Density density, Font.ResourceLoader resourceLoader) {
        this(new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, DelegatingFontLoaderForDeprecatedUsage_androidKt.createFontFamilyResolver(resourceLoader)), ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(f10), 0, 0, 13, null), i10, z, null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, float f10, Density density, FontFamily.Resolver resolver, List list, int i10, boolean z, int i11, kotlin.jvm.internal.h hVar) {
        this(annotatedString, textStyle, f10, density, resolver, (List<AnnotatedString.Range<Placeholder>>) ((i11 & 32) != 0 ? z.f19728i : list), (i11 & 64) != 0 ? Integer.MAX_VALUE : i10, (i11 & 128) != 0 ? false : z);
    }

    @e
    public MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, float f10, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<Placeholder>> list, int i10, boolean z) {
        this(new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, resolver), ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(f10), 0, 0, 13, null), i10, z, null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j10, Density density, FontFamily.Resolver resolver, List list, int i10, boolean z, int i11, kotlin.jvm.internal.h hVar) {
        this(annotatedString, textStyle, j10, density, resolver, (i11 & 32) != 0 ? z.f19728i : list, (i11 & 64) != 0 ? Integer.MAX_VALUE : i10, (i11 & 128) != 0 ? false : z, null);
    }

    private MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j10, Density density, FontFamily.Resolver resolver, List<AnnotatedString.Range<Placeholder>> list, int i10, boolean z) {
        this(new MultiParagraphIntrinsics(annotatedString, textStyle, list, density, resolver), j10, i10, z, null);
    }
}
