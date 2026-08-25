package androidx.activity.result;

import android.os.Bundle;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.lifecycle.p;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f992i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f993l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f994m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f995n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f996o;

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f992i = i10;
        this.f993l = obj;
        this.f994m = obj2;
        this.f995n = obj3;
        this.f996o = obj4;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        switch (this.f992i) {
            case 0:
                k kVar = (k) this.f993l;
                LinkedHashMap linkedHashMap = kVar.f1009e;
                String str = (String) this.f994m;
                b bVar = (b) this.f995n;
                g.a aVar = (g.a) this.f996o;
                if (p.ON_START == pVar) {
                    Bundle bundle = kVar.f1011g;
                    LinkedHashMap linkedHashMap2 = kVar.f1010f;
                    linkedHashMap.put(str, new f(aVar, bVar));
                    if (linkedHashMap2.containsKey(str)) {
                        Object obj = linkedHashMap2.get(str);
                        linkedHashMap2.remove(str);
                        bVar.a(obj);
                    }
                    a aVar2 = (a) t7.a.z(bundle, str);
                    if (aVar2 != null) {
                        bundle.remove(str);
                        bVar.a(aVar.c(aVar2.f991l, aVar2.f990i));
                    }
                } else if (p.ON_STOP == pVar) {
                    linkedHashMap.remove(str);
                } else if (p.ON_DESTROY == pVar) {
                    kVar.f(str);
                }
                break;
            default:
                LiveTvScreenKt.LiveTvScreen$lambda$213$0$0((ExoPlayer) this.f993l, (TvViewModel) this.f994m, (MutableState) this.f995n, (State) this.f996o, yVar, pVar);
                break;
        }
    }
}
