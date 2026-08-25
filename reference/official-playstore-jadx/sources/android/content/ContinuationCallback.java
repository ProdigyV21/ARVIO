package android.content;

import gb.i;
import gb.j;
import gb.p0;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import ka.k;
import kotlin.Metadata;
import r7.l;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u00012#\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0002j\u0002`\bB\u001d\u0012\u0006\u0010\n\u001a\u00020\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0011\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0019R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcoil/util/ContinuationCallback;", "Lgb/j;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Lx6/t0;", "Lkotlinx/coroutines/CompletionHandler;", "Lgb/i;", "call", "Lka/k;", "Lgb/p0;", "continuation", "<init>", "(Lgb/i;Lka/k;)V", "response", "onResponse", "(Lgb/i;Lgb/p0;)V", "Ljava/io/IOException;", "e", "onFailure", "(Lgb/i;Ljava/io/IOException;)V", "invoke", "(Ljava/lang/Throwable;)V", "Lgb/i;", "Lka/k;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class ContinuationCallback implements j, l<Throwable, t0> {
    private final i call;
    private final k<p0> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationCallback(i iVar, k<? super p0> kVar) {
        this.call = iVar;
        this.continuation = kVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    @Override // gb.j
    public void onFailure(i call, IOException e5) {
        if (call.k()) {
            return;
        }
        this.continuation.resumeWith(new c0(e5));
    }

    @Override // gb.j
    public void onResponse(i call, p0 response) {
        this.continuation.resumeWith(response);
    }

    public void invoke(Throwable cause) {
        try {
            this.call.cancel();
        } catch (Throwable unused) {
        }
    }
}
