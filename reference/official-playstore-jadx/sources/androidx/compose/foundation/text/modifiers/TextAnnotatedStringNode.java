package androidx.compose.foundation.text.modifiers;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.b;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001yB¯\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0013\u0012\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0018\u00010\u0016\u0012\u001e\b\u0002\u0010\u001b\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J\u001f\u0010#\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b%\u0010&JV\u0010)\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0014\u0010\u0019\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0018\u00010\u00162\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b'\u0010(JK\u0010*\u001a\u00020\u00112\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u001c\u0010\u001b\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b*\u0010+J-\u00100\u001a\u00020\r2\u0006\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u0011¢\u0006\u0004\b0\u00101J\u0013\u00103\u001a\u00020\r*\u000202H\u0016¢\u0006\u0004\b3\u00104J(\u0010>\u001a\u00020;2\u0006\u00106\u001a\u0002052\u0006\u00108\u001a\u0002072\u0006\u0010:\u001a\u000209ø\u0001\u0000¢\u0006\u0004\b<\u0010=J&\u0010@\u001a\u00020;*\u0002052\u0006\u00108\u001a\u0002072\u0006\u0010:\u001a\u000209H\u0016ø\u0001\u0000¢\u0006\u0004\b?\u0010=J%\u0010E\u001a\u00020\u00132\u0006\u0010B\u001a\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0013¢\u0006\u0004\bE\u0010FJ#\u0010G\u001a\u00020\u0013*\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0013H\u0016¢\u0006\u0004\bG\u0010FJ%\u0010I\u001a\u00020\u00132\u0006\u0010B\u001a\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010H\u001a\u00020\u0013¢\u0006\u0004\bI\u0010FJ#\u0010J\u001a\u00020\u0013*\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010H\u001a\u00020\u0013H\u0016¢\u0006\u0004\bJ\u0010FJ%\u0010K\u001a\u00020\u00132\u0006\u0010B\u001a\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0013¢\u0006\u0004\bK\u0010FJ#\u0010L\u001a\u00020\u0013*\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0013H\u0016¢\u0006\u0004\bL\u0010FJ%\u0010M\u001a\u00020\u00132\u0006\u0010B\u001a\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010H\u001a\u00020\u0013¢\u0006\u0004\bM\u0010FJ#\u0010N\u001a\u00020\u0013*\u00020A2\u0006\u00108\u001a\u00020C2\u0006\u0010H\u001a\u00020\u0013H\u0016¢\u0006\u0004\bN\u0010FJ\u0015\u0010Q\u001a\u00020\r2\u0006\u0010P\u001a\u00020O¢\u0006\u0004\bQ\u0010RJ\u0013\u0010S\u001a\u00020\r*\u00020OH\u0016¢\u0006\u0004\bS\u0010RJ\u0017\u0010W\u001a\u00020V2\u0006\u0010U\u001a\u00020TH\u0002¢\u0006\u0004\bW\u0010XJ\u0017\u0010Z\u001a\u00020\u00112\u0006\u0010Y\u001a\u00020\u0005H\u0002¢\u0006\u0004\bZ\u0010&J\u000f\u0010[\u001a\u00020\rH\u0002¢\u0006\u0004\b[\u0010\\R\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010]R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010^R\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010_R$\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010`R\u001c\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0010\u0010aR\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010bR\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010aR\u0016\u0010\u0015\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010aR$\u0010\u0019\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010cR,\u0010\u001b\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010`R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010eR$\u0010h\u001a\u0010\u0012\u0004\u0012\u00020g\u0012\u0004\u0012\u00020\u0013\u0018\u00010f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010j\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010kR*\u0010m\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0l\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010`R/\u0010v\u001a\u0004\u0018\u00010n2\b\u0010o\u001a\u0004\u0018\u00010n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bp\u0010q\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u0014\u0010x\u001a\u00020V8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010w\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006z"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/text/AnnotatedString;", "text", "Landroidx/compose/ui/text/TextStyle;", "style", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lx6/t0;", "onTextLayout", "Landroidx/compose/ui/text/style/TextOverflow;", "overflow", "", "softWrap", "", "maxLines", "minLines", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "placeholders", "Landroidx/compose/ui/geometry/Rect;", "onPlaceholderLayout", "Landroidx/compose/foundation/text/modifiers/SelectionController;", "selectionController", "Landroidx/compose/ui/graphics/ColorProducer;", "overrideColor", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;Lr7/l;IZIILjava/util/List;Lr7/l;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/internal/h;)V", TtmlNode.ATTR_TTS_COLOR, "updateDraw", "(Landroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/ui/text/TextStyle;)Z", "updateText", "(Landroidx/compose/ui/text/AnnotatedString;)Z", "updateLayoutRelatedArgs-MPT68mk", "(Landroidx/compose/ui/text/TextStyle;Ljava/util/List;IIZLandroidx/compose/ui/text/font/FontFamily$Resolver;I)Z", "updateLayoutRelatedArgs", "updateCallbacks", "(Lr7/l;Lr7/l;Landroidx/compose/foundation/text/modifiers/SelectionController;)Z", "drawChanged", "textChanged", "layoutChanged", "callbacksChanged", "doInvalidations", "(ZZZZ)V", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "Landroidx/compose/ui/layout/MeasureScope;", "measureScope", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measureNonExtension-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measureNonExtension", "measure-3p2s80s", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "intrinsicMeasureScope", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicWidthNonExtension", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "minIntrinsicWidth", "width", "minIntrinsicHeightNonExtension", "minIntrinsicHeight", "maxIntrinsicWidthNonExtension", "maxIntrinsicWidth", "maxIntrinsicHeightNonExtension", "maxIntrinsicHeight", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "contentDrawScope", "drawNonExtension", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "draw", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "getLayoutCache", "(Landroidx/compose/ui/unit/Density;)Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "updatedText", "setSubstitution", "clearSubstitution", "()V", "Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/text/TextStyle;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "Lr7/l;", "I", "Z", "Ljava/util/List;", "Landroidx/compose/foundation/text/modifiers/SelectionController;", "Landroidx/compose/ui/graphics/ColorProducer;", "", "Landroidx/compose/ui/layout/AlignmentLine;", "baselineCache", "Ljava/util/Map;", "_layoutCache", "Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "", "semanticsTextLayoutResult", "Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;", "<set-?>", "textSubstitution$delegate", "Landroidx/compose/runtime/MutableState;", "getTextSubstitution", "()Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;", "setTextSubstitution", "(Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;)V", "textSubstitution", "()Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "layoutCache", "TextSubstitutionValue", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextAnnotatedStringNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private MultiParagraphLayoutCache _layoutCache;
    private Map<AlignmentLine, Integer> baselineCache;
    private FontFamily.Resolver fontFamilyResolver;
    private int maxLines;
    private int minLines;
    private l<? super List<Rect>, t0> onPlaceholderLayout;
    private l<? super TextLayoutResult, t0> onTextLayout;
    private int overflow;
    private ColorProducer overrideColor;
    private List<AnnotatedString.Range<Placeholder>> placeholders;
    private SelectionController selectionController;
    private l<? super List<TextLayoutResult>, Boolean> semanticsTextLayoutResult;
    private boolean softWrap;
    private TextStyle style;
    private AnnotatedString text;

    /* JADX INFO: renamed from: textSubstitution$delegate, reason: from kotlin metadata */
    private final MutableState textSubstitution;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "textLayoutResult", "", "Landroidx/compose/ui/text/TextLayoutResult;", "invoke", "(Ljava/util/List;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<List<TextLayoutResult>, Boolean> {
        public AnonymousClass1() {
            super(1);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x00b7  */
        @Override // r7.l
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Boolean invoke(java.util.List<androidx.compose.ui.text.TextLayoutResult> r38) {
            /*
                r37 = this;
                r0 = r37
                androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.this
                androidx.compose.foundation.text.modifiers.MultiParagraphLayoutCache r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.access$getLayoutCache(r1)
                androidx.compose.ui.text.TextLayoutResult r2 = r1.getLayoutCache()
                if (r2 == 0) goto Lb7
                androidx.compose.ui.text.TextLayoutInput r3 = new androidx.compose.ui.text.TextLayoutInput
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                androidx.compose.ui.text.AnnotatedString r4 = r1.getText()
                androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.this
                androidx.compose.ui.text.TextStyle r5 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.access$getStyle$p(r1)
                androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.this
                androidx.compose.ui.graphics.ColorProducer r1 = androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.access$getOverrideColor$p(r1)
                if (r1 == 0) goto L2b
                long r6 = r1.m3553invoke0d7_KjU()
                goto L31
            L2b:
                androidx.compose.ui.graphics.Color$Companion r1 = androidx.compose.ui.graphics.Color.INSTANCE
                long r6 = r1.m3508getUnspecified0d7_KjU()
            L31:
                r35 = 16777214(0xfffffe, float:2.3509884E-38)
                r36 = 0
                r8 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
                r15 = 0
                r17 = 0
                r18 = 0
                r19 = 0
                r20 = 0
                r22 = 0
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                r27 = 0
                r29 = 0
                r30 = 0
                r31 = 0
                r32 = 0
                r33 = 0
                r34 = 0
                androidx.compose.ui.text.TextStyle r5 = androidx.compose.ui.text.TextStyle.m5212mergedA7vx0o$default(r5, r6, r8, r10, r11, r12, r13, r14, r15, r17, r18, r19, r20, r22, r23, r24, r25, r26, r27, r29, r30, r31, r32, r33, r34, r35, r36)
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                java.util.List r6 = r1.getPlaceholders()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                int r7 = r1.getMaxLines()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                boolean r8 = r1.getSoftWrap()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                int r9 = r1.getOverflow()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                androidx.compose.ui.unit.Density r10 = r1.getDensity()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                androidx.compose.ui.unit.LayoutDirection r11 = r1.getLayoutDirection()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                androidx.compose.ui.text.font.FontFamily$Resolver r12 = r1.getFontFamilyResolver()
                androidx.compose.ui.text.TextLayoutInput r1 = r2.getLayoutInput()
                long r13 = r1.getConstraints()
                r15 = 0
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r15)
                r6 = 2
                r7 = 0
                r4 = 0
                androidx.compose.ui.text.TextLayoutResult r1 = androidx.compose.ui.text.TextLayoutResult.m5162copyO0kMr_c$default(r2, r3, r4, r6, r7)
                if (r1 == 0) goto Lb7
                r2 = r38
                r2.add(r1)
                goto Lb8
            Lb7:
                r1 = 0
            Lb8:
                if (r1 == 0) goto Lbc
                r1 = 1
                goto Lbd
            Lbc:
                r1 = 0
            Lbd:
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.AnonymousClass1.invoke(java.util.List):java.lang.Boolean");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "updatedText", "Landroidx/compose/ui/text/AnnotatedString;", "invoke", "(Landroidx/compose/ui/text/AnnotatedString;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<AnnotatedString, Boolean> {
        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(AnnotatedString annotatedString) {
            TextAnnotatedStringNode.this.setSubstitution(annotatedString);
            SemanticsModifierNodeKt.invalidateSemantics(TextAnnotatedStringNode.this);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<Boolean, Boolean> {
        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }

        public final Boolean invoke(boolean z) {
            if (TextAnnotatedStringNode.this.getTextSubstitution() == null) {
                return Boolean.FALSE;
            }
            TextSubstitutionValue textSubstitution = TextAnnotatedStringNode.this.getTextSubstitution();
            if (textSubstitution != null) {
                textSubstitution.setShowingSubstitution(z);
            }
            SemanticsModifierNodeKt.invalidateSemantics(TextAnnotatedStringNode.this);
            LayoutModifierNodeKt.invalidateMeasurement(TextAnnotatedStringNode.this);
            DrawModifierNodeKt.invalidateDraw(TextAnnotatedStringNode.this);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements a<Boolean> {
        public AnonymousClass4() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextAnnotatedStringNode.this.clearSubstitution();
            SemanticsModifierNodeKt.invalidateSemantics(TextAnnotatedStringNode.this);
            LayoutModifierNodeKt.invalidateMeasurement(TextAnnotatedStringNode.this);
            DrawModifierNodeKt.invalidateDraw(TextAnnotatedStringNode.this);
            return Boolean.TRUE;
        }
    }

    public /* synthetic */ TextAnnotatedStringNode(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, l lVar, int i10, boolean z, int i11, int i12, List list, l lVar2, SelectionController selectionController, ColorProducer colorProducer, h hVar) {
        this(annotatedString, textStyle, resolver, lVar, i10, z, i11, i12, list, lVar2, selectionController, colorProducer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearSubstitution() {
        setTextSubstitution(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MultiParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new MultiParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders, null);
        }
        return this._layoutCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final TextSubstitutionValue getTextSubstitution() {
        return (TextSubstitutionValue) this.textSubstitution.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setSubstitution(AnnotatedString updatedText) {
        t0 t0Var;
        TextSubstitutionValue textSubstitution = getTextSubstitution();
        if (textSubstitution == null) {
            TextSubstitutionValue textSubstitutionValue = new TextSubstitutionValue(this.text, updatedText, false, null, 12, null);
            MultiParagraphLayoutCache multiParagraphLayoutCache = new MultiParagraphLayoutCache(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders, null);
            multiParagraphLayoutCache.setDensity$foundation_release(getLayoutCache().getDensity());
            textSubstitutionValue.setLayoutCache(multiParagraphLayoutCache);
            setTextSubstitution(textSubstitutionValue);
            return true;
        }
        if (p.a(updatedText, textSubstitution.getSubstitution())) {
            return false;
        }
        textSubstitution.setSubstitution(updatedText);
        MultiParagraphLayoutCache layoutCache = textSubstitution.getLayoutCache();
        if (layoutCache != null) {
            layoutCache.m937updateZNqEYIc(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders);
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        return t0Var != null;
    }

    private final void setTextSubstitution(TextSubstitutionValue textSubstitutionValue) {
        this.textSubstitution.setValue(textSubstitutionValue);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        l anonymousClass1 = this.semanticsTextLayoutResult;
        if (anonymousClass1 == null) {
            anonymousClass1 = new AnonymousClass1();
            this.semanticsTextLayoutResult = anonymousClass1;
        }
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, this.text);
        TextSubstitutionValue textSubstitution = getTextSubstitution();
        if (textSubstitution != null) {
            SemanticsPropertiesKt.setTextSubstitution(semanticsPropertyReceiver, textSubstitution.getSubstitution());
            SemanticsPropertiesKt.setShowingTextSubstitution(semanticsPropertyReceiver, textSubstitution.isShowingSubstitution());
        }
        SemanticsPropertiesKt.setTextSubstitution$default(semanticsPropertyReceiver, null, new AnonymousClass2(), 1, null);
        SemanticsPropertiesKt.showTextSubstitution$default(semanticsPropertyReceiver, null, new AnonymousClass3(), 1, null);
        SemanticsPropertiesKt.clearTextSubstitution$default(semanticsPropertyReceiver, null, new AnonymousClass4(), 1, null);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, null, anonymousClass1, 1, null);
    }

    public final void doInvalidations(boolean drawChanged, boolean textChanged, boolean layoutChanged, boolean callbacksChanged) {
        if (getIsAttached()) {
            if (textChanged || (drawChanged && this.semanticsTextLayoutResult != null)) {
                SemanticsModifierNodeKt.invalidateSemantics(this);
            }
            if (textChanged || layoutChanged || callbacksChanged) {
                getLayoutCache().m937updateZNqEYIc(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders);
                LayoutModifierNodeKt.invalidateMeasurement(this);
                DrawModifierNodeKt.invalidateDraw(this);
            }
            if (drawChanged) {
                DrawModifierNodeKt.invalidateDraw(this);
            }
        }
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        if (!getIsAttached()) {
            return;
        }
        SelectionController selectionController = this.selectionController;
        if (selectionController != null) {
            selectionController.draw(contentDrawScope);
        }
        Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
        TextLayoutResult textLayoutResult = getLayoutCache(contentDrawScope).getTextLayoutResult();
        MultiParagraph multiParagraph = textLayoutResult.getMultiParagraph();
        boolean z = textLayoutResult.getHasVisualOverflow() && !TextOverflow.m5624equalsimpl0(this.overflow, TextOverflow.INSTANCE.m5633getVisiblegIe3tQ8());
        if (z) {
            Rect rectM3276Recttz77jQw = RectKt.m3276Recttz77jQw(Offset.INSTANCE.m3252getZeroF1C5BW0(), SizeKt.Size(IntSize.m5844getWidthimpl(textLayoutResult.getSize()), IntSize.m5843getHeightimpl(textLayoutResult.getSize())));
            canvas.save();
            b.o(canvas, rectM3276Recttz77jQw, 0, 2, null);
        }
        try {
            TextDecoration textDecoration = this.style.getTextDecoration();
            if (textDecoration == null) {
                textDecoration = TextDecoration.INSTANCE.getNone();
            }
            TextDecoration textDecoration2 = textDecoration;
            Shadow shadow = this.style.getShadow();
            if (shadow == null) {
                shadow = Shadow.INSTANCE.getNone();
            }
            Shadow shadow2 = shadow;
            DrawStyle drawStyle = this.style.getDrawStyle();
            if (drawStyle == null) {
                drawStyle = Fill.INSTANCE;
            }
            DrawStyle drawStyle2 = drawStyle;
            Brush brush = this.style.getBrush();
            if (brush != null) {
                MultiParagraph.m5070painthn5TExg$default(multiParagraph, canvas, brush, this.style.getAlpha(), shadow2, textDecoration2, drawStyle2, 0, 64, null);
            } else {
                ColorProducer colorProducer = this.overrideColor;
                long jM3553invoke0d7_KjU = colorProducer != null ? colorProducer.m3553invoke0d7_KjU() : Color.INSTANCE.m3508getUnspecified0d7_KjU();
                Color.Companion companion = Color.INSTANCE;
                if (jM3553invoke0d7_KjU == companion.m3508getUnspecified0d7_KjU()) {
                    jM3553invoke0d7_KjU = this.style.m5222getColor0d7_KjU() != companion.m3508getUnspecified0d7_KjU() ? this.style.m5222getColor0d7_KjU() : companion.m3498getBlack0d7_KjU();
                }
                multiParagraph.m5074paintLG529CI(canvas, (32 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : jM3553invoke0d7_KjU, (32 & 4) != 0 ? null : shadow2, (32 & 8) != 0 ? null : textDecoration2, (32 & 16) == 0 ? drawStyle2 : null, (32 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : 0);
            }
            if (z) {
                canvas.restore();
            }
            List<AnnotatedString.Range<Placeholder>> list = this.placeholders;
            if (list == null || list.isEmpty()) {
                return;
            }
            contentDrawScope.drawContent();
        } finally {
        }
    }

    public final void drawNonExtension(ContentDrawScope contentDrawScope) {
        draw(contentDrawScope);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* JADX INFO: renamed from: getShouldClearDescendantSemantics */
    public final /* synthetic */ boolean getIsClearingSemantics() {
        return androidx.compose.ui.node.h.a(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return androidx.compose.ui.node.h.b(this);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i10, intrinsicMeasureScope.getLayoutDirection());
    }

    public final int maxIntrinsicHeightNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int width) {
        return maxIntrinsicHeight(intrinsicMeasureScope, measurable, width);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).maxIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    public final int maxIntrinsicWidthNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int height) {
        return maxIntrinsicWidth(intrinsicMeasureScope, measurable, height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        MultiParagraphLayoutCache layoutCache = getLayoutCache(measureScope);
        boolean zM936layoutWithConstraintsK40F9xA = layoutCache.m936layoutWithConstraintsK40F9xA(j10, measureScope.getLayoutDirection());
        TextLayoutResult textLayoutResult = layoutCache.getTextLayoutResult();
        textLayoutResult.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts();
        if (zM936layoutWithConstraintsK40F9xA) {
            LayoutModifierNodeKt.invalidateLayer(this);
            l<? super TextLayoutResult, t0> lVar = this.onTextLayout;
            if (lVar != null) {
                lVar.invoke(textLayoutResult);
            }
            SelectionController selectionController = this.selectionController;
            if (selectionController != null) {
                selectionController.updateTextLayout(textLayoutResult);
            }
            this.baselineCache = h0.t0(new x(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(t7.a.M(textLayoutResult.getFirstBaseline()))), new x(AlignmentLineKt.getLastBaseline(), Integer.valueOf(t7.a.M(textLayoutResult.getLastBaseline()))));
        }
        l<? super List<Rect>, t0> lVar2 = this.onPlaceholderLayout;
        if (lVar2 != null) {
            lVar2.invoke(textLayoutResult.getPlaceholderRects());
        }
        return measureScope.layout(IntSize.m5844getWidthimpl(textLayoutResult.getSize()), IntSize.m5843getHeightimpl(textLayoutResult.getSize()), this.baselineCache, new TextAnnotatedStringNode$measure$1(measurable.mo4631measureBRTryo0(LayoutUtilsKt.fixedCoerceHeightAndWidthForBits(Constraints.INSTANCE, IntSize.m5844getWidthimpl(textLayoutResult.getSize()), IntSize.m5843getHeightimpl(textLayoutResult.getSize())))));
    }

    /* JADX INFO: renamed from: measureNonExtension-3p2s80s, reason: not valid java name */
    public final MeasureResult m952measureNonExtension3p2s80s(MeasureScope measureScope, Measurable measurable, long constraints) {
        return mo55measure3p2s80s(measureScope, measurable, constraints);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i10, intrinsicMeasureScope.getLayoutDirection());
    }

    public final int minIntrinsicHeightNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int width) {
        return minIntrinsicHeight(intrinsicMeasureScope, measurable, width);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).minIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    public final int minIntrinsicWidthNonExtension(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int height) {
        return minIntrinsicWidth(intrinsicMeasureScope, measurable, height);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }

    public final boolean updateCallbacks(l<? super TextLayoutResult, t0> onTextLayout, l<? super List<Rect>, t0> onPlaceholderLayout, SelectionController selectionController) {
        boolean z;
        if (p.a(this.onTextLayout, onTextLayout)) {
            z = false;
        } else {
            this.onTextLayout = onTextLayout;
            z = true;
        }
        if (!p.a(this.onPlaceholderLayout, onPlaceholderLayout)) {
            this.onPlaceholderLayout = onPlaceholderLayout;
            z = true;
        }
        if (p.a(this.selectionController, selectionController)) {
            return z;
        }
        this.selectionController = selectionController;
        return true;
    }

    public final boolean updateDraw(ColorProducer color, TextStyle style) {
        boolean zA = p.a(color, this.overrideColor);
        this.overrideColor = color;
        return (zA && style.hasSameDrawAffectingAttributes(this.style)) ? false : true;
    }

    /* JADX INFO: renamed from: updateLayoutRelatedArgs-MPT68mk, reason: not valid java name */
    public final boolean m953updateLayoutRelatedArgsMPT68mk(TextStyle style, List<AnnotatedString.Range<Placeholder>> placeholders, int minLines, int maxLines, boolean softWrap, FontFamily.Resolver fontFamilyResolver, int overflow) {
        boolean z = !this.style.hasSameLayoutAffectingAttributes(style);
        this.style = style;
        if (!p.a(this.placeholders, placeholders)) {
            this.placeholders = placeholders;
            z = true;
        }
        if (this.minLines != minLines) {
            this.minLines = minLines;
            z = true;
        }
        if (this.maxLines != maxLines) {
            this.maxLines = maxLines;
            z = true;
        }
        if (this.softWrap != softWrap) {
            this.softWrap = softWrap;
            z = true;
        }
        if (!p.a(this.fontFamilyResolver, fontFamilyResolver)) {
            this.fontFamilyResolver = fontFamilyResolver;
            z = true;
        }
        if (TextOverflow.m5624equalsimpl0(this.overflow, overflow)) {
            return z;
        }
        this.overflow = overflow;
        return true;
    }

    public final boolean updateText(AnnotatedString text) {
        if (p.a(this.text, text)) {
            return false;
        }
        this.text = text;
        clearSubstitution();
        return true;
    }

    public /* synthetic */ TextAnnotatedStringNode(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, l lVar, int i10, boolean z, int i11, int i12, List list, l lVar2, SelectionController selectionController, ColorProducer colorProducer, int i13, h hVar) {
        this(annotatedString, textStyle, resolver, (i13 & 8) != 0 ? null : lVar, (i13 & 16) != 0 ? TextOverflow.INSTANCE.m5631getClipgIe3tQ8() : i10, (i13 & 32) != 0 ? true : z, (i13 & 64) != 0 ? Integer.MAX_VALUE : i11, (i13 & 128) != 0 ? 1 : i12, (i13 & 256) != 0 ? null : list, (i13 & 512) != 0 ? null : lVar2, (i13 & 1024) != 0 ? null : selectionController, (i13 & 2048) != 0 ? null : colorProducer, null);
    }

    private TextAnnotatedStringNode(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, l<? super TextLayoutResult, t0> lVar, int i10, boolean z, int i11, int i12, List<AnnotatedString.Range<Placeholder>> list, l<? super List<Rect>, t0> lVar2, SelectionController selectionController, ColorProducer colorProducer) {
        this.text = annotatedString;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.onTextLayout = lVar;
        this.overflow = i10;
        this.softWrap = z;
        this.maxLines = i11;
        this.minLines = i12;
        this.placeholders = list;
        this.onPlaceholderLayout = lVar2;
        this.selectionController = selectionController;
        this.overrideColor = colorProducer;
        this.textSubstitution = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0012\"\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;", "", "original", "Landroidx/compose/ui/text/AnnotatedString;", "substitution", "isShowingSubstitution", "", "layoutCache", "Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/AnnotatedString;ZLandroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;)V", "()Z", "setShowingSubstitution", "(Z)V", "getLayoutCache", "()Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;", "setLayoutCache", "(Landroidx/compose/foundation/text/modifiers/MultiParagraphLayoutCache;)V", "getOriginal", "()Landroidx/compose/ui/text/AnnotatedString;", "getSubstitution", "setSubstitution", "(Landroidx/compose/ui/text/AnnotatedString;)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class TextSubstitutionValue {
        public static final int $stable = 8;
        private boolean isShowingSubstitution;
        private MultiParagraphLayoutCache layoutCache;
        private final AnnotatedString original;
        private AnnotatedString substitution;

        public TextSubstitutionValue(AnnotatedString annotatedString, AnnotatedString annotatedString2, boolean z, MultiParagraphLayoutCache multiParagraphLayoutCache) {
            this.original = annotatedString;
            this.substitution = annotatedString2;
            this.isShowingSubstitution = z;
            this.layoutCache = multiParagraphLayoutCache;
        }

        public static /* synthetic */ TextSubstitutionValue copy$default(TextSubstitutionValue textSubstitutionValue, AnnotatedString annotatedString, AnnotatedString annotatedString2, boolean z, MultiParagraphLayoutCache multiParagraphLayoutCache, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                annotatedString = textSubstitutionValue.original;
            }
            if ((i10 & 2) != 0) {
                annotatedString2 = textSubstitutionValue.substitution;
            }
            if ((i10 & 4) != 0) {
                z = textSubstitutionValue.isShowingSubstitution;
            }
            if ((i10 & 8) != 0) {
                multiParagraphLayoutCache = textSubstitutionValue.layoutCache;
            }
            return textSubstitutionValue.copy(annotatedString, annotatedString2, z, multiParagraphLayoutCache);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AnnotatedString getOriginal() {
            return this.original;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AnnotatedString getSubstitution() {
            return this.substitution;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final MultiParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final TextSubstitutionValue copy(AnnotatedString original, AnnotatedString substitution, boolean isShowingSubstitution, MultiParagraphLayoutCache layoutCache) {
            return new TextSubstitutionValue(original, substitution, isShowingSubstitution, layoutCache);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TextSubstitutionValue)) {
                return false;
            }
            TextSubstitutionValue textSubstitutionValue = (TextSubstitutionValue) other;
            return p.a(this.original, textSubstitutionValue.original) && p.a(this.substitution, textSubstitutionValue.substitution) && this.isShowingSubstitution == textSubstitutionValue.isShowingSubstitution && p.a(this.layoutCache, textSubstitutionValue.layoutCache);
        }

        public final MultiParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final AnnotatedString getOriginal() {
            return this.original;
        }

        public final AnnotatedString getSubstitution() {
            return this.substitution;
        }

        public int hashCode() {
            int iHashCode = (((this.substitution.hashCode() + (this.original.hashCode() * 31)) * 31) + (this.isShowingSubstitution ? 1231 : 1237)) * 31;
            MultiParagraphLayoutCache multiParagraphLayoutCache = this.layoutCache;
            return iHashCode + (multiParagraphLayoutCache == null ? 0 : multiParagraphLayoutCache.hashCode());
        }

        public final boolean isShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        public final void setLayoutCache(MultiParagraphLayoutCache multiParagraphLayoutCache) {
            this.layoutCache = multiParagraphLayoutCache;
        }

        public final void setShowingSubstitution(boolean z) {
            this.isShowingSubstitution = z;
        }

        public final void setSubstitution(AnnotatedString annotatedString) {
            this.substitution = annotatedString;
        }

        public String toString() {
            return "TextSubstitutionValue(original=" + ((Object) this.original) + ", substitution=" + ((Object) this.substitution) + ", isShowingSubstitution=" + this.isShowingSubstitution + ", layoutCache=" + this.layoutCache + ')';
        }

        public /* synthetic */ TextSubstitutionValue(AnnotatedString annotatedString, AnnotatedString annotatedString2, boolean z, MultiParagraphLayoutCache multiParagraphLayoutCache, int i10, h hVar) {
            this(annotatedString, annotatedString2, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? null : multiParagraphLayoutCache);
        }
    }

    private final MultiParagraphLayoutCache getLayoutCache(Density density) {
        MultiParagraphLayoutCache layoutCache;
        TextSubstitutionValue textSubstitution = getTextSubstitution();
        if (textSubstitution != null && textSubstitution.isShowingSubstitution() && (layoutCache = textSubstitution.getLayoutCache()) != null) {
            layoutCache.setDensity$foundation_release(density);
            return layoutCache;
        }
        MultiParagraphLayoutCache layoutCache2 = getLayoutCache();
        layoutCache2.setDensity$foundation_release(density);
        return layoutCache2;
    }
}
