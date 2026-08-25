package androidx.compose.material.icons.automirrored.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notListedLocation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotListedLocation", "Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;", "getNotListedLocation", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NotListedLocationKt {
    private static ImageVector _notListedLocation;

    public static final ImageVector getNotListedLocation(Icons.AutoMirrored.Filled filled) {
        ImageVector imageVector = _notListedLocation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Filled.NotListedLocation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(8.14f, 2.0f, 5.0f, 5.14f, 5.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 5.25f, 7.0f, 13.0f, 7.0f, 13.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, -7.75f, 7.0f, -13.0f);
        pathBuilderA.curveToRelative(0.0f, -3.86f, -3.14f, -7.0f, -7.0f, -7.0f);
        a.g(pathBuilderA, 12.88f, 15.75f, -1.75f);
        b.g(pathBuilderA, 11.13f, 14.0f, 1.75f, 1.75f);
        pathBuilderA.moveTo(12.88f, 12.88f);
        pathBuilderA.horizontalLineToRelative(-1.75f);
        pathBuilderA.curveToRelative(0.0f, -2.84f, 2.62f, -2.62f, 2.62f, -4.38f);
        pathBuilderA.curveToRelative(0.0f, -0.96f, -0.79f, -1.75f, -1.75f, -1.75f);
        pathBuilderA.reflectiveCurveToRelative(-1.75f, 0.79f, -1.75f, 1.75f);
        pathBuilderA.lineTo(8.5f, 8.5f);
        pathBuilderA.curveTo(8.5f, 6.57f, 10.07f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilderA.curveToRelative(0.0f, 2.19f, -2.62f, 2.41f, -2.62f, 4.38f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _notListedLocation = imageVectorBuild;
        return imageVectorBuild;
    }
}
