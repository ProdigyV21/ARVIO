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
import v.b;
import v.c;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_medicalInformation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MedicalInformation", "Landroidx/compose/material/icons/Icons$Outlined;", "getMedicalInformation", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MedicalInformationKt {
    private static ImageVector _medicalInformation;

    public static final ImageVector getMedicalInformation(Icons.Outlined outlined) {
        ImageVector imageVector = _medicalInformation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MedicalInformation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = e.b(20.0f, 7.0f, -5.0f, 4.0f);
        pathBuilderB.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderB.horizontalLineToRelative(-2.0f);
        pathBuilderB.curveTo(9.9f, 2.0f, 9.0f, 2.9f, 9.0f, 4.0f);
        pathBuilderB.verticalLineToRelative(3.0f);
        pathBuilderB.horizontalLineTo(4.0f);
        pathBuilderB.curveTo(2.9f, 7.0f, 2.0f, 7.9f, 2.0f, 9.0f);
        pathBuilderB.verticalLineToRelative(11.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderB.horizontalLineToRelative(16.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderB.verticalLineTo(9.0f);
        pathBuilderB.curveTo(22.0f, 7.9f, 21.1f, 7.0f, 20.0f, 7.0f);
        b.w(pathBuilderB, 11.0f, 4.0f, 2.0f, 5.0f);
        c.D(pathBuilderB, -2.0f, 4.0f, 20.0f, 20.0f);
        a0.b.u(pathBuilderB, 4.0f, 9.0f, 5.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderB.horizontalLineToRelative(2.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        c.D(pathBuilderB, 5.0f, 20.0f, 11.0f, 16.0f);
        a.A(pathBuilderB, 9.0f, 2.0f, 7.0f, -2.0f);
        b.q(pathBuilderB, 5.0f, -2.0f, 2.0f, -2.0f);
        f.w(pathBuilderB, 2.0f, 2.0f, 2.0f, 16.0f);
        e.o(pathBuilderB, 13.0f, 14.5f, 13.0f, 6.0f);
        e.y(pathBuilderB, 1.5f, 13.0f, 13.0f, 17.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.b.d(pathBuilderB, 16.0f, 4.0f, 1.5f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _medicalInformation = imageVectorBuild;
        return imageVectorBuild;
    }
}
