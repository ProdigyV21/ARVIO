package w2;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.navigation.l0;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.navigation.AppNavigationKt;
import r7.l;
import r7.r;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22349i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f22350l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Profile f22351m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l0 f22352n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ l f22353o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f22354p;

    public /* synthetic */ f(Profile profile, l0 l0Var, r7.a aVar, l lVar, r7.a aVar2, int i10) {
        this.f22349i = i10;
        this.f22351m = profile;
        this.f22352n = l0Var;
        this.f22350l = aVar;
        this.f22353o = lVar;
        this.f22354p = aVar2;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f22349i) {
            case 0:
                int iIntValue = ((Integer) obj4).intValue();
                Profile profile = this.f22351m;
                l0 l0Var = this.f22352n;
                return AppNavigationKt.AppNavigation$lambda$9$0$10(profile, this.f22350l, this.f22353o, l0Var, this.f22354p, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj4).intValue();
                Profile profile2 = this.f22351m;
                l0 l0Var2 = this.f22352n;
                return AppNavigationKt.AppNavigation$lambda$9$0$19(this.f22350l, profile2, l0Var2, this.f22353o, this.f22354p, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$2(this.f22351m, this.f22352n, this.f22350l, this.f22353o, this.f22354p, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue3);
            default:
                int iIntValue4 = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$3(this.f22351m, this.f22352n, this.f22350l, this.f22353o, this.f22354p, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue4);
        }
    }

    public /* synthetic */ f(Profile profile, r7.a aVar, l lVar, l0 l0Var, r7.a aVar2) {
        this.f22349i = 0;
        this.f22351m = profile;
        this.f22350l = aVar;
        this.f22353o = lVar;
        this.f22352n = l0Var;
        this.f22354p = aVar2;
    }

    public /* synthetic */ f(r7.a aVar, Profile profile, l0 l0Var, l lVar, r7.a aVar2) {
        this.f22349i = 1;
        this.f22350l = aVar;
        this.f22351m = profile;
        this.f22352n = l0Var;
        this.f22353o = lVar;
        this.f22354p = aVar2;
    }
}
