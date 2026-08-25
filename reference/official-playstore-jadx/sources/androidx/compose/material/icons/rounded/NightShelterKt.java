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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightShelter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightShelter", "Landroidx/compose/material/icons/Icons$Rounded;", "getNightShelter", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightShelterKt {
    private static ImageVector _nightShelter;

    public static final ImageVector getNightShelter(Icons.Rounded rounded) {
        ImageVector imageVector = _nightShelter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NightShelter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.8f, 3.9f, -6.0f, 4.5f);
        pathBuilderR.curveTo(4.3f, 8.78f, 4.0f, 9.37f, 4.0f, 10.0f);
        pathBuilderR.verticalLineToRelative(9.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(12.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-9.0f);
        pathBuilderR.curveToRelative(0.0f, -0.63f, -0.3f, -1.22f, -0.8f, -1.6f);
        pathBuilderR.lineToRelative(-6.0f, -4.5f);
        pathBuilderR.curveTo(12.49f, 3.37f, 11.51f, 3.37f, 10.8f, 3.9f);
        pathBuilderR.close();
        pathBuilderR.moveTo(9.75f, 12.5f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderR.reflectiveCurveTo(10.44f, 15.0f, 9.75f, 15.0f);
        pathBuilderR.reflectiveCurveTo(8.5f, 14.44f, 8.5f, 13.75f);
        pathBuilderR.reflectiveCurveTo(9.06f, 12.5f, 9.75f, 12.5f);
        a.p(pathBuilderR, 16.5f, 18.0f, 16.5f, 18.0f);
        pathBuilderR.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        y.a.g(pathBuilderR, -1.0f, 8.0f, 1.0f);
        pathBuilderR.curveTo(8.0f, 17.78f, 7.78f, 18.0f, 7.5f, 18.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveTo(7.22f, 18.0f, 7.0f, 17.78f, 7.0f, 17.5f);
        pathBuilderR.verticalLineToRelative(-6.0f);
        pathBuilderR.curveTo(7.0f, 11.22f, 7.22f, 11.0f, 7.5f, 11.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveTo(7.78f, 11.0f, 8.0f, 11.22f, 8.0f, 11.5f);
        e.l(pathBuilderR, 4.0f, 3.5f, -3.0f);
        pathBuilderR.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderR.horizontalLineToRelative(3.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(3.5f);
        pathBuilderR.curveTo(17.0f, 17.78f, 16.78f, 18.0f, 16.5f, 18.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightShelter = imageVectorBuild;
        return imageVectorBuild;
    }
}
