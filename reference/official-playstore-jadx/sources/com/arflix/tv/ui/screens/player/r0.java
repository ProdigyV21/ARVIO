package com.arflix.tv.ui.screens.player;

import android.graphics.Bitmap;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.tv.TvScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9718i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9719l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9720m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9721n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f9722o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9723p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9724q;

    public /* synthetic */ r0(Bitmap bitmap, String str, boolean z, r7.a aVar, int i10, int i11) {
        this.f9724q = bitmap;
        this.f9719l = str;
        this.f9720m = z;
        this.f9721n = aVar;
        this.f9722o = i10;
        this.f9723p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9718i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PlayerScreenKt.TabButton$lambda$2((String) this.f9719l, this.f9720m, (r7.a) this.f9721n, (Modifier) this.f9724q, this.f9722o, this.f9723p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.AiKeyQrOverlay$lambda$4((Bitmap) this.f9724q, (String) this.f9719l, this.f9720m, (r7.a) this.f9721n, this.f9722o, this.f9723p, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return TvScreenKt.FullscreenEpgOverlay$lambda$3((IptvChannel) this.f9719l, (IptvProgram) this.f9721n, (IptvProgram) this.f9724q, this.f9720m, this.f9722o, this.f9723p, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ r0(IptvChannel iptvChannel, IptvProgram iptvProgram, IptvProgram iptvProgram2, boolean z, int i10, int i11) {
        this.f9719l = iptvChannel;
        this.f9721n = iptvProgram;
        this.f9724q = iptvProgram2;
        this.f9720m = z;
        this.f9722o = i10;
        this.f9723p = i11;
    }

    public /* synthetic */ r0(String str, boolean z, r7.a aVar, Modifier modifier, int i10, int i11) {
        this.f9719l = str;
        this.f9720m = z;
        this.f9721n = aVar;
        this.f9724q = modifier;
        this.f9722o = i10;
        this.f9723p = i11;
    }
}
