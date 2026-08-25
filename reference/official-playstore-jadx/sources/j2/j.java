package j2;

import androidx.compose.foundation.shape.GenericShape;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: loaded from: classes3.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final GenericShape f19117a = new GenericShape(i.f19116i);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final RoundedCornerShape f19118b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RoundedCornerShape f19119c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final RoundedCornerShape f19120d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final RoundedCornerShape f19121e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Shape f19122f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final RoundedCornerShape f19123g;

    static {
        float f10 = (float) 28.0d;
        f19118b = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10));
        float f11 = (float) 0.0d;
        RoundedCornerShapeKt.m799RoundedCornerShapea9UjIt4(Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f11));
        float f12 = (float) 4.0d;
        f19119c = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f12));
        RoundedCornerShapeKt.m799RoundedCornerShapea9UjIt4(Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f11));
        RoundedCornerShapeKt.getCircleShape();
        float f13 = (float) 16.0d;
        f19120d = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f13));
        RoundedCornerShapeKt.m799RoundedCornerShapea9UjIt4(Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f11));
        RoundedCornerShapeKt.m799RoundedCornerShapea9UjIt4(Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f11));
        f19121e = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl((float) 12.0d));
        f19122f = RectangleShapeKt.getRectangleShape();
        f19123g = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl((float) 8.0d));
    }
}
