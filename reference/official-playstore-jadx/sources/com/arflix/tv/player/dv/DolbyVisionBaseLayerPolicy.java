package com.arflix.tv.player.dv;

import a0.c;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.view.Display;
import g7.a;
import g7.b;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.text.u;
import v.f;
import x6.c0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003\u001f !B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jn\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0007J\b\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy;", "", "<init>", "()V", "resolveFromCapabilities", "Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$Result;", "hdrCapsKnown", "", "displayDv", "displayHdr10", "displayHdr10Plus", "displayHlg", "codecSupportsDvheDtb", "codecSupportsDvheStn", "codecSupportsDvheSt", "isAmazonFireTv", "isSamsung", "isXiaomi", "bridgeReady", "apiLevel", "", "resolve", "context", "Landroid/content/Context;", "queryDvDecoderProfileSupport", "Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport;", "DOLBY_VISION_MIME", "", "DvheDtbProfile", "DvheStnProfile", "DvheStProfile", "Decision", "Result", "DvDecoderProfileSupport", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DolbyVisionBaseLayerPolicy {
    public static final int $stable = 0;
    private static final String DOLBY_VISION_MIME = "video/dolby-vision";
    private static final int DvheDtbProfile = 128;
    private static final int DvheStProfile = 256;
    private static final int DvheStnProfile = 32;
    public static final DolbyVisionBaseLayerPolicy INSTANCE = new DolbyVisionBaseLayerPolicy();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$Decision;", "", "<init>", "(Ljava/lang/String;I)V", "NATIVE_DV7", "CONVERT_TO_DV81", "STRIP_TO_HDR10", "STRIP_BEST_EFFORT", "STRIP_AND_TONEMAP", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Decision {
        private static final /* synthetic */ a $ENTRIES;
        private static final /* synthetic */ Decision[] $VALUES;
        public static final Decision NATIVE_DV7 = new Decision("NATIVE_DV7", 0);
        public static final Decision CONVERT_TO_DV81 = new Decision("CONVERT_TO_DV81", 1);
        public static final Decision STRIP_TO_HDR10 = new Decision("STRIP_TO_HDR10", 2);
        public static final Decision STRIP_BEST_EFFORT = new Decision("STRIP_BEST_EFFORT", 3);
        public static final Decision STRIP_AND_TONEMAP = new Decision("STRIP_AND_TONEMAP", 4);

        private static final /* synthetic */ Decision[] $values() {
            return new Decision[]{NATIVE_DV7, CONVERT_TO_DV81, STRIP_TO_HDR10, STRIP_BEST_EFFORT, STRIP_AND_TONEMAP};
        }

        static {
            Decision[] decisionArr$values = $values();
            $VALUES = decisionArr$values;
            $ENTRIES = new b(decisionArr$values);
        }

        private Decision(String str, int i10) {
        }

        public static a<Decision> getEntries() {
            return $ENTRIES;
        }

        public static Decision valueOf(String str) {
            return (Decision) Enum.valueOf(Decision.class, str);
        }

        public static Decision[] values() {
            return (Decision[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport;", "", "dvheDtb", "", "dvheStn", "dvheSt", "<init>", "(ZZZ)V", "getDvheDtb", "()Z", "getDvheStn", "getDvheSt", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DvDecoderProfileSupport {
        private final boolean dvheDtb;
        private final boolean dvheSt;
        private final boolean dvheStn;

        public DvDecoderProfileSupport(boolean z, boolean z5, boolean z10) {
            this.dvheDtb = z;
            this.dvheStn = z5;
            this.dvheSt = z10;
        }

        public static /* synthetic */ DvDecoderProfileSupport copy$default(DvDecoderProfileSupport dvDecoderProfileSupport, boolean z, boolean z5, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z = dvDecoderProfileSupport.dvheDtb;
            }
            if ((i10 & 2) != 0) {
                z5 = dvDecoderProfileSupport.dvheStn;
            }
            if ((i10 & 4) != 0) {
                z10 = dvDecoderProfileSupport.dvheSt;
            }
            return dvDecoderProfileSupport.copy(z, z5, z10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getDvheDtb() {
            return this.dvheDtb;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getDvheStn() {
            return this.dvheStn;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getDvheSt() {
            return this.dvheSt;
        }

        public final DvDecoderProfileSupport copy(boolean dvheDtb, boolean dvheStn, boolean dvheSt) {
            return new DvDecoderProfileSupport(dvheDtb, dvheStn, dvheSt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DvDecoderProfileSupport)) {
                return false;
            }
            DvDecoderProfileSupport dvDecoderProfileSupport = (DvDecoderProfileSupport) other;
            return this.dvheDtb == dvDecoderProfileSupport.dvheDtb && this.dvheStn == dvDecoderProfileSupport.dvheStn && this.dvheSt == dvDecoderProfileSupport.dvheSt;
        }

        public final boolean getDvheDtb() {
            return this.dvheDtb;
        }

        public final boolean getDvheSt() {
            return this.dvheSt;
        }

        public final boolean getDvheStn() {
            return this.dvheStn;
        }

        public int hashCode() {
            return ((((this.dvheDtb ? 1231 : 1237) * 31) + (this.dvheStn ? 1231 : 1237)) * 31) + (this.dvheSt ? 1231 : 1237);
        }

        public String toString() {
            boolean z = this.dvheDtb;
            boolean z5 = this.dvheStn;
            boolean z10 = this.dvheSt;
            StringBuilder sb2 = new StringBuilder("DvDecoderProfileSupport(dvheDtb=");
            sb2.append(z);
            sb2.append(", dvheStn=");
            sb2.append(z5);
            sb2.append(", dvheSt=");
            return c.m(")", z10, sb2);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b'\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0012HÆ\u0003J\u0095\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0014\u00106\u001a\u00020\u00052\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00108\u001a\u00020\u0012HÖ\u0081\u0004J\n\u00109\u001a\u00020:HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b$\u0010\u0018R\u0011\u0010%\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b&\u0010\u0018¨\u0006;"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$Result;", "", "decision", "Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$Decision;", "hdrCapsKnown", "", "displayDv", "displayHdr10", "displayHdr10Plus", "displayHlg", "codecSupportsDvheDtb", "codecSupportsDvheStn", "codecSupportsDvheSt", "isAmazonFireTv", "isSamsung", "isXiaomi", "bridgeReady", "apiLevel", "", "<init>", "(Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$Decision;ZZZZZZZZZZZZI)V", "getDecision", "()Lcom/arflix/tv/player/dv/DolbyVisionBaseLayerPolicy$Decision;", "getHdrCapsKnown", "()Z", "getDisplayDv", "getDisplayHdr10", "getDisplayHdr10Plus", "getDisplayHlg", "getCodecSupportsDvheDtb", "getCodecSupportsDvheStn", "getCodecSupportsDvheSt", "getBridgeReady", "getApiLevel", "()I", "divertsFromNativeDv7", "getDivertsFromNativeDv7", "mapToHevc", "getMapToHevc", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Result {
        public static final int $stable = 0;
        private final int apiLevel;
        private final boolean bridgeReady;
        private final boolean codecSupportsDvheDtb;
        private final boolean codecSupportsDvheSt;
        private final boolean codecSupportsDvheStn;
        private final Decision decision;
        private final boolean displayDv;
        private final boolean displayHdr10;
        private final boolean displayHdr10Plus;
        private final boolean displayHlg;
        private final boolean hdrCapsKnown;
        private final boolean isAmazonFireTv;
        private final boolean isSamsung;
        private final boolean isXiaomi;

        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Decision.values().length];
                try {
                    iArr[Decision.STRIP_TO_HDR10.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Decision.STRIP_BEST_EFFORT.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Decision.STRIP_AND_TONEMAP.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Result(Decision decision, boolean z, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, int i10) {
            this.decision = decision;
            this.hdrCapsKnown = z;
            this.displayDv = z5;
            this.displayHdr10 = z10;
            this.displayHdr10Plus = z11;
            this.displayHlg = z12;
            this.codecSupportsDvheDtb = z13;
            this.codecSupportsDvheStn = z14;
            this.codecSupportsDvheSt = z15;
            this.isAmazonFireTv = z16;
            this.isSamsung = z17;
            this.isXiaomi = z18;
            this.bridgeReady = z19;
            this.apiLevel = i10;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Decision getDecision() {
            return this.decision;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsAmazonFireTv() {
            return this.isAmazonFireTv;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final boolean getIsSamsung() {
            return this.isSamsung;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final boolean getIsXiaomi() {
            return this.isXiaomi;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final boolean getBridgeReady() {
            return this.bridgeReady;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final int getApiLevel() {
            return this.apiLevel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getHdrCapsKnown() {
            return this.hdrCapsKnown;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getDisplayDv() {
            return this.displayDv;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getDisplayHdr10() {
            return this.displayHdr10;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getDisplayHdr10Plus() {
            return this.displayHdr10Plus;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getDisplayHlg() {
            return this.displayHlg;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getCodecSupportsDvheDtb() {
            return this.codecSupportsDvheDtb;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getCodecSupportsDvheStn() {
            return this.codecSupportsDvheStn;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getCodecSupportsDvheSt() {
            return this.codecSupportsDvheSt;
        }

        public final Result copy(Decision decision, boolean hdrCapsKnown, boolean displayDv, boolean displayHdr10, boolean displayHdr10Plus, boolean displayHlg, boolean codecSupportsDvheDtb, boolean codecSupportsDvheStn, boolean codecSupportsDvheSt, boolean isAmazonFireTv, boolean isSamsung, boolean isXiaomi, boolean bridgeReady, int apiLevel) {
            return new Result(decision, hdrCapsKnown, displayDv, displayHdr10, displayHdr10Plus, displayHlg, codecSupportsDvheDtb, codecSupportsDvheStn, codecSupportsDvheSt, isAmazonFireTv, isSamsung, isXiaomi, bridgeReady, apiLevel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.decision == result.decision && this.hdrCapsKnown == result.hdrCapsKnown && this.displayDv == result.displayDv && this.displayHdr10 == result.displayHdr10 && this.displayHdr10Plus == result.displayHdr10Plus && this.displayHlg == result.displayHlg && this.codecSupportsDvheDtb == result.codecSupportsDvheDtb && this.codecSupportsDvheStn == result.codecSupportsDvheStn && this.codecSupportsDvheSt == result.codecSupportsDvheSt && this.isAmazonFireTv == result.isAmazonFireTv && this.isSamsung == result.isSamsung && this.isXiaomi == result.isXiaomi && this.bridgeReady == result.bridgeReady && this.apiLevel == result.apiLevel;
        }

        public final int getApiLevel() {
            return this.apiLevel;
        }

        public final boolean getBridgeReady() {
            return this.bridgeReady;
        }

        public final boolean getCodecSupportsDvheDtb() {
            return this.codecSupportsDvheDtb;
        }

        public final boolean getCodecSupportsDvheSt() {
            return this.codecSupportsDvheSt;
        }

        public final boolean getCodecSupportsDvheStn() {
            return this.codecSupportsDvheStn;
        }

        public final Decision getDecision() {
            return this.decision;
        }

        public final boolean getDisplayDv() {
            return this.displayDv;
        }

        public final boolean getDisplayHdr10() {
            return this.displayHdr10;
        }

        public final boolean getDisplayHdr10Plus() {
            return this.displayHdr10Plus;
        }

        public final boolean getDisplayHlg() {
            return this.displayHlg;
        }

        public final boolean getDivertsFromNativeDv7() {
            return this.decision != Decision.NATIVE_DV7;
        }

        public final boolean getHdrCapsKnown() {
            return this.hdrCapsKnown;
        }

        public final boolean getMapToHevc() {
            int i10 = WhenMappings.$EnumSwitchMapping$0[this.decision.ordinal()];
            return i10 == 1 || i10 == 2 || i10 == 3;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((this.decision.hashCode() * 31) + (this.hdrCapsKnown ? 1231 : 1237)) * 31) + (this.displayDv ? 1231 : 1237)) * 31) + (this.displayHdr10 ? 1231 : 1237)) * 31) + (this.displayHdr10Plus ? 1231 : 1237)) * 31) + (this.displayHlg ? 1231 : 1237)) * 31) + (this.codecSupportsDvheDtb ? 1231 : 1237)) * 31) + (this.codecSupportsDvheStn ? 1231 : 1237)) * 31) + (this.codecSupportsDvheSt ? 1231 : 1237)) * 31) + (this.isAmazonFireTv ? 1231 : 1237)) * 31) + (this.isSamsung ? 1231 : 1237)) * 31) + (this.isXiaomi ? 1231 : 1237)) * 31) + (this.bridgeReady ? 1231 : 1237)) * 31) + this.apiLevel;
        }

        public final boolean isAmazonFireTv() {
            return this.isAmazonFireTv;
        }

        public final boolean isSamsung() {
            return this.isSamsung;
        }

        public final boolean isXiaomi() {
            return this.isXiaomi;
        }

        public String toString() {
            Decision decision = this.decision;
            boolean z = this.hdrCapsKnown;
            boolean z5 = this.displayDv;
            boolean z10 = this.displayHdr10;
            boolean z11 = this.displayHdr10Plus;
            boolean z12 = this.displayHlg;
            boolean z13 = this.codecSupportsDvheDtb;
            boolean z14 = this.codecSupportsDvheStn;
            boolean z15 = this.codecSupportsDvheSt;
            boolean z16 = this.isAmazonFireTv;
            boolean z17 = this.isSamsung;
            boolean z18 = this.isXiaomi;
            boolean z19 = this.bridgeReady;
            int i10 = this.apiLevel;
            StringBuilder sb2 = new StringBuilder("Result(decision=");
            sb2.append(decision);
            sb2.append(", hdrCapsKnown=");
            sb2.append(z);
            sb2.append(", displayDv=");
            f.h(sb2, z5, ", displayHdr10=", z10, ", displayHdr10Plus=");
            f.h(sb2, z11, ", displayHlg=", z12, ", codecSupportsDvheDtb=");
            f.h(sb2, z13, ", codecSupportsDvheStn=", z14, ", codecSupportsDvheSt=");
            f.h(sb2, z15, ", isAmazonFireTv=", z16, ", isSamsung=");
            f.h(sb2, z17, ", isXiaomi=", z18, ", bridgeReady=");
            sb2.append(z19);
            sb2.append(", apiLevel=");
            sb2.append(i10);
            sb2.append(")");
            return sb2.toString();
        }
    }

    private DolbyVisionBaseLayerPolicy() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0039, code lost:
    
        r8 = r8.getCapabilitiesForType("video/dolby-vision");
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy.DvDecoderProfileSupport queryDvDecoderProfileSupport() {
        /*
            r14 = this;
            java.lang.String r0 = "video/dolby-vision"
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            r3 = 0
            if (r1 >= r2) goto L10
            com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport r0 = new com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport
            r0.<init>(r3, r3, r3)
            return r0
        L10:
            android.media.MediaCodecList r1 = new android.media.MediaCodecList     // Catch: java.lang.Throwable -> L72
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L72
            android.media.MediaCodecInfo[] r1 = r1.getCodecInfos()     // Catch: java.lang.Throwable -> L72
            int r2 = r1.length     // Catch: java.lang.Throwable -> L72
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
        L1e:
            if (r4 >= r2) goto L7a
            r8 = r1[r4]     // Catch: java.lang.Throwable -> L72
            boolean r9 = r8.isEncoder()     // Catch: java.lang.Throwable -> L72
            if (r9 != 0) goto L77
            java.lang.String[] r9 = r8.getSupportedTypes()     // Catch: java.lang.Throwable -> L72
            int r10 = r9.length     // Catch: java.lang.Throwable -> L72
            r11 = r3
        L2e:
            if (r11 >= r10) goto L77
            r12 = r9[r11]     // Catch: java.lang.Throwable -> L72
            r13 = 1
            boolean r12 = kotlin.text.u.L(r12, r0, r13)     // Catch: java.lang.Throwable -> L72
            if (r12 == 0) goto L74
            android.media.MediaCodecInfo$CodecCapabilities r8 = r8.getCapabilitiesForType(r0)     // Catch: java.lang.Throwable -> L3e
            goto L45
        L3e:
            r8 = move-exception
            x6.c0 r9 = new x6.c0     // Catch: java.lang.Throwable -> L72
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L72
            r8 = r9
        L45:
            boolean r9 = r8 instanceof x6.c0     // Catch: java.lang.Throwable -> L72
            if (r9 == 0) goto L4b
            r8 = 0
        L4b:
            android.media.MediaCodecInfo$CodecCapabilities r8 = (android.media.MediaCodecInfo.CodecCapabilities) r8     // Catch: java.lang.Throwable -> L72
            if (r8 != 0) goto L50
            goto L77
        L50:
            android.media.MediaCodecInfo$CodecProfileLevel[] r8 = r8.profileLevels     // Catch: java.lang.Throwable -> L72
            if (r8 != 0) goto L55
            goto L77
        L55:
            int r9 = r8.length     // Catch: java.lang.Throwable -> L72
            r10 = r3
        L57:
            if (r10 >= r9) goto L77
            r11 = r8[r10]     // Catch: java.lang.Throwable -> L72
            int r11 = r11.profile     // Catch: java.lang.Throwable -> L72
            r12 = 32
            if (r11 == r12) goto L6e
            r12 = 128(0x80, float:1.8E-43)
            if (r11 == r12) goto L6c
            r12 = 256(0x100, float:3.59E-43)
            if (r11 == r12) goto L6a
            goto L6f
        L6a:
            r7 = r13
            goto L6f
        L6c:
            r5 = r13
            goto L6f
        L6e:
            r6 = r13
        L6f:
            int r10 = r10 + 1
            goto L57
        L72:
            r0 = move-exception
            goto L80
        L74:
            int r11 = r11 + 1
            goto L2e
        L77:
            int r4 = r4 + 1
            goto L1e
        L7a:
            com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport r0 = new com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport     // Catch: java.lang.Throwable -> L72
            r0.<init>(r5, r6, r7)     // Catch: java.lang.Throwable -> L72
            goto L86
        L80:
            x6.c0 r1 = new x6.c0
            r1.<init>(r0)
            r0 = r1
        L86:
            com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport r1 = new com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport
            r1.<init>(r3, r3, r3)
            boolean r2 = r0 instanceof x6.c0
            if (r2 == 0) goto L90
            r0 = r1
        L90:
            com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport r0 = (com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy.DvDecoderProfileSupport) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy.queryDvDecoderProfileSupport():com.arflix.tv.player.dv.DolbyVisionBaseLayerPolicy$DvDecoderProfileSupport");
    }

    public final Result resolve(Context context, boolean bridgeReady) {
        Object c0Var;
        boolean z;
        boolean z5;
        boolean z10;
        Display.HdrCapabilities hdrCapabilities;
        int i10 = Build.VERSION.SDK_INT;
        String str = Build.MANUFACTURER;
        boolean zL = u.L(str, "Amazon", true);
        boolean zL2 = u.L(str, "Samsung", true);
        boolean zL3 = u.L(str, "Xiaomi", true);
        if (i10 < 24) {
            return resolveFromCapabilities(false, false, false, false, false, false, false, false, zL, zL2, zL3, bridgeReady, i10);
        }
        boolean z11 = false;
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
            Display display = displayManager != null ? displayManager.getDisplay(0) : null;
            c0Var = (display == null || (hdrCapabilities = display.getHdrCapabilities()) == null) ? null : hdrCapabilities.getSupportedHdrTypes();
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        int[] iArr = (int[]) (c0Var instanceof c0 ? null : c0Var);
        boolean z12 = iArr != null;
        if (iArr == null || !r.I(iArr, 1)) {
            z = false;
        } else {
            z = false;
            z11 = true;
        }
        if (iArr == null || !r.I(iArr, 2)) {
            z5 = z;
        } else {
            z5 = z;
            z = true;
        }
        if (iArr == null || !r.I(iArr, 4)) {
            z10 = z5;
        } else {
            z10 = z5;
            z5 = true;
        }
        if (iArr != null && r.I(iArr, 3)) {
            z10 = true;
        }
        DvDecoderProfileSupport dvDecoderProfileSupportQueryDvDecoderProfileSupport = queryDvDecoderProfileSupport();
        return resolveFromCapabilities(z12, z11, z, z5, z10, dvDecoderProfileSupportQueryDvDecoderProfileSupport.getDvheDtb(), dvDecoderProfileSupportQueryDvDecoderProfileSupport.getDvheStn(), dvDecoderProfileSupportQueryDvDecoderProfileSupport.getDvheSt(), zL, zL2, zL3, bridgeReady, i10);
    }

    public final Result resolveFromCapabilities(boolean hdrCapsKnown, boolean displayDv, boolean displayHdr10, boolean displayHdr10Plus, boolean displayHlg, boolean codecSupportsDvheDtb, boolean codecSupportsDvheStn, boolean codecSupportsDvheSt, boolean isAmazonFireTv, boolean isSamsung, boolean isXiaomi, boolean bridgeReady, int apiLevel) {
        boolean z = displayHdr10 || displayHdr10Plus;
        return new Result(!hdrCapsKnown ? Decision.STRIP_BEST_EFFORT : (displayDv && codecSupportsDvheDtb) ? Decision.NATIVE_DV7 : (displayDv && bridgeReady && codecSupportsDvheSt) ? Decision.CONVERT_TO_DV81 : (displayDv && isXiaomi && bridgeReady) ? Decision.CONVERT_TO_DV81 : displayDv ? Decision.NATIVE_DV7 : (z && bridgeReady && codecSupportsDvheSt && (isSamsung || isAmazonFireTv)) ? Decision.CONVERT_TO_DV81 : (z && isXiaomi && bridgeReady) ? Decision.CONVERT_TO_DV81 : z ? Decision.STRIP_TO_HDR10 : Decision.STRIP_AND_TONEMAP, hdrCapsKnown, displayDv, displayHdr10, displayHdr10Plus, displayHlg, codecSupportsDvheDtb, codecSupportsDvheStn, codecSupportsDvheSt, isAmazonFireTv, isSamsung, isXiaomi, bridgeReady, apiLevel);
    }
}
