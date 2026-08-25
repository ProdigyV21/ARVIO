package androidx.compose.material3;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.k;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\u001a\u008a\u0002\u0010!\u001a\u00020\u00022\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0014¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00162\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0014¢\u0006\u0002\b\u00032\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a#\u0010%\u001a\u00020\b2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u0019H\u0007¢\u0006\u0004\b%\u0010&\u001a9\u0010+\u001a\u00020\"2\b\b\u0002\u0010(\u001a\u00020'2\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00160\u00002\b\b\u0002\u0010*\u001a\u00020\u0016H\u0007¢\u0006\u0004\b+\u0010,\u001a¶\u0001\u0010;\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\"2'\u00103\u001a#\u0012\u0013\u0012\u00110.¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\n\u0012\b\u0012\u0004\u0012\u00020'020\u00002\u0006\u00104\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00105\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u00020\n2\u0013\u00108\u001a\u000f\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0014¢\u0006\u0002\b\u00032\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u0004H\u0003ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001aÀ\u0001\u0010F\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0014¢\u0006\u0002\b\u00032&\u0010=\u001a\"\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u00032&\u0010@\u001a\"\u0012\u0013\u0012\u00110>¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u00032\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00020\u0014¢\u0006\u0002\b\u00032\u0006\u0010\u000b\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020A0\u00142\u0006\u0010C\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0003ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "sheetContent", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/BottomSheetScaffoldState;", "scaffoldState", "Landroidx/compose/ui/unit/Dp;", "sheetPeekHeight", "sheetMaxWidth", "Landroidx/compose/ui/graphics/Shape;", "sheetShape", "Landroidx/compose/ui/graphics/Color;", "sheetContainerColor", "sheetContentColor", "sheetTonalElevation", "sheetShadowElevation", "Lkotlin/Function0;", "sheetDragHandle", "", "sheetSwipeEnabled", "topBar", "Landroidx/compose/material3/SnackbarHostState;", "snackbarHost", "containerColor", "contentColor", "Landroidx/compose/foundation/layout/PaddingValues;", "content", "BottomSheetScaffold-sdMYb0k", "(Lr7/q;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/BottomSheetScaffoldState;FFLandroidx/compose/ui/graphics/Shape;JJFFLr7/p;ZLr7/p;Lr7/q;JJLr7/q;Landroidx/compose/runtime/Composer;III)V", "BottomSheetScaffold", "Landroidx/compose/material3/SheetState;", "bottomSheetState", "snackbarHostState", "rememberBottomSheetScaffoldState", "(Landroidx/compose/material3/SheetState;Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomSheetScaffoldState;", "Landroidx/compose/material3/SheetValue;", "initialValue", "confirmValueChange", "skipHiddenState", "rememberStandardBottomSheetState", "(Landroidx/compose/material3/SheetValue;Lr7/l;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "state", "Landroidx/compose/ui/unit/IntSize;", "Lx6/y;", ContentDisposition.Parameters.Name, "sheetSize", "Landroidx/compose/material3/DraggableAnchors;", "calculateAnchors", "peekHeight", "shape", "tonalElevation", "shadowElevation", "dragHandle", "StandardBottomSheet-XcniZvE", "(Landroidx/compose/material3/SheetState;Lr7/l;FFZLandroidx/compose/ui/graphics/Shape;JJFFLr7/p;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "StandardBottomSheet", "innerPadding", TtmlNode.TAG_BODY, "", "layoutHeight", "bottomSheet", "", "sheetOffset", "sheetState", "BottomSheetScaffoldLayout-PxNyym8", "(Landroidx/compose/ui/Modifier;Lr7/p;Lr7/q;Lr7/q;Lr7/p;FLr7/a;Landroidx/compose/material3/SheetState;JJLandroidx/compose/runtime/Composer;I)V", "BottomSheetScaffoldLayout", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetScaffoldKt {

    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$rememberStandardBottomSheetState$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<SheetValue, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SheetValue sheetValue) {
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:288:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0121  */
    /* JADX INFO: renamed from: BottomSheetScaffold-sdMYb0k, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1249BottomSheetScaffoldsdMYb0k(r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r34, androidx.compose.ui.Modifier r35, androidx.compose.material3.BottomSheetScaffoldState r36, float r37, float r38, androidx.compose.ui.graphics.Shape r39, long r40, long r42, float r44, float r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, boolean r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.q<? super androidx.compose.material3.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, long r50, long r52, r7.q<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r54, androidx.compose.runtime.Composer r55, int r56, int r57, int r58) {
        /*
            Method dump skipped, instruction units count: 1173
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BottomSheetScaffoldKt.m1249BottomSheetScaffoldsdMYb0k(r7.q, androidx.compose.ui.Modifier, androidx.compose.material3.BottomSheetScaffoldState, float, float, androidx.compose.ui.graphics.Shape, long, long, float, float, r7.p, boolean, r7.p, r7.q, long, long, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: BottomSheetScaffoldLayout-PxNyym8, reason: not valid java name */
    public static final void m1250BottomSheetScaffoldLayoutPxNyym8(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, q<? super Integer, ? super Composer, ? super Integer, t0> qVar2, p<? super Composer, ? super Integer, t0> pVar2, float f10, r7.a<Float> aVar, SheetState sheetState, long j10, long j11, Composer composer, int i10) {
        int i11;
        p<? super Composer, ? super Integer, t0> pVar3;
        float f11;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1120561936);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(qVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(qVar2) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            pVar3 = pVar2;
            i11 |= composerStartRestartGroup.changedInstance(pVar3) ? 16384 : 8192;
        } else {
            pVar3 = pVar2;
        }
        if ((196608 & i10) == 0) {
            f11 = f10;
            i11 |= composerStartRestartGroup.changed(f11) ? 131072 : 65536;
        } else {
            f11 = f10;
        }
        if ((i10 & 1572864) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i11 |= composerStartRestartGroup.changed(sheetState) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i11 |= composerStartRestartGroup.changed(j11) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 306783379) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1120561936, i11, -1, "androidx.compose.material3.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:332)");
            }
            Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            composerStartRestartGroup.startReplaceableGroup(-99158096);
            int i13 = 29360128 & i11;
            boolean zChanged = (i13 == 8388608) | composerStartRestartGroup.changed(density);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(sheetState, density);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.SideEffect((r7.a) objRememberedValue, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-99158030);
            boolean z = ((i11 & 7168) == 2048) | ((i11 & 112) == 32) | ((i11 & 14) == 4) | ((234881024 & i11) == 67108864) | ((1879048192 & i11) == 536870912) | ((i11 & 896) == 256) | ((458752 & i11) == 131072) | ((57344 & i11) == 16384) | ((i11 & 3670016) == 1048576) | (i13 == 8388608);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                i12 = 1;
                BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1 bottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1 = new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1(pVar, pVar3, qVar2, modifier, j10, j11, qVar, f11, aVar, sheetState);
                composerStartRestartGroup.updateRememberedValue(bottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1);
                objRememberedValue2 = bottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1;
            } else {
                i12 = 1;
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (p) objRememberedValue2, composerStartRestartGroup, 0, i12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$3(modifier, pVar, qVar, qVar2, pVar2, f10, aVar, sheetState, j10, j11, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: StandardBottomSheet-XcniZvE, reason: not valid java name */
    public static final void m1251StandardBottomSheetXcniZvE(SheetState sheetState, l<? super IntSize, ? extends DraggableAnchors<SheetValue>> lVar, float f10, float f11, boolean z, Shape shape, long j10, long j11, float f12, float f13, p<? super Composer, ? super Integer, t0> pVar, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i10, int i11) {
        int i12;
        boolean z5;
        Shape shape2;
        long j12;
        int i13;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(424459667);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(sheetState) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(f11) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            z5 = z;
            i12 |= composerStartRestartGroup.changed(z5) ? 16384 : 8192;
        } else {
            z5 = z;
        }
        if ((196608 & i10) == 0) {
            shape2 = shape;
            i12 |= composerStartRestartGroup.changed(shape2) ? 131072 : 65536;
        } else {
            shape2 = shape;
        }
        if ((1572864 & i10) == 0) {
            j12 = j10;
            i12 |= composerStartRestartGroup.changed(j12) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            j12 = j10;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= composerStartRestartGroup.changed(j11) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= composerStartRestartGroup.changed(f12) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(f13) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(qVar) ? 32 : 16;
        }
        int i14 = i13;
        if ((i12 & 306783379) == 306783378 && (i14 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(424459667, i12, i14, "androidx.compose.material3.StandardBottomSheet (BottomSheetScaffold.kt:231)");
            }
            Object objG = a0.c.g(773894976, composerStartRestartGroup, -492369756);
            Composer.Companion companion = Composer.INSTANCE;
            if (objG == companion.getEmpty()) {
                objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            Orientation orientation = Orientation.Vertical;
            int i15 = i12;
            Modifier modifierM569requiredHeightInVpY3zN4$default = SizeKt.m569requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(SizeKt.m585widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, f11, 1, null), 0.0f, 1, null), f10, 0.0f, 2, null);
            AnchoredDraggableState<SheetValue> anchoredDraggableState$material3_release = sheetState.getAnchoredDraggableState$material3_release();
            composerStartRestartGroup.startReplaceableGroup(1603483798);
            boolean zChanged = composerStartRestartGroup.changed(anchoredDraggableState$material3_release);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState, orientation, new BottomSheetScaffoldKt$StandardBottomSheet$1$1(coroutineScope, sheetState));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierAnchoredDraggable$default = AnchoredDraggableKt.anchoredDraggable$default(NestedScrollModifierKt.nestedScroll$default(modifierM569requiredHeightInVpY3zN4$default, (NestedScrollConnection) objRememberedValue, null, 2, null), sheetState.getAnchoredDraggableState$material3_release(), orientation, z5, false, null, 24, null);
            composerStartRestartGroup.startReplaceableGroup(1603484353);
            boolean z10 = ((i15 & 112) == 32) | ((i15 & 14) == 4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new BottomSheetScaffoldKt$StandardBottomSheet$2$1(lVar, sheetState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            int i16 = i15 >> 12;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m2004SurfaceT9BRK9s(OnRemeasuredModifierKt.onSizeChanged(modifierAnchoredDraggable$default, (l) objRememberedValue2), shape2, j12, j11, f12, f13, null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1070542936, true, new BottomSheetScaffoldKt$StandardBottomSheet$3(pVar, sheetState, z, coroutineScope, qVar)), composer2, (i16 & 112) | 12582912 | (i16 & 896) | (i16 & 7168) | (57344 & i16) | (i16 & 458752), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new BottomSheetScaffoldKt$StandardBottomSheet$4(sheetState, lVar, f10, f11, z, shape, j10, j11, f12, f13, pVar, qVar, i10, i11));
        }
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(SheetState sheetState, SnackbarHostState snackbarHostState, Composer composer, int i10, int i11) {
        Composer composer2;
        composer.startReplaceableGroup(-1474606134);
        if ((i11 & 1) != 0) {
            composer2 = composer;
            sheetState = rememberStandardBottomSheetState(null, null, false, composer2, 0, 7);
        } else {
            composer2 = composer;
        }
        if ((i11 & 2) != 0) {
            composer2.startReplaceableGroup(667326536);
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SnackbarHostState();
                composer2.updateRememberedValue(objRememberedValue);
            }
            snackbarHostState = (SnackbarHostState) objRememberedValue;
            composer2.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1474606134, i10, -1, "androidx.compose.material3.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:191)");
        }
        composer2.startReplaceableGroup(667326610);
        boolean z = ((((i10 & 14) ^ 6) > 4 && composer2.changed(sheetState)) || (i10 & 6) == 4) | ((((i10 & 112) ^ 48) > 32 && composer2.changed(snackbarHostState)) || (i10 & 48) == 32);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new BottomSheetScaffoldState(sheetState, snackbarHostState);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) objRememberedValue2;
        composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer2.endReplaceableGroup();
        return bottomSheetScaffoldState;
    }

    public static final SheetState rememberStandardBottomSheetState(SheetValue sheetValue, l<? super SheetValue, Boolean> lVar, boolean z, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(678511581);
        if ((i11 & 1) != 0) {
            sheetValue = SheetValue.PartiallyExpanded;
        }
        SheetValue sheetValue2 = sheetValue;
        if ((i11 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        l<? super SheetValue, Boolean> lVar2 = lVar;
        if ((i11 & 4) != 0) {
            z = true;
        }
        boolean z5 = z;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(678511581, i10, -1, "androidx.compose.material3.rememberStandardBottomSheetState (BottomSheetScaffold.kt:214)");
        }
        SheetState sheetStateRememberSheetState = SheetDefaultsKt.rememberSheetState(false, lVar2, sheetValue2, z5, composer, (i10 & 112) | 6 | ((i10 << 6) & 896) | ((i10 << 3) & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return sheetStateRememberSheetState;
    }
}
