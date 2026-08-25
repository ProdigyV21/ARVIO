package androidx.compose.ui.graphics;

import android.graphics.Shader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¨\u0006\u0005"}, d2 = {"ShaderBrush", "Landroidx/compose/ui/graphics/ShaderBrush;", "shader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BrushKt {
    public static final ShaderBrush ShaderBrush(final Shader shader) {
        return new ShaderBrush() { // from class: androidx.compose.ui.graphics.BrushKt.ShaderBrush.1
            @Override // androidx.compose.ui.graphics.ShaderBrush
            /* JADX INFO: renamed from: createShader-uvyYCjk, reason: not valid java name */
            public Shader mo3447createShaderuvyYCjk(long size) {
                return shader;
            }
        };
    }
}
