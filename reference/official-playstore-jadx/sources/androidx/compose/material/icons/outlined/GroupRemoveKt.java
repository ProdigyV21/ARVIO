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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_groupRemove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GroupRemove", "Landroidx/compose/material/icons/Icons$Outlined;", "getGroupRemove", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GroupRemoveKt {
    private static ImageVector _groupRemove;

    public static final ImageVector getGroupRemove(Icons.Outlined outlined) {
        ImageVector imageVector = _groupRemove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.GroupRemove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(24.0f, 9.0f, 2.0f, -6.0f, 9.0f);
        v.a.x(pathBuilderQ, 24.0f, 8.0f, 4.0f);
        pathBuilderQ.curveTo(5.79f, 4.0f, 4.0f, 5.79f, 4.0f, 8.0f);
        pathBuilderQ.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderQ.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderQ.reflectiveCurveTo(10.21f, 4.0f, 8.0f, 4.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(8.0f, 10.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderQ.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderQ.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderQ.reflectiveCurveTo(9.1f, 10.0f, 8.0f, 10.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(8.0f, 13.0f);
        pathBuilderQ.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        e.l(pathBuilderQ, 3.0f, 16.0f, -3.0f);
        pathBuilderQ.curveTo(16.0f, 14.34f, 10.67f, 13.0f, 8.0f, 13.0f);
        c.C(pathBuilderQ, 14.0f, 18.0f, 2.0f, -0.99f);
        pathBuilderQ.curveTo(2.2f, 16.29f, 5.3f, 15.0f, 8.0f, 15.0f);
        pathBuilderQ.reflectiveCurveToRelative(5.8f, 1.29f, 6.0f, 2.0f);
        f.r(pathBuilderQ, 18.0f, 12.51f, 4.05f);
        pathBuilderQ.curveTo(13.43f, 5.11f, 14.0f, 6.49f, 14.0f, 8.0f);
        pathBuilderQ.reflectiveCurveToRelative(-0.57f, 2.89f, -1.49f, 3.95f);
        pathBuilderQ.curveTo(14.47f, 11.7f, 16.0f, 10.04f, 16.0f, 8.0f);
        pathBuilderQ.reflectiveCurveTo(14.47f, 4.3f, 12.51f, 4.05f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(16.53f, 13.83f);
        pathBuilderQ.curveTo(17.42f, 14.66f, 18.0f, 15.7f, 18.0f, 17.0f);
        e.l(pathBuilderQ, 3.0f, 2.0f, -3.0f);
        pathBuilderQ.curveTo(20.0f, 15.55f, 18.41f, 14.49f, 16.53f, 13.83f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _groupRemove = imageVectorBuild;
        return imageVectorBuild;
    }
}
