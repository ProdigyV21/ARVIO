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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_school", "Landroidx/compose/ui/graphics/vector/ImageVector;", "School", "Landroidx/compose/material/icons/Icons$Rounded;", "getSchool", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SchoolKt {
    private static ImageVector _school;

    public static final ImageVector getSchool(Icons.Rounded rounded) {
        ImageVector imageVector = _school;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.School", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(5.0f, 13.18f, 2.81f);
        pathBuilderI.curveToRelative(0.0f, 0.73f, 0.4f, 1.41f, 1.04f, 1.76f);
        pathBuilderI.lineToRelative(5.0f, 2.73f);
        pathBuilderI.curveToRelative(0.6f, 0.33f, 1.32f, 0.33f, 1.92f, 0.0f);
        pathBuilderI.lineToRelative(5.0f, -2.73f);
        pathBuilderI.curveToRelative(0.64f, -0.35f, 1.04f, -1.03f, 1.04f, -1.76f);
        pathBuilderI.verticalLineToRelative(-2.81f);
        pathBuilderI.lineToRelative(-6.04f, 3.3f);
        pathBuilderI.curveToRelative(-0.6f, 0.33f, -1.32f, 0.33f, -1.92f, 0.0f);
        c.z(pathBuilderI, 5.0f, 13.18f, 11.04f, 3.52f);
        pathBuilderI.lineToRelative(-8.43f, 4.6f);
        pathBuilderI.curveToRelative(-0.69f, 0.38f, -0.69f, 1.38f, 0.0f, 1.76f);
        pathBuilderI.lineToRelative(8.43f, 4.6f);
        pathBuilderI.curveToRelative(0.6f, 0.33f, 1.32f, 0.33f, 1.92f, 0.0f);
        pathBuilderI.lineTo(21.0f, 10.09f);
        pathBuilderI.lineTo(21.0f, 16.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.lineTo(23.0f, 9.59f);
        pathBuilderI.curveToRelative(0.0f, -0.37f, -0.2f, -0.7f, -0.52f, -0.88f);
        pathBuilderI.lineToRelative(-9.52f, -5.19f);
        pathBuilderI.curveToRelative(-0.6f, -0.32f, -1.32f, -0.32f, -1.92f, 0.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _school = imageVectorBuild;
        return imageVectorBuild;
    }
}
