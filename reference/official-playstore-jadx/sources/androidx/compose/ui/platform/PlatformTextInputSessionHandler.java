package androidx.compose.ui.platform;

import io.github.jan.supabase.gotrue.SettingsSessionManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J9\u0010\b\u001a\u00020\u00052'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0002¢\u0006\u0002\b\u0006H¦@¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/PlatformTextInputSessionHandler;", "", "Lkotlin/Function2;", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "Ld7/d;", "", "Lx6/n;", SettingsSessionManager.SETTINGS_KEY, "textInputSession", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PlatformTextInputSessionHandler {
    Object textInputSession(r7.p<? super PlatformTextInputSessionScope, ? super d7.d<?>, ? extends Object> pVar, d7.d<?> dVar);
}
