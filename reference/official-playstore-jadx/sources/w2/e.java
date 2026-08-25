package w2;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.navigation.l0;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.navigation.AppNavigationKt;
import r7.r;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22347i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f22348l;

    public /* synthetic */ e(l0 l0Var, int i10) {
        this.f22347i = i10;
        this.f22348l = l0Var;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f22347i) {
            case 0:
                int iIntValue = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$0(this.f22348l, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$11(this.f22348l, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj4).intValue();
                return AppNavigationKt.AppNavigation$lambda$9$0$35(this.f22348l, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, iIntValue3);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$1$0$0(this.f22348l, (MediaType) obj, ((Integer) obj2).intValue(), (Integer) obj3, (Integer) obj4);
        }
    }
}
