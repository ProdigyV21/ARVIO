package androidx.compose.foundation.pager;

import androidx.compose.runtime.saveable.SaverScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/foundation/pager/PagerStateImpl;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PagerStateImpl$Companion$Saver$1 extends r implements p<SaverScope, PagerStateImpl, List<? extends Object>> {
    public static final PagerStateImpl$Companion$Saver$1 INSTANCE = new PagerStateImpl$Companion$Saver$1();

    public PagerStateImpl$Companion$Saver$1() {
        super(2);
    }

    @Override // r7.p
    public final List<Object> invoke(SaverScope saverScope, PagerStateImpl pagerStateImpl) {
        return t7.a.E(Integer.valueOf(pagerStateImpl.getCurrentPage()), Float.valueOf(pagerStateImpl.getCurrentPageOffsetFraction()), Integer.valueOf(pagerStateImpl.getPageCount()));
    }
}
