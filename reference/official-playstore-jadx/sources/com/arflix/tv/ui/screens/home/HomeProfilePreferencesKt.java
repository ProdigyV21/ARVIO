package com.arflix.tv.ui.screens.home;

import java.util.LinkedHashMap;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a1\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\f\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007H\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lc1/g;", "preferences", "", "profileId", "Lcom/arflix/tv/ui/screens/home/HomeProfilePreferences;", "readHomeProfilePreferences", "(Lc1/g;Ljava/lang/String;)Lcom/arflix/tv/ui/screens/home/HomeProfilePreferences;", "Lna/j;", "activeProfileId", "observeHomeProfilePreferences", "(Lna/j;Lna/j;)Lna/j;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HomeProfilePreferencesKt {

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeProfilePreferencesKt$observeHomeProfilePreferences$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", "profileId", "Lc1/g;", "profilePreferences", "Lcom/arflix/tv/ui/screens/home/HomeProfilePreferences;", "<anonymous>", "(Ljava/lang/String;Lc1/g;)Lcom/arflix/tv/ui/screens/home/HomeProfilePreferences;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeProfilePreferencesKt$observeHomeProfilePreferences$1", f = "HomeProfilePreferences.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.q<String, c1.g, d7.d<? super HomeProfilePreferences>, Object> {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            c1.g gVar = (c1.g) this.L$1;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return HomeProfilePreferencesKt.readHomeProfilePreferences(gVar, str);
        }

        @Override // r7.q
        public final Object invoke(String str, c1.g gVar, d7.d<? super HomeProfilePreferences> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = str;
            anonymousClass1.L$1 = gVar;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }
    }

    public static final na.j<HomeProfilePreferences> observeHomeProfilePreferences(na.j<String> jVar, na.j<? extends c1.g> jVar2) {
        return y0.h(new na.n0(jVar, jVar2, new AnonymousClass1(null)));
    }

    public static final HomeProfilePreferences readHomeProfilePreferences(c1.g gVar, String str) {
        Integer numR;
        String strL = a0.c.l("profile_", str, "_");
        c1.e eVar = new c1.e("last_app_language");
        LinkedHashMap linkedHashMap = ((c1.b) gVar).f7368a;
        String str2 = (String) linkedHashMap.get(eVar);
        if (str2 == null) {
            str2 = "en-US";
        }
        String str3 = (String) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "content_language")));
        String str4 = str3 == null ? str2 : str3;
        Boolean bool = (Boolean) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "trailer_auto_play")));
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Boolean bool2 = (Boolean) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "trailer_sound_enabled")));
        boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : false;
        String str5 = (String) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "trailer_delay_seconds")));
        int iIntValue = (str5 == null || (numR = kotlin.text.u.R(str5)) == null) ? 2 : numR.intValue();
        Boolean bool3 = (Boolean) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "trailer_in_cards")));
        boolean zBooleanValue3 = bool3 != null ? bool3.booleanValue() : true;
        Boolean bool4 = (Boolean) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "show_budget_on_home")));
        boolean zBooleanValue4 = bool4 != null ? bool4.booleanValue() : true;
        String str6 = (String) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "clock_format")));
        if (str6 == null) {
            str6 = "24h";
        }
        String str7 = str6;
        Boolean bool5 = (Boolean) linkedHashMap.get(new c1.e(androidx.compose.material3.d.m(strL, "smooth_scrolling")));
        return new HomeProfilePreferences(zBooleanValue, zBooleanValue2, iIntValue, zBooleanValue3, zBooleanValue4, str7, bool5 != null ? bool5.booleanValue() : false, str4);
    }
}
