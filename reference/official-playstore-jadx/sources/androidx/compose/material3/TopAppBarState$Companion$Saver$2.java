package androidx.compose.material3;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/material3/TopAppBarState;", "it", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TopAppBarState$Companion$Saver$2 extends r implements l<List<? extends Float>, TopAppBarState> {
    public static final TopAppBarState$Companion$Saver$2 INSTANCE = new TopAppBarState$Companion$Saver$2();

    public TopAppBarState$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public final TopAppBarState invoke(List<Float> list) {
        return new TopAppBarState(list.get(0).floatValue(), list.get(1).floatValue(), list.get(2).floatValue());
    }
}
