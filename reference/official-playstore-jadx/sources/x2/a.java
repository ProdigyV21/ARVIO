package x2;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.theme.ThemeKt;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22550i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f22551l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f22552m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f22553n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f22554o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f22555p;

    public /* synthetic */ a(boolean z, String str, p pVar, int i10, int i11, int i12) {
        this.f22550i = i12;
        this.f22551l = z;
        this.f22552m = str;
        this.f22553n = pVar;
        this.f22554o = i10;
        this.f22555p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f22550i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ThemeKt.ArflixTvTheme$lambda$0(this.f22551l, this.f22552m, this.f22553n, this.f22554o, this.f22555p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ThemeKt.ArvioTvTheme$lambda$2(this.f22551l, this.f22552m, this.f22553n, this.f22554o, this.f22555p, (Composer) obj, iIntValue2);
        }
    }
}
