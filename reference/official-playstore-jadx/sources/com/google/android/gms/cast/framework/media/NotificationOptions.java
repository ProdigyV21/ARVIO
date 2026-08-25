package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.foundation.c;
import com.google.android.gms.cast.framework.media.internal.ResourceProvider;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.h4;
import com.google.android.gms.internal.cast.j4;
import com.google.android.gms.internal.cast.u0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationOptions extends a {
    public static final Parcelable.Creator<NotificationOptions> CREATOR;
    public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000;
    public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000;
    private static final j4 zza;
    private static final int[] zzb;
    private final int zzA;
    private final int zzB;
    private final int zzC;
    private final int zzD;
    private final int zzE;
    private final int zzF;
    private final int zzG;
    private final zzg zzH;
    private final boolean zzI;
    private final boolean zzJ;
    private final List zzc;
    private final int[] zzd;
    private final long zze;
    private final String zzf;
    private final int zzg;
    private final int zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final int zzm;
    private final int zzn;
    private final int zzo;
    private final int zzp;
    private final int zzq;
    private final int zzr;
    private final int zzs;
    private final int zzt;
    private final int zzu;
    private final int zzv;
    private final int zzw;
    private final int zzx;
    private final int zzy;
    private final int zzz;

    public static final class Builder {
        private String zza;
        private NotificationActionsProvider zzc;
        private boolean zzs;
        private boolean zzt;
        private List zzb = NotificationOptions.zza;
        private int[] zzd = NotificationOptions.zzb;
        private int zze = zza("smallIconDrawableResId");
        private int zzf = zza("stopLiveStreamDrawableResId");
        private int zzg = zza("pauseDrawableResId");
        private int zzh = zza("playDrawableResId");
        private int zzi = zza("skipNextDrawableResId");
        private int zzj = zza("skipPrevDrawableResId");
        private int zzk = zza("forwardDrawableResId");
        private int zzl = zza("forward10DrawableResId");
        private int zzm = zza("forward30DrawableResId");
        private int zzn = zza("rewindDrawableResId");
        private int zzo = zza("rewind10DrawableResId");
        private int zzp = zza("rewind30DrawableResId");
        private int zzq = zza("disconnectDrawableResId");
        private long zzr = 10000;

        private static int zza(String str) {
            try {
                int i10 = ResourceProvider.f12663a;
                Integer num = (Integer) ResourceProvider.class.getMethod("findResourceByName", String.class).invoke(null, str);
                if (num != null) {
                    return num.intValue();
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
            return 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r35v0, types: [android.os.IBinder] */
        public NotificationOptions build() {
            NotificationActionsProvider notificationActionsProvider = this.zzc;
            return new NotificationOptions(this.zzb, this.zzd, this.zzr, this.zza, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, zza("notificationImageSizeDimenResId"), zza("castingToDeviceStringResId"), zza("stopLiveStreamStringResId"), zza("pauseStringResId"), zza("playStringResId"), zza("skipNextStringResId"), zza("skipPrevStringResId"), zza("forwardStringResId"), zza("forward10StringResId"), zza("forward30StringResId"), zza("rewindStringResId"), zza("rewind10StringResId"), zza("rewind30StringResId"), zza("disconnectStringResId"), notificationActionsProvider == null ? null : notificationActionsProvider.zza(), this.zzs, this.zzt);
        }

        public Builder setActions(List<String> list, int[] iArr) {
            if (list == null && iArr != null) {
                throw new IllegalArgumentException("When setting actions to null, you must also set compatActionIndices to null.");
            }
            if (list != null && iArr == null) {
                throw new IllegalArgumentException("When setting compatActionIndices to null, you must also set actions to null.");
            }
            if (list == null || iArr == null) {
                this.zzb = NotificationOptions.zza;
                this.zzd = NotificationOptions.zzb;
                return this;
            }
            int length = iArr.length;
            int size = list.size();
            if (length > size) {
                Locale locale = Locale.ROOT;
                throw new IllegalArgumentException(c.s("Invalid number of compat actions: ", length, " > ", size, "."));
            }
            for (int i10 : iArr) {
                if (i10 < 0 || i10 >= size) {
                    Locale locale2 = Locale.ROOT;
                    throw new IllegalArgumentException(c.s("Index ", i10, " in compatActionIndices out of range: [0, ", size - 1, "]"));
                }
            }
            this.zzb = new ArrayList(list);
            this.zzd = Arrays.copyOf(iArr, iArr.length);
            return this;
        }

        public Builder setDisconnectDrawableResId(int i10) {
            this.zzq = i10;
            return this;
        }

        public Builder setForward10DrawableResId(int i10) {
            this.zzl = i10;
            return this;
        }

        public Builder setForward30DrawableResId(int i10) {
            this.zzm = i10;
            return this;
        }

        public Builder setForwardDrawableResId(int i10) {
            this.zzk = i10;
            return this;
        }

        public Builder setNotificationActionsProvider(NotificationActionsProvider notificationActionsProvider) {
            if (notificationActionsProvider == null) {
                throw new IllegalArgumentException("notificationActionsProvider cannot be null.");
            }
            this.zzc = notificationActionsProvider;
            return this;
        }

        public Builder setPauseDrawableResId(int i10) {
            this.zzg = i10;
            return this;
        }

        public Builder setPlayDrawableResId(int i10) {
            this.zzh = i10;
            return this;
        }

        public Builder setRewind10DrawableResId(int i10) {
            this.zzo = i10;
            return this;
        }

        public Builder setRewind30DrawableResId(int i10) {
            this.zzp = i10;
            return this;
        }

        public Builder setRewindDrawableResId(int i10) {
            this.zzn = i10;
            return this;
        }

        public Builder setSkipNextDrawableResId(int i10) {
            this.zzi = i10;
            return this;
        }

        public Builder setSkipPrevDrawableResId(int i10) {
            this.zzj = i10;
            return this;
        }

        public Builder setSkipStepMs(long j10) {
            t.a("skipStepMs must be positive.", j10 > 0);
            this.zzr = j10;
            return this;
        }

        public Builder setSkipToNextSlotReserved(boolean z) {
            this.zzt = z;
            return this;
        }

        public Builder setSkipToPrevSlotReserved(boolean z) {
            this.zzs = z;
            return this;
        }

        public Builder setSmallIconDrawableResId(int i10) {
            this.zze = i10;
            return this;
        }

        public Builder setStopLiveStreamDrawableResId(int i10) {
            this.zzf = i10;
            return this;
        }

        public Builder setTargetActivityClassName(String str) {
            this.zza = str;
            return this;
        }
    }

    static {
        h4 h4Var = j4.f13340l;
        Object[] objArr = {MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_STOP_CASTING};
        for (int i10 = 0; i10 < 2; i10++) {
            u0.s(i10, objArr[i10]);
        }
        zza = j4.h(2, objArr);
        zzb = new int[]{0, 1};
        CREATOR = new zzs();
    }

    public NotificationOptions(List list, int[] iArr, long j10, String str, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36, IBinder iBinder, boolean z, boolean z5) {
        zzg zzeVar;
        this.zzc = new ArrayList(list);
        this.zzd = Arrays.copyOf(iArr, iArr.length);
        this.zze = j10;
        this.zzf = str;
        this.zzg = i10;
        this.zzh = i11;
        this.zzi = i12;
        this.zzj = i13;
        this.zzk = i14;
        this.zzl = i15;
        this.zzm = i16;
        this.zzn = i17;
        this.zzo = i18;
        this.zzp = i19;
        this.zzq = i20;
        this.zzr = i21;
        this.zzs = i22;
        this.zzt = i23;
        this.zzu = i24;
        this.zzv = i25;
        this.zzw = i26;
        this.zzx = i27;
        this.zzy = i28;
        this.zzz = i29;
        this.zzA = i30;
        this.zzB = i31;
        this.zzC = i32;
        this.zzD = i33;
        this.zzE = i34;
        this.zzF = i35;
        this.zzG = i36;
        this.zzI = z;
        this.zzJ = z5;
        if (iBinder == null) {
            zzeVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
            zzeVar = iInterfaceQueryLocalInterface instanceof zzg ? (zzg) iInterfaceQueryLocalInterface : new zze(iBinder);
        }
        this.zzH = zzeVar;
    }

    public List<String> getActions() {
        return this.zzc;
    }

    public int getCastingToDeviceStringResId() {
        return this.zzu;
    }

    public int[] getCompatActionIndices() {
        int[] iArr = this.zzd;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public int getDisconnectDrawableResId() {
        return this.zzs;
    }

    public int getForward10DrawableResId() {
        return this.zzn;
    }

    public int getForward30DrawableResId() {
        return this.zzo;
    }

    public int getForwardDrawableResId() {
        return this.zzm;
    }

    public int getPauseDrawableResId() {
        return this.zzi;
    }

    public int getPlayDrawableResId() {
        return this.zzj;
    }

    public int getRewind10DrawableResId() {
        return this.zzq;
    }

    public int getRewind30DrawableResId() {
        return this.zzr;
    }

    public int getRewindDrawableResId() {
        return this.zzp;
    }

    public int getSkipNextDrawableResId() {
        return this.zzk;
    }

    public int getSkipPrevDrawableResId() {
        return this.zzl;
    }

    public long getSkipStepMs() {
        return this.zze;
    }

    public int getSmallIconDrawableResId() {
        return this.zzg;
    }

    public int getStopLiveStreamDrawableResId() {
        return this.zzh;
    }

    public int getStopLiveStreamTitleResId() {
        return this.zzv;
    }

    public String getTargetActivityClassName() {
        return this.zzf;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.i0(parcel, getActions(), 2);
        a.a.c0(parcel, 3, getCompatActionIndices(), false);
        long skipStepMs = getSkipStepMs();
        a.a.l0(parcel, 4, 8);
        parcel.writeLong(skipStepMs);
        a.a.g0(parcel, 5, getTargetActivityClassName(), false);
        int smallIconDrawableResId = getSmallIconDrawableResId();
        a.a.l0(parcel, 6, 4);
        parcel.writeInt(smallIconDrawableResId);
        int stopLiveStreamDrawableResId = getStopLiveStreamDrawableResId();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(stopLiveStreamDrawableResId);
        int pauseDrawableResId = getPauseDrawableResId();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(pauseDrawableResId);
        int playDrawableResId = getPlayDrawableResId();
        a.a.l0(parcel, 9, 4);
        parcel.writeInt(playDrawableResId);
        int skipNextDrawableResId = getSkipNextDrawableResId();
        a.a.l0(parcel, 10, 4);
        parcel.writeInt(skipNextDrawableResId);
        int skipPrevDrawableResId = getSkipPrevDrawableResId();
        a.a.l0(parcel, 11, 4);
        parcel.writeInt(skipPrevDrawableResId);
        int forwardDrawableResId = getForwardDrawableResId();
        a.a.l0(parcel, 12, 4);
        parcel.writeInt(forwardDrawableResId);
        int forward10DrawableResId = getForward10DrawableResId();
        a.a.l0(parcel, 13, 4);
        parcel.writeInt(forward10DrawableResId);
        int forward30DrawableResId = getForward30DrawableResId();
        a.a.l0(parcel, 14, 4);
        parcel.writeInt(forward30DrawableResId);
        int rewindDrawableResId = getRewindDrawableResId();
        a.a.l0(parcel, 15, 4);
        parcel.writeInt(rewindDrawableResId);
        int rewind10DrawableResId = getRewind10DrawableResId();
        a.a.l0(parcel, 16, 4);
        parcel.writeInt(rewind10DrawableResId);
        int rewind30DrawableResId = getRewind30DrawableResId();
        a.a.l0(parcel, 17, 4);
        parcel.writeInt(rewind30DrawableResId);
        int disconnectDrawableResId = getDisconnectDrawableResId();
        a.a.l0(parcel, 18, 4);
        parcel.writeInt(disconnectDrawableResId);
        int i11 = this.zzt;
        a.a.l0(parcel, 19, 4);
        parcel.writeInt(i11);
        int castingToDeviceStringResId = getCastingToDeviceStringResId();
        a.a.l0(parcel, 20, 4);
        parcel.writeInt(castingToDeviceStringResId);
        int stopLiveStreamTitleResId = getStopLiveStreamTitleResId();
        a.a.l0(parcel, 21, 4);
        parcel.writeInt(stopLiveStreamTitleResId);
        int i12 = this.zzw;
        a.a.l0(parcel, 22, 4);
        parcel.writeInt(i12);
        int i13 = this.zzx;
        a.a.l0(parcel, 23, 4);
        parcel.writeInt(i13);
        int i14 = this.zzy;
        a.a.l0(parcel, 24, 4);
        parcel.writeInt(i14);
        int i15 = this.zzz;
        a.a.l0(parcel, 25, 4);
        parcel.writeInt(i15);
        int i16 = this.zzA;
        a.a.l0(parcel, 26, 4);
        parcel.writeInt(i16);
        int i17 = this.zzB;
        a.a.l0(parcel, 27, 4);
        parcel.writeInt(i17);
        int i18 = this.zzC;
        a.a.l0(parcel, 28, 4);
        parcel.writeInt(i18);
        int i19 = this.zzD;
        a.a.l0(parcel, 29, 4);
        parcel.writeInt(i19);
        int i20 = this.zzE;
        a.a.l0(parcel, 30, 4);
        parcel.writeInt(i20);
        int i21 = this.zzF;
        a.a.l0(parcel, 31, 4);
        parcel.writeInt(i21);
        int i22 = this.zzG;
        a.a.l0(parcel, 32, 4);
        parcel.writeInt(i22);
        zzg zzgVar = this.zzH;
        a.a.b0(parcel, 33, zzgVar == null ? null : zzgVar.asBinder());
        boolean z = this.zzI;
        a.a.l0(parcel, 34, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z5 = this.zzJ;
        a.a.l0(parcel, 35, 4);
        parcel.writeInt(z5 ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final int zza() {
        return this.zzt;
    }

    public final int zzb() {
        return this.zzw;
    }

    public final int zzc() {
        return this.zzx;
    }

    public final int zzd() {
        return this.zzy;
    }

    public final int zze() {
        return this.zzz;
    }

    public final int zzf() {
        return this.zzA;
    }

    public final int zzg() {
        return this.zzB;
    }

    public final int zzh() {
        return this.zzC;
    }

    public final int zzi() {
        return this.zzD;
    }

    public final int zzj() {
        return this.zzE;
    }

    public final int zzk() {
        return this.zzF;
    }

    public final int zzl() {
        return this.zzG;
    }

    public final boolean zzm() {
        return this.zzI;
    }

    public final boolean zzn() {
        return this.zzJ;
    }

    public final zzg zzo() {
        return this.zzH;
    }
}
