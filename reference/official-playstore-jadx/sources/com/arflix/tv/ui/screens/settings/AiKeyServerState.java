package com.arflix.tv.ui.screens.settings;

import android.graphics.Bitmap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/AiKeyServerState;", "", "isActive", "", "serverUrl", "", "qrBitmap", "Landroid/graphics/Bitmap;", "keyReceived", "<init>", "(ZLjava/lang/String;Landroid/graphics/Bitmap;Z)V", "()Z", "getServerUrl", "()Ljava/lang/String;", "getQrBitmap", "()Landroid/graphics/Bitmap;", "getKeyReceived", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AiKeyServerState {
    public static final int $stable = 8;
    private final boolean isActive;
    private final boolean keyReceived;
    private final Bitmap qrBitmap;
    private final String serverUrl;

    public AiKeyServerState() {
        this(false, null, null, false, 15, null);
    }

    public static /* synthetic */ AiKeyServerState copy$default(AiKeyServerState aiKeyServerState, boolean z, String str, Bitmap bitmap, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = aiKeyServerState.isActive;
        }
        if ((i10 & 2) != 0) {
            str = aiKeyServerState.serverUrl;
        }
        if ((i10 & 4) != 0) {
            bitmap = aiKeyServerState.qrBitmap;
        }
        if ((i10 & 8) != 0) {
            z5 = aiKeyServerState.keyReceived;
        }
        return aiKeyServerState.copy(z, str, bitmap, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getServerUrl() {
        return this.serverUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Bitmap getQrBitmap() {
        return this.qrBitmap;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getKeyReceived() {
        return this.keyReceived;
    }

    public final AiKeyServerState copy(boolean isActive, String serverUrl, Bitmap qrBitmap, boolean keyReceived) {
        return new AiKeyServerState(isActive, serverUrl, qrBitmap, keyReceived);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AiKeyServerState)) {
            return false;
        }
        AiKeyServerState aiKeyServerState = (AiKeyServerState) other;
        return this.isActive == aiKeyServerState.isActive && kotlin.jvm.internal.p.a(this.serverUrl, aiKeyServerState.serverUrl) && kotlin.jvm.internal.p.a(this.qrBitmap, aiKeyServerState.qrBitmap) && this.keyReceived == aiKeyServerState.keyReceived;
    }

    public final boolean getKeyReceived() {
        return this.keyReceived;
    }

    public final Bitmap getQrBitmap() {
        return this.qrBitmap;
    }

    public final String getServerUrl() {
        return this.serverUrl;
    }

    public int hashCode() {
        int i10 = (this.isActive ? 1231 : 1237) * 31;
        String str = this.serverUrl;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        Bitmap bitmap = this.qrBitmap;
        return ((iHashCode + (bitmap != null ? bitmap.hashCode() : 0)) * 31) + (this.keyReceived ? 1231 : 1237);
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public String toString() {
        return "AiKeyServerState(isActive=" + this.isActive + ", serverUrl=" + this.serverUrl + ", qrBitmap=" + this.qrBitmap + ", keyReceived=" + this.keyReceived + ")";
    }

    public AiKeyServerState(boolean z, String str, Bitmap bitmap, boolean z5) {
        this.isActive = z;
        this.serverUrl = str;
        this.qrBitmap = bitmap;
        this.keyReceived = z5;
    }

    public /* synthetic */ AiKeyServerState(boolean z, String str, Bitmap bitmap, boolean z5, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : bitmap, (i10 & 8) != 0 ? false : z5);
    }
}
