package androidx.compose.material.icons.rounded;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_insertEmoticon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "InsertEmoticon", "Landroidx/compose/material/icons/Icons$Rounded;", "getInsertEmoticon", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InsertEmoticonKt {
    private static ImageVector _insertEmoticon;

    public static final ImageVector getInsertEmoticon(Icons.Rounded rounded) {
        ImageVector imageVector = _insertEmoticon;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.InsertEmoticon", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.99f, 2.0f);
        pathBuilderA.curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderA.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 11.99f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.5f, 8.0f);
        pathBuilderA.curveTo(9.33f, 8.0f, 10.0f, 8.67f, 10.0f, 9.5f);
        pathBuilderA.reflectiveCurveTo(9.33f, 11.0f, 8.5f, 11.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 10.33f, 7.0f, 9.5f);
        pathBuilderA.reflectiveCurveTo(7.67f, 8.0f, 8.5f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.75f, 14.75f);
        pathBuilderA.curveTo(15.8f, 16.39f, 14.03f, 17.5f, 12.0f, 17.5f);
        pathBuilderA.reflectiveCurveToRelative(-3.8f, -1.11f, -4.75f, -2.75f);
        pathBuilderA.curveTo(7.06f, 14.42f, 7.31f, 14.0f, 7.69f, 14.0f);
        pathBuilderA.horizontalLineToRelative(8.62f);
        pathBuilderA.curveTo(16.7f, 14.0f, 16.94f, 14.42f, 16.75f, 14.75f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.5f, 11.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(14.67f, 8.0f, 15.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(17.0f, 8.67f, 17.0f, 9.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 16.33f, 11.0f, 15.5f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _insertEmoticon = imageVectorBuild;
        return imageVectorBuild;
    }
}
