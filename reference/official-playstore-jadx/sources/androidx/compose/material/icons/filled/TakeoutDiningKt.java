package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_takeoutDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TakeoutDining", "Landroidx/compose/material/icons/Icons$Filled;", "getTakeoutDining", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TakeoutDiningKt {
    private static ImageVector _takeoutDining;

    public static final ImageVector getTakeoutDining(Icons.Filled filled) {
        ImageVector imageVector = _takeoutDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TakeoutDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(5.26f, 11.0f, 13.48f, -0.67f, 9.0f);
        a.w(pathBuilderG, 5.93f, 5.26f, 11.0f);
        pathBuilderG.moveTo(9.02f, 4.0f);
        pathBuilderG.horizontalLineToRelative(5.95f);
        pathBuilderG.lineTo(19.0f, 7.38f);
        pathBuilderG.lineToRelative(1.59f, -1.59f);
        pathBuilderG.lineTo(22.0f, 7.21f);
        pathBuilderG.lineTo(19.21f, 10.0f);
        pathBuilderG.horizontalLineTo(4.79f);
        pathBuilderG.lineTo(2.0f, 7.21f);
        pathBuilderG.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderG, 5.0f, 7.38f, 9.02f, 4.0f), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _takeoutDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
