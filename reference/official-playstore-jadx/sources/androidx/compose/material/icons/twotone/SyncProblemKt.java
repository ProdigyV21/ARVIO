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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_syncProblem", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SyncProblem", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSyncProblem", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SyncProblemKt {
    private static ImageVector _syncProblem;

    public static final ImageVector getSyncProblem(Icons.TwoTone twoTone) {
        ImageVector imageVector = _syncProblem;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SyncProblem", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(3.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 0.91f, 4.2f, 2.36f, 5.64f);
        b.A(pathBuilderA, 3.0f, 20.0f, 6.0f, -6.0f);
        pathBuilderA.lineToRelative(-2.24f, 2.24f);
        pathBuilderA.curveTo(5.68f, 15.15f, 5.0f, 13.66f, 5.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.61f, 1.67f, -4.83f, 4.0f, -5.65f);
        pathBuilderA.lineTo(9.0f, 4.26f);
        pathBuilderA.curveTo(5.55f, 5.15f, 3.0f, 8.27f, 3.0f, 12.0f);
        b.w(pathBuilderA, 11.0f, 17.0f, 2.0f, -2.0f);
        b.B(pathBuilderA, -2.0f, 2.0f, 21.0f, 4.0f);
        f.y(pathBuilderA, -6.0f, 6.0f, 2.24f, -2.24f);
        pathBuilderA.curveTo(18.32f, 8.85f, 19.0f, 10.34f, 19.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.61f, -1.67f, 4.83f, -4.0f, 5.65f);
        pathBuilderA.verticalLineToRelative(2.09f);
        pathBuilderA.curveToRelative(3.45f, -0.89f, 6.0f, -4.01f, 6.0f, -7.74f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -0.91f, -4.2f, -2.36f, -5.64f);
        c.z(pathBuilderA, 21.0f, 4.0f, 11.0f, 13.0f);
        c.A(pathBuilderA, 2.0f, 13.0f, 7.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderA, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _syncProblem = imageVectorBuild;
        return imageVectorBuild;
    }
}
