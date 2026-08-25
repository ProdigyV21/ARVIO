package androidx.compose.ui.text.platform;

import android.text.TextPaint;
import kotlin.Metadata;
import qb.d;
import t7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroid/text/TextPaint;", "", "alpha", "Lx6/t0;", "setAlpha", "(Landroid/text/TextPaint;F)V", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextPaint_androidKt {
    public static final void setAlpha(TextPaint textPaint, float f10) {
        if (Float.isNaN(f10)) {
            return;
        }
        textPaint.setAlpha(a.M(d.m(f10, 0.0f, 1.0f) * 255));
    }
}
