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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_markEmailUnread", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MarkEmailUnread", "Landroidx/compose/material/icons/Icons$Outlined;", "getMarkEmailUnread", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MarkEmailUnreadKt {
    private static ImageVector _markEmailUnread;

    public static final ImageVector getMarkEmailUnread(Icons.Outlined outlined) {
        ImageVector imageVector = _markEmailUnread;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MarkEmailUnread", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(22.0f, 8.98f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.lineTo(2.01f, 6.0f);
        pathBuilderA.curveTo(2.01f, 4.9f, 2.9f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(10.1f);
        pathBuilderA.curveTo(14.04f, 4.32f, 14.0f, 4.66f, 14.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(0.04f, 0.68f, 0.1f, 1.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.lineToRelative(8.0f, 5.0f);
        pathBuilderA.lineToRelative(3.67f, -2.29f);
        pathBuilderA.curveToRelative(0.47f, 0.43f, 1.02f, 0.76f, 1.63f, 0.98f);
        pathBuilderA.lineTo(12.0f, 13.0f);
        a.h(pathBuilderA, 4.0f, 8.0f, 10.0f, 16.0f);
        pathBuilderA.verticalLineTo(9.9f);
        pathBuilderA.curveTo(20.74f, 9.75f, 21.42f, 9.42f, 22.0f, 8.98f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 16.0f, 3.34f, 16.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _markEmailUnread = imageVectorBuild;
        return imageVectorBuild;
    }
}
