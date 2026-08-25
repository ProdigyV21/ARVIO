package androidx.compose.foundation;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "Lx6/t0;", "invoke-ozmzZPI", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidEdgeEffectOverscrollEffect$onNewSize$1 extends r implements l<IntSize, t0> {
    final /* synthetic */ AndroidEdgeEffectOverscrollEffect this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidEdgeEffectOverscrollEffect$onNewSize$1(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect) {
        super(1);
        this.this$0 = androidEdgeEffectOverscrollEffect;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m161invokeozmzZPI(((IntSize) obj).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
    public final void m161invokeozmzZPI(long j10) {
        boolean zM3301equalsimpl0 = Size.m3301equalsimpl0(IntSizeKt.m5854toSizeozmzZPI(j10), this.this$0.containerSize);
        this.this$0.containerSize = IntSizeKt.m5854toSizeozmzZPI(j10);
        if (!zM3301equalsimpl0) {
            this.this$0.topEffect.setSize(IntSize.m5844getWidthimpl(j10), IntSize.m5843getHeightimpl(j10));
            this.this$0.bottomEffect.setSize(IntSize.m5844getWidthimpl(j10), IntSize.m5843getHeightimpl(j10));
            this.this$0.leftEffect.setSize(IntSize.m5843getHeightimpl(j10), IntSize.m5844getWidthimpl(j10));
            this.this$0.rightEffect.setSize(IntSize.m5843getHeightimpl(j10), IntSize.m5844getWidthimpl(j10));
            this.this$0.topEffectNegation.setSize(IntSize.m5844getWidthimpl(j10), IntSize.m5843getHeightimpl(j10));
            this.this$0.bottomEffectNegation.setSize(IntSize.m5844getWidthimpl(j10), IntSize.m5843getHeightimpl(j10));
            this.this$0.leftEffectNegation.setSize(IntSize.m5843getHeightimpl(j10), IntSize.m5844getWidthimpl(j10));
            this.this$0.rightEffectNegation.setSize(IntSize.m5843getHeightimpl(j10), IntSize.m5844getWidthimpl(j10));
        }
        if (zM3301equalsimpl0) {
            return;
        }
        this.this$0.invalidateOverscroll();
        this.this$0.animateToRelease();
    }
}
