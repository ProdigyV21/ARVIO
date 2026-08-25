package androidx.cursoradapter.widget;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import androidx.appcompat.widget.c4;
import com.google.android.gms.internal.auth.c1;
import com.google.android.gms.internal.auth.t0;
import com.google.android.gms.internal.auth.u0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2404a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2405b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(t0 t0Var) {
        super(null);
        this.f2405b = t0Var;
    }

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        switch (this.f2404a) {
            case 0:
                return true;
            default:
                return super.deliverSelfNotifications();
        }
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        Cursor cursor;
        switch (this.f2404a) {
            case 0:
                c4 c4Var = (c4) this.f2405b;
                if (!c4Var.f2407l || (cursor = c4Var.f2408m) == null || cursor.isClosed()) {
                    return;
                }
                c4Var.f2406i = c4Var.f2408m.requery();
                return;
            default:
                t0 t0Var = (t0) this.f2405b;
                synchronized (t0Var.f13119n) {
                    t0Var.f13120o = null;
                    c1.f13006i.incrementAndGet();
                    break;
                }
                synchronized (t0Var) {
                    try {
                        Iterator it = t0Var.f13121p.iterator();
                        while (it.hasNext()) {
                            ((u0) it.next()).zza();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(c4 c4Var) {
        super(new Handler());
        this.f2405b = c4Var;
    }
}
