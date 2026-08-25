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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_catchingPokemon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CatchingPokemon", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCatchingPokemon", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CatchingPokemonKt {
    private static ImageVector _catchingPokemon;

    public static final ImageVector getCatchingPokemon(Icons.TwoTone twoTone) {
        ImageVector imageVector = _catchingPokemon;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CatchingPokemon", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(4.08f, 0.0f, 7.45f, 3.05f, 7.94f, 7.0f);
        pathBuilderA.horizontalLineToRelative(-4.06f);
        pathBuilderA.curveTo(15.43f, 9.27f, 13.86f, 8.0f, 12.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.43f, 1.27f, -3.87f, 3.0f);
        pathBuilderA.horizontalLineTo(4.06f);
        pathBuilderA.curveTo(4.55f, 7.05f, 7.92f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 2.0f);
        pathBuilderA2.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA2.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 4.0f);
        pathBuilderA2.curveToRelative(4.08f, 0.0f, 7.45f, 3.05f, 7.94f, 7.0f);
        pathBuilderA2.horizontalLineToRelative(-4.06f);
        pathBuilderA2.curveTo(15.43f, 9.27f, 13.86f, 8.0f, 12.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(-3.43f, 1.27f, -3.87f, 3.0f);
        pathBuilderA2.horizontalLineTo(4.06f);
        pathBuilderA2.curveTo(4.55f, 7.05f, 7.92f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(14.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveTo(14.0f, 10.9f, 14.0f, 12.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 20.0f);
        pathBuilderA2.curveToRelative(-4.08f, 0.0f, -7.45f, -3.05f, -7.94f, -7.0f);
        pathBuilderA2.horizontalLineToRelative(4.06f);
        pathBuilderA2.curveToRelative(0.44f, 1.73f, 2.01f, 3.0f, 3.87f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.43f, -1.27f, 3.87f, -3.0f);
        pathBuilderA2.horizontalLineToRelative(4.06f);
        pathBuilderA2.curveTo(19.45f, 16.95f, 16.08f, 20.0f, 12.0f, 20.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _catchingPokemon = imageVectorBuild;
        return imageVectorBuild;
    }
}
