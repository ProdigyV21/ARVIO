package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
class MediaRowFocusView extends View {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f2969i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final RectF f2970l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2971m;

    public MediaRowFocusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2970l = new RectF();
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.lb_playback_media_row_highlight_color));
        this.f2969i = paint;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        this.f2971m = height;
        int height2 = ((height * 2) - getHeight()) / 2;
        float f10 = -height2;
        float width = getWidth();
        float height3 = getHeight() + height2;
        RectF rectF = this.f2970l;
        rectF.set(0.0f, f10, width, height3);
        int i10 = this.f2971m;
        canvas.drawRoundRect(rectF, i10, i10, this.f2969i);
    }
}
