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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_group", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Group", "Landroidx/compose/material/icons/Icons$TwoTone;", "getGroup", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GroupKt {
    private static ImageVector _group;

    public static final ImageVector getGroup(Icons.TwoTone twoTone) {
        ImageVector imageVector = _group;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Group", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(9.0f, 8.5f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(4.34f, 17.0f, 9.32f);
        pathBuilderA.curveToRelative(-0.84f, -0.58f, -2.87f, -1.25f, -4.66f, -1.25f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -3.82f, 0.67f, -4.66f, 1.25f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(9.0f, 12.0f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA2.reflectiveCurveTo(10.93f, 5.0f, 9.0f, 5.0f);
        pathBuilderA2.reflectiveCurveTo(5.5f, 6.57f, 5.5f, 8.5f);
        pathBuilderA2.reflectiveCurveTo(7.07f, 12.0f, 9.0f, 12.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(9.0f, 7.0f);
        pathBuilderA2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveTo(9.83f, 10.0f, 9.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveTo(8.17f, 7.0f, 9.0f, 7.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(9.0f, 13.75f);
        pathBuilderA2.curveToRelative(-2.34f, 0.0f, -7.0f, 1.17f, -7.0f, 3.5f);
        b.A(pathBuilderA2, 2.0f, 19.0f, 14.0f, -1.75f);
        pathBuilderA2.curveToRelative(0.0f, -2.33f, -4.66f, -3.5f, -7.0f, -3.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(4.34f, 17.0f);
        pathBuilderA2.curveToRelative(0.84f, -0.58f, 2.87f, -1.25f, 4.66f, -1.25f);
        pathBuilderA2.reflectiveCurveToRelative(3.82f, 0.67f, 4.66f, 1.25f);
        c.z(pathBuilderA2, 4.34f, 17.0f, 16.04f, 13.81f);
        pathBuilderA2.curveToRelative(1.16f, 0.84f, 1.96f, 1.96f, 1.96f, 3.44f);
        b.A(pathBuilderA2, 18.0f, 19.0f, 4.0f, -1.75f);
        pathBuilderA2.curveToRelative(0.0f, -2.02f, -3.5f, -3.17f, -5.96f, -3.44f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(15.0f, 12.0f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA2.reflectiveCurveTo(16.93f, 5.0f, 15.0f, 5.0f);
        pathBuilderA2.curveToRelative(-0.54f, 0.0f, -1.04f, 0.13f, -1.5f, 0.35f);
        pathBuilderA2.curveToRelative(0.63f, 0.89f, 1.0f, 1.98f, 1.0f, 3.15f);
        pathBuilderA2.reflectiveCurveToRelative(-0.37f, 2.26f, -1.0f, 3.15f);
        pathBuilderA2.curveToRelative(0.46f, 0.22f, 0.96f, 0.35f, 1.5f, 0.35f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _group = imageVectorBuild;
        return imageVectorBuild;
    }
}
