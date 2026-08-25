package androidx.compose.ui.graphics.vector;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/ui/graphics/vector/GroupComponent;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/vector/GroupComponent;F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class VectorComposeKt$Group$2$6 extends r implements p<GroupComponent, Float, t0> {
    public static final VectorComposeKt$Group$2$6 INSTANCE = new VectorComposeKt$Group$2$6();

    public VectorComposeKt$Group$2$6() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((GroupComponent) obj, ((Number) obj2).floatValue());
        return t0.f22605a;
    }

    public final void invoke(GroupComponent groupComponent, float f10) {
        groupComponent.setScaleY(f10);
    }
}
