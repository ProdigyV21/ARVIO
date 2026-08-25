package androidx.compose.ui.platform;

import android.os.Parcel;
import android.util.Base64;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0010J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u000b\u0010\u0016J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001aJ\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eø\u0001\u0000¢\u0006\u0004\b \u0010!J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b\u000b\u0010$J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b\u000b\u0010'J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010)\u001a\u00020(¢\u0006\u0004\b\u000b\u0010*J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010,\u001a\u00020+¢\u0006\u0004\b\u000b\u0010-J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b\u000b\u0010\u001aJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u00101\u001a\u000200¢\u0006\u0004\b\u000b\u0010!J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u00103\u001a\u000202ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0010J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u00106R\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006:"}, d2 = {"Landroidx/compose/ui/platform/EncodeHelper;", "", "<init>", "()V", "Lx6/t0;", "reset", "", "encodedString", "()Ljava/lang/String;", "Landroidx/compose/ui/text/SpanStyle;", "spanStyle", "encode", "(Landroidx/compose/ui/text/SpanStyle;)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "encode-8_81llA", "(J)V", "Landroidx/compose/ui/unit/TextUnit;", "textUnit", "encode--R2X_6o", "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "(Landroidx/compose/ui/text/font/FontWeight;)V", "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_STYLE, "encode-nzbMABs", "(I)V", "Landroidx/compose/ui/text/font/FontSynthesis;", "fontSynthesis", "encode-6p3vJLY", "Landroidx/compose/ui/text/style/BaselineShift;", "baselineShift", "encode-4Dl_Bck", "(F)V", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "textGeometricTransform", "(Landroidx/compose/ui/text/style/TextGeometricTransform;)V", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "(Landroidx/compose/ui/text/style/TextDecoration;)V", "Landroidx/compose/ui/graphics/Shadow;", "shadow", "(Landroidx/compose/ui/graphics/Shadow;)V", "", "byte", "(B)V", "", "int", "", "float", "Lx6/o0;", "uLong", "encode-VKZWuLQ", "string", "(Ljava/lang/String;)V", "Landroid/os/Parcel;", "parcel", "Landroid/os/Parcel;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EncodeHelper {
    public static final int $stable = 8;
    private Parcel parcel = Parcel.obtain();

    public final void encode(SpanStyle spanStyle) {
        long jM5151getColor0d7_KjU = spanStyle.m5151getColor0d7_KjU();
        Color.Companion companion = Color.INSTANCE;
        if (!Color.m3473equalsimpl0(jM5151getColor0d7_KjU, companion.m3508getUnspecified0d7_KjU())) {
            encode((byte) 1);
            m4965encode8_81llA(spanStyle.m5151getColor0d7_KjU());
        }
        long fontSize = spanStyle.getFontSize();
        TextUnit.Companion companion2 = TextUnit.INSTANCE;
        if (!TextUnit.m5862equalsimpl0(fontSize, companion2.m5876getUnspecifiedXSAIIZE())) {
            encode((byte) 2);
            m4962encodeR2X_6o(spanStyle.getFontSize());
        }
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight != null) {
            encode((byte) 3);
            encode(fontWeight);
        }
        FontStyle fontStyle = spanStyle.getFontStyle();
        if (fontStyle != null) {
            int iM5305unboximpl = fontStyle.m5305unboximpl();
            encode((byte) 4);
            m4967encodenzbMABs(iM5305unboximpl);
        }
        FontSynthesis fontSynthesis = spanStyle.getFontSynthesis();
        if (fontSynthesis != null) {
            int value = fontSynthesis.getValue();
            encode((byte) 5);
            m4964encode6p3vJLY(value);
        }
        String fontFeatureSettings = spanStyle.getFontFeatureSettings();
        if (fontFeatureSettings != null) {
            encode((byte) 6);
            encode(fontFeatureSettings);
        }
        if (!TextUnit.m5862equalsimpl0(spanStyle.getLetterSpacing(), companion2.m5876getUnspecifiedXSAIIZE())) {
            encode((byte) 7);
            m4962encodeR2X_6o(spanStyle.getLetterSpacing());
        }
        BaselineShift baselineShift = spanStyle.getBaselineShift();
        if (baselineShift != null) {
            float fM5472unboximpl = baselineShift.m5472unboximpl();
            encode((byte) 8);
            m4963encode4Dl_Bck(fM5472unboximpl);
        }
        TextGeometricTransform textGeometricTransform = spanStyle.getTextGeometricTransform();
        if (textGeometricTransform != null) {
            encode((byte) 9);
            encode(textGeometricTransform);
        }
        if (!Color.m3473equalsimpl0(spanStyle.getBackground(), companion.m3508getUnspecified0d7_KjU())) {
            encode((byte) 10);
            m4965encode8_81llA(spanStyle.getBackground());
        }
        TextDecoration background = spanStyle.getBackground();
        if (background != null) {
            encode((byte) 11);
            encode(background);
        }
        Shadow shadow = spanStyle.getShadow();
        if (shadow != null) {
            encode((byte) 12);
            encode(shadow);
        }
    }

    /* JADX INFO: renamed from: encode--R2X_6o, reason: not valid java name */
    public final void m4962encodeR2X_6o(long textUnit) {
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(textUnit);
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        byte b10 = 0;
        if (!TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5899getUnspecifiedUIouoOA())) {
            if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA())) {
                b10 = 1;
            } else if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA())) {
                b10 = 2;
            }
        }
        encode(b10);
        if (TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(textUnit), companion.m5899getUnspecifiedUIouoOA())) {
            return;
        }
        encode(TextUnit.m5865getValueimpl(textUnit));
    }

    /* JADX INFO: renamed from: encode-4Dl_Bck, reason: not valid java name */
    public final void m4963encode4Dl_Bck(float baselineShift) {
        encode(baselineShift);
    }

    /* JADX INFO: renamed from: encode-6p3vJLY, reason: not valid java name */
    public final void m4964encode6p3vJLY(int fontSynthesis) {
        FontSynthesis.Companion companion = FontSynthesis.INSTANCE;
        byte b10 = 0;
        if (!FontSynthesis.m5313equalsimpl0(fontSynthesis, companion.m5320getNoneGVVA2EU())) {
            if (FontSynthesis.m5313equalsimpl0(fontSynthesis, companion.m5319getAllGVVA2EU())) {
                b10 = 1;
            } else if (FontSynthesis.m5313equalsimpl0(fontSynthesis, companion.m5322getWeightGVVA2EU())) {
                b10 = 2;
            } else if (FontSynthesis.m5313equalsimpl0(fontSynthesis, companion.m5321getStyleGVVA2EU())) {
                b10 = 3;
            }
        }
        encode(b10);
    }

    /* JADX INFO: renamed from: encode-8_81llA, reason: not valid java name */
    public final void m4965encode8_81llA(long color) {
        m4966encodeVKZWuLQ(color);
    }

    /* JADX INFO: renamed from: encode-VKZWuLQ, reason: not valid java name */
    public final void m4966encodeVKZWuLQ(long uLong) {
        this.parcel.writeLong(uLong);
    }

    /* JADX INFO: renamed from: encode-nzbMABs, reason: not valid java name */
    public final void m4967encodenzbMABs(int fontStyle) {
        FontStyle.Companion companion = FontStyle.INSTANCE;
        byte b10 = 0;
        if (!FontStyle.m5302equalsimpl0(fontStyle, companion.m5309getNormal_LCdwA()) && FontStyle.m5302equalsimpl0(fontStyle, companion.m5308getItalic_LCdwA())) {
            b10 = 1;
        }
        encode(b10);
    }

    public final String encodedString() {
        return Base64.encodeToString(this.parcel.marshall(), 0);
    }

    public final void reset() {
        this.parcel.recycle();
        this.parcel = Parcel.obtain();
    }

    public final void encode(FontWeight fontWeight) {
        encode(fontWeight.getWeight());
    }

    public final void encode(TextGeometricTransform textGeometricTransform) {
        encode(textGeometricTransform.getScaleX());
        encode(textGeometricTransform.getSkewX());
    }

    public final void encode(TextDecoration textDecoration) {
        encode(textDecoration.getMask());
    }

    public final void encode(Shadow shadow) {
        m4965encode8_81llA(shadow.getColor());
        encode(Offset.m3236getXimpl(shadow.getOffset()));
        encode(Offset.m3237getYimpl(shadow.getOffset()));
        encode(shadow.getBlurRadius());
    }

    public final void encode(byte b10) {
        this.parcel.writeByte(b10);
    }

    public final void encode(int i10) {
        this.parcel.writeInt(i10);
    }

    public final void encode(float f10) {
        this.parcel.writeFloat(f10);
    }

    public final void encode(String string) {
        this.parcel.writeString(string);
    }
}
