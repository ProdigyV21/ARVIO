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
import v.b;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addReaction", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddReaction", "Landroidx/compose/material/icons/Icons$Sharp;", "getAddReaction", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddReactionKt {
    private static ImageVector _addReaction;

    public static final ImageVector getAddReaction(Icons.Sharp sharp) {
        ImageVector imageVector = _addReaction;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.AddReaction", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderM = a.m(18.0f, 9.0f, 7.0f, -2.0f, 2.84f);
        pathBuilderM.curveTo(14.77f, 2.3f, 13.42f, 2.0f, 11.99f, 2.0f);
        pathBuilderM.curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderM.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderM.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
        pathBuilderM.curveToRelative(0.0f, -1.05f, -0.17f, -2.05f, -0.47f, -3.0f);
        v.a.x(pathBuilderM, 18.0f, 15.5f, 8.0f);
        pathBuilderM.curveTo(16.33f, 8.0f, 17.0f, 8.67f, 17.0f, 9.5f);
        pathBuilderM.reflectiveCurveTo(16.33f, 11.0f, 15.5f, 11.0f);
        pathBuilderM.reflectiveCurveTo(14.0f, 10.33f, 14.0f, 9.5f);
        pathBuilderM.reflectiveCurveTo(14.67f, 8.0f, 15.5f, 8.0f);
        pathBuilderM.close();
        pathBuilderM.moveTo(8.5f, 8.0f);
        pathBuilderM.curveTo(9.33f, 8.0f, 10.0f, 8.67f, 10.0f, 9.5f);
        pathBuilderM.reflectiveCurveTo(9.33f, 11.0f, 8.5f, 11.0f);
        pathBuilderM.reflectiveCurveTo(7.0f, 10.33f, 7.0f, 9.5f);
        pathBuilderM.reflectiveCurveTo(7.67f, 8.0f, 8.5f, 8.0f);
        pathBuilderM.close();
        pathBuilderM.moveTo(12.0f, 17.5f);
        pathBuilderM.curveToRelative(-2.33f, 0.0f, -4.31f, -1.46f, -5.11f, -3.5f);
        pathBuilderM.horizontalLineToRelative(10.22f);
        pathBuilderM.curveTo(16.31f, 16.04f, 14.33f, 17.5f, 12.0f, 17.5f);
        b.w(pathBuilderM, 22.0f, 3.0f, 2.0f, 2.0f);
        a0.a.j(pathBuilderM, -2.0f, 2.0f, -2.0f, 5.0f);
        a0.a.C(pathBuilderM, -2.0f, 3.0f, 2.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderM, 2.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addReaction = imageVectorBuild;
        return imageVectorBuild;
    }
}
