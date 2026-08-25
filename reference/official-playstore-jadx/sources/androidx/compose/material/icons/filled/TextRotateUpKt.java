package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.c;
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_textRotateUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TextRotateUp", "Landroidx/compose/material/icons/Icons$Filled;", "getTextRotateUp", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRotateUpKt {
    private static ImageVector _textRotateUp;

    public static final ImageVector getTextRotateUp(Icons.Filled filled) {
        ImageVector imageVector = _textRotateUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TextRotateUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(3.0f, 12.0f, 1.5f, 11.0f, 4.75f);
        e.v(pathBuilderG, -2.1f, -2.2f, -0.9f, -5.0f);
        pathBuilderG.lineToRelative(2.2f, -0.9f);
        y.a.n(pathBuilderG, -2.1f, 3.0f, 12.0f);
        pathBuilderG.moveTo(10.0f, 14.62f);
        pathBuilderG.lineToRelative(-5.02f, -1.87f);
        d.f(pathBuilderG, 10.0f, 10.88f, 3.74f);
        pathBuilderG.moveTo(18.0f, 4.25f);
        a0.a.m(pathBuilderG, -3.0f, 3.0f, 2.0f, 12.5f);
        c.A(pathBuilderG, 2.0f, 19.0f, 7.25f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderG, -3.0f, -3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _textRotateUp = imageVectorBuild;
        return imageVectorBuild;
    }
}
