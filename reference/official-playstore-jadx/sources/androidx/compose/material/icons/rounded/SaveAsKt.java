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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_saveAs", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SaveAs", "Landroidx/compose/material/icons/Icons$Rounded;", "getSaveAs", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SaveAsKt {
    private static ImageVector _saveAs;

    public static final ImageVector getSaveAs(Icons.Rounded rounded) {
        ImageVector imageVector = _saveAs;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SaveAs", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.41f, 6.41f, -2.83f, -2.83f);
        pathBuilderR.curveTo(17.21f, 3.21f, 16.7f, 3.0f, 16.17f, 3.0f);
        pathBuilderR.horizontalLineTo(5.0f);
        pathBuilderR.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderR.verticalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        e.n(pathBuilderR, 7.4f, 8.6f, -8.6f, 7.83f);
        pathBuilderR.curveTo(21.0f, 7.3f, 20.79f, 6.79f, 20.41f, 6.41f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 18.0f);
        pathBuilderR.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderR.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveTo(13.66f, 18.0f, 12.0f, 18.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(15.0f, 9.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR.horizontalLineTo(7.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.verticalLineTo(7.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderR, 9.0f, 19.99f, 16.25f);
        pathBuilderR.lineToRelative(1.77f, 1.77f);
        pathBuilderR.lineToRelative(-4.84f, 4.84f);
        pathBuilderR.curveTo(16.82f, 22.95f, 16.69f, 23.0f, 16.56f, 23.0f);
        pathBuilderR.horizontalLineTo(15.5f);
        pathBuilderR.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderR.verticalLineToRelative(-1.06f);
        pathBuilderR.curveToRelative(0.0f, -0.13f, 0.05f, -0.26f, 0.15f, -0.35f);
        c.z(pathBuilderR, 19.99f, 16.25f, 23.25f, 16.51f);
        pathBuilderR.lineToRelative(-0.85f, 0.85f);
        pathBuilderR.lineToRelative(-1.77f, -1.77f);
        pathBuilderR.lineToRelative(0.85f, -0.85f);
        pathBuilderR.curveToRelative(0.2f, -0.2f, 0.51f, -0.2f, 0.71f, 0.0f);
        pathBuilderR.lineToRelative(1.06f, 1.06f);
        pathBuilderR.curveTo(23.45f, 16.0f, 23.45f, 16.32f, 23.25f, 16.51f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _saveAs = imageVectorBuild;
        return imageVectorBuild;
    }
}
