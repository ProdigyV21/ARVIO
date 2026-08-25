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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editAttributes", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditAttributes", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEditAttributes", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditAttributesKt {
    private static ImageVector _editAttributes;

    public static final ImageVector getEditAttributes(Icons.TwoTone twoTone) {
        ImageVector imageVector = _editAttributes;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EditAttributes", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(17.63f, 9.0f, 6.37f);
        pathBuilderQ.curveTo(5.09f, 9.0f, 4.0f, 10.37f, 4.0f, 12.0f);
        pathBuilderQ.reflectiveCurveToRelative(1.09f, 3.0f, 2.37f, 3.0f);
        pathBuilderQ.horizontalLineToRelative(11.26f);
        pathBuilderQ.curveToRelative(1.28f, 0.0f, 2.37f, -1.37f, 2.37f, -3.0f);
        pathBuilderQ.reflectiveCurveToRelative(-1.09f, -3.0f, -2.37f, -3.0f);
        a.z(pathBuilderQ, 7.24f, 14.46f, -2.57f, -2.57f);
        pathBuilderQ.lineToRelative(0.7f, -0.7f);
        pathBuilderQ.lineToRelative(1.87f, 1.87f);
        pathBuilderQ.lineToRelative(3.52f, -3.52f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderQ, 0.7f, 0.7f, -4.22f, 4.22f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.63f, 7.0f, 6.37f, 7.0f);
        pathBuilderJ.curveTo(3.96f, 7.0f, 2.0f, 9.24f, 2.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.96f, 5.0f, 4.37f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(11.26f);
        pathBuilderJ.curveToRelative(2.41f, 0.0f, 4.37f, -2.24f, 4.37f, -5.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.96f, -5.0f, -4.37f, -5.0f);
        a.p(pathBuilderJ, 17.63f, 15.0f, 6.37f, 15.0f);
        pathBuilderJ.curveTo(5.09f, 15.0f, 4.0f, 13.63f, 4.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.09f, -3.0f, 2.37f, -3.0f);
        pathBuilderJ.horizontalLineToRelative(11.26f);
        pathBuilderJ.curveTo(18.91f, 9.0f, 20.0f, 10.37f, 20.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.09f, 3.0f, -2.37f, 3.0f);
        a.z(pathBuilderJ, 7.24f, 13.06f, -1.87f, -1.87f);
        pathBuilderJ.lineToRelative(-0.7f, 0.7f);
        pathBuilderJ.lineToRelative(2.57f, 2.57f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 4.22f, -4.22f, -0.7f, -0.7f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editAttributes = imageVectorBuild;
        return imageVectorBuild;
    }
}
