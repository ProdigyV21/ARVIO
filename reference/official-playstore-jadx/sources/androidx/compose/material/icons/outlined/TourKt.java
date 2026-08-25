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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tour", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tour", "Landroidx/compose/material/icons/Icons$Outlined;", "getTour", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TourKt {
    private static ImageVector _tour;

    public static final ImageVector getTour(Icons.Outlined outlined) {
        ImageVector imageVector = _tour;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Tour", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(-8.0f);
        pathBuilder.horizontalLineToRelative(-1.0f);
        pathBuilder.horizontalLineTo(7.0f);
        pathBuilder.verticalLineTo(2.0f);
        pathBuilder.horizontalLineTo(5.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(10.0f);
        b.z(pathBuilder, 8.0f, 2.0f, -8.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(1.0f);
        pathBuilder.horizontalLineToRelative(9.0f);
        pathBuilder.lineToRelative(-2.0f, -5.0f);
        c.z(pathBuilder, 21.0f, 4.0f, 17.14f, 9.74f);
        pathBuilder.lineToRelative(0.9f, 2.26f);
        pathBuilder.horizontalLineTo(12.0f);
        pathBuilder.horizontalLineToRelative(-1.0f);
        pathBuilder.horizontalLineTo(7.0f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.horizontalLineToRelative(5.0f);
        pathBuilder.horizontalLineToRelative(1.0f);
        pathBuilder.horizontalLineToRelative(5.05f);
        pathBuilder.lineToRelative(-0.9f, 2.26f);
        b.y(pathBuilder, 16.85f, 9.0f, 17.14f, 9.74f);
        pathBuilder.moveTo(14.0f, 9.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilder, 14.0f, 7.9f, 14.0f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tour = imageVectorBuild;
        return imageVectorBuild;
    }
}
