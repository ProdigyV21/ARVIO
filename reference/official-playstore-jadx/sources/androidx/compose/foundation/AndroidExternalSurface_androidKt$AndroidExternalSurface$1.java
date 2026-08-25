package androidx.compose.foundation;

import android.content.Context;
import android.view.SurfaceView;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroid/view/SurfaceView;", "context", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidExternalSurface_androidKt$AndroidExternalSurface$1 extends r implements l<Context, SurfaceView> {
    final /* synthetic */ l<AndroidExternalSurfaceScope, t0> $onInit;
    final /* synthetic */ AndroidExternalSurfaceState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AndroidExternalSurface_androidKt$AndroidExternalSurface$1(l<? super AndroidExternalSurfaceScope, t0> lVar, AndroidExternalSurfaceState androidExternalSurfaceState) {
        super(1);
        this.$onInit = lVar;
        this.$state = androidExternalSurfaceState;
    }

    @Override // r7.l
    public final SurfaceView invoke(Context context) {
        SurfaceView surfaceView = new SurfaceView(context);
        l<AndroidExternalSurfaceScope, t0> lVar = this.$onInit;
        AndroidExternalSurfaceState androidExternalSurfaceState = this.$state;
        lVar.invoke(androidExternalSurfaceState);
        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
        return surfaceView;
    }
}
