package androidx.compose.material.icons.rounded;

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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rawOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RawOn", "Landroidx/compose/material/icons/Icons$Rounded;", "getRawOn", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RawOnKt {
    private static ImageVector _rawOn;

    public static final ImageVector getRawOn(Icons.Rounded rounded) {
        ImageVector imageVector = _rawOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RawOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(6.5f, 9.0f, 4.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(4.31f);
        pathBuilderQ.curveTo(3.0f, 14.69f, 3.31f, 15.0f, 3.69f, 15.0f);
        pathBuilderQ.horizontalLineToRelative(0.11f);
        pathBuilderQ.curveToRelative(0.38f, 0.0f, 0.69f, -0.31f, 0.69f, -0.69f);
        b.l(pathBuilderQ, 13.0f, 1.1f, 0.72f, 1.59f);
        pathBuilderQ.curveTo(6.43f, 14.84f, 6.68f, 15.0f, 6.95f, 15.0f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.5f, 0.0f, 0.83f, -0.51f, 0.64f, -0.97f);
        pathBuilderQ.lineTo(7.1f, 12.9f);
        pathBuilderQ.curveTo(7.6f, 12.6f, 8.0f, 12.1f, 8.0f, 11.5f);
        pathBuilderQ.verticalLineToRelative(-1.0f);
        pathBuilderQ.curveTo(8.0f, 9.68f, 7.32f, 9.0f, 6.5f, 9.0f);
        v.b.w(pathBuilderQ, 6.5f, 11.5f, -2.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderQ, 2.0f, 11.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.5f, 9.0f);
        pathBuilderA.curveToRelative(-0.73f, 0.0f, -1.37f, 0.5f, -1.55f, 1.21f);
        pathBuilderA.lineTo(8.98f, 14.1f);
        pathBuilderA.curveTo(8.86f, 14.56f, 9.21f, 15.0f, 9.68f, 15.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.33f, 0.0f, 0.62f, -0.23f, 0.7f, -0.55f);
        pathBuilderA.lineToRelative(0.24f, -0.95f);
        pathBuilderA.horizontalLineToRelative(1.75f);
        pathBuilderA.lineToRelative(0.23f, 0.95f);
        pathBuilderA.curveToRelative(0.08f, 0.32f, 0.37f, 0.55f, 0.71f, 0.55f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.47f, 0.0f, 0.82f, -0.44f, 0.71f, -0.9f);
        pathBuilderA.lineToRelative(-0.97f, -3.88f);
        pathBuilderA.curveTo(12.87f, 9.5f, 12.23f, 9.0f, 11.5f, 9.0f);
        c.z(pathBuilderA, 11.5f, 9.0f, 11.0f, 12.0f);
        pathBuilderA.lineToRelative(0.25f, -1.0f);
        pathBuilderA.horizontalLineToRelative(0.5f);
        pathBuilderA.lineTo(12.0f, 12.0f);
        pathBuilderA.horizontalLineTo(11.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.84f, 9.55f, 19.24f, 12.0f);
        pathBuilderJ.lineToRelative(-0.56f, -2.26f);
        pathBuilderJ.curveTo(18.58f, 9.3f, 18.19f, 9.0f, 17.74f, 9.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.45f, 0.0f, -0.84f, 0.3f, -0.94f, 0.74f);
        pathBuilderJ.lineTo(16.24f, 12.0f);
        pathBuilderJ.lineToRelative(-0.6f, -2.45f);
        pathBuilderJ.curveTo(15.56f, 9.23f, 15.27f, 9.0f, 14.93f, 9.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.47f, 0.0f, -0.82f, 0.44f, -0.71f, 0.9f);
        pathBuilderJ.lineToRelative(1.09f, 4.38f);
        pathBuilderJ.curveTo(15.43f, 14.7f, 15.81f, 15.0f, 16.24f, 15.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.43f, 0.0f, 0.81f, -0.3f, 0.92f, -0.72f);
        pathBuilderJ.lineToRelative(0.58f, -2.32f);
        pathBuilderJ.lineToRelative(0.58f, 2.32f);
        pathBuilderJ.curveTo(18.43f, 14.7f, 18.81f, 15.0f, 19.24f, 15.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.43f, 0.0f, 0.81f, -0.3f, 0.92f, -0.72f);
        pathBuilderJ.lineToRelative(1.09f, -4.38f);
        pathBuilderJ.curveTo(21.37f, 9.44f, 21.02f, 9.0f, 20.55f, 9.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveTo(20.21f, 9.0f, 19.92f, 9.23f, 19.84f, 9.55f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rawOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
