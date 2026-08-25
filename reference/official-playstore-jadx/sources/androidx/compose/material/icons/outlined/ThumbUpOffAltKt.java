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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbUpOffAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbUpOffAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getThumbUpOffAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbUpOffAltKt {
    private static ImageVector _thumbUpOffAlt;

    public static final ImageVector getThumbUpOffAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _thumbUpOffAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ThumbUpOffAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(9.0f, 21.0f, 9.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.54f, -0.5f, 1.84f, -1.22f);
        pathBuilderA.lineToRelative(3.02f, -7.05f);
        pathBuilderA.curveTo(22.95f, 12.5f, 23.0f, 12.26f, 23.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-6.31f);
        pathBuilderA.lineToRelative(0.95f, -4.57f);
        pathBuilderA.lineToRelative(0.03f, -0.32f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, -0.17f, -0.79f, -0.44f, -1.06f);
        pathBuilderA.lineTo(14.17f, 1.0f);
        pathBuilderA.lineTo(7.58f, 7.59f);
        pathBuilderA.curveTo(7.22f, 7.95f, 7.0f, 8.45f, 7.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveTo(7.0f, 20.1f, 7.9f, 21.0f, 9.0f, 21.0f);
        a.z(pathBuilderA, 9.0f, 9.0f, 4.34f, -4.34f);
        b.A(pathBuilderA, 12.0f, 10.0f, 9.0f, 2.0f);
        pathBuilderA.lineToRelative(-3.0f, 7.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.close();
        a.C(pathBuilderA, 1.0f, 9.0f, 4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderA, 1.0f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbUpOffAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
