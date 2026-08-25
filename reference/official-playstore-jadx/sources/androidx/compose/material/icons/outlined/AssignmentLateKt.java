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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_assignmentLate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssignmentLate", "Landroidx/compose/material/icons/Icons$Outlined;", "getAssignmentLate", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssignmentLateKt {
    private static ImageVector _assignmentLate;

    public static final ImageVector getAssignmentLate(Icons.Outlined outlined) {
        ImageVector imageVector = _assignmentLate;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AssignmentLate", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(11.0f, 15.0f, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderB, 11.0f, 7.0f, 2.0f, 6.0f);
        e.r(pathBuilderB, -2.0f, 19.0f, 3.0f, -4.18f);
        pathBuilderB.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilderB.curveToRelative(-1.3f, 0.0f, -2.4f, 0.84f, -2.82f, 2.0f);
        pathBuilderB.lineTo(5.0f, 3.0f);
        pathBuilderB.curveToRelative(-0.14f, 0.0f, -0.27f, 0.01f, -0.4f, 0.04f);
        pathBuilderB.curveToRelative(-0.39f, 0.08f, -0.74f, 0.28f, -1.01f, 0.55f);
        pathBuilderB.curveToRelative(-0.18f, 0.18f, -0.33f, 0.4f, -0.43f, 0.64f);
        pathBuilderB.curveToRelative(-0.1f, 0.23f, -0.16f, 0.49f, -0.16f, 0.77f);
        pathBuilderB.verticalLineToRelative(14.0f);
        pathBuilderB.curveToRelative(0.0f, 0.27f, 0.06f, 0.54f, 0.16f, 0.78f);
        pathBuilderB.reflectiveCurveToRelative(0.25f, 0.45f, 0.43f, 0.64f);
        pathBuilderB.curveToRelative(0.27f, 0.27f, 0.62f, 0.47f, 1.01f, 0.55f);
        pathBuilderB.curveToRelative(0.13f, 0.02f, 0.26f, 0.03f, 0.4f, 0.03f);
        pathBuilderB.horizontalLineToRelative(14.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderB.lineTo(21.0f, 5.0f);
        pathBuilderB.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderB.close();
        pathBuilderB.moveTo(12.0f, 2.75f);
        pathBuilderB.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderB.reflectiveCurveToRelative(-0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderB.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderB.reflectiveCurveToRelative(0.34f, -0.75f, 0.75f, -0.75f);
        a.p(pathBuilderB, 19.0f, 19.0f, 5.0f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderB, 5.0f, 5.0f, 14.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assignmentLate = imageVectorBuild;
        return imageVectorBuild;
    }
}
