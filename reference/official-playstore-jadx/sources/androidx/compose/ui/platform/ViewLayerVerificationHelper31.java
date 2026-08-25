package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.graphics.RenderEffect;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/ViewLayerVerificationHelper31;", "", "<init>", "()V", "Landroid/view/View;", "view", "Landroidx/compose/ui/graphics/RenderEffect;", "target", "Lx6/t0;", "setRenderEffect", "(Landroid/view/View;Landroidx/compose/ui/graphics/RenderEffect;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ViewLayerVerificationHelper31 {
    public static final ViewLayerVerificationHelper31 INSTANCE = new ViewLayerVerificationHelper31();

    private ViewLayerVerificationHelper31() {
    }

    public final void setRenderEffect(View view, RenderEffect target) {
        view.setRenderEffect(target != null ? target.asAndroidRenderEffect() : null);
    }
}
