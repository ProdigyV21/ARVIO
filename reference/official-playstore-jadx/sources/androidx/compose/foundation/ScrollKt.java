package androidx.compose.foundation;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.arflix.tv.data.repository.g;
import d7.k;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a2\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a6\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0002\u001a2\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t¨\u0006\u0011"}, d2 = {"rememberScrollState", "Landroidx/compose/foundation/ScrollState;", "initial", "", "(ILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/ScrollState;", "horizontalScroll", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "reverseScrolling", "scroll", "isScrollable", "isVertical", "verticalScroll", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScrollKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.ScrollKt$scroll$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ FlingBehavior $flingBehavior;
        final /* synthetic */ boolean $isScrollable;
        final /* synthetic */ boolean $isVertical;
        final /* synthetic */ boolean $reverseScrolling;
        final /* synthetic */ ScrollState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(boolean z, boolean z5, ScrollState scrollState, boolean z10, FlingBehavior flingBehavior) {
            super(3);
            this.$isVertical = z;
            this.$reverseScrolling = z5;
            this.$state = scrollState;
            this.$isScrollable = z10;
            this.$flingBehavior = flingBehavior;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(1478351300);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1478351300, i10, -1, "androidx.compose.foundation.scroll.<anonymous> (Scroll.kt:272)");
            }
            ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
            OverscrollEffect overscrollEffect = scrollableDefaults.overscrollEffect(composer, 6);
            composer.startReplaceableGroup(773894976);
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
            }
            composer.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            composer.endReplaceableGroup();
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, new ScrollKt$scroll$2$semantics$1(this.$reverseScrolling, this.$isVertical, this.$isScrollable, this.$state, coroutineScope), 1, null);
            Orientation orientation = this.$isVertical ? Orientation.Vertical : Orientation.Horizontal;
            Modifier modifierThen = OverscrollKt.overscroll(ClipScrollableContainerKt.clipScrollableContainer(modifierSemantics$default, orientation), overscrollEffect).then(ScrollableKt.scrollable$default(companion, this.$state, orientation, overscrollEffect, this.$isScrollable, scrollableDefaults.reverseDirection((LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection()), orientation, this.$reverseScrolling), this.$flingBehavior, this.$state.getInternalInteractionSource(), null, 128, null)).then(new ScrollingLayoutElement(this.$state, this.$reverseScrolling, this.$isVertical));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierThen;
        }
    }

    public static final Modifier horizontalScroll(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z5) {
        return scroll(modifier, scrollState, z5, flingBehavior, z, false);
    }

    public static /* synthetic */ Modifier horizontalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z5, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        if ((i10 & 4) != 0) {
            flingBehavior = null;
        }
        if ((i10 & 8) != 0) {
            z5 = false;
        }
        return horizontalScroll(modifier, scrollState, z, flingBehavior, z5);
    }

    public static final ScrollState rememberScrollState(int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-1464256199);
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1464256199, i11, -1, "androidx.compose.foundation.rememberScrollState (Scroll.kt:73)");
        }
        Object[] objArr = new Object[0];
        Saver<ScrollState, ?> saver = ScrollState.INSTANCE.getSaver();
        composer.startReplaceableGroup(-699453458);
        boolean zChanged = composer.changed(i10);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new ScrollKt$rememberScrollState$1$1(i10);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        ScrollState scrollState = (ScrollState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return scrollState;
    }

    private static final Modifier scroll(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z5, boolean z10) {
        ScrollState scrollState2;
        boolean z11;
        FlingBehavior flingBehavior2;
        boolean z12;
        boolean z13;
        l noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            scrollState2 = scrollState;
            z11 = z;
            flingBehavior2 = flingBehavior;
            z12 = z5;
            z13 = z10;
            noInspectorInfo = new ScrollKt$scroll$$inlined$debugInspectorInfo$1(scrollState2, z11, flingBehavior2, z12, z13);
        } else {
            scrollState2 = scrollState;
            z11 = z;
            flingBehavior2 = flingBehavior;
            z12 = z5;
            z13 = z10;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        FlingBehavior flingBehavior3 = flingBehavior2;
        boolean z14 = z11;
        return ComposedModifierKt.composed(modifier, noInspectorInfo, new AnonymousClass2(z13, z14, scrollState2, z12, flingBehavior3));
    }

    public static final Modifier verticalScroll(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z5) {
        return scroll(modifier, scrollState, z5, flingBehavior, z, true);
    }

    public static /* synthetic */ Modifier verticalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z5, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        if ((i10 & 4) != 0) {
            flingBehavior = null;
        }
        if ((i10 & 8) != 0) {
            z5 = false;
        }
        return verticalScroll(modifier, scrollState, z, flingBehavior, z5);
    }
}
