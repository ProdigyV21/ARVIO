package com.arflix.tv.ui.screens.tv;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class TvScreenKt$GuidePanel$lambda$5$1$0$$inlined$itemsIndexed$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ Set $favoriteChannels$inlined;
    final /* synthetic */ int $focusedChannelIndex$inlined;
    final /* synthetic */ boolean $guideFocused$inlined;
    final /* synthetic */ boolean $isMobile$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ MutableState $now$delegate$inlined;
    final /* synthetic */ Map $nowNext$inlined;
    final /* synthetic */ float $nowRatio$inlined;
    final /* synthetic */ r7.l $onChannelClick$inlined;
    final /* synthetic */ String $playingChannelId$inlined;
    final /* synthetic */ long $windowEnd$inlined;
    final /* synthetic */ long $windowStart$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$GuidePanel$lambda$5$1$0$$inlined$itemsIndexed$3(List list, boolean z, int i10, Map map, String str, Set set, long j10, long j11, float f10, boolean z5, r7.l lVar, MutableState mutableState) {
        super(4);
        this.$items = list;
        this.$guideFocused$inlined = z;
        this.$focusedChannelIndex$inlined = i10;
        this.$nowNext$inlined = map;
        this.$playingChannelId$inlined = str;
        this.$favoriteChannels$inlined = set;
        this.$windowStart$inlined = j10;
        this.$windowEnd$inlined = j11;
        this.$nowRatio$inlined = f10;
        this.$isMobile$inlined = z5;
        this.$onChannelClick$inlined = lVar;
        this.$now$delegate$inlined = mutableState;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
        List<IptvProgram> upcoming;
        IptvProgram later;
        IptvProgram next;
        int i13;
        IptvProgram iptvProgram;
        boolean z;
        boolean z5;
        boolean z10;
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
        IptvChannel iptvChannel = (IptvChannel) this.$items.get(i10);
        composer.startReplaceGroup(-1724760110);
        boolean z11 = this.$guideFocused$inlined && i10 == this.$focusedChannelIndex$inlined;
        IptvNowNext iptvNowNext = (IptvNowNext) this.$nowNext$inlined.get(iptvChannel.getId());
        boolean zChanged = composer.changed(iptvNowNext);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            z6.c cVarN = t7.a.n();
            if (iptvNowNext != null && (next = iptvNowNext.getNext()) != null) {
                cVarN.add(next);
            }
            if (iptvNowNext != null && (later = iptvNowNext.getLater()) != null) {
                cVarN.add(later);
            }
            if (iptvNowNext != null && (upcoming = iptvNowNext.getUpcoming()) != null) {
                cVarN.addAll(upcoming);
            }
            z6.c cVarE = t7.a.e(cVarN);
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            ListIterator listIterator = cVarE.listIterator(0);
            while (true) {
                z6.a aVar = (z6.a) listIterator;
                if (!aVar.hasNext()) {
                    break;
                }
                Object next2 = aVar.next();
                IptvProgram iptvProgram2 = (IptvProgram) next2;
                if (hashSet.add(iptvProgram2.getStartUtcMillis() + "-" + iptvProgram2.getEndUtcMillis())) {
                    arrayList.add(next2);
                }
            }
            composer.updateRememberedValue(arrayList);
            objRememberedValue = arrayList;
        }
        List list = (List) objRememberedValue;
        List<IptvProgram> recent = iptvNowNext != null ? iptvNowNext.getRecent() : null;
        if (recent == null) {
            recent = kotlin.collections.z.f19728i;
        }
        IptvProgram now = iptvNowNext != null ? iptvNowNext.getNow() : null;
        boolean zA = kotlin.jvm.internal.p.a(iptvChannel.getId(), this.$playingChannelId$inlined);
        boolean zContains = this.$favoriteChannels$inlined.contains(iptvChannel.getId());
        if (z11 || kotlin.jvm.internal.p.a(iptvChannel.getId(), this.$playingChannelId$inlined)) {
            i13 = i12;
            iptvProgram = now;
            z = z11;
            z5 = zA;
            z10 = true;
        } else {
            i13 = i12;
            iptvProgram = now;
            z = z11;
            z5 = zA;
            z10 = false;
        }
        long j10 = this.$windowStart$inlined;
        List<IptvProgram> list2 = recent;
        int i14 = i13;
        long j11 = this.$windowEnd$inlined;
        IptvProgram iptvProgram3 = iptvProgram;
        long jGuidePanel$lambda$2 = TvScreenKt.GuidePanel$lambda$2(this.$now$delegate$inlined);
        float f10 = this.$nowRatio$inlined;
        boolean z12 = this.$isMobile$inlined;
        boolean zChanged2 = composer.changed(this.$onChannelClick$inlined) | ((((i14 & 112) ^ 48) > 32 && composer.changed(i10)) || (i14 & 48) == 32);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onChannelClick$inlined;
            objRememberedValue2 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$GuidePanel$3$2$1$3$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6340invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6340invoke() {
                    lVar.invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        TvScreenKt.GuideChannelRow(iptvChannel, list2, iptvProgram3, list, z, z5, zContains, z10, j10, j11, jGuidePanel$lambda$2, f10, z12, (r7.a) objRememberedValue2, composer, 0, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
