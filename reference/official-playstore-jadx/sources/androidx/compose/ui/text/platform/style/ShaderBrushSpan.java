package androidx.compose.ui.text.platform.style;

import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.text.platform.AndroidTextPaint_androidKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R1\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00148F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006!"}, d2 = {"Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "Landroid/text/style/CharacterStyle;", "Landroid/text/style/UpdateAppearance;", "Landroidx/compose/ui/graphics/ShaderBrush;", "shaderBrush", "", "alpha", "<init>", "(Landroidx/compose/ui/graphics/ShaderBrush;F)V", "Landroid/text/TextPaint;", "textPaint", "Lx6/t0;", "updateDrawState", "(Landroid/text/TextPaint;)V", "Landroidx/compose/ui/graphics/ShaderBrush;", "getShaderBrush", "()Landroidx/compose/ui/graphics/ShaderBrush;", "F", "getAlpha", "()F", "Landroidx/compose/ui/geometry/Size;", "<set-?>", "size$delegate", "Landroidx/compose/runtime/MutableState;", "getSize-NH-jbRc", "()J", "setSize-uvyYCjk", "(J)V", ContentDisposition.Parameters.Size, "Landroidx/compose/runtime/State;", "Landroid/graphics/Shader;", "shaderState", "Landroidx/compose/runtime/State;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ShaderBrushSpan extends CharacterStyle implements UpdateAppearance {
    public static final int $stable = 0;
    private final float alpha;
    private final ShaderBrush shaderBrush;

    /* JADX INFO: renamed from: size$delegate, reason: from kotlin metadata */
    private final MutableState size = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m3293boximpl(Size.INSTANCE.m3313getUnspecifiedNHjbRc()), null, 2, null);
    private final State<Shader> shaderState = SnapshotStateKt.derivedStateOf(new ShaderBrushSpan$shaderState$1(this));

    public ShaderBrushSpan(ShaderBrush shaderBrush, float f10) {
        this.shaderBrush = shaderBrush;
        this.alpha = f10;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final ShaderBrush getShaderBrush() {
        return this.shaderBrush;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m5464getSizeNHjbRc() {
        return ((Size) this.size.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
    public final void m5465setSizeuvyYCjk(long j10) {
        this.size.setValue(Size.m3293boximpl(j10));
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        AndroidTextPaint_androidKt.setAlpha(textPaint, this.alpha);
        textPaint.setShader(this.shaderState.getValue());
    }
}
