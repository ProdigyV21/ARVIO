package androidx.tv.material3;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;

/* JADX INFO: loaded from: classes3.dex */
public final class l0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Saver f6183d = SaverKt.Saver(k0.f6147l, s.f6360p);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MutableIntState f6184a = SnapshotIntStateKt.mutableIntStateOf(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MutableIntState f6185b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f6186c;

    public l0(int i10) {
        this.f6185b = SnapshotIntStateKt.mutableIntStateOf(i10);
    }

    public final void a(int i10) {
        if (i10 == 0) {
            return;
        }
        this.f6186c = false;
        MutableIntState mutableIntState = this.f6185b;
        int intValue = mutableIntState.getIntValue() + 1;
        int i11 = intValue % i10;
        mutableIntState.setIntValue(i11 != 0 ? (((intValue ^ i10) >> 31) | 1) > 0 ? i11 : i11 + i10 : 0);
    }

    public final void b(int i10) {
        if (i10 == 0) {
            return;
        }
        this.f6186c = true;
        MutableIntState mutableIntState = this.f6185b;
        int intValue = mutableIntState.getIntValue() - 1;
        int i11 = intValue % i10;
        if (i11 == 0) {
            i11 = 0;
        } else if ((1 | ((intValue ^ i10) >> 31)) <= 0) {
            i11 += i10;
        }
        mutableIntState.setIntValue(i11);
    }
}
