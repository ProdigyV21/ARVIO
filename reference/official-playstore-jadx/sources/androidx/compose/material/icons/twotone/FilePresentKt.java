package androidx.compose.material.icons.twotone;

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
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_filePresent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FilePresent", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFilePresent", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FilePresentKt {
    private static ImageVector _filePresent;

    public static final ImageVector getFilePresent(Icons.TwoTone twoTone) {
        ImageVector imageVector = _filePresent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FilePresent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(14.0f, 4.0f, 6.0f, 16.0f, 12.0f);
        f.k(pathBuilderH, 8.0f, -4.0f, 4.0f);
        pathBuilderH.moveTo(16.0f, 11.0f);
        pathBuilderH.verticalLineToRelative(4.0f);
        pathBuilderH.curveToRelative(0.0f, 2.21f, -1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderH.reflectiveCurveToRelative(-4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderH.verticalLineTo(9.5f);
        pathBuilderH.curveTo(8.0f, 8.12f, 9.12f, 7.0f, 10.5f, 7.0f);
        pathBuilderH.reflectiveCurveTo(13.0f, 8.12f, 13.0f, 9.5f);
        c.i(pathBuilderH, 15.0f, -2.0f, 9.5f);
        pathBuilderH.curveTo(11.0f, 9.22f, 10.78f, 9.0f, 10.5f, 9.0f);
        pathBuilderH.reflectiveCurveTo(10.0f, 9.22f, 10.0f, 9.5f);
        pathBuilderH.verticalLineTo(15.0f);
        pathBuilderH.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderH.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderH, -4.0f, 16.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.0f, 15.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.verticalLineTo(9.5f);
        pathBuilderA.curveTo(10.0f, 9.22f, 10.22f, 9.0f, 10.5f, 9.0f);
        pathBuilderA.reflectiveCurveTo(11.0f, 9.22f, 11.0f, 9.5f);
        c.i(pathBuilderA, 15.0f, 2.0f, 9.5f);
        pathBuilderA.curveTo(13.0f, 8.12f, 11.88f, 7.0f, 10.5f, 7.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 8.12f, 8.0f, 9.5f);
        pathBuilderA.verticalLineTo(15.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderA, -4.0f, -2.0f, 15.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(14.0f, 2.0f, 6.0f);
        pathBuilderQ.curveTo(4.9f, 2.0f, 4.0f, 2.9f, 4.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        c.x(pathBuilderQ, 8.0f, 14.0f, 2.0f);
        c.B(pathBuilderQ, 18.0f, 20.0f, 6.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.f(pathBuilderQ, 8.0f, 4.0f, 4.0f, 20.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _filePresent = imageVectorBuild;
        return imageVectorBuild;
    }
}
