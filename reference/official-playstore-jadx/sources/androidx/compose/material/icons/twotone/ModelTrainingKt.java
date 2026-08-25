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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_modelTraining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ModelTraining", "Landroidx/compose/material/icons/Icons$TwoTone;", "getModelTraining", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModelTrainingKt {
    private static ImageVector _modelTraining;

    public static final ImageVector getModelTraining(Icons.TwoTone twoTone) {
        ImageVector imageVector = _modelTraining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ModelTraining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.5f, 13.5f);
        pathBuilderA.curveToRelative(0.0f, 2.0f, -2.5f, 3.5f, -2.5f, 5.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.5f, -2.5f, -3.0f, -2.5f, -5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveTo(13.93f, 10.0f, 15.5f, 11.57f, 15.5f, 13.5f);
        a.u(pathBuilderA, 13.0f, 19.5f, -2.0f, 21.0f);
        c.D(pathBuilderA, 2.0f, 19.5f, 19.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 1.68f, -0.59f, 3.21f, -1.58f, 4.42f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.curveTo(20.18f, 17.27f, 21.0f, 15.23f, 21.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, -2.74f, -1.23f, -5.19f, -3.16f, -6.84f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(17.99f, 8.86f, 19.0f, 10.82f, 19.0f, 13.0f);
        a.z(pathBuilderA, 16.0f, 5.0f, -4.0f, -4.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 2.23f, 0.82f, 4.27f, 2.16f, 5.84f);
        pathBuilderA.lineToRelative(1.42f, -1.42f);
        pathBuilderA.curveTo(5.59f, 16.21f, 5.0f, 14.68f, 5.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, -3.86f, 3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, 3.0f, 16.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _modelTraining = imageVectorBuild;
        return imageVectorBuild;
    }
}
