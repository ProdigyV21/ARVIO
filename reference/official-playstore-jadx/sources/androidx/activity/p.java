package androidx.activity;

import android.content.Intent;
import android.content.IntentSender;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f983i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f984l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f985m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f986n;

    public /* synthetic */ p(int i10, int i11, Object obj, Object obj2) {
        this.f983i = i11;
        this.f984l = obj;
        this.f985m = i10;
        this.f986n = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f983i) {
            case 0:
                q qVar = (q) this.f984l;
                Object obj = ((com.google.android.gms.internal.fido.c) this.f986n).f13759i;
                String str = (String) qVar.f1005a.get(Integer.valueOf(this.f985m));
                if (str != null) {
                    androidx.activity.result.f fVar = (androidx.activity.result.f) qVar.f1009e.get(str);
                    if ((fVar != null ? fVar.f997a : null) != null) {
                        androidx.activity.result.b bVar = fVar.f997a;
                        if (qVar.f1008d.remove(str)) {
                            bVar.a(obj);
                        }
                    } else {
                        qVar.f1011g.remove(str);
                        qVar.f1010f.put(str, obj);
                    }
                    break;
                }
                break;
            case 1:
                ((q) this.f984l).a(this.f985m, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", (IntentSender.SendIntentException) this.f986n));
                break;
            case 2:
                ListenerSet.lambda$queueEvent$0((CopyOnWriteArraySet) this.f984l, this.f985m, (ListenerSet.Event) this.f986n);
                break;
            case 3:
                ((DrmSessionEventListener.EventDispatcher) this.f984l).lambda$drmSessionAcquired$0((DrmSessionEventListener) this.f986n, this.f985m);
                break;
            default:
                ((v1.b) this.f984l).f22119b.d(this.f985m, this.f986n);
                break;
        }
    }

    public /* synthetic */ p(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, int i10) {
        this.f983i = 3;
        this.f984l = eventDispatcher;
        this.f986n = drmSessionEventListener;
        this.f985m = i10;
    }
}
