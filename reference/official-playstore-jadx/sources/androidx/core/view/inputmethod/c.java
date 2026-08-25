package androidx.core.view.inputmethod;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.appcompat.widget.c0;
import androidx.core.view.b2;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c0 f2259a;

    public final boolean a(j jVar, int i10, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 25 && (i10 & 1) != 0) {
            try {
                jVar.f2266a.d();
                Parcelable parcelable = (Parcelable) jVar.f2266a.b();
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e5) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e5);
                return false;
            }
        }
        i iVar = jVar.f2266a;
        androidx.core.view.f fVar = new androidx.core.view.f(new ClipData(iVar.a(), new ClipData.Item(iVar.c())), 2);
        fVar.e(iVar.e());
        fVar.c(bundle);
        return b2.f(this.f2259a, fVar.a()) == null;
    }
}
