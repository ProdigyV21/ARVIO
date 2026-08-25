package androidx.compose.material3;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.tokens.CircularProgressIndicatorTokens;
import androidx.compose.material3.tokens.LinearProgressIndicatorTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\u001aH\u0010\r\u001a\u00020\n2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a:\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001aB\u0010\r\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0010\u001a8\u0010\r\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a0\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a>\u0010\u001b\u001a\u00020\n*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a.\u0010\u001e\u001a\u00020\n*\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001aR\u0010\"\u001a\u00020\n2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u001f2\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001aD\u0010\"\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u001f2\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001aL\u0010\"\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u001f2\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010%\u001a8\u0010\"\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a0\u0010\"\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a6\u00100\u001a\u00020\n*\u00020\u00152\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010-\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a&\u00103\u001a\u00020\n*\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010-\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a6\u00105\u001a\u00020\n*\u00020\u00152\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010-\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0004\b4\u0010/\u001a>\u00108\u001a\u00020\n*\u00020\u00152\u0006\u0010*\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010-\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0004\b6\u00107\"\u0014\u00109\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:\"\u0014\u0010;\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<\"\u001a\u0010=\u001a\u00020\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b=\u0010:\u001a\u0004\b>\u0010?\"\u001a\u0010@\u001a\u00020\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b@\u0010:\u001a\u0004\bA\u0010?\"\u001a\u0010B\u001a\u00020\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bB\u0010:\u001a\u0004\bC\u0010?\"\u0014\u0010E\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bE\u0010F\"\u0014\u0010G\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bG\u0010F\"\u0014\u0010H\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bH\u0010F\"\u0014\u0010I\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bI\u0010F\"\u0014\u0010J\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bJ\u0010F\"\u0014\u0010K\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bK\u0010F\"\u0014\u0010L\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bL\u0010F\"\u0014\u0010M\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bM\u0010F\"\u0014\u0010N\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bN\u0010F\"\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010Q\"\u0014\u0010R\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010Q\"\u0014\u0010S\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010Q\"\u0014\u0010T\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010Q\"\u0014\u0010U\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bU\u0010F\"\u0014\u0010V\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\bV\u0010F\"\u0014\u0010W\u001a\u00020\u00018\u0002X\u0082T¢\u0006\u0006\n\u0004\bW\u0010:\"\u0014\u0010X\u001a\u00020\u00018\u0002X\u0082T¢\u0006\u0006\n\u0004\bX\u0010:\"\u0014\u0010Y\u001a\u00020\u00018\u0002X\u0082T¢\u0006\u0006\n\u0004\bY\u0010:\"\u0014\u0010Z\u001a\u00020\u00018\u0002X\u0082T¢\u0006\u0006\n\u0004\bZ\u0010:\"\u0014\u0010[\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\b[\u0010F\"\u0014\u0010\\\u001a\u00020D8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\\\u0010F\"\u0014\u0010]\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010Q\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006^"}, d2 = {"Lkotlin/Function0;", "", "progress", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "trackColor", "Landroidx/compose/ui/graphics/StrokeCap;", "strokeCap", "Lx6/t0;", "LinearProgressIndicator-_5eSR-E", "(Lr7/a;Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startFraction", "endFraction", "strokeWidth", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "drawLinearIndicator", "drawLinearIndicatorTrack-AZGd3zU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFI)V", "drawLinearIndicatorTrack", "Landroidx/compose/ui/unit/Dp;", "CircularProgressIndicator-DUhRLBM", "(Lr7/a;Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "startAngle", "sweep", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "stroke", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicator", "drawCircularIndicatorTrack-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorTrack", "drawDeterminateCircularIndicator-42QJj7c", "drawDeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawIndeterminateCircularIndicator", "SemanticsBoundsPadding", "F", "IncreaseSemanticsBounds", "Landroidx/compose/ui/Modifier;", "LinearIndicatorWidth", "getLinearIndicatorWidth", "()F", "LinearIndicatorHeight", "getLinearIndicatorHeight", "CircularIndicatorDiameter", "getCircularIndicatorDiameter", "", "LinearAnimationDuration", "I", "FirstLineHeadDuration", "FirstLineTailDuration", "SecondLineHeadDuration", "SecondLineTailDuration", "FirstLineHeadDelay", "FirstLineTailDelay", "SecondLineHeadDelay", "SecondLineTailDelay", "Landroidx/compose/animation/core/CubicBezierEasing;", "FirstLineHeadEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "FirstLineTailEasing", "SecondLineHeadEasing", "SecondLineTailEasing", "RotationsPerCycle", "RotationDuration", "StartAngleOffset", "BaseRotationAngle", "JumpRotationAngle", "RotationAngleOffset", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "CircularEasing", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final CubicBezierEasing CircularEasing;
    private static final float CircularIndicatorDiameter;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final CubicBezierEasing FirstLineHeadEasing;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final CubicBezierEasing FirstLineTailEasing;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final Modifier IncreaseSemanticsBounds;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float LinearIndicatorHeight;
    private static final float LinearIndicatorWidth;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final CubicBezierEasing SecondLineHeadEasing;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final CubicBezierEasing SecondLineTailEasing;
    private static final float SemanticsBoundsPadding;
    private static final float StartAngleOffset = -90.0f;

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl(10);
        SemanticsBoundsPadding = fM5678constructorimpl;
        IncreaseSemanticsBounds = PaddingKt.m531paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(Modifier.INSTANCE, ProgressIndicatorKt$IncreaseSemanticsBounds$1.INSTANCE), true, ProgressIndicatorKt$IncreaseSemanticsBounds$2.INSTANCE), 0.0f, fM5678constructorimpl, 1, null);
        LinearIndicatorWidth = Dp.m5678constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
        LinearIndicatorHeight = LinearProgressIndicatorTokens.INSTANCE.m2636getTrackHeightD9Ej5fM();
        CircularProgressIndicatorTokens circularProgressIndicatorTokens = CircularProgressIndicatorTokens.INSTANCE;
        CircularIndicatorDiameter = Dp.m5678constructorimpl(circularProgressIndicatorTokens.m2370getSizeD9Ej5fM() - Dp.m5678constructorimpl(circularProgressIndicatorTokens.m2369getActiveIndicatorWidthD9Ej5fM() * 2));
        FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
        FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
        SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
        SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
        CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011f  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1761CircularProgressIndicatorDUhRLBM(r7.a<java.lang.Float> r25, androidx.compose.ui.Modifier r26, long r27, float r29, long r30, int r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1761CircularProgressIndicatorDUhRLBM(r7.a, androidx.compose.ui.Modifier, long, float, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0262  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1762CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier r32, long r33, float r35, long r36, int r38, androidx.compose.runtime.Composer r39, int r40, int r41) {
        /*
            Method dump skipped, instruction units count: 769
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1762CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier, long, float, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    @x6.e
    /* JADX INFO: renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m1763CircularProgressIndicatorMBs18nI(float r20, androidx.compose.ui.Modifier r21, long r22, float r24, androidx.compose.runtime.Composer r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1763CircularProgressIndicatorMBs18nI(float, androidx.compose.ui.Modifier, long, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    @x6.e
    /* JADX INFO: renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m1764CircularProgressIndicatoraMcp0Q(androidx.compose.ui.Modifier r16, long r17, float r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1764CircularProgressIndicatoraMcp0Q(androidx.compose.ui.Modifier, long, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01a4 A[PHI: r10
      0x01a4: PHI (r10v19 long) = (r10v17 long), (r10v20 long) binds: [B:84:0x01a2, B:80:0x019b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01cb  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1765LinearProgressIndicator2cYBFYY(androidx.compose.ui.Modifier r34, long r35, long r37, int r39, androidx.compose.runtime.Composer r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1765LinearProgressIndicator2cYBFYY(androidx.compose.ui.Modifier, long, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    @x6.e
    /* JADX INFO: renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m1766LinearProgressIndicatorRIQooxk(Modifier modifier, long j10, long j11, Composer composer, int i10, int i11) {
        Modifier modifier2;
        int i12;
        long linearColor;
        long linearTrackColor;
        long j12;
        Modifier modifier3;
        Modifier modifier4;
        long j13;
        long j14;
        Composer composerStartRestartGroup = composer.startRestartGroup(585576195);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
            modifier2 = modifier;
        } else if ((i10 & 6) == 0) {
            modifier2 = modifier;
            i12 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i10;
        } else {
            modifier2 = modifier;
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            if ((i11 & 2) == 0) {
                linearColor = j10;
                int i14 = composerStartRestartGroup.changed(linearColor) ? 32 : 16;
                i12 |= i14;
            } else {
                linearColor = j10;
            }
            i12 |= i14;
        } else {
            linearColor = j10;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if ((i11 & 4) == 0) {
                linearTrackColor = j11;
                int i15 = composerStartRestartGroup.changed(linearTrackColor) ? 256 : 128;
                i12 |= i15;
            } else {
                linearTrackColor = j11;
            }
            i12 |= i15;
        } else {
            linearTrackColor = j11;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier4 = modifier2;
            j13 = linearColor;
            j14 = linearTrackColor;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i10 & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                Modifier modifier5 = i13 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i11 & 2) != 0) {
                    linearColor = ProgressIndicatorDefaults.INSTANCE.getLinearColor(composerStartRestartGroup, 6);
                    i12 &= -113;
                }
                if ((i11 & 4) != 0) {
                    linearTrackColor = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor(composerStartRestartGroup, 6);
                    i12 &= -897;
                }
                j12 = linearTrackColor;
                modifier3 = modifier5;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i11 & 2) != 0) {
                    i12 &= -113;
                }
                if ((i11 & 4) != 0) {
                    i12 &= -897;
                }
                j12 = linearTrackColor;
                modifier3 = modifier2;
            }
            long j15 = linearColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585576195, i12, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:246)");
            }
            m1765LinearProgressIndicator2cYBFYY(modifier3, j15, j12, ProgressIndicatorDefaults.INSTANCE.m1759getLinearStrokeCapKaPHkGw(), composerStartRestartGroup, (i12 & 14) | 3072 | (i12 & 112) | (i12 & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            j13 = j15;
            j14 = j12;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ProgressIndicatorKt$LinearProgressIndicator$9(modifier4, j13, j14, i10, i11));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0122  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1768LinearProgressIndicator_5eSRE(r7.a<java.lang.Float> r19, androidx.compose.ui.Modifier r20, long r21, long r23, int r25, androidx.compose.runtime.Composer r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 487
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1768LinearProgressIndicator_5eSRE(r7.a, androidx.compose.ui.Modifier, long, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    @x6.e
    /* JADX INFO: renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m1769LinearProgressIndicatoreaDK9VM(float r18, androidx.compose.ui.Modifier r19, long r20, long r22, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1769LinearProgressIndicatoreaDK9VM(float, androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m1775drawCircularIndicator42QJj7c(DrawScope drawScope, float f10, float f11, long j10, Stroke stroke) {
        float f12 = 2;
        float width = stroke.getWidth() / f12;
        float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) - (f12 * width);
        androidx.compose.ui.graphics.drawscope.c.v(drawScope, j10, f10, f11, false, OffsetKt.Offset(width, width), SizeKt.Size(fM3305getWidthimpl, fM3305getWidthimpl), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    public static final void m1776drawCircularIndicatorTrackbw27NRU(DrawScope drawScope, long j10, Stroke stroke) {
        m1775drawCircularIndicator42QJj7c(drawScope, 0.0f, 360.0f, j10, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    public static final void m1777drawDeterminateCircularIndicator42QJj7c(DrawScope drawScope, float f10, float f11, long j10, Stroke stroke) {
        m1775drawCircularIndicator42QJj7c(drawScope, f10, f11, j10, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    public static final void m1778drawIndeterminateCircularIndicatorhrjfTZI(DrawScope drawScope, float f10, float f11, float f12, long j10, Stroke stroke) {
        m1775drawCircularIndicator42QJj7c(drawScope, f10 + (StrokeCap.m3799equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m3803getButtKaPHkGw()) ? 0.0f : ((f11 / Dp.m5678constructorimpl(CircularIndicatorDiameter / 2)) * 57.29578f) / 2.0f), Math.max(f12, 0.1f), j10, stroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    public static final void m1779drawLinearIndicatorqYKTg0g(DrawScope drawScope, float f10, float f11, long j10, float f12, int i10) {
        float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc());
        float fM3302getHeightimpl = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc());
        float f13 = 2;
        float f14 = fM3302getHeightimpl / f13;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Ltr;
        float f15 = (z ? f10 : 1.0f - f11) * fM3305getWidthimpl;
        float f16 = (z ? f11 : 1.0f - f10) * fM3305getWidthimpl;
        if (StrokeCap.m3799equalsimpl0(i10, StrokeCap.INSTANCE.m3803getButtKaPHkGw()) || fM3302getHeightimpl > fM3305getWidthimpl) {
            androidx.compose.ui.graphics.drawscope.c.C(drawScope, j10, OffsetKt.Offset(f15, f14), OffsetKt.Offset(f16, f14), f12, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float f17 = f12 / f13;
        x7.d dVar = new x7.d(f17, fM3305getWidthimpl - f17);
        float fFloatValue = ((Number) qb.d.q(Float.valueOf(f15), dVar)).floatValue();
        float fFloatValue2 = ((Number) qb.d.q(Float.valueOf(f16), dVar)).floatValue();
        if (Math.abs(f11 - f10) > 0.0f) {
            androidx.compose.ui.graphics.drawscope.c.C(drawScope, j10, OffsetKt.Offset(fFloatValue, f14), OffsetKt.Offset(fFloatValue2, f14), f12, i10, null, 0.0f, null, 0, 480, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawLinearIndicatorTrack-AZGd3zU, reason: not valid java name */
    public static final void m1780drawLinearIndicatorTrackAZGd3zU(DrawScope drawScope, long j10, float f10, int i10) {
        m1779drawLinearIndicatorqYKTg0g(drawScope, 0.0f, 1.0f, j10, f10, i10);
    }

    public static final float getCircularIndicatorDiameter() {
        return CircularIndicatorDiameter;
    }

    public static final float getLinearIndicatorHeight() {
        return LinearIndicatorHeight;
    }

    public static final float getLinearIndicatorWidth() {
        return LinearIndicatorWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0139  */
    @x6.e
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1767LinearProgressIndicator_5eSRE(float r19, androidx.compose.ui.Modifier r20, long r21, long r23, int r25, androidx.compose.runtime.Composer r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1767LinearProgressIndicator_5eSRE(float, androidx.compose.ui.Modifier, long, long, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011c  */
    @x6.e
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1760CircularProgressIndicatorDUhRLBM(float r21, androidx.compose.ui.Modifier r22, long r23, float r25, long r26, int r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m1760CircularProgressIndicatorDUhRLBM(float, androidx.compose.ui.Modifier, long, float, long, int, androidx.compose.runtime.Composer, int, int):void");
    }
}
