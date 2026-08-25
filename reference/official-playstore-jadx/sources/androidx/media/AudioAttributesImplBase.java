package androidx.media;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3351a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3352b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3353c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3354d = -1;

    public final boolean equals(Object obj) {
        int i10;
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.f3352b == audioAttributesImplBase.f3352b) {
            int i11 = this.f3353c;
            int i12 = audioAttributesImplBase.f3353c;
            int i13 = audioAttributesImplBase.f3354d;
            if (i13 == -1) {
                int i14 = audioAttributesImplBase.f3351a;
                int i15 = AudioAttributesCompat.f3347b;
                if ((i12 & 1) != 1) {
                    i10 = 4;
                    if ((i12 & 4) != 4) {
                        switch (i14) {
                            case 2:
                                i10 = 0;
                                break;
                            case 3:
                                i10 = 8;
                                break;
                            case 4:
                                break;
                            case 5:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                                i10 = 5;
                                break;
                            case 6:
                                i10 = 2;
                                break;
                            case 11:
                                i10 = 10;
                                break;
                            case 12:
                            default:
                                i10 = 3;
                                break;
                            case 13:
                                i10 = 1;
                                break;
                        }
                    } else {
                        i10 = 6;
                    }
                } else {
                    i10 = 7;
                }
            } else {
                i10 = i13;
            }
            if (i10 == 6) {
                i12 |= 4;
            } else if (i10 == 7) {
                i12 |= 1;
            }
            if (i11 == (i12 & 273) && this.f3351a == audioAttributesImplBase.f3351a && this.f3354d == i13) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3352b), Integer.valueOf(this.f3353c), Integer.valueOf(this.f3351a), Integer.valueOf(this.f3354d)});
    }

    public final String toString() {
        String strI;
        StringBuilder sb2 = new StringBuilder("AudioAttributesCompat:");
        if (this.f3354d != -1) {
            sb2.append(" stream=");
            sb2.append(this.f3354d);
            sb2.append(" derived");
        }
        sb2.append(" usage=");
        int i10 = this.f3351a;
        int i11 = AudioAttributesCompat.f3347b;
        switch (i10) {
            case 0:
                strI = "USAGE_UNKNOWN";
                break;
            case 1:
                strI = "USAGE_MEDIA";
                break;
            case 2:
                strI = "USAGE_VOICE_COMMUNICATION";
                break;
            case 3:
                strI = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                break;
            case 4:
                strI = "USAGE_ALARM";
                break;
            case 5:
                strI = "USAGE_NOTIFICATION";
                break;
            case 6:
                strI = "USAGE_NOTIFICATION_RINGTONE";
                break;
            case 7:
                strI = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                break;
            case 8:
                strI = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                break;
            case 9:
                strI = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                break;
            case 10:
                strI = "USAGE_NOTIFICATION_EVENT";
                break;
            case 11:
                strI = "USAGE_ASSISTANCE_ACCESSIBILITY";
                break;
            case 12:
                strI = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                break;
            case 13:
                strI = "USAGE_ASSISTANCE_SONIFICATION";
                break;
            case 14:
                strI = "USAGE_GAME";
                break;
            case 15:
            default:
                strI = a0.c.i(i10, "unknown usage ");
                break;
            case 16:
                strI = "USAGE_ASSISTANT";
                break;
        }
        sb2.append(strI);
        sb2.append(" content=");
        sb2.append(this.f3352b);
        sb2.append(" flags=0x");
        sb2.append(Integer.toHexString(this.f3353c).toUpperCase());
        return sb2.toString();
    }
}
