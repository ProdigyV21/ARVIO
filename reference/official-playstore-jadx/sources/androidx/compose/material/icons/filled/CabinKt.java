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
import v.a;
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cabin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cabin", "Landroidx/compose/material/icons/Icons$Filled;", "getCabin", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CabinKt {
    private static ImageVector _cabin;

    public static final ImageVector getCabin(Icons.Filled filled) {
        ImageVector imageVector = _cabin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Cabin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.curveTo(6.45f, 4.0f, 6.0f, 4.45f, 6.0f, 5.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        a.x(pathBuilderA, 10.0f, 12.0f, 3.0f);
        c.f(pathBuilderA, 6.0f, 7.58f, 6.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(3.11f);
        pathBuilderA.lineTo(1.0f, 11.4f);
        pathBuilderA.lineToRelative(1.21f, 1.59f);
        b.n(pathBuilderA, 4.0f, 11.62f, 21.0f, 16.0f);
        pathBuilderA.verticalLineToRelative(-9.38f);
        pathBuilderA.lineToRelative(1.79f, 1.36f);
        b.y(pathBuilderA, 23.0f, 11.4f, 12.0f, 3.0f);
        pathBuilderA.moveTo(13.94f, 7.0f);
        pathBuilderA.horizontalLineToRelative(-3.89f);
        b.y(pathBuilderA, 12.0f, 5.52f, 13.94f, 7.0f);
        pathBuilderA.moveTo(7.44f, 9.0f);
        a0.b.x(pathBuilderA, 9.12f, 18.0f, 10.1f, 11.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.verticalLineToRelative(-0.9f);
        pathBuilderA.lineTo(7.44f, 9.0f);
        pathBuilderA.close();
        c.u(pathBuilderA, 18.0f, 13.0f, 2.0f, 6.0f);
        e.y(pathBuilderA, -2.0f, 18.0f, 6.0f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.d(pathBuilderA, -2.0f, 12.0f, 2.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cabin = imageVectorBuild;
        return imageVectorBuild;
    }
}
