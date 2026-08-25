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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_face", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Face", "Landroidx/compose/material/icons/Icons$Rounded;", "getFace", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FaceKt {
    private static ImageVector _face;

    public static final ImageVector getFace(Icons.Rounded rounded) {
        ImageVector imageVector = _face;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Face", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.25f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 0.69f, -0.56f, 1.25f, -1.25f, 1.25f);
        pathBuilderA.reflectiveCurveTo(7.75f, 13.69f, 7.75f, 13.0f);
        pathBuilderA.reflectiveCurveTo(8.31f, 11.75f, 9.0f, 11.75f);
        pathBuilderA.reflectiveCurveTo(10.25f, 12.31f, 10.25f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 11.75f);
        pathBuilderA.curveToRelative(-0.69f, 0.0f, -1.25f, 0.56f, -1.25f, 1.25f);
        pathBuilderA.reflectiveCurveToRelative(0.56f, 1.25f, 1.25f, 1.25f);
        pathBuilderA.reflectiveCurveToRelative(1.25f, -0.56f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveTo(15.69f, 11.75f, 15.0f, 11.75f);
        pathBuilderA.close();
        pathBuilderA.moveTo(22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, -4.48f, 10.0f, -10.0f, 10.0f);
        pathBuilderA.reflectiveCurveTo(2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveTo(6.48f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(22.0f, 6.48f, 22.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.78f, -0.12f, -1.53f, -0.33f, -2.24f);
        pathBuilderA.curveTo(18.97f, 9.91f, 18.25f, 10.0f, 17.5f, 10.0f);
        pathBuilderA.curveToRelative(-3.13f, 0.0f, -5.92f, -1.44f, -7.76f, -3.69f);
        pathBuilderA.curveTo(8.69f, 8.87f, 6.6f, 10.88f, 4.0f, 11.86f);
        pathBuilderA.curveTo(4.01f, 11.9f, 4.0f, 11.95f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 20.0f, 16.41f, 20.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _face = imageVectorBuild;
        return imageVectorBuild;
    }
}
