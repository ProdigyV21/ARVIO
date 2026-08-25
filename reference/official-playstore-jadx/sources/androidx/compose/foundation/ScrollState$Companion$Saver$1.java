package androidx.compose.foundation;

import androidx.compose.runtime.saveable.SaverScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/foundation/ScrollState;", "invoke", "(Landroidx/compose/runtime/saveable/SaverScope;Landroidx/compose/foundation/ScrollState;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ScrollState$Companion$Saver$1 extends r implements p<SaverScope, ScrollState, Integer> {
    public static final ScrollState$Companion$Saver$1 INSTANCE = new ScrollState$Companion$Saver$1();

    public ScrollState$Companion$Saver$1() {
        super(2);
    }

    @Override // r7.p
    public final Integer invoke(SaverScope saverScope, ScrollState scrollState) {
        return Integer.valueOf(scrollState.getValue());
    }
}
