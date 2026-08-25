package androidx.compose.material.icons.filled;

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
import v.d;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addToDrive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddToDrive", "Landroidx/compose/material/icons/Icons$Filled;", "getAddToDrive", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddToDriveKt {
    private static ImageVector _addToDrive;

    public static final ImageVector getAddToDrive(Icons.Filled filled) {
        ImageVector imageVector = _addToDrive;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AddToDrive", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(20.0f, 21.0f, -3.0f, 3.0f, -2.0f);
        v.a.o(pathBuilderU, -3.0f, -3.0f, -2.0f, 3.0f);
        v.a.o(pathBuilderU, -3.0f, 2.0f, 3.0f, 3.0f);
        w.a.s(pathBuilderU, 20.0f, 15.03f, 21.5f, 5.66f);
        pathBuilderU.curveToRelative(-0.72f, 0.0f, -1.38f, -0.38f, -1.73f, -1.0f);
        pathBuilderU.lineTo(1.57f, 16.4f);
        pathBuilderU.curveToRelative(-0.36f, -0.62f, -0.35f, -1.38f, 0.01f, -2.0f);
        pathBuilderU.lineTo(7.92f, 3.49f);
        pathBuilderU.curveTo(8.28f, 2.88f, 8.94f, 2.5f, 9.65f, 2.5f);
        pathBuilderU.horizontalLineToRelative(4.7f);
        pathBuilderU.curveToRelative(0.71f, 0.0f, 1.37f, 0.38f, 1.73f, 0.99f);
        pathBuilderU.lineToRelative(4.48f, 7.71f);
        pathBuilderU.curveTo(20.06f, 11.07f, 19.54f, 11.0f, 19.0f, 11.0f);
        pathBuilderU.curveToRelative(-0.28f, 0.0f, -0.56f, 0.02f, -0.84f, 0.06f);
        pathBuilderU.lineTo(14.35f, 4.5f);
        pathBuilderU.horizontalLineToRelative(-4.7f);
        pathBuilderU.lineTo(3.31f, 15.41f);
        pathBuilderU.lineToRelative(2.35f, 4.09f);
        pathBuilderU.horizontalLineToRelative(7.89f);
        pathBuilderU.curveTo(13.9f, 20.27f, 14.4f, 20.95f, 15.03f, 21.5f);
        pathBuilderU.close();
        pathBuilderU.moveTo(13.34f, 15.0f);
        pathBuilderU.curveTo(13.12f, 15.63f, 13.0f, 16.3f, 13.0f, 17.0f);
        pathBuilderU.horizontalLineTo(7.25f);
        pathBuilderU.lineToRelative(-0.73f, -1.27f);
        pathBuilderU.lineToRelative(4.58f, -7.98f);
        pathBuilderU.horizontalLineToRelative(1.8f);
        pathBuilderU.lineToRelative(2.53f, 4.42f);
        pathBuilderU.curveToRelative(-0.56f, 0.42f, -1.05f, 0.93f, -1.44f, 1.51f);
        pathBuilderU.lineToRelative(-2.0f, -3.49f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderU, 9.25f, 15.0f, 13.34f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addToDrive = imageVectorBuild;
        return imageVectorBuild;
    }
}
