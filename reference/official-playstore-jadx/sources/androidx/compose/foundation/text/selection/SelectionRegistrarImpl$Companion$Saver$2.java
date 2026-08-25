package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SelectionRegistrarImpl$Companion$Saver$2 extends r implements l<Long, SelectionRegistrarImpl> {
    public static final SelectionRegistrarImpl$Companion$Saver$2 INSTANCE = new SelectionRegistrarImpl$Companion$Saver$2();

    public SelectionRegistrarImpl$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).longValue());
    }

    public final SelectionRegistrarImpl invoke(long j10) {
        return new SelectionRegistrarImpl(j10, null);
    }
}
