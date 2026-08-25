package com.arflix.tv.util;

import com.arflix.tv.util.AppException;
import com.arflix.tv.util.Result;
import d7.d;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import kotlin.Metadata;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a*\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0086H¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"", "Lcom/arflix/tv/util/AppException;", "toAppException", "(Ljava/lang/Throwable;)Lcom/arflix/tv/util/AppException;", "T", "Lkotlin/Function0;", "block", "Lcom/arflix/tv/util/Result;", "runCatching", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ResultKt {
    public static final <T> Object runCatching(r7.a<? extends T> aVar, d<? super Result<? extends T>> dVar) {
        try {
            return new Result.Success(aVar.invoke());
        } catch (Exception e5) {
            return new Result.Error(toAppException(e5));
        }
    }

    private static final <T> Object runCatching$$forInline(r7.a<? extends T> aVar, d<? super Result<? extends T>> dVar) {
        try {
            return new Result.Success(aVar.invoke());
        } catch (Exception e5) {
            return new Result.Error(toAppException(e5));
        }
    }

    public static final AppException toAppException(Throwable th) {
        if (th instanceof AppException) {
            return (AppException) th;
        }
        if (th instanceof UnknownHostException) {
            return new AppException.Network("No internet connection", th, null, 4, null);
        }
        if (th instanceof SocketTimeoutException) {
            return new AppException.Network("Connection timed out", th, null, 4, null);
        }
        if (th instanceof ConnectException) {
            return new AppException.Network("Could not connect to server", th, null, 4, null);
        }
        if (th instanceof SSLException) {
            return new AppException.Network("Secure connection failed", th, null, 4, null);
        }
        if (!(th instanceof HttpException)) {
            String message = th.getMessage();
            if (message == null) {
                message = "An unexpected error occurred";
            }
            return new AppException.Unknown(message, th, null, 4, null);
        }
        HttpException httpException = (HttpException) th;
        int i10 = httpException.f21464i;
        if (i10 == 401) {
            return new AppException.Auth("Session expired. Please sign in again.", th, null, 4, null);
        }
        if (i10 == 403) {
            return new AppException.Auth("Access denied", th, null, 4, null);
        }
        if (i10 == 404) {
            return new AppException.Server("Not found", httpException.f21464i, th, null, 8, null);
        }
        if (500 > i10 || i10 >= 600) {
            return new AppException.Server("Request failed", httpException.f21464i, th, null, 8, null);
        }
        return new AppException.Server("Server error. Please try again later.", httpException.f21464i, th, null, 8, null);
    }
}
