package androidx.compose.ui.viewinterop;

import android.view.ViewParent;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/node/Owner;", "owner", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/Owner;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidViewHolder$layoutNode$1$3 extends r implements l<Owner, t0> {
    final /* synthetic */ LayoutNode $layoutNode;
    final /* synthetic */ AndroidViewHolder $this_run;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidViewHolder$layoutNode$1$3(AndroidViewHolder androidViewHolder, LayoutNode layoutNode) {
        super(1);
        this.$this_run = androidViewHolder;
        this.$layoutNode = layoutNode;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Owner) obj);
        return t0.f22605a;
    }

    public final void invoke(Owner owner) {
        AndroidComposeView androidComposeView = owner instanceof AndroidComposeView ? (AndroidComposeView) owner : null;
        if (androidComposeView != null) {
            androidComposeView.addAndroidView(this.$this_run, this.$layoutNode);
        }
        ViewParent parent = this.$this_run.getView().getParent();
        AndroidViewHolder androidViewHolder = this.$this_run;
        if (parent != androidViewHolder) {
            androidViewHolder.addView(androidViewHolder.getView());
        }
    }
}
