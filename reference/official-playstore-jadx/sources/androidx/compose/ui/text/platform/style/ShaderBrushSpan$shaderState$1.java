package androidx.compose.ui.text.platform.style;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ShaderBrushSpan$shaderState$1 extends r implements a<Shader> {
    final /* synthetic */ ShaderBrushSpan this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShaderBrushSpan$shaderState$1(ShaderBrushSpan shaderBrushSpan) {
        super(0);
        this.this$0 = shaderBrushSpan;
    }

    @Override // r7.a
    public final Shader invoke() {
        if (this.this$0.m5464getSizeNHjbRc() == Size.INSTANCE.m3313getUnspecifiedNHjbRc() || Size.m3307isEmptyimpl(this.this$0.m5464getSizeNHjbRc())) {
            return null;
        }
        return this.this$0.getShaderBrush().mo3447createShaderuvyYCjk(this.this$0.m5464getSizeNHjbRc());
    }
}
