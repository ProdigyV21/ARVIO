package com.google.android.gms.cast;

import android.content.Context;
import android.view.Display;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.x2;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class CastRemoteDisplay {

    @Deprecated
    public static final i API;
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;

    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi;
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final a zza;

    @Deprecated
    public static final class CastRemoteDisplayOptions implements d.c {
        final CastDevice zza;
        final CastRemoteDisplaySessionCallbacks zzb;
        final int zzc;

        @Deprecated
        public static final class Builder {
            final CastDevice zza;
            final CastRemoteDisplaySessionCallbacks zzb;
            int zzc;

            public Builder(CastDevice castDevice, CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks) {
                t.j(castDevice, "CastDevice parameter cannot be null");
                this.zza = castDevice;
                this.zzb = castRemoteDisplaySessionCallbacks;
                this.zzc = 2;
            }

            public CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, null);
            }

            public Builder setConfigPreset(int i10) {
                this.zzc = i10;
                return this;
            }
        }

        public /* synthetic */ CastRemoteDisplayOptions(Builder builder, byte[] bArr) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
            this.zzc = builder.zzc;
        }
    }

    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(Status status);
    }

    @Deprecated
    public interface CastRemoteDisplaySessionResult extends x {
        Display getPresentationDisplay();

        @Override // com.google.android.gms.common.api.x
        /* synthetic */ Status getStatus();
    }

    public @interface Configuration {
    }

    static {
        zzu zzuVar = new zzu();
        zza = zzuVar;
        i iVar = new i("CastRemoteDisplay.API", zzuVar, com.google.android.gms.cast.internal.zzal.zzc);
        API = iVar;
        CastRemoteDisplayApi = new x2(iVar);
    }

    private CastRemoteDisplay() {
    }

    public static CastRemoteDisplayClient getClient(Context context) {
        return new CastRemoteDisplayClient(context);
    }

    @Deprecated
    public static final boolean isRemoteDisplaySdkSupported(Context context) {
        return false;
    }
}
