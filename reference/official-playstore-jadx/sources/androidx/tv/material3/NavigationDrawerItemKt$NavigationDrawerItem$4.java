package androidx.tv.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NavigationDrawerItemKt$NavigationDrawerItem$4 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o2 f5690i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.p f5691l;

    /* JADX INFO: renamed from: androidx.tv.material3.NavigationDrawerItemKt$NavigationDrawerItem$4$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/animation/AnimatedVisibilityScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.q<AnimatedVisibilityScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.p f5692i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(r7.p pVar) {
            super(3);
            this.f5692i = pVar;
        }

        @Override // r7.q
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((AnimatedVisibilityScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1104783670, i10, -1, "androidx.tv.material3.NavigationDrawerItem.<anonymous>.<anonymous> (NavigationDrawerItem.kt:107)");
            }
            if (a0.c.A(composer, 0, this.f5692i)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationDrawerItemKt$NavigationDrawerItem$4(o2 o2Var, r7.p pVar) {
        super(2);
        this.f5690i = o2Var;
        this.f5691l = pVar;
    }

    @Override // r7.p
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2013163790, i10, -1, "androidx.tv.material3.NavigationDrawerItem.<anonymous> (NavigationDrawerItem.kt:102)");
        }
        AnimatedVisibilityKt.AnimatedVisibility(this.f5690i.getHasFocus(), (Modifier) null, NavigationDrawerItemDefaults.f5680h, NavigationDrawerItemDefaults.f5681i, (String) null, ComposableLambdaKt.composableLambda(composer, 1104783670, true, new AnonymousClass1(this.f5691l)), composer, 200064, 18);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
