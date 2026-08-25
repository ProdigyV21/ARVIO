package androidx.compose.material3;

import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import d7.k;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TabRowKt$ScrollableTabRowImp$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $divider;
    final /* synthetic */ float $edgePadding;
    final /* synthetic */ q<List<TabPosition>, Composer, Integer, t0> $indicator;
    final /* synthetic */ ScrollState $scrollState;
    final /* synthetic */ int $selectedTabIndex;
    final /* synthetic */ p<Composer, Integer, t0> $tabs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TabRowKt$ScrollableTabRowImp$1(ScrollState scrollState, float f10, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, q<? super List<TabPosition>, ? super Composer, ? super Integer, t0> qVar, int i10) {
        super(2);
        this.$scrollState = scrollState;
        this.$edgePadding = f10;
        this.$tabs = pVar;
        this.$divider = pVar2;
        this.$indicator = qVar;
        this.$selectedTabIndex = i10;
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
            ComposerKt.traceEventStart(-1178901494, i10, -1, "androidx.compose.material3.ScrollableTabRowImp.<anonymous> (TabRow.kt:861)");
        }
        Object objG = a0.c.g(773894976, composer, -492369756);
        Composer.Companion companion = Composer.INSTANCE;
        if (objG == companion.getEmpty()) {
            objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(121290627);
        boolean zChanged = composer.changed(this.$scrollState) | composer.changed(coroutineScope);
        ScrollState scrollState = this.$scrollState;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new ScrollableTabData(scrollState, coroutineScope);
            composer.updateRememberedValue(objRememberedValue);
        }
        ScrollableTabData scrollableTabData = (ScrollableTabData) objRememberedValue;
        composer.endReplaceableGroup();
        Modifier modifierClipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), this.$scrollState, false, null, false, 14, null)));
        composer.startReplaceableGroup(121291080);
        boolean zChanged2 = composer.changed(this.$edgePadding) | composer.changed(this.$tabs) | composer.changed(this.$divider) | composer.changed(this.$indicator) | composer.changedInstance(scrollableTabData) | composer.changed(this.$selectedTabIndex);
        float f10 = this.$edgePadding;
        p<Composer, Integer, t0> pVar = this.$tabs;
        p<Composer, Integer, t0> pVar2 = this.$divider;
        int i11 = this.$selectedTabIndex;
        q<List<TabPosition>, Composer, Integer, t0> qVar = this.$indicator;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == companion.getEmpty()) {
            Object tabRowKt$ScrollableTabRowImp$1$1$1 = new TabRowKt$ScrollableTabRowImp$1$1$1(f10, pVar, pVar2, scrollableTabData, i11, qVar);
            composer.updateRememberedValue(tabRowKt$ScrollableTabRowImp$1$1$1);
            objRememberedValue2 = tabRowKt$ScrollableTabRowImp$1$1$1;
        }
        composer.endReplaceableGroup();
        SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, (p) objRememberedValue2, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
