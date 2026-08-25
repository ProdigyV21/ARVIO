package retrofit2;

import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends CompletableFuture {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y f21515i;

    public i(y yVar) {
        this.f21515i = yVar;
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        if (z) {
            this.f21515i.cancel();
        }
        return super.cancel(z);
    }
}
