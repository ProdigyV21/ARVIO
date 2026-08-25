package androidx.tv.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;

/* JADX INFO: loaded from: classes3.dex */
public final class p1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Saver f6292b = SaverKt.Saver(k0.f6148m, s.f6361q);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MutableState f6293a;

    public p1(q1 q1Var) {
        this.f6293a = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(q1Var, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final q1 a() {
        return (q1) this.f6293a.getValue();
    }
}
