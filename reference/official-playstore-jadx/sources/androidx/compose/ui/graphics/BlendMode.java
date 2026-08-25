package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\f\u0010\u0005J\u000f\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/graphics/BlendMode;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class BlendMode {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Clear = m3387constructorimpl(0);
    private static final int Src = m3387constructorimpl(1);
    private static final int Dst = m3387constructorimpl(2);
    private static final int SrcOver = m3387constructorimpl(3);
    private static final int DstOver = m3387constructorimpl(4);
    private static final int SrcIn = m3387constructorimpl(5);
    private static final int DstIn = m3387constructorimpl(6);
    private static final int SrcOut = m3387constructorimpl(7);
    private static final int DstOut = m3387constructorimpl(8);
    private static final int SrcAtop = m3387constructorimpl(9);
    private static final int DstAtop = m3387constructorimpl(10);
    private static final int Xor = m3387constructorimpl(11);
    private static final int Plus = m3387constructorimpl(12);
    private static final int Modulate = m3387constructorimpl(13);
    private static final int Screen = m3387constructorimpl(14);
    private static final int Overlay = m3387constructorimpl(15);
    private static final int Darken = m3387constructorimpl(16);
    private static final int Lighten = m3387constructorimpl(17);
    private static final int ColorDodge = m3387constructorimpl(18);
    private static final int ColorBurn = m3387constructorimpl(19);
    private static final int Hardlight = m3387constructorimpl(20);
    private static final int Softlight = m3387constructorimpl(21);
    private static final int Difference = m3387constructorimpl(22);
    private static final int Exclusion = m3387constructorimpl(23);
    private static final int Multiply = m3387constructorimpl(24);
    private static final int Hue = m3387constructorimpl(25);
    private static final int Saturation = m3387constructorimpl(26);
    private static final int Color = m3387constructorimpl(27);
    private static final int Luminosity = m3387constructorimpl(28);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b<\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0019\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006R\u0019\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0013\u0010\u0006R\u0019\u0010\u0014\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0015\u0010\u0006R\u0019\u0010\u0016\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0017\u0010\u0006R\u0019\u0010\u0018\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0019\u0010\u0006R\u0019\u0010\u001a\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001b\u0010\u0006R\u0019\u0010\u001c\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001d\u0010\u0006R\u0019\u0010\u001e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001f\u0010\u0006R\u0019\u0010 \u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b!\u0010\u0006R\u0019\u0010\"\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b#\u0010\u0006R\u0019\u0010$\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b%\u0010\u0006R\u0019\u0010&\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b'\u0010\u0006R\u0019\u0010(\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b)\u0010\u0006R\u0019\u0010*\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b+\u0010\u0006R\u0019\u0010,\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b-\u0010\u0006R\u0019\u0010.\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b/\u0010\u0006R\u0019\u00100\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b1\u0010\u0006R\u0019\u00102\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b3\u0010\u0006R\u0019\u00104\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b5\u0010\u0006R\u0019\u00106\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b7\u0010\u0006R\u0019\u00108\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b9\u0010\u0006R\u0019\u0010:\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b;\u0010\u0006R\u0019\u0010<\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b=\u0010\u0006R\u0019\u0010>\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b?\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006@"}, d2 = {"Landroidx/compose/ui/graphics/BlendMode$Companion;", "", "()V", "Clear", "Landroidx/compose/ui/graphics/BlendMode;", "getClear-0nO6VwU", "()I", "I", "Color", "getColor-0nO6VwU", "ColorBurn", "getColorBurn-0nO6VwU", "ColorDodge", "getColorDodge-0nO6VwU", "Darken", "getDarken-0nO6VwU", "Difference", "getDifference-0nO6VwU", "Dst", "getDst-0nO6VwU", "DstAtop", "getDstAtop-0nO6VwU", "DstIn", "getDstIn-0nO6VwU", "DstOut", "getDstOut-0nO6VwU", "DstOver", "getDstOver-0nO6VwU", "Exclusion", "getExclusion-0nO6VwU", "Hardlight", "getHardlight-0nO6VwU", "Hue", "getHue-0nO6VwU", "Lighten", "getLighten-0nO6VwU", "Luminosity", "getLuminosity-0nO6VwU", "Modulate", "getModulate-0nO6VwU", "Multiply", "getMultiply-0nO6VwU", "Overlay", "getOverlay-0nO6VwU", "Plus", "getPlus-0nO6VwU", "Saturation", "getSaturation-0nO6VwU", "Screen", "getScreen-0nO6VwU", "Softlight", "getSoftlight-0nO6VwU", "Src", "getSrc-0nO6VwU", "SrcAtop", "getSrcAtop-0nO6VwU", "SrcIn", "getSrcIn-0nO6VwU", "SrcOut", "getSrcOut-0nO6VwU", "SrcOver", "getSrcOver-0nO6VwU", "Xor", "getXor-0nO6VwU", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getClear-0nO6VwU, reason: not valid java name */
        public final int m3393getClear0nO6VwU() {
            return BlendMode.Clear;
        }

        /* JADX INFO: renamed from: getColor-0nO6VwU, reason: not valid java name */
        public final int m3394getColor0nO6VwU() {
            return BlendMode.Color;
        }

        /* JADX INFO: renamed from: getColorBurn-0nO6VwU, reason: not valid java name */
        public final int m3395getColorBurn0nO6VwU() {
            return BlendMode.ColorBurn;
        }

        /* JADX INFO: renamed from: getColorDodge-0nO6VwU, reason: not valid java name */
        public final int m3396getColorDodge0nO6VwU() {
            return BlendMode.ColorDodge;
        }

        /* JADX INFO: renamed from: getDarken-0nO6VwU, reason: not valid java name */
        public final int m3397getDarken0nO6VwU() {
            return BlendMode.Darken;
        }

        /* JADX INFO: renamed from: getDifference-0nO6VwU, reason: not valid java name */
        public final int m3398getDifference0nO6VwU() {
            return BlendMode.Difference;
        }

        /* JADX INFO: renamed from: getDst-0nO6VwU, reason: not valid java name */
        public final int m3399getDst0nO6VwU() {
            return BlendMode.Dst;
        }

        /* JADX INFO: renamed from: getDstAtop-0nO6VwU, reason: not valid java name */
        public final int m3400getDstAtop0nO6VwU() {
            return BlendMode.DstAtop;
        }

        /* JADX INFO: renamed from: getDstIn-0nO6VwU, reason: not valid java name */
        public final int m3401getDstIn0nO6VwU() {
            return BlendMode.DstIn;
        }

        /* JADX INFO: renamed from: getDstOut-0nO6VwU, reason: not valid java name */
        public final int m3402getDstOut0nO6VwU() {
            return BlendMode.DstOut;
        }

        /* JADX INFO: renamed from: getDstOver-0nO6VwU, reason: not valid java name */
        public final int m3403getDstOver0nO6VwU() {
            return BlendMode.DstOver;
        }

        /* JADX INFO: renamed from: getExclusion-0nO6VwU, reason: not valid java name */
        public final int m3404getExclusion0nO6VwU() {
            return BlendMode.Exclusion;
        }

        /* JADX INFO: renamed from: getHardlight-0nO6VwU, reason: not valid java name */
        public final int m3405getHardlight0nO6VwU() {
            return BlendMode.Hardlight;
        }

        /* JADX INFO: renamed from: getHue-0nO6VwU, reason: not valid java name */
        public final int m3406getHue0nO6VwU() {
            return BlendMode.Hue;
        }

        /* JADX INFO: renamed from: getLighten-0nO6VwU, reason: not valid java name */
        public final int m3407getLighten0nO6VwU() {
            return BlendMode.Lighten;
        }

        /* JADX INFO: renamed from: getLuminosity-0nO6VwU, reason: not valid java name */
        public final int m3408getLuminosity0nO6VwU() {
            return BlendMode.Luminosity;
        }

        /* JADX INFO: renamed from: getModulate-0nO6VwU, reason: not valid java name */
        public final int m3409getModulate0nO6VwU() {
            return BlendMode.Modulate;
        }

        /* JADX INFO: renamed from: getMultiply-0nO6VwU, reason: not valid java name */
        public final int m3410getMultiply0nO6VwU() {
            return BlendMode.Multiply;
        }

        /* JADX INFO: renamed from: getOverlay-0nO6VwU, reason: not valid java name */
        public final int m3411getOverlay0nO6VwU() {
            return BlendMode.Overlay;
        }

        /* JADX INFO: renamed from: getPlus-0nO6VwU, reason: not valid java name */
        public final int m3412getPlus0nO6VwU() {
            return BlendMode.Plus;
        }

        /* JADX INFO: renamed from: getSaturation-0nO6VwU, reason: not valid java name */
        public final int m3413getSaturation0nO6VwU() {
            return BlendMode.Saturation;
        }

        /* JADX INFO: renamed from: getScreen-0nO6VwU, reason: not valid java name */
        public final int m3414getScreen0nO6VwU() {
            return BlendMode.Screen;
        }

        /* JADX INFO: renamed from: getSoftlight-0nO6VwU, reason: not valid java name */
        public final int m3415getSoftlight0nO6VwU() {
            return BlendMode.Softlight;
        }

        /* JADX INFO: renamed from: getSrc-0nO6VwU, reason: not valid java name */
        public final int m3416getSrc0nO6VwU() {
            return BlendMode.Src;
        }

        /* JADX INFO: renamed from: getSrcAtop-0nO6VwU, reason: not valid java name */
        public final int m3417getSrcAtop0nO6VwU() {
            return BlendMode.SrcAtop;
        }

        /* JADX INFO: renamed from: getSrcIn-0nO6VwU, reason: not valid java name */
        public final int m3418getSrcIn0nO6VwU() {
            return BlendMode.SrcIn;
        }

        /* JADX INFO: renamed from: getSrcOut-0nO6VwU, reason: not valid java name */
        public final int m3419getSrcOut0nO6VwU() {
            return BlendMode.SrcOut;
        }

        /* JADX INFO: renamed from: getSrcOver-0nO6VwU, reason: not valid java name */
        public final int m3420getSrcOver0nO6VwU() {
            return BlendMode.SrcOver;
        }

        /* JADX INFO: renamed from: getXor-0nO6VwU, reason: not valid java name */
        public final int m3421getXor0nO6VwU() {
            return BlendMode.Xor;
        }

        private Companion() {
        }
    }

    private /* synthetic */ BlendMode(int i10) {
        this.value = i10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BlendMode m3386boximpl(int i10) {
        return new BlendMode(i10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m3387constructorimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3388equalsimpl(int i10, Object obj) {
        return (obj instanceof BlendMode) && i10 == ((BlendMode) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3389equalsimpl0(int i10, int i11) {
        return i10 == i11;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3390hashCodeimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3391toStringimpl(int i10) {
        return m3389equalsimpl0(i10, Clear) ? "Clear" : m3389equalsimpl0(i10, Src) ? "Src" : m3389equalsimpl0(i10, Dst) ? "Dst" : m3389equalsimpl0(i10, SrcOver) ? "SrcOver" : m3389equalsimpl0(i10, DstOver) ? "DstOver" : m3389equalsimpl0(i10, SrcIn) ? "SrcIn" : m3389equalsimpl0(i10, DstIn) ? "DstIn" : m3389equalsimpl0(i10, SrcOut) ? "SrcOut" : m3389equalsimpl0(i10, DstOut) ? "DstOut" : m3389equalsimpl0(i10, SrcAtop) ? "SrcAtop" : m3389equalsimpl0(i10, DstAtop) ? "DstAtop" : m3389equalsimpl0(i10, Xor) ? "Xor" : m3389equalsimpl0(i10, Plus) ? "Plus" : m3389equalsimpl0(i10, Modulate) ? "Modulate" : m3389equalsimpl0(i10, Screen) ? "Screen" : m3389equalsimpl0(i10, Overlay) ? "Overlay" : m3389equalsimpl0(i10, Darken) ? "Darken" : m3389equalsimpl0(i10, Lighten) ? "Lighten" : m3389equalsimpl0(i10, ColorDodge) ? "ColorDodge" : m3389equalsimpl0(i10, ColorBurn) ? "ColorBurn" : m3389equalsimpl0(i10, Hardlight) ? "HardLight" : m3389equalsimpl0(i10, Softlight) ? "Softlight" : m3389equalsimpl0(i10, Difference) ? "Difference" : m3389equalsimpl0(i10, Exclusion) ? "Exclusion" : m3389equalsimpl0(i10, Multiply) ? "Multiply" : m3389equalsimpl0(i10, Hue) ? "Hue" : m3389equalsimpl0(i10, Saturation) ? "Saturation" : m3389equalsimpl0(i10, Color) ? "Color" : m3389equalsimpl0(i10, Luminosity) ? "Luminosity" : "Unknown";
    }

    public boolean equals(Object obj) {
        return m3388equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m3390hashCodeimpl(this.value);
    }

    public String toString() {
        return m3391toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }
}
