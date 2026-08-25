package androidx.compose.ui.node;

import androidx.compose.runtime.CompositionLocalMap;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/ui/node/ComposeUiNode;", "Landroidx/compose/runtime/CompositionLocalMap;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/ComposeUiNode;Landroidx/compose/runtime/CompositionLocalMap;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ComposeUiNode$Companion$SetResolvedCompositionLocals$1 extends r implements p<ComposeUiNode, CompositionLocalMap, t0> {
    public static final ComposeUiNode$Companion$SetResolvedCompositionLocals$1 INSTANCE = new ComposeUiNode$Companion$SetResolvedCompositionLocals$1();

    public ComposeUiNode$Companion$SetResolvedCompositionLocals$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((ComposeUiNode) obj, (CompositionLocalMap) obj2);
        return t0.f22605a;
    }

    public final void invoke(ComposeUiNode composeUiNode, CompositionLocalMap compositionLocalMap) {
        composeUiNode.setCompositionLocalMap(compositionLocalMap);
    }
}
