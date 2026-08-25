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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_assignmentInd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssignmentInd", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAssignmentInd", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssignmentIndKt {
    private static ImageVector _assignmentInd;

    public static final ImageVector getAssignmentInd(Icons.TwoTone twoTone) {
        ImageVector imageVector = _assignmentInd;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AssignmentInd", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(19.0f, 5.0f, 5.0f, 5.0f, 14.0f);
        d.q(pathBuilderS, 14.0f, 19.0f, 5.0f);
        pathBuilderS.moveTo(12.0f, 6.0f);
        pathBuilderS.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderS.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        a.p(pathBuilderS, 18.0f, 18.0f, 6.0f, 18.0f);
        pathBuilderS.verticalLineToRelative(-1.53f);
        pathBuilderS.curveToRelative(0.0f, -2.5f, 3.97f, -3.58f, 6.0f, -3.58f);
        pathBuilderS.reflectiveCurveToRelative(6.0f, 1.08f, 6.0f, 3.58f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 18.0f, 18.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.66f, 3.88f);
        pathBuilderA.curveToRelative(-0.14f, -0.21f, -0.33f, -0.4f, -0.54f, -0.54f);
        pathBuilderA.curveToRelative(-0.11f, -0.07f, -0.22f, -0.13f, -0.34f, -0.18f);
        pathBuilderA.curveToRelative(-0.24f, -0.1f, -0.5f, -0.16f, -0.78f, -0.16f);
        pathBuilderA.horizontalLineToRelative(-4.18f);
        pathBuilderA.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.4f, 0.84f, -2.82f, 2.0f);
        pathBuilderA.lineTo(5.0f, 3.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.54f, -0.06f, 0.78f, -0.16f);
        pathBuilderA.curveToRelative(0.12f, -0.05f, 0.23f, -0.11f, 0.34f, -0.18f);
        pathBuilderA.curveToRelative(0.21f, -0.14f, 0.4f, -0.33f, 0.54f, -0.54f);
        pathBuilderA.curveToRelative(0.21f, -0.32f, 0.34f, -0.71f, 0.34f, -1.12f);
        pathBuilderA.lineTo(21.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, -0.13f, -0.8f, -0.34f, -1.12f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 2.75f);
        pathBuilderA.curveToRelative(0.22f, 0.0f, 0.41f, 0.1f, 0.55f, 0.25f);
        pathBuilderA.curveToRelative(0.12f, 0.13f, 0.2f, 0.31f, 0.2f, 0.5f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderA.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderA.curveToRelative(0.0f, -0.19f, 0.08f, -0.37f, 0.2f, -0.5f);
        pathBuilderA.curveToRelative(0.14f, -0.15f, 0.33f, -0.25f, 0.55f, -0.25f);
        a.p(pathBuilderA, 19.0f, 19.0f, 5.0f, 19.0f);
        b.g(pathBuilderA, 5.0f, 5.0f, 14.0f, 14.0f);
        pathBuilderA.moveTo(12.0f, 12.0f);
        pathBuilderA.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 10.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 12.88f);
        pathBuilderA.curveToRelative(-2.03f, 0.0f, -6.0f, 1.08f, -6.0f, 3.58f);
        b.A(pathBuilderA, 6.0f, 18.0f, 12.0f, -1.53f);
        pathBuilderA.curveToRelative(0.0f, -2.51f, -3.97f, -3.59f, -6.0f, -3.59f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.31f, 16.0f);
        pathBuilderA.curveToRelative(0.69f, -0.56f, 2.38f, -1.12f, 3.69f, -1.12f);
        pathBuilderA.reflectiveCurveToRelative(3.01f, 0.56f, 3.69f, 1.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 8.31f, 16.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assignmentInd = imageVectorBuild;
        return imageVectorBuild;
    }
}
