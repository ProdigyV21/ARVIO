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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_updateDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "UpdateDisabled", "Landroidx/compose/material/icons/Icons$Outlined;", "getUpdateDisabled", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UpdateDisabledKt {
    private static ImageVector _updateDisabled;

    public static final ImageVector getUpdateDisabled(Icons.Outlined outlined) {
        ImageVector imageVector = _updateDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.UpdateDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.94f, 13.0f);
        pathBuilderA.curveToRelative(-0.15f, 1.38f, -0.62f, 2.67f, -1.33f, 3.79f);
        pathBuilderA.lineToRelative(-1.47f, -1.47f);
        pathBuilderA.curveToRelative(0.38f, -0.71f, 0.65f, -1.49f, 0.77f, -2.32f);
        a.x(pathBuilderA, 20.94f, 8.67f, 5.84f);
        pathBuilderA.curveTo(9.66f, 5.31f, 10.8f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(2.37f, 0.0f, 4.47f, 1.19f, 5.74f, 3.0f);
        a0.a.e(pathBuilderA, 15.0f, 2.0f, 6.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.verticalLineToRelative(2.36f);
        pathBuilderA.curveTo(17.35f, 4.32f, 14.83f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.curveToRelative(-1.76f, 0.0f, -3.4f, 0.51f, -4.78f, 1.39f);
        c.z(pathBuilderA, 8.67f, 5.84f, 11.0f, 7.0f);
        a0.a.l(pathBuilderA, 1.17f, 2.0f, 2.0f, 7.0f);
        a.x(pathBuilderA, 11.0f, 19.78f, 22.61f);
        pathBuilderA.lineToRelative(-3.0f, -3.0f);
        pathBuilderA.curveTo(15.39f, 20.48f, 13.76f, 21.0f, 12.0f, 21.0f);
        pathBuilderA.curveToRelative(-4.97f, 0.0f, -9.0f, -4.03f, -9.0f, -9.0f);
        pathBuilderA.curveToRelative(0.0f, -1.76f, 0.51f, -3.4f, 1.39f, -4.78f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderA, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderA.moveTo(15.32f, 18.15f);
        pathBuilderA.lineTo(5.84f, 8.67f);
        pathBuilderA.curveTo(5.31f, 9.66f, 5.0f, 10.8f, 5.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 3.86f, 3.14f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.curveTo(13.2f, 19.0f, 14.34f, 18.69f, 15.32f, 18.15f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _updateDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
