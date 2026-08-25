package com.arflix.tv.util;

import com.arflix.tv.util.AppException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u001d*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\u001e\u001f\u001dB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000f\u001a\u00028\u00002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u00000\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J3\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\u0004\b\u0001\u0010\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J9\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\u0004\b\u0001\u0010\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00000\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0014J-\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00160\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0014J-\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0014R\u0011\u0010\u001a\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001b\u0082\u0001\u0002 !\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"Lcom/arflix/tv/util/Result;", "T", "", "<init>", "()V", "getOrNull", "()Ljava/lang/Object;", "Lcom/arflix/tv/util/AppException;", "exceptionOrNull", "()Lcom/arflix/tv/util/AppException;", "default", "getOrDefault", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Function1;", "onError", "getOrElse", "(Lr7/l;)Ljava/lang/Object;", "R", "transform", "map", "(Lr7/l;)Lcom/arflix/tv/util/Result;", "flatMap", "Lx6/t0;", "action", "onSuccess", "", "isSuccess", "()Z", "isError", "Companion", "Success", "Error", "Lcom/arflix/tv/util/Result$Error;", "Lcom/arflix/tv/util/Result$Success;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Result<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\u0007\u001a\u0002H\u0006¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\f\u001a\u00020\rJ\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/util/Result$Companion;", "", "<init>", "()V", "success", "Lcom/arflix/tv/util/Result;", "T", "value", "(Ljava/lang/Object;)Lcom/arflix/tv/util/Result;", "error", "exception", "Lcom/arflix/tv/util/AppException;", "throwable", "", "message", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final <T> Result<T> error(AppException exception) {
            return new Error(exception);
        }

        public final <T> Result<T> success(T value) {
            return new Success(value);
        }

        private Companion() {
        }

        public final <T> Result<T> error(Throwable throwable) {
            return new Error(ResultKt.toAppException(throwable));
        }

        public final <T> Result<T> error(String message) {
            return new Error(new AppException.Unknown(message, null, null, 6, null));
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\t\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/util/Result$Error;", "Lcom/arflix/tv/util/Result;", "", "exception", "Lcom/arflix/tv/util/AppException;", "<init>", "(Lcom/arflix/tv/util/AppException;)V", "getException", "()Lcom/arflix/tv/util/AppException;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Error extends Result {
        public static final int $stable = 8;
        private final AppException exception;

        public Error(AppException appException) {
            super(null);
            this.exception = appException;
        }

        public static /* synthetic */ Error copy$default(Error error, AppException appException, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                appException = error.exception;
            }
            return error.copy(appException);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AppException getException() {
            return this.exception;
        }

        public final Error copy(AppException exception) {
            return new Error(exception);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Error) && p.a(this.exception, ((Error) other).exception);
        }

        public final AppException getException() {
            return this.exception;
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Error(exception=" + this.exception + ")";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/util/Result$Success;", "T", "Lcom/arflix/tv/util/Result;", "data", "<init>", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/arflix/tv/util/Result$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Success<T> extends Result<T> {
        public static final int $stable = 0;
        private final T data;

        public Success(T t2) {
            super(null);
            this.data = t2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Success copy$default(Success success, Object obj, int i10, Object obj2) {
            if ((i10 & 1) != 0) {
                obj = success.data;
            }
            return success.copy(obj);
        }

        public final T component1() {
            return this.data;
        }

        public final Success<T> copy(T data) {
            return new Success<>(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && p.a(this.data, ((Success) other).data);
        }

        public final T getData() {
            return this.data;
        }

        public int hashCode() {
            T t2 = this.data;
            if (t2 == null) {
                return 0;
            }
            return t2.hashCode();
        }

        public String toString() {
            return "Success(data=" + this.data + ")";
        }
    }

    public /* synthetic */ Result(h hVar) {
        this();
    }

    public final AppException exceptionOrNull() {
        if (this instanceof Success) {
            return null;
        }
        if (this instanceof Error) {
            return ((Error) this).getException();
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Result<R> flatMap(l<? super T, ? extends Result<? extends R>> transform) {
        if (this instanceof Success) {
            return (Result) transform.invoke(((Success) this).getData());
        }
        if (this instanceof Error) {
            return this;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final T getOrDefault(T t2) {
        if (this instanceof Success) {
            return (T) ((Success) this).getData();
        }
        if (this instanceof Error) {
            return t2;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final T getOrElse(l<? super AppException, ? extends T> onError) {
        if (this instanceof Success) {
            return (T) ((Success) this).getData();
        }
        if (this instanceof Error) {
            return (T) onError.invoke(((Error) this).getException());
        }
        throw new NoWhenBranchMatchedException();
    }

    public final T getOrNull() {
        if (this instanceof Success) {
            return (T) ((Success) this).getData();
        }
        if (this instanceof Error) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final boolean isError() {
        return this instanceof Error;
    }

    public final boolean isSuccess() {
        return this instanceof Success;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Result<R> map(l<? super T, ? extends R> transform) {
        if (this instanceof Success) {
            return new Success(transform.invoke(((Success) this).getData()));
        }
        if (this instanceof Error) {
            return this;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Result<T> onError(l<? super AppException, t0> action) {
        if (this instanceof Error) {
            action.invoke(((Error) this).getException());
        }
        return this;
    }

    public final Result<T> onSuccess(l<? super T, t0> action) {
        if (this instanceof Success) {
            action.invoke(((Success) this).getData());
        }
        return this;
    }

    private Result() {
    }
}
