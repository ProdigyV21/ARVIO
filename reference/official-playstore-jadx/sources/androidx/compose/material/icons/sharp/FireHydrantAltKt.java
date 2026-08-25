package androidx.compose.material.icons.sharp;

import a0.a;
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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fireHydrantAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FireHydrantAlt", "Landroidx/compose/material/icons/Icons$Sharp;", "getFireHydrantAlt", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FireHydrantAltKt {
    private static ImageVector _fireHydrantAlt;

    public static final ImageVector getFireHydrantAlt(Icons.Sharp sharp) {
        ImageVector imageVector = _fireHydrantAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.FireHydrantAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(21.0f, 11.0f, -3.0f, 8.0f, 2.0f);
        pathBuilderU.verticalLineTo(6.0f);
        pathBuilderU.horizontalLineToRelative(-2.35f);
        pathBuilderU.curveTo(16.83f, 3.67f, 14.61f, 2.0f, 12.0f, 2.0f);
        pathBuilderU.reflectiveCurveTo(7.17f, 3.67f, 6.35f, 6.0f);
        b.q(pathBuilderU, 4.0f, 2.0f, 2.0f, 3.0f);
        b.q(pathBuilderU, 3.0f, 6.0f, 3.0f, 3.0f);
        b.q(pathBuilderU, 4.0f, 2.0f, 16.0f, -2.0f);
        f.w(pathBuilderU, -2.0f, -3.0f, 3.0f, 11.0f);
        pathBuilderU.moveTo(12.0f, 17.5f);
        pathBuilderU.curveToRelative(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilderU.reflectiveCurveToRelative(1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderU.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderU, 13.93f, 17.5f, 12.0f, 17.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = v.a.b(12.0f, 14.0f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fireHydrantAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
