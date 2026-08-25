package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.c;
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
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.TextUnit;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001aBS\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\u00122\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\"\u0010#J\u0015\u0010$\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b$\u0010\u001dJ@\u0010'\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0004\b%\u0010&J%\u0010+\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\r¢\u0006\u0004\b+\u0010,J\u0013\u0010.\u001a\u00020\u001e*\u00020-H\u0016¢\u0006\u0004\b.\u0010/J&\u00108\u001a\u000205*\u0002002\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u000203H\u0016ø\u0001\u0000¢\u0006\u0004\b6\u00107J#\u0010<\u001a\u00020\u000f*\u0002092\u0006\u00102\u001a\u00020:2\u0006\u0010;\u001a\u00020\u000fH\u0016¢\u0006\u0004\b<\u0010=J#\u0010?\u001a\u00020\u000f*\u0002092\u0006\u00102\u001a\u00020:2\u0006\u0010>\u001a\u00020\u000fH\u0016¢\u0006\u0004\b?\u0010=J#\u0010@\u001a\u00020\u000f*\u0002092\u0006\u00102\u001a\u00020:2\u0006\u0010;\u001a\u00020\u000fH\u0016¢\u0006\u0004\b@\u0010=J#\u0010A\u001a\u00020\u000f*\u0002092\u0006\u00102\u001a\u00020:2\u0006\u0010>\u001a\u00020\u000fH\u0016¢\u0006\u0004\bA\u0010=J\u0013\u0010C\u001a\u00020\u001e*\u00020BH\u0016¢\u0006\u0004\bC\u0010DR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010ER\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010FR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010GR\u001c\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\f\u0010HR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010IR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010HR\u0016\u0010\u0011\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010HR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010JR$\u0010M\u001a\u0010\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u000f\u0018\u00010K8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0018\u0010O\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010PR*\u0010T\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0R\u0012\u0004\u0012\u00020\r\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR/\u0010^\u001a\u0004\u0018\u00010V2\b\u0010W\u001a\u0004\u0018\u00010V8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0014\u0010`\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010_\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006b"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "", "text", "Landroidx/compose/ui/text/TextStyle;", "style", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "Landroidx/compose/ui/text/style/TextOverflow;", "overflow", "", "softWrap", "", "maxLines", "minLines", "Landroidx/compose/ui/graphics/ColorProducer;", "overrideColor", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "getLayoutCache", "(Landroidx/compose/ui/unit/Density;)Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "updatedText", "setSubstitution", "(Ljava/lang/String;)Z", "Lx6/t0;", "clearSubstitution", "()V", TtmlNode.ATTR_TTS_COLOR, "updateDraw", "(Landroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/ui/text/TextStyle;)Z", "updateText", "updateLayoutRelatedArgs-HuAbxIM", "(Landroidx/compose/ui/text/TextStyle;IIZLandroidx/compose/ui/text/font/FontFamily$Resolver;I)Z", "updateLayoutRelatedArgs", "drawChanged", "textChanged", "layoutChanged", "doInvalidations", "(ZZZ)V", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "maxIntrinsicHeight", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "Ljava/lang/String;", "Landroidx/compose/ui/text/TextStyle;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "I", "Z", "Landroidx/compose/ui/graphics/ColorProducer;", "", "Landroidx/compose/ui/layout/AlignmentLine;", "baselineCache", "Ljava/util/Map;", "_layoutCache", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/TextLayoutResult;", "semanticsTextLayoutResult", "Lr7/l;", "Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "<set-?>", "textSubstitution$delegate", "Landroidx/compose/runtime/MutableState;", "getTextSubstitution", "()Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "setTextSubstitution", "(Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;)V", "textSubstitution", "()Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "layoutCache", "TextSubstitutionValue", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextStringSimpleNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private ParagraphLayoutCache _layoutCache;
    private Map<AlignmentLine, Integer> baselineCache;
    private FontFamily.Resolver fontFamilyResolver;
    private int maxLines;
    private int minLines;
    private int overflow;
    private ColorProducer overrideColor;
    private l<? super List<TextLayoutResult>, Boolean> semanticsTextLayoutResult;
    private boolean softWrap;
    private TextStyle style;
    private String text;

    /* JADX INFO: renamed from: textSubstitution$delegate, reason: from kotlin metadata */
    private final MutableState textSubstitution;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "textLayoutResult", "", "Landroidx/compose/ui/text/TextLayoutResult;", "invoke", "(Ljava/util/List;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<List<TextLayoutResult>, Boolean> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(List<TextLayoutResult> list) {
            ParagraphLayoutCache layoutCache = TextStringSimpleNode.this.getLayoutCache();
            TextStyle textStyle = TextStringSimpleNode.this.style;
            ColorProducer colorProducer = TextStringSimpleNode.this.overrideColor;
            TextLayoutResult textLayoutResultSlowCreateTextLayoutResultOrNull = layoutCache.slowCreateTextLayoutResultOrNull(textStyle.m5237mergedA7vx0o((16609104 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : colorProducer != null ? colorProducer.m3553invoke0d7_KjU() : Color.INSTANCE.m3508getUnspecified0d7_KjU(), (16609104 & 2) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : 0L, (16609104 & 4) != 0 ? null : null, (16609104 & 8) != 0 ? null : null, (16609104 & 16) != 0 ? null : null, (16609104 & 32) != 0 ? null : null, (16609104 & 64) != 0 ? null : null, (16609104 & 128) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : 0L, (16609104 & 256) != 0 ? null : null, (16609104 & 512) != 0 ? null : null, (16609104 & 1024) != 0 ? null : null, (16609104 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : 0L, (16609104 & 4096) != 0 ? null : null, (16609104 & 8192) != 0 ? null : null, (16609104 & 16384) != 0 ? null : null, (16609104 & 32768) != 0 ? TextAlign.INSTANCE.m5588getUnspecifiede0LSkKk() : 0, (16609104 & 65536) != 0 ? TextDirection.INSTANCE.m5601getUnspecifieds_7Xco() : 0, (16609104 & 131072) != 0 ? TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE() : 0L, (16609104 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : null, (16609104 & 524288) != 0 ? null : null, (16609104 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k() : 0, (16609104 & 2097152) != 0 ? Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8() : 0, (16609104 & 4194304) != 0 ? null : null, (16609104 & 8388608) != 0 ? null : null));
            if (textLayoutResultSlowCreateTextLayoutResultOrNull != null) {
                list.add(textLayoutResultSlowCreateTextLayoutResultOrNull);
            } else {
                textLayoutResultSlowCreateTextLayoutResultOrNull = null;
            }
            return Boolean.valueOf(textLayoutResultSlowCreateTextLayoutResultOrNull != null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "updatedText", "Landroidx/compose/ui/text/AnnotatedString;", "invoke", "(Landroidx/compose/ui/text/AnnotatedString;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<AnnotatedString, Boolean> {
        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(AnnotatedString annotatedString) {
            TextStringSimpleNode.this.setSubstitution(annotatedString.getText());
            SemanticsModifierNodeKt.invalidateSemantics(TextStringSimpleNode.this);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$3, reason: invalid class name */
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
            if (TextStringSimpleNode.this.getTextSubstitution() == null) {
                return Boolean.FALSE;
            }
            TextSubstitutionValue textSubstitution = TextStringSimpleNode.this.getTextSubstitution();
            if (textSubstitution != null) {
                textSubstitution.setShowingSubstitution(z);
            }
            SemanticsModifierNodeKt.invalidateSemantics(TextStringSimpleNode.this);
            LayoutModifierNodeKt.invalidateMeasurement(TextStringSimpleNode.this);
            DrawModifierNodeKt.invalidateDraw(TextStringSimpleNode.this);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements a<Boolean> {
        public AnonymousClass4() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextStringSimpleNode.this.clearSubstitution();
            SemanticsModifierNodeKt.invalidateSemantics(TextStringSimpleNode.this);
            LayoutModifierNodeKt.invalidateMeasurement(TextStringSimpleNode.this);
            DrawModifierNodeKt.invalidateDraw(TextStringSimpleNode.this);
            return Boolean.TRUE;
        }
    }

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i10, boolean z, int i11, int i12, ColorProducer colorProducer, h hVar) {
        this(str, textStyle, resolver, i10, z, i11, i12, colorProducer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearSubstitution() {
        setTextSubstitution(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new ParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        }
        return this._layoutCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final TextSubstitutionValue getTextSubstitution() {
        return (TextSubstitutionValue) this.textSubstitution.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setSubstitution(String updatedText) {
        t0 t0Var;
        TextSubstitutionValue textSubstitution = getTextSubstitution();
        if (textSubstitution == null) {
            TextSubstitutionValue textSubstitutionValue = new TextSubstitutionValue(this.text, updatedText, false, null, 12, null);
            ParagraphLayoutCache paragraphLayoutCache = new ParagraphLayoutCache(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
            paragraphLayoutCache.setDensity$foundation_release(getLayoutCache().getDensity());
            textSubstitutionValue.setLayoutCache(paragraphLayoutCache);
            setTextSubstitution(textSubstitutionValue);
            return true;
        }
        if (p.a(updatedText, textSubstitution.getSubstitution())) {
            return false;
        }
        textSubstitution.setSubstitution(updatedText);
        ParagraphLayoutCache layoutCache = textSubstitution.getLayoutCache();
        if (layoutCache != null) {
            layoutCache.m943updateL6sJoHM(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
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
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, new AnnotatedString(this.text, null, null, 6, null));
        TextSubstitutionValue textSubstitution = getTextSubstitution();
        if (textSubstitution != null) {
            SemanticsPropertiesKt.setShowingTextSubstitution(semanticsPropertyReceiver, textSubstitution.isShowingSubstitution());
            SemanticsPropertiesKt.setTextSubstitution(semanticsPropertyReceiver, new AnnotatedString(textSubstitution.getSubstitution(), null, null, 6, null));
        }
        SemanticsPropertiesKt.setTextSubstitution$default(semanticsPropertyReceiver, null, new AnonymousClass2(), 1, null);
        SemanticsPropertiesKt.showTextSubstitution$default(semanticsPropertyReceiver, null, new AnonymousClass3(), 1, null);
        SemanticsPropertiesKt.clearTextSubstitution$default(semanticsPropertyReceiver, null, new AnonymousClass4(), 1, null);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, null, anonymousClass1, 1, null);
    }

    public final void doInvalidations(boolean drawChanged, boolean textChanged, boolean layoutChanged) {
        if (getIsAttached()) {
            if (textChanged || (drawChanged && this.semanticsTextLayoutResult != null)) {
                SemanticsModifierNodeKt.invalidateSemantics(this);
            }
            if (textChanged || layoutChanged) {
                getLayoutCache().m943updateL6sJoHM(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
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
        if (getIsAttached()) {
            Paragraph paragraph = getLayoutCache().getParagraph();
            if (paragraph == null) {
                throw new IllegalArgumentException("no paragraph");
            }
            Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
            boolean didOverflow = getLayoutCache().getDidOverflow();
            if (didOverflow) {
                Rect rectM3276Recttz77jQw = RectKt.m3276Recttz77jQw(Offset.INSTANCE.m3252getZeroF1C5BW0(), SizeKt.Size(IntSize.m5844getWidthimpl(getLayoutCache().getLayoutSize()), IntSize.m5843getHeightimpl(getLayoutCache().getLayoutSize())));
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
                    androidx.compose.ui.text.a.d(paragraph, canvas, brush, this.style.getAlpha(), shadow2, textDecoration2, drawStyle2, 0, 64, null);
                } else {
                    ColorProducer colorProducer = this.overrideColor;
                    long jM3553invoke0d7_KjU = colorProducer != null ? colorProducer.m3553invoke0d7_KjU() : Color.INSTANCE.m3508getUnspecified0d7_KjU();
                    Color.Companion companion = Color.INSTANCE;
                    if (jM3553invoke0d7_KjU == companion.m3508getUnspecified0d7_KjU()) {
                        jM3553invoke0d7_KjU = this.style.m5222getColor0d7_KjU() != companion.m3508getUnspecified0d7_KjU() ? this.style.m5222getColor0d7_KjU() : companion.m3498getBlack0d7_KjU();
                    }
                    androidx.compose.ui.text.a.b(paragraph, canvas, jM3553invoke0d7_KjU, shadow2, textDecoration2, drawStyle2, 0, 32, null);
                }
                if (didOverflow) {
                    canvas.restore();
                }
            } catch (Throwable th) {
                if (didOverflow) {
                    canvas.restore();
                }
                throw th;
            }
        }
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

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).maxIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        ParagraphLayoutCache layoutCache = getLayoutCache(measureScope);
        boolean zM941layoutWithConstraintsK40F9xA = layoutCache.m941layoutWithConstraintsK40F9xA(j10, measureScope.getLayoutDirection());
        layoutCache.getObserveFontChanges$foundation_release();
        Paragraph paragraph = layoutCache.getParagraph();
        long layoutSize = layoutCache.getLayoutSize();
        if (zM941layoutWithConstraintsK40F9xA) {
            LayoutModifierNodeKt.invalidateLayer(this);
            Map<AlignmentLine, Integer> linkedHashMap = this.baselineCache;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>(2);
            }
            linkedHashMap.put(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(t7.a.M(paragraph.getFirstBaseline())));
            linkedHashMap.put(AlignmentLineKt.getLastBaseline(), Integer.valueOf(t7.a.M(paragraph.getLastBaseline())));
            this.baselineCache = linkedHashMap;
        }
        return measureScope.layout(IntSize.m5844getWidthimpl(layoutSize), IntSize.m5843getHeightimpl(layoutSize), this.baselineCache, new TextStringSimpleNode$measure$1(measurable.mo4631measureBRTryo0(LayoutUtilsKt.fixedCoerceHeightAndWidthForBits(Constraints.INSTANCE, IntSize.m5844getWidthimpl(layoutSize), IntSize.m5843getHeightimpl(layoutSize)))));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).intrinsicHeight(i10, intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return getLayoutCache(intrinsicMeasureScope).minIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }

    public final boolean updateDraw(ColorProducer color, TextStyle style) {
        boolean zA = p.a(color, this.overrideColor);
        this.overrideColor = color;
        return (zA && style.hasSameDrawAffectingAttributes(this.style)) ? false : true;
    }

    /* JADX INFO: renamed from: updateLayoutRelatedArgs-HuAbxIM, reason: not valid java name */
    public final boolean m954updateLayoutRelatedArgsHuAbxIM(TextStyle style, int minLines, int maxLines, boolean softWrap, FontFamily.Resolver fontFamilyResolver, int overflow) {
        boolean z = !this.style.hasSameLayoutAffectingAttributes(style);
        this.style = style;
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

    public final boolean updateText(String text) {
        if (p.a(this.text, text)) {
            return false;
        }
        this.text = text;
        clearSubstitution();
        return true;
    }

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i10, boolean z, int i11, int i12, ColorProducer colorProducer, int i13, h hVar) {
        this(str, textStyle, resolver, (i13 & 8) != 0 ? TextOverflow.INSTANCE.m5631getClipgIe3tQ8() : i10, (i13 & 16) != 0 ? true : z, (i13 & 32) != 0 ? Integer.MAX_VALUE : i11, (i13 & 64) != 0 ? 1 : i12, (i13 & 128) != 0 ? null : colorProducer, null);
    }

    private TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i10, boolean z, int i11, int i12, ColorProducer colorProducer) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i10;
        this.softWrap = z;
        this.maxLines = i11;
        this.minLines = i12;
        this.overrideColor = colorProducer;
        this.textSubstitution = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0012\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "", "original", "", "substitution", "isShowingSubstitution", "", "layoutCache", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;)V", "()Z", "setShowingSubstitution", "(Z)V", "getLayoutCache", "()Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "setLayoutCache", "(Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;)V", "getOriginal", "()Ljava/lang/String;", "getSubstitution", "setSubstitution", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class TextSubstitutionValue {
        public static final int $stable = 8;
        private boolean isShowingSubstitution;
        private ParagraphLayoutCache layoutCache;
        private final String original;
        private String substitution;

        public TextSubstitutionValue(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache) {
            this.original = str;
            this.substitution = str2;
            this.isShowingSubstitution = z;
            this.layoutCache = paragraphLayoutCache;
        }

        public static /* synthetic */ TextSubstitutionValue copy$default(TextSubstitutionValue textSubstitutionValue, String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = textSubstitutionValue.original;
            }
            if ((i10 & 2) != 0) {
                str2 = textSubstitutionValue.substitution;
            }
            if ((i10 & 4) != 0) {
                z = textSubstitutionValue.isShowingSubstitution;
            }
            if ((i10 & 8) != 0) {
                paragraphLayoutCache = textSubstitutionValue.layoutCache;
            }
            return textSubstitutionValue.copy(str, str2, z, paragraphLayoutCache);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOriginal() {
            return this.original;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSubstitution() {
            return this.substitution;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final TextSubstitutionValue copy(String original, String substitution, boolean isShowingSubstitution, ParagraphLayoutCache layoutCache) {
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

        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final String getOriginal() {
            return this.original;
        }

        public final String getSubstitution() {
            return this.substitution;
        }

        public int hashCode() {
            int iC = (c.c(this.original.hashCode() * 31, 31, this.substitution) + (this.isShowingSubstitution ? 1231 : 1237)) * 31;
            ParagraphLayoutCache paragraphLayoutCache = this.layoutCache;
            return iC + (paragraphLayoutCache == null ? 0 : paragraphLayoutCache.hashCode());
        }

        public final boolean isShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        public final void setLayoutCache(ParagraphLayoutCache paragraphLayoutCache) {
            this.layoutCache = paragraphLayoutCache;
        }

        public final void setShowingSubstitution(boolean z) {
            this.isShowingSubstitution = z;
        }

        public final void setSubstitution(String str) {
            this.substitution = str;
        }

        public String toString() {
            return "TextSubstitutionValue(original=" + this.original + ", substitution=" + this.substitution + ", isShowingSubstitution=" + this.isShowingSubstitution + ", layoutCache=" + this.layoutCache + ')';
        }

        public /* synthetic */ TextSubstitutionValue(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i10, h hVar) {
            this(str, str2, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? null : paragraphLayoutCache);
        }
    }

    private final ParagraphLayoutCache getLayoutCache(Density density) {
        ParagraphLayoutCache layoutCache;
        TextSubstitutionValue textSubstitution = getTextSubstitution();
        if (textSubstitution != null && textSubstitution.isShowingSubstitution() && (layoutCache = textSubstitution.getLayoutCache()) != null) {
            layoutCache.setDensity$foundation_release(density);
            return layoutCache;
        }
        ParagraphLayoutCache layoutCache2 = getLayoutCache();
        layoutCache2.setDensity$foundation_release(density);
        return layoutCache2;
    }
}
