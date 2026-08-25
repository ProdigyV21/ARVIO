package com.arflix.tv.util;

import android.content.Context;
import com.arflix.tv.data.model.Profile;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u000f\u0010\u0014J)\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/util/ProfileAvatarFiles;", "", "<init>", "()V", "", "value", "safeName", "(Ljava/lang/String;)Ljava/lang/String;", "Landroid/content/Context;", "context", "Ljava/io/File;", "directory", "(Landroid/content/Context;)Ljava/io/File;", "Lcom/arflix/tv/data/model/Profile;", "profile", "localFile", "(Landroid/content/Context;Lcom/arflix/tv/data/model/Profile;)Ljava/io/File;", "profileId", "", "version", "(Landroid/content/Context;Ljava/lang/String;J)Ljava/io/File;", "keepVersion", "Lx6/t0;", "cleanupProfile", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Long;)V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileAvatarFiles {
    public static final int $stable = 0;
    public static final ProfileAvatarFiles INSTANCE = new ProfileAvatarFiles();

    private ProfileAvatarFiles() {
    }

    public static /* synthetic */ void cleanupProfile$default(ProfileAvatarFiles profileAvatarFiles, Context context, String str, Long l10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            l10 = null;
        }
        profileAvatarFiles.cleanupProfile(context, str, l10);
    }

    private final String safeName(String value) {
        return ProfileAvatarRegexes.INSTANCE.getSANITIZATION_REGEX().f("_", value);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void cleanupProfile(android.content.Context r8, java.lang.String r9, java.lang.Long r10) {
        /*
            r7 = this;
            java.lang.String r9 = r7.safeName(r9)
            java.lang.String r0 = "_"
            java.lang.String r9 = androidx.compose.material3.d.m(r9, r0)
            java.io.File r8 = r7.directory(r8)
            java.io.File[] r8 = r8.listFiles()
            if (r8 == 0) goto L4b
            int r0 = r8.length
            r1 = 0
            r2 = r1
        L17:
            if (r2 >= r0) goto L4b
            r3 = r8[r2]
            java.lang.String r4 = r3.getName()
            boolean r4 = kotlin.text.u.P(r4, r9, r1)
            if (r4 == 0) goto L48
            if (r10 == 0) goto L45
            java.lang.String r4 = r3.getName()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r9)
            r5.append(r10)
            java.lang.String r6 = ".jpg"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            boolean r4 = kotlin.jvm.internal.p.a(r4, r5)
            if (r4 != 0) goto L48
        L45:
            r3.delete()     // Catch: java.lang.Throwable -> L48
        L48:
            int r2 = r2 + 1
            goto L17
        L4b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.ProfileAvatarFiles.cleanupProfile(android.content.Context, java.lang.String, java.lang.Long):void");
    }

    public final File directory(Context context) {
        File file = new File(context.getFilesDir(), "profile_avatars");
        file.mkdirs();
        return file;
    }

    public final File localFile(Context context, Profile profile) {
        if (profile.getAvatarImageVersion() <= 0) {
            return null;
        }
        return localFile(context, profile.getId(), profile.getAvatarImageVersion());
    }

    public final File localFile(Context context, String profileId, long version) {
        return new File(directory(context), safeName(profileId) + "_" + version + ".jpg");
    }
}
