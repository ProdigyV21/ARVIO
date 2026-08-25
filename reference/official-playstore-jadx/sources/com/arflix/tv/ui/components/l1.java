package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.player.AudioTrackInfo;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import com.arflix.tv.ui.screens.plugin.ComposableSingletons$PluginScreenKt;
import com.arflix.tv.ui.screens.profile.ComposableSingletons$ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.ComposableSingletons$TelegramSettingsScreenKt;
import d7.j;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8159i;

    public /* synthetic */ l1(int i10) {
        this.f8159i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        d7.c cVar;
        switch (this.f8159i) {
            case 0:
                return PersonModalKt.PersonModal$lambda$1$0((MediaType) obj, ((Integer) obj2).intValue());
            case 1:
                return PlayerScreenKt.SubtitleMenu$lambda$7$1$1$1$0$0(((Integer) obj).intValue(), (AudioTrackInfo) obj2);
            case 2:
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$2(((Integer) obj).intValue(), (AudioTrackInfo) obj2);
            case 3:
                return ComposableSingletons$PluginScreenKt.lambda_891478482$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 4:
                return ComposableSingletons$ProfileSelectionScreenKt.lambda_2090623951$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 5:
                return ComposableSingletons$TelegramSettingsScreenKt.lambda__1398904882$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 6:
                return ComposableSingletons$TelegramSettingsScreenKt.lambda_1913069037$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 7:
                return ComposableSingletons$TelegramSettingsScreenKt.lambda__941610430$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 8:
                return ComposableSingletons$TelegramSettingsScreenKt.lambda__260574925$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 9:
                String str = (String) obj;
                j.a aVar = (j.a) obj2;
                if (str.length() == 0) {
                    return aVar.toString();
                }
                return str + ", " + aVar;
            default:
                j.a aVar2 = (j.a) obj2;
                d7.j jVarMinusKey = ((d7.j) obj).minusKey(aVar2.getKey());
                d7.k kVar = d7.k.f14688i;
                if (jVarMinusKey == kVar) {
                    return aVar2;
                }
                d7.f fVar = d7.f.f14687i;
                d7.g gVar = (d7.g) jVarMinusKey.get(fVar);
                if (gVar == null) {
                    cVar = new d7.c(jVarMinusKey, aVar2);
                } else {
                    d7.j jVarMinusKey2 = jVarMinusKey.minusKey(fVar);
                    if (jVarMinusKey2 == kVar) {
                        return new d7.c(aVar2, gVar);
                    }
                    cVar = new d7.c(new d7.c(jVarMinusKey2, aVar2), gVar);
                }
                return cVar;
        }
    }
}
