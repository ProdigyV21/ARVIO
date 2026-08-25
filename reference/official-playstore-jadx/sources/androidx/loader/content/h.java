package androidx.loader.content;

import android.util.Log;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends FutureTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a f3343i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(a aVar, g gVar) {
        super(gVar);
        this.f3343i = aVar;
    }

    @Override // java.util.concurrent.FutureTask
    public final void done() {
        a aVar = this.f3343i;
        AtomicBoolean atomicBoolean = aVar.f3335o;
        try {
            Object obj = get();
            if (atomicBoolean.get()) {
                return;
            }
            aVar.a(obj);
        } catch (InterruptedException e5) {
            Log.w("AsyncTask", e5);
        } catch (CancellationException unused) {
            if (atomicBoolean.get()) {
                return;
            }
            aVar.a(null);
        } catch (ExecutionException e6) {
            throw new RuntimeException("An error occurred while executing doInBackground()", e6.getCause());
        } catch (Throwable th) {
            throw new RuntimeException("An error occurred while executing doInBackground()", th);
        }
    }
}
