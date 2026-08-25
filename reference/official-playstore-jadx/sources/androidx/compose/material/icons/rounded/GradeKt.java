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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_grade", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Grade", "Landroidx/compose/material/icons/Icons$Rounded;", "getGrade", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GradeKt {
    private static ImageVector _grade;

    public static final ImageVector getGrade(Icons.Rounded rounded) {
        ImageVector imageVector = _grade;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Grade", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 17.27f, 5.17f, 3.12f);
        pathBuilderR.curveToRelative(0.38f, 0.23f, 0.85f, -0.11f, 0.75f, -0.54f);
        pathBuilderR.lineToRelative(-1.37f, -5.88f);
        pathBuilderR.lineToRelative(4.56f, -3.95f);
        pathBuilderR.curveToRelative(0.33f, -0.29f, 0.16f, -0.84f, -0.29f, -0.88f);
        pathBuilderR.lineToRelative(-6.01f, -0.51f);
        pathBuilderR.lineToRelative(-2.35f, -5.54f);
        pathBuilderR.curveToRelative(-0.17f, -0.41f, -0.75f, -0.41f, -0.92f, 0.0f);
        pathBuilderR.lineTo(9.19f, 8.63f);
        pathBuilderR.lineToRelative(-6.01f, 0.51f);
        pathBuilderR.curveToRelative(-0.44f, 0.04f, -0.62f, 0.59f, -0.28f, 0.88f);
        pathBuilderR.lineToRelative(4.56f, 3.95f);
        pathBuilderR.lineToRelative(-1.37f, 5.88f);
        pathBuilderR.curveToRelative(-0.1f, 0.43f, 0.37f, 0.77f, 0.75f, 0.54f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 12.0f, 17.27f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _grade = imageVectorBuild;
        return imageVectorBuild;
    }
}
