package com.arflix.tv.ui.screens.tv;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class TvScreenKt$CategoryRail$lambda$8$0$$inlined$itemsIndexed$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ Set $favoriteGroups$inlined;
    final /* synthetic */ int $focusedGroupIndex$inlined;
    final /* synthetic */ boolean $isFocused$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.a $onDismissMenu$inlined;
    final /* synthetic */ r7.l $onGroupClick$inlined;
    final /* synthetic */ r7.l $onGroupLongPress$inlined;
    final /* synthetic */ r7.l $onMoveDown$inlined;
    final /* synthetic */ r7.l $onMoveToTop$inlined;
    final /* synthetic */ r7.l $onMoveUp$inlined;
    final /* synthetic */ r7.l $onToggleFavorite$inlined;
    final /* synthetic */ r7.l $onToggleHidden$inlined;
    final /* synthetic */ int $showMenuForIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$CategoryRail$lambda$8$0$$inlined$itemsIndexed$3(List list, boolean z, int i10, Set set, int i11, r7.l lVar, r7.l lVar2, r7.a aVar, r7.l lVar3, r7.l lVar4, r7.l lVar5, r7.l lVar6, r7.l lVar7) {
        super(4);
        this.$items = list;
        this.$isFocused$inlined = z;
        this.$focusedGroupIndex$inlined = i10;
        this.$favoriteGroups$inlined = set;
        this.$showMenuForIndex$inlined = i11;
        this.$onGroupClick$inlined = lVar;
        this.$onGroupLongPress$inlined = lVar2;
        this.$onDismissMenu$inlined = aVar;
        this.$onToggleFavorite$inlined = lVar3;
        this.$onToggleHidden$inlined = lVar4;
        this.$onMoveUp$inlined = lVar5;
        this.$onMoveToTop$inlined = lVar6;
        this.$onMoveDown$inlined = lVar7;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
        int i13;
        boolean z;
        if ((i11 & 14) == 0) {
            i12 = i11 | (composer.changed(lazyItemScope) ? 4 : 2);
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
            ComposerKt.traceEventStart(-1091073711, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
        }
        final String str = (String) this.$items.get(i10);
        composer.startReplaceGroup(-750323965);
        if (this.$isFocused$inlined && i10 == this.$focusedGroupIndex$inlined) {
            i13 = i12;
            z = true;
        } else {
            i13 = i12;
            z = false;
        }
        boolean zContains = this.$favoriteGroups$inlined.contains(str);
        boolean z5 = i10 == this.$showMenuForIndex$inlined;
        int i14 = (i13 & 112) ^ 48;
        boolean zChanged = composer.changed(this.$onGroupClick$inlined) | ((i14 > 32 && composer.changed(i10)) || (i13 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onGroupClick$inlined;
            objRememberedValue = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6332invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6332invoke() {
                    lVar.invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.a aVar = (r7.a) objRememberedValue;
        boolean zChanged2 = composer.changed(this.$onGroupLongPress$inlined) | ((i14 > 32 && composer.changed(i10)) || (i13 & 48) == 32);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar2 = this.$onGroupLongPress$inlined;
            objRememberedValue2 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6333invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6333invoke() {
                    lVar2.invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar2 = (r7.a) objRememberedValue2;
        r7.a aVar3 = this.$onDismissMenu$inlined;
        boolean zChanged3 = composer.changed(this.$onToggleFavorite$inlined) | composer.changed(str);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar3 = this.$onToggleFavorite$inlined;
            objRememberedValue3 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$3$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6334invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6334invoke() {
                    lVar3.invoke(str);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        r7.a aVar4 = (r7.a) objRememberedValue3;
        boolean zChanged4 = composer.changed(this.$onToggleHidden$inlined) | composer.changed(str);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar4 = this.$onToggleHidden$inlined;
            objRememberedValue4 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$4$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6335invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6335invoke() {
                    lVar4.invoke(str);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        r7.a aVar5 = (r7.a) objRememberedValue4;
        boolean zChanged5 = composer.changed(this.$onMoveUp$inlined) | composer.changed(str);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar5 = this.$onMoveUp$inlined;
            objRememberedValue5 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$5$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6336invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6336invoke() {
                    lVar5.invoke(str);
                }
            };
            composer.updateRememberedValue(objRememberedValue5);
        }
        r7.a aVar6 = (r7.a) objRememberedValue5;
        boolean zChanged6 = composer.changed(this.$onMoveToTop$inlined) | composer.changed(str);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar6 = this.$onMoveToTop$inlined;
            objRememberedValue6 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$6$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6337invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6337invoke() {
                    lVar6.invoke(str);
                }
            };
            composer.updateRememberedValue(objRememberedValue6);
        }
        r7.a aVar7 = (r7.a) objRememberedValue6;
        boolean zChanged7 = composer.changed(this.$onMoveDown$inlined) | composer.changed(str);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChanged7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar7 = this.$onMoveDown$inlined;
            objRememberedValue7 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$CategoryRail$9$1$3$7$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6338invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6338invoke() {
                    lVar7.invoke(str);
                }
            };
            composer.updateRememberedValue(objRememberedValue7);
        }
        TvScreenKt.GroupRailItem(str, z, zContains, z5, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, (r7.a) objRememberedValue7, composer, 0, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
