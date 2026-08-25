package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_houseSiding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HouseSiding", "Landroidx/compose/material/icons/Icons$Rounded;", "getHouseSiding", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HouseSidingKt {
    private static ImageVector _houseSiding;

    public static final ImageVector getHouseSiding(Icons.Rounded rounded) {
        ImageVector imageVector = _houseSiding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HouseSiding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(19.0f, 12.0f, 1.7f);
        pathBuilderA.curveToRelative(0.46f, 0.0f, 0.68f, -0.57f, 0.33f, -0.87f);
        pathBuilderA.lineTo(12.67f, 3.6f);
        pathBuilderA.curveToRelative(-0.38f, -0.34f, -0.96f, -0.34f, -1.34f, 0.0f);
        pathBuilderA.lineToRelative(-8.36f, 7.53f);
        pathBuilderA.curveTo(2.63f, 11.43f, 2.84f, 12.0f, 3.3f, 12.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        e.l(pathBuilderA, -1.0f, 10.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        a.D(pathBuilderA, 12.0f, 7.21f, 10.0f, 9.58f);
        c.f(pathBuilderA, 17.0f, 10.19f, 12.0f, 7.0f);
        y.a.n(pathBuilderA, -1.81f, 7.21f, 10.0f);
        pathBuilderA.moveTo(14.57f, 8.0f);
        pathBuilderA.horizontalLineTo(9.43f);
        b.y(pathBuilderA, 12.0f, 5.69f, 14.57f, 8.0f);
        c.m(pathBuilderA, 7.0f, 16.0f, -2.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderA, 2.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _houseSiding = imageVectorBuild;
        return imageVectorBuild;
    }
}
