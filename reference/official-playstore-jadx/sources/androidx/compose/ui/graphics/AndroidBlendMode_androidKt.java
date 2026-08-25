package androidx.compose.ui.graphics;

import android.graphics.PorterDuff;
import android.os.Build;
import androidx.compose.ui.graphics.BlendMode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0011\u0010\t\u001a\u00020\u0002*\u00020\u0006H\u0001¢\u0006\u0002\u0010\n\u001a\u0016\u0010\u000b\u001a\u00020\f*\u00020\u0002H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000f"}, d2 = {"isSupported", "", "Landroidx/compose/ui/graphics/BlendMode;", "isSupported-s9anfk8", "(I)Z", "toAndroidBlendMode", "Landroid/graphics/BlendMode;", "toAndroidBlendMode-s9anfk8", "(I)Landroid/graphics/BlendMode;", "toComposeBlendMode", "(Landroid/graphics/BlendMode;)I", "toPorterDuffMode", "Landroid/graphics/PorterDuff$Mode;", "toPorterDuffMode-s9anfk8", "(I)Landroid/graphics/PorterDuff$Mode;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidBlendMode_androidKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[android.graphics.BlendMode.values().length];
            try {
                iArr[android.graphics.BlendMode.CLEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[android.graphics.BlendMode.DST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_OVER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_OVER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_IN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_IN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_OUT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_OUT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_ATOP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_ATOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[android.graphics.BlendMode.XOR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[android.graphics.BlendMode.PLUS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[android.graphics.BlendMode.MODULATE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[android.graphics.BlendMode.SCREEN.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[android.graphics.BlendMode.OVERLAY.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[android.graphics.BlendMode.DARKEN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[android.graphics.BlendMode.LIGHTEN.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR_DODGE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR_BURN.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[android.graphics.BlendMode.HARD_LIGHT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[android.graphics.BlendMode.SOFT_LIGHT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[android.graphics.BlendMode.DIFFERENCE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[android.graphics.BlendMode.EXCLUSION.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[android.graphics.BlendMode.MULTIPLY.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[android.graphics.BlendMode.HUE.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[android.graphics.BlendMode.SATURATION.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[android.graphics.BlendMode.LUMINOSITY.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: isSupported-s9anfk8, reason: not valid java name */
    public static final boolean m3327isSupporteds9anfk8(int i10) {
        return Build.VERSION.SDK_INT >= 29 || BlendMode.m3389equalsimpl0(i10, BlendMode.INSTANCE.m3420getSrcOver0nO6VwU()) || m3329toPorterDuffModes9anfk8(i10) != PorterDuff.Mode.SRC_OVER;
    }

    /* JADX INFO: renamed from: toAndroidBlendMode-s9anfk8, reason: not valid java name */
    public static final android.graphics.BlendMode m3328toAndroidBlendModes9anfk8(int i10) {
        BlendMode.Companion companion = BlendMode.INSTANCE;
        return BlendMode.m3389equalsimpl0(i10, companion.m3393getClear0nO6VwU()) ? android.graphics.BlendMode.CLEAR : BlendMode.m3389equalsimpl0(i10, companion.m3416getSrc0nO6VwU()) ? android.graphics.BlendMode.SRC : BlendMode.m3389equalsimpl0(i10, companion.m3399getDst0nO6VwU()) ? android.graphics.BlendMode.DST : BlendMode.m3389equalsimpl0(i10, companion.m3420getSrcOver0nO6VwU()) ? android.graphics.BlendMode.SRC_OVER : BlendMode.m3389equalsimpl0(i10, companion.m3403getDstOver0nO6VwU()) ? android.graphics.BlendMode.DST_OVER : BlendMode.m3389equalsimpl0(i10, companion.m3418getSrcIn0nO6VwU()) ? android.graphics.BlendMode.SRC_IN : BlendMode.m3389equalsimpl0(i10, companion.m3401getDstIn0nO6VwU()) ? android.graphics.BlendMode.DST_IN : BlendMode.m3389equalsimpl0(i10, companion.m3419getSrcOut0nO6VwU()) ? android.graphics.BlendMode.SRC_OUT : BlendMode.m3389equalsimpl0(i10, companion.m3402getDstOut0nO6VwU()) ? android.graphics.BlendMode.DST_OUT : BlendMode.m3389equalsimpl0(i10, companion.m3417getSrcAtop0nO6VwU()) ? android.graphics.BlendMode.SRC_ATOP : BlendMode.m3389equalsimpl0(i10, companion.m3400getDstAtop0nO6VwU()) ? android.graphics.BlendMode.DST_ATOP : BlendMode.m3389equalsimpl0(i10, companion.m3421getXor0nO6VwU()) ? android.graphics.BlendMode.XOR : BlendMode.m3389equalsimpl0(i10, companion.m3412getPlus0nO6VwU()) ? android.graphics.BlendMode.PLUS : BlendMode.m3389equalsimpl0(i10, companion.m3409getModulate0nO6VwU()) ? android.graphics.BlendMode.MODULATE : BlendMode.m3389equalsimpl0(i10, companion.m3414getScreen0nO6VwU()) ? android.graphics.BlendMode.SCREEN : BlendMode.m3389equalsimpl0(i10, companion.m3411getOverlay0nO6VwU()) ? android.graphics.BlendMode.OVERLAY : BlendMode.m3389equalsimpl0(i10, companion.m3397getDarken0nO6VwU()) ? android.graphics.BlendMode.DARKEN : BlendMode.m3389equalsimpl0(i10, companion.m3407getLighten0nO6VwU()) ? android.graphics.BlendMode.LIGHTEN : BlendMode.m3389equalsimpl0(i10, companion.m3396getColorDodge0nO6VwU()) ? android.graphics.BlendMode.COLOR_DODGE : BlendMode.m3389equalsimpl0(i10, companion.m3395getColorBurn0nO6VwU()) ? android.graphics.BlendMode.COLOR_BURN : BlendMode.m3389equalsimpl0(i10, companion.m3405getHardlight0nO6VwU()) ? android.graphics.BlendMode.HARD_LIGHT : BlendMode.m3389equalsimpl0(i10, companion.m3415getSoftlight0nO6VwU()) ? android.graphics.BlendMode.SOFT_LIGHT : BlendMode.m3389equalsimpl0(i10, companion.m3398getDifference0nO6VwU()) ? android.graphics.BlendMode.DIFFERENCE : BlendMode.m3389equalsimpl0(i10, companion.m3404getExclusion0nO6VwU()) ? android.graphics.BlendMode.EXCLUSION : BlendMode.m3389equalsimpl0(i10, companion.m3410getMultiply0nO6VwU()) ? android.graphics.BlendMode.MULTIPLY : BlendMode.m3389equalsimpl0(i10, companion.m3406getHue0nO6VwU()) ? android.graphics.BlendMode.HUE : BlendMode.m3389equalsimpl0(i10, companion.m3413getSaturation0nO6VwU()) ? android.graphics.BlendMode.SATURATION : BlendMode.m3389equalsimpl0(i10, companion.m3394getColor0nO6VwU()) ? android.graphics.BlendMode.COLOR : BlendMode.m3389equalsimpl0(i10, companion.m3408getLuminosity0nO6VwU()) ? android.graphics.BlendMode.LUMINOSITY : android.graphics.BlendMode.SRC_OVER;
    }

    public static final int toComposeBlendMode(android.graphics.BlendMode blendMode) {
        switch (WhenMappings.$EnumSwitchMapping$0[blendMode.ordinal()]) {
            case 1:
                return BlendMode.INSTANCE.m3393getClear0nO6VwU();
            case 2:
                return BlendMode.INSTANCE.m3416getSrc0nO6VwU();
            case 3:
                return BlendMode.INSTANCE.m3399getDst0nO6VwU();
            case 4:
                return BlendMode.INSTANCE.m3420getSrcOver0nO6VwU();
            case 5:
                return BlendMode.INSTANCE.m3403getDstOver0nO6VwU();
            case 6:
                return BlendMode.INSTANCE.m3418getSrcIn0nO6VwU();
            case 7:
                return BlendMode.INSTANCE.m3401getDstIn0nO6VwU();
            case 8:
                return BlendMode.INSTANCE.m3419getSrcOut0nO6VwU();
            case 9:
                return BlendMode.INSTANCE.m3402getDstOut0nO6VwU();
            case 10:
                return BlendMode.INSTANCE.m3417getSrcAtop0nO6VwU();
            case 11:
                return BlendMode.INSTANCE.m3400getDstAtop0nO6VwU();
            case 12:
                return BlendMode.INSTANCE.m3421getXor0nO6VwU();
            case 13:
                return BlendMode.INSTANCE.m3412getPlus0nO6VwU();
            case 14:
                return BlendMode.INSTANCE.m3409getModulate0nO6VwU();
            case 15:
                return BlendMode.INSTANCE.m3414getScreen0nO6VwU();
            case 16:
                return BlendMode.INSTANCE.m3411getOverlay0nO6VwU();
            case 17:
                return BlendMode.INSTANCE.m3397getDarken0nO6VwU();
            case 18:
                return BlendMode.INSTANCE.m3407getLighten0nO6VwU();
            case 19:
                return BlendMode.INSTANCE.m3396getColorDodge0nO6VwU();
            case 20:
                return BlendMode.INSTANCE.m3395getColorBurn0nO6VwU();
            case 21:
                return BlendMode.INSTANCE.m3405getHardlight0nO6VwU();
            case 22:
                return BlendMode.INSTANCE.m3415getSoftlight0nO6VwU();
            case 23:
                return BlendMode.INSTANCE.m3398getDifference0nO6VwU();
            case 24:
                return BlendMode.INSTANCE.m3404getExclusion0nO6VwU();
            case 25:
                return BlendMode.INSTANCE.m3410getMultiply0nO6VwU();
            case 26:
                return BlendMode.INSTANCE.m3406getHue0nO6VwU();
            case 27:
                return BlendMode.INSTANCE.m3413getSaturation0nO6VwU();
            case 28:
                return BlendMode.INSTANCE.m3394getColor0nO6VwU();
            case 29:
                return BlendMode.INSTANCE.m3408getLuminosity0nO6VwU();
            default:
                return BlendMode.INSTANCE.m3420getSrcOver0nO6VwU();
        }
    }

    /* JADX INFO: renamed from: toPorterDuffMode-s9anfk8, reason: not valid java name */
    public static final PorterDuff.Mode m3329toPorterDuffModes9anfk8(int i10) {
        BlendMode.Companion companion = BlendMode.INSTANCE;
        return BlendMode.m3389equalsimpl0(i10, companion.m3393getClear0nO6VwU()) ? PorterDuff.Mode.CLEAR : BlendMode.m3389equalsimpl0(i10, companion.m3416getSrc0nO6VwU()) ? PorterDuff.Mode.SRC : BlendMode.m3389equalsimpl0(i10, companion.m3399getDst0nO6VwU()) ? PorterDuff.Mode.DST : BlendMode.m3389equalsimpl0(i10, companion.m3420getSrcOver0nO6VwU()) ? PorterDuff.Mode.SRC_OVER : BlendMode.m3389equalsimpl0(i10, companion.m3403getDstOver0nO6VwU()) ? PorterDuff.Mode.DST_OVER : BlendMode.m3389equalsimpl0(i10, companion.m3418getSrcIn0nO6VwU()) ? PorterDuff.Mode.SRC_IN : BlendMode.m3389equalsimpl0(i10, companion.m3401getDstIn0nO6VwU()) ? PorterDuff.Mode.DST_IN : BlendMode.m3389equalsimpl0(i10, companion.m3419getSrcOut0nO6VwU()) ? PorterDuff.Mode.SRC_OUT : BlendMode.m3389equalsimpl0(i10, companion.m3402getDstOut0nO6VwU()) ? PorterDuff.Mode.DST_OUT : BlendMode.m3389equalsimpl0(i10, companion.m3417getSrcAtop0nO6VwU()) ? PorterDuff.Mode.SRC_ATOP : BlendMode.m3389equalsimpl0(i10, companion.m3400getDstAtop0nO6VwU()) ? PorterDuff.Mode.DST_ATOP : BlendMode.m3389equalsimpl0(i10, companion.m3421getXor0nO6VwU()) ? PorterDuff.Mode.XOR : BlendMode.m3389equalsimpl0(i10, companion.m3412getPlus0nO6VwU()) ? PorterDuff.Mode.ADD : BlendMode.m3389equalsimpl0(i10, companion.m3414getScreen0nO6VwU()) ? PorterDuff.Mode.SCREEN : BlendMode.m3389equalsimpl0(i10, companion.m3411getOverlay0nO6VwU()) ? PorterDuff.Mode.OVERLAY : BlendMode.m3389equalsimpl0(i10, companion.m3397getDarken0nO6VwU()) ? PorterDuff.Mode.DARKEN : BlendMode.m3389equalsimpl0(i10, companion.m3407getLighten0nO6VwU()) ? PorterDuff.Mode.LIGHTEN : BlendMode.m3389equalsimpl0(i10, companion.m3409getModulate0nO6VwU()) ? PorterDuff.Mode.MULTIPLY : PorterDuff.Mode.SRC_OVER;
    }
}
