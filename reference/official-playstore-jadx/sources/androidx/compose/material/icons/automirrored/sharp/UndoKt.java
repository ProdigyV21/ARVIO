package androidx.compose.material.icons.automirrored.sharp;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_undo", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Undo", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getUndo", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UndoKt {
    private static ImageVector _undo;

    public static final ImageVector getUndo(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _undo;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.Undo", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.5f, 8.0f);
        pathBuilderA.curveToRelative(-2.65f, 0.0f, -5.05f, 0.99f, -6.9f, 2.6f);
        a.h(pathBuilderA, 2.0f, 7.0f, 9.0f, 9.0f);
        pathBuilderA.lineToRelative(-3.62f, -3.62f);
        pathBuilderA.curveToRelative(1.39f, -1.16f, 3.16f, -1.88f, 5.12f, -1.88f);
        pathBuilderA.curveToRelative(3.54f, 0.0f, 6.55f, 2.31f, 7.6f, 5.5f);
        pathBuilderA.lineToRelative(2.37f, -0.78f);
        pathBuilderA.curveTo(21.08f, 11.03f, 17.15f, 8.0f, 12.5f, 8.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _undo = imageVectorBuild;
        return imageVectorBuild;
    }
}
