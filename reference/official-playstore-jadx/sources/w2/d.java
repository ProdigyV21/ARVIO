package w2;

import androidx.navigation.l0;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.navigation.AppNavigationKt;
import r7.p;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22345i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f22346l;

    public /* synthetic */ d(l0 l0Var, int i10) {
        this.f22345i = i10;
        this.f22346l = l0Var;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f22345i;
        MediaType mediaType = (MediaType) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return AppNavigationKt.AppNavigation$lambda$9$0$19$3$0(this.f22346l, mediaType, iIntValue);
            case 1:
                return AppNavigationKt.AppNavigation$lambda$9$0$2$0$0(this.f22346l, mediaType, iIntValue);
            case 2:
                return AppNavigationKt.AppNavigation$lambda$9$0$14$0$0(this.f22346l, mediaType, iIntValue);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$3$0$0(this.f22346l, mediaType, iIntValue);
        }
    }
}
