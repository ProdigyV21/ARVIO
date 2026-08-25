package androidx.compose.ui.platform;

import android.graphics.RenderNode;
import androidx.compose.ui.graphics.RenderEffect;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeApi29VerificationHelper;", "", "<init>", "()V", "Landroid/graphics/RenderNode;", "renderNode", "Landroidx/compose/ui/graphics/RenderEffect;", "target", "Lx6/t0;", "setRenderEffect", "(Landroid/graphics/RenderNode;Landroidx/compose/ui/graphics/RenderEffect;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class RenderNodeApi29VerificationHelper {
    public static final RenderNodeApi29VerificationHelper INSTANCE = new RenderNodeApi29VerificationHelper();

    private RenderNodeApi29VerificationHelper() {
    }

    public final void setRenderEffect(RenderNode renderNode, RenderEffect target) {
        renderNode.setRenderEffect(target != null ? target.asAndroidRenderEffect() : null);
    }
}
