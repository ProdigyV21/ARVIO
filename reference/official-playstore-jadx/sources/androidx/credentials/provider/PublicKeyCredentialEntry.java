package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.R;
import com.google.android.gms.common.internal.f;
import j$.time.Instant;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0007\u0018\u0000 .2\u00020\u0001:\u0004/01.B\u008b\u0001\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0013\u001a\u00020\f\u0012\b\b\u0002\u0010\u0014\u001a\u00020\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0017B_\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u001aBU\b\u0017\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010*\u001a\u0004\b\r\u0010+R\u0014\u0010\u0014\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010*R\u0014\u0010\u0015\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010*R\u0017\u0010,\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b,\u0010*\u001a\u0004\b,\u0010+R\u0011\u0010-\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b-\u0010+¨\u00062"}, d2 = {"Landroidx/credentials/provider/PublicKeyCredentialEntry;", "Landroidx/credentials/provider/CredentialEntry;", "", "username", "displayName", "typeDisplayName", "Landroid/app/PendingIntent;", f.KEY_PENDING_INTENT, "Landroid/graphics/drawable/Icon;", "icon", "j$/time/Instant", "lastUsedTime", "", "isAutoSelectAllowed", "Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;", "beginGetPublicKeyCredentialOption", "isDefaultIconPreferredAsSingleProvider", "entryGroupId", "affiliatedDomain", "autoSelectAllowedFromOption", "isCreatedFromSlice", "isDefaultIconFromSlice", "<init>", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroid/graphics/drawable/Icon;Lj$/time/Instant;ZLandroidx/credentials/provider/BeginGetPublicKeyCredentialOption;ZLjava/lang/CharSequence;Ljava/lang/CharSequence;ZZZ)V", "Landroid/content/Context;", "context", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;Ljava/lang/CharSequence;Lj$/time/Instant;Landroid/graphics/drawable/Icon;ZZ)V", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;Ljava/lang/CharSequence;Lj$/time/Instant;Landroid/graphics/drawable/Icon;Z)V", "Ljava/lang/CharSequence;", "getUsername", "()Ljava/lang/CharSequence;", "getDisplayName", "getTypeDisplayName", "Landroid/app/PendingIntent;", "getPendingIntent", "()Landroid/app/PendingIntent;", "Landroid/graphics/drawable/Icon;", "getIcon", "()Landroid/graphics/drawable/Icon;", "Lj$/time/Instant;", "getLastUsedTime", "()Lj$/time/Instant;", "Z", "()Z", "isAutoSelectAllowedFromOption", "hasDefaultIcon", "Companion", "Api28Impl", "Api34Impl", "Builder", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PublicKeyCredentialEntry extends CredentialEntry {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FALSE_STRING = "false";
    private static final int REVISION_ID = 1;
    private static final String SLICE_HINT_AFFILIATED_DOMAIN = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AFFILIATED_DOMAIN";
    private static final String SLICE_HINT_AUTO_ALLOWED = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AUTO_ALLOWED";
    private static final String SLICE_HINT_AUTO_SELECT_FROM_OPTION = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION";
    private static final String SLICE_HINT_DEDUPLICATION_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_DEDUPLICATION_ID";
    private static final String SLICE_HINT_DEFAULT_ICON_RES_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID";
    private static final String SLICE_HINT_ICON = "androidx.credentials.provider.credentialEntry.SLICE_HINT_PROFILE_ICON";
    private static final String SLICE_HINT_IS_DEFAULT_ICON_PREFERRED = "androidx.credentials.provider.credentialEntry.SLICE_HINT_IS_DEFAULT_ICON_PREFERRED";
    private static final String SLICE_HINT_LAST_USED_TIME_MILLIS = "androidx.credentials.provider.credentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS";
    private static final String SLICE_HINT_OPTION_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_OPTION_ID";
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.credentialEntry.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_HINT_SUBTITLE = "androidx.credentials.provider.credentialEntry.SLICE_HINT_CREDENTIAL_TYPE_DISPLAY_NAME";
    private static final String SLICE_HINT_TITLE = "androidx.credentials.provider.credentialEntry.SLICE_HINT_USER_NAME";
    private static final String SLICE_HINT_TYPE_DISPLAY_NAME = "androidx.credentials.provider.credentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME";
    private static final String TAG = "PublicKeyCredEntry";
    private static final String TRUE_STRING = "true";
    private final CharSequence displayName;
    private final Icon icon;
    private final boolean isAutoSelectAllowed;
    private final boolean isAutoSelectAllowedFromOption;
    private final boolean isCreatedFromSlice;
    private final boolean isDefaultIconFromSlice;
    private final Instant lastUsedTime;
    private final PendingIntent pendingIntent;
    private final CharSequence typeDisplayName;
    private final CharSequence username;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0007¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/PublicKeyCredentialEntry$Api28Impl;", "", "()V", "fromSlice", "Landroidx/credentials/provider/PublicKeyCredentialEntry;", "slice", "Landroid/app/slice/Slice;", "isDefaultIcon", "", "entry", "toSlice", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @n
        public static final PublicKeyCredentialEntry fromSlice(Slice slice) {
            boolean z = false;
            boolean z5 = false;
            boolean z10 = false;
            boolean z11 = false;
            CharSequence text = null;
            CharSequence text2 = null;
            CharSequence text3 = null;
            CharSequence text4 = null;
            PendingIntent action = null;
            Icon icon = null;
            Instant instantOfEpochMilli = null;
            CharSequence text5 = null;
            CharSequence text6 = null;
            for (SliceItem sliceItem : slice.getItems()) {
                if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)) {
                    text4 = sliceItem.getText();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_TITLE)) {
                    text2 = sliceItem.getText();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_SUBTITLE)) {
                    text3 = sliceItem.getText();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_ICON)) {
                    icon = sliceItem.getIcon();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_PENDING_INTENT)) {
                    action = sliceItem.getAction();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_OPTION_ID)) {
                    text = sliceItem.getText();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instantOfEpochMilli = Instant.ofEpochMilli(sliceItem.getLong());
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_AUTO_ALLOWED)) {
                    if (p.a(sliceItem.getText(), PublicKeyCredentialEntry.TRUE_STRING)) {
                        z = true;
                    }
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION)) {
                    z10 = true;
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_IS_DEFAULT_ICON_PREFERRED)) {
                    if (p.a(sliceItem.getText(), PublicKeyCredentialEntry.TRUE_STRING)) {
                        z5 = true;
                    }
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID)) {
                    z11 = true;
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_DEDUPLICATION_ID)) {
                    text5 = sliceItem.getText();
                } else if (sliceItem.hasHint(PublicKeyCredentialEntry.SLICE_HINT_AFFILIATED_DOMAIN)) {
                    text6 = sliceItem.getText();
                }
            }
            try {
                return new PublicKeyCredentialEntry(text2, text3, text4, action, icon, instantOfEpochMilli, z, BeginGetPublicKeyCredentialOption.INSTANCE.createFromEntrySlice$credentials_release(new Bundle(), text.toString()), z5, text5, text6, z10, true, z11);
            } catch (Exception e5) {
                Log.i(PublicKeyCredentialEntry.TAG, "fromSlice failed with: " + e5.getMessage());
                return null;
            }
        }

        @n
        public static final boolean isDefaultIcon(PublicKeyCredentialEntry entry) {
            return entry.isCreatedFromSlice ? entry.isDefaultIconFromSlice : entry.getIcon().getType() == 2 && entry.getIcon().getResId() == R.drawable.ic_passkey;
        }

        @n
        public static final Slice toSlice(PublicKeyCredentialEntry entry) {
            String type = entry.getType();
            CharSequence username = entry.getUsername();
            CharSequence displayName = entry.getDisplayName();
            PendingIntent pendingIntent = entry.getPendingIntent();
            CharSequence typeDisplayName = entry.getTypeDisplayName();
            Instant lastUsedTime = entry.getLastUsedTime();
            Icon icon = entry.getIcon();
            boolean isAutoSelectAllowed = entry.getIsAutoSelectAllowed();
            BeginGetCredentialOption beginGetCredentialOption = entry.getBeginGetCredentialOption();
            CharSequence entryGroupId = entry.getEntryGroupId();
            CharSequence affiliatedDomain = entry.getAffiliatedDomain();
            boolean isDefaultIconPreferredAsSingleProvider = entry.getIsDefaultIconPreferredAsSingleProvider();
            String str = PublicKeyCredentialEntry.FALSE_STRING;
            CharSequence charSequence = isAutoSelectAllowed ? PublicKeyCredentialEntry.TRUE_STRING : PublicKeyCredentialEntry.FALSE_STRING;
            if (isDefaultIconPreferredAsSingleProvider) {
                str = PublicKeyCredentialEntry.TRUE_STRING;
            }
            Slice.Builder builderAddText = new Slice.Builder(Uri.EMPTY, new SliceSpec(type, 1)).addText(typeDisplayName, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)).addText(username, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_TITLE)).addText(displayName, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_SUBTITLE)).addText(charSequence, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_AUTO_ALLOWED)).addText(beginGetCredentialOption.getId(), null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_OPTION_ID)).addIcon(icon, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_ICON)).addText(entryGroupId, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_DEDUPLICATION_ID)).addText(affiliatedDomain, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_AFFILIATED_DOMAIN)).addText(str, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_IS_DEFAULT_ICON_PREFERRED));
            try {
                if (entry.hasDefaultIcon()) {
                    builderAddText.addInt(1, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID));
                }
            } catch (IllegalStateException unused) {
            }
            if (entry.getIsAutoSelectAllowedFromOption()) {
                builderAddText.addInt(1, null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION));
            }
            if (lastUsedTime != null) {
                builderAddText.addLong(lastUsedTime.toEpochMilli(), null, Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS));
            }
            builderAddText.addAction(pendingIntent, new Slice.Builder(builderAddText).addHints(Collections.singletonList(PublicKeyCredentialEntry.SLICE_HINT_PENDING_INTENT)).build(), null);
            return builderAddText.build();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/PublicKeyCredentialEntry$Api34Impl;", "", "()V", "fromCredentialEntry", "Landroidx/credentials/provider/PublicKeyCredentialEntry;", "credentialEntry", "Landroid/service/credentials/CredentialEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final PublicKeyCredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
            return PublicKeyCredentialEntry.INSTANCE.fromSlice(credentialEntry.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0013¢\u0006\u0004\b\u001c\u0010\u0016J\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010 R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010!R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\"R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010#R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010!R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010$R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010%R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010&R\u0016\u0010\u001b\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010&¨\u0006'"}, d2 = {"Landroidx/credentials/provider/PublicKeyCredentialEntry$Builder;", "", "Landroid/content/Context;", "context", "", "username", "Landroid/app/PendingIntent;", f.KEY_PENDING_INTENT, "Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;", "beginGetPublicKeyCredentialOption", "<init>", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;)V", "displayName", "setDisplayName", "(Ljava/lang/CharSequence;)Landroidx/credentials/provider/PublicKeyCredentialEntry$Builder;", "Landroid/graphics/drawable/Icon;", "icon", "setIcon", "(Landroid/graphics/drawable/Icon;)Landroidx/credentials/provider/PublicKeyCredentialEntry$Builder;", "", "autoSelectAllowed", "setAutoSelectAllowed", "(Z)Landroidx/credentials/provider/PublicKeyCredentialEntry$Builder;", "j$/time/Instant", "lastUsedTime", "setLastUsedTime", "(Lj$/time/Instant;)Landroidx/credentials/provider/PublicKeyCredentialEntry$Builder;", "isDefaultIconPreferredAsSingleProvider", "setDefaultIconPreferredAsSingleProvider", "Landroidx/credentials/provider/PublicKeyCredentialEntry;", "build", "()Landroidx/credentials/provider/PublicKeyCredentialEntry;", "Landroid/content/Context;", "Ljava/lang/CharSequence;", "Landroid/app/PendingIntent;", "Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;", "Lj$/time/Instant;", "Landroid/graphics/drawable/Icon;", "Z", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private boolean autoSelectAllowed;
        private final BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption;
        private final Context context;
        private CharSequence displayName;
        private Icon icon;
        private boolean isDefaultIconPreferredAsSingleProvider;
        private Instant lastUsedTime;
        private final PendingIntent pendingIntent;
        private final CharSequence username;

        public Builder(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption) {
            this.context = context;
            this.username = charSequence;
            this.pendingIntent = pendingIntent;
            this.beginGetPublicKeyCredentialOption = beginGetPublicKeyCredentialOption;
        }

        public final PublicKeyCredentialEntry build() {
            if (this.icon == null) {
                this.icon = Icon.createWithResource(this.context, R.drawable.ic_passkey);
            }
            return new PublicKeyCredentialEntry(this.username, this.displayName, this.context.getString(R.string.androidx_credentials_TYPE_PUBLIC_KEY_CREDENTIAL), this.pendingIntent, this.icon, this.lastUsedTime, this.autoSelectAllowed, this.beginGetPublicKeyCredentialOption, this.isDefaultIconPreferredAsSingleProvider, null, null, false, false, false, 15872, null);
        }

        public final Builder setAutoSelectAllowed(boolean autoSelectAllowed) {
            this.autoSelectAllowed = autoSelectAllowed;
            return this;
        }

        public final Builder setDefaultIconPreferredAsSingleProvider(boolean isDefaultIconPreferredAsSingleProvider) {
            this.isDefaultIconPreferredAsSingleProvider = isDefaultIconPreferredAsSingleProvider;
            return this;
        }

        public final Builder setDisplayName(CharSequence displayName) {
            this.displayName = displayName;
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
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/credentials/provider/PublicKeyCredentialEntry$Companion;", "", "()V", "FALSE_STRING", "", "REVISION_ID", "", "SLICE_HINT_AFFILIATED_DOMAIN", "SLICE_HINT_AUTO_ALLOWED", "SLICE_HINT_AUTO_SELECT_FROM_OPTION", "SLICE_HINT_DEDUPLICATION_ID", "SLICE_HINT_DEFAULT_ICON_RES_ID", "SLICE_HINT_ICON", "SLICE_HINT_IS_DEFAULT_ICON_PREFERRED", "SLICE_HINT_LAST_USED_TIME_MILLIS", "SLICE_HINT_OPTION_ID", "SLICE_HINT_PENDING_INTENT", "SLICE_HINT_SUBTITLE", "SLICE_HINT_TITLE", "SLICE_HINT_TYPE_DISPLAY_NAME", "TAG", "TRUE_STRING", "fromCredentialEntry", "Landroidx/credentials/provider/PublicKeyCredentialEntry;", "credentialEntry", "Landroid/service/credentials/CredentialEntry;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "toSlice", "entry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final PublicKeyCredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromCredentialEntry(credentialEntry);
            }
            return null;
        }

        @n
        public final PublicKeyCredentialEntry fromSlice(Slice slice) {
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.fromSlice(slice);
            }
            return null;
        }

        @n
        public final Slice toSlice(PublicKeyCredentialEntry entry) {
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.toSlice(entry);
            }
            return null;
        }

        private Companion() {
        }
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ PublicKeyCredentialEntry(java.lang.CharSequence r18, java.lang.CharSequence r19, java.lang.CharSequence r20, android.app.PendingIntent r21, android.graphics.drawable.Icon r22, j$.time.Instant r23, boolean r24, androidx.credentials.provider.BeginGetPublicKeyCredentialOption r25, boolean r26, java.lang.CharSequence r27, java.lang.CharSequence r28, boolean r29, boolean r30, boolean r31, int r32, kotlin.jvm.internal.h r33) {
        /*
            r17 = this;
            r0 = r32
            r1 = r0 & 512(0x200, float:7.17E-43)
            if (r1 == 0) goto L9
            r12 = r18
            goto Lb
        L9:
            r12 = r27
        Lb:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L12
            r1 = 0
            r13 = r1
            goto L14
        L12:
            r13 = r28
        L14:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L24
            androidx.credentials.CredentialOption$Companion r1 = androidx.credentials.CredentialOption.INSTANCE
            android.os.Bundle r2 = r25.getCandidateQueryData()
            boolean r1 = r1.extractAutoSelectValue$credentials_release(r2)
            r14 = r1
            goto L26
        L24:
            r14 = r29
        L26:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            r2 = 0
            if (r1 == 0) goto L2d
            r15 = r2
            goto L2f
        L2d:
            r15 = r30
        L2f:
            r0 = r0 & 8192(0x2000, float:1.148E-41)
            if (r0 == 0) goto L4a
            r16 = r2
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r2 = r17
            goto L60
        L4a:
            r16 = r31
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
        L60:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.PublicKeyCredentialEntry.<init>(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, android.app.PendingIntent, android.graphics.drawable.Icon, j$.time.Instant, boolean, androidx.credentials.provider.BeginGetPublicKeyCredentialOption, boolean, java.lang.CharSequence, java.lang.CharSequence, boolean, boolean, boolean, int, kotlin.jvm.internal.h):void");
    }

    @n
    public static final PublicKeyCredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
        return INSTANCE.fromCredentialEntry(credentialEntry);
    }

    @n
    public static final PublicKeyCredentialEntry fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @n
    public static final Slice toSlice(PublicKeyCredentialEntry publicKeyCredentialEntry) {
        return INSTANCE.toSlice(publicKeyCredentialEntry);
    }

    public final CharSequence getDisplayName() {
        return this.displayName;
    }

    public final Icon getIcon() {
        return this.icon;
    }

    public final Instant getLastUsedTime() {
        return this.lastUsedTime;
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final CharSequence getTypeDisplayName() {
        return this.typeDisplayName;
    }

    public final CharSequence getUsername() {
        return this.username;
    }

    public final boolean hasDefaultIcon() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.isDefaultIcon(this);
        }
        return false;
    }

    /* JADX INFO: renamed from: isAutoSelectAllowed, reason: from getter */
    public final boolean getIsAutoSelectAllowed() {
        return this.isAutoSelectAllowed;
    }

    /* JADX INFO: renamed from: isAutoSelectAllowedFromOption, reason: from getter */
    public final boolean getIsAutoSelectAllowedFromOption() {
        return this.isAutoSelectAllowedFromOption;
    }

    public PublicKeyCredentialEntry(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent, Icon icon, Instant instant, boolean z, BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption, boolean z5, CharSequence charSequence4, CharSequence charSequence5, boolean z10, boolean z11, boolean z12) {
        super(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, beginGetPublicKeyCredentialOption, charSequence4 == null ? charSequence : charSequence4, z5, charSequence5);
        this.username = charSequence;
        this.displayName = charSequence2;
        this.typeDisplayName = charSequence3;
        this.pendingIntent = pendingIntent;
        this.icon = icon;
        this.lastUsedTime = instant;
        this.isAutoSelectAllowed = z;
        this.isCreatedFromSlice = z11;
        this.isDefaultIconFromSlice = z12;
        this.isAutoSelectAllowedFromOption = z10;
        if (charSequence.length() > 0) {
            if (charSequence3.length() <= 0) {
                throw new IllegalArgumentException("typeDisplayName must not be empty");
            }
            return;
        }
        throw new IllegalArgumentException("username must not be empty");
    }

    public /* synthetic */ PublicKeyCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption, CharSequence charSequence2, Instant instant, Icon icon, boolean z, boolean z5, int i10, h hVar) {
        this(context, charSequence, pendingIntent, beginGetPublicKeyCredentialOption, (i10 & 16) != 0 ? null : charSequence2, (i10 & 32) != 0 ? null : instant, (i10 & 64) != 0 ? Icon.createWithResource(context, R.drawable.ic_passkey) : icon, (i10 & 128) != 0 ? false : z, (i10 & 256) != 0 ? false : z5);
    }

    public PublicKeyCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption, CharSequence charSequence2, Instant instant, Icon icon, boolean z, boolean z5) {
        this(charSequence, charSequence2, context.getString(R.string.androidx_credentials_TYPE_PUBLIC_KEY_CREDENTIAL), pendingIntent, icon, instant, z, beginGetPublicKeyCredentialOption, z5, null, null, false, false, false, 15872, null);
    }

    public /* synthetic */ PublicKeyCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption, CharSequence charSequence2, Instant instant, Icon icon, boolean z, int i10, h hVar) {
        this(context, charSequence, pendingIntent, beginGetPublicKeyCredentialOption, (i10 & 16) != 0 ? null : charSequence2, (i10 & 32) != 0 ? null : instant, (i10 & 64) != 0 ? Icon.createWithResource(context, R.drawable.ic_passkey) : icon, (i10 & 128) != 0 ? false : z);
    }

    @e
    public /* synthetic */ PublicKeyCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetPublicKeyCredentialOption beginGetPublicKeyCredentialOption, CharSequence charSequence2, Instant instant, Icon icon, boolean z) {
        this(charSequence, charSequence2, context.getString(R.string.androidx_credentials_TYPE_PUBLIC_KEY_CREDENTIAL), pendingIntent, icon, instant, z, beginGetPublicKeyCredentialOption, false, null, null, false, false, false, 15872, null);
    }
}
