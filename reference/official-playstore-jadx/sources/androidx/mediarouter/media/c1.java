package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public final class c1 implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Messenger f4310a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f1 f4311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Messenger f4312c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4315f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4316g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ j1 f4318i;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4313d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4314e = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final SparseArray f4317h = new SparseArray();

    public c1(j1 j1Var, Messenger messenger) {
        this.f4318i = j1Var;
        this.f4310a = messenger;
        f1 f1Var = new f1(this);
        this.f4311b = f1Var;
        this.f4312c = new Messenger(f1Var);
    }

    public final void a(int i10) {
        int i11 = this.f4313d;
        this.f4313d = i11 + 1;
        b(5, i11, i10, null, null);
    }

    public final boolean b(int i10, int i11, int i12, Bundle bundle, Bundle bundle2) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i10;
        messageObtain.arg1 = i11;
        messageObtain.arg2 = i12;
        messageObtain.obj = bundle;
        messageObtain.setData(bundle2);
        messageObtain.replyTo = this.f4312c;
        try {
            this.f4310a.send(messageObtain);
            return true;
        } catch (DeadObjectException unused) {
            return false;
        } catch (RemoteException e5) {
            if (i10 == 2) {
                return false;
            }
            Log.e("MediaRouteProviderProxy", "Could not send message to service.", e5);
            return false;
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.f4318i.f4419t.post(new b1(this, 1));
    }

    public final void c(int i10, int i11) {
        Bundle bundle = new Bundle();
        bundle.putInt("volume", i11);
        int i12 = this.f4313d;
        this.f4313d = i12 + 1;
        b(7, i12, i10, null, bundle);
    }

    public final void d(int i10, int i11) {
        Bundle bundle = new Bundle();
        bundle.putInt("volume", i11);
        int i12 = this.f4313d;
        this.f4313d = i12 + 1;
        b(8, i12, i10, null, bundle);
    }
}
