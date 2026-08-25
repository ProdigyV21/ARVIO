package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.style.TextDecoration;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ int a(Paragraph paragraph, int i10, boolean z, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLineEnd");
        }
        if ((i11 & 2) != 0) {
            z = false;
        }
        return paragraph.getLineEnd(i10, z);
    }

    public static /* synthetic */ void b(Paragraph paragraph, Canvas canvas, long j10, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: paint-LG529CI");
        }
        paragraph.mo5045paintLG529CI(canvas, (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10, (i11 & 4) != 0 ? null : shadow, (i11 & 8) != 0 ? null : textDecoration, (i11 & 16) == 0 ? drawStyle : null, (i11 & 32) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }

    public static /* synthetic */ void c(Paragraph paragraph, Canvas canvas, long j10, Shadow shadow, TextDecoration textDecoration, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: paint-RPmYEkk");
        }
        if ((i10 & 2) != 0) {
            j10 = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }
        paragraph.mo5046paintRPmYEkk(canvas, j10, (i10 & 4) != 0 ? null : shadow, (i10 & 8) != 0 ? null : textDecoration);
    }

    public static /* synthetic */ void d(Paragraph paragraph, Canvas canvas, Brush brush, float f10, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: paint-hn5TExg");
        }
        if ((i11 & 4) != 0) {
            f10 = Float.NaN;
        }
        paragraph.mo5047painthn5TExg(canvas, brush, f10, (i11 & 8) != 0 ? null : shadow, (i11 & 16) != 0 ? null : textDecoration, (i11 & 32) != 0 ? null : drawStyle, (i11 & 64) != 0 ? DrawScope.INSTANCE.m3946getDefaultBlendMode0nO6VwU() : i10);
    }
}
