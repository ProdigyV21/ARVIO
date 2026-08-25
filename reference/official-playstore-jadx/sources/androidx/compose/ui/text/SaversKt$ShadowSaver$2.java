package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/graphics/Shadow;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$ShadowSaver$2 extends r implements l<Object, Shadow> {
    public static final SaversKt$ShadowSaver$2 INSTANCE = new SaversKt$ShadowSaver$2();

    public SaversKt$ShadowSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final Shadow invoke(Object obj) {
        List list = (List) obj;
        Object obj2 = list.get(0);
        Saver<Color, Object> saver = SaversKt.getSaver(Color.INSTANCE);
        Boolean bool = Boolean.FALSE;
        long jM3482unboximpl = (((!p.a(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null).m3482unboximpl();
        Object obj3 = list.get(1);
        Saver<Offset, Object> saver2 = SaversKt.getSaver(Offset.INSTANCE);
        long packedValue = (((!p.a(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? saver2.restore(obj3) : null).getPackedValue();
        Object obj4 = list.get(2);
        return new Shadow(jM3482unboximpl, packedValue, (obj4 != null ? (Float) obj4 : null).floatValue(), null);
    }
}
