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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_faceRetouchingNatural", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FaceRetouchingNatural", "Landroidx/compose/material/icons/Icons$Rounded;", "getFaceRetouchingNatural", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FaceRetouchingNaturalKt {
    private static ImageVector _faceRetouchingNatural;

    public static final ImageVector getFaceRetouchingNatural(Icons.Rounded rounded) {
        ImageVector imageVector = _faceRetouchingNatural;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FaceRetouchingNatural", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.01f, 4.05f, 20.6f, 3.4f);
        pathBuilderJ.lineToRelative(-0.65f, -1.41f);
        pathBuilderJ.curveToRelative(-0.18f, -0.39f, -0.73f, -0.39f, -0.91f, 0.0f);
        pathBuilderJ.lineTo(18.4f, 3.4f);
        pathBuilderJ.lineToRelative(-1.41f, 0.65f);
        pathBuilderJ.curveToRelative(-0.39f, 0.18f, -0.39f, 0.73f, 0.0f, 0.91f);
        pathBuilderJ.lineTo(18.4f, 5.6f);
        pathBuilderJ.lineToRelative(0.65f, 1.41f);
        pathBuilderJ.curveToRelative(0.18f, 0.39f, 0.73f, 0.39f, 0.91f, 0.0f);
        pathBuilderJ.lineTo(20.6f, 5.6f);
        pathBuilderJ.lineToRelative(1.41f, -0.65f);
        pathBuilderJ.curveTo(22.4f, 4.78f, 22.4f, 4.22f, 22.01f, 4.05f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(9.0f, 13.0f, -1.25f, 0.0f);
        pathBuilderB.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilderB.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = a.b(15.0f, 13.0f, -1.25f, 0.0f);
        pathBuilderB2.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilderB2.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.5f, 8.8f);
        pathBuilderA.curveToRelative(-0.78f, 0.0f, -1.49f, -0.46f, -1.82f, -1.17f);
        pathBuilderA.lineToRelative(-0.41f, -0.9f);
        pathBuilderA.lineToRelative(-0.9f, -0.41f);
        pathBuilderA.curveTo(15.66f, 5.99f, 15.2f, 5.28f, 15.2f, 4.5f);
        pathBuilderA.curveToRelative(0.0f, -0.66f, 0.34f, -1.26f, 0.87f, -1.63f);
        pathBuilderA.curveTo(14.83f, 2.32f, 13.45f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveToRelative(0.0f, -1.45f, -0.32f, -2.83f, -0.87f, -4.07f);
        pathBuilderA.curveTo(20.76f, 8.46f, 20.16f, 8.8f, 19.5f, 8.8f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.05f, 0.01f, -0.1f, 0.0f, -0.14f);
        pathBuilderA.curveToRelative(2.6f, -0.98f, 4.69f, -2.99f, 5.74f, -5.55f);
        pathBuilderA.curveTo(11.58f, 8.56f, 14.37f, 10.0f, 17.5f, 10.0f);
        pathBuilderA.curveToRelative(0.75f, 0.0f, 1.47f, -0.09f, 2.17f, -0.24f);
        pathBuilderA.curveTo(19.88f, 10.47f, 20.0f, 11.22f, 20.0f, 12.0f);
        pathBuilderA.curveTo(20.0f, 16.41f, 16.41f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _faceRetouchingNatural = imageVectorBuild;
        return imageVectorBuild;
    }
}
