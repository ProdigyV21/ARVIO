package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tornado", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tornado", "Landroidx/compose/material/icons/Icons$Outlined;", "getTornado", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TornadoKt {
    private static ImageVector _tornado;

    public static final ImageVector getTornado(Icons.Outlined outlined) {
        ImageVector imageVector = _tornado;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Tornado", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = b.b(23.0f, 3.0f, 1.0f, 11.0f, 19.0f);
        c.z(pathBuilderB, 23.0f, 3.0f, 19.53f, 5.0f);
        pathBuilderB.lineToRelative(-1.74f, 3.0f);
        pathBuilderB.horizontalLineTo(6.21f);
        pathBuilderB.lineTo(4.47f, 5.0f);
        pathBuilderB.horizontalLineTo(19.53f);
        pathBuilderB.close();
        pathBuilderB.moveTo(10.26f, 15.0f);
        pathBuilderB.horizontalLineToRelative(3.48f);
        b.y(pathBuilderB, 12.0f, 18.01f, 10.26f, 15.0f);
        pathBuilderB.moveTo(14.9f, 13.0f);
        a0.b.D(pathBuilderB, 9.1f, -1.74f, -3.0f, 9.27f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderB, 14.9f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tornado = imageVectorBuild;
        return imageVectorBuild;
    }
}
