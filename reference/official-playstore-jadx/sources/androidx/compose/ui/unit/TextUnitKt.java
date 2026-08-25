package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0014\u001a \u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\f\u001a\u00020\u0004*\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\u000f\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0006\u001a\u001f\u0010\u000f\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0011\u001a\u001f\u0010\u000f\u001a\u00020\u0004*\u00020\u00122\u0006\u0010\r\u001a\u00020\u0004H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0013\u001a\u001f\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001a\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0004H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\"\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a*\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a*\u0010)\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\"\u0014\u0010*\u001a\u00020\u00148\u0002X\u0082T¢\u0006\u0006\n\u0004\b*\u0010+\"\u0014\u0010,\u001a\u00020\u00148\u0002X\u0082T¢\u0006\u0006\n\u0004\b,\u0010+\"\u0014\u0010-\u001a\u00020\u00148\u0002X\u0082T¢\u0006\u0006\n\u0004\b-\u0010+\"\u0014\u0010.\u001a\u00020\u00148\u0002X\u0082T¢\u0006\u0006\n\u0004\b.\u0010+\"\u001f\u00103\u001a\u00020/*\u00020\u00048Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b2\u0010\u001c\u001a\u0004\b0\u00101\"\u001e\u00106\u001a\u00020/*\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b5\u0010\u001c\u001a\u0004\b4\u00101\"\u001e\u0010;\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u0010:\u001a\u0004\b7\u00108\"\u001e\u0010>\u001a\u00020\u0004*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b=\u0010:\u001a\u0004\b<\u00108\"\u001e\u0010;\u001a\u00020\u0004*\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u0010@\u001a\u0004\b7\u0010?\"\u001e\u0010>\u001a\u00020\u0004*\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\b=\u0010@\u001a\u0004\b<\u0010?\"\u001e\u0010;\u001a\u00020\u0004*\u00020\u00128FX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u0010B\u001a\u0004\b7\u0010A\"\u001e\u0010>\u001a\u00020\u0004*\u00020\u00128FX\u0087\u0004¢\u0006\f\u0012\u0004\b=\u0010B\u001a\u0004\b<\u0010A\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"", "value", "Landroidx/compose/ui/unit/TextUnitType;", LinkHeader.Parameters.Type, "Landroidx/compose/ui/unit/TextUnit;", "TextUnit-anM5pPY", "(FJ)J", "TextUnit", "Lkotlin/Function0;", "block", "takeOrElse-eAf_CNQ", "(JLr7/a;)J", "takeOrElse", "other", "times-mpE4wyQ", "times", "", "(DJ)J", "", "(IJ)J", "", "unitType", "v", "pack", "(JF)J", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Lx6/t0;", "checkArithmetic--R2X_6o", "(J)V", "checkArithmetic", "b", "checkArithmetic-NB67dxo", "(JJ)V", "c", "checkArithmetic-vU-0ePk", "(JJJ)V", TtmlNode.START, "stop", "fraction", "lerp-C3pnCVY", "(JJF)J", "lerp", "UNIT_MASK", "J", "UNIT_TYPE_UNSPECIFIED", "UNIT_TYPE_SP", "UNIT_TYPE_EM", "", "isSpecified--R2X_6o", "(J)Z", "isSpecified--R2X_6o$annotations", "isSpecified", "isUnspecified--R2X_6o", "isUnspecified--R2X_6o$annotations", "isUnspecified", "getSp", "(F)J", "getSp$annotations", "(F)V", "sp", "getEm", "getEm$annotations", "em", "(D)J", "(D)V", "(I)J", "(I)V", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextUnitKt {
    private static final long UNIT_MASK = 1095216660480L;
    private static final long UNIT_TYPE_EM = 8589934592L;
    private static final long UNIT_TYPE_SP = 4294967296L;
    private static final long UNIT_TYPE_UNSPECIFIED = 0;

    /* JADX INFO: renamed from: TextUnit-anM5pPY, reason: not valid java name */
    public static final long m5877TextUnitanM5pPY(float f10, long j10) {
        return pack(j10, f10);
    }

    /* JADX INFO: renamed from: checkArithmetic--R2X_6o, reason: not valid java name */
    public static final void m5878checkArithmeticR2X_6o(long j10) {
        if (m5883isUnspecifiedR2X_6o(j10)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
    }

    /* JADX INFO: renamed from: checkArithmetic-NB67dxo, reason: not valid java name */
    public static final void m5879checkArithmeticNB67dxo(long j10, long j11) {
        if (m5883isUnspecifiedR2X_6o(j10) || m5883isUnspecifiedR2X_6o(j11)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
        if (TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnit.m5864getTypeUIouoOA(j11))) {
            return;
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) TextUnitType.m5895toStringimpl(TextUnit.m5864getTypeUIouoOA(j10))) + " and " + ((Object) TextUnitType.m5895toStringimpl(TextUnit.m5864getTypeUIouoOA(j11)))).toString());
    }

    /* JADX INFO: renamed from: checkArithmetic-vU-0ePk, reason: not valid java name */
    public static final void m5880checkArithmeticvU0ePk(long j10, long j11, long j12) {
        if (m5883isUnspecifiedR2X_6o(j10) || m5883isUnspecifiedR2X_6o(j11) || m5883isUnspecifiedR2X_6o(j12)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
        if (TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnit.m5864getTypeUIouoOA(j11)) && TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j11), TextUnit.m5864getTypeUIouoOA(j12))) {
            return;
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) TextUnitType.m5895toStringimpl(TextUnit.m5864getTypeUIouoOA(j10))) + " and " + ((Object) TextUnitType.m5895toStringimpl(TextUnit.m5864getTypeUIouoOA(j11)))).toString());
    }

    public static final long getEm(float f10) {
        return pack(UNIT_TYPE_EM, f10);
    }

    public static /* synthetic */ void getEm$annotations(double d4) {
    }

    public static final long getSp(float f10) {
        return pack(UNIT_TYPE_SP, f10);
    }

    public static /* synthetic */ void getSp$annotations(double d4) {
    }

    /* JADX INFO: renamed from: isSpecified--R2X_6o, reason: not valid java name */
    public static final boolean m5881isSpecifiedR2X_6o(long j10) {
        return !m5883isUnspecifiedR2X_6o(j10);
    }

    /* JADX INFO: renamed from: isSpecified--R2X_6o$annotations, reason: not valid java name */
    public static /* synthetic */ void m5882isSpecifiedR2X_6o$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified--R2X_6o, reason: not valid java name */
    public static final boolean m5883isUnspecifiedR2X_6o(long j10) {
        return TextUnit.m5863getRawTypeimpl(j10) == 0;
    }

    /* JADX INFO: renamed from: isUnspecified--R2X_6o$annotations, reason: not valid java name */
    public static /* synthetic */ void m5884isUnspecifiedR2X_6o$annotations(long j10) {
    }

    /* JADX INFO: renamed from: lerp-C3pnCVY, reason: not valid java name */
    public static final long m5885lerpC3pnCVY(long j10, long j11, float f10) {
        m5879checkArithmeticNB67dxo(j10, j11);
        return pack(TextUnit.m5863getRawTypeimpl(j10), MathHelpersKt.lerp(TextUnit.m5865getValueimpl(j10), TextUnit.m5865getValueimpl(j11), f10));
    }

    public static final long pack(long j10, float f10) {
        return TextUnit.m5857constructorimpl(j10 | (((long) Float.floatToIntBits(f10)) & 4294967295L));
    }

    /* JADX INFO: renamed from: takeOrElse-eAf_CNQ, reason: not valid java name */
    public static final long m5886takeOrElseeAf_CNQ(long j10, r7.a<TextUnit> aVar) {
        return !m5883isUnspecifiedR2X_6o(j10) ? j10 : ((TextUnit) aVar.invoke()).getPackedValue();
    }

    /* JADX INFO: renamed from: times-mpE4wyQ, reason: not valid java name */
    public static final long m5888timesmpE4wyQ(float f10, long j10) {
        m5878checkArithmeticR2X_6o(j10);
        return pack(TextUnit.m5863getRawTypeimpl(j10), TextUnit.m5865getValueimpl(j10) * f10);
    }

    public static final long getEm(double d4) {
        return pack(UNIT_TYPE_EM, (float) d4);
    }

    public static /* synthetic */ void getEm$annotations(float f10) {
    }

    public static final long getSp(double d4) {
        return pack(UNIT_TYPE_SP, (float) d4);
    }

    public static /* synthetic */ void getSp$annotations(float f10) {
    }

    public static final long getEm(int i10) {
        return pack(UNIT_TYPE_EM, i10);
    }

    public static /* synthetic */ void getEm$annotations(int i10) {
    }

    public static final long getSp(int i10) {
        return pack(UNIT_TYPE_SP, i10);
    }

    public static /* synthetic */ void getSp$annotations(int i10) {
    }

    /* JADX INFO: renamed from: times-mpE4wyQ, reason: not valid java name */
    public static final long m5887timesmpE4wyQ(double d4, long j10) {
        m5878checkArithmeticR2X_6o(j10);
        return pack(TextUnit.m5863getRawTypeimpl(j10), TextUnit.m5865getValueimpl(j10) * ((float) d4));
    }

    /* JADX INFO: renamed from: times-mpE4wyQ, reason: not valid java name */
    public static final long m5889timesmpE4wyQ(int i10, long j10) {
        m5878checkArithmeticR2X_6o(j10);
        return pack(TextUnit.m5863getRawTypeimpl(j10), TextUnit.m5865getValueimpl(j10) * i10);
    }
}
