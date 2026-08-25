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
import v.c;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cameraswitch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cameraswitch", "Landroidx/compose/material/icons/Icons$Outlined;", "getCameraswitch", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CameraswitchKt {
    private static ImageVector _cameraswitch;

    public static final ImageVector getCameraswitch(Icons.Outlined outlined) {
        ImageVector imageVector = _cameraswitch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Cameraswitch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(16.0f, 7.0f, -1.0f, -1.0f, -1.0f);
        a.f(pathBuilderG, -4.0f, 9.0f, 7.0f, 8.0f);
        pathBuilderG.curveTo(6.9f, 7.0f, 6.0f, 7.9f, 6.0f, 9.0f);
        pathBuilderG.verticalLineToRelative(6.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(8.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(9.0f);
        pathBuilderG.curveTo(18.0f, 7.9f, 17.1f, 7.0f, 16.0f, 7.0f);
        f.o(pathBuilderG, 16.0f, 15.0f, 8.0f, 9.0f);
        e.C(pathBuilderG, 1.83f, 1.0f, -1.0f, 2.34f);
        pathBuilderG.lineToRelative(1.0f, 1.0f);
        pathBuilderG.horizontalLineTo(16.0f);
        pathBuilderG.verticalLineTo(15.0f);
        pathBuilderG.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = v.a.b(12.0f, 12.0f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = v.a.j(8.57f, 0.52f, 13.05f, 5.0f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        pathBuilderJ.lineToRelative(-1.54f, -1.54f);
        pathBuilderJ.curveTo(17.7f, 2.46f, 21.53f, 6.24f, 22.0f, 11.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.curveTo(23.36f, 3.3f, 15.79f, -1.67f, 8.57f, 0.52f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(9.54f, 20.41f, 1.54f, 1.54f);
        pathBuilderR.curveTo(6.3f, 21.54f, 2.47f, 17.76f, 2.0f, 13.0f);
        pathBuilderR.horizontalLineTo(0.0f);
        pathBuilderR.curveToRelative(0.64f, 7.7f, 8.21f, 12.67f, 15.43f, 10.48f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderR, 10.95f, 19.0f, 9.54f, 20.41f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cameraswitch = imageVectorBuild;
        return imageVectorBuild;
    }
}
