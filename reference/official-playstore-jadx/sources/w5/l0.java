package w5;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class l0 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f22467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f22468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f22469c;

    public l0(Looper looper) {
        super(looper);
        this.f22469c = new ArrayList();
    }

    public final void a(Messenger messenger) {
        if (this.f22467a) {
            c(messenger, ((h0) z4.g.c().b(h0.class)).a().f22498a);
            return;
        }
        String strA = ((t) z4.g.c().b(t.class)).a();
        Log.d("SessionLifecycleService", "App has not yet foregrounded. Using previously stored session: " + strA);
        if (strA != null) {
            c(messenger, strA);
        }
    }

    public final void b() {
        h0 h0Var = (h0) z4.g.c().b(h0.class);
        int i10 = h0Var.f22447d + 1;
        h0Var.f22447d = i10;
        String lowerCase = i10 == 0 ? h0Var.f22446c : kotlin.text.u.O(((UUID) h0Var.f22445b.invoke()).toString(), "-", "", false).toLowerCase(Locale.ROOT);
        String str = h0Var.f22446c;
        int i11 = h0Var.f22447d;
        h0Var.f22444a.getClass();
        h0Var.f22448e = new y(lowerCase, str, i11, System.currentTimeMillis() * 1000);
        h0Var.a();
        Log.d("SessionLifecycleService", "Generated new session " + ((h0) z4.g.c().b(h0.class)).a().f22498a);
        Log.d("SessionLifecycleService", "Broadcasting new session: " + ((h0) z4.g.c().b(h0.class)).a());
        ((b0) z4.g.c().b(b0.class)).a(((h0) z4.g.c().b(h0.class)).a());
        Iterator it = new ArrayList(this.f22469c).iterator();
        while (it.hasNext()) {
            a((Messenger) it.next());
        }
        ((t) z4.g.c().b(t.class)).b(((h0) z4.g.c().b(h0.class)).a().f22498a);
    }

    public final void c(Messenger messenger, String str) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("SessionUpdateExtra", str);
            Message messageObtain = Message.obtain(null, 3, 0, 0);
            messageObtain.setData(bundle);
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
            Log.d("SessionLifecycleService", "Removing dead client from list: " + messenger);
            this.f22469c.remove(messenger);
        } catch (Exception e5) {
            Log.w("SessionLifecycleService", "Unable to push new session to " + messenger + '.', e5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0149  */
    @Override // android.os.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleMessage(android.os.Message r11) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: w5.l0.handleMessage(android.os.Message):void");
    }
}
