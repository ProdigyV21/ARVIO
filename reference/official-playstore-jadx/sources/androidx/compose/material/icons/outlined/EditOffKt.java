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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getEditOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditOffKt {
    private static ImageVector _editOff;

    public static final ImageVector getEditOff(Icons.Outlined outlined) {
        ImageVector imageVector = _editOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.EditOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.06f, 9.02f, 0.92f, 0.92f);
        pathBuilderR.lineToRelative(-1.11f, 1.11f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(2.52f, -2.52f);
        pathBuilderR.lineToRelative(-3.75f, -3.75f);
        pathBuilderR.lineToRelative(-2.52f, 2.52f);
        d.C(pathBuilderR, 1.41f, 1.41f, 14.06f, 9.02f);
        pathBuilderR.moveTo(20.71f, 7.04f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineToRelative(-2.34f, -2.34f);
        pathBuilderR.curveTo(18.17f, 3.09f, 17.92f, 3.0f, 17.66f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.51f, 0.1f, -0.7f, 0.29f);
        pathBuilderR.lineToRelative(-1.83f, 1.83f);
        d.C(pathBuilderR, 3.75f, 3.75f, 20.71f, 7.04f);
        pathBuilderR.moveTo(2.81f, 2.81f);
        pathBuilderR.lineTo(1.39f, 4.22f);
        pathBuilderR.lineToRelative(7.32f, 7.32f);
        b.n(pathBuilderR, 3.0f, 17.25f, 21.0f, 3.75f);
        pathBuilderR.lineToRelative(5.71f, -5.71f);
        pathBuilderR.lineToRelative(7.32f, 7.32f);
        d.C(pathBuilderR, 1.41f, -1.41f, 2.81f, 2.81f);
        a.A(pathBuilderR, 5.92f, 19.0f, 5.0f, -0.92f);
        pathBuilderR.lineToRelative(5.13f, -5.13f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 0.92f, 0.92f, 5.92f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
