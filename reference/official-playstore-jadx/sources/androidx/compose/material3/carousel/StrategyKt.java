package androidx.compose.material3.carousel;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a0\u0010\u0000\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002¨\u0006\u000e"}, d2 = {"lerp", "Landroidx/compose/material3/carousel/Keyline;", TtmlNode.START, TtmlNode.END, "fraction", "", "Landroidx/compose/material3/carousel/KeylineList;", "from", "to", "outputMin", "outputMax", "inputMin", "inputMax", "value", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrategyKt {
    public static final Keyline lerp(Keyline keyline, Keyline keyline2, float f10) {
        return new Keyline(MathHelpersKt.lerp(keyline.getSize(), keyline2.getSize(), f10), MathHelpersKt.lerp(keyline.getOffset(), keyline2.getOffset(), f10), MathHelpersKt.lerp(keyline.getUnadjustedOffset(), keyline2.getUnadjustedOffset(), f10), f10 < 0.5f ? keyline.isFocal() : keyline2.isFocal(), f10 < 0.5f ? keyline.isAnchor() : keyline2.isAnchor(), f10 < 0.5f ? keyline.isPivot() : keyline2.isPivot(), MathHelpersKt.lerp(keyline.getCutoff(), keyline2.getCutoff(), f10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float lerp(float f10, float f11, float f12, float f13, float f14) {
        return f14 <= f12 ? f10 : f14 >= f13 ? f11 : MathHelpersKt.lerp(f10, f11, (f14 - f12) / (f13 - f12));
    }

    public static final KeylineList lerp(KeylineList keylineList, KeylineList keylineList2, float f10) {
        ArrayList arrayList = new ArrayList(keylineList.size());
        int size = keylineList.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(lerp(keylineList.get(i10), keylineList2.get(i10), f10));
        }
        return new KeylineList(arrayList);
    }
}
