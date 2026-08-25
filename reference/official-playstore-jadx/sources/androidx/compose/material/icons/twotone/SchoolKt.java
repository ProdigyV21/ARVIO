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
import v.b;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_school", "Landroidx/compose/ui/graphics/vector/ImageVector;", "School", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSchool", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SchoolKt {
    private static ImageVector _school;

    public static final ImageVector getSchool(Icons.TwoTone twoTone) {
        ImageVector imageVector = _school;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.School", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(7.0f, 12.27f, 3.72f, 5.0f, 2.73f);
        pathBuilderG.lineToRelative(5.0f, -2.73f);
        y.a.n(pathBuilderG, -3.72f, 12.0f, 15.0f);
        pathBuilderG.moveTo(5.18f, 9.0f);
        pathBuilderG.lineTo(12.0f, 12.72f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderG, 18.82f, 9.0f, 12.0f, 5.28f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = v.a.j(12.0f, 3.0f, 1.0f, 9.0f);
        pathBuilderJ.lineToRelative(4.0f, 2.18f);
        pathBuilderJ.verticalLineToRelative(6.0f);
        pathBuilderJ.lineTo(12.0f, 21.0f);
        pathBuilderJ.lineToRelative(7.0f, -3.82f);
        pathBuilderJ.verticalLineToRelative(-6.0f);
        pathBuilderJ.lineToRelative(2.0f, -1.09f);
        pathBuilderJ.lineTo(21.0f, 17.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        b.y(pathBuilderJ, 23.0f, 9.0f, 12.0f, 3.0f);
        pathBuilderJ.moveTo(17.0f, 15.99f);
        pathBuilderJ.lineToRelative(-5.0f, 2.73f);
        pathBuilderJ.lineToRelative(-5.0f, -2.73f);
        pathBuilderJ.verticalLineToRelative(-3.72f);
        pathBuilderJ.lineTo(12.0f, 15.0f);
        f.p(pathBuilderJ, 5.0f, -2.73f, 3.72f);
        pathBuilderJ.moveTo(12.0f, 12.72f);
        pathBuilderJ.lineTo(5.18f, 9.0f);
        pathBuilderJ.lineTo(12.0f, 5.28f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderJ, 18.82f, 9.0f, 12.0f, 12.72f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _school = imageVectorBuild;
        return imageVectorBuild;
    }
}
