package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_manageAccounts", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ManageAccounts", "Landroidx/compose/material/icons/Icons$Sharp;", "getManageAccounts", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ManageAccountsKt {
    private static ImageVector _manageAccounts;

    public static final ImageVector getManageAccounts(Icons.Sharp sharp) {
        ImageVector imageVector = _manageAccounts;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ManageAccounts", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.67f, 13.02f);
        pathBuilderA.curveTo(10.45f, 13.01f, 10.23f, 13.0f, 10.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.42f, 0.0f, -4.68f, 0.67f, -6.61f, 1.82f);
        pathBuilderA.curveTo(2.51f, 15.34f, 2.0f, 16.32f, 2.0f, 17.35f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.horizontalLineToRelative(9.26f);
        pathBuilderA.curveTo(10.47f, 18.87f, 10.0f, 17.49f, 10.0f, 16.0f);
        pathBuilderA.curveTo(10.0f, 14.93f, 10.25f, 13.93f, 10.67f, 13.02f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(10.0f, 8.0f, -4.0f, 0.0f);
        pathBuilderB.arcToRelative(4.0f, 4.0f, 0.0f, true, true, 8.0f, 0.0f);
        pathBuilderB.arcToRelative(4.0f, 4.0f, 0.0f, true, true, -8.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(20.75f, 16.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.22f, -0.03f, -0.42f, -0.06f, -0.63f);
        pathBuilderA2.lineToRelative(1.14f, -1.01f);
        pathBuilderA2.lineToRelative(-1.0f, -1.73f);
        pathBuilderA2.lineToRelative(-1.45f, 0.49f);
        pathBuilderA2.curveToRelative(-0.32f, -0.27f, -0.68f, -0.48f, -1.08f, -0.63f);
        pathBuilderA2.lineTo(18.0f, 11.0f);
        pathBuilderA2.horizontalLineToRelative(-2.0f);
        pathBuilderA2.lineToRelative(-0.3f, 1.49f);
        pathBuilderA2.curveToRelative(-0.4f, 0.15f, -0.76f, 0.36f, -1.08f, 0.63f);
        pathBuilderA2.lineToRelative(-1.45f, -0.49f);
        pathBuilderA2.lineToRelative(-1.0f, 1.73f);
        pathBuilderA2.lineToRelative(1.14f, 1.01f);
        pathBuilderA2.curveToRelative(-0.03f, 0.21f, -0.06f, 0.41f, -0.06f, 0.63f);
        pathBuilderA2.reflectiveCurveToRelative(0.03f, 0.42f, 0.06f, 0.63f);
        pathBuilderA2.lineToRelative(-1.14f, 1.01f);
        pathBuilderA2.lineToRelative(1.0f, 1.73f);
        pathBuilderA2.lineToRelative(1.45f, -0.49f);
        pathBuilderA2.curveToRelative(0.32f, 0.27f, 0.68f, 0.48f, 1.08f, 0.63f);
        pathBuilderA2.lineTo(16.0f, 21.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.lineToRelative(0.3f, -1.49f);
        pathBuilderA2.curveToRelative(0.4f, -0.15f, 0.76f, -0.36f, 1.08f, -0.63f);
        pathBuilderA2.lineToRelative(1.45f, 0.49f);
        pathBuilderA2.lineToRelative(1.0f, -1.73f);
        pathBuilderA2.lineToRelative(-1.14f, -1.01f);
        pathBuilderA2.curveTo(20.72f, 16.42f, 20.75f, 16.22f, 20.75f, 16.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(17.0f, 18.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA2, 18.1f, 18.0f, 17.0f, 18.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _manageAccounts = imageVectorBuild;
        return imageVectorBuild;
    }
}
