package com.google.android.gms.cast;

import com.google.android.gms.common.internal.t;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MediaSeekOptions {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    private final long zza;
    private final int zzb;
    private final boolean zzc;
    private final JSONObject zzd;

    public static class Builder {
        private long zza;
        private int zzb = 0;
        private boolean zzc;
        private JSONObject zzd;

        public MediaSeekOptions build() {
            return new MediaSeekOptions(this.zza, this.zzb, this.zzc, this.zzd, null);
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzd = jSONObject;
            return this;
        }

        public Builder setIsSeekToInfinite(boolean z) {
            this.zzc = z;
            return this;
        }

        public Builder setPosition(long j10) {
            this.zza = j10;
            return this;
        }

        public Builder setResumeState(int i10) {
            this.zzb = i10;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResumeState {
    }

    public /* synthetic */ MediaSeekOptions(long j10, int i10, boolean z, JSONObject jSONObject, byte[] bArr) {
        this.zza = j10;
        this.zzb = i10;
        this.zzc = z;
        this.zzd = jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSeekOptions)) {
            return false;
        }
        MediaSeekOptions mediaSeekOptions = (MediaSeekOptions) obj;
        return this.zza == mediaSeekOptions.zza && this.zzb == mediaSeekOptions.zzb && this.zzc == mediaSeekOptions.zzc && t.l(this.zzd, mediaSeekOptions.zzd);
    }

    public JSONObject getCustomData() {
        return this.zzd;
    }

    public long getPosition() {
        return this.zza;
    }

    public int getResumeState() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zza), Integer.valueOf(this.zzb), Boolean.valueOf(this.zzc), this.zzd});
    }

    public boolean isSeekToInfinite() {
        return this.zzc;
    }
}
