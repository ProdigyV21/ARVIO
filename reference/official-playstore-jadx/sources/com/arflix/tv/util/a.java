package com.arflix.tv.util;

import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.navigation.h;
import androidx.navigation.o0;
import com.arflix.tv.navigation.AppNavigationKt;
import ga.m;
import h.f0;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r0;
import kotlin.reflect.q;
import kotlin.reflect.t;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12245i;

    public /* synthetic */ a(int i10) {
        this.f12245i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        String strC;
        switch (this.f12245i) {
            case 0:
                return Boolean.valueOf(CrashReportFilter.containsNetworkFailure$lambda$0((Throwable) obj));
            case 1:
                return ((m) obj).iterator();
            case 2:
                return ((Iterable) obj).iterator();
            case 3:
                return obj;
            case 4:
                return Boolean.valueOf(obj == null);
            case 5:
                t tVar = (t) obj;
                int i10 = tVar.f19911a;
                q qVar = tVar.f19912b;
                if (i10 == 0) {
                    return "*";
                }
                r0 r0Var = qVar instanceof r0 ? (r0) qVar : null;
                String strValueOf = (r0Var == null || (strC = r0Var.c(true)) == null) ? String.valueOf(qVar) : strC;
                int iC = f0.c(i10);
                if (iC == 0) {
                    return strValueOf;
                }
                if (iC == 1) {
                    return "in ".concat(strValueOf);
                }
                if (iC == 2) {
                    return "out ".concat(strValueOf);
                }
                throw new NoWhenBranchMatchedException();
            case 6:
                return AppNavigationKt.AppNavigation$lambda$8$0((AnimatedContentTransitionScope) obj);
            case 7:
                return AppNavigationKt.AppNavigation$lambda$4$0$0((o0) obj);
            case 8:
                return AppNavigationKt.AppNavigation$lambda$9$0$12$0$0$0((o0) obj);
            case 9:
                return AppNavigationKt.AppNavigation$lambda$9$0$10$5$0$0((o0) obj);
            case 10:
                return AppNavigationKt.AppNavigation$lambda$9$0$13((h) obj);
            case 11:
                return AppNavigationKt.AppNavigation$lambda$9$0$15((h) obj);
            case 12:
                return AppNavigationKt.AppNavigation$lambda$9$0$16((h) obj);
            case 13:
                return AppNavigationKt.AppNavigation$lambda$9$0$17((h) obj);
            case 14:
                return AppNavigationKt.AppNavigation$lambda$9$0$18((h) obj);
            case 15:
                return AppNavigationKt.AppNavigation$lambda$9$0$20((h) obj);
            case 16:
                return AppNavigationKt.AppNavigation$lambda$9$0$21((h) obj);
            case 17:
                return AppNavigationKt.AppNavigation$lambda$9$0$22((h) obj);
            case 18:
                return AppNavigationKt.AppNavigation$lambda$9$0$23((h) obj);
            case 19:
                return AppNavigationKt.AppNavigation$lambda$9$0$24((h) obj);
            case 20:
                return AppNavigationKt.AppNavigation$lambda$9$0$25((h) obj);
            case 21:
                return AppNavigationKt.AppNavigation$lambda$9$0$26((h) obj);
            case 22:
                return AppNavigationKt.AppNavigation$lambda$9$0$27((h) obj);
            case 23:
                return AppNavigationKt.AppNavigation$lambda$9$0$28((h) obj);
            case 24:
                return AppNavigationKt.AppNavigation$lambda$9$0$29((h) obj);
            case 25:
                return AppNavigationKt.AppNavigation$lambda$9$0$30((h) obj);
            case 26:
                return AppNavigationKt.AppNavigation$lambda$1$0(((Boolean) obj).booleanValue());
            case 27:
                return AppNavigationKt.AppNavigation$lambda$9$0$31((h) obj);
            case 28:
                return AppNavigationKt.AppNavigation$lambda$9$0$32((h) obj);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$33((h) obj);
        }
    }

    public /* synthetic */ a(r0 r0Var) {
        this.f12245i = 5;
    }
}
