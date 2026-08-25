package androidx.core.app;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends a1.a {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static HandlerThread f2029p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static Handler f2030q;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2031l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SparseIntArray[] f2032m = new SparseIntArray[9];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f2033n = new ArrayList();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final n f2034o = new n(this);

    public o(int i10) {
        this.f2031l = i10;
    }

    public static void u(SparseIntArray sparseIntArray, long j10) {
        if (sparseIntArray != null) {
            int i10 = (int) ((500000 + j10) / 1000000);
            if (j10 >= 0) {
                sparseIntArray.put(i10, sparseIntArray.get(i10) + 1);
            }
        }
    }

    @Override // a1.a
    public final void i(Activity activity) {
        if (f2029p == null) {
            HandlerThread handlerThread = new HandlerThread("FrameMetricsAggregator");
            f2029p = handlerThread;
            handlerThread.start();
            f2030q = new Handler(f2029p.getLooper());
        }
        for (int i10 = 0; i10 <= 8; i10++) {
            SparseIntArray[] sparseIntArrayArr = this.f2032m;
            if (sparseIntArrayArr[i10] == null && (this.f2031l & (1 << i10)) != 0) {
                sparseIntArrayArr[i10] = new SparseIntArray();
            }
        }
        activity.getWindow().addOnFrameMetricsAvailableListener(this.f2034o, f2030q);
        this.f2033n.add(new WeakReference(activity));
    }

    @Override // a1.a
    public final SparseIntArray[] k() {
        return this.f2032m;
    }

    @Override // a1.a
    public final SparseIntArray[] p(Activity activity) {
        ArrayList arrayList = this.f2033n;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() == activity) {
                arrayList.remove(weakReference);
                break;
            }
        }
        activity.getWindow().removeOnFrameMetricsAvailableListener(this.f2034o);
        return this.f2032m;
    }

    @Override // a1.a
    public final SparseIntArray[] q() {
        SparseIntArray[] sparseIntArrayArr = this.f2032m;
        this.f2032m = new SparseIntArray[9];
        return sparseIntArrayArr;
    }

    @Override // a1.a
    public final SparseIntArray[] r() {
        ArrayList arrayList = this.f2033n;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            WeakReference weakReference = (WeakReference) arrayList.get(size);
            Activity activity = (Activity) weakReference.get();
            if (weakReference.get() != null) {
                activity.getWindow().removeOnFrameMetricsAvailableListener(this.f2034o);
                arrayList.remove(size);
            }
        }
        return this.f2032m;
    }
}
