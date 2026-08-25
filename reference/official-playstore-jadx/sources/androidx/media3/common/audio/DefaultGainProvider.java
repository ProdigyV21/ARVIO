package androidx.media3.common.audio;

import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.audio.DefaultGainProvider;
import androidx.media3.common.audio.GainProcessor;
import androidx.media3.common.util.Util;
import com.google.common.base.m;
import com.google.common.collect.j3;
import com.google.common.collect.n0;
import com.google.common.collect.n4;
import com.google.common.collect.v2;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultGainProvider implements GainProcessor.GainProvider {
    private static final float GAIN_UNSET = -3.4028235E38f;
    private final float defaultGain;
    private final n4 gainMap;
    public static final FadeProvider FADE_IN_LINEAR = new androidx.credentials.d(26);
    public static final FadeProvider FADE_OUT_LINEAR = new androidx.credentials.d(27);
    public static final FadeProvider FADE_IN_EQUAL_POWER = new androidx.credentials.d(28);
    public static final FadeProvider FADE_OUT_EQUAL_POWER = new androidx.credentials.d(29);

    public static final class Builder {
        private final float defaultGain;
        private final n4 gainMap;

        public Builder(float f10) {
            n4 n4Var = new n4();
            this.gainMap = n4Var;
            this.defaultGain = f10;
            n4Var.c(j3.f14034m, new androidx.media3.common.e(14));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Float lambda$addFadeAt$1(long j10, FadeProvider fadeProvider, long j11, Pair pair) {
            int iIntValue = ((Integer) pair.second).intValue();
            return Float.valueOf(fadeProvider.getGainFactorAt(((Long) pair.first).longValue() - Util.durationUsToSampleCount(j10, iIntValue), Util.durationUsToSampleCount(j11, iIntValue)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Float lambda$new$0(Pair pair) {
            return Float.valueOf(-3.4028235E38f);
        }

        public Builder addFadeAt(final long j10, final long j11, final FadeProvider fadeProvider) {
            ac.b.j(j10 >= 0);
            ac.b.j(j11 > 1);
            int i10 = 2;
            this.gainMap.c(new j3(new n0(Long.valueOf(j10), i10), new n0(Long.valueOf(j10 + j11), i10)), new m() { // from class: androidx.media3.common.audio.f
                @Override // com.google.common.base.m
                public final Object apply(Object obj) {
                    return DefaultGainProvider.Builder.lambda$addFadeAt$1(j10, fadeProvider, j11, (Pair) obj);
                }
            });
            return this;
        }

        public DefaultGainProvider build() {
            return new DefaultGainProvider(this.gainMap, this.defaultGain);
        }
    }

    public interface FadeProvider {
        float getGainFactorAt(long j10, long j11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$static$0(long j10, long j11) {
        return j10 / j11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$static$1(long j10, long j11) {
        return (j11 - j10) / j11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$static$2(long j10, long j11) {
        return (float) Math.sin((j10 * 1.5707963267948966d) / j11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$static$3(long j10, long j11) {
        return (float) Math.cos((j10 * 1.5707963267948966d) / j11);
    }

    @Override // androidx.media3.common.audio.GainProcessor.GainProvider
    public float getGainFactorAtSamplePosition(long j10, int i10) {
        ac.b.s(i10 > 0);
        ac.b.j(j10 >= 0);
        Map.Entry entryB = this.gainMap.b(Long.valueOf(Util.sampleCountToDurationUs(j10, i10)));
        m mVar = (m) (entryB == null ? null : entryB.getValue());
        mVar.getClass();
        float fFloatValue = ((Float) mVar.apply(Pair.create(Long.valueOf(j10), Integer.valueOf(i10)))).floatValue();
        return fFloatValue == -3.4028235E38f ? this.defaultGain : fFloatValue;
    }

    @Override // androidx.media3.common.audio.GainProcessor.GainProvider
    public long isUnityUntil(long j10, int i10) {
        ac.b.s(i10 > 0);
        ac.b.j(j10 >= 0);
        Map.Entry entryB = this.gainMap.b(Long.valueOf(Util.sampleCountToDurationUs(j10, i10)));
        entryB.getClass();
        float fFloatValue = ((Float) ((m) entryB.getValue()).apply(Pair.create(Long.valueOf(j10), Integer.valueOf(i10)))).floatValue();
        if (fFloatValue == 1.0f) {
            return j10 + 1;
        }
        if (this.defaultGain != 1.0f || fFloatValue != -3.4028235E38f) {
            return C.TIME_UNSET;
        }
        if (((j3) entryB.getKey()).f14036l != n0.f14063m) {
            return Util.durationUsToSampleCount(((Long) ((j3) entryB.getKey()).f14036l.i()).longValue(), i10);
        }
        return Long.MIN_VALUE;
    }

    private DefaultGainProvider(n4 n4Var, float f10) {
        n4 n4Var2 = new n4();
        this.gainMap = n4Var2;
        v2 v2Var = (v2) n4Var.a();
        new com.google.common.collect.d(v2Var, 1);
        Iterator itC = v2Var.c();
        while (itC.hasNext()) {
            Map.Entry entry = (Map.Entry) itC.next();
            n4Var2.c((j3) entry.getKey(), entry.getValue());
        }
        this.defaultGain = f10;
    }
}
