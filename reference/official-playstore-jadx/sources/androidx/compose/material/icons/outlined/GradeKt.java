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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_grade", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Grade", "Landroidx/compose/material/icons/Icons$Outlined;", "getGrade", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GradeKt {
    private static ImageVector _grade;

    public static final ImageVector getGrade(Icons.Outlined outlined) {
        ImageVector imageVector = _grade;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Grade", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 7.13f, 0.97f, 2.29f);
        pathBuilderR.lineToRelative(0.47f, 1.11f);
        pathBuilderR.lineToRelative(1.2f, 0.1f);
        pathBuilderR.lineToRelative(2.47f, 0.21f);
        pathBuilderR.lineToRelative(-1.88f, 1.63f);
        pathBuilderR.lineToRelative(-0.91f, 0.79f);
        pathBuilderR.lineToRelative(0.27f, 1.18f);
        pathBuilderR.lineToRelative(0.56f, 2.41f);
        pathBuilderR.lineToRelative(-2.12f, -1.28f);
        pathBuilderR.lineToRelative(-1.03f, -0.64f);
        pathBuilderR.lineToRelative(-1.03f, 0.62f);
        pathBuilderR.lineToRelative(-2.12f, 1.28f);
        pathBuilderR.lineToRelative(0.56f, -2.41f);
        pathBuilderR.lineToRelative(0.27f, -1.18f);
        pathBuilderR.lineToRelative(-0.91f, -0.79f);
        pathBuilderR.lineToRelative(-1.88f, -1.63f);
        pathBuilderR.lineToRelative(2.47f, -0.21f);
        pathBuilderR.lineToRelative(1.2f, -0.1f);
        pathBuilderR.lineToRelative(0.47f, -1.11f);
        pathBuilderR.lineToRelative(0.97f, -2.27f);
        pathBuilderR.moveTo(12.0f, 2.0f);
        pathBuilderR.lineTo(9.19f, 8.63f);
        pathBuilderR.lineTo(2.0f, 9.24f);
        pathBuilderR.lineToRelative(5.46f, 4.73f);
        pathBuilderR.lineTo(5.82f, 21.0f);
        pathBuilderR.lineTo(12.0f, 17.27f);
        pathBuilderR.lineTo(18.18f, 21.0f);
        pathBuilderR.lineToRelative(-1.64f, -7.03f);
        pathBuilderR.lineTo(22.0f, 9.24f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, -7.19f, -0.61f, 12.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _grade = imageVectorBuild;
        return imageVectorBuild;
    }
}
