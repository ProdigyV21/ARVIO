package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9388i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9389l;

    public /* synthetic */ b(Object obj, int i10) {
        this.f9388i = i10;
        this.f9389l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9388i) {
            case 0:
                return Boolean.valueOf(PlaybackCookieJar.saveFromResponse$lambda$0$0((gb.q) this.f9389l, (gb.q) obj));
            case 1:
                return SkipIntroButtonKt.SkipIntroButton$lambda$11$0$0((MutableState) this.f9389l, (FocusState) obj);
            default:
                return ((JSONObject) this.f9389l).optJSONArray((String) obj);
        }
    }
}
