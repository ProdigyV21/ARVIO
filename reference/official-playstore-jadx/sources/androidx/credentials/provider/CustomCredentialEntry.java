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
import androidx.credentials.R;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.f;
import io.ktor.http.LinkHeader;
import j$.time.Instant;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0007\u0018\u0000 32\u00020\u0001:\u00044563B\u0095\u0001\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\b¢\u0006\u0004\b\u0018\u0010\u0019Ba\b\u0017\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0018\u0010\u001cBu\b\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0018\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b\t\u0010(R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b)\u0010#R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b*\u0010#R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010.\u001a\u0004\b/\u00100R\u0016\u0010\u0016\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010'R\u0016\u0010\u0017\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010'R\u0017\u00101\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b1\u0010'\u001a\u0004\b1\u0010(R\u0011\u00102\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b2\u0010(¨\u00067"}, d2 = {"Landroidx/credentials/provider/CustomCredentialEntry;", "Landroidx/credentials/provider/CredentialEntry;", "", LinkHeader.Parameters.Type, "", LinkHeader.Parameters.Title, "Landroid/app/PendingIntent;", f.KEY_PENDING_INTENT, "", "isAutoSelectAllowed", MediaTrack.ROLE_SUBTITLE, "typeDisplayName", "Landroid/graphics/drawable/Icon;", "icon", "j$/time/Instant", "lastUsedTime", "Landroidx/credentials/provider/BeginGetCredentialOption;", "beginGetCredentialOption", "isDefaultIconPreferredAsSingleProvider", "entryGroupId", "affiliatedDomain", "autoSelectAllowedFromOption", "isCreatedFromSlice", "isDefaultIconFromSlice", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Landroid/app/PendingIntent;ZLjava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/graphics/drawable/Icon;Lj$/time/Instant;Landroidx/credentials/provider/BeginGetCredentialOption;ZLjava/lang/CharSequence;Ljava/lang/CharSequence;ZZZ)V", "Landroid/content/Context;", "context", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetCredentialOption;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Lj$/time/Instant;Landroid/graphics/drawable/Icon;Z)V", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetCredentialOption;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Lj$/time/Instant;Landroid/graphics/drawable/Icon;ZLjava/lang/CharSequence;Z)V", "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "Ljava/lang/CharSequence;", "getTitle", "()Ljava/lang/CharSequence;", "Landroid/app/PendingIntent;", "getPendingIntent", "()Landroid/app/PendingIntent;", "Z", "()Z", "getSubtitle", "getTypeDisplayName", "Landroid/graphics/drawable/Icon;", "getIcon", "()Landroid/graphics/drawable/Icon;", "Lj$/time/Instant;", "getLastUsedTime", "()Lj$/time/Instant;", "isAutoSelectAllowedFromOption", "hasDefaultIcon", "Companion", "Api28Impl", "Api34Impl", "Builder", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CustomCredentialEntry extends CredentialEntry {

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
    private static final String TAG = "CredentialEntry";
    private static final String TRUE_STRING = "true";
    private final Icon icon;
    private final boolean isAutoSelectAllowed;
    private final boolean isAutoSelectAllowedFromOption;
    private boolean isCreatedFromSlice;
    private boolean isDefaultIconFromSlice;
    private final Instant lastUsedTime;
    private final PendingIntent pendingIntent;
    private final CharSequence subtitle;
    private final CharSequence title;
    private final String type;
    private final CharSequence typeDisplayName;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0007¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/CustomCredentialEntry$Api28Impl;", "", "()V", "fromSlice", "Landroidx/credentials/provider/CustomCredentialEntry;", "slice", "Landroid/app/slice/Slice;", "isDefaultIcon", "", "entry", "toSlice", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @n
        public static final CustomCredentialEntry fromSlice(Slice slice) {
            Iterator it;
            String type = slice.getSpec().getType();
            Iterator it2 = slice.getItems().iterator();
            boolean z = false;
            boolean z5 = false;
            boolean z10 = false;
            boolean z11 = false;
            CharSequence text = null;
            CharSequence text2 = null;
            PendingIntent action = null;
            CharSequence text3 = null;
            CharSequence text4 = null;
            Icon icon = null;
            Instant instantOfEpochMilli = null;
            CharSequence text5 = null;
            CharSequence text6 = null;
            while (it2.hasNext()) {
                SliceItem sliceItem = (SliceItem) it2.next();
                if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)) {
                    text4 = sliceItem.getText();
                } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_TITLE)) {
                    text2 = sliceItem.getText();
                } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_SUBTITLE)) {
                    text3 = sliceItem.getText();
                } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_ICON)) {
                    icon = sliceItem.getIcon();
                } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_PENDING_INTENT)) {
                    action = sliceItem.getAction();
                } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_OPTION_ID)) {
                    text = sliceItem.getText();
                } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instantOfEpochMilli = Instant.ofEpochMilli(sliceItem.getLong());
                } else {
                    it = it2;
                    if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_AUTO_ALLOWED)) {
                        if (p.a(sliceItem.getText(), CustomCredentialEntry.TRUE_STRING)) {
                            z = true;
                        }
                    } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_DEDUPLICATION_ID)) {
                        text5 = sliceItem.getText();
                    } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION)) {
                        z10 = true;
                    } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_IS_DEFAULT_ICON_PREFERRED)) {
                        if (p.a(sliceItem.getText(), CustomCredentialEntry.TRUE_STRING)) {
                            z5 = true;
                        }
                    } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID)) {
                        z11 = true;
                    } else if (sliceItem.hasHint(CustomCredentialEntry.SLICE_HINT_AFFILIATED_DOMAIN)) {
                        text6 = sliceItem.getText();
                    }
                    it2 = it;
                }
                it = it2;
                it2 = it;
            }
            try {
                return new CustomCredentialEntry(type, text2, action, z, text3, text4, icon, instantOfEpochMilli, new BeginGetCustomCredentialOption(text.toString(), type, new Bundle()), z5, text5, text6, z10, true, z11);
            } catch (Exception e5) {
                Log.i(CustomCredentialEntry.TAG, "fromSlice failed with: " + e5.getMessage());
                return null;
            }
        }

        @n
        public static final boolean isDefaultIcon(CustomCredentialEntry entry) {
            return entry.isCreatedFromSlice ? entry.isDefaultIconFromSlice : entry.getIcon().getType() == 2 && entry.getIcon().getResId() == R.drawable.ic_other_sign_in;
        }

        @n
        public static final Slice toSlice(CustomCredentialEntry entry) {
            String type = entry.getType();
            CharSequence title = entry.getTitle();
            CharSequence subtitle = entry.getSubtitle();
            PendingIntent pendingIntent = entry.getPendingIntent();
            CharSequence typeDisplayName = entry.getTypeDisplayName();
            Instant lastUsedTime = entry.getLastUsedTime();
            Icon icon = entry.getIcon();
            boolean isAutoSelectAllowed = entry.getIsAutoSelectAllowed();
            BeginGetCredentialOption beginGetCredentialOption = entry.getBeginGetCredentialOption();
            CharSequence entryGroupId = entry.getEntryGroupId();
            CharSequence affiliatedDomain = entry.getAffiliatedDomain();
            boolean zIsDefaultIconPreferredAsSingleProvider = entry.getIsDefaultIconPreferredAsSingleProvider();
            String str = CustomCredentialEntry.FALSE_STRING;
            CharSequence charSequence = isAutoSelectAllowed ? CustomCredentialEntry.TRUE_STRING : CustomCredentialEntry.FALSE_STRING;
            if (zIsDefaultIconPreferredAsSingleProvider) {
                str = CustomCredentialEntry.TRUE_STRING;
            }
            Slice.Builder builderAddText = new Slice.Builder(Uri.EMPTY, new SliceSpec(type, 1)).addText(typeDisplayName, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)).addText(title, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_TITLE)).addText(subtitle, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_SUBTITLE)).addText(charSequence, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_AUTO_ALLOWED)).addText(beginGetCredentialOption.getId(), null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_OPTION_ID)).addText(entryGroupId, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_DEDUPLICATION_ID)).addText(affiliatedDomain, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_AFFILIATED_DOMAIN)).addIcon(icon, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_ICON)).addText(str, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_IS_DEFAULT_ICON_PREFERRED));
            try {
                if (entry.hasDefaultIcon()) {
                    builderAddText.addInt(1, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID));
                }
            } catch (IllegalStateException unused) {
            }
            if (entry.getIsAutoSelectAllowedFromOption()) {
                builderAddText.addInt(1, null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION));
            }
            if (lastUsedTime != null) {
                builderAddText.addLong(lastUsedTime.toEpochMilli(), null, Collections.singletonList(CustomCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS));
            }
            builderAddText.addAction(pendingIntent, new Slice.Builder(builderAddText).addHints(Collections.singletonList(CustomCredentialEntry.SLICE_HINT_PENDING_INTENT)).build(), null);
            return builderAddText.build();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/CustomCredentialEntry$Api34Impl;", "", "()V", "fromCredentialEntry", "Landroidx/credentials/provider/CustomCredentialEntry;", "credentialEntry", "Landroid/service/credentials/CredentialEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final CustomCredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
            return CustomCredentialEntry.INSTANCE.fromSlice(credentialEntry.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0012\u0010\u0010J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\u0010J\u0017\u0010\u001f\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0017¢\u0006\u0004\b\"\u0010\u001aJ\r\u0010$\u001a\u00020#¢\u0006\u0004\b$\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010&R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010'R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010(R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010)R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010*R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010(R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010+R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010(R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010,R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010-R\u0016\u0010\u001b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010(R\u0016\u0010!\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010-¨\u0006."}, d2 = {"Landroidx/credentials/provider/CustomCredentialEntry$Builder;", "", "Landroid/content/Context;", "context", "", LinkHeader.Parameters.Type, "", LinkHeader.Parameters.Title, "Landroid/app/PendingIntent;", f.KEY_PENDING_INTENT, "Landroidx/credentials/provider/BeginGetCredentialOption;", "beginGetCredentialOption", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetCredentialOption;)V", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "(Ljava/lang/CharSequence;)Landroidx/credentials/provider/CustomCredentialEntry$Builder;", "typeDisplayName", "setTypeDisplayName", "Landroid/graphics/drawable/Icon;", "icon", "setIcon", "(Landroid/graphics/drawable/Icon;)Landroidx/credentials/provider/CustomCredentialEntry$Builder;", "", "autoSelectAllowed", "setAutoSelectAllowed", "(Z)Landroidx/credentials/provider/CustomCredentialEntry$Builder;", "entryGroupId", "setEntryGroupId", "j$/time/Instant", "lastUsedTime", "setLastUsedTime", "(Lj$/time/Instant;)Landroidx/credentials/provider/CustomCredentialEntry$Builder;", "isDefaultIconPreferredAsSingleProvider", "setDefaultIconPreferredAsSingleProvider", "Landroidx/credentials/provider/CustomCredentialEntry;", "build", "()Landroidx/credentials/provider/CustomCredentialEntry;", "Landroid/content/Context;", "Ljava/lang/String;", "Ljava/lang/CharSequence;", "Landroid/app/PendingIntent;", "Landroidx/credentials/provider/BeginGetCredentialOption;", "Lj$/time/Instant;", "Landroid/graphics/drawable/Icon;", "Z", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private boolean autoSelectAllowed;
        private final BeginGetCredentialOption beginGetCredentialOption;
        private final Context context;
        private CharSequence entryGroupId;
        private Icon icon;
        private boolean isDefaultIconPreferredAsSingleProvider;
        private Instant lastUsedTime;
        private final PendingIntent pendingIntent;
        private CharSequence subtitle;
        private final CharSequence title;
        private final String type;
        private CharSequence typeDisplayName;

        public Builder(Context context, String str, CharSequence charSequence, PendingIntent pendingIntent, BeginGetCredentialOption beginGetCredentialOption) {
            this.context = context;
            this.type = str;
            this.title = charSequence;
            this.pendingIntent = pendingIntent;
            this.beginGetCredentialOption = beginGetCredentialOption;
            this.entryGroupId = charSequence;
        }

        public final CustomCredentialEntry build() {
            if (this.icon == null) {
                this.icon = Icon.createWithResource(this.context, R.drawable.ic_other_sign_in);
            }
            return new CustomCredentialEntry(this.type, this.title, this.pendingIntent, this.autoSelectAllowed, this.subtitle, this.typeDisplayName, this.icon, this.lastUsedTime, this.beginGetCredentialOption, this.isDefaultIconPreferredAsSingleProvider, this.entryGroupId, null, false, false, false, 30720, null);
        }

        public final Builder setAutoSelectAllowed(boolean autoSelectAllowed) {
            this.autoSelectAllowed = autoSelectAllowed;
            return this;
        }

        public final Builder setDefaultIconPreferredAsSingleProvider(boolean isDefaultIconPreferredAsSingleProvider) {
            this.isDefaultIconPreferredAsSingleProvider = isDefaultIconPreferredAsSingleProvider;
            return this;
        }

        public final Builder setEntryGroupId(CharSequence entryGroupId) {
            if (entryGroupId.length() <= 0) {
                throw new IllegalArgumentException("entryGroupId must not be empty");
            }
            this.entryGroupId = entryGroupId;
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

        public final Builder setSubtitle(CharSequence subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public final Builder setTypeDisplayName(CharSequence typeDisplayName) {
            this.typeDisplayName = typeDisplayName;
            return this;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/credentials/provider/CustomCredentialEntry$Companion;", "", "()V", "FALSE_STRING", "", "REVISION_ID", "", "SLICE_HINT_AFFILIATED_DOMAIN", "SLICE_HINT_AUTO_ALLOWED", "SLICE_HINT_AUTO_SELECT_FROM_OPTION", "SLICE_HINT_DEDUPLICATION_ID", "SLICE_HINT_DEFAULT_ICON_RES_ID", "SLICE_HINT_ICON", "SLICE_HINT_IS_DEFAULT_ICON_PREFERRED", "SLICE_HINT_LAST_USED_TIME_MILLIS", "SLICE_HINT_OPTION_ID", "SLICE_HINT_PENDING_INTENT", "SLICE_HINT_SUBTITLE", "SLICE_HINT_TITLE", "SLICE_HINT_TYPE_DISPLAY_NAME", "TAG", "TRUE_STRING", "fromCredentialEntry", "Landroidx/credentials/provider/CustomCredentialEntry;", "credentialEntry", "Landroid/service/credentials/CredentialEntry;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "toSlice", "entry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final CustomCredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromCredentialEntry(credentialEntry);
            }
            return null;
        }

        @n
        public final CustomCredentialEntry fromSlice(Slice slice) {
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.fromSlice(slice);
            }
            return null;
        }

        @n
        public final Slice toSlice(CustomCredentialEntry entry) {
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
    public /* synthetic */ CustomCredentialEntry(java.lang.String r19, java.lang.CharSequence r20, android.app.PendingIntent r21, boolean r22, java.lang.CharSequence r23, java.lang.CharSequence r24, android.graphics.drawable.Icon r25, j$.time.Instant r26, androidx.credentials.provider.BeginGetCredentialOption r27, boolean r28, java.lang.CharSequence r29, java.lang.CharSequence r30, boolean r31, boolean r32, boolean r33, int r34, kotlin.jvm.internal.h r35) {
        /*
            r18 = this;
            r0 = r34
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L9
            r13 = r20
            goto Lb
        L9:
            r13 = r29
        Lb:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L12
            r1 = 0
            r14 = r1
            goto L14
        L12:
            r14 = r30
        L14:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L24
            androidx.credentials.CredentialOption$Companion r1 = androidx.credentials.CredentialOption.INSTANCE
            android.os.Bundle r2 = r27.getCandidateQueryData()
            boolean r1 = r1.extractAutoSelectValue$credentials_release(r2)
            r15 = r1
            goto L26
        L24:
            r15 = r31
        L26:
            r1 = r0 & 8192(0x2000, float:1.148E-41)
            r2 = 0
            if (r1 == 0) goto L2e
            r16 = r2
            goto L30
        L2e:
            r16 = r32
        L30:
            r0 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L4d
            r17 = r2
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r23
            r8 = r24
            r9 = r25
            r10 = r26
            r11 = r27
            r12 = r28
            r2 = r18
            goto L65
        L4d:
            r17 = r33
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r23
            r8 = r24
            r9 = r25
            r10 = r26
            r11 = r27
            r12 = r28
        L65:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.CustomCredentialEntry.<init>(java.lang.String, java.lang.CharSequence, android.app.PendingIntent, boolean, java.lang.CharSequence, java.lang.CharSequence, android.graphics.drawable.Icon, j$.time.Instant, androidx.credentials.provider.BeginGetCredentialOption, boolean, java.lang.CharSequence, java.lang.CharSequence, boolean, boolean, boolean, int, kotlin.jvm.internal.h):void");
    }

    @n
    public static final CustomCredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
        return INSTANCE.fromCredentialEntry(credentialEntry);
    }

    @n
    public static final CustomCredentialEntry fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @n
    public static final Slice toSlice(CustomCredentialEntry customCredentialEntry) {
        return INSTANCE.toSlice(customCredentialEntry);
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

    public final CharSequence getSubtitle() {
        return this.subtitle;
    }

    public final CharSequence getTitle() {
        return this.title;
    }

    @Override // androidx.credentials.provider.CredentialEntry
    public String getType() {
        return this.type;
    }

    public final CharSequence getTypeDisplayName() {
        return this.typeDisplayName;
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

    public CustomCredentialEntry(String str, CharSequence charSequence, PendingIntent pendingIntent, boolean z, CharSequence charSequence2, CharSequence charSequence3, Icon icon, Instant instant, BeginGetCredentialOption beginGetCredentialOption, boolean z5, CharSequence charSequence4, CharSequence charSequence5, boolean z10, boolean z11, boolean z12) {
        super(str, beginGetCredentialOption, charSequence4 == null ? charSequence : charSequence4, z5, charSequence5);
        this.type = str;
        this.title = charSequence;
        this.pendingIntent = pendingIntent;
        this.isAutoSelectAllowed = z;
        this.subtitle = charSequence2;
        this.typeDisplayName = charSequence3;
        this.icon = icon;
        this.lastUsedTime = instant;
        this.isCreatedFromSlice = z11;
        this.isDefaultIconFromSlice = z12;
        this.isAutoSelectAllowedFromOption = z10;
        if (getType().length() > 0) {
            if (charSequence.length() <= 0) {
                throw new IllegalArgumentException("title must not be empty");
            }
            return;
        }
        throw new IllegalArgumentException("type must not be empty");
    }

    public /* synthetic */ CustomCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetCredentialOption beginGetCredentialOption, CharSequence charSequence2, CharSequence charSequence3, Instant instant, Icon icon, boolean z, int i10, h hVar) {
        this(context, charSequence, pendingIntent, beginGetCredentialOption, (i10 & 16) != 0 ? null : charSequence2, (i10 & 32) != 0 ? null : charSequence3, (i10 & 64) != 0 ? null : instant, (i10 & 128) != 0 ? Icon.createWithResource(context, R.drawable.ic_other_sign_in) : icon, (i10 & 256) != 0 ? false : z);
    }

    @e
    public /* synthetic */ CustomCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetCredentialOption beginGetCredentialOption, CharSequence charSequence2, CharSequence charSequence3, Instant instant, Icon icon, boolean z) {
        this(beginGetCredentialOption.getType(), charSequence, pendingIntent, z, charSequence2, charSequence3, icon, instant, beginGetCredentialOption, false, null, null, false, false, false, 31744, null);
    }

    public /* synthetic */ CustomCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetCredentialOption beginGetCredentialOption, CharSequence charSequence2, CharSequence charSequence3, Instant instant, Icon icon, boolean z, CharSequence charSequence4, boolean z5, int i10, h hVar) {
        this(context, charSequence, pendingIntent, beginGetCredentialOption, (i10 & 16) != 0 ? null : charSequence2, (i10 & 32) != 0 ? null : charSequence3, (i10 & 64) != 0 ? null : instant, (i10 & 128) != 0 ? Icon.createWithResource(context, R.drawable.ic_other_sign_in) : icon, (i10 & 256) != 0 ? false : z, (i10 & 512) != 0 ? charSequence : charSequence4, (i10 & 1024) != 0 ? false : z5);
    }

    public CustomCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetCredentialOption beginGetCredentialOption, CharSequence charSequence2, CharSequence charSequence3, Instant instant, Icon icon, boolean z, CharSequence charSequence4, boolean z5) {
        this(beginGetCredentialOption.getType(), charSequence, pendingIntent, z, charSequence2, charSequence3, icon, instant, beginGetCredentialOption, z5, charSequence4.length() == 0 ? charSequence : charSequence4, null, false, false, false, 30720, null);
    }
}
