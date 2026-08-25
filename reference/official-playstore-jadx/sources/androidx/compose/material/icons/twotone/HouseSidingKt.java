package androidx.compose.material.icons.twotone;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_houseSiding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HouseSiding", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHouseSiding", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HouseSidingKt {
    private static ImageVector _houseSiding;

    public static final ImageVector getHouseSiding(Icons.TwoTone twoTone) {
        ImageVector imageVector = _houseSiding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HouseSiding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(7.21f, 10.0f, 9.58f, 17.0f, 10.19f);
        a.s(pathBuilderI, 12.0f, 7.0f, -1.81f);
        c.z(pathBuilderI, 7.21f, 10.0f, 14.57f, 8.0f);
        pathBuilderI.horizontalLineTo(9.43f);
        b.y(pathBuilderI, 12.0f, 5.69f, 14.57f, 8.0f);
        c.m(pathBuilderI, 7.0f, 16.0f, -2.0f, 10.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderI, 2.0f, 7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI2 = e.i(19.0f, 12.0f, 3.0f, 12.0f, 3.0f);
        b.A(pathBuilderI2, 2.0f, 12.0f, 3.0f, 8.0f);
        v.a.o(pathBuilderI2, 2.0f, -2.0f, 10.0f, 2.0f);
        c.D(pathBuilderI2, 2.0f, 12.0f, 7.21f, 10.0f);
        a0.b.x(pathBuilderI2, 9.58f, 17.0f, 10.19f, 12.0f);
        pathBuilderI2.horizontalLineTo(7.0f);
        pathBuilderI2.verticalLineToRelative(-1.81f);
        pathBuilderI2.lineTo(7.21f, 10.0f);
        pathBuilderI2.close();
        pathBuilderI2.moveTo(14.57f, 8.0f);
        pathBuilderI2.horizontalLineTo(9.43f);
        b.y(pathBuilderI2, 12.0f, 5.69f, 14.57f, 8.0f);
        c.m(pathBuilderI2, 7.0f, 16.0f, -2.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderI2, 2.0f, 7.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _houseSiding = imageVectorBuild;
        return imageVectorBuild;
    }
}
