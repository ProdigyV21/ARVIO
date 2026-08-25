package androidx.compose.foundation.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.modifier.ModifierLocalKt;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.platform.InspectableValueKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001b\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0005\u0010\b\u001a6\u0010\u000f\u001a\u00020\u0000*\u00020\u00002!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\" \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/layout/WindowInsets;", "insets", "windowInsetsPadding", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/WindowInsets;)Landroidx/compose/ui/Modifier;", "consumeWindowInsets", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;)Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "consumedWindowInsets", "Lx6/t0;", "block", "onConsumedWindowInsetsChanged", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "ModifierLocalConsumedWindowInsets", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getModifierLocalConsumedWindowInsets", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WindowInsetsPaddingKt {
    private static final ProvidableModifierLocal<WindowInsets> ModifierLocalConsumedWindowInsets = ModifierLocalKt.modifierLocalOf(WindowInsetsPaddingKt$ModifierLocalConsumedWindowInsets$1.INSTANCE);

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsPaddingKt$consumeWindowInsets$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ WindowInsets $insets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(WindowInsets windowInsets) {
            super(3);
            this.$insets = windowInsets;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(788931215);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(788931215, i10, -1, "androidx.compose.foundation.layout.consumeWindowInsets.<anonymous> (WindowInsetsPadding.kt:81)");
            }
            WindowInsets windowInsets = this.$insets;
            composer.startReplaceableGroup(1157296644);
            boolean zChanged = composer.changed(windowInsets);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new UnionInsetsConsumingModifier(windowInsets);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            UnionInsetsConsumingModifier unionInsetsConsumingModifier = (UnionInsetsConsumingModifier) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return unionInsetsConsumingModifier;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsPaddingKt$consumeWindowInsets$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ PaddingValues $paddingValues;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(PaddingValues paddingValues) {
            super(3);
            this.$paddingValues = paddingValues;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(114694318);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(114694318, i10, -1, "androidx.compose.foundation.layout.consumeWindowInsets.<anonymous> (WindowInsetsPadding.kt:105)");
            }
            PaddingValues paddingValues = this.$paddingValues;
            composer.startReplaceableGroup(1157296644);
            boolean zChanged = composer.changed(paddingValues);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new PaddingValuesConsumingModifier(paddingValues);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            PaddingValuesConsumingModifier paddingValuesConsumingModifier = (PaddingValuesConsumingModifier) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return paddingValuesConsumingModifier;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsPaddingKt$onConsumedWindowInsetsChanged$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03382 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ l<WindowInsets, t0> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03382(l<? super WindowInsets, t0> lVar) {
            super(3);
            this.$block = lVar;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(-1608161351);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1608161351, i10, -1, "androidx.compose.foundation.layout.onConsumedWindowInsetsChanged.<anonymous> (WindowInsetsPadding.kt:125)");
            }
            l<WindowInsets, t0> lVar = this.$block;
            composer.startReplaceableGroup(1157296644);
            boolean zChanged = composer.changed(lVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new ConsumedInsetsModifier(lVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            ConsumedInsetsModifier consumedInsetsModifier = (ConsumedInsetsModifier) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return consumedInsetsModifier;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsPaddingKt$windowInsetsPadding$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03392 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ WindowInsets $insets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03392(WindowInsets windowInsets) {
            super(3);
            this.$insets = windowInsets;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(-1415685722);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1415685722, i10, -1, "androidx.compose.foundation.layout.windowInsetsPadding.<anonymous> (WindowInsetsPadding.kt:61)");
            }
            WindowInsets windowInsets = this.$insets;
            composer.startReplaceableGroup(1157296644);
            boolean zChanged = composer.changed(windowInsets);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new InsetsPaddingModifier(windowInsets);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            InsetsPaddingModifier insetsPaddingModifier = (InsetsPaddingModifier) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return insetsPaddingModifier;
        }
    }

    public static final Modifier consumeWindowInsets(Modifier modifier, WindowInsets windowInsets) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsPaddingKt$consumeWindowInsets$$inlined$debugInspectorInfo$1(windowInsets) : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(windowInsets));
    }

    public static final ProvidableModifierLocal<WindowInsets> getModifierLocalConsumedWindowInsets() {
        return ModifierLocalConsumedWindowInsets;
    }

    public static final Modifier onConsumedWindowInsetsChanged(Modifier modifier, l<? super WindowInsets, t0> lVar) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsPaddingKt$onConsumedWindowInsetsChanged$$inlined$debugInspectorInfo$1(lVar) : InspectableValueKt.getNoInspectorInfo(), new C03382(lVar));
    }

    public static final Modifier windowInsetsPadding(Modifier modifier, WindowInsets windowInsets) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsPaddingKt$windowInsetsPadding$$inlined$debugInspectorInfo$1(windowInsets) : InspectableValueKt.getNoInspectorInfo(), new C03392(windowInsets));
    }

    public static final Modifier consumeWindowInsets(Modifier modifier, PaddingValues paddingValues) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsPaddingKt$consumeWindowInsets$$inlined$debugInspectorInfo$2(paddingValues) : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass4(paddingValues));
    }
}
