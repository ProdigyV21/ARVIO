package androidx.leanback.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* JADX INFO: loaded from: classes3.dex */
public final class l1 extends ReplacementSpan {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3099i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f3100l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m1 f3101m;

    public l1(m1 m1Var, int i10, int i11) {
        this.f3101m = m1Var;
        this.f3099i = i10;
        this.f3100l = i11;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i10, int i11, float f10, int i12, int i13, int i14, Paint paint) {
        int iMeasureText = (int) paint.measureText(charSequence, i10, i11);
        m1 m1Var = this.f3101m;
        int width = m1Var.f3105l.getWidth();
        int i15 = width * 2;
        int i16 = iMeasureText / i15;
        int i17 = (iMeasureText % i15) / 2;
        boolean z = 1 == m1Var.getLayoutDirection();
        m1Var.f3104i.setSeed(this.f3099i);
        int alpha = paint.getAlpha();
        for (int i18 = 0; i18 < i16 && this.f3100l + i18 < m1Var.f3107n; i18++) {
            float f11 = (width / 2) + (i18 * i15) + i17;
            float f12 = z ? ((f10 + iMeasureText) - f11) - width : f10 + f11;
            paint.setAlpha((m1Var.f3104i.nextInt(4) + 1) * 63);
            if (m1Var.f3104i.nextBoolean()) {
                canvas.drawBitmap(m1Var.f3106m, f12, i13 - r13.getHeight(), paint);
            } else {
                canvas.drawBitmap(m1Var.f3105l, f12, i13 - r13.getHeight(), paint);
            }
        }
        paint.setAlpha(alpha);
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i10, int i11, Paint.FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(charSequence, i10, i11);
    }
}
