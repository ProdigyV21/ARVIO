package com.arflix.tv.util;

import a0.c;
import com.arflix.tv.util.Result;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u0015*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0005\u0016\u0017\u0018\u0019\u0015B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ3\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\u0004\b\u0001\u0010\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011\u0082\u0001\u0004\u001a\u001b\u001c\u001d\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/util/UiState;", "T", "", "<init>", "()V", "getOrNull", "()Ljava/lang/Object;", "Lcom/arflix/tv/util/AppException;", "exceptionOrNull", "()Lcom/arflix/tv/util/AppException;", "R", "Lkotlin/Function1;", "transform", "map", "(Lr7/l;)Lcom/arflix/tv/util/UiState;", "", "isLoading", "()Z", "isSuccess", "isError", "isIdle", "Companion", "Idle", "Loading", "Success", "Error", "Lcom/arflix/tv/util/UiState$Error;", "Lcom/arflix/tv/util/UiState$Idle;", "Lcom/arflix/tv/util/UiState$Loading;", "Lcom/arflix/tv/util/UiState$Success;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class UiState<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\"\u0004\b\u0001\u0010\u00042\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0004\b\u0007\u0010\bJ3\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\"\u0004\b\u0001\u0010\u00042\u0006\u0010\n\u001a\u00020\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ9\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\"\u0004\b\u0001\u0010\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00102\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\"\u0004\b\u0001\u0010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/util/UiState$Companion;", "", "<init>", "()V", "T", "data", "Lcom/arflix/tv/util/UiState;", "success", "(Ljava/lang/Object;)Lcom/arflix/tv/util/UiState;", "Lcom/arflix/tv/util/AppException;", "exception", "Lkotlin/Function0;", "Lx6/t0;", "retryAction", "error", "(Lcom/arflix/tv/util/AppException;Lr7/a;)Lcom/arflix/tv/util/UiState;", "Lcom/arflix/tv/util/Result;", "result", "fromResult", "(Lcom/arflix/tv/util/Result;Lr7/a;)Lcom/arflix/tv/util/UiState;", "", "message", "loading", "(Ljava/lang/String;)Lcom/arflix/tv/util/UiState;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UiState error$default(Companion companion, AppException appException, r7.a aVar, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                aVar = null;
            }
            return companion.error(appException, aVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UiState fromResult$default(Companion companion, Result result, r7.a aVar, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                aVar = null;
            }
            return companion.fromResult(result, aVar);
        }

        public static /* synthetic */ UiState loading$default(Companion companion, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = null;
            }
            return companion.loading(str);
        }

        public final <T> UiState<T> error(AppException exception, r7.a<t0> retryAction) {
            return new Error(exception, retryAction);
        }

        public final <T> UiState<T> fromResult(Result<? extends T> result, r7.a<t0> retryAction) {
            if (result instanceof Result.Success) {
                return new Success(((Result.Success) result).getData());
            }
            if (result instanceof Result.Error) {
                return new Error(((Result.Error) result).getException(), retryAction);
            }
            throw new NoWhenBranchMatchedException();
        }

        public final <T> UiState<T> loading(String message) {
            return new Loading(message);
        }

        public final <T> UiState<T> success(T data) {
            return new Success(data);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0083\u0004J\n\u0010\t\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004¨\u0006\r"}, d2 = {"Lcom/arflix/tv/util/UiState$Idle;", "Lcom/arflix/tv/util/UiState;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Idle extends UiState {
        public static final int $stable = 0;
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Idle);
        }

        public int hashCode() {
            return -1299230496;
        }

        public String toString() {
            return "Idle";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0004HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/util/UiState$Loading;", "Lcom/arflix/tv/util/UiState;", "", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Loading extends UiState {
        public static final int $stable = 0;
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public Loading() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Loading copy$default(Loading loading, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = loading.message;
            }
            return loading.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Loading copy(String message) {
            return new Loading(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Loading) && p.a(this.message, ((Loading) other).message);
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            String str = this.message;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return c.l("Loading(message=", this.message, ")");
        }

        public Loading(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ Loading(String str, int i10, h hVar) {
            this((i10 & 1) != 0 ? null : str);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/util/UiState$Success;", "T", "Lcom/arflix/tv/util/UiState;", "data", "<init>", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/arflix/tv/util/UiState$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Success<T> extends UiState<T> {
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

    public /* synthetic */ UiState(h hVar) {
        this();
    }

    public final AppException exceptionOrNull() {
        if (this instanceof Error) {
            return ((Error) this).getException();
        }
        return null;
    }

    public final T getOrNull() {
        if (this instanceof Success) {
            return (T) ((Success) this).getData();
        }
        return null;
    }

    public final boolean isError() {
        return this instanceof Error;
    }

    public final boolean isIdle() {
        return this instanceof Idle;
    }

    public final boolean isLoading() {
        return this instanceof Loading;
    }

    public final boolean isSuccess() {
        return this instanceof Success;
    }

    public final <R> UiState<R> map(l<? super T, ? extends R> transform) {
        if (this instanceof Idle) {
            return Idle.INSTANCE;
        }
        if (this instanceof Loading) {
            return new Loading(((Loading) this).getMessage());
        }
        if (this instanceof Success) {
            return new Success(transform.invoke(((Success) this).getData()));
        }
        if (!(this instanceof Error)) {
            throw new NoWhenBranchMatchedException();
        }
        Error error = (Error) this;
        return new Error(error.getException(), error.getRetryAction());
    }

    private UiState() {
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ,\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u001f\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\rR\u0011\u0010 \u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0012R\u0013\u0010\"\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b!\u0010\u0012R\u0011\u0010#\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/arflix/tv/util/UiState$Error;", "Lcom/arflix/tv/util/UiState;", "", "Lcom/arflix/tv/util/AppException;", "exception", "Lkotlin/Function0;", "Lx6/t0;", "retryAction", "<init>", "(Lcom/arflix/tv/util/AppException;Lr7/a;)V", "component1", "()Lcom/arflix/tv/util/AppException;", "component2", "()Lr7/a;", "copy", "(Lcom/arflix/tv/util/AppException;Lr7/a;)Lcom/arflix/tv/util/UiState$Error;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/arflix/tv/util/AppException;", "getException", "Lr7/a;", "getRetryAction", "getMessage", "message", "getErrorCode", "errorCode", "isRetryable", "()Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Error extends UiState {
        public static final int $stable = AppException.$stable;
        private final AppException exception;
        private final r7.a<t0> retryAction;

        public Error(AppException appException, r7.a<t0> aVar) {
            super(null);
            this.exception = appException;
            this.retryAction = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Error copy$default(Error error, AppException appException, r7.a aVar, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                appException = error.exception;
            }
            if ((i10 & 2) != 0) {
                aVar = error.retryAction;
            }
            return error.copy(appException, aVar);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AppException getException() {
            return this.exception;
        }

        public final r7.a<t0> component2() {
            return this.retryAction;
        }

        public final Error copy(AppException exception, r7.a<t0> retryAction) {
            return new Error(exception, retryAction);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return p.a(this.exception, error.exception) && p.a(this.retryAction, error.retryAction);
        }

        public final String getErrorCode() {
            return this.exception.getErrorCode();
        }

        public final AppException getException() {
            return this.exception;
        }

        public final String getMessage() {
            return this.exception.getMessage();
        }

        public final r7.a<t0> getRetryAction() {
            return this.retryAction;
        }

        public int hashCode() {
            int iHashCode = this.exception.hashCode() * 31;
            r7.a<t0> aVar = this.retryAction;
            return iHashCode + (aVar == null ? 0 : aVar.hashCode());
        }

        public final boolean isRetryable() {
            return this.exception.isRetryable();
        }

        public String toString() {
            return "Error(exception=" + this.exception + ", retryAction=" + this.retryAction + ")";
        }

        public /* synthetic */ Error(AppException appException, r7.a aVar, int i10, h hVar) {
            this(appException, (i10 & 2) != 0 ? null : aVar);
        }
    }
}
