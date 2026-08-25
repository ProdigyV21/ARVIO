package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR7\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R+\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00058F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/SliderPositions;", "", "Lx7/e;", "", "initialActiveRange", "", "initialTickFractions", "<init>", "(Lx7/e;[F)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "<set-?>", "activeRange$delegate", "Landroidx/compose/runtime/MutableState;", "getActiveRange", "()Lx7/e;", "setActiveRange$material3_release", "(Lx7/e;)V", "activeRange", "tickFractions$delegate", "getTickFractions", "()[F", "setTickFractions$material3_release", "([F)V", "tickFractions", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@x6.e
public final class SliderPositions {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: activeRange$delegate, reason: from kotlin metadata */
    private final MutableState activeRange;

    /* JADX INFO: renamed from: tickFractions$delegate, reason: from kotlin metadata */
    private final MutableState tickFractions;

    public SliderPositions() {
        this(null, null, 3, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SliderPositions)) {
            return false;
        }
        SliderPositions sliderPositions = (SliderPositions) other;
        return p.a(getActiveRange(), sliderPositions.getActiveRange()) && Arrays.equals(getTickFractions(), sliderPositions.getTickFractions());
    }

    public final x7.e<Float> getActiveRange() {
        return (x7.e) this.activeRange.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final float[] getTickFractions() {
        return (float[]) this.tickFractions.getValue();
    }

    public int hashCode() {
        return Arrays.hashCode(getTickFractions()) + (getActiveRange().hashCode() * 31);
    }

    public final void setActiveRange$material3_release(x7.e<Float> eVar) {
        this.activeRange.setValue(eVar);
    }

    public final void setTickFractions$material3_release(float[] fArr) {
        this.tickFractions.setValue(fArr);
    }

    public SliderPositions(x7.e eVar, float[] fArr, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? new x7.d(0.0f, 1.0f) : eVar, (i10 & 2) != 0 ? new float[0] : fArr);
    }

    public SliderPositions(x7.e<Float> eVar, float[] fArr) {
        this.activeRange = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(eVar, null, 2, null);
        this.tickFractions = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fArr, null, 2, null);
    }
}
