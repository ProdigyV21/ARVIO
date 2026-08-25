package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.f;
import j$.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import q7.n;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b \b\u0007\u0018\u0000 +2\u00020\u0001:\u0004,-.+BU\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012Bk\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0016J\u000f\u0010\u0017\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0019\u0010\u0018J\u000f\u0010\u001a\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u001a\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b$\u0010\u001dR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b&\u0010'R\"\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010(R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010)\u001a\u0004\b\u0010\u0010*¨\u0006/"}, d2 = {"Landroidx/credentials/provider/CreateEntry;", "", "", "accountName", "Landroid/app/PendingIntent;", f.KEY_PENDING_INTENT, "Landroid/graphics/drawable/Icon;", "icon", MediaTrack.ROLE_DESCRIPTION, "j$/time/Instant", "lastUsedTime", "", "", "", "credentialCountInformationMap", "", "isAutoSelectAllowed", "<init>", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroid/graphics/drawable/Icon;Ljava/lang/CharSequence;Lj$/time/Instant;Ljava/util/Map;Z)V", "passwordCredentialCount", "publicKeyCredentialCount", "totalCredentialCount", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;Ljava/lang/CharSequence;Lj$/time/Instant;Landroid/graphics/drawable/Icon;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "getPasswordCredentialCount", "()Ljava/lang/Integer;", "getPublicKeyCredentialCount", "getTotalCredentialCount", "Ljava/lang/CharSequence;", "getAccountName", "()Ljava/lang/CharSequence;", "Landroid/app/PendingIntent;", "getPendingIntent", "()Landroid/app/PendingIntent;", "Landroid/graphics/drawable/Icon;", "getIcon", "()Landroid/graphics/drawable/Icon;", "getDescription", "Lj$/time/Instant;", "getLastUsedTime", "()Lj$/time/Instant;", "Ljava/util/Map;", "Z", "()Z", "Companion", "Api28Impl", "Api34Impl", "Builder", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CreateEntry {
    private static final String AUTO_SELECT_FALSE_STRING = "false";
    private static final String AUTO_SELECT_TRUE_STRING = "true";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int DESCRIPTION_MAX_CHAR_LIMIT = 300;
    private static final int REVISION_ID = 1;
    private static final String SLICE_HINT_ACCOUNT_NAME = "androidx.credentials.provider.createEntry.SLICE_HINT_USER_PROVIDER_ACCOUNT_NAME";
    private static final String SLICE_HINT_AUTO_SELECT_ALLOWED = "androidx.credentials.provider.createEntry.SLICE_HINT_AUTO_SELECT_ALLOWED";
    private static final String SLICE_HINT_CREDENTIAL_COUNT_INFORMATION = "androidx.credentials.provider.createEntry.SLICE_HINT_CREDENTIAL_COUNT_INFORMATION";
    private static final String SLICE_HINT_ICON = "androidx.credentials.provider.createEntry.SLICE_HINT_PROFILE_ICON";
    private static final String SLICE_HINT_LAST_USED_TIME_MILLIS = "androidx.credentials.provider.createEntry.SLICE_HINT_LAST_USED_TIME_MILLIS";
    private static final String SLICE_HINT_NOTE = "androidx.credentials.provider.createEntry.SLICE_HINT_NOTE";
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.createEntry.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_SPEC_TYPE = "CreateEntry";
    private static final String TAG = "CreateEntry";
    public static final String TYPE_TOTAL_CREDENTIAL = "TOTAL_CREDENTIAL_COUNT_TYPE";
    private final CharSequence accountName;
    private final Map<String, Integer> credentialCountInformationMap;
    private final CharSequence description;
    private final Icon icon;
    private final boolean isAutoSelectAllowed;
    private final Instant lastUsedTime;
    private final PendingIntent pendingIntent;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\tJ%\u0010\n\u001a\u0004\u0018\u00010\b2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0001¢\u0006\u0002\b\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000eH\u0007¨\u0006\u0013"}, d2 = {"Landroidx/credentials/provider/CreateEntry$Api28Impl;", "", "()V", "convertBundleToCredentialCountInfo", "", "", "", "bundle", "Landroid/os/Bundle;", "convertBundleToCredentialCountInfo$credentials_release", "convertCredentialCountInfoToBundle", "credentialCountInformationMap", "convertCredentialCountInfoToBundle$credentials_release", "fromSlice", "Landroidx/credentials/provider/CreateEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "createEntry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @n
        public static final Map<String, Integer> convertBundleToCredentialCountInfo$credentials_release(Bundle bundle) {
            HashMap map = new HashMap();
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    try {
                        map.put(str, Integer.valueOf(bundle.getInt(str)));
                    } catch (Exception e5) {
                        Log.i("CreateEntry", "Issue unpacking credential count info bundle: " + e5.getMessage());
                    }
                }
            }
            return map;
        }

        @n
        public static final Bundle convertCredentialCountInfoToBundle$credentials_release(Map<String, Integer> credentialCountInformationMap) {
            Bundle bundle = new Bundle();
            boolean z = false;
            for (Map.Entry<String, Integer> entry : credentialCountInformationMap.entrySet()) {
                if (entry.getValue() != null) {
                    bundle.putInt(entry.getKey(), entry.getValue().intValue());
                    z = true;
                }
            }
            if (z) {
                return bundle;
            }
            return null;
        }

        @n
        public static final CreateEntry fromSlice(Slice slice) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Map mapC = linkedHashMap;
            CharSequence text = null;
            PendingIntent action = null;
            Icon icon = null;
            CharSequence text2 = null;
            Instant instantOfEpochMilli = null;
            boolean z = false;
            for (SliceItem sliceItem : slice.getItems()) {
                if (sliceItem.hasHint(CreateEntry.SLICE_HINT_ACCOUNT_NAME)) {
                    text = sliceItem.getText();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_ICON)) {
                    icon = sliceItem.getIcon();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_PENDING_INTENT)) {
                    action = sliceItem.getAction();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_CREDENTIAL_COUNT_INFORMATION)) {
                    mapC = q0.c(convertBundleToCredentialCountInfo$credentials_release(sliceItem.getBundle()));
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instantOfEpochMilli = Instant.ofEpochMilli(sliceItem.getLong());
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_NOTE)) {
                    text2 = sliceItem.getText();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instantOfEpochMilli = Instant.ofEpochMilli(sliceItem.getLong());
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_AUTO_SELECT_ALLOWED) && p.a(sliceItem.getText(), CreateEntry.AUTO_SELECT_TRUE_STRING)) {
                    z = true;
                }
            }
            try {
                return new CreateEntry(text, action, icon, text2, instantOfEpochMilli, mapC, z);
            } catch (Exception e5) {
                Log.i("CreateEntry", "fromSlice failed with: " + e5.getMessage());
                return null;
            }
        }

        @n
        public static final Slice toSlice(CreateEntry createEntry) {
            CharSequence accountName = createEntry.getAccountName();
            Icon icon = createEntry.getIcon();
            CharSequence description = createEntry.getDescription();
            Instant lastUsedTime = createEntry.getLastUsedTime();
            Map map = createEntry.credentialCountInformationMap;
            PendingIntent pendingIntent = createEntry.getPendingIntent();
            Slice.Builder builder = new Slice.Builder(Uri.EMPTY, new SliceSpec("CreateEntry", 1));
            String str = createEntry.getIsAutoSelectAllowed() ? CreateEntry.AUTO_SELECT_TRUE_STRING : CreateEntry.AUTO_SELECT_FALSE_STRING;
            builder.addText(accountName, null, Collections.singletonList(CreateEntry.SLICE_HINT_ACCOUNT_NAME));
            if (lastUsedTime != null) {
                builder.addLong(lastUsedTime.toEpochMilli(), null, Collections.singletonList(CreateEntry.SLICE_HINT_LAST_USED_TIME_MILLIS));
            }
            if (description != null) {
                builder.addText(description, null, Collections.singletonList(CreateEntry.SLICE_HINT_NOTE));
            }
            if (icon != null) {
                builder.addIcon(icon, null, Collections.singletonList(CreateEntry.SLICE_HINT_ICON));
            }
            if (convertCredentialCountInfoToBundle$credentials_release(map) != null) {
                builder.addBundle(convertCredentialCountInfoToBundle$credentials_release(map), null, Collections.singletonList(CreateEntry.SLICE_HINT_CREDENTIAL_COUNT_INFORMATION));
            }
            builder.addAction(pendingIntent, new Slice.Builder(builder).addHints(Collections.singletonList(CreateEntry.SLICE_HINT_PENDING_INTENT)).build(), null).addText(str, null, Collections.singletonList(CreateEntry.SLICE_HINT_AUTO_SELECT_ALLOWED));
            return builder.build();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/CreateEntry$Api34Impl;", "", "()V", "fromCreateEntry", "Landroidx/credentials/provider/CreateEntry;", "createEntry", "Landroid/service/credentials/CreateEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final CreateEntry fromCreateEntry(android.service.credentials.CreateEntry createEntry) {
            return CreateEntry.INSTANCE.fromSlice(createEntry.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u000fJ\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00002\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010 R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010!R$\u0010$\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010\f0\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010&R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010 R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010'R\u0018\u0010(\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010)R\u0018\u0010+\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010)R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010,¨\u0006-"}, d2 = {"Landroidx/credentials/provider/CreateEntry$Builder;", "", "", "accountName", "Landroid/app/PendingIntent;", f.KEY_PENDING_INTENT, "<init>", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V", "", "autoSelectAllowed", "setAutoSelectAllowed", "(Z)Landroidx/credentials/provider/CreateEntry$Builder;", "", "count", "setPasswordCredentialCount", "(I)Landroidx/credentials/provider/CreateEntry$Builder;", "setPublicKeyCredentialCount", "setTotalCredentialCount", "Landroid/graphics/drawable/Icon;", "icon", "setIcon", "(Landroid/graphics/drawable/Icon;)Landroidx/credentials/provider/CreateEntry$Builder;", MediaTrack.ROLE_DESCRIPTION, "setDescription", "(Ljava/lang/CharSequence;)Landroidx/credentials/provider/CreateEntry$Builder;", "j$/time/Instant", "lastUsedTime", "setLastUsedTime", "(Lj$/time/Instant;)Landroidx/credentials/provider/CreateEntry$Builder;", "Landroidx/credentials/provider/CreateEntry;", "build", "()Landroidx/credentials/provider/CreateEntry;", "Ljava/lang/CharSequence;", "Landroid/app/PendingIntent;", "", "", "credentialCountInformationMap", "Ljava/util/Map;", "Landroid/graphics/drawable/Icon;", "Lj$/time/Instant;", "passwordCredentialCount", "Ljava/lang/Integer;", "publicKeyCredentialCount", "totalCredentialCount", "Z", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private final CharSequence accountName;
        private boolean autoSelectAllowed;
        private Map<String, Integer> credentialCountInformationMap = new LinkedHashMap();
        private CharSequence description;
        private Icon icon;
        private Instant lastUsedTime;
        private Integer passwordCredentialCount;
        private final PendingIntent pendingIntent;
        private Integer publicKeyCredentialCount;
        private Integer totalCredentialCount;

        public Builder(CharSequence charSequence, PendingIntent pendingIntent) {
            this.accountName = charSequence;
            this.pendingIntent = pendingIntent;
        }

        public final CreateEntry build() {
            return new CreateEntry(this.accountName, this.pendingIntent, this.icon, this.description, this.lastUsedTime, this.credentialCountInformationMap, this.autoSelectAllowed);
        }

        public final Builder setAutoSelectAllowed(boolean autoSelectAllowed) {
            this.autoSelectAllowed = autoSelectAllowed;
            return this;
        }

        public final Builder setDescription(CharSequence description) {
            if ((description != null ? Integer.valueOf(description.length()) : null) != null && description.length() > 300) {
                throw new IllegalArgumentException("Description must follow a limit of 300 characters.");
            }
            this.description = description;
            return this;
        }

        public final Builder setIcon(Icon icon) {
            this.icon = icon;
            return this;
        }

        public final Builder setLastUsedTime(Instant lastUsedTime) {
            this.lastUsedTime = lastUsedTime;
            return this;
        }

        public final Builder setPasswordCredentialCount(int count) {
            this.passwordCredentialCount = Integer.valueOf(count);
            this.credentialCountInformationMap.put(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, Integer.valueOf(count));
            return this;
        }

        public final Builder setPublicKeyCredentialCount(int count) {
            this.publicKeyCredentialCount = Integer.valueOf(count);
            this.credentialCountInformationMap.put(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, Integer.valueOf(count));
            return this;
        }

        public final Builder setTotalCredentialCount(int count) {
            this.totalCredentialCount = Integer.valueOf(count);
            this.credentialCountInformationMap.put(CreateEntry.TYPE_TOTAL_CREDENTIAL, Integer.valueOf(count));
            return this;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0015\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/credentials/provider/CreateEntry$Companion;", "", "()V", "AUTO_SELECT_FALSE_STRING", "", "AUTO_SELECT_TRUE_STRING", "DESCRIPTION_MAX_CHAR_LIMIT", "", "REVISION_ID", "SLICE_HINT_ACCOUNT_NAME", "SLICE_HINT_AUTO_SELECT_ALLOWED", "SLICE_HINT_CREDENTIAL_COUNT_INFORMATION", "SLICE_HINT_ICON", "SLICE_HINT_LAST_USED_TIME_MILLIS", "SLICE_HINT_NOTE", "SLICE_HINT_PENDING_INTENT", "SLICE_SPEC_TYPE", "TAG", "TYPE_TOTAL_CREDENTIAL", "fromCreateEntry", "Landroidx/credentials/provider/CreateEntry;", "createEntry", "Landroid/service/credentials/CreateEntry;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "toSlice", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final CreateEntry fromCreateEntry(android.service.credentials.CreateEntry createEntry) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromCreateEntry(createEntry);
            }
            return null;
        }

        @n
        public final CreateEntry fromSlice(Slice slice) {
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.fromSlice(slice);
            }
            return null;
        }

        @n
        public final Slice toSlice(CreateEntry createEntry) {
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.toSlice(createEntry);
            }
            return null;
        }

        private Companion() {
        }
    }

    public CreateEntry(CharSequence charSequence, PendingIntent pendingIntent, CharSequence charSequence2, Instant instant, Icon icon, Integer num, Integer num2, Integer num3, boolean z) {
        this(charSequence, pendingIntent, icon, charSequence2, instant, h0.u0(new x(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, num), new x(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, num2), new x(TYPE_TOTAL_CREDENTIAL, num3)), z);
    }

    @n
    public static final CreateEntry fromCreateEntry(android.service.credentials.CreateEntry createEntry) {
        return INSTANCE.fromCreateEntry(createEntry);
    }

    @n
    public static final CreateEntry fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @n
    public static final Slice toSlice(CreateEntry createEntry) {
        return INSTANCE.toSlice(createEntry);
    }

    public final CharSequence getAccountName() {
        return this.accountName;
    }

    public final CharSequence getDescription() {
        return this.description;
    }

    public final Icon getIcon() {
        return this.icon;
    }

    public final Instant getLastUsedTime() {
        return this.lastUsedTime;
    }

    public final Integer getPasswordCredentialCount() {
        return this.credentialCountInformationMap.get(PasswordCredential.TYPE_PASSWORD_CREDENTIAL);
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final Integer getPublicKeyCredentialCount() {
        return this.credentialCountInformationMap.get(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL);
    }

    public final Integer getTotalCredentialCount() {
        return this.credentialCountInformationMap.get(TYPE_TOTAL_CREDENTIAL);
    }

    /* JADX INFO: renamed from: isAutoSelectAllowed, reason: from getter */
    public final boolean getIsAutoSelectAllowed() {
        return this.isAutoSelectAllowed;
    }

    public CreateEntry(CharSequence charSequence, PendingIntent pendingIntent, Icon icon, CharSequence charSequence2, Instant instant, Map<String, Integer> map, boolean z) {
        this.accountName = charSequence;
        this.pendingIntent = pendingIntent;
        this.icon = icon;
        this.description = charSequence2;
        this.lastUsedTime = instant;
        this.credentialCountInformationMap = map;
        this.isAutoSelectAllowed = z;
        if (charSequence.length() > 0) {
            if (charSequence2 != null && charSequence2.length() > 300) {
                throw new IllegalArgumentException("Description must follow a limit of 300 characters.");
            }
            return;
        }
        throw new IllegalArgumentException("accountName must not be empty");
    }

    public /* synthetic */ CreateEntry(CharSequence charSequence, PendingIntent pendingIntent, CharSequence charSequence2, Instant instant, Icon icon, Integer num, Integer num2, Integer num3, boolean z, int i10, h hVar) {
        this(charSequence, pendingIntent, (i10 & 4) != 0 ? null : charSequence2, (i10 & 8) != 0 ? null : instant, (i10 & 16) != 0 ? null : icon, (i10 & 32) != 0 ? null : num, (i10 & 64) != 0 ? null : num2, (i10 & 128) != 0 ? null : num3, (i10 & 256) != 0 ? false : z);
    }
}
