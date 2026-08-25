package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.SolidColor;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\b`\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015J\u0017\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\u0006\u001a\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u000b\u001a\u00020\b8&X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/style/TextForegroundStyle;", "", "other", "merge", "(Landroidx/compose/ui/text/style/TextForegroundStyle;)Landroidx/compose/ui/text/style/TextForegroundStyle;", "Lkotlin/Function0;", "takeOrElse", "(Lr7/a;)Landroidx/compose/ui/text/style/TextForegroundStyle;", "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Brush;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "brush", "", "getAlpha", "()F", "alpha", "Companion", "Unspecified", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface TextForegroundStyle {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: androidx.compose.ui.text.style.TextForegroundStyle$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static TextForegroundStyle a(TextForegroundStyle textForegroundStyle, TextForegroundStyle textForegroundStyle2) {
            boolean z = textForegroundStyle2 instanceof BrushStyle;
            return (z && (textForegroundStyle instanceof BrushStyle)) ? new BrushStyle(((BrushStyle) textForegroundStyle2).getValue(), TextDrawStyleKt.takeOrElse(textForegroundStyle2.getAlpha(), textForegroundStyle.new AnonymousClass1())) : (!z || (textForegroundStyle instanceof BrushStyle)) ? (z || !(textForegroundStyle instanceof BrushStyle)) ? textForegroundStyle2.takeOrElse(textForegroundStyle.new AnonymousClass2()) : textForegroundStyle : textForegroundStyle2;
        }

        public static TextForegroundStyle b(TextForegroundStyle textForegroundStyle, a aVar) {
            return !p.a(textForegroundStyle, Unspecified.INSTANCE) ? textForegroundStyle : (TextForegroundStyle) aVar.invoke();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/style/TextForegroundStyle$Companion;", "", "()V", "from", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "from-8_81llA", "(J)Landroidx/compose/ui/text/style/TextForegroundStyle;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final TextForegroundStyle from(Brush brush, float alpha) {
            if (brush == null) {
                return Unspecified.INSTANCE;
            }
            if (brush instanceof SolidColor) {
                return m5603from8_81llA(TextDrawStyleKt.m5602modulateDxMtmZc(((SolidColor) brush).getValue(), alpha));
            }
            if (brush instanceof ShaderBrush) {
                return new BrushStyle((ShaderBrush) brush, alpha);
            }
            throw new NoWhenBranchMatchedException();
        }

        /* JADX INFO: renamed from: from-8_81llA, reason: not valid java name */
        public final TextForegroundStyle m5603from8_81llA(long color) {
            return color != Color.INSTANCE.m3508getUnspecified0d7_KjU() ? new ColorStyle(color, null) : Unspecified.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/style/TextForegroundStyle$Unspecified;", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "()V", "alpha", "", "getAlpha", "()F", "brush", "Landroidx/compose/ui/graphics/Brush;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Unspecified implements TextForegroundStyle {
        public static final int $stable = 0;
        public static final Unspecified INSTANCE = new Unspecified();

        private Unspecified() {
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public float getAlpha() {
            return Float.NaN;
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public Brush getBrush() {
            return null;
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        /* JADX INFO: renamed from: getColor-0d7_KjU */
        public long mo5480getColor0d7_KjU() {
            return Color.INSTANCE.m3508getUnspecified0d7_KjU();
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public final /* synthetic */ TextForegroundStyle merge(TextForegroundStyle textForegroundStyle) {
            return CC.a(this, textForegroundStyle);
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public final /* synthetic */ TextForegroundStyle takeOrElse(a aVar) {
            return CC.b(this, aVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.style.TextForegroundStyle$merge$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Float> {
        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Float invoke() {
            return Float.valueOf(TextForegroundStyle.this.getAlpha());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.style.TextForegroundStyle$merge$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<TextForegroundStyle> {
        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final TextForegroundStyle invoke() {
            return TextForegroundStyle.this;
        }
    }

    float getAlpha();

    Brush getBrush();

    /* JADX INFO: renamed from: getColor-0d7_KjU */
    long mo5480getColor0d7_KjU();

    TextForegroundStyle merge(TextForegroundStyle other);

    TextForegroundStyle takeOrElse(a<? extends TextForegroundStyle> other);
}
