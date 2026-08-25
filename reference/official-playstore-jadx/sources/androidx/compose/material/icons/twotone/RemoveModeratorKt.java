package androidx.compose.material.icons.twotone;

import a0.b;
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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_removeModerator", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RemoveModerator", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRemoveModerator", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RemoveModeratorKt {
    private static ImageVector _removeModerator;

    public static final ImageVector getRemoveModerator(Icons.TwoTone twoTone) {
        ImageVector imageVector = _removeModerator;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.RemoveModerator", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 11.09f);
        pathBuilderA.curveToRelative(0.0f, 4.0f, 2.55f, 7.7f, 6.0f, 8.83f);
        pathBuilderA.curveToRelative(1.17f, -0.38f, 2.24f, -1.07f, 3.14f, -1.95f);
        b.s(pathBuilderA, 6.0f, 8.83f, 11.09f);
        pathBuilderA.moveTo(12.0f, 4.14f);
        pathBuilderA.lineTo(8.34f, 5.51f);
        pathBuilderA.lineToRelative(9.02f, 9.02f);
        pathBuilderA.curveToRelative(0.41f, -1.08f, 0.64f, -2.25f, 0.64f, -3.44f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, -4.7f, 12.0f, 4.14f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = e.s(12.0f, 4.14f, 6.0f, 2.25f, 4.7f);
        pathBuilderS.curveToRelative(0.0f, 1.19f, -0.23f, 2.36f, -0.64f, 3.44f);
        pathBuilderS.lineToRelative(1.51f, 1.51f);
        pathBuilderS.curveToRelative(0.72f, -1.53f, 1.13f, -3.22f, 1.13f, -4.95f);
        pathBuilderS.verticalLineTo(5.0f);
        pathBuilderS.lineToRelative(-8.0f, -3.0f);
        pathBuilderS.lineTo(6.78f, 3.96f);
        d.C(pathBuilderS, 1.55f, 1.55f, 12.0f, 4.14f);
        pathBuilderS.moveTo(2.81f, 2.81f);
        pathBuilderS.lineTo(1.39f, 4.22f);
        pathBuilderS.lineTo(4.0f, 6.83f);
        pathBuilderS.verticalLineToRelative(4.26f);
        pathBuilderS.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilderS.curveToRelative(1.72f, -0.43f, 3.28f, -1.36f, 4.55f, -2.62f);
        pathBuilderS.lineToRelative(3.23f, 3.23f);
        d.C(pathBuilderS, 1.41f, -1.41f, 2.81f, 2.81f);
        pathBuilderS.moveTo(12.0f, 19.92f);
        pathBuilderS.curveToRelative(-3.45f, -1.13f, -6.0f, -4.82f, -6.0f, -8.83f);
        pathBuilderS.verticalLineTo(8.83f);
        pathBuilderS.lineToRelative(9.14f, 9.14f);
        pathBuilderS.curveTo(14.24f, 18.85f, 13.17f, 19.54f, 12.0f, 19.92f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _removeModerator = imageVectorBuild;
        return imageVectorBuild;
    }
}
