package f4;

import android.os.Process;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends Thread {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15265i = 1;

    public /* synthetic */ e(String str) {
        super(str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        ReentrantLock reentrantLock;
        xb.e eVarD;
        switch (this.f15265i) {
            case 0:
                Process.setThreadPriority(19);
                synchronized (this) {
                    while (true) {
                        try {
                            wait();
                        } catch (InterruptedException unused) {
                            return;
                        }
                    }
                }
                break;
        }
        while (true) {
            try {
                ReentrantLock reentrantLock2 = xb.e.f22724h;
                reentrantLock = xb.e.f22724h;
                reentrantLock.lock();
                try {
                    eVarD = v1.i.d();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            } catch (InterruptedException unused2) {
            }
            if (eVarD == xb.e.f22727l) {
                xb.e.f22727l = null;
                reentrantLock.unlock();
                return;
            } else {
                reentrantLock.unlock();
                if (eVarD != null) {
                    eVarD.k();
                }
            }
        }
    }

    public /* synthetic */ e(ThreadGroup threadGroup, String str) {
        super(threadGroup, str);
    }
}
