package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public final class z extends g {
    static {
    }

    public final ContentValues b() {
        ContentValues contentValuesA = super.a();
        if (Build.VERSION.SDK_INT < 26) {
            contentValuesA.remove("watch_next_type");
            contentValuesA.remove("last_engagement_time_utc_millis");
        }
        return contentValuesA;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof z) {
            return this.f6609a.equals(((z) obj).f6609a);
        }
        return false;
    }

    public final String toString() {
        return "WatchNextProgram{" + this.f6609a.toString() + "}";
    }
}
