package com.arflix.tv.util;

import androidx.fragment.app.a2;
import com.arflix.tv.ui.screens.player.w0;
import d7.d;
import f7.e;
import f7.h;
import ga.o;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLHandshakeException;
import k2.c;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.text.m;
import kotlin.text.u;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ%\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\bJ+\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0018\u001a\u00020\u0006*\u00020\u00042\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a*\u00020\u0004H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\bJ!\u0010 \u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J)\u0010 \u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\"\u001a\u00020\u0006¢\u0006\u0004\b \u0010#J+\u0010$\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0004\b$\u0010%J\u0015\u0010&\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b&\u0010'J%\u0010,\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b.\u0010\bJ\u0017\u0010/\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b/\u0010'R\u0014\u00100\u001a\u00020*8\u0002X\u0082T¢\u0006\u0006\n\u0004\b0\u00101R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00103R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\u000b058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R \u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u000209088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010>¨\u0006@"}, d2 = {"Lcom/arflix/tv/util/CrashReportFilter;", "", "<init>", "()V", "", "throwable", "", "isAlwaysIgnored", "(Ljava/lang/Throwable;)Z", "containsNetworkFailure", "", "", "classNames", "containsClassName", "(Ljava/lang/Throwable;Ljava/util/Set;)Z", "containsIgnoredMessage", "", "context", "handledExceptionSignature", "(Ljava/lang/Throwable;Ljava/util/Map;)Ljava/lang/String;", "rootCause", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Lkotlin/Function1;", "predicate", "anyCause", "(Ljava/lang/Throwable;Lr7/l;)Z", "Lga/m;", "causeSequence", "(Ljava/lang/Throwable;)Lga/m;", "shouldReportHandledException", "Lio/sentry/w5;", "level", "shouldSendSentryEvent", "(Ljava/lang/Throwable;Lio/sentry/w5;)Z", "isCrashed", "(Ljava/lang/Throwable;Lio/sentry/w5;Z)Z", "shouldSampleHandledException", "(Ljava/lang/Throwable;Ljava/util/Map;)Z", "handledEventFingerprint", "(Ljava/lang/Throwable;)Ljava/lang/String;", "installationSeed", "fingerprint", "", "samplePermille", "isSelectedForHandledSample", "(Ljava/lang/String;Ljava/lang/String;I)Z", "isAnr", "dropReasonForHandledException", "MAX_TRACKED_HANDLED_SIGNATURES", "I", "alwaysIgnoredClassNames", "Ljava/util/Set;", "handledOnlyClassNames", "", "ignoredMessageFragments", "Ljava/util/List;", "j$/util/concurrent/ConcurrentHashMap", "Ljava/util/concurrent/atomic/AtomicInteger;", "handledExceptionCounts", "Lj$/util/concurrent/ConcurrentHashMap;", "Lkotlin/text/m;", "URL_PATTERN", "Lkotlin/text/m;", "TOKEN_PATTERN", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CrashReportFilter {
    private static final int MAX_TRACKED_HANDLED_SIGNATURES = 256;
    public static final CrashReportFilter INSTANCE = new CrashReportFilter();
    private static final Set<String> alwaysIgnoredClassNames = r.p0(new String[]{"JobCancellationException", "LeftCompositionCancellationException", "ModifierNodeDetachedCancellationException", "PointerEventTimeoutCancellationException"});
    private static final Set<String> handledOnlyClassNames = r.p0(new String[]{"HttpRequestException", "HttpRequestTimeoutException", "ClientRequestException", "ServerResponseException", "RedirectResponseException", "UnknownRestException", "TimeoutCancellationException"});
    private static final List<String> ignoredMessageFragments = t7.a.E("not logged in", "job was cancelled", "was cancelled", "playback error displayed", "selected stream playback failed", "playback source list empty", "playback imdb id missing", "provided playback url could not be resolved", "source lookup failed", "complete epg backfill timed out", "complete epg backfill returned empty guide", "iptv load timed out", "playlist loaded but contains no channels", "m3u request failed", "expected url scheme 'http' or 'https'", "expected url scheme \"http\" or \"https\"", "incomplete trakt watchlist fetch", "trakt credentials not configured", "trakt token request failed", "trakt continue watching hydration returned zero items", "trakt rate limit", "rate limit", "too many requests", "http 401", "http 403", "http 404", "http 429", "bad http status", "exponential backoff active", "cache overflow", "queue overflow", "ratelimit_backoff", "error_usage_exceeded", "send_error", "network_error", "jwt expired", "invalid jwt", "token is expired", "unable to parse or verify signature", "supabase.co", "auth/v1/logout", "row-level security policy", "chain validation failed", "unable to resolve host", "failed to connect", "request timeout has expired");
    private static final ConcurrentHashMap<String, AtomicInteger> handledExceptionCounts = new ConcurrentHashMap<>();
    private static final m URL_PATTERN = new m("\\b(?:https?|wss?|ftp|file|content)://[^\\s\"'<>]+", 0);
    private static final m TOKEN_PATTERN = new m("\\b[A-Za-z0-9_-]{16,}\\.[A-Za-z0-9_-]{16,}\\.[A-Za-z0-9_-]{16,}\\b");
    public static final int $stable = 8;

    /* JADX INFO: renamed from: com.arflix.tv.util.CrashReportFilter$causeSequence$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lga/o;", "", "Lx6/t0;", "<anonymous>", "(Lga/o;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.util.CrashReportFilter$causeSequence$1", f = "CrashReportFilter.kt", l = {246}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends h implements p<o, d<? super t0>, Object> {
        final /* synthetic */ Throwable $this_causeSequence;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Throwable th, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$this_causeSequence = th;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_causeSequence, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(o oVar, d<? super t0> dVar) {
            return ((AnonymousClass1) create(oVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Throwable cause;
            Set linkedHashSet;
            o oVar = (o) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                cause = this.$this_causeSequence;
                linkedHashSet = new LinkedHashSet();
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                linkedHashSet = (Set) this.L$2;
                Throwable th = (Throwable) this.L$1;
                c.G(obj);
                cause = th.getCause();
            }
            if (cause == null || !linkedHashSet.add(cause)) {
                return t0.f22605a;
            }
            this.L$0 = oVar;
            this.L$1 = cause;
            this.L$2 = linkedHashSet;
            this.label = 1;
            oVar.b(this, cause);
            return e7.a.f15033i;
        }
    }

    private CrashReportFilter() {
    }

    private final boolean anyCause(Throwable th, l<? super Throwable, Boolean> lVar) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (th != null && linkedHashSet.add(th)) {
            if (((Boolean) lVar.invoke(th)).booleanValue()) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    private final ga.m<Throwable> causeSequence(Throwable th) {
        return new ga.p(new AnonymousClass1(th, null));
    }

    private final boolean containsClassName(Throwable throwable, Set<String> classNames) {
        return anyCause(throwable, new com.arflix.tv.cast.a(classNames, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsClassName$lambda$0(Set set, Throwable th) {
        return set.contains(th.getClass().getSimpleName()) || set.contains(th.getClass().getName());
    }

    private final boolean containsIgnoredMessage(Throwable throwable) {
        return anyCause(throwable, new w0(27));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsIgnoredMessage$lambda$0(Throwable th) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(th.getClass().getSimpleName());
        sb2.append(' ');
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        sb2.append(message);
        String string = sb2.toString();
        List<String> list = ignoredMessageFragments;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (kotlin.text.o.T(string, (String) it.next(), true)) {
                return true;
            }
        }
        return false;
    }

    private final boolean containsNetworkFailure(Throwable throwable) {
        return anyCause(throwable, new a(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsNetworkFailure$lambda$0(Throwable th) {
        if (!(th instanceof UnknownHostException) && !(th instanceof SocketTimeoutException) && !(th instanceof ConnectException) && !(th instanceof NoRouteToHostException) && !(th instanceof PortUnreachableException) && !(th instanceof SSLHandshakeException) && !(th instanceof InterruptedIOException)) {
            if (!(th instanceof SocketException)) {
                return false;
            }
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            if (!kotlin.text.o.T(message, "timed out", true)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ga.m handledEventFingerprint$lambda$0(Throwable th) {
        return r.G(th.getStackTrace());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence handledEventFingerprint$lambda$3(byte b10) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    private final String handledExceptionSignature(Throwable throwable, Map<String, String> context) {
        Throwable thRootCause = rootCause(throwable);
        String str = context.get("error_area");
        if (str == null) {
            str = "";
        }
        String str2 = context.get("cloud_flow");
        if (str2 == null) {
            str2 = "";
        }
        String str3 = context.get("trakt_phase");
        if (str3 == null && (str3 = context.get("player_phase")) == null && (str3 = context.get("iptv_phase")) == null && (str3 = context.get("phase")) == null) {
            str3 = "";
        }
        String message = thRootCause.getMessage();
        return a2.n(a2.D(thRootCause.getClass().getName(), "|", str, "|", str2), "|", str3, "|", kotlin.text.o.I0(140, TOKEN_PATTERN.f("[TOKEN]", URL_PATTERN.f("[URL]", message != null ? message : ""))));
    }

    private final boolean isAlwaysIgnored(Throwable throwable) {
        return (throwable instanceof CancellationException) || containsClassName(throwable, alwaysIgnoredClassNames);
    }

    private final Throwable rootCause(Throwable throwable) {
        Throwable cause;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (throwable.getCause() != null && linkedHashSet.add(throwable) && (cause = throwable.getCause()) != null) {
            throwable = cause;
        }
        return throwable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean shouldSampleHandledException$default(CrashReportFilter crashReportFilter, Throwable th, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = a0.f19683i;
        }
        return crashReportFilter.shouldSampleHandledException(th, map);
    }

    public final String dropReasonForHandledException(Throwable throwable) {
        if (isAlwaysIgnored(throwable)) {
            return "cancellation";
        }
        if (containsClassName(throwable, handledOnlyClassNames)) {
            return "timeout";
        }
        if (containsNetworkFailure(throwable)) {
            return "network";
        }
        if (containsIgnoredMessage(throwable)) {
            return "expected_state";
        }
        return null;
    }

    public final String handledEventFingerprint(Throwable throwable) {
        Object next;
        Throwable thRootCause = rootCause(throwable);
        ga.h hVar = new ga.h(ga.r.L(causeSequence(throwable), new w0(28)));
        while (true) {
            if (!hVar.hasNext()) {
                next = null;
                break;
            }
            next = hVar.next();
            if (u.P(((StackTraceElement) next).getClassName(), "com.arflix.tv.", false)) {
                break;
            }
        }
        StackTraceElement stackTraceElement = (StackTraceElement) next;
        if (stackTraceElement == null) {
            stackTraceElement = (StackTraceElement) r.Z(thRootCause.getStackTrace());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(thRootCause.getClass().getName());
        sb2.append('|');
        String className = stackTraceElement != null ? stackTraceElement.getClassName() : null;
        if (className == null) {
            className = "";
        }
        sb2.append(className);
        sb2.append('.');
        String methodName = stackTraceElement != null ? stackTraceElement.getMethodName() : null;
        sb2.append(methodName != null ? methodName : "");
        return x.u0(r.l0(12, MessageDigest.getInstance("SHA-256").digest(sb2.toString().getBytes(kotlin.text.a.f19924a))), "", null, null, new w0(29), 30);
    }

    public final boolean isAnr(Throwable throwable) {
        return containsClassName(throwable, Collections.singleton("ApplicationNotResponding"));
    }

    public final boolean isSelectedForHandledSample(String installationSeed, String fingerprint, int samplePermille) {
        if (samplePermille <= 0) {
            return false;
        }
        if (samplePermille >= 1000) {
            return true;
        }
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(androidx.compose.foundation.c.t(installationSeed, "|", fingerprint).getBytes(kotlin.text.a.f19924a));
        return ((bArrDigest[1] & 255) | ((bArrDigest[0] & 255) << 8)) % 1000 < samplePermille;
    }

    public final boolean shouldReportHandledException(Throwable throwable) {
        return dropReasonForHandledException(throwable) == null;
    }

    public final boolean shouldSampleHandledException(Throwable throwable, Map<String, String> context) {
        AtomicInteger atomicIntegerPutIfAbsent;
        ConcurrentHashMap<String, AtomicInteger> concurrentHashMap = handledExceptionCounts;
        if (concurrentHashMap.size() > 256) {
            concurrentHashMap.clear();
        }
        String strHandledExceptionSignature = handledExceptionSignature(throwable, context);
        AtomicInteger atomicInteger = concurrentHashMap.get(strHandledExceptionSignature);
        if (atomicInteger == null && (atomicIntegerPutIfAbsent = concurrentHashMap.putIfAbsent(strHandledExceptionSignature, (atomicInteger = new AtomicInteger(0)))) != null) {
            atomicInteger = atomicIntegerPutIfAbsent;
        }
        return atomicInteger.incrementAndGet() == 1;
    }

    public final boolean shouldSendSentryEvent(Throwable throwable, w5 level) {
        return shouldSendSentryEvent(throwable, level, false);
    }

    public final boolean shouldSendSentryEvent(Throwable throwable, w5 level, boolean isCrashed) {
        if (isCrashed || level == w5.FATAL) {
            return true;
        }
        if (throwable == null || isAlwaysIgnored(throwable)) {
            return false;
        }
        return shouldReportHandledException(throwable);
    }
}
