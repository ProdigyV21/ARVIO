package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/geometry/Offset;", "invoke-k-4lQ0M", "(J)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TransformableNode$updatedCanPan$1 extends r implements l<Offset, Boolean> {
    final /* synthetic */ TransformableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformableNode$updatedCanPan$1(TransformableNode transformableNode) {
        super(1);
        this.this$0 = transformableNode;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m392invokek4lQ0M(((Offset) obj).getPackedValue());
    }

    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
    public final Boolean m392invokek4lQ0M(long j10) {
        return (Boolean) this.this$0.canPan.invoke(Offset.m3225boximpl(j10));
    }
}
