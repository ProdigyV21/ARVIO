package androidx.compose.runtime;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006JH\u0010\u0011\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072.\b\b\u0010\u000e\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0002\b\rH\u0086\b¢\u0006\u0004\b\u000f\u0010\u0010JI\u0010\u0011\u001a\u00020\f\"\u0004\b\u0001\u0010\u00122\u0006\u0010\b\u001a\u00028\u00012,\u0010\u000e\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0002\b\r¢\u0006\u0004\b\u000f\u0010\u0013JH\u0010\u0015\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072.\b\b\u0010\u000e\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0002\b\rH\u0086\b¢\u0006\u0004\b\u0014\u0010\u0010JI\u0010\u0015\u001a\u00020\f\"\u0004\b\u0001\u0010\u00122\u0006\u0010\b\u001a\u00028\u00012,\u0010\u000e\u001a(\u0012\u0004\u0012\u00028\u0000\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0002\b\r¢\u0006\u0004\b\u0014\u0010\u0013J&\u0010\u0019\u001a\u00020\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0016¢\u0006\u0002\b\r¢\u0006\u0004\b\u0017\u0010\u0018J&\u0010\u001b\u001a\u00020\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u0016¢\u0006\u0002\b\r¢\u0006\u0004\b\u001a\u0010\u0018J\u0010\u0010\u001f\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\"\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010'\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b%\u0010&R\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0004\u0010(\u0012\u0004\b)\u0010*\u0088\u0001\u0004\u0092\u0001\u00020\u0003¨\u0006+"}, d2 = {"Landroidx/compose/runtime/Updater;", "T", "", "Landroidx/compose/runtime/Composer;", "composer", "constructor-impl", "(Landroidx/compose/runtime/Composer;)Landroidx/compose/runtime/Composer;", "", "value", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "Lx6/t0;", "Lx6/n;", "block", "set-impl", "(Landroidx/compose/runtime/Composer;ILr7/p;)V", "set", "V", "(Landroidx/compose/runtime/Composer;Ljava/lang/Object;Lr7/p;)V", "update-impl", "update", "Lkotlin/Function1;", "init-impl", "(Landroidx/compose/runtime/Composer;Lr7/l;)V", "init", "reconcile-impl", "reconcile", "", "toString-impl", "(Landroidx/compose/runtime/Composer;)Ljava/lang/String;", "toString", "hashCode-impl", "(Landroidx/compose/runtime/Composer;)I", "hashCode", "other", "", "equals-impl", "(Landroidx/compose/runtime/Composer;Ljava/lang/Object;)Z", "equals", "Landroidx/compose/runtime/Composer;", "getComposer$annotations", "()V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class Updater<T> {
    private final Composer composer;

    private /* synthetic */ Updater(Composer composer) {
        this.composer = composer;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Updater m2990boximpl(Composer composer) {
        return new Updater(composer);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> Composer m2991constructorimpl(Composer composer) {
        return composer;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2992equalsimpl(Composer composer, Object obj) {
        return (obj instanceof Updater) && p.a(composer, ((Updater) obj).getComposer());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2993equalsimpl0(Composer composer, Composer composer2) {
        return p.a(composer, composer2);
    }

    public static /* synthetic */ void getComposer$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2994hashCodeimpl(Composer composer) {
        return composer.hashCode();
    }

    /* JADX INFO: renamed from: init-impl, reason: not valid java name */
    public static final void m2995initimpl(Composer composer, r7.l<? super T, t0> lVar) {
        if (composer.getInserting()) {
            composer.apply(t0.f22605a, new Updater$init$1(lVar));
        }
    }

    /* JADX INFO: renamed from: reconcile-impl, reason: not valid java name */
    public static final void m2996reconcileimpl(Composer composer, r7.l<? super T, t0> lVar) {
        composer.apply(t0.f22605a, new Updater$reconcile$1(lVar));
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m2997setimpl(Composer composer, int i10, r7.p<? super T, ? super Integer, t0> pVar) {
        if (composer.getInserting() || !p.a(composer.rememberedValue(), Integer.valueOf(i10))) {
            a0.c.w(i10, composer, i10, pVar);
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2999toStringimpl(Composer composer) {
        return "Updater(composer=" + composer + ')';
    }

    /* JADX INFO: renamed from: update-impl, reason: not valid java name */
    public static final void m3000updateimpl(Composer composer, int i10, r7.p<? super T, ? super Integer, t0> pVar) {
        boolean inserting = composer.getInserting();
        if (inserting || !p.a(composer.rememberedValue(), Integer.valueOf(i10))) {
            composer.updateRememberedValue(Integer.valueOf(i10));
            if (inserting) {
                return;
            }
            composer.apply(Integer.valueOf(i10), pVar);
        }
    }

    public boolean equals(Object obj) {
        return m2992equalsimpl(this.composer, obj);
    }

    public int hashCode() {
        return m2994hashCodeimpl(this.composer);
    }

    public String toString() {
        return m2999toStringimpl(this.composer);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Composer getComposer() {
        return this.composer;
    }

    /* JADX INFO: renamed from: update-impl, reason: not valid java name */
    public static final <V> void m3001updateimpl(Composer composer, V v2, r7.p<? super T, ? super V, t0> pVar) {
        boolean inserting = composer.getInserting();
        if (inserting || !p.a(composer.rememberedValue(), v2)) {
            composer.updateRememberedValue(v2);
            if (inserting) {
                return;
            }
            composer.apply(v2, pVar);
        }
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final <V> void m2998setimpl(Composer composer, V v2, r7.p<? super T, ? super V, t0> pVar) {
        if (composer.getInserting() || !p.a(composer.rememberedValue(), v2)) {
            composer.updateRememberedValue(v2);
            composer.apply(v2, pVar);
        }
    }
}
