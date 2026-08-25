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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoFixNormal", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoFixNormal", "Landroidx/compose/material/icons/Icons$Outlined;", "getAutoFixNormal", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoFixNormalKt {
    private static ImageVector _autoFixNormal;

    public static final ImageVector getAutoFixNormal(Icons.Outlined outlined) {
        ImageVector imageVector = _autoFixNormal;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AutoFixNormal", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.0f, 7.0f, 0.94f, -2.06f);
        pathBuilderR.lineToRelative(2.06f, -0.94f);
        pathBuilderR.lineToRelative(-2.06f, -0.94f);
        pathBuilderR.lineToRelative(-0.94f, -2.06f);
        pathBuilderR.lineToRelative(-0.94f, 2.06f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -2.06f, 0.94f, 2.06f, 0.94f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(17.71f, 9.12f, -2.83f, -2.83f);
        pathBuilderR2.curveTo(14.68f, 6.1f, 14.43f, 6.0f, 14.17f, 6.0f);
        pathBuilderR2.curveToRelative(-0.26f, 0.0f, -0.51f, 0.1f, -0.71f, 0.29f);
        pathBuilderR2.lineTo(2.29f, 17.46f);
        pathBuilderR2.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR2.lineToRelative(2.83f, 2.83f);
        pathBuilderR2.curveTo(5.32f, 21.9f, 5.57f, 22.0f, 5.83f, 22.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.51f, -0.1f, 0.71f, -0.29f);
        pathBuilderR2.lineToRelative(11.17f, -11.17f);
        pathBuilderR2.curveTo(18.1f, 10.15f, 18.1f, 9.51f, 17.71f, 9.12f);
        a.z(pathBuilderR2, 14.17f, 8.42f, 1.41f, 1.41f);
        pathBuilderR2.lineTo(14.41f, 11.0f);
        b.y(pathBuilderR2, 13.0f, 9.59f, 14.17f, 8.42f);
        pathBuilderR2.moveTo(5.83f, 19.59f);
        pathBuilderR2.lineToRelative(-1.41f, -1.41f);
        pathBuilderR2.lineTo(11.59f, 11.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR2, 13.0f, 12.41f, 5.83f, 19.59f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autoFixNormal = imageVectorBuild;
        return imageVectorBuild;
    }
}
