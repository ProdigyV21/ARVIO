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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localFireDepartment", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalFireDepartment", "Landroidx/compose/material/icons/Icons$Outlined;", "getLocalFireDepartment", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFireDepartmentKt {
    private static ImageVector _localFireDepartment;

    public static final ImageVector getLocalFireDepartment(Icons.Outlined outlined) {
        ImageVector imageVector = _localFireDepartment;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.LocalFireDepartment", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.0f, 6.0f, -0.44f, 0.55f);
        pathBuilderR.curveToRelative(-0.42f, 0.52f, -0.98f, 0.75f, -1.54f, 0.75f);
        pathBuilderR.curveTo(13.0f, 7.3f, 12.0f, 6.52f, 12.0f, 5.3f);
        pathBuilderR.verticalLineTo(2.0f);
        pathBuilderR.curveToRelative(0.0f, 0.0f, -8.0f, 4.0f, -8.0f, 11.0f);
        pathBuilderR.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderR.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderR.curveTo(20.0f, 10.04f, 18.39f, 7.38f, 16.0f, 6.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 19.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.87f, -2.0f, -1.94f);
        pathBuilderR.curveToRelative(0.0f, -0.51f, 0.2f, -0.99f, 0.58f, -1.36f);
        pathBuilderR.lineToRelative(1.42f, -1.4f);
        pathBuilderR.lineToRelative(1.43f, 1.4f);
        pathBuilderR.curveTo(13.8f, 16.07f, 14.0f, 16.55f, 14.0f, 17.06f);
        pathBuilderR.curveTo(14.0f, 18.13f, 13.1f, 19.0f, 12.0f, 19.0f);
        a.p(pathBuilderR, 15.96f, 17.5f, 15.96f, 17.5f);
        pathBuilderR.curveToRelative(0.04f, -0.36f, 0.22f, -1.89f, -1.13f, -3.22f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.lineTo(12.0f, 11.5f);
        pathBuilderR.lineToRelative(-2.83f, 2.78f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(-1.36f, 1.34f, -1.17f, 2.88f, -1.13f, 3.22f);
        pathBuilderR.curveTo(6.79f, 16.4f, 6.0f, 14.79f, 6.0f, 13.0f);
        pathBuilderR.curveToRelative(0.0f, -3.16f, 2.13f, -5.65f, 4.03f, -7.25f);
        pathBuilderR.curveToRelative(0.23f, 1.99f, 1.93f, 3.55f, 3.99f, 3.55f);
        pathBuilderR.curveToRelative(0.78f, 0.0f, 1.54f, -0.23f, 2.18f, -0.66f);
        pathBuilderR.curveTo(17.34f, 9.78f, 18.0f, 11.35f, 18.0f, 13.0f);
        pathBuilderR.curveTo(18.0f, 14.79f, 17.21f, 16.4f, 15.96f, 17.5f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localFireDepartment = imageVectorBuild;
        return imageVectorBuild;
    }
}
