package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a3\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/tv/foundation/lazy/list/m0;", "state", "", "beyondBoundsItemCount", "", "reverseLayout", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "lazyListBeyondBoundsModifier", "(Landroidx/compose/ui/Modifier;Landroidx/tv/foundation/lazy/list/m0;IZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyBeyondBoundsModifierKt {
    public static final List a(LazyLayoutItemProvider lazyLayoutItemProvider, LazyLayoutPinnedItemList lazyLayoutPinnedItemList, i2.i iVar) {
        x7.i iVar2;
        int index;
        MutableVector mutableVector = iVar.f16052a;
        if (!mutableVector.isNotEmpty() && lazyLayoutPinnedItemList.isEmpty()) {
            return kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        if (mutableVector.isNotEmpty()) {
            int i10 = ((i2.h) mutableVector.first()).f16050a;
            int size = mutableVector.getSize();
            if (size > 0) {
                Object[] content = mutableVector.getContent();
                int i11 = 0;
                do {
                    int i12 = ((i2.h) content[i11]).f16050a;
                    if (i12 < i10) {
                        i10 = i12;
                    }
                    i11++;
                } while (i11 < size);
            }
            if (i10 < 0) {
                throw new IllegalArgumentException("negative minIndex");
            }
            int i13 = ((i2.h) mutableVector.first()).f16051b;
            int size2 = mutableVector.getSize();
            if (size2 > 0) {
                Object[] content2 = mutableVector.getContent();
                int i14 = 0;
                do {
                    int i15 = ((i2.h) content2[i14]).f16051b;
                    if (i15 > i13) {
                        i13 = i15;
                    }
                    i14++;
                } while (i14 < size2);
            }
            iVar2 = new x7.i(i10, Math.min(i13, lazyLayoutItemProvider.getItemCount() - 1), 1);
        } else {
            iVar2 = x7.i.f22626n;
        }
        int size3 = lazyLayoutPinnedItemList.size();
        for (int i16 = 0; i16 < size3; i16++) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = lazyLayoutPinnedItemList.get(i16);
            Object key = pinnedItem.getKey();
            int index2 = pinnedItem.getIndex();
            if (key != null && ((index2 >= lazyLayoutItemProvider.getItemCount() || !key.equals(lazyLayoutItemProvider.getKey(index2))) && (index = lazyLayoutItemProvider.getIndex(key)) != -1)) {
                index2 = index;
            }
            int i17 = iVar2.f22619i;
            if ((index2 > iVar2.f22620l || i17 > index2) && index2 >= 0 && index2 < lazyLayoutItemProvider.getItemCount()) {
                arrayList.add(Integer.valueOf(index2));
            }
        }
        int i18 = iVar2.f22619i;
        int i19 = iVar2.f22620l;
        if (i18 <= i19) {
            while (true) {
                arrayList.add(Integer.valueOf(i18));
                if (i18 == i19) {
                    break;
                }
                i18++;
            }
        }
        return arrayList;
    }

    public static final Modifier lazyListBeyondBoundsModifier(Modifier modifier, m0 m0Var, int i10, boolean z, Orientation orientation, Composer composer, int i11) {
        composer.startReplaceableGroup(-1188423612);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1188423612, i11, -1, "androidx.tv.foundation.lazy.list.lazyListBeyondBoundsModifier (LazyBeyondBoundsModifier.kt:57)");
        }
        LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
        composer.startReplaceableGroup(-892132083);
        boolean z5 = ((((i11 & 112) ^ 48) > 32 && composer.changed(m0Var)) || (i11 & 48) == 32) | ((((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(i10)) || (i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i(m0Var, i10);
            composer.updateRememberedValue(objRememberedValue);
        }
        i iVar = (i) objRememberedValue;
        composer.endReplaceableGroup();
        i2.i iVar2 = m0Var.f5443r;
        composer.startReplaceableGroup(-892125539);
        boolean zChanged = composer.changed(iVar) | composer.changed(iVar2) | ((((i11 & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i11 & 3072) == 2048) | composer.changed(layoutDirection) | ((((57344 & i11) ^ 24576) > 16384 && composer.changed(orientation)) || (i11 & 24576) == 16384);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object gVar = new g(iVar, iVar2, z, layoutDirection, orientation);
            composer.updateRememberedValue(gVar);
            objRememberedValue2 = gVar;
        }
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then((g) objRememberedValue2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }
}
