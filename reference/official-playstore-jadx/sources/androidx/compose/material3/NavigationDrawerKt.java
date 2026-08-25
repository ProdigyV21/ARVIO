package androidx.compose.material3;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a`\u0010\u0015\u001a\u00020\t2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001aS\u0010\u0016\u001a\u00020\t2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a?\u0010\u0018\u001a\u00020\t2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\b\b\u0002\u0010\r\u001a\u00020\f2\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001al\u0010&\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t0\u0002¢\u0006\u0002\b\n¢\u0006\u0002\b#H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001al\u0010(\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t0\u0002¢\u0006\u0002\b\n¢\u0006\u0002\b#H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010%\u001al\u0010*\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t0\u0002¢\u0006\u0002\b\n¢\u0006\u0002\b#H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010%\u001aj\u0010-\u001a\u00020\t2\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t0\u0002¢\u0006\u0002\b\n¢\u0006\u0002\b#H\u0003ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u008e\u0001\u00108\u001a\u00020\t2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\u0006\u0010/\u001a\u00020\u00032\f\u00100\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\r\u001a\u00020\f2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\n2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\n2\b\b\u0002\u00103\u001a\u00020\u001a2\b\b\u0002\u00105\u001a\u0002042\b\b\u0002\u00107\u001a\u000206H\u0007¢\u0006\u0004\b8\u00109\u001a'\u0010>\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020:2\u0006\u0010=\u001a\u00020:H\u0002¢\u0006\u0004\b>\u0010?\u001a>\u0010F\u001a\u00020\t2\u0006\u0010@\u001a\u00020\u00032\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020:0\b2\u0006\u0010C\u001a\u00020\u0010H\u0003ø\u0001\u0000¢\u0006\u0004\bD\u0010E\"\u0014\u0010G\u001a\u00020:8\u0002X\u0082D¢\u0006\u0006\n\u0004\bG\u0010H\"\u0014\u0010I\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010H\"\u0014\u0010J\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010H\"\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020:0K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010M\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006N"}, d2 = {"Landroidx/compose/material3/DrawerValue;", "initialValue", "Lkotlin/Function1;", "", "confirmStateChange", "Landroidx/compose/material3/DrawerState;", "rememberDrawerState", "(Landroidx/compose/material3/DrawerValue;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DrawerState;", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "drawerContent", "Landroidx/compose/ui/Modifier;", "modifier", "drawerState", "gesturesEnabled", "Landroidx/compose/ui/graphics/Color;", "scrimColor", "content", "ModalNavigationDrawer-FHprtrg", "(Lr7/p;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZJLr7/p;Landroidx/compose/runtime/Composer;II)V", "ModalNavigationDrawer", "DismissibleNavigationDrawer", "(Lr7/p;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZLr7/p;Landroidx/compose/runtime/Composer;II)V", "PermanentNavigationDrawer", "(Lr7/p;Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/graphics/Shape;", "drawerShape", "drawerContainerColor", "drawerContentColor", "Landroidx/compose/ui/unit/Dp;", "drawerTonalElevation", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/n;", "ModalDrawerSheet-afqeVBk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "ModalDrawerSheet", "DismissibleDrawerSheet-afqeVBk", "DismissibleDrawerSheet", "PermanentDrawerSheet-afqeVBk", "PermanentDrawerSheet", "DrawerSheet-vywBR7E", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLr7/q;Landroidx/compose/runtime/Composer;II)V", "DrawerSheet", "label", "selected", "onClick", "icon", "badge", "shape", "Landroidx/compose/material3/NavigationDrawerItemColors;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "NavigationDrawerItem", "(Lr7/p;ZLr7/a;Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/NavigationDrawerItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "pos", "calculateFraction", "(FFF)F", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "onClose", "fraction", TtmlNode.ATTR_TTS_COLOR, "Scrim-Bx497Mc", "(ZLr7/a;Lr7/a;JLandroidx/compose/runtime/Composer;I)V", "Scrim", "DrawerPositionalThreshold", "F", "DrawerVelocityThreshold", "MinimumDrawerWidth", "Landroidx/compose/animation/core/TweenSpec;", "AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerKt {
    private static final float DrawerPositionalThreshold = 0.5f;
    private static final float DrawerVelocityThreshold = Dp.m5678constructorimpl(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
    private static final float MinimumDrawerWidth = Dp.m5678constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ p<Composer, Integer, t0> $drawerContent;
        final /* synthetic */ DrawerState $drawerState;
        final /* synthetic */ boolean $gesturesEnabled;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, DrawerState drawerState, boolean z, p<? super Composer, ? super Integer, t0> pVar2, int i10, int i11) {
            super(2);
            this.$drawerContent = pVar;
            this.$modifier = modifier;
            this.$drawerState = drawerState;
            this.$gesturesEnabled = z;
            this.$content = pVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            NavigationDrawerKt.DismissibleNavigationDrawer(this.$drawerContent, this.$modifier, this.$drawerState, this.$gesturesEnabled, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<SemanticsPropertyReceiver, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.m5039setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5029getTabo7Vup1c());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05283 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $badge;
        final /* synthetic */ NavigationDrawerItemColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ boolean $selected;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05283(p<? super Composer, ? super Integer, t0> pVar, NavigationDrawerItemColors navigationDrawerItemColors, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3) {
            super(2);
            this.$icon = pVar;
            this.$colors = navigationDrawerItemColors;
            this.$selected = z;
            this.$badge = pVar2;
            this.$label = pVar3;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(191488423, i10, -1, "androidx.compose.material3.NavigationDrawerItem.<anonymous> (NavigationDrawer.kt:744)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(16), 0.0f, Dp.m5678constructorimpl(24), 0.0f, 10, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            p<Composer, Integer, t0> pVar = this.$icon;
            NavigationDrawerItemColors navigationDrawerItemColors = this.$colors;
            boolean z = this.$selected;
            p<Composer, Integer, t0> pVar2 = this.$badge;
            p<Composer, Integer, t0> pVar3 = this.$label;
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = d.g(Arrangement.INSTANCE, centerVertically, composer, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composer.startReplaceableGroup(-1538529193);
            if (pVar != null) {
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(navigationDrawerItemColors.iconColor(z, composer, 0).getValue().m3482unboximpl())), pVar, composer, ProvidedValue.$stable);
                d.w(12, companion, composer, 6);
            }
            composer.endReplaceableGroup();
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierA);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
            p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(navigationDrawerItemColors.textColor(z, composer, 0).getValue().m3482unboximpl()));
            int i11 = ProvidedValue.$stable;
            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, pVar3, composer, i11);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.startReplaceableGroup(-533536502);
            if (pVar2 != null) {
                d.w(12, companion, composer, 6);
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(navigationDrawerItemColors.badgeColor(z, composer, 0).getValue().m3482unboximpl())), pVar2, composer, i11);
            }
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $badge;
        final /* synthetic */ NavigationDrawerItemColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(p<? super Composer, ? super Integer, t0> pVar, boolean z, r7.a<t0> aVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, NavigationDrawerItemColors navigationDrawerItemColors, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$label = pVar;
            this.$selected = z;
            this.$onClick = aVar;
            this.$modifier = modifier;
            this.$icon = pVar2;
            this.$badge = pVar3;
            this.$shape = shape;
            this.$colors = navigationDrawerItemColors;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            NavigationDrawerKt.NavigationDrawerItem(this.$label, this.$selected, this.$onClick, this.$modifier, this.$icon, this.$badge, this.$shape, this.$colors, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$PermanentNavigationDrawer$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05292 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ p<Composer, Integer, t0> $drawerContent;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05292(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, int i10, int i11) {
            super(2);
            this.$drawerContent = pVar;
            this.$modifier = modifier;
            this.$content = pVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            NavigationDrawerKt.PermanentNavigationDrawer(this.$drawerContent, this.$modifier, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$rememberDrawerState$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<DrawerValue, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(DrawerValue drawerValue) {
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011e  */
    /* JADX INFO: renamed from: DismissibleDrawerSheet-afqeVBk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1713DismissibleDrawerSheetafqeVBk(androidx.compose.ui.Modifier r23, androidx.compose.ui.graphics.Shape r24, long r25, long r27, float r29, androidx.compose.foundation.layout.WindowInsets r30, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.m1713DismissibleDrawerSheetafqeVBk(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, float, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:194:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0161  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DismissibleNavigationDrawer(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.compose.ui.Modifier r25, androidx.compose.material3.DrawerState r26, boolean r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 1028
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer(r7.p, androidx.compose.ui.Modifier, androidx.compose.material3.DrawerState, boolean, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0112  */
    /* JADX INFO: renamed from: DrawerSheet-vywBR7E, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1714DrawerSheetvywBR7E(androidx.compose.foundation.layout.WindowInsets r24, androidx.compose.ui.Modifier r25, androidx.compose.ui.graphics.Shape r26, long r27, long r29, float r31, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 449
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.m1714DrawerSheetvywBR7E(androidx.compose.foundation.layout.WindowInsets, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, float, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ea  */
    /* JADX INFO: renamed from: ModalDrawerSheet-afqeVBk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1715ModalDrawerSheetafqeVBk(androidx.compose.ui.Modifier r23, androidx.compose.ui.graphics.Shape r24, long r25, long r27, float r29, androidx.compose.foundation.layout.WindowInsets r30, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.m1715ModalDrawerSheetafqeVBk(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, float, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:234:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0141  */
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
    /* JADX INFO: renamed from: ModalNavigationDrawer-FHprtrg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1716ModalNavigationDrawerFHprtrg(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.ui.Modifier r28, androidx.compose.material3.DrawerState r29, boolean r30, long r31, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 1171
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.m1716ModalNavigationDrawerFHprtrg(r7.p, androidx.compose.ui.Modifier, androidx.compose.material3.DrawerState, boolean, long, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:154:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationDrawerItem(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, boolean r30, r7.a<x6.t0> r31, androidx.compose.ui.Modifier r32, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r34, androidx.compose.ui.graphics.Shape r35, androidx.compose.material3.NavigationDrawerItemColors r36, androidx.compose.foundation.interaction.MutableInteractionSource r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 657
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem(r7.p, boolean, r7.a, androidx.compose.ui.Modifier, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.NavigationDrawerItemColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:132:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ed  */
    /* JADX INFO: renamed from: PermanentDrawerSheet-afqeVBk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1717PermanentDrawerSheetafqeVBk(androidx.compose.ui.Modifier r23, androidx.compose.ui.graphics.Shape r24, long r25, long r27, float r29, androidx.compose.foundation.layout.WindowInsets r30, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.m1717PermanentDrawerSheetafqeVBk(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, float, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PermanentNavigationDrawer(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r17, androidx.compose.ui.Modifier r18, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.PermanentNavigationDrawer(r7.p, androidx.compose.ui.Modifier, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-Bx497Mc, reason: not valid java name */
    public static final void m1718ScrimBx497Mc(boolean z, r7.a<t0> aVar, r7.a<Float> aVar2, long j10, Composer composer, int i10) {
        int i11;
        Modifier modifierSemantics;
        Composer composerStartRestartGroup = composer.startRestartGroup(2106487387);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2106487387, i11, -1, "androidx.compose.material3.Scrim (NavigationDrawer.kt:918)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(androidx.compose.ui.R.string.close_drawer), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-1858700652);
            if (z) {
                Modifier.Companion companion2 = Modifier.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(-1858700588);
                int i12 = i11 & 112;
                boolean z5 = i12 == 32;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new NavigationDrawerKt$Scrim$dismissDrawer$1$1(aVar, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, aVar, (p<? super PointerInputScope, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue);
                composerStartRestartGroup.startReplaceableGroup(-1858700504);
                boolean zChanged = (i12 == 32) | composerStartRestartGroup.changed(strM1994getStringNWtq28);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new NavigationDrawerKt$Scrim$dismissDrawer$2$1(strM1994getStringNWtq28, aVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                composerStartRestartGroup.endReplaceableGroup();
                modifierSemantics = SemanticsModifierKt.semantics(modifierPointerInput, true, (l) objRememberedValue2);
            } else {
                modifierSemantics = Modifier.INSTANCE;
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(modifierSemantics);
            composerStartRestartGroup.startReplaceableGroup(-1858700263);
            boolean z10 = ((i11 & 7168) == 2048) | ((i11 & 896) == 256);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new NavigationDrawerKt$Scrim$1$1(j10, aVar2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            CanvasKt.Canvas(modifierThen, (l) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new NavigationDrawerKt$Scrim$2(z, aVar, aVar2, j10, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float f10, float f11, float f12) {
        return qb.d.m((f12 - f10) / (f11 - f10), 0.0f, 1.0f);
    }

    public static final DrawerState rememberDrawerState(DrawerValue drawerValue, l<? super DrawerValue, Boolean> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(2098699222);
        if ((i11 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2098699222, i10, -1, "androidx.compose.material3.rememberDrawerState (NavigationDrawer.kt:279)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(lVar);
        composer.startReplaceableGroup(-21510967);
        boolean z = ((((i10 & 14) ^ 6) > 4 && composer.changed(drawerValue)) || (i10 & 6) == 4) | ((((i10 & 112) ^ 48) > 32 && composer.changed(lVar)) || (i10 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new NavigationDrawerKt$rememberDrawerState$2$1(drawerValue, lVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        DrawerState drawerState = (DrawerState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return drawerState;
    }
}
