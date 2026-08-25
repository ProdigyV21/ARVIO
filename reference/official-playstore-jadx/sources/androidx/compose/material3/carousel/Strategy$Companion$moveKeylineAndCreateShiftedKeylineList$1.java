package androidx.compose.material3.carousel;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/carousel/KeylineListScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Strategy$Companion$moveKeylineAndCreateShiftedKeylineList$1 extends r implements l<KeylineListScope, t0> {
    final /* synthetic */ int $dstIndex;
    final /* synthetic */ KeylineList $from;
    final /* synthetic */ int $srcIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Strategy$Companion$moveKeylineAndCreateShiftedKeylineList$1(KeylineList keylineList, int i10, int i11) {
        super(1);
        this.$from = keylineList;
        this.$srcIndex = i10;
        this.$dstIndex = i11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((KeylineListScope) obj);
        return t0.f22605a;
    }

    public final void invoke(KeylineListScope keylineListScope) {
        List listMove = Strategy.INSTANCE.move(new ArrayList(this.$from), this.$srcIndex, this.$dstIndex);
        int size = listMove.size();
        for (int i10 = 0; i10 < size; i10++) {
            Keyline keyline = (Keyline) listMove.get(i10);
            keylineListScope.add(keyline.getSize(), keyline.isAnchor());
        }
    }
}
