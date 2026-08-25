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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editLocation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditLocation", "Landroidx/compose/material/icons/Icons$Outlined;", "getEditLocation", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditLocationKt {
    private static ImageVector _editLocation;

    public static final ImageVector getEditLocation(Icons.Outlined outlined) {
        ImageVector imageVector = _editLocation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.EditLocation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.17f, 4.91f, 17.1f, 3.84f);
        b.u(pathBuilderJ, -5.55f, 5.55f, 1.08f, 1.08f);
        b.C(pathBuilderJ, 5.54f, -5.56f, 16.0f, 2.74f);
        pathBuilderJ.lineToRelative(1.29f, -1.29f);
        pathBuilderJ.arcToRelative(1.49f, 1.49f, 0.0f, false, true, 2.12f, 0.0f);
        pathBuilderJ.lineToRelative(1.15f, 1.15f);
        pathBuilderJ.curveToRelative(0.59f, 0.59f, 0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilderJ.lineToRelative(-0.68f, 0.68f);
        pathBuilderJ.lineToRelative(-0.02f, 0.02f);
        pathBuilderJ.lineToRelative(-0.58f, 0.58f);
        pathBuilderJ.lineToRelative(-6.0f, 6.0f);
        pathBuilderJ.lineTo(10.0f, 12.0f);
        d.l(pathBuilderJ, 10.0f, 8.74f, 6.0f, -6.0f);
        pathBuilderJ.moveTo(13.72f, 2.19f);
        pathBuilderJ.lineToRelative(-0.55f, 0.55f);
        pathBuilderJ.lineToRelative(-1.27f, 1.27f);
        pathBuilderJ.curveToRelative(-3.3f, 0.05f, -5.9f, 2.6f, -5.9f, 6.2f);
        pathBuilderJ.curveToRelative(0.0f, 2.34f, 1.95f, 5.44f, 6.0f, 9.14f);
        pathBuilderJ.curveToRelative(4.05f, -3.7f, 6.0f, -6.79f, 6.0f, -9.14f);
        pathBuilderJ.verticalLineToRelative(-0.1f);
        pathBuilderJ.lineToRelative(1.8f, -1.8f);
        pathBuilderJ.curveToRelative(0.13f, 0.6f, 0.2f, 1.24f, 0.2f, 1.9f);
        pathBuilderJ.curveToRelative(0.0f, 3.32f, -2.67f, 7.25f, -8.0f, 11.8f);
        pathBuilderJ.curveToRelative(-5.33f, -4.55f, -8.0f, -8.48f, -8.0f, -11.8f);
        pathBuilderJ.curveToRelative(0.0f, -4.98f, 3.8f, -8.2f, 8.0f, -8.2f);
        pathBuilderJ.curveToRelative(0.58f, 0.0f, 1.16f, 0.06f, 1.72f, 0.18f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editLocation = imageVectorBuild;
        return imageVectorBuild;
    }
}
