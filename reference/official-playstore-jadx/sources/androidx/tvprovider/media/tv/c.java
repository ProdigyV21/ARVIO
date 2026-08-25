package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import j$.util.DesugarTimeZone;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c extends h {
    static {
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").setTimeZone(DesugarTimeZone.getTimeZone("GMT-0"));
    }

    public c() {
        this.f6607i = new ContentValues();
    }
}
