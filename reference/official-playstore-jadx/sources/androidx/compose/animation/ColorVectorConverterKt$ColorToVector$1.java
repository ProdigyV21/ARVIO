package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/animation/core/AnimationVector4D;", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ColorVectorConverterKt$ColorToVector$1 extends r implements l<ColorSpace, TwoWayConverter<Color, AnimationVector4D>> {
    public static final ColorVectorConverterKt$ColorToVector$1 INSTANCE = new ColorVectorConverterKt$ColorToVector$1();

    /* JADX INFO: renamed from: androidx.compose.animation.ColorVectorConverterKt$ColorToVector$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationVector4D;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "invoke-8_81llA", "(J)Landroidx/compose/animation/core/AnimationVector4D;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Color, AnimationVector4D> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m35invoke8_81llA(((Color) obj).m3482unboximpl());
        }

        /* JADX INFO: renamed from: invoke-8_81llA, reason: not valid java name */
        public final AnimationVector4D m35invoke8_81llA(long j10) {
            long jM3469convertvNxB06k = Color.m3469convertvNxB06k(j10, ColorSpaces.INSTANCE.getOklab());
            return new AnimationVector4D(Color.m3466component4impl(jM3469convertvNxB06k), Color.m3463component1impl(jM3469convertvNxB06k), Color.m3464component2impl(jM3469convertvNxB06k), Color.m3465component3impl(jM3469convertvNxB06k));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.ColorVectorConverterKt$ColorToVector$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/graphics/Color;", "vector", "Landroidx/compose/animation/core/AnimationVector4D;", "invoke-vNxB06k", "(Landroidx/compose/animation/core/AnimationVector4D;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<AnimationVector4D, Color> {
        final /* synthetic */ ColorSpace $colorSpace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ColorSpace colorSpace) {
            super(1);
            this.$colorSpace = colorSpace;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Color.m3462boximpl(m36invokevNxB06k((AnimationVector4D) obj));
        }

        /* JADX INFO: renamed from: invoke-vNxB06k, reason: not valid java name */
        public final long m36invokevNxB06k(AnimationVector4D animationVector4D) {
            return Color.m3469convertvNxB06k(ColorKt.Color(d.m(animationVector4D.getV2(), 0.0f, 1.0f), d.m(animationVector4D.getV3(), -0.5f, 0.5f), d.m(animationVector4D.getV4(), -0.5f, 0.5f), d.m(animationVector4D.getV1(), 0.0f, 1.0f), ColorSpaces.INSTANCE.getOklab()), this.$colorSpace);
        }
    }

    public ColorVectorConverterKt$ColorToVector$1() {
        super(1);
    }

    @Override // r7.l
    public final TwoWayConverter<Color, AnimationVector4D> invoke(ColorSpace colorSpace) {
        return VectorConvertersKt.TwoWayConverter(AnonymousClass1.INSTANCE, new AnonymousClass2(colorSpace));
    }
}
