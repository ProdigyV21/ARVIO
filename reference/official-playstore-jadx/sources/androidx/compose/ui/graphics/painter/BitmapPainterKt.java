package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"BitmapPainter", "Landroidx/compose/ui/graphics/painter/BitmapPainter;", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "BitmapPainter-QZhYCtY", "(Landroidx/compose/ui/graphics/ImageBitmap;JJI)Landroidx/compose/ui/graphics/painter/BitmapPainter;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BitmapPainterKt {
    /* JADX INFO: renamed from: BitmapPainter-QZhYCtY, reason: not valid java name */
    public static final BitmapPainter m4012BitmapPainterQZhYCtY(ImageBitmap imageBitmap, long j10, long j11, int i10) {
        BitmapPainter bitmapPainter = new BitmapPainter(imageBitmap, j10, j11, null);
        bitmapPainter.m4011setFilterQualityvDHp3xo$ui_graphics_release(i10);
        return bitmapPainter;
    }

    /* JADX INFO: renamed from: BitmapPainter-QZhYCtY$default, reason: not valid java name */
    public static /* synthetic */ BitmapPainter m4013BitmapPainterQZhYCtY$default(ImageBitmap imageBitmap, long j10, long j11, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            j10 = IntOffset.INSTANCE.m5812getZeronOccac();
        }
        long j12 = j10;
        if ((i11 & 4) != 0) {
            j11 = IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight());
        }
        long j13 = j11;
        if ((i11 & 8) != 0) {
            i10 = FilterQuality.INSTANCE.m3572getLowfv9h1I();
        }
        return m4012BitmapPainterQZhYCtY(imageBitmap, j12, j13, i10);
    }
}
