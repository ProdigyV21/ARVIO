package androidx.compose.material.icons.filled;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_attachEmail", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AttachEmail", "Landroidx/compose/material/icons/Icons$Filled;", "getAttachEmail", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AttachEmailKt {
    private static ImageVector _attachEmail;

    public static final ImageVector getAttachEmail(Icons.Filled filled) {
        ImageVector imageVector = _attachEmail;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AttachEmail", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(21.0f, 10.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        pathBuilderA.curveTo(1.9f, 2.0f, 1.01f, 2.9f, 1.01f, 4.0f);
        pathBuilderA.lineTo(1.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(11.0f);
        pathBuilderA.verticalLineToRelative(-5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        a.x(pathBuilderA, 21.0f, 11.0f, 11.0f);
        pathBuilderA.lineTo(3.0f, 6.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.lineToRelative(8.0f, 5.0f);
        pathBuilderA.lineToRelative(8.0f, -5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, 2.0f, 11.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(21.0f, 14.0f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderI.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderI.verticalLineToRelative(-4.5f);
        pathBuilderI.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderI.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        w.a.j(pathBuilderI, 18.0f, 2.0f, -4.5f);
        pathBuilderI.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderI.reflectiveCurveTo(15.0f, 12.12f, 15.0f, 13.5f);
        pathBuilderI.verticalLineTo(18.0f);
        pathBuilderI.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderI.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderI, -4.0f, 21.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _attachEmail = imageVectorBuild;
        return imageVectorBuild;
    }
}
