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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsRemote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsRemote", "Landroidx/compose/material/icons/Icons$Rounded;", "getSettingsRemote", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsRemoteKt {
    private static ImageVector _settingsRemote;

    public static final ImageVector getSettingsRemote(Icons.Rounded rounded) {
        ImageVector imageVector = _settingsRemote;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SettingsRemote", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(15.0f, 9.0f, 9.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(6.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineTo(10.0f);
        pathBuilderQ.curveTo(16.0f, 9.45f, 15.55f, 9.0f, 15.0f, 9.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(12.0f, 14.25f);
        pathBuilderQ.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderQ.reflectiveCurveToRelative(0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderQ.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderQ, 12.69f, 14.25f, 12.0f, 14.25f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.82f, 6.82f, 7.82f, 6.82f);
        pathBuilderJ.curveToRelative(0.35f, 0.35f, 0.9f, 0.38f, 1.3f, 0.1f);
        pathBuilderJ.curveTo(9.93f, 6.34f, 10.93f, 6.0f, 12.0f, 6.0f);
        pathBuilderJ.curveToRelative(1.07f, 0.0f, 2.07f, 0.34f, 2.88f, 0.91f);
        pathBuilderJ.curveToRelative(0.4f, 0.28f, 0.95f, 0.26f, 1.3f, -0.09f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.43f, -0.43f, 0.39f, -1.15f, -0.09f, -1.5f);
        pathBuilderJ.curveTo(14.94f, 4.49f, 13.53f, 4.0f, 12.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.53f, 0.0f, -2.94f, 0.49f, -4.09f, 1.32f);
        pathBuilderJ.curveTo(7.42f, 5.67f, 7.39f, 6.39f, 7.82f, 6.82f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 0.0f);
        pathBuilderA.curveTo(9.36f, 0.0f, 6.94f, 0.93f, 5.05f, 2.47f);
        pathBuilderA.curveToRelative(-0.46f, 0.38f, -0.5f, 1.07f, -0.08f, 1.49f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.36f, 0.36f, 0.93f, 0.39f, 1.32f, 0.07f);
        pathBuilderA.curveTo(7.84f, 2.77f, 9.83f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveToRelative(2.17f, 0.0f, 4.16f, 0.77f, 5.7f, 2.04f);
        pathBuilderA.curveToRelative(0.39f, 0.32f, 0.96f, 0.29f, 1.32f, -0.07f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.42f, -0.42f, 0.38f, -1.11f, -0.08f, -1.49f);
        pathBuilderA.curveTo(17.06f, 0.93f, 14.64f, 0.0f, 12.0f, 0.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsRemote = imageVectorBuild;
        return imageVectorBuild;
    }
}
