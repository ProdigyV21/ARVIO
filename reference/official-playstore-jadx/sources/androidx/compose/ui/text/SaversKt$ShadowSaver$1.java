package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/ui/graphics/Shadow;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$ShadowSaver$1 extends r implements p<SaverScope, Shadow, Object> {
    public static final SaversKt$ShadowSaver$1 INSTANCE = new SaversKt$ShadowSaver$1();

    public SaversKt$ShadowSaver$1() {
        super(2);
    }

    @Override // r7.p
    public final Object invoke(SaverScope saverScope, Shadow shadow) {
        return t7.a.a(SaversKt.save(Color.m3462boximpl(shadow.getColor()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(Offset.m3225boximpl(shadow.getOffset()), SaversKt.getSaver(Offset.INSTANCE), saverScope), SaversKt.save(Float.valueOf(shadow.getBlurRadius())));
    }
}
