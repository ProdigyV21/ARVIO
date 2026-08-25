package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ(\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0013\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u00020\u00048\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Brush;", "<init>", "()V", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "createShader", "Landroidx/compose/ui/graphics/Paint;", TtmlNode.TAG_P, "", "alpha", "Lx6/t0;", "applyTo-Pq9zytI", "(JLandroidx/compose/ui/graphics/Paint;F)V", "applyTo", "internalShader", "Landroid/graphics/Shader;", "createdSize", "J", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ShaderBrush extends Brush {
    private long createdSize;
    private Shader internalShader;

    public ShaderBrush() {
        super(null);
        this.createdSize = Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: applyTo-Pq9zytI */
    public final void mo3425applyToPq9zytI(long size, Paint p10, float alpha) {
        Shader shaderMo3447createShaderuvyYCjk = this.internalShader;
        if (shaderMo3447createShaderuvyYCjk == null || !Size.m3301equalsimpl0(this.createdSize, size)) {
            if (Size.m3307isEmptyimpl(size)) {
                shaderMo3447createShaderuvyYCjk = null;
                this.internalShader = null;
                this.createdSize = Size.INSTANCE.m3313getUnspecifiedNHjbRc();
            } else {
                shaderMo3447createShaderuvyYCjk = mo3447createShaderuvyYCjk(size);
                this.internalShader = shaderMo3447createShaderuvyYCjk;
                this.createdSize = size;
            }
        }
        long jMo3351getColor0d7_KjU = p10.mo3351getColor0d7_KjU();
        Color.Companion companion = Color.INSTANCE;
        if (!Color.m3473equalsimpl0(jMo3351getColor0d7_KjU, companion.m3498getBlack0d7_KjU())) {
            p10.mo3357setColor8_81llA(companion.m3498getBlack0d7_KjU());
        }
        if (!p.a(p10.getShader(), shaderMo3447createShaderuvyYCjk)) {
            p10.setShader(shaderMo3447createShaderuvyYCjk);
        }
        if (p10.getAlpha() == alpha) {
            return;
        }
        p10.setAlpha(alpha);
    }

    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public abstract Shader mo3447createShaderuvyYCjk(long size);
}
