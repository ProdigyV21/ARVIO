package com.arflix.tv.ui.screens.profile;

import androidx.fragment.app.a2;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.components.ToastType;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b:\b\u0087\b\u0018\u00002\u00020\u0001Bé\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0019\u001a\u00020\f\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u001b\u001a\u00020\f\u0012\b\b\u0002\u0010\u001c\u001a\u00020\f¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u00108\u001a\u00020\u0007HÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0007HÆ\u0003J\t\u0010<\u001a\u00020\fHÆ\u0003J\t\u0010=\u001a\u00020\u000eHÆ\u0003J\t\u0010>\u001a\u00020\u000eHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\fHÆ\u0003J\t\u0010@\u001a\u00020\u0007HÆ\u0003J\t\u0010A\u001a\u00020\u0007HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\fHÆ\u0003J\t\u0010D\u001a\u00020\u0016HÆ\u0003J\t\u0010E\u001a\u00020\u0007HÆ\u0003J\t\u0010F\u001a\u00020\u0007HÆ\u0003J\t\u0010G\u001a\u00020\fHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010I\u001a\u00020\fHÆ\u0003J\t\u0010J\u001a\u00020\fHÆ\u0003Jë\u0001\u0010K\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\f2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u001c\u001a\u00020\fHÆ\u0001J\u0014\u0010L\u001a\u00020\u00072\b\u0010M\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010N\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010O\u001a\u00020\fHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010#R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010#R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010#R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010#R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0017\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0011\u0010\u0018\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u0010#R\u0011\u0010\u0019\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b2\u0010&R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\"R\u0011\u0010\u001b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u0011\u0010\u001c\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b5\u0010&¨\u0006P"}, d2 = {"Lcom/arflix/tv/ui/screens/profile/ProfileUiState;", "", "profiles", "", "Lcom/arflix/tv/data/model/Profile;", "activeProfile", "isLoading", "", "isSwitchingProfile", "isManageMode", "showAddDialog", "newProfileName", "", "selectedColorIndex", "", "selectedAvatarId", "selectedAvatarImageUri", "useCustomAvatarImage", "isKidsProfile", "editingProfile", "toastMessage", "toastType", "Lcom/arflix/tv/ui/components/ToastType;", "showToast", "showPinDialog", "pinDialogMode", "pendingProfileForPin", "pinContext", "pinError", "<init>", "(Ljava/util/List;Lcom/arflix/tv/data/model/Profile;ZZZZLjava/lang/String;IILjava/lang/String;ZZLcom/arflix/tv/data/model/Profile;Ljava/lang/String;Lcom/arflix/tv/ui/components/ToastType;ZZLjava/lang/String;Lcom/arflix/tv/data/model/Profile;Ljava/lang/String;Ljava/lang/String;)V", "getProfiles", "()Ljava/util/List;", "getActiveProfile", "()Lcom/arflix/tv/data/model/Profile;", "()Z", "getShowAddDialog", "getNewProfileName", "()Ljava/lang/String;", "getSelectedColorIndex", "()I", "getSelectedAvatarId", "getSelectedAvatarImageUri", "getUseCustomAvatarImage", "getEditingProfile", "getToastMessage", "getToastType", "()Lcom/arflix/tv/ui/components/ToastType;", "getShowToast", "getShowPinDialog", "getPinDialogMode", "getPendingProfileForPin", "getPinContext", "getPinError", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ProfileUiState {
    public static final int $stable = 0;
    private final Profile activeProfile;
    private final Profile editingProfile;
    private final boolean isKidsProfile;
    private final boolean isLoading;
    private final boolean isManageMode;
    private final boolean isSwitchingProfile;
    private final String newProfileName;
    private final Profile pendingProfileForPin;
    private final String pinContext;
    private final String pinDialogMode;
    private final String pinError;
    private final List<Profile> profiles;
    private final int selectedAvatarId;
    private final String selectedAvatarImageUri;
    private final int selectedColorIndex;
    private final boolean showAddDialog;
    private final boolean showPinDialog;
    private final boolean showToast;
    private final String toastMessage;
    private final ToastType toastType;
    private final boolean useCustomAvatarImage;

    public ProfileUiState() {
        this(null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097151, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProfileUiState copy$default(ProfileUiState profileUiState, List list, Profile profile, boolean z, boolean z5, boolean z10, boolean z11, String str, int i10, int i11, String str2, boolean z12, boolean z13, Profile profile2, String str3, ToastType toastType, boolean z14, boolean z15, String str4, Profile profile3, String str5, String str6, int i12, Object obj) {
        String str7;
        String str8;
        List list2 = (i12 & 1) != 0 ? profileUiState.profiles : list;
        Profile profile4 = (i12 & 2) != 0 ? profileUiState.activeProfile : profile;
        boolean z16 = (i12 & 4) != 0 ? profileUiState.isLoading : z;
        boolean z17 = (i12 & 8) != 0 ? profileUiState.isSwitchingProfile : z5;
        boolean z18 = (i12 & 16) != 0 ? profileUiState.isManageMode : z10;
        boolean z19 = (i12 & 32) != 0 ? profileUiState.showAddDialog : z11;
        String str9 = (i12 & 64) != 0 ? profileUiState.newProfileName : str;
        int i13 = (i12 & 128) != 0 ? profileUiState.selectedColorIndex : i10;
        int i14 = (i12 & 256) != 0 ? profileUiState.selectedAvatarId : i11;
        String str10 = (i12 & 512) != 0 ? profileUiState.selectedAvatarImageUri : str2;
        boolean z20 = (i12 & 1024) != 0 ? profileUiState.useCustomAvatarImage : z12;
        boolean z21 = (i12 & 2048) != 0 ? profileUiState.isKidsProfile : z13;
        Profile profile5 = (i12 & 4096) != 0 ? profileUiState.editingProfile : profile2;
        String str11 = (i12 & 8192) != 0 ? profileUiState.toastMessage : str3;
        List list3 = list2;
        ToastType toastType2 = (i12 & 16384) != 0 ? profileUiState.toastType : toastType;
        boolean z22 = (i12 & 32768) != 0 ? profileUiState.showToast : z14;
        boolean z23 = (i12 & 65536) != 0 ? profileUiState.showPinDialog : z15;
        String str12 = (i12 & 131072) != 0 ? profileUiState.pinDialogMode : str4;
        Profile profile6 = (i12 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? profileUiState.pendingProfileForPin : profile3;
        String str13 = (i12 & 524288) != 0 ? profileUiState.pinContext : str5;
        if ((i12 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0) {
            str8 = str13;
            str7 = profileUiState.pinError;
        } else {
            str7 = str6;
            str8 = str13;
        }
        return profileUiState.copy(list3, profile4, z16, z17, z18, z19, str9, i13, i14, str10, z20, z21, profile5, str11, toastType2, z22, z23, str12, profile6, str8, str7);
    }

    public final List<Profile> component1() {
        return this.profiles;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSelectedAvatarImageUri() {
        return this.selectedAvatarImageUri;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getUseCustomAvatarImage() {
        return this.useCustomAvatarImage;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getIsKidsProfile() {
        return this.isKidsProfile;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Profile getEditingProfile() {
        return this.editingProfile;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getToastMessage() {
        return this.toastMessage;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final ToastType getToastType() {
        return this.toastType;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getShowToast() {
        return this.showToast;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getShowPinDialog() {
        return this.showPinDialog;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getPinDialogMode() {
        return this.pinDialogMode;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final Profile getPendingProfileForPin() {
        return this.pendingProfileForPin;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Profile getActiveProfile() {
        return this.activeProfile;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getPinContext() {
        return this.pinContext;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getPinError() {
        return this.pinError;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsSwitchingProfile() {
        return this.isSwitchingProfile;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsManageMode() {
        return this.isManageMode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getShowAddDialog() {
        return this.showAddDialog;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getNewProfileName() {
        return this.newProfileName;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getSelectedColorIndex() {
        return this.selectedColorIndex;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getSelectedAvatarId() {
        return this.selectedAvatarId;
    }

    public final ProfileUiState copy(List<Profile> profiles, Profile activeProfile, boolean isLoading, boolean isSwitchingProfile, boolean isManageMode, boolean showAddDialog, String newProfileName, int selectedColorIndex, int selectedAvatarId, String selectedAvatarImageUri, boolean useCustomAvatarImage, boolean isKidsProfile, Profile editingProfile, String toastMessage, ToastType toastType, boolean showToast, boolean showPinDialog, String pinDialogMode, Profile pendingProfileForPin, String pinContext, String pinError) {
        return new ProfileUiState(profiles, activeProfile, isLoading, isSwitchingProfile, isManageMode, showAddDialog, newProfileName, selectedColorIndex, selectedAvatarId, selectedAvatarImageUri, useCustomAvatarImage, isKidsProfile, editingProfile, toastMessage, toastType, showToast, showPinDialog, pinDialogMode, pendingProfileForPin, pinContext, pinError);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProfileUiState)) {
            return false;
        }
        ProfileUiState profileUiState = (ProfileUiState) other;
        return kotlin.jvm.internal.p.a(this.profiles, profileUiState.profiles) && kotlin.jvm.internal.p.a(this.activeProfile, profileUiState.activeProfile) && this.isLoading == profileUiState.isLoading && this.isSwitchingProfile == profileUiState.isSwitchingProfile && this.isManageMode == profileUiState.isManageMode && this.showAddDialog == profileUiState.showAddDialog && kotlin.jvm.internal.p.a(this.newProfileName, profileUiState.newProfileName) && this.selectedColorIndex == profileUiState.selectedColorIndex && this.selectedAvatarId == profileUiState.selectedAvatarId && kotlin.jvm.internal.p.a(this.selectedAvatarImageUri, profileUiState.selectedAvatarImageUri) && this.useCustomAvatarImage == profileUiState.useCustomAvatarImage && this.isKidsProfile == profileUiState.isKidsProfile && kotlin.jvm.internal.p.a(this.editingProfile, profileUiState.editingProfile) && kotlin.jvm.internal.p.a(this.toastMessage, profileUiState.toastMessage) && this.toastType == profileUiState.toastType && this.showToast == profileUiState.showToast && this.showPinDialog == profileUiState.showPinDialog && kotlin.jvm.internal.p.a(this.pinDialogMode, profileUiState.pinDialogMode) && kotlin.jvm.internal.p.a(this.pendingProfileForPin, profileUiState.pendingProfileForPin) && kotlin.jvm.internal.p.a(this.pinContext, profileUiState.pinContext) && kotlin.jvm.internal.p.a(this.pinError, profileUiState.pinError);
    }

    public final Profile getActiveProfile() {
        return this.activeProfile;
    }

    public final Profile getEditingProfile() {
        return this.editingProfile;
    }

    public final String getNewProfileName() {
        return this.newProfileName;
    }

    public final Profile getPendingProfileForPin() {
        return this.pendingProfileForPin;
    }

    public final String getPinContext() {
        return this.pinContext;
    }

    public final String getPinDialogMode() {
        return this.pinDialogMode;
    }

    public final String getPinError() {
        return this.pinError;
    }

    public final List<Profile> getProfiles() {
        return this.profiles;
    }

    public final int getSelectedAvatarId() {
        return this.selectedAvatarId;
    }

    public final String getSelectedAvatarImageUri() {
        return this.selectedAvatarImageUri;
    }

    public final int getSelectedColorIndex() {
        return this.selectedColorIndex;
    }

    public final boolean getShowAddDialog() {
        return this.showAddDialog;
    }

    public final boolean getShowPinDialog() {
        return this.showPinDialog;
    }

    public final boolean getShowToast() {
        return this.showToast;
    }

    public final String getToastMessage() {
        return this.toastMessage;
    }

    public final ToastType getToastType() {
        return this.toastType;
    }

    public final boolean getUseCustomAvatarImage() {
        return this.useCustomAvatarImage;
    }

    public int hashCode() {
        int iHashCode = this.profiles.hashCode() * 31;
        Profile profile = this.activeProfile;
        int iC = (((androidx.compose.foundation.c.c((((((((((iHashCode + (profile == null ? 0 : profile.hashCode())) * 31) + (this.isLoading ? 1231 : 1237)) * 31) + (this.isSwitchingProfile ? 1231 : 1237)) * 31) + (this.isManageMode ? 1231 : 1237)) * 31) + (this.showAddDialog ? 1231 : 1237)) * 31, 31, this.newProfileName) + this.selectedColorIndex) * 31) + this.selectedAvatarId) * 31;
        String str = this.selectedAvatarImageUri;
        int iHashCode2 = (((((iC + (str == null ? 0 : str.hashCode())) * 31) + (this.useCustomAvatarImage ? 1231 : 1237)) * 31) + (this.isKidsProfile ? 1231 : 1237)) * 31;
        Profile profile2 = this.editingProfile;
        int iHashCode3 = (iHashCode2 + (profile2 == null ? 0 : profile2.hashCode())) * 31;
        String str2 = this.toastMessage;
        int iC2 = androidx.compose.foundation.c.c((((((this.toastType.hashCode() + ((iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31) + (this.showToast ? 1231 : 1237)) * 31) + (this.showPinDialog ? 1231 : 1237)) * 31, 31, this.pinDialogMode);
        Profile profile3 = this.pendingProfileForPin;
        return this.pinError.hashCode() + androidx.compose.foundation.c.c((iC2 + (profile3 != null ? profile3.hashCode() : 0)) * 31, 31, this.pinContext);
    }

    public final boolean isKidsProfile() {
        return this.isKidsProfile;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isManageMode() {
        return this.isManageMode;
    }

    public final boolean isSwitchingProfile() {
        return this.isSwitchingProfile;
    }

    public String toString() {
        List<Profile> list = this.profiles;
        Profile profile = this.activeProfile;
        boolean z = this.isLoading;
        boolean z5 = this.isSwitchingProfile;
        boolean z10 = this.isManageMode;
        boolean z11 = this.showAddDialog;
        String str = this.newProfileName;
        int i10 = this.selectedColorIndex;
        int i11 = this.selectedAvatarId;
        String str2 = this.selectedAvatarImageUri;
        boolean z12 = this.useCustomAvatarImage;
        boolean z13 = this.isKidsProfile;
        Profile profile2 = this.editingProfile;
        String str3 = this.toastMessage;
        ToastType toastType = this.toastType;
        boolean z14 = this.showToast;
        boolean z15 = this.showPinDialog;
        String str4 = this.pinDialogMode;
        Profile profile3 = this.pendingProfileForPin;
        String str5 = this.pinContext;
        String str6 = this.pinError;
        StringBuilder sb2 = new StringBuilder("ProfileUiState(profiles=");
        sb2.append(list);
        sb2.append(", activeProfile=");
        sb2.append(profile);
        sb2.append(", isLoading=");
        v.f.h(sb2, z, ", isSwitchingProfile=", z5, ", isManageMode=");
        v.f.h(sb2, z10, ", showAddDialog=", z11, ", newProfileName=");
        androidx.compose.material3.d.A(sb2, str, ", selectedColorIndex=", i10, ", selectedAvatarId=");
        sb2.append(i11);
        sb2.append(", selectedAvatarImageUri=");
        sb2.append(str2);
        sb2.append(", useCustomAvatarImage=");
        v.f.h(sb2, z12, ", isKidsProfile=", z13, ", editingProfile=");
        sb2.append(profile2);
        sb2.append(", toastMessage=");
        sb2.append(str3);
        sb2.append(", toastType=");
        sb2.append(toastType);
        sb2.append(", showToast=");
        sb2.append(z14);
        sb2.append(", showPinDialog=");
        a2.B(sb2, z15, ", pinDialogMode=", str4, ", pendingProfileForPin=");
        sb2.append(profile3);
        sb2.append(", pinContext=");
        sb2.append(str5);
        sb2.append(", pinError=");
        return a0.c.p(sb2, str6, ")");
    }

    public ProfileUiState(List<Profile> list, Profile profile, boolean z, boolean z5, boolean z10, boolean z11, String str, int i10, int i11, String str2, boolean z12, boolean z13, Profile profile2, String str3, ToastType toastType, boolean z14, boolean z15, String str4, Profile profile3, String str5, String str6) {
        this.profiles = list;
        this.activeProfile = profile;
        this.isLoading = z;
        this.isSwitchingProfile = z5;
        this.isManageMode = z10;
        this.showAddDialog = z11;
        this.newProfileName = str;
        this.selectedColorIndex = i10;
        this.selectedAvatarId = i11;
        this.selectedAvatarImageUri = str2;
        this.useCustomAvatarImage = z12;
        this.isKidsProfile = z13;
        this.editingProfile = profile2;
        this.toastMessage = str3;
        this.toastType = toastType;
        this.showToast = z14;
        this.showPinDialog = z15;
        this.pinDialogMode = str4;
        this.pendingProfileForPin = profile3;
        this.pinContext = str5;
        this.pinError = str6;
    }

    public /* synthetic */ ProfileUiState(List list, Profile profile, boolean z, boolean z5, boolean z10, boolean z11, String str, int i10, int i11, String str2, boolean z12, boolean z13, Profile profile2, String str3, ToastType toastType, boolean z14, boolean z15, String str4, Profile profile3, String str5, String str6, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? kotlin.collections.z.f19728i : list, (i12 & 2) != 0 ? null : profile, (i12 & 4) != 0 ? true : z, (i12 & 8) != 0 ? false : z5, (i12 & 16) != 0 ? false : z10, (i12 & 32) != 0 ? false : z11, (i12 & 64) != 0 ? "" : str, (i12 & 128) != 0 ? 0 : i10, (i12 & 256) != 0 ? 0 : i11, (i12 & 512) != 0 ? null : str2, (i12 & 1024) != 0 ? false : z12, (i12 & 2048) != 0 ? false : z13, (i12 & 4096) != 0 ? null : profile2, (i12 & 8192) != 0 ? null : str3, (i12 & 16384) != 0 ? ToastType.SUCCESS : toastType, (i12 & 32768) != 0 ? false : z14, (i12 & 65536) != 0 ? false : z15, (i12 & 131072) != 0 ? "" : str4, (i12 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : profile3, (i12 & 524288) != 0 ? "" : str5, (i12 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? "" : str6);
    }
}
