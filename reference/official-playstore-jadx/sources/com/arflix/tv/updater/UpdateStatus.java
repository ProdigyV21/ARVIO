package com.arflix.tv.updater;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\u0004\u0005\u0006\u0007\b\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus;", "", "<init>", "()V", "Idle", "Checking", "UpdateAvailable", "Downloading", "ReadyToInstall", "Installing", "Success", "Failure", "Lcom/arflix/tv/updater/UpdateStatus$Checking;", "Lcom/arflix/tv/updater/UpdateStatus$Downloading;", "Lcom/arflix/tv/updater/UpdateStatus$Failure;", "Lcom/arflix/tv/updater/UpdateStatus$Idle;", "Lcom/arflix/tv/updater/UpdateStatus$Installing;", "Lcom/arflix/tv/updater/UpdateStatus$ReadyToInstall;", "Lcom/arflix/tv/updater/UpdateStatus$Success;", "Lcom/arflix/tv/updater/UpdateStatus$UpdateAvailable;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class UpdateStatus {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$Checking;", "Lcom/arflix/tv/updater/UpdateStatus;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Checking extends UpdateStatus {
        public static final int $stable = 0;
        public static final Checking INSTANCE = new Checking();

        private Checking() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Checking);
        }

        public int hashCode() {
            return -1832341545;
        }

        public String toString() {
            return "Checking";
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J$\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$Downloading;", "Lcom/arflix/tv/updater/UpdateStatus;", "progress", "", "update", "Lcom/arflix/tv/updater/AppUpdate;", "<init>", "(Ljava/lang/Float;Lcom/arflix/tv/updater/AppUpdate;)V", "getProgress", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getUpdate", "()Lcom/arflix/tv/updater/AppUpdate;", "component1", "component2", "copy", "(Ljava/lang/Float;Lcom/arflix/tv/updater/AppUpdate;)Lcom/arflix/tv/updater/UpdateStatus$Downloading;", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Downloading extends UpdateStatus {
        public static final int $stable = 0;
        private final Float progress;
        private final AppUpdate update;

        public Downloading(Float f10, AppUpdate appUpdate) {
            super(null);
            this.progress = f10;
            this.update = appUpdate;
        }

        public static /* synthetic */ Downloading copy$default(Downloading downloading, Float f10, AppUpdate appUpdate, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                f10 = downloading.progress;
            }
            if ((i10 & 2) != 0) {
                appUpdate = downloading.update;
            }
            return downloading.copy(f10, appUpdate);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Float getProgress() {
            return this.progress;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AppUpdate getUpdate() {
            return this.update;
        }

        public final Downloading copy(Float progress, AppUpdate update) {
            return new Downloading(progress, update);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Downloading)) {
                return false;
            }
            Downloading downloading = (Downloading) other;
            return p.a(this.progress, downloading.progress) && p.a(this.update, downloading.update);
        }

        public final Float getProgress() {
            return this.progress;
        }

        public final AppUpdate getUpdate() {
            return this.update;
        }

        public int hashCode() {
            Float f10 = this.progress;
            return this.update.hashCode() + ((f10 == null ? 0 : f10.hashCode()) * 31);
        }

        public String toString() {
            return "Downloading(progress=" + this.progress + ", update=" + this.update + ")";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$Failure;", "Lcom/arflix/tv/updater/UpdateStatus;", "message", "", "update", "Lcom/arflix/tv/updater/AppUpdate;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/updater/AppUpdate;)V", "getMessage", "()Ljava/lang/String;", "getUpdate", "()Lcom/arflix/tv/updater/AppUpdate;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Failure extends UpdateStatus {
        public static final int $stable = 0;
        private final String message;
        private final AppUpdate update;

        public Failure(String str, AppUpdate appUpdate) {
            super(null);
            this.message = str;
            this.update = appUpdate;
        }

        public static /* synthetic */ Failure copy$default(Failure failure, String str, AppUpdate appUpdate, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = failure.message;
            }
            if ((i10 & 2) != 0) {
                appUpdate = failure.update;
            }
            return failure.copy(str, appUpdate);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AppUpdate getUpdate() {
            return this.update;
        }

        public final Failure copy(String message, AppUpdate update) {
            return new Failure(message, update);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Failure)) {
                return false;
            }
            Failure failure = (Failure) other;
            return p.a(this.message, failure.message) && p.a(this.update, failure.update);
        }

        public final String getMessage() {
            return this.message;
        }

        public final AppUpdate getUpdate() {
            return this.update;
        }

        public int hashCode() {
            int iHashCode = this.message.hashCode() * 31;
            AppUpdate appUpdate = this.update;
            return iHashCode + (appUpdate == null ? 0 : appUpdate.hashCode());
        }

        public String toString() {
            return "Failure(message=" + this.message + ", update=" + this.update + ")";
        }

        public /* synthetic */ Failure(String str, AppUpdate appUpdate, int i10, h hVar) {
            this(str, (i10 & 2) != 0 ? null : appUpdate);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$Idle;", "Lcom/arflix/tv/updater/UpdateStatus;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Idle extends UpdateStatus {
        public static final int $stable = 0;
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Idle);
        }

        public int hashCode() {
            return 914178129;
        }

        public String toString() {
            return "Idle";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$Installing;", "Lcom/arflix/tv/updater/UpdateStatus;", "update", "Lcom/arflix/tv/updater/AppUpdate;", "<init>", "(Lcom/arflix/tv/updater/AppUpdate;)V", "getUpdate", "()Lcom/arflix/tv/updater/AppUpdate;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Installing extends UpdateStatus {
        public static final int $stable = 0;
        private final AppUpdate update;

        public Installing(AppUpdate appUpdate) {
            super(null);
            this.update = appUpdate;
        }

        public static /* synthetic */ Installing copy$default(Installing installing, AppUpdate appUpdate, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                appUpdate = installing.update;
            }
            return installing.copy(appUpdate);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AppUpdate getUpdate() {
            return this.update;
        }

        public final Installing copy(AppUpdate update) {
            return new Installing(update);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Installing) && p.a(this.update, ((Installing) other).update);
        }

        public final AppUpdate getUpdate() {
            return this.update;
        }

        public int hashCode() {
            AppUpdate appUpdate = this.update;
            if (appUpdate == null) {
                return 0;
            }
            return appUpdate.hashCode();
        }

        public String toString() {
            return "Installing(update=" + this.update + ")";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$ReadyToInstall;", "Lcom/arflix/tv/updater/UpdateStatus;", "apkPath", "", "update", "Lcom/arflix/tv/updater/AppUpdate;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/updater/AppUpdate;)V", "getApkPath", "()Ljava/lang/String;", "getUpdate", "()Lcom/arflix/tv/updater/AppUpdate;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ReadyToInstall extends UpdateStatus {
        public static final int $stable = 0;
        private final String apkPath;
        private final AppUpdate update;

        public ReadyToInstall(String str, AppUpdate appUpdate) {
            super(null);
            this.apkPath = str;
            this.update = appUpdate;
        }

        public static /* synthetic */ ReadyToInstall copy$default(ReadyToInstall readyToInstall, String str, AppUpdate appUpdate, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = readyToInstall.apkPath;
            }
            if ((i10 & 2) != 0) {
                appUpdate = readyToInstall.update;
            }
            return readyToInstall.copy(str, appUpdate);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getApkPath() {
            return this.apkPath;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AppUpdate getUpdate() {
            return this.update;
        }

        public final ReadyToInstall copy(String apkPath, AppUpdate update) {
            return new ReadyToInstall(apkPath, update);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReadyToInstall)) {
                return false;
            }
            ReadyToInstall readyToInstall = (ReadyToInstall) other;
            return p.a(this.apkPath, readyToInstall.apkPath) && p.a(this.update, readyToInstall.update);
        }

        public final String getApkPath() {
            return this.apkPath;
        }

        public final AppUpdate getUpdate() {
            return this.update;
        }

        public int hashCode() {
            return this.update.hashCode() + (this.apkPath.hashCode() * 31);
        }

        public String toString() {
            return "ReadyToInstall(apkPath=" + this.apkPath + ", update=" + this.update + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$Success;", "Lcom/arflix/tv/updater/UpdateStatus;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Success extends UpdateStatus {
        public static final int $stable = 0;
        public static final Success INSTANCE = new Success();

        private Success() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Success);
        }

        public int hashCode() {
            return 656544358;
        }

        public String toString() {
            return "Success";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatus$UpdateAvailable;", "Lcom/arflix/tv/updater/UpdateStatus;", "update", "Lcom/arflix/tv/updater/AppUpdate;", "<init>", "(Lcom/arflix/tv/updater/AppUpdate;)V", "getUpdate", "()Lcom/arflix/tv/updater/AppUpdate;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class UpdateAvailable extends UpdateStatus {
        public static final int $stable = 0;
        private final AppUpdate update;

        public UpdateAvailable(AppUpdate appUpdate) {
            super(null);
            this.update = appUpdate;
        }

        public static /* synthetic */ UpdateAvailable copy$default(UpdateAvailable updateAvailable, AppUpdate appUpdate, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                appUpdate = updateAvailable.update;
            }
            return updateAvailable.copy(appUpdate);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AppUpdate getUpdate() {
            return this.update;
        }

        public final UpdateAvailable copy(AppUpdate update) {
            return new UpdateAvailable(update);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UpdateAvailable) && p.a(this.update, ((UpdateAvailable) other).update);
        }

        public final AppUpdate getUpdate() {
            return this.update;
        }

        public int hashCode() {
            return this.update.hashCode();
        }

        public String toString() {
            return "UpdateAvailable(update=" + this.update + ")";
        }
    }

    public /* synthetic */ UpdateStatus(h hVar) {
        this();
    }

    private UpdateStatus() {
    }
}
