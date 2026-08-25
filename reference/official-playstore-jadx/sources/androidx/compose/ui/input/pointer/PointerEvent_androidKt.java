package androidx.compose.ui.input.pointer;

import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u000b\u001a\r\u0010$\u001a\u00020\u0006H\u0000¢\u0006\u0002\u0010%\u001a\u0014\u0010&\u001a\u00020'*\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u0014\u0010*\u001a\u00020'*\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010)\u001a\u001c\u0010,\u001a\u00020\u0001*\u00020\u00022\u0006\u0010-\u001a\u00020'ø\u0001\u0000¢\u0006\u0004\b.\u0010/\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0004\"\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0004\"\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0004\"\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\r\u0010\u0004\"\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0004\"\u0015\u0010\u0010\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0004\"\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0004\"\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0004\"\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0004\"\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0004\"\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0004\"\u0015\u0010\u001c\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0004\"\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0004\"\u0015\u0010 \u001a\u00020\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b!\u0010\u0004\"\u0015\u0010\"\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b#\u0010\u0004*\f\b\u0000\u00100\"\u00020'2\u00020'*\f\b\u0000\u00101\"\u00020'2\u00020'\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"areAnyPressed", "", "Landroidx/compose/ui/input/pointer/PointerButtons;", "getAreAnyPressed-aHzCx-E", "(I)Z", "isAltGraphPressed", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "isAltGraphPressed-5xRPYO0", "isAltPressed", "isAltPressed-5xRPYO0", "isBackPressed", "isBackPressed-aHzCx-E", "isCapsLockOn", "isCapsLockOn-5xRPYO0", "isCtrlPressed", "isCtrlPressed-5xRPYO0", "isForwardPressed", "isForwardPressed-aHzCx-E", "isFunctionPressed", "isFunctionPressed-5xRPYO0", "isMetaPressed", "isMetaPressed-5xRPYO0", "isNumLockOn", "isNumLockOn-5xRPYO0", "isPrimaryPressed", "isPrimaryPressed-aHzCx-E", "isScrollLockOn", "isScrollLockOn-5xRPYO0", "isSecondaryPressed", "isSecondaryPressed-aHzCx-E", "isShiftPressed", "isShiftPressed-5xRPYO0", "isSymPressed", "isSymPressed-5xRPYO0", "isTertiaryPressed", "isTertiaryPressed-aHzCx-E", "EmptyPointerKeyboardModifiers", "()I", "indexOfFirstPressed", "", "indexOfFirstPressed-aHzCx-E", "(I)I", "indexOfLastPressed", "indexOfLastPressed-aHzCx-E", "isPressed", "buttonIndex", "isPressed-bNIWhpI", "(II)Z", "NativePointerButtons", "NativePointerKeyboardModifiers", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerEvent_androidKt {
    public static final int EmptyPointerKeyboardModifiers() {
        return PointerKeyboardModifiers.m4568constructorimpl(0);
    }

    /* JADX INFO: renamed from: getAreAnyPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m4478getAreAnyPressedaHzCxE(int i10) {
        return i10 != 0;
    }

    /* JADX INFO: renamed from: indexOfFirstPressed-aHzCx-E, reason: not valid java name */
    public static final int m4479indexOfFirstPressedaHzCxE(int i10) {
        if (i10 == 0) {
            return -1;
        }
        int i11 = 0;
        for (int i12 = (i10 & (-97)) | ((i10 & 96) >>> 5); (i12 & 1) == 0; i12 >>>= 1) {
            i11++;
        }
        return i11;
    }

    /* JADX INFO: renamed from: indexOfLastPressed-aHzCx-E, reason: not valid java name */
    public static final int m4480indexOfLastPressedaHzCxE(int i10) {
        int i11 = -1;
        for (int i12 = (i10 & (-97)) | ((i10 & 96) >>> 5); i12 != 0; i12 >>>= 1) {
            i11++;
        }
        return i11;
    }

    /* JADX INFO: renamed from: isAltGraphPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4481isAltGraphPressed5xRPYO0(int i10) {
        return false;
    }

    /* JADX INFO: renamed from: isAltPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4482isAltPressed5xRPYO0(int i10) {
        return (i10 & 2) != 0;
    }

    /* JADX INFO: renamed from: isBackPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m4483isBackPressedaHzCxE(int i10) {
        return (i10 & 8) != 0;
    }

    /* JADX INFO: renamed from: isCapsLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m4484isCapsLockOn5xRPYO0(int i10) {
        return (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0;
    }

    /* JADX INFO: renamed from: isCtrlPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4485isCtrlPressed5xRPYO0(int i10) {
        return (i10 & 4096) != 0;
    }

    /* JADX INFO: renamed from: isForwardPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m4486isForwardPressedaHzCxE(int i10) {
        return (i10 & 16) != 0;
    }

    /* JADX INFO: renamed from: isFunctionPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4487isFunctionPressed5xRPYO0(int i10) {
        return (i10 & 8) != 0;
    }

    /* JADX INFO: renamed from: isMetaPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4488isMetaPressed5xRPYO0(int i10) {
        return (i10 & 65536) != 0;
    }

    /* JADX INFO: renamed from: isNumLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m4489isNumLockOn5xRPYO0(int i10) {
        return (i10 & 2097152) != 0;
    }

    /* JADX INFO: renamed from: isPressed-bNIWhpI, reason: not valid java name */
    public static final boolean m4490isPressedbNIWhpI(int i10, int i11) {
        return i11 != 0 ? i11 != 1 ? (i11 == 2 || i11 == 3 || i11 == 4) ? (i10 & (1 << i11)) != 0 : (i10 & (1 << (i11 + 2))) != 0 : m4493isSecondaryPressedaHzCxE(i10) : m4491isPrimaryPressedaHzCxE(i10);
    }

    /* JADX INFO: renamed from: isPrimaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m4491isPrimaryPressedaHzCxE(int i10) {
        return (i10 & 33) != 0;
    }

    /* JADX INFO: renamed from: isScrollLockOn-5xRPYO0, reason: not valid java name */
    public static final boolean m4492isScrollLockOn5xRPYO0(int i10) {
        return (i10 & 4194304) != 0;
    }

    /* JADX INFO: renamed from: isSecondaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m4493isSecondaryPressedaHzCxE(int i10) {
        return (i10 & 66) != 0;
    }

    /* JADX INFO: renamed from: isShiftPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4494isShiftPressed5xRPYO0(int i10) {
        return (i10 & 1) != 0;
    }

    /* JADX INFO: renamed from: isSymPressed-5xRPYO0, reason: not valid java name */
    public static final boolean m4495isSymPressed5xRPYO0(int i10) {
        return (i10 & 4) != 0;
    }

    /* JADX INFO: renamed from: isTertiaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m4496isTertiaryPressedaHzCxE(int i10) {
        return (i10 & 4) != 0;
    }
}
