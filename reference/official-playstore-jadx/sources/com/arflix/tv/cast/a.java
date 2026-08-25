package com.arflix.tv.cast;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.local.PluginDataStore;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.ui.screens.login.LoginScreenKt;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.search.SearchViewModel;
import com.arflix.tv.util.CrashReportFilter;
import com.google.android.gms.cast.framework.CastContext;
import java.util.Map;
import java.util.Set;
import kotlin.collections.h;
import kotlin.jvm.internal.r;
import kotlin.text.j;
import r7.l;
import x7.i;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7609i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7610l;

    public /* synthetic */ a(Object obj, int i10) {
        this.f7609i = i10;
        this.f7610l = obj;
    }

    /* JADX WARN: Type inference failed for: r5v14, types: [kotlin.jvm.internal.r, r7.a] */
    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7609i) {
            case 0:
                return CastManager.initialize$lambda$0((CastManager) this.f7610l, (CastContext) obj);
            case 1:
                return Boolean.valueOf(PluginDataStore.addRepository$lambda$0((PluginRepository) this.f7610l, (PluginRepository) obj));
            case 2:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$2$0((FocusRequester) this.f7610l, (KeyboardActionScope) obj);
            case 3:
                return SearchScreenKt.SearchScreen$lambda$69$0$0$0$0((SearchViewModel) this.f7610l, (String) obj);
            case 4:
                return Boolean.valueOf(CrashReportFilter.containsClassName$lambda$0((Set) this.f7610l, (Throwable) obj));
            case 5:
                return ((r) this.f7610l).invoke();
            case 6:
                return obj == ((kotlin.collections.a) this.f7610l) ? "(this Collection)" : String.valueOf(obj);
            case 7:
                h hVar = (h) this.f7610l;
                Map.Entry entry = (Map.Entry) obj;
                StringBuilder sb2 = new StringBuilder();
                Object key = entry.getKey();
                sb2.append(key == hVar ? "(this Map)" : String.valueOf(key));
                sb2.append('=');
                Object value = entry.getValue();
                sb2.append(value != hVar ? String.valueOf(value) : "(this Map)");
                return sb2.toString();
            case 8:
                return ((j) this.f7610l).a(((Integer) obj).intValue());
            default:
                i iVar = (i) obj;
                return ((CharSequence) this.f7610l).subSequence(iVar.f22619i, iVar.f22620l + 1).toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ a(r7.a aVar) {
        this.f7609i = 5;
        this.f7610l = (r) aVar;
    }
}
