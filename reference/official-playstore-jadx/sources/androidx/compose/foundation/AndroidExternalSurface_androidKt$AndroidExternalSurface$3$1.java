package androidx.compose.foundation;

import android.view.SurfaceView;
import androidx.compose.foundation.AndroidExternalSurfaceZOrder;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/view/SurfaceView;", "view", "Lx6/t0;", "invoke", "(Landroid/view/SurfaceView;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1 extends r implements l<SurfaceView, t0> {
    final /* synthetic */ boolean $isOpaque;
    final /* synthetic */ boolean $isSecure;
    final /* synthetic */ long $surfaceSize;
    final /* synthetic */ int $zOrder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidExternalSurface_androidKt$AndroidExternalSurface$3$1(long j10, boolean z, int i10, boolean z5) {
        super(1);
        this.$surfaceSize = j10;
        this.$isOpaque = z;
        this.$zOrder = i10;
        this.$isSecure = z5;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SurfaceView) obj);
        return t0.f22605a;
    }

    public final void invoke(SurfaceView surfaceView) {
        if (IntSize.m5842equalsimpl0(this.$surfaceSize, IntSize.INSTANCE.m5849getZeroYbymL2g())) {
            surfaceView.getHolder().setSizeFromLayout();
        } else {
            surfaceView.getHolder().setFixedSize(IntSize.m5844getWidthimpl(this.$surfaceSize), IntSize.m5843getHeightimpl(this.$surfaceSize));
        }
        surfaceView.getHolder().setFormat(this.$isOpaque ? -1 : -3);
        int i10 = this.$zOrder;
        AndroidExternalSurfaceZOrder.Companion companion = AndroidExternalSurfaceZOrder.INSTANCE;
        if (AndroidExternalSurfaceZOrder.m167equalsimpl0(i10, companion.m171getBehindB_4ceCc())) {
            surfaceView.setZOrderOnTop(false);
        } else if (AndroidExternalSurfaceZOrder.m167equalsimpl0(i10, companion.m172getMediaOverlayB_4ceCc())) {
            surfaceView.setZOrderMediaOverlay(true);
        } else if (AndroidExternalSurfaceZOrder.m167equalsimpl0(i10, companion.m173getOnTopB_4ceCc())) {
            surfaceView.setZOrderOnTop(true);
        }
        surfaceView.setSecure(this.$isSecure);
    }
}
