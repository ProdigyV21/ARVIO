package com.arflix.tv.ui.components;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$items$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class StreamSelectorKt$StreamSelector$lambda$44$0$1$3$3$0$$inlined$items$default$4 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelect$inlined;
    final /* synthetic */ StreamSource $selectedStream$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamSelectorKt$StreamSelector$lambda$44$0$1$3$3$0$$inlined$items$default$4(List list, StreamSource streamSource, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$selectedStream$inlined = streamSource;
        this.$onSelect$inlined = lVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 14) == 0) {
            i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 112) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 731) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-632812321, i12, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:148)");
        }
        final SourcePresentation sourcePresentation = (SourcePresentation) this.$items.get(i10);
        composer.startReplaceGroup(544832671);
        boolean zIsSelectedSource = StreamSelectorKt.isSelectedSource(sourcePresentation.getStream(), this.$selectedStream$inlined);
        boolean zChanged = composer.changed(this.$onSelect$inlined) | composer.changed(sourcePresentation);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelect$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.components.StreamSelectorKt$StreamSelector$11$1$2$4$4$1$1$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6193invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6193invoke() {
                    lVar.invoke(sourcePresentation.getStream());
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        StreamSelectorKt.MobileStreamCard(sourcePresentation, zIsSelectedSource, (r7.a) objRememberedValue, composer, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
