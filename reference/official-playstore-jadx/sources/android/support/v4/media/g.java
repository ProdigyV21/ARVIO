package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.session.v;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f844a;

    public g() {
        this.f844a = new Bundle();
    }

    public final void a(String str, String str2) {
        t.e eVar = MediaMetadataCompat.f838m;
        if (eVar.containsKey(str) && ((Integer) eVar.get(str)).intValue() != 1) {
            throw new IllegalArgumentException(a0.c.l("The ", str, " key cannot be used to put a String"));
        }
        this.f844a.putCharSequence(str, str2);
    }

    public g(MediaMetadataCompat mediaMetadataCompat) {
        Bundle bundle = new Bundle(mediaMetadataCompat.f839i);
        this.f844a = bundle;
        v.a(bundle);
    }
}
