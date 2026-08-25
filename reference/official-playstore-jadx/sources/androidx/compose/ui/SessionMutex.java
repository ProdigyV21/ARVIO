package androidx.compose.ui;

import d7.d;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.atomic.AtomicReference;
import ka.k0;
import ka.l0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.g;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001(B\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B1\b\u0002\u0012&\u0010\b\u001a\"\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006`\u0007¢\u0006\u0004\b\u0003\u0010\tJ]\u0010\u0016\u001a\u00028\u0001\"\u0004\b\u0001\u0010\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u00000\u000b21\u0010\u0013\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000eH\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u001a\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001e\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010#\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b!\u0010\"R4\u0010\b\u001a\"\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006`\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010$R\u0013\u0010'\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b%\u0010&\u0088\u0001\b\u0092\u0001\"\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u0005j\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006`\u0007¨\u0006)"}, d2 = {"Landroidx/compose/ui/SessionMutex;", "T", "", "constructor-impl", "()Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/ui/SessionMutex$Session;", "Landroidx/compose/ui/AtomicReference;", "currentSessionHolder", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/util/concurrent/atomic/AtomicReference;", "R", "Lkotlin/Function1;", "Lka/k0;", "sessionInitializer", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "data", "Ld7/d;", SettingsSessionManager.SETTINGS_KEY, "withSessionCancellingPrevious-impl", "(Ljava/util/concurrent/atomic/AtomicReference;Lr7/l;Lr7/p;Ld7/d;)Ljava/lang/Object;", "withSessionCancellingPrevious", "", "toString-impl", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/lang/String;", "toString", "", "hashCode-impl", "(Ljava/util/concurrent/atomic/AtomicReference;)I", "hashCode", "other", "", "equals-impl", "(Ljava/util/concurrent/atomic/AtomicReference;Ljava/lang/Object;)Z", "equals", "Ljava/util/concurrent/atomic/AtomicReference;", "getCurrentSession-impl", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/lang/Object;", "currentSession", "Session", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class SessionMutex<T> {
    private final AtomicReference<Session<T>> currentSessionHolder;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00028\u00018\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/SessionMutex$Session;", "T", "", "Lka/v1;", "job", "value", "<init>", "(Lka/v1;Ljava/lang/Object;)V", "Lka/v1;", "getJob", "()Lka/v1;", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Session<T> {
        private final v1 job;
        private final T value;

        public Session(v1 v1Var, T t2) {
            this.job = v1Var;
            this.value = t2;
        }

        public final v1 getJob() {
            return this.job;
        }

        public final T getValue() {
            return this.value;
        }
    }

    private /* synthetic */ SessionMutex(AtomicReference atomicReference) {
        this.currentSessionHolder = atomicReference;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SessionMutex m3111boximpl(AtomicReference atomicReference) {
        return new SessionMutex(atomicReference);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static <T> AtomicReference<Session<T>> m3113constructorimpl(AtomicReference<Session<T>> atomicReference) {
        return atomicReference;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3114equalsimpl(AtomicReference<Session<T>> atomicReference, Object obj) {
        return (obj instanceof SessionMutex) && p.a(atomicReference, ((SessionMutex) obj).getCurrentSessionHolder());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3115equalsimpl0(AtomicReference<Session<T>> atomicReference, AtomicReference<Session<T>> atomicReference2) {
        return p.a(atomicReference, atomicReference2);
    }

    /* JADX INFO: renamed from: getCurrentSession-impl, reason: not valid java name */
    public static final T m3116getCurrentSessionimpl(AtomicReference<Session<T>> atomicReference) {
        Session<T> session = atomicReference.get();
        if (session != null) {
            return session.getValue();
        }
        return null;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3117hashCodeimpl(AtomicReference<Session<T>> atomicReference) {
        return atomicReference.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3118toStringimpl(AtomicReference<Session<T>> atomicReference) {
        return "SessionMutex(currentSessionHolder=" + atomicReference + ')';
    }

    /* JADX INFO: renamed from: withSessionCancellingPrevious-impl, reason: not valid java name */
    public static final <R> Object m3119withSessionCancellingPreviousimpl(AtomicReference<Session<T>> atomicReference, l<? super k0, ? extends T> lVar, r7.p<? super T, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        return l0.c(new SessionMutex$withSessionCancellingPrevious$2(lVar, atomicReference, pVar, null), dVar);
    }

    public boolean equals(Object obj) {
        return m3114equalsimpl(this.currentSessionHolder, obj);
    }

    public int hashCode() {
        return m3117hashCodeimpl(this.currentSessionHolder);
    }

    public String toString() {
        return m3118toStringimpl(this.currentSessionHolder);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ AtomicReference getCurrentSessionHolder() {
        return this.currentSessionHolder;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> AtomicReference<Session<T>> m3112constructorimpl() {
        return m3113constructorimpl(new AtomicReference(null));
    }
}
