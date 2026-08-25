package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_childFriendly", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ChildFriendly", "Landroidx/compose/material/icons/Icons$TwoTone;", "getChildFriendly", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChildFriendlyKt {
    private static ImageVector _childFriendly;

    public static final ImageVector getChildFriendly(Icons.TwoTone twoTone) {
        ImageVector imageVector = _childFriendly;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ChildFriendly", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(15.0f, 4.34f, 8.0f, 3.66f);
        pathBuilderA.curveTo(18.05f, 6.3f, 16.7f, 4.95f, 15.0f, 4.34f);
        a.z(pathBuilderA, 8.04f, 14.36f, 0.44f, 0.67f);
        pathBuilderA.curveToRelative(1.19f, 0.16f, 2.19f, 0.92f, 2.68f, 1.97f);
        pathBuilderA.horizontalLineToRelative(2.68f);
        pathBuilderA.curveToRelative(0.56f, -1.18f, 1.77f, -2.0f, 3.16f, -2.0f);
        pathBuilderA.curveToRelative(0.15f, 0.0f, 0.31f, 0.01f, 0.46f, 0.03f);
        pathBuilderA.lineToRelative(0.29f, -0.37f);
        pathBuilderA.curveToRelative(0.4f, -0.51f, 0.7f, -1.07f, 0.92f, -1.66f);
        pathBuilderA.horizontalLineTo(7.37f);
        pathBuilderA.curveToRelative(0.32f, 0.67f, 0.57f, 1.19f, 0.67f, 1.36f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = v.b.a(13.0f, 2.0f, 8.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, -4.42f, -3.58f, -8.0f, -8.0f, -8.0f);
        a.p(pathBuilderA2, 15.0f, 8.0f, 15.0f, 4.34f);
        pathBuilderA2.curveToRelative(1.7f, 0.6f, 3.05f, 1.95f, 3.66f, 3.66f);
        c.z(pathBuilderA2, 15.0f, 8.0f, 6.44f, 11.0f);
        pathBuilderA2.lineToRelative(-0.95f, -2.0f);
        a.h(pathBuilderA2, 2.0f, 9.0f, 2.0f, 2.22f);
        pathBuilderA2.reflectiveCurveToRelative(1.89f, 4.07f, 2.12f, 4.42f);
        pathBuilderA2.curveToRelative(-1.1f, 0.59f, -1.84f, 1.75f, -1.84f, 3.08f);
        pathBuilderA2.curveTo(4.5f, 20.43f, 6.07f, 22.0f, 8.0f, 22.0f);
        pathBuilderA2.curveToRelative(1.76f, 0.0f, 3.22f, -1.3f, 3.46f, -3.0f);
        pathBuilderA2.horizontalLineToRelative(2.08f);
        pathBuilderA2.curveToRelative(0.24f, 1.7f, 1.7f, 3.0f, 3.46f, 3.0f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA2.curveToRelative(0.0f, -1.04f, -0.46f, -1.97f, -1.18f, -2.61f);
        pathBuilderA2.curveTo(20.37f, 14.54f, 21.0f, 12.84f, 21.0f, 11.0f);
        c.z(pathBuilderA2, 6.44f, 11.0f, 8.0f, 20.0f);
        pathBuilderA2.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveTo(7.17f, 17.0f, 8.0f, 17.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveTo(8.83f, 20.0f, 8.0f, 20.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(17.0f, 20.0f);
        pathBuilderA2.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveTo(16.17f, 17.0f, 17.0f, 17.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA2.reflectiveCurveTo(17.83f, 20.0f, 17.0f, 20.0f);
        a.z(pathBuilderA2, 17.74f, 14.66f, -0.29f, 0.37f);
        pathBuilderA2.curveToRelative(-0.14f, -0.02f, -0.3f, -0.03f, -0.45f, -0.03f);
        pathBuilderA2.curveToRelative(-1.39f, 0.0f, -2.6f, 0.82f, -3.16f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(-2.68f);
        pathBuilderA2.curveToRelative(-0.5f, -1.04f, -1.5f, -1.8f, -2.68f, -1.97f);
        pathBuilderA2.lineToRelative(-0.44f, -0.67f);
        pathBuilderA2.curveToRelative(-0.1f, -0.17f, -0.34f, -0.69f, -0.67f, -1.36f);
        pathBuilderA2.horizontalLineToRelative(11.29f);
        pathBuilderA2.curveToRelative(-0.21f, 0.59f, -0.52f, 1.15f, -0.92f, 1.66f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _childFriendly = imageVectorBuild;
        return imageVectorBuild;
    }
}
