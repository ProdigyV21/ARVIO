package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002¨\u0006\u0003"}, d2 = {"Lokhttp3/internal/connection/RouteException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RouteException extends RuntimeException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final IOException f20987i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public IOException f20988l;

    public RouteException(IOException iOException) {
        super(iOException);
        this.f20987i = iOException;
        this.f20988l = iOException;
    }
}
