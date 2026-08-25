package androidx.media3.session;

import android.net.Uri;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k3 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4057a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4058b;

    public /* synthetic */ k3(Object obj, int i10) {
        this.f4057a = i10;
        this.f4058b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f4057a) {
            case 0:
                return SimpleBitmapLoader.load((Uri) this.f4058b);
            default:
                return SimpleBitmapLoader.decode((byte[]) this.f4058b);
        }
    }
}
