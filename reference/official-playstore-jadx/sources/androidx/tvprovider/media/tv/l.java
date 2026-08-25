package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public final class l extends g {
    static {
    }

    public final ContentValues b() {
        ContentValues contentValuesA = super.a();
        if (Build.VERSION.SDK_INT < 26) {
            contentValuesA.remove("channel_id");
            contentValuesA.remove("weight");
        }
        return contentValuesA;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof l) {
            return this.f6609a.equals(((l) obj).f6609a);
        }
        return false;
    }

    public final String toString() {
        return "PreviewProgram{" + this.f6609a.toString() + "}";
    }
}
