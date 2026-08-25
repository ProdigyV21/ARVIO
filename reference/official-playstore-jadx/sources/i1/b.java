package i1;

import android.content.Context;
import androidx.work.WorkerParameters;
import java.util.Map;
import javax.inject.Provider;
import m2.i0;
import m2.s;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends i0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f16017b;

    public b(Map map) {
        this.f16017b = map;
    }

    @Override // m2.i0
    public final s a(Context context, String str, WorkerParameters workerParameters) {
        Provider provider = (Provider) this.f16017b.get(str);
        if (provider == null) {
            return null;
        }
        return ((c) provider.get()).create(context, workerParameters);
    }
}
