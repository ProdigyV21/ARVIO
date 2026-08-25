package androidx.compose.runtime;

import android.os.Looper;
import android.util.Log;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import kotlin.Metadata;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0001\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001f\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001c\u0010\u001d\"\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001f\u0010 \"!\u0010(\u001a\u00020!8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\"\u0010#\u0012\u0004\b&\u0010'\u001a\u0004\b$\u0010%\"\u0014\u0010)\u001a\u00020\u00178\u0002X\u0082T¢\u0006\u0006\n\u0004\b)\u0010*\"\u001a\u0010+\u001a\u00020\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.*\f\b\u0000\u00100\"\u00020/2\u00020/¨\u00061"}, d2 = {"T", "value", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "policy", "Landroidx/compose/runtime/snapshots/SnapshotMutableState;", "createSnapshotMutableState", "(Ljava/lang/Object;Landroidx/compose/runtime/SnapshotMutationPolicy;)Landroidx/compose/runtime/snapshots/SnapshotMutableState;", "", "Landroidx/compose/runtime/MutableIntState;", "createSnapshotMutableIntState", "(I)Landroidx/compose/runtime/MutableIntState;", "", "Landroidx/compose/runtime/MutableLongState;", "createSnapshotMutableLongState", "(J)Landroidx/compose/runtime/MutableLongState;", "", "Landroidx/compose/runtime/MutableFloatState;", "createSnapshotMutableFloatState", "(F)Landroidx/compose/runtime/MutableFloatState;", "", "Landroidx/compose/runtime/MutableDoubleState;", "createSnapshotMutableDoubleState", "(D)Landroidx/compose/runtime/MutableDoubleState;", "", "message", "", "e", "Lx6/t0;", "logError", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "", "DisallowDefaultMonotonicFrameClock", "Z", "Landroidx/compose/runtime/MonotonicFrameClock;", "DefaultMonotonicFrameClock$delegate", "Lx6/s;", "getDefaultMonotonicFrameClock", "()Landroidx/compose/runtime/MonotonicFrameClock;", "getDefaultMonotonicFrameClock$annotations", "()V", "DefaultMonotonicFrameClock", "LogTag", "Ljava/lang/String;", "MainThreadId", "J", "getMainThreadId", "()J", "Lh/j;", "CheckResult", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ActualAndroid_androidKt {
    private static final s DefaultMonotonicFrameClock$delegate = new i0(ActualAndroid_androidKt$DefaultMonotonicFrameClock$2.INSTANCE);
    private static final boolean DisallowDefaultMonotonicFrameClock = false;
    private static final String LogTag = "ComposeInternal";
    private static final long MainThreadId;

    static {
        long id;
        try {
            id = Looper.getMainLooper().getThread().getId();
        } catch (Exception unused) {
            id = -1;
        }
        MainThreadId = id;
    }

    public static final MutableDoubleState createSnapshotMutableDoubleState(double d4) {
        return new ParcelableSnapshotMutableDoubleState(d4);
    }

    public static final MutableFloatState createSnapshotMutableFloatState(float f10) {
        return new ParcelableSnapshotMutableFloatState(f10);
    }

    public static final MutableIntState createSnapshotMutableIntState(int i10) {
        return new ParcelableSnapshotMutableIntState(i10);
    }

    public static final MutableLongState createSnapshotMutableLongState(long j10) {
        return new ParcelableSnapshotMutableLongState(j10);
    }

    public static final <T> SnapshotMutableState<T> createSnapshotMutableState(T t2, SnapshotMutationPolicy<T> snapshotMutationPolicy) {
        return new ParcelableSnapshotMutableState(t2, snapshotMutationPolicy);
    }

    public static final MonotonicFrameClock getDefaultMonotonicFrameClock() {
        return (MonotonicFrameClock) DefaultMonotonicFrameClock$delegate.getValue();
    }

    @x6.e
    public static /* synthetic */ void getDefaultMonotonicFrameClock$annotations() {
    }

    public static final long getMainThreadId() {
        return MainThreadId;
    }

    public static final void logError(String str, Throwable th) {
        Log.e(LogTag, str, th);
    }
}
