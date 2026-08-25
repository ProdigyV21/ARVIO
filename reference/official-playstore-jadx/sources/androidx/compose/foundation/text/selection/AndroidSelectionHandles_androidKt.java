package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.c;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a7\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\n\u0010\u000b\u001a-\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a)\u0010\u0011\u001a\u00020\u0007*\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u001b\u0010\u0017\u001a\u00020\u0016*\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001a2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\t0\f¢\u0006\u0002\b\u001cH\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a'\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000e\u0010 \u001a\u001f\u0010\"\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Landroidx/compose/foundation/text/selection/OffsetProvider;", "offsetProvider", "", "isStartHandle", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "direction", "handlesCrossed", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "SelectionHandle", "(Landroidx/compose/foundation/text/selection/OffsetProvider;ZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "Lkotlin/Function0;", "iconVisible", "isLeft", "SelectionHandleIcon", "(Landroidx/compose/ui/Modifier;Lr7/a;ZLandroidx/compose/runtime/Composer;I)V", "drawSelectionHandle", "(Landroidx/compose/ui/Modifier;Lr7/a;Z)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/draw/CacheDrawScope;", "", "radius", "Landroidx/compose/ui/graphics/ImageBitmap;", "createHandleImage", "(Landroidx/compose/ui/draw/CacheDrawScope;F)Landroidx/compose/ui/graphics/ImageBitmap;", "positionProvider", "Landroidx/compose/foundation/text/selection/HandleReferencePoint;", "handleReferencePoint", "Landroidx/compose/runtime/Composable;", "content", "HandlePopup", "(Landroidx/compose/foundation/text/selection/OffsetProvider;Landroidx/compose/foundation/text/selection/HandleReferencePoint;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "(ZLandroidx/compose/ui/text/style/ResolvedTextDirection;Z)Z", "areHandlesCrossed", "isHandleLtrDirection", "(Landroidx/compose/ui/text/style/ResolvedTextDirection;Z)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidSelectionHandles_androidKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandlePopup$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ HandleReferencePoint $handleReferencePoint;
        final /* synthetic */ OffsetProvider $positionProvider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(OffsetProvider offsetProvider, HandleReferencePoint handleReferencePoint, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$positionProvider = offsetProvider;
            this.$handleReferencePoint = handleReferencePoint;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidSelectionHandles_androidKt.HandlePopup(this.$positionProvider, this.$handleReferencePoint, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03891 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ boolean $isLeft;
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ OffsetProvider $offsetProvider;
        final /* synthetic */ ViewConfiguration $viewConfiguration;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00541 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ boolean $isLeft;
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ Modifier $modifier;
            final /* synthetic */ OffsetProvider $offsetProvider;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00551 extends r implements l<SemanticsPropertyReceiver, t0> {
                final /* synthetic */ boolean $isLeft;
                final /* synthetic */ boolean $isStartHandle;
                final /* synthetic */ OffsetProvider $offsetProvider;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00551(OffsetProvider offsetProvider, boolean z, boolean z5) {
                    super(1);
                    this.$offsetProvider = offsetProvider;
                    this.$isStartHandle = z;
                    this.$isLeft = z5;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((SemanticsPropertyReceiver) obj);
                    return t0.f22605a;
                }

                public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    long jMo802provideF1C5BW0 = this.$offsetProvider.mo802provideF1C5BW0();
                    semanticsPropertyReceiver.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(this.$isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd, jMo802provideF1C5BW0, this.$isLeft ? SelectionHandleAnchor.Left : SelectionHandleAnchor.Right, OffsetKt.m3255isSpecifiedk4lQ0M(jMo802provideF1C5BW0), null));
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class AnonymousClass2 extends r implements r7.a<Boolean> {
                final /* synthetic */ OffsetProvider $offsetProvider;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(OffsetProvider offsetProvider) {
                    super(0);
                    this.$offsetProvider = offsetProvider;
                }

                @Override // r7.a
                public final Boolean invoke() {
                    return Boolean.valueOf(OffsetKt.m3255isSpecifiedk4lQ0M(this.$offsetProvider.mo802provideF1C5BW0()));
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00541(Modifier modifier, boolean z, OffsetProvider offsetProvider, boolean z5) {
                super(2);
                this.$modifier = modifier;
                this.$isLeft = z;
                this.$offsetProvider = offsetProvider;
                this.$isStartHandle = z5;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return t0.f22605a;
            }

            public final void invoke(Composer composer, int i10) {
                if ((i10 & 11) == 2 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1338858912, i10, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous>.<anonymous> (AndroidSelectionHandles.android.kt:75)");
                }
                AndroidSelectionHandles_androidKt.SelectionHandleIcon(SemanticsModifierKt.semantics$default(this.$modifier, false, new C00551(this.$offsetProvider, this.$isStartHandle, this.$isLeft), 1, null), new AnonymousClass2(this.$offsetProvider), this.$isLeft, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03891(ViewConfiguration viewConfiguration, Modifier modifier, boolean z, OffsetProvider offsetProvider, boolean z5) {
            super(2);
            this.$viewConfiguration = viewConfiguration;
            this.$modifier = modifier;
            this.$isLeft = z;
            this.$offsetProvider = offsetProvider;
            this.$isStartHandle = z5;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1868300064, i10, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:74)");
            }
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalViewConfiguration().provides(this.$viewConfiguration), ComposableLambdaKt.composableLambda(composer, -1338858912, true, new C00541(this.$modifier, this.$isLeft, this.$offsetProvider, this.$isStartHandle)), composer, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ ResolvedTextDirection $direction;
        final /* synthetic */ boolean $handlesCrossed;
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ OffsetProvider $offsetProvider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OffsetProvider offsetProvider, boolean z, ResolvedTextDirection resolvedTextDirection, boolean z5, Modifier modifier, int i10) {
            super(2);
            this.$offsetProvider = offsetProvider;
            this.$isStartHandle = z;
            this.$direction = resolvedTextDirection;
            this.$handlesCrossed = z5;
            this.$modifier = modifier;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidSelectionHandles_androidKt.SelectionHandle(this.$offsetProvider, this.$isStartHandle, this.$direction, this.$handlesCrossed, this.$modifier, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandleIcon$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03901 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ r7.a<Boolean> $iconVisible;
        final /* synthetic */ boolean $isLeft;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03901(Modifier modifier, r7.a<Boolean> aVar, boolean z, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$iconVisible = aVar;
            this.$isLeft = z;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidSelectionHandles_androidKt.SelectionHandleIcon(this.$modifier, this.$iconVisible, this.$isLeft, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03911 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ r7.a<Boolean> $iconVisible;
        final /* synthetic */ boolean $isLeft;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03911(r7.a<Boolean> aVar, boolean z) {
            super(3);
            this.$iconVisible = aVar;
            this.$isLeft = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(-196777734);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-196777734, i10, -1, "androidx.compose.foundation.text.selection.drawSelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:110)");
            }
            long handleColor = ((TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors())).getHandleColor();
            composer.startReplaceableGroup(442417347);
            boolean zChanged = composer.changed(handleColor) | composer.changedInstance(this.$iconVisible) | composer.changed(this.$isLeft);
            r7.a<Boolean> aVar = this.$iconVisible;
            boolean z = this.$isLeft;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1(handleColor, aVar, z);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifierDrawWithCache = DrawModifierKt.drawWithCache(modifier, (l) objRememberedValue);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierDrawWithCache;
        }
    }

    public static final void HandlePopup(OffsetProvider offsetProvider, HandleReferencePoint handleReferencePoint, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(345017889);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changed(offsetProvider) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changed(handleReferencePoint) ? 32 : 16;
        }
        if ((i10 & 896) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i11 & 731) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(345017889, i11, -1, "androidx.compose.foundation.text.selection.HandlePopup (AndroidSelectionHandles.android.kt:223)");
            }
            int i12 = i11 << 3;
            composerStartRestartGroup.startReplaceableGroup(511388516);
            boolean zChanged = composerStartRestartGroup.changed(handleReferencePoint) | composerStartRestartGroup.changed(offsetProvider);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new HandlePositionProvider(handleReferencePoint, offsetProvider);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            AndroidPopup_androidKt.Popup((HandlePositionProvider) objRememberedValue, null, new PopupProperties(false, false, false, null, true, false, 15, null), pVar, composerStartRestartGroup, (i12 & 7168) | RendererCapabilities.DECODER_SUPPORT_MASK, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(offsetProvider, handleReferencePoint, pVar, i10));
        }
    }

    public static final void SelectionHandle(OffsetProvider offsetProvider, boolean z, ResolvedTextDirection resolvedTextDirection, boolean z5, Modifier modifier, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-626955031);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changed(offsetProvider) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & 896) == 0) {
            i11 |= composerStartRestartGroup.changed(resolvedTextDirection) ? 256 : 128;
        }
        if ((i10 & 7168) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        if ((57344 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(modifier) ? 16384 : 8192;
        }
        int i12 = i11;
        if ((46811 & i12) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-626955031, i12, -1, "androidx.compose.foundation.text.selection.SelectionHandle (AndroidSelectionHandles.android.kt:66)");
            }
            boolean zIsLeft = isLeft(z, resolvedTextDirection, z5);
            HandlePopup(offsetProvider, zIsLeft ? HandleReferencePoint.TopRight : HandleReferencePoint.TopLeft, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1868300064, true, new C03891((ViewConfiguration) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalViewConfiguration()), modifier, zIsLeft, offsetProvider, z)), composerStartRestartGroup, (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(offsetProvider, z, resolvedTextDirection, z5, modifier, i10));
        }
    }

    public static final void SelectionHandleIcon(Modifier modifier, r7.a<Boolean> aVar, boolean z, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(2111672474);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & 896) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i11 & 731) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2111672474, i11, -1, "androidx.compose.foundation.text.selection.SelectionHandleIcon (AndroidSelectionHandles.android.kt:98)");
            }
            SpacerKt.Spacer(drawSelectionHandle(SizeKt.m580sizeVpY3zN4(modifier, SelectionHandlesKt.getHandleWidth(), SelectionHandlesKt.getHandleHeight()), aVar, z), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C03901(modifier, aVar, z, i10));
        }
    }

    public static final ImageBitmap createHandleImage(CacheDrawScope cacheDrawScope, float f10) {
        int iCeil = ((int) Math.ceil(f10)) * 2;
        HandleImageCache handleImageCache = HandleImageCache.INSTANCE;
        ImageBitmap imageBitmap = handleImageCache.getImageBitmap();
        Canvas canvas = handleImageCache.getCanvas();
        CanvasDrawScope canvasDrawScope = handleImageCache.getCanvasDrawScope();
        if (imageBitmap == null || canvas == null || iCeil > imageBitmap.getWidth() || iCeil > imageBitmap.getHeight()) {
            imageBitmap = ImageBitmapKt.m3679ImageBitmapx__hDU$default(iCeil, iCeil, ImageBitmapConfig.INSTANCE.m3673getAlpha8_sVssgQ(), false, null, 24, null);
            handleImageCache.setImageBitmap(imageBitmap);
            canvas = CanvasKt.Canvas(imageBitmap);
            handleImageCache.setCanvas(canvas);
        }
        ImageBitmap imageBitmap2 = imageBitmap;
        Canvas canvas2 = canvas;
        if (canvasDrawScope == null) {
            canvasDrawScope = new CanvasDrawScope();
            handleImageCache.setCanvasDrawScope(canvasDrawScope);
        }
        CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
        LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
        long jSize = androidx.compose.ui.geometry.SizeKt.Size(imageBitmap2.getWidth(), imageBitmap2.getHeight());
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
        Density density = drawParams.getDensity();
        LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
        Canvas canvas3 = drawParams.getCanvas();
        long size = drawParams.getSize();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
        drawParams2.setDensity(cacheDrawScope);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas2);
        drawParams2.m3921setSizeuvyYCjk(jSize);
        canvas2.save();
        c.K(canvasDrawScope2, Color.INSTANCE.m3498getBlack0d7_KjU(), 0L, c.c(canvasDrawScope2), 0.0f, null, null, BlendMode.INSTANCE.m3393getClear0nO6VwU(), 58, null);
        c.K(canvasDrawScope2, ColorKt.Color(4278190080L), Offset.INSTANCE.m3252getZeroF1C5BW0(), androidx.compose.ui.geometry.SizeKt.Size(f10, f10), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        c.x(canvasDrawScope2, ColorKt.Color(4278190080L), f10, OffsetKt.Offset(f10, f10), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        canvas2.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
        drawParams3.setDensity(density);
        drawParams3.setLayoutDirection(layoutDirection2);
        drawParams3.setCanvas(canvas3);
        drawParams3.m3921setSizeuvyYCjk(size);
        return imageBitmap2;
    }

    public static final Modifier drawSelectionHandle(Modifier modifier, r7.a<Boolean> aVar, boolean z) {
        return ComposedModifierKt.composed$default(modifier, null, new C03911(aVar, z), 1, null);
    }

    public static final boolean isHandleLtrDirection(ResolvedTextDirection resolvedTextDirection, boolean z) {
        if (resolvedTextDirection != ResolvedTextDirection.Ltr || z) {
            return resolvedTextDirection == ResolvedTextDirection.Rtl && z;
        }
        return true;
    }

    public static final boolean isLeft(boolean z, ResolvedTextDirection resolvedTextDirection, boolean z5) {
        return z ? isHandleLtrDirection(resolvedTextDirection, z5) : !isHandleLtrDirection(resolvedTextDirection, z5);
    }
}
