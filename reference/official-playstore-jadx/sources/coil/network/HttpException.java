package coil.network;

import gb.p0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcoil/network/HttpException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "Lgb/p0;", "response", "<init>", "(Lgb/p0;)V", "Lgb/p0;", "getResponse", "()Lgb/p0;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HttpException extends RuntimeException {
    private final p0 response;

    public HttpException(p0 p0Var) {
        super("HTTP " + p0Var.f15783n + ": " + p0Var.f15782m);
        this.response = p0Var;
    }

    public final p0 getResponse() {
        return this.response;
    }
}
