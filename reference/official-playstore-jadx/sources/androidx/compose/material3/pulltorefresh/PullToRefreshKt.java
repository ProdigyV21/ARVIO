package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.c;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import qb.d;
import r7.a;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a]\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a,\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a1\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a(\u0010!\u001a\u00020\u00052\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\u00122\u0006\u0010\u001e\u001a\u00020\nH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a>\u0010+\u001a\u00020\u0005*\u00020\"2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u00182\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u0017\u0010,\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u0018H\u0002¢\u0006\u0004\b,\u0010-\u001aF\u00103\u001a\u00020\u0005*\u00020\"2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u00182\u0006\u0010%\u001a\u00020$2\u0006\u0010(\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102\"\u0014\u00104\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b4\u00105\"\u0014\u00107\u001a\u0002068\u0002X\u0082T¢\u0006\u0006\n\u0004\b7\u00108\"\u0014\u00109\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00105\"\u0014\u0010:\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00105\"\u001a\u0010;\u001a\u00020\u00108\u0000X\u0080\u0004¢\u0006\f\n\u0004\b;\u00105\u001a\u0004\b<\u0010=\"\u001a\u0010>\u001a\u00020\u00108\u0000X\u0080\u0004¢\u0006\f\n\u0004\b>\u00105\u001a\u0004\b?\u0010=\"\u0014\u0010@\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u00105\"\u0014\u0010A\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u00105\"\u0014\u0010B\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u00105\"\u0014\u0010C\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\bC\u00105\"\u0014\u0010D\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\bD\u00105\"\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00180E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010G\"\u0014\u0010H\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\bH\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006J²\u0006\f\u0010I\u001a\u00020\u00188\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function1;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "indicator", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "PullToRefreshContainer-wBJOh4Y", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/ui/Modifier;Lr7/q;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/runtime/Composer;II)V", "PullToRefreshContainer", "Landroidx/compose/ui/unit/Dp;", "positionalThreshold", "Lkotlin/Function0;", "", "enabled", "rememberPullToRefreshState--orJrPs", "(FLr7/a;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "rememberPullToRefreshState", "", "positionalThresholdPx", "initialRefreshing", "PullToRefreshState", "(FZLr7/a;)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "progress", TtmlNode.ATTR_TTS_COLOR, "CircularArrowProgressIndicator-RPmYEkk", "(Lr7/a;JLandroidx/compose/runtime/Composer;I)V", "CircularArrowProgressIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "alpha", "Landroidx/compose/material3/pulltorefresh/ArrowValues;", "values", "Landroidx/compose/ui/geometry/Rect;", "arcBounds", "strokeWidth", "drawCircularIndicator-KzyDr3Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;Landroidx/compose/ui/geometry/Rect;F)V", "drawCircularIndicator", "ArrowValues", "(F)Landroidx/compose/material3/pulltorefresh/ArrowValues;", "Landroidx/compose/ui/graphics/Path;", "arrow", "bounds", "drawArrow-uDrxG_w", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;F)V", "drawArrow", "MaxProgressArc", "F", "", "CrossfadeDurationMs", "I", "StrokeWidth", "ArcRadius", "SpinnerSize", "getSpinnerSize", "()F", "SpinnerContainerSize", "getSpinnerContainerSize", "Elevation", "ArrowWidth", "ArrowHeight", "MinAlpha", "MaxAlpha", "Landroidx/compose/animation/core/TweenSpec;", "AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "DragMultiplier", "targetAlpha", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float DragMultiplier = 0.5f;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float StrokeWidth = Dp.m5678constructorimpl((float) 2.5d);
    private static final float ArcRadius = Dp.m5678constructorimpl((float) 5.5d);
    private static final float SpinnerSize = Dp.m5678constructorimpl(16);
    private static final float SpinnerContainerSize = Dp.m5678constructorimpl(40);
    private static final float Elevation = ElevationTokens.INSTANCE.m2508getLevel2D9Ej5fM();
    private static final float ArrowWidth = Dp.m5678constructorimpl(10);
    private static final float ArrowHeight = Dp.m5678constructorimpl(5);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(300, 0, EasingKt.getLinearEasing(), 2, null);

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshKt$PullToRefreshState$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrowValues ArrowValues(float f10) {
        float fMax = (Math.max(Math.min(1.0f, f10) - 0.4f, 0.0f) * 5) / 3;
        float fM = d.m(Math.abs(f10) - 1.0f, 0.0f, 2.0f);
        float fPow = (((0.4f * fMax) - 0.25f) + (fM - (((float) Math.pow(fM, 2)) / 4))) * 0.5f;
        float f11 = 360;
        return new ArrowValues(fPow, fPow * f11, ((0.8f * fMax) + fPow) * f11, Math.min(1.0f, fMax));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularArrowProgressIndicator-RPmYEkk, reason: not valid java name */
    public static final void m2326CircularArrowProgressIndicatorRPmYEkk(a<Float> aVar, long j10, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-569718810);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(aVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-569718810, i11, -1, "androidx.compose.material3.pulltorefresh.CircularArrowProgressIndicator (PullToRefresh.kt:427)");
            }
            composerStartRestartGroup.startReplaceableGroup(-656076138);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            Object obj = objRememberedValue;
            if (objRememberedValue == companion.getEmpty()) {
                Path Path = AndroidPath_androidKt.Path();
                Path.mo3371setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m3733getEvenOddRgk1Os());
                composerStartRestartGroup.updateRememberedValue(Path);
                obj = Path;
            }
            Path path = (Path) obj;
            Object objL = c.l(composerStartRestartGroup, -656075976);
            if (objL == companion.getEmpty()) {
                objL = SnapshotStateKt.derivedStateOf(new PullToRefreshKt$CircularArrowProgressIndicator$targetAlpha$2$1(aVar));
                composerStartRestartGroup.updateRememberedValue(objL);
            }
            composerStartRestartGroup.endReplaceableGroup();
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowProgressIndicator_RPmYEkk$lambda$6((State) objL), AlphaTween, 0.0f, null, null, composerStartRestartGroup, 48, 28);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-656075714);
            int i12 = i11 & 14;
            boolean z = i12 == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new PullToRefreshKt$CircularArrowProgressIndicator$1$1(aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(SemanticsModifierKt.semantics(companion2, true, (l) objRememberedValue2), SpinnerSize);
            composerStartRestartGroup.startReplaceableGroup(-656075558);
            boolean zChanged = (i12 == 4) | composerStartRestartGroup.changed(stateAnimateFloatAsState) | ((i11 & 112) == 32) | composerStartRestartGroup.changedInstance(path);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                PullToRefreshKt$CircularArrowProgressIndicator$2$1 pullToRefreshKt$CircularArrowProgressIndicator$2$1 = new PullToRefreshKt$CircularArrowProgressIndicator$2$1(aVar, stateAnimateFloatAsState, j10, path);
                composerStartRestartGroup.updateRememberedValue(pullToRefreshKt$CircularArrowProgressIndicator$2$1);
                objRememberedValue3 = pullToRefreshKt$CircularArrowProgressIndicator$2$1;
            }
            composerStartRestartGroup.endReplaceableGroup();
            CanvasKt.Canvas(modifierM578size3ABfNKs, (l) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new PullToRefreshKt$CircularArrowProgressIndicator$3(aVar, j10, i10));
        }
    }

    private static final float CircularArrowProgressIndicator_RPmYEkk$lambda$6(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x010f  */
    /* JADX INFO: renamed from: PullToRefreshContainer-wBJOh4Y, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2327PullToRefreshContainerwBJOh4Y(androidx.compose.material3.pulltorefresh.PullToRefreshState r17, androidx.compose.ui.Modifier r18, r7.q<? super androidx.compose.material3.pulltorefresh.PullToRefreshState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r19, androidx.compose.ui.graphics.Shape r20, long r21, long r23, androidx.compose.runtime.Composer r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 411
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshKt.m2327PullToRefreshContainerwBJOh4Y(androidx.compose.material3.pulltorefresh.PullToRefreshState, androidx.compose.ui.Modifier, r7.q, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final PullToRefreshState PullToRefreshState(float f10, boolean z, a<Boolean> aVar) {
        return new PullToRefreshStateImpl(z, f10, aVar);
    }

    public static /* synthetic */ PullToRefreshState PullToRefreshState$default(float f10, boolean z, a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            aVar = AnonymousClass1.INSTANCE;
        }
        return PullToRefreshState(f10, z, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawArrow-uDrxG_w, reason: not valid java name */
    public static final void m2331drawArrowuDrxG_w(DrawScope drawScope, Path path, Rect rect, long j10, float f10, ArrowValues arrowValues, float f11) {
        path.reset();
        path.moveTo(0.0f, 0.0f);
        float f12 = ArrowWidth;
        path.lineTo((drawScope.mo285toPx0680j_4(f12) * arrowValues.getScale()) / 2, drawScope.mo285toPx0680j_4(ArrowHeight) * arrowValues.getScale());
        path.lineTo(drawScope.mo285toPx0680j_4(f12) * arrowValues.getScale(), 0.0f);
        path.mo3373translatek4lQ0M(OffsetKt.Offset((Offset.m3236getXimpl(rect.m3266getCenterF1C5BW0()) + (Math.min(rect.getWidth(), rect.getHeight()) / 2.0f)) - ((drawScope.mo285toPx0680j_4(f12) * arrowValues.getScale()) / 2.0f), Offset.m3237getYimpl(rect.m3266getCenterF1C5BW0()) - drawScope.mo285toPx0680j_4(f11)));
        float endAngle = arrowValues.getEndAngle() - drawScope.mo285toPx0680j_4(f11);
        long jMo3915getCenterF1C5BW0 = drawScope.mo3915getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3928rotateUv8p0NA(endAngle, jMo3915getCenterF1C5BW0);
        androidx.compose.ui.graphics.drawscope.c.G(drawScope, path, j10, f10, new Stroke(drawScope.mo285toPx0680j_4(f11), 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCircularIndicator-KzyDr3Q, reason: not valid java name */
    public static final void m2332drawCircularIndicatorKzyDr3Q(DrawScope drawScope, long j10, float f10, ArrowValues arrowValues, Rect rect, float f11) {
        androidx.compose.ui.graphics.drawscope.c.v(drawScope, j10, arrowValues.getStartAngle(), arrowValues.getEndAngle() - arrowValues.getStartAngle(), false, rect.m3271getTopLeftF1C5BW0(), rect.m3269getSizeNHjbRc(), f10, new Stroke(drawScope.mo285toPx0680j_4(f11), 0.0f, StrokeCap.INSTANCE.m3803getButtKaPHkGw(), 0, null, 26, null), null, 0, 768, null);
    }

    public static final float getSpinnerContainerSize() {
        return SpinnerContainerSize;
    }

    public static final float getSpinnerSize() {
        return SpinnerSize;
    }

    /* JADX INFO: renamed from: rememberPullToRefreshState--orJrPs, reason: not valid java name */
    public static final PullToRefreshState m2333rememberPullToRefreshStateorJrPs(float f10, a<Boolean> aVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1935213334);
        if ((i11 & 1) != 0) {
            f10 = PullToRefreshDefaults.INSTANCE.m2325getPositionalThresholdD9Ej5fM();
        }
        if ((i11 & 2) != 0) {
            aVar = PullToRefreshKt$rememberPullToRefreshState$1.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1935213334, i10, -1, "androidx.compose.material3.pulltorefresh.rememberPullToRefreshState (PullToRefresh.kt:252)");
        }
        float fMo285toPx0680j_4 = ((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo285toPx0680j_4(f10);
        Object[] objArr = {Float.valueOf(fMo285toPx0680j_4), aVar};
        Saver<PullToRefreshState, Boolean> Saver = PullToRefreshStateImpl.INSTANCE.Saver(fMo285toPx0680j_4, aVar);
        composer.startReplaceableGroup(804873447);
        boolean zChanged = composer.changed(fMo285toPx0680j_4) | ((((i10 & 112) ^ 48) > 32 && composer.changed(aVar)) || (i10 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PullToRefreshKt$rememberPullToRefreshState$2$1(fMo285toPx0680j_4, aVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        PullToRefreshState pullToRefreshState = (PullToRefreshState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return pullToRefreshState;
    }
}
