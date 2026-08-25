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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editLocation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditLocation", "Landroidx/compose/material/icons/Icons$Sharp;", "getEditLocation", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditLocationKt {
    private static ImageVector _editLocation;

    public static final ImageVector getEditLocation(Icons.Sharp sharp) {
        ImageVector imageVector = _editLocation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.EditLocation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.11f, 1.77f, 19.78f, 0.1f);
        pathBuilderJ.lineToRelative(2.12f, 2.12f);
        b.D(pathBuilderJ, -1.67f, 1.67f, -2.12f, -2.12f);
        pathBuilderJ.moveTo(17.11f, 2.77f);
        pathBuilderJ.lineToRelative(2.12f, 2.12f);
        pathBuilderJ.lineTo(13.12f, 11.0f);
        pathBuilderJ.lineTo(11.0f, 11.0f);
        d.l(pathBuilderJ, 11.0f, 8.89f, 6.11f, -6.12f);
        pathBuilderJ.moveTo(15.13f, 2.64f);
        a.h(pathBuilderJ, 9.5f, 8.27f, 4.24f, 4.24f);
        pathBuilderJ.lineToRelative(5.62f, -5.62f);
        pathBuilderJ.curveToRelative(0.41f, 0.99f, 0.64f, 2.1f, 0.64f, 3.32f);
        pathBuilderJ.curveToRelative(0.0f, 3.32f, -2.67f, 7.25f, -8.0f, 11.8f);
        pathBuilderJ.curveToRelative(-5.33f, -4.55f, -8.0f, -8.48f, -8.0f, -11.8f);
        pathBuilderJ.curveToRelative(0.0f, -4.98f, 3.8f, -8.2f, 8.0f, -8.2f);
        pathBuilderJ.curveToRelative(1.09f, 0.0f, 2.16f, 0.22f, 3.13f, 0.63f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editLocation = imageVectorBuild;
        return imageVectorBuild;
    }
}
