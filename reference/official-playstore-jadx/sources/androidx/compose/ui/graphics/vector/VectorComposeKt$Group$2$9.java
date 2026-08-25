package androidx.compose.ui.graphics.vector;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/graphics/vector/GroupComponent;", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/vector/GroupComponent;Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class VectorComposeKt$Group$2$9 extends r implements p<GroupComponent, List<? extends PathNode>, t0> {
    public static final VectorComposeKt$Group$2$9 INSTANCE = new VectorComposeKt$Group$2$9();

    public VectorComposeKt$Group$2$9() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((GroupComponent) obj, (List<? extends PathNode>) obj2);
        return t0.f22605a;
    }

    public final void invoke(GroupComponent groupComponent, List<? extends PathNode> list) {
        groupComponent.setClipPathData(list);
    }
}
