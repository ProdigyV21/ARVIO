package androidx.tv.material3;

import android.view.accessibility.AccessibilityManager;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\u001a\u009c\u0001\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\u001e\b\u0002\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f21\u0010\u0016\u001a-\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r0\u0011¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a!\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0003¢\u0006\u0004\b\u001e\u0010\u001f\u001aT\u0010#\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001d2#\b\u0002\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\r0\u000bH\u0003¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0003¢\u0006\u0004\b%\u0010&\u001a\u0019\u0010(\u001a\u00020\u00042\b\b\u0002\u0010'\u001a\u00020\u0000H\u0007¢\u0006\u0004\b(\u0010)\u001a#\u0010+\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0004H\u0001¢\u0006\u0004\b+\u0010,¨\u0006.²\u0006\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00198\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010!\u001a\u00020\u001d8\n@\nX\u008a\u008e\u0002²\u0006\f\u0010-\u001a\u00020\u00008\nX\u008a\u0084\u0002"}, d2 = {"", "itemCount", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/tv/material3/l0;", "carouselState", "", "autoScrollDurationMillis", "Landroidx/compose/animation/ContentTransform;", "contentTransformStartToEnd", "contentTransformEndToStart", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "carouselIndicator", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "content", "Carousel", "(ILandroidx/compose/ui/Modifier;Landroidx/tv/material3/l0;JLandroidx/compose/animation/ContentTransform;Landroidx/compose/animation/ContentTransform;Lr7/q;Lr7/r;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/focus/FocusState;", "focusState", "Landroid/view/accessibility/AccessibilityManager;", "accessibilityManager", "", "shouldPerformAutoScroll", "(Landroidx/compose/ui/focus/FocusState;Landroid/view/accessibility/AccessibilityManager;Landroidx/compose/runtime/Composer;I)Z", "doAutoScroll", "isAutoScrollActive", "onAutoScrollChange", "AutoScrollSideEffect", "(JILandroidx/tv/material3/l0;ZLr7/l;Landroidx/compose/runtime/Composer;II)V", "CarouselStateUpdater", "(Landroidx/tv/material3/l0;ILandroidx/compose/runtime/Composer;I)V", "initialActiveItemIndex", "rememberCarouselState", "(ILandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/l0;", "state", "carouselSemantics", "(Landroidx/compose/ui/Modifier;ILandroidx/tv/material3/l0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "updatedItemCount", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarouselKt {

    /* JADX INFO: renamed from: androidx.tv.material3.CarouselKt$Carousel$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ int f5566i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ l0 f5567l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i10, l0 l0Var) {
            super(3);
            this.f5566i = i10;
            this.f5567l = l0Var;
        }

        @Override // r7.q
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((BoxScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(BoxScope boxScope, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(boxScope) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-204492407, i10, -1, "androidx.tv.material3.Carousel.<anonymous> (Carousel.kt:116)");
            }
            CarouselDefaults.f5565a.m5981IndicatorRowhGBTI10(this.f5566i, this.f5567l.f6185b.getIntValue(), PaddingKt.m529padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomEnd()), Dp.m5678constructorimpl(16)), 0.0f, null, composer, 196608, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AutoScrollSideEffect(long r16, int r18, androidx.tv.material3.l0 r19, boolean r20, r7.l<? super java.lang.Boolean, x6.t0> r21, androidx.compose.runtime.Composer r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CarouselKt.AutoScrollSideEffect(long, int, androidx.tv.material3.l0, boolean, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03ed A[PHI: r2
      0x03ed: PHI (r2v32 androidx.compose.animation.ContentTransform) = (r2v29 androidx.compose.animation.ContentTransform), (r2v33 androidx.compose.animation.ContentTransform) binds: [B:199:0x03eb, B:195:0x03e4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:215:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Carousel(int r30, androidx.compose.ui.Modifier r31, androidx.tv.material3.l0 r32, long r33, androidx.compose.animation.ContentTransform r35, androidx.compose.animation.ContentTransform r36, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, r7.r<? super androidx.compose.animation.AnimatedContentScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r38, androidx.compose.runtime.Composer r39, int r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1147
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CarouselKt.Carousel(int, androidx.compose.ui.Modifier, androidx.tv.material3.l0, long, androidx.compose.animation.ContentTransform, androidx.compose.animation.ContentTransform, r7.q, r7.r, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CarouselStateUpdater(l0 l0Var, int i10, Composer composer, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1406923675);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(l0Var) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1406923675, i12, -1, "androidx.tv.material3.CarouselStateUpdater (Carousel.kt:349)");
            }
            Integer numValueOf = Integer.valueOf(i10);
            composerStartRestartGroup.startReplaceableGroup(975108815);
            boolean z = ((i12 & 112) == 32) | ((i12 & 14) == 4);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new androidx.tv.foundation.lazy.grid.a1(i10, l0Var, (d7.d) null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(l0Var, numValueOf, (r7.p) objRememberedValue, composerStartRestartGroup, i12 & 126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new g0(l0Var, i10, i11));
        }
    }

    public static final Modifier carouselSemantics(Modifier modifier, int i10, l0 l0Var, Composer composer, int i11) {
        composer.startReplaceableGroup(-1538853104);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1538853104, i11, -1, "androidx.tv.material3.carouselSemantics (Carousel.kt:523)");
        }
        composer.startReplaceableGroup(-1147642987);
        boolean z = ((((i11 & 112) ^ 48) > 32 && composer.changed(i10)) || (i11 & 48) == 32) | ((((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(l0Var)) || (i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new h0(new ScrollAxisRange(new z(1, l0Var), new i0(i10, 0), false), new androidx.navigation.compose.j(l0Var, i10, 1), i10), 1, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then((Modifier) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }

    public static final l0 rememberCarouselState(int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-1706722661);
        boolean z = true;
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1706722661, i11, -1, "androidx.tv.material3.rememberCarouselState (Carousel.kt:367)");
        }
        Object[] objArr = new Object[0];
        Saver saver = l0.f6183d;
        composer.startReplaceableGroup(2121214308);
        if ((((i11 & 14) ^ 6) <= 4 || !composer.changed(i10)) && (i11 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i0(i10, 1);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        l0 l0Var = (l0) RememberSaveableKt.m3084rememberSaveable(objArr, saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return l0Var;
    }

    private static final boolean shouldPerformAutoScroll(FocusState focusState, AccessibilityManager accessibilityManager, Composer composer, int i10) {
        composer.startReplaceableGroup(1565774971);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1565774971, i10, -1, "androidx.tv.material3.shouldPerformAutoScroll (Carousel.kt:206)");
        }
        boolean z = false;
        boolean zIsFocused = focusState != null ? focusState.isFocused() : false;
        boolean hasFocus = focusState != null ? focusState.getHasFocus() : false;
        if (!accessibilityManager.isEnabled() && !zIsFocused && !hasFocus) {
            z = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return z;
    }
}
