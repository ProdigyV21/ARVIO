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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addReaction", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddReaction", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAddReaction", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddReactionKt {
    private static ImageVector _addReaction;

    public static final ImageVector getAddReaction(Icons.TwoTone twoTone) {
        ImageVector imageVector = _addReaction;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AddReaction", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(19.41f, 9.0f, 18.0f, 7.0f, -2.0f);
        pathBuilderC.verticalLineTo(5.08f);
        pathBuilderC.curveTo(14.82f, 4.4f, 13.46f, 4.0f, 12.0f, 4.0f);
        pathBuilderC.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderC.reflectiveCurveToRelative(3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderC.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderC.curveTo(20.0f, 10.94f, 19.79f, 9.93f, 19.41f, 9.0f);
        pathBuilderC.close();
        pathBuilderC.moveTo(15.5f, 8.0f);
        pathBuilderC.curveTo(16.33f, 8.0f, 17.0f, 8.67f, 17.0f, 9.5f);
        pathBuilderC.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderC.reflectiveCurveTo(14.0f, 10.33f, 14.0f, 9.5f);
        pathBuilderC.curveTo(14.0f, 8.67f, 14.67f, 8.0f, 15.5f, 8.0f);
        pathBuilderC.close();
        pathBuilderC.moveTo(8.5f, 8.0f);
        pathBuilderC.curveTo(9.33f, 8.0f, 10.0f, 8.67f, 10.0f, 9.5f);
        pathBuilderC.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderC.reflectiveCurveTo(7.0f, 10.33f, 7.0f, 9.5f);
        pathBuilderC.curveTo(7.0f, 8.67f, 7.67f, 8.0f, 8.5f, 8.0f);
        pathBuilderC.close();
        pathBuilderC.moveTo(12.0f, 17.5f);
        pathBuilderC.curveToRelative(-2.33f, 0.0f, -4.31f, -1.46f, -5.11f, -3.5f);
        pathBuilderC.horizontalLineToRelative(10.22f);
        pathBuilderC.curveTo(16.31f, 16.04f, 14.33f, 17.5f, 12.0f, 17.5f);
        pathBuilderC.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.0f, 9.5f);
        pathBuilderA.curveTo(7.0f, 8.67f, 7.67f, 8.0f, 8.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(10.0f, 8.67f, 10.0f, 9.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(7.0f, 10.33f, 7.0f, 9.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 17.5f);
        pathBuilderA.curveToRelative(2.33f, 0.0f, 4.31f, -1.46f, 5.11f, -3.5f);
        pathBuilderA.horizontalLineTo(6.89f);
        pathBuilderA.curveTo(7.69f, 16.04f, 9.67f, 17.5f, 12.0f, 17.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.5f, 11.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.curveTo(17.0f, 8.67f, 16.33f, 8.0f, 15.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 8.67f, 14.0f, 9.5f);
        pathBuilderA.curveTo(14.0f, 10.33f, 14.67f, 11.0f, 15.5f, 11.0f);
        b.w(pathBuilderA, 22.0f, 1.0f, -2.0f, 2.0f);
        a.o(pathBuilderA, -2.0f, 2.0f, 2.0f, 2.0f);
        a0.a.C(pathBuilderA, 2.0f, 5.0f, 2.0f, 3.0f);
        c.D(pathBuilderA, -2.0f, 1.0f, 20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, -3.58f, 8.0f, -8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(-8.0f, -3.58f, -8.0f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -4.42f, 3.58f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.curveToRelative(1.46f, 0.0f, 2.82f, 0.4f, 4.0f, 1.08f);
        pathBuilderA.verticalLineTo(2.84f);
        pathBuilderA.curveTo(14.77f, 2.3f, 13.42f, 2.0f, 11.99f, 2.0f);
        pathBuilderA.curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderA.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.05f, -0.17f, -2.05f, -0.47f, -3.0f);
        pathBuilderA.horizontalLineToRelative(-2.13f);
        pathBuilderA.curveTo(19.78f, 9.93f, 20.0f, 10.94f, 20.0f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addReaction = imageVectorBuild;
        return imageVectorBuild;
    }
}
