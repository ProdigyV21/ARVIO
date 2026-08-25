package androidx.compose.material.icons.outlined;

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_saveAs", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SaveAs", "Landroidx/compose/material/icons/Icons$Outlined;", "getSaveAs", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SaveAsKt {
    private static ImageVector _saveAs;

    public static final ImageVector getSaveAs(Icons.Outlined outlined) {
        ImageVector imageVector = _saveAs;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SaveAs", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = d.b(21.0f, 12.4f, 7.0f, -4.0f, -4.0f);
        pathBuilderB.horizontalLineTo(5.0f);
        pathBuilderB.curveTo(3.89f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderB.verticalLineToRelative(14.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        a.n(pathBuilderB, 7.4f, 2.0f, -2.0f, 5.0f);
        pathBuilderB.verticalLineTo(5.0f);
        pathBuilderB.horizontalLineToRelative(11.17f);
        pathBuilderB.lineTo(19.0f, 7.83f);
        y.a.n(pathBuilderB, 6.57f, 21.0f, 12.4f);
        pathBuilderB.moveTo(15.0f, 15.0f);
        pathBuilderB.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderB.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderB.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderB.reflectiveCurveTo(15.0f, 13.34f, 15.0f, 15.0f);
        b.w(pathBuilderB, 6.0f, 6.0f, 9.0f, 4.0f);
        c.l(pathBuilderB, 6.0f, 6.0f, 19.99f, 16.25f);
        pathBuilderB.lineToRelative(1.77f, 1.77f);
        b.m(pathBuilderB, 16.77f, 23.0f, 15.0f, -1.77f);
        c.z(pathBuilderB, 19.99f, 16.25f, 23.25f, 16.51f);
        pathBuilderB.lineToRelative(-0.85f, 0.85f);
        pathBuilderB.lineToRelative(-1.77f, -1.77f);
        pathBuilderB.lineToRelative(0.85f, -0.85f);
        pathBuilderB.curveToRelative(0.2f, -0.2f, 0.51f, -0.2f, 0.71f, 0.0f);
        pathBuilderB.lineToRelative(1.06f, 1.06f);
        pathBuilderB.curveTo(23.45f, 16.0f, 23.45f, 16.32f, 23.25f, 16.51f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _saveAs = imageVectorBuild;
        return imageVectorBuild;
    }
}
