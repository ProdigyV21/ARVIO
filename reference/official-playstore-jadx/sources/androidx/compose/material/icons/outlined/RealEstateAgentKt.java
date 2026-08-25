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
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_realEstateAgent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RealEstateAgent", "Landroidx/compose/material/icons/Icons$Outlined;", "getRealEstateAgent", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RealEstateAgentKt {
    private static ImageVector _realEstateAgent;

    public static final ImageVector getRealEstateAgent(Icons.Outlined outlined) {
        ImageVector imageVector = _realEstateAgent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RealEstateAgent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderM = a.m(21.0f, 6.5f, 14.0f, -2.0f, 7.5f);
        pathBuilderM.lineTo(14.0f, 4.0f);
        c.f(pathBuilderM, 9.0f, 7.5f, 9.0f, 7.0f);
        pathBuilderM.verticalLineTo(6.5f);
        d.C(pathBuilderM, 7.0f, -5.0f, 21.0f, 6.5f);
        v.a.C(pathBuilderM, 15.5f, 7.0f, -1.0f, 1.0f);
        c.D(pathBuilderM, 1.0f, 7.0f, 13.5f, 7.0f);
        f.w(pathBuilderM, -1.0f, 1.0f, 1.0f, 7.0f);
        v.a.C(pathBuilderM, 15.5f, 9.0f, -1.0f, 1.0f);
        c.D(pathBuilderM, 1.0f, 9.0f, 13.5f, 9.0f);
        f.w(pathBuilderM, -1.0f, 1.0f, 1.0f, 9.0f);
        pathBuilderM.moveTo(19.0f, 16.0f);
        pathBuilderM.horizontalLineToRelative(-2.0f);
        pathBuilderM.curveToRelative(0.0f, -1.2f, -0.75f, -2.28f, -1.87f, -2.7f);
        b.m(pathBuilderM, 8.97f, 11.0f, 1.0f, 11.0f);
        f.y(pathBuilderM, 6.0f, -1.44f, 7.0f, 1.94f);
        pathBuilderM.lineToRelative(8.0f, -2.5f);
        pathBuilderM.verticalLineToRelative(-1.0f);
        pathBuilderM.curveTo(22.0f, 17.34f, 20.66f, 16.0f, 19.0f, 16.0f);
        c.y(pathBuilderM, 3.0f, 20.0f, -7.0f, 2.0f);
        e.y(pathBuilderM, 7.0f, 3.0f, 13.97f, 20.41f);
        b.n(pathBuilderM, 7.0f, 18.48f, 13.0f, 1.61f);
        pathBuilderM.lineToRelative(5.82f, 2.17f);
        pathBuilderM.curveTo(14.77f, 15.3f, 15.0f, 15.63f, 15.0f, 16.0f);
        pathBuilderM.curveToRelative(0.0f, 0.0f, -1.99f, -0.05f, -2.3f, -0.15f);
        pathBuilderM.lineToRelative(-2.38f, -0.79f);
        pathBuilderM.lineToRelative(-0.63f, 1.9f);
        pathBuilderM.lineToRelative(2.38f, 0.79f);
        pathBuilderM.curveToRelative(0.51f, 0.17f, 1.04f, 0.26f, 1.58f, 0.26f);
        pathBuilderM.horizontalLineTo(19.0f);
        pathBuilderM.curveToRelative(0.39f, 0.0f, 0.74f, 0.23f, 0.9f, 0.56f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderM, 13.97f, 20.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _realEstateAgent = imageVectorBuild;
        return imageVectorBuild;
    }
}
