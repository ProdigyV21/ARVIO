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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_roomService", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RoomService", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRoomService", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoomServiceKt {
    private static ImageVector _roomService;

    public static final ImageVector getRoomService(Icons.TwoTone twoTone) {
        ImageVector imageVector = _roomService;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.RoomService", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 9.58f);
        pathBuilderA.curveToRelative(-2.95f, 0.0f, -5.47f, 1.83f, -6.5f, 4.41f);
        pathBuilderA.horizontalLineToRelative(13.0f);
        pathBuilderA.curveToRelative(-1.03f, -2.58f, -3.55f, -4.41f, -6.5f, -4.41f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = d.a(2.0f, 17.0f, 20.0f, 2.0f);
        c.z(pathBuilderA2, 2.0f, 19.0f, 13.84f, 7.79f);
        pathBuilderA2.curveToRelative(0.1f, -0.24f, 0.16f, -0.51f, 0.16f, -0.79f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.28f, 0.06f, 0.55f, 0.16f, 0.79f);
        pathBuilderA2.curveTo(6.25f, 8.6f, 3.27f, 11.93f, 3.0f, 16.0f);
        pathBuilderA2.horizontalLineToRelative(18.0f);
        pathBuilderA2.curveToRelative(-0.27f, -4.07f, -3.25f, -7.4f, -7.16f, -8.21f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 9.58f);
        pathBuilderA2.curveToRelative(2.95f, 0.0f, 5.47f, 1.83f, 6.5f, 4.41f);
        pathBuilderA2.horizontalLineToRelative(-13.0f);
        pathBuilderA2.curveToRelative(1.03f, -2.58f, 3.55f, -4.41f, 6.5f, -4.41f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _roomService = imageVectorBuild;
        return imageVectorBuild;
    }
}
