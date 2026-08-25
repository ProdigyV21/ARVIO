package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import q7.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/text/android/Paint29;", "", "<init>", "()V", "Landroid/graphics/Paint;", "paint", "", "text", "", TtmlNode.START, TtmlNode.END, "Landroid/graphics/Rect;", "rect", "Lx6/t0;", "getTextBounds", "(Landroid/graphics/Paint;Ljava/lang/CharSequence;IILandroid/graphics/Rect;)V", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class Paint29 {
    public static final Paint29 INSTANCE = new Paint29();

    private Paint29() {
    }

    @n
    public static final void getTextBounds(Paint paint, CharSequence text, int start, int end, Rect rect) {
        paint.getTextBounds(text, start, end, rect);
    }
}
