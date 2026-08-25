package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\u0002HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0007R\u001a\u0010\u001a\u001a\u00020\u00028VX\u0096\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\"\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"Landroidx/compose/ui/text/style/ColorStyle;", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "Landroidx/compose/ui/graphics/Color;", "value", "<init>", "(JLkotlin/jvm/internal/h;)V", "component1-0d7_KjU", "()J", "component1", "copy-8_81llA", "(J)Landroidx/compose/ui/text/style/ColorStyle;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getValue-0d7_KjU", "getColor-0d7_KjU", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Brush;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "brush", "", "getAlpha", "()F", "alpha", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class ColorStyle implements TextForegroundStyle {
    private final long value;

    public /* synthetic */ ColorStyle(long j10, h hVar) {
        this(j10);
    }

    /* JADX INFO: renamed from: copy-8_81llA$default, reason: not valid java name */
    public static /* synthetic */ ColorStyle m5481copy8_81llA$default(ColorStyle colorStyle, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = colorStyle.value;
        }
        return colorStyle.m5483copy8_81llA(j10);
    }

    /* JADX INFO: renamed from: component1-0d7_KjU, reason: not valid java name and from getter */
    public final long getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: copy-8_81llA, reason: not valid java name */
    public final ColorStyle m5483copy8_81llA(long value) {
        return new ColorStyle(value, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ColorStyle) && Color.m3473equalsimpl0(this.value, ((ColorStyle) other).value);
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    public float getAlpha() {
        return Color.m3474getAlphaimpl(mo5480getColor0d7_KjU());
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    public Brush getBrush() {
        return null;
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    /* JADX INFO: renamed from: getColor-0d7_KjU */
    public long mo5480getColor0d7_KjU() {
        return this.value;
    }

    /* JADX INFO: renamed from: getValue-0d7_KjU, reason: not valid java name */
    public final long m5484getValue0d7_KjU() {
        return this.value;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.value);
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    public final /* synthetic */ TextForegroundStyle merge(TextForegroundStyle textForegroundStyle) {
        return TextForegroundStyle.CC.a(this, textForegroundStyle);
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    public final /* synthetic */ TextForegroundStyle takeOrElse(a aVar) {
        return TextForegroundStyle.CC.b(this, aVar);
    }

    public String toString() {
        return "ColorStyle(value=" + ((Object) Color.m3480toStringimpl(this.value)) + ')';
    }

    private ColorStyle(long j10) {
        this.value = j10;
        if (j10 == Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            throw new IllegalArgumentException("ColorStyle value must be specified, use TextForegroundStyle.Unspecified instead.");
        }
    }
}
