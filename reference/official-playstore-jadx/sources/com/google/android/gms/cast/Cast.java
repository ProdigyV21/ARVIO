package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.q;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.internal.t;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class Cast {
    public static final int ACTIVE_INPUT_STATE_NO = 0;
    public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
    public static final int ACTIVE_INPUT_STATE_YES = 1;
    public static final i API;
    public static final CastApi CastApi;
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    public static final int STANDBY_STATE_NO = 0;
    public static final int STANDBY_STATE_UNKNOWN = -1;
    public static final int STANDBY_STATE_YES = 1;
    static final a zza;

    public interface ApplicationConnectionResult extends x {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();

        boolean getWasLaunched();
    }

    @Deprecated
    public interface CastApi {
        int getActiveInputState(q qVar) throws IllegalStateException;

        ApplicationMetadata getApplicationMetadata(q qVar) throws IllegalStateException;

        String getApplicationStatus(q qVar) throws IllegalStateException;

        int getStandbyState(q qVar) throws IllegalStateException;

        double getVolume(q qVar) throws IllegalStateException;

        boolean isMute(q qVar) throws IllegalStateException;

        v joinApplication(q qVar);

        v joinApplication(q qVar, String str);

        v joinApplication(q qVar, String str, String str2);

        v launchApplication(q qVar, String str);

        v launchApplication(q qVar, String str, LaunchOptions launchOptions);

        @Deprecated
        v launchApplication(q qVar, String str, boolean z);

        v leaveApplication(q qVar);

        void removeMessageReceivedCallbacks(q qVar, String str) throws IOException, IllegalArgumentException;

        void requestStatus(q qVar) throws IllegalStateException, IOException;

        v sendMessage(q qVar, String str, String str2);

        void setMessageReceivedCallbacks(q qVar, String str, MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException;

        void setMute(q qVar, boolean z) throws IllegalStateException, IOException;

        void setVolume(q qVar, double d4) throws IllegalStateException, IOException, IllegalArgumentException;

        v stopApplication(q qVar);

        v stopApplication(q qVar, String str);
    }

    public static final class CastOptions implements d.c {
        final CastDevice zza;
        final Listener zzb;
        final Bundle zzc;
        final int zzd;
        final String zze = UUID.randomUUID().toString();

        public static final class Builder {
            final CastDevice zza;
            final Listener zzb;
            private int zzc;
            private Bundle zzd;

            public Builder(CastDevice castDevice, Listener listener) {
                t.j(castDevice, "CastDevice parameter cannot be null");
                t.j(listener, "CastListener parameter cannot be null");
                this.zza = castDevice;
                this.zzb = listener;
                this.zzc = 0;
            }

            public CastOptions build() {
                return new CastOptions(this, null);
            }

            public Builder setVerboseLoggingEnabled(boolean z) {
                this.zzc = z ? 1 : 0;
                return this;
            }

            public final Builder zza(Bundle bundle) {
                this.zzd = bundle;
                return this;
            }

            public final /* synthetic */ int zzb() {
                return this.zzc;
            }

            public final /* synthetic */ Bundle zzc() {
                return this.zzd;
            }
        }

        public /* synthetic */ CastOptions(Builder builder, byte[] bArr) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
            this.zzd = builder.zzb();
            this.zzc = builder.zzc();
        }

        @Deprecated
        public static Builder builder(CastDevice castDevice, Listener listener) {
            return new Builder(castDevice, listener);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CastOptions)) {
                return false;
            }
            CastOptions castOptions = (CastOptions) obj;
            return t.l(this.zza, castOptions.zza) && t.c(this.zzc, castOptions.zzc) && this.zzd == castOptions.zzd && t.l(this.zze, castOptions.zze);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.zza, this.zzc, Integer.valueOf(this.zzd), this.zze});
        }
    }

    public static class Listener {
        public void onActiveInputStateChanged(int i10) {
        }

        public void onApplicationDisconnected(int i10) {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onDeviceNameChanged() {
        }

        public void onStandbyStateChanged(int i10) {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    static {
        zze zzeVar = new zze();
        zza = zzeVar;
        API = new i("Cast.API", zzeVar, com.google.android.gms.cast.internal.zzal.zza);
        CastApi = new zzm();
    }

    private Cast() {
    }

    public static zzq zza(Context context, CastOptions castOptions) {
        return new zzbm(context, castOptions);
    }
}
