package androidx.compose.material.icons.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dry", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Dry", "Landroidx/compose/material/icons/Icons$Sharp;", "getDry", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DryKt {
    private static ImageVector _dry;

    public static final ImageVector getDry(Icons.Sharp sharp) {
        ImageVector imageVector = _dry;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Dry", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(1.0f, 12.68f, 23.0f, 18.0f, -2.5f);
        a.j(pathBuilderA, -7.0f, -1.0f, 9.0f, 17.0f);
        v.a.o(pathBuilderA, -9.0f, -1.0f, 10.0f, -2.5f);
        a.e(pathBuilderA, 12.0f, -1.0f, 8.0f, 10.0f);
        pathBuilderA.horizontalLineTo(8.86f);
        pathBuilderA.lineToRelative(1.88f, -3.3f);
        b.y(pathBuilderA, 9.12f, 5.0f, 1.0f, 12.68f);
        pathBuilderA.moveTo(15.65f, 4.86f);
        pathBuilderA.lineToRelative(-0.07f, -0.07f);
        pathBuilderA.curveToRelative(-0.57f, -0.62f, -0.82f, -1.41f, -0.67f, -2.2f);
        pathBuilderA.lineTo(15.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-1.89f);
        pathBuilderA.lineToRelative(-0.06f, 0.43f);
        pathBuilderA.curveToRelative(-0.2f, 1.36f, 0.27f, 2.71f, 1.3f, 3.72f);
        pathBuilderA.lineToRelative(0.07f, 0.06f);
        pathBuilderA.curveToRelative(0.57f, 0.62f, 0.82f, 1.41f, 0.67f, 2.2f);
        pathBuilderA.lineTo(14.98f, 9.0f);
        pathBuilderA.horizontalLineToRelative(1.91f);
        pathBuilderA.lineToRelative(0.06f, -0.43f);
        pathBuilderA.curveTo(17.16f, 7.21f, 16.68f, 5.86f, 15.65f, 4.86f);
        v.a.z(pathBuilderA, 19.65f, 4.86f, -0.07f, -0.07f);
        pathBuilderA.curveToRelative(-0.57f, -0.62f, -0.82f, -1.41f, -0.67f, -2.2f);
        pathBuilderA.lineTo(19.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-1.89f);
        pathBuilderA.lineToRelative(-0.06f, 0.43f);
        pathBuilderA.curveToRelative(-0.2f, 1.36f, 0.27f, 2.71f, 1.3f, 3.72f);
        pathBuilderA.lineToRelative(0.07f, 0.06f);
        pathBuilderA.curveToRelative(0.57f, 0.62f, 0.82f, 1.41f, 0.67f, 2.2f);
        pathBuilderA.lineTo(18.98f, 9.0f);
        pathBuilderA.horizontalLineToRelative(1.91f);
        pathBuilderA.lineToRelative(0.06f, -0.43f);
        pathBuilderA.curveTo(21.16f, 7.21f, 20.68f, 5.86f, 19.65f, 4.86f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dry = imageVectorBuild;
        return imageVectorBuild;
    }
}
