package androidx.compose.foundation.pager;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/pager/PagerStateImpl;", "it", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PagerStateImpl$Companion$Saver$2 extends r implements l<List, PagerStateImpl> {
    public static final PagerStateImpl$Companion$Saver$2 INSTANCE = new PagerStateImpl$Companion$Saver$2();

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerStateImpl$Companion$Saver$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<Integer> {
        final /* synthetic */ List<Object> $it;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List<? extends Object> list) {
            super(0);
            this.$it = list;
        }

        @Override // r7.a
        public final Integer invoke() {
            return (Integer) this.$it.get(2);
        }
    }

    public PagerStateImpl$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public final PagerStateImpl invoke(List<? extends Object> list) {
        return new PagerStateImpl(((Integer) list.get(0)).intValue(), ((Float) list.get(1)).floatValue(), new AnonymousClass1(list));
    }
}
