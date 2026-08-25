package androidx.navigation.compose;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.navigation.h0;
import androidx.navigation.i0;
import androidx.navigation.l0;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\u001aN\u0010\f\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001aÜ\u0001\u0010\f\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u001f\b\u0002\u0010\u0013\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00120\u0007¢\u0006\u0002\b\n2\u001f\b\u0002\u0010\u0015\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00140\u0007¢\u0006\u0002\b\n2\u001f\b\u0002\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00120\u0007¢\u0006\u0002\b\n2\u001f\b\u0002\u0010\u0017\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00140\u0007¢\u0006\u0002\b\n2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\f\u0010\u0018\u001a)\u0010\f\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\u001b\u001a·\u0001\u0010\f\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u001f\b\u0002\u0010\u0013\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00120\u0007¢\u0006\u0002\b\n2\u001f\b\u0002\u0010\u0015\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00140\u0007¢\u0006\u0002\b\n2\u001f\b\u0002\u0010\u0016\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00120\u0007¢\u0006\u0002\b\n2\u001f\b\u0002\u0010\u0017\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00140\u0007¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\f\u0010\u001c¨\u0006!²\u0006\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u001d8\nX\u008a\u0084\u0002²\u0006\u0012\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u001d8\nX\u008a\u0084\u0002²\u0006\u0012\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\u001d8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/navigation/l0;", "navController", "", "startDestination", "Landroidx/compose/ui/Modifier;", "modifier", "route", "Lkotlin/Function1;", "Landroidx/navigation/i0;", "Lx6/t0;", "Lx6/n;", "builder", "NavHost", "(Landroidx/navigation/l0;Ljava/lang/String;Landroidx/compose/ui/Modifier;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/Alignment;", "contentAlignment", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/navigation/i;", "Landroidx/compose/animation/EnterTransition;", "enterTransition", "Landroidx/compose/animation/ExitTransition;", "exitTransition", "popEnterTransition", "popExitTransition", "(Landroidx/navigation/l0;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "Landroidx/navigation/h0;", "graph", "(Landroidx/navigation/l0;Landroidx/navigation/h0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/navigation/l0;Landroidx/navigation/h0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "", "currentBackStack", "allVisibleEntries", "visibleEntries", "navigation-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavHostKt {

    /* JADX INFO: renamed from: androidx.navigation.compose.NavHostKt$NavHost$14, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/AnimatedContentScope;", "Landroidx/navigation/i;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedContentScope;Landroidx/navigation/i;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass14 extends kotlin.jvm.internal.r implements r7.r<AnimatedContentScope, androidx.navigation.i, Composer, Integer, t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ SaveableStateHolder f4550i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ State f4551l;

        /* JADX INFO: renamed from: androidx.navigation.compose.NavHostKt$NavHost$14$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            public final /* synthetic */ androidx.navigation.i f4552i;

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ AnimatedContentScope f4553l;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(androidx.navigation.i iVar, AnimatedContentScope animatedContentScope) {
                super(2);
                this.f4552i = iVar;
                this.f4553l = animatedContentScope;
            }

            @Override // r7.p
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return t0.f22605a;
            }

            public final void invoke(Composer composer, int i10) {
                if ((i10 & 11) == 2 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1425390790, i10, -1, "androidx.navigation.compose.NavHost.<anonymous>.<anonymous> (NavHost.kt:306)");
                }
                androidx.navigation.i iVar = this.f4552i;
                ((e) iVar.f4681l).f4562s.invoke(this.f4553l, iVar, composer, 72);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass14(SaveableStateHolder saveableStateHolder, State state) {
            super(4);
            this.f4550i = saveableStateHolder;
            this.f4551l = state;
        }

        @Override // r7.r
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(AnimatedContentScope animatedContentScope, androidx.navigation.i iVar, Composer composer, int i10) {
            Object objPrevious;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1440061047, i10, -1, "androidx.navigation.compose.NavHost.<anonymous> (NavHost.kt:301)");
            }
            List list = (List) this.f4551l.getValue();
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                } else {
                    objPrevious = listIterator.previous();
                    if (kotlin.jvm.internal.p.a(iVar, (androidx.navigation.i) objPrevious)) {
                        break;
                    }
                }
            }
            androidx.navigation.i iVar2 = (androidx.navigation.i) objPrevious;
            if (iVar2 != null) {
                NavBackStackEntryProviderKt.LocalOwnersProvider(iVar2, this.f4550i, ComposableLambdaKt.composableLambda(composer, -1425390790, true, new AnonymousClass1(iVar2, animatedContentScope)), composer, 456);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    @x6.e
    public static final void NavHost(l0 l0Var, String str, Modifier modifier, String str2, r7.l lVar, Composer composer, int i10, int i11) {
        l0 l0Var2;
        Object objA;
        Composer composerStartRestartGroup = composer.startRestartGroup(141827520);
        Modifier modifier2 = (i11 & 4) != 0 ? Modifier.INSTANCE : modifier;
        String str3 = (i11 & 8) != 0 ? null : str2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(141827520, i10, -1, "androidx.navigation.compose.NavHost (NavHost.kt:80)");
        }
        composerStartRestartGroup.startReplaceableGroup(1618982084);
        boolean zChanged = composerStartRestartGroup.changed(str3) | composerStartRestartGroup.changed(str) | composerStartRestartGroup.changed(lVar);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            l0Var2 = l0Var;
            i0 i0Var = new i0(l0Var2.f4780v, str, str3);
            lVar.invoke(i0Var);
            objA = i0Var.a();
            composerStartRestartGroup.updateRememberedValue(objA);
        } else {
            objA = objRememberedValue;
            l0Var2 = l0Var;
        }
        composerStartRestartGroup.endReplaceableGroup();
        NavHost(l0Var2, (h0) objA, modifier2, null, null, null, null, null, composerStartRestartGroup, (i10 & 896) | 72, 248);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new x(l0Var, str, modifier2, str3, lVar, i10, i11));
    }

    public static final void NavHost(l0 l0Var, String str, Modifier modifier, Alignment alignment, String str2, r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends EnterTransition> lVar, r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends ExitTransition> lVar2, r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends EnterTransition> lVar3, r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends ExitTransition> lVar4, r7.l<? super i0, t0> lVar5, Composer composer, int i10, int i11) {
        r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends EnterTransition> lVar6;
        int i12;
        r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends ExitTransition> lVar7;
        l0 l0Var2;
        Composer composerStartRestartGroup = composer.startRestartGroup(410432995);
        Modifier modifier2 = (i11 & 4) != 0 ? Modifier.INSTANCE : modifier;
        Alignment center = (i11 & 8) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        String str3 = (i11 & 16) != 0 ? null : str2;
        r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends EnterTransition> lVar8 = (i11 & 32) != 0 ? u.f4608m : lVar;
        r7.l<? super AnimatedContentTransitionScope<androidx.navigation.i>, ? extends ExitTransition> lVar9 = (i11 & 64) != 0 ? u.f4609n : lVar2;
        if ((i11 & 128) != 0) {
            i12 = i10 & (-29360129);
            lVar6 = lVar8;
        } else {
            lVar6 = lVar3;
            i12 = i10;
        }
        if ((i11 & 256) != 0) {
            i12 &= -234881025;
            lVar7 = lVar9;
        } else {
            lVar7 = lVar4;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(410432995, i12, -1, "androidx.navigation.compose.NavHost (NavHost.kt:126)");
        }
        composerStartRestartGroup.startReplaceableGroup(1618982084);
        boolean zChanged = composerStartRestartGroup.changed(str3) | composerStartRestartGroup.changed(str) | composerStartRestartGroup.changed(lVar5);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            l0Var2 = l0Var;
            i0 i0Var = new i0(l0Var2.f4780v, str, str3);
            lVar5.invoke(i0Var);
            objRememberedValue = i0Var.a();
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        } else {
            l0Var2 = l0Var;
        }
        composerStartRestartGroup.endReplaceableGroup();
        int i13 = (i12 & 896) | 72 | (i12 & 7168);
        int i14 = i12 >> 3;
        NavHost(l0Var2, (h0) objRememberedValue, modifier2, center, lVar8, lVar9, lVar6, lVar7, composerStartRestartGroup, i13 | (57344 & i14) | (458752 & i14) | (3670016 & i14) | (i14 & 29360128), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new y(l0Var, str, modifier2, center, str3, lVar8, lVar9, lVar6, lVar7, lVar5, i10, i11));
    }

    @x6.e
    public static final /* synthetic */ void NavHost(l0 l0Var, h0 h0Var, Modifier modifier, Composer composer, int i10, int i11) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-957014592);
        if ((i11 & 4) != 0) {
            modifier = Modifier.INSTANCE;
        }
        Modifier modifier2 = modifier;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-957014592, i10, -1, "androidx.navigation.compose.NavHost (NavHost.kt:163)");
        }
        NavHost(l0Var, h0Var, modifier2, null, null, null, null, null, composerStartRestartGroup, (i10 & 896) | 72, 248);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new z(i10, i11, 0, l0Var, h0Var, modifier2));
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0389  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavHost(androidx.navigation.l0 r35, androidx.navigation.h0 r36, androidx.compose.ui.Modifier r37, androidx.compose.ui.Alignment r38, r7.l<? super androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.i>, ? extends androidx.compose.animation.EnterTransition> r39, r7.l<? super androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.i>, ? extends androidx.compose.animation.ExitTransition> r40, r7.l<? super androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.i>, ? extends androidx.compose.animation.EnterTransition> r41, r7.l<? super androidx.compose.animation.AnimatedContentTransitionScope<androidx.navigation.i>, ? extends androidx.compose.animation.ExitTransition> r42, androidx.compose.runtime.Composer r43, int r44, int r45) {
        /*
            Method dump skipped, instruction units count: 2099
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.compose.NavHostKt.NavHost(androidx.navigation.l0, androidx.navigation.h0, androidx.compose.ui.Modifier, androidx.compose.ui.Alignment, r7.l, r7.l, r7.l, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }
}
