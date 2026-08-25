package androidx.compose.material.icons.outlined;

import a0.a;
import a0.b;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sailing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sailing", "Landroidx/compose/material/icons/Icons$Outlined;", "getSailing", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SailingKt {
    private static ImageVector _sailing;

    public static final ImageVector getSailing(Icons.Outlined outlined) {
        ImageVector imageVector = _sailing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Sailing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderF = a.f(11.0f, 13.5f, 2.0f, 3.0f, 13.5f);
        w.a.s(pathBuilderF, 11.0f, 9.0f, 11.5f, 6.83f);
        b.s(pathBuilderF, 9.0f, 8.38f, 11.5f);
        pathBuilderF.moveTo(21.0f, 13.5f);
        pathBuilderF.curveTo(21.0f, 6.5f, 14.5f, 1.0f, 12.5f, 1.0f);
        pathBuilderF.curveToRelative(0.0f, 0.0f, 1.0f, 3.0f, 1.0f, 6.5f);
        pathBuilderF.reflectiveCurveToRelative(-1.0f, 6.0f, -1.0f, 6.0f);
        v.a.x(pathBuilderF, 21.0f, 15.38f, 5.24f);
        pathBuilderF.curveToRelative(1.42f, 1.52f, 2.88f, 3.72f, 3.41f, 6.26f);
        pathBuilderF.horizontalLineToRelative(-3.68f);
        pathBuilderF.curveToRelative(0.21f, -1.1f, 0.39f, -2.46f, 0.39f, -4.0f);
        pathBuilderF.curveTo(15.5f, 6.71f, 15.45f, 5.95f, 15.38f, 5.24f);
        w.a.r(pathBuilderF, 22.0f, 15.0f, 2.0f);
        pathBuilderF.curveToRelative(0.31f, 1.53f, 1.16f, 2.84f, 2.33f, 3.73f);
        pathBuilderF.curveTo(4.98f, 18.46f, 5.55f, 18.01f, 6.0f, 17.5f);
        pathBuilderF.curveTo(6.73f, 18.34f, 7.8f, 19.0f, 9.0f, 19.0f);
        pathBuilderF.reflectiveCurveToRelative(2.27f, -0.66f, 3.0f, -1.5f);
        pathBuilderF.curveToRelative(0.73f, 0.84f, 1.8f, 1.5f, 3.0f, 1.5f);
        pathBuilderF.reflectiveCurveToRelative(2.26f, -0.66f, 3.0f, -1.5f);
        pathBuilderF.curveToRelative(0.45f, 0.51f, 1.02f, 0.96f, 1.67f, 1.23f);
        pathBuilderF.curveTo(20.84f, 17.84f, 21.69f, 16.53f, 22.0f, 15.0f);
        c.y(pathBuilderF, 22.0f, 23.0f, -2.0f, -1.0f);
        pathBuilderF.curveToRelative(-1.04f, 0.0f, -2.08f, -0.35f, -3.0f, -1.0f);
        pathBuilderF.curveToRelative(-1.83f, 1.3f, -4.17f, 1.3f, -6.0f, 0.0f);
        pathBuilderF.curveToRelative(-1.83f, 1.3f, -4.17f, 1.3f, -6.0f, 0.0f);
        pathBuilderF.curveToRelative(-0.91f, 0.65f, -1.96f, 1.0f, -3.0f, 1.0f);
        b.D(pathBuilderF, 2.0f, 0.0f, 2.0f, 1.0f);
        pathBuilderF.curveToRelative(1.03f, 0.0f, 2.05f, -0.25f, 3.0f, -0.75f);
        pathBuilderF.curveToRelative(1.89f, 1.0f, 4.11f, 1.0f, 6.0f, 0.0f);
        pathBuilderF.curveToRelative(1.89f, 1.0f, 4.11f, 1.0f, 6.0f, 0.0f);
        pathBuilderF.horizontalLineToRelative(0.0f);
        pathBuilderF.curveToRelative(0.95f, 0.5f, 1.97f, 0.75f, 3.0f, 0.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderF, 22.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sailing = imageVectorBuild;
        return imageVectorBuild;
    }
}
