package w2;

import androidx.navigation.l0;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.navigation.AppNavigationKt;
import r7.s;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements s {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22373i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f22374l;

    public /* synthetic */ k(l0 l0Var, int i10) {
        this.f22373i = i10;
        this.f22374l = l0Var;
    }

    @Override // r7.s
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        switch (this.f22373i) {
            case 0:
                return AppNavigationKt.AppNavigation$lambda$9$0$1$5$0(this.f22374l, (MediaType) obj, ((Integer) obj2).intValue(), (String) obj3, (String) obj4, (String) obj5);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$14$1$0(this.f22374l, (MediaType) obj, ((Integer) obj2).intValue(), (String) obj3, (String) obj4, (String) obj5);
        }
    }
}
