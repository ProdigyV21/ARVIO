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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_medication", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Medication", "Landroidx/compose/material/icons/Icons$Rounded;", "getMedication", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MedicationKt {
    private static ImageVector _medication;

    public static final ImageVector getMedication(Icons.Rounded rounded) {
        ImageVector imageVector = _medication;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Medication", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(17.0f, 3.0f, 7.0f);
        pathBuilderQ.curveTo(6.45f, 3.0f, 6.0f, 3.45f, 6.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveTo(18.0f, 3.45f, 17.55f, 3.0f, 17.0f, 3.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ2 = a.q(17.0f, 6.0f, 7.0f);
        pathBuilderQ2.curveTo(5.9f, 6.0f, 5.0f, 6.9f, 5.0f, 8.0f);
        pathBuilderQ2.verticalLineToRelative(11.0f);
        pathBuilderQ2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ2.horizontalLineToRelative(10.0f);
        pathBuilderQ2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ2.verticalLineTo(8.0f);
        pathBuilderQ2.curveTo(19.0f, 6.9f, 18.1f, 6.0f, 17.0f, 6.0f);
        b.w(pathBuilderQ2, 14.5f, 15.0f, -1.0f, 1.0f);
        pathBuilderQ2.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderQ2.horizontalLineToRelative(0.0f);
        pathBuilderQ2.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderQ2.verticalLineToRelative(-1.0f);
        pathBuilderQ2.horizontalLineToRelative(-1.0f);
        pathBuilderQ2.curveTo(8.67f, 15.0f, 8.0f, 14.33f, 8.0f, 13.5f);
        pathBuilderQ2.verticalLineToRelative(0.0f);
        pathBuilderQ2.curveTo(8.0f, 12.67f, 8.67f, 12.0f, 9.5f, 12.0f);
        pathBuilderQ2.horizontalLineToRelative(1.0f);
        pathBuilderQ2.verticalLineToRelative(-1.0f);
        pathBuilderQ2.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderQ2.horizontalLineToRelative(0.0f);
        pathBuilderQ2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderQ2.verticalLineToRelative(1.0f);
        pathBuilderQ2.horizontalLineToRelative(1.0f);
        pathBuilderQ2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderQ2.verticalLineToRelative(0.0f);
        pathBuilderQ2.curveTo(16.0f, 14.33f, 15.33f, 15.0f, 14.5f, 15.0f);
        pathBuilderQ2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _medication = imageVectorBuild;
        return imageVectorBuild;
    }
}
