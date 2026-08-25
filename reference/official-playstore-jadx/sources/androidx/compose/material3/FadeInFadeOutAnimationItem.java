package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BF\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u00125\u0010\f\u001a1\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u000b¢\u0006\u0002\b\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J?\u0010\u0011\u001a1\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u000b¢\u0006\u0002\b\u0007HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012JY\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u000027\b\u0002\u0010\f\u001a1\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u000b¢\u0006\u0002\b\u0007HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u0010RF\u0010\f\u001a1\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u000b¢\u0006\u0002\b\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010!\u001a\u0004\b\"\u0010\u0012¨\u0006#"}, d2 = {"Landroidx/compose/material3/FadeInFadeOutAnimationItem;", "T", "", "key", "Lkotlin/Function1;", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/y;", ContentDisposition.Parameters.Name, "content", "Landroidx/compose/material3/FadeInFadeOutTransition;", "transition", "<init>", "(Ljava/lang/Object;Lr7/q;)V", "component1", "()Ljava/lang/Object;", "component2", "()Lr7/q;", "copy", "(Ljava/lang/Object;Lr7/q;)Landroidx/compose/material3/FadeInFadeOutAnimationItem;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "getKey", "Lr7/q;", "getTransition", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FadeInFadeOutAnimationItem<T> {
    private final T key;
    private final q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> transition;

    /* JADX WARN: Multi-variable type inference failed */
    public FadeInFadeOutAnimationItem(T t2, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar) {
        this.key = t2;
        this.transition = qVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FadeInFadeOutAnimationItem copy$default(FadeInFadeOutAnimationItem fadeInFadeOutAnimationItem, Object obj, q qVar, int i10, Object obj2) {
        if ((i10 & 1) != 0) {
            obj = fadeInFadeOutAnimationItem.key;
        }
        if ((i10 & 2) != 0) {
            qVar = fadeInFadeOutAnimationItem.transition;
        }
        return fadeInFadeOutAnimationItem.copy(obj, qVar);
    }

    public final T component1() {
        return this.key;
    }

    public final q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> component2() {
        return this.transition;
    }

    public final FadeInFadeOutAnimationItem<T> copy(T key, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> transition) {
        return new FadeInFadeOutAnimationItem<>(key, transition);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FadeInFadeOutAnimationItem)) {
            return false;
        }
        FadeInFadeOutAnimationItem fadeInFadeOutAnimationItem = (FadeInFadeOutAnimationItem) other;
        return kotlin.jvm.internal.p.a(this.key, fadeInFadeOutAnimationItem.key) && kotlin.jvm.internal.p.a(this.transition, fadeInFadeOutAnimationItem.transition);
    }

    public final T getKey() {
        return this.key;
    }

    public final q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> getTransition() {
        return this.transition;
    }

    public int hashCode() {
        T t2 = this.key;
        return this.transition.hashCode() + ((t2 == null ? 0 : t2.hashCode()) * 31);
    }

    public String toString() {
        return "FadeInFadeOutAnimationItem(key=" + this.key + ", transition=" + this.transition + ')';
    }
}
