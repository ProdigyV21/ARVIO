package androidx.compose.foundation;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.TextureView;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/view/TextureView;", "view", "Lx6/t0;", "invoke", "(Landroid/view/TextureView;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3 extends r implements l<TextureView, t0> {
    final /* synthetic */ boolean $isOpaque;
    final /* synthetic */ AndroidEmbeddedExternalSurfaceState $state;
    final /* synthetic */ long $surfaceSize;
    final /* synthetic */ float[] $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$3(long j10, AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState, boolean z, float[] fArr) {
        super(1);
        this.$surfaceSize = j10;
        this.$state = androidEmbeddedExternalSurfaceState;
        this.$isOpaque = z;
        this.$transform = fArr;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TextureView) obj);
        return t0.f22605a;
    }

    public final void invoke(TextureView textureView) {
        Matrix matrix;
        SurfaceTexture surfaceTexture;
        if (!IntSize.m5842equalsimpl0(this.$surfaceSize, IntSize.INSTANCE.m5849getZeroYbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
            surfaceTexture.setDefaultBufferSize(IntSize.m5844getWidthimpl(this.$surfaceSize), IntSize.m5843getHeightimpl(this.$surfaceSize));
        }
        this.$state.m163setSurfaceSizeozmzZPI(this.$surfaceSize);
        textureView.setOpaque(this.$isOpaque);
        float[] fArr = this.$transform;
        if (fArr != null) {
            matrix = this.$state.getMatrix();
            AndroidMatrixConversions_androidKt.m3348setFromEL8BTi8(matrix, fArr);
        } else {
            matrix = null;
        }
        textureView.setTransform(matrix);
    }
}
