package x2;

import androidx.compose.runtime.Composer;
import androidx.tv.material3.h1;
import com.arflix.tv.MainActivity;
import com.arflix.tv.ui.theme.ThemeKt;
import com.arflix.tv.util.DeviceType;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22556i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f22557l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f22558m;

    public /* synthetic */ b(Object obj, Object obj2, int i10) {
        this.f22556i = i10;
        this.f22557l = obj;
        this.f22558m = obj2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f22556i) {
            case 0:
                return ThemeKt.ArvioTvTheme$lambda$1$0((h1) this.f22557l, (p) this.f22558m, (Composer) obj, ((Integer) obj2).intValue());
            case 1:
                return ThemeKt.ArvioTvTheme$lambda$1((h1) this.f22557l, (p) this.f22558m, (Composer) obj, ((Integer) obj2).intValue());
            default:
                return MainActivity.onCreate$lambda$4((MainActivity) this.f22557l, (DeviceType) this.f22558m, (Composer) obj, ((Integer) obj2).intValue());
        }
    }
}
