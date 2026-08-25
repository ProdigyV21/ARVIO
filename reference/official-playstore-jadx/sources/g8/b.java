package g8;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.utils.io.charsets.UTFKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.core.internal.NumbersKt;
import io.sentry.ILogger;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import kotlin.KotlinNothingValueException;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class b {
    public static boolean a(String str, ILogger iLogger) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        iLogger.q(w5.INFO, "No cached dir path is defined in options.", new Object[0]);
        return false;
    }

    public static final boolean b(int i10) {
        return i10 == 3 || i10 == 4 || i10 == 6;
    }

    public static final boolean c(int i10) {
        return i10 == 6 || i10 == 4;
    }

    public static /* synthetic */ boolean d(int i10) {
        if (i10 == 1 || i10 == 2) {
            return false;
        }
        if (i10 == 3 || i10 == 4) {
            return true;
        }
        throw null;
    }

    public static long e(ByteBuffer byteBuffer, int i10, int i11, int i12, int i13) {
        byteBuffer.position(byteBuffer.position() - i10);
        return UTFKt.decodeUtf8Result(i11 - i12, i13);
    }

    public static HttpRequestBuilder f(String str, r7.l lVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestKt.url(httpRequestBuilder, str);
        lVar.invoke(httpRequestBuilder);
        return httpRequestBuilder;
    }

    public static HttpRequestBuilder g(r7.l lVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        lVar.invoke(httpRequestBuilder);
        return httpRequestBuilder;
    }

    public static String h(kotlin.jvm.internal.m0 m0Var, Class cls, StringBuilder sb2) {
        sb2.append(m0Var.b(cls));
        return sb2.toString();
    }

    public static KotlinNothingValueException i(int i10) {
        StringsKt.prematureEndOfStream(i10);
        return new KotlinNothingValueException();
    }

    public static KotlinNothingValueException j(long j10, String str) {
        NumbersKt.failLongToIntConversion(j10, str);
        return new KotlinNothingValueException();
    }

    public static void k(HttpRequestBuilder httpRequestBuilder, Url url, r7.l lVar, HttpRequestBuilder httpRequestBuilder2) {
        URLUtilsKt.takeFrom(httpRequestBuilder.getUrl(), url);
        lVar.invoke(httpRequestBuilder2);
    }

    public static /* synthetic */ void l(io.sentry.util.a aVar) throws Exception {
        if (aVar instanceof AutoCloseable) {
            aVar.close();
        } else {
            if (!(aVar instanceof ExecutorService)) {
                throw new IllegalArgumentException();
            }
            throw null;
        }
    }

    public static void m(HashMap map, String str, fi.iki.elonen.f fVar, String str2, ILogger iLogger) {
        Object obj = map.get(str);
        fVar.S(str2);
        fVar.b0(iLogger, obj);
    }

    public static void n(ConcurrentHashMap concurrentHashMap, String str, fi.iki.elonen.f fVar, String str2, ILogger iLogger) {
        Object obj = concurrentHashMap.get(str);
        fVar.S(str2);
        fVar.b0(iLogger, obj);
    }

    public static void o(kotlin.jvm.internal.m0 m0Var, Class cls, Type type, kotlin.reflect.q qVar, HttpRequestBuilder httpRequestBuilder) {
        httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(type, m0Var.b(cls), qVar));
    }

    public static long p(ByteBuffer byteBuffer, int i10, int i11, int i12, int i13) {
        byteBuffer.position(i10 - byteBuffer.arrayOffset());
        return UTFKt.decodeUtf8Result(i11 - i12, i13);
    }

    public static /* synthetic */ String q(int i10) {
        switch (i10) {
            case 1:
                return "OBJECT";
            case 2:
                return "LIST";
            case 3:
                return "NUMBER";
            case 4:
                return "BOOLEAN";
            case 5:
                return "NULL";
            case 6:
                return "STRING";
            default:
                throw null;
        }
    }

    public static /* synthetic */ String r(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "null" : "SYNTHESIZED" : "DELEGATION" : "FAKE_OVERRIDE" : "DECLARATION";
    }

    public static /* synthetic */ String s(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : "PROPERTIES" : "CONF" : "JSON";
    }

    public static /* synthetic */ String t(int i10) {
        switch (i10) {
            case 1:
                return "NOT_REQUIRED";
            case 2:
                return "CONNECTED";
            case 3:
                return "UNMETERED";
            case 4:
                return "NOT_ROAMING";
            case 5:
                return "METERED";
            case 6:
                return "TEMPORARILY_UNMETERED";
            default:
                return "null";
        }
    }

    public static /* synthetic */ String u(int i10) {
        switch (i10) {
            case 1:
                return "ENQUEUED";
            case 2:
                return "RUNNING";
            case 3:
                return "SUCCEEDED";
            case 4:
                return "FAILED";
            case 5:
                return "BLOCKED";
            case 6:
                return "CANCELLED";
            default:
                return "null";
        }
    }

    public static /* synthetic */ String v(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : "DROP_LATEST" : "DROP_OLDEST" : "SUSPEND";
    }
}
