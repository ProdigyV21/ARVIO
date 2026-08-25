package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "it", "", "invoke-x-9fifI"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$OffsetSaver$2 extends r implements l<Object, Offset> {
    public static final SaversKt$OffsetSaver$2 INSTANCE = new SaversKt$OffsetSaver$2();

    public SaversKt$OffsetSaver$2() {
        super(1);
    }

    @Override // r7.l
    /* JADX INFO: renamed from: invoke-x-9fifI, reason: not valid java name and merged with bridge method [inline-methods] */
    public final Offset invoke(Object obj) {
        if (p.a(obj, Boolean.FALSE)) {
            return Offset.m3225boximpl(Offset.INSTANCE.m3251getUnspecifiedF1C5BW0());
        }
        List list = (List) obj;
        Object obj2 = list.get(0);
        float fFloatValue = (obj2 != null ? (Float) obj2 : null).floatValue();
        Object obj3 = list.get(1);
        return Offset.m3225boximpl(OffsetKt.Offset(fFloatValue, (obj3 != null ? (Float) obj3 : null).floatValue()));
    }
}
