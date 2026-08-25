package androidx.compose.ui.node;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/node/BackwardsCompatNode;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/BackwardsCompatNode;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 extends r implements l<BackwardsCompatNode, t0> {
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1();

    public BackwardsCompatNodeKt$onDrawCacheReadsChanged$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BackwardsCompatNode) obj);
        return t0.f22605a;
    }

    public final void invoke(BackwardsCompatNode backwardsCompatNode) {
        backwardsCompatNode.onDrawCacheReadsChanged$ui_release();
    }
}
