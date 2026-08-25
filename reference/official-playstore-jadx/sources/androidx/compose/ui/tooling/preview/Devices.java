package androidx.compose.ui.tooling.preview;

import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b-\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010)\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b*\u0010\u0002R\u0016\u0010+\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b,\u0010\u0002R\u0016\u0010-\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b.\u0010\u0002R\u0016\u0010/\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b0\u0010\u0002¨\u00061"}, d2 = {"Landroidx/compose/ui/tooling/preview/Devices;", "", "()V", "AUTOMOTIVE_1024p", "", "DEFAULT", "DESKTOP", "FOLDABLE", "NEXUS_10", "NEXUS_5", "NEXUS_5X", "NEXUS_6", "NEXUS_6P", "NEXUS_7", "NEXUS_7_2013", "NEXUS_9", "PHONE", "PIXEL", "PIXEL_2", "PIXEL_2_XL", "PIXEL_3", "PIXEL_3A", "PIXEL_3A_XL", "PIXEL_3_XL", "PIXEL_4", "PIXEL_4A", "PIXEL_4_XL", "PIXEL_5", "PIXEL_6", "PIXEL_6A", "PIXEL_6_PRO", "PIXEL_7", "PIXEL_7A", "PIXEL_7_PRO", "PIXEL_C", "PIXEL_FOLD", "PIXEL_TABLET", "PIXEL_XL", "TABLET", "TV_1080p", "TV_720p", "WEAR_OS_LARGE_ROUND", "getWEAR_OS_LARGE_ROUND$annotations", "WEAR_OS_RECT", "getWEAR_OS_RECT$annotations", "WEAR_OS_SMALL_ROUND", "getWEAR_OS_SMALL_ROUND$annotations", "WEAR_OS_SQUARE", "getWEAR_OS_SQUARE$annotations", "ui-tooling-preview_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Devices {
    public static final int $stable = 0;
    public static final String AUTOMOTIVE_1024p = "id:automotive_1024p_landscape";
    public static final String DEFAULT = "";
    public static final String DESKTOP = "spec:id=reference_desktop,shape=Normal,width=1920,height=1080,unit=dp,dpi=160";
    public static final String FOLDABLE = "spec:id=reference_foldable,shape=Normal,width=673,height=841,unit=dp,dpi=420";
    public static final Devices INSTANCE = new Devices();
    public static final String NEXUS_10 = "name:Nexus 10";
    public static final String NEXUS_5 = "id:Nexus 5";
    public static final String NEXUS_5X = "id:Nexus 5X";
    public static final String NEXUS_6 = "id:Nexus 6";
    public static final String NEXUS_6P = "id:Nexus 6P";
    public static final String NEXUS_7 = "id:Nexus 7";
    public static final String NEXUS_7_2013 = "id:Nexus 7 2013";
    public static final String NEXUS_9 = "id:Nexus 9";
    public static final String PHONE = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420";
    public static final String PIXEL = "id:pixel";
    public static final String PIXEL_2 = "id:pixel_2";
    public static final String PIXEL_2_XL = "id:pixel_2_xl";
    public static final String PIXEL_3 = "id:pixel_3";
    public static final String PIXEL_3A = "id:pixel_3a";
    public static final String PIXEL_3A_XL = "id:pixel_3a_xl";
    public static final String PIXEL_3_XL = "id:pixel_3_xl";
    public static final String PIXEL_4 = "id:pixel_4";
    public static final String PIXEL_4A = "id:pixel_4a";
    public static final String PIXEL_4_XL = "id:pixel_4_xl";
    public static final String PIXEL_5 = "id:pixel_5";
    public static final String PIXEL_6 = "id:pixel_6";
    public static final String PIXEL_6A = "id:pixel_6a";
    public static final String PIXEL_6_PRO = "id:pixel_6_pro";
    public static final String PIXEL_7 = "id:pixel_7";
    public static final String PIXEL_7A = "id:pixel_7a";
    public static final String PIXEL_7_PRO = "id:pixel_7_pro";
    public static final String PIXEL_C = "id:pixel_c";
    public static final String PIXEL_FOLD = "id:pixel_fold";
    public static final String PIXEL_TABLET = "id:pixel_tablet";
    public static final String PIXEL_XL = "id:pixel_xl";
    public static final String TABLET = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240";
    public static final String TV_1080p = "spec:shape=Normal,width=1920,height=1080,unit=dp,dpi=420";
    public static final String TV_720p = "spec:shape=Normal,width=1280,height=720,unit=dp,dpi=420";
    public static final String WEAR_OS_LARGE_ROUND = "id:wearos_large_round";
    public static final String WEAR_OS_RECT = "id:wearos_rect";
    public static final String WEAR_OS_SMALL_ROUND = "id:wearos_small_round";
    public static final String WEAR_OS_SQUARE = "id:wearos_square";

    private Devices() {
    }

    @e
    public static /* synthetic */ void getWEAR_OS_LARGE_ROUND$annotations() {
    }

    @e
    public static /* synthetic */ void getWEAR_OS_RECT$annotations() {
    }

    @e
    public static /* synthetic */ void getWEAR_OS_SMALL_ROUND$annotations() {
    }

    @e
    public static /* synthetic */ void getWEAR_OS_SQUARE$annotations() {
    }
}
