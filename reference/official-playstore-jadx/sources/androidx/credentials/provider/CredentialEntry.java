package androidx.credentials.provider;

import android.app.slice.Slice;
import android.app.slice.SliceSpec;
import android.os.Build;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b&\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B3\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/credentials/provider/CredentialEntry;", "", LinkHeader.Parameters.Type, "", "beginGetCredentialOption", "Landroidx/credentials/provider/BeginGetCredentialOption;", "entryGroupId", "", "isDefaultIconPreferredAsSingleProvider", "", "affiliatedDomain", "(Ljava/lang/String;Landroidx/credentials/provider/BeginGetCredentialOption;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;)V", "getAffiliatedDomain", "()Ljava/lang/CharSequence;", "getBeginGetCredentialOption", "()Landroidx/credentials/provider/BeginGetCredentialOption;", "getEntryGroupId", "()Z", "getType", "()Ljava/lang/String;", "Api34Impl", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CredentialEntry {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CharSequence affiliatedDomain;
    private final BeginGetCredentialOption beginGetCredentialOption;
    private final CharSequence entryGroupId;
    private final boolean isDefaultIconPreferredAsSingleProvider;
    private final String type;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/provider/CredentialEntry$Api34Impl;", "", "()V", "fromCredentialEntry", "Landroidx/credentials/provider/CredentialEntry;", "credentialEntry", "Landroid/service/credentials/CredentialEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @n
        public static final CredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
            return CredentialEntry.INSTANCE.fromSlice$credentials_release(credentialEntry.getSlice());
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"Landroidx/credentials/provider/CredentialEntry$Companion;", "", "()V", "fromCredentialEntry", "Landroidx/credentials/provider/CredentialEntry;", "credentialEntry", "Landroid/service/credentials/CredentialEntry;", "fromSlice", "slice", "Landroid/app/slice/Slice;", "fromSlice$credentials_release", "toSlice", "entry", "toSlice$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final CredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromCredentialEntry(credentialEntry);
            }
            return null;
        }

        @n
        public final CredentialEntry fromSlice$credentials_release(Slice slice) {
            try {
                SliceSpec spec = slice.getSpec();
                String type = spec != null ? spec.getType() : null;
                return p.a(type, PasswordCredential.TYPE_PASSWORD_CREDENTIAL) ? PasswordCredentialEntry.INSTANCE.fromSlice(slice) : p.a(type, PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL) ? PublicKeyCredentialEntry.INSTANCE.fromSlice(slice) : CustomCredentialEntry.INSTANCE.fromSlice(slice);
            } catch (Exception unused) {
                return CustomCredentialEntry.INSTANCE.fromSlice(slice);
            }
        }

        @n
        public final Slice toSlice$credentials_release(CredentialEntry entry) {
            if (entry instanceof PasswordCredentialEntry) {
                return PasswordCredentialEntry.INSTANCE.toSlice((PasswordCredentialEntry) entry);
            }
            if (entry instanceof PublicKeyCredentialEntry) {
                return PublicKeyCredentialEntry.INSTANCE.toSlice((PublicKeyCredentialEntry) entry);
            }
            if (entry instanceof CustomCredentialEntry) {
                return CustomCredentialEntry.INSTANCE.toSlice((CustomCredentialEntry) entry);
            }
            return null;
        }

        private Companion() {
        }
    }

    public CredentialEntry(String str, BeginGetCredentialOption beginGetCredentialOption, CharSequence charSequence, boolean z, CharSequence charSequence2) {
        this.type = str;
        this.beginGetCredentialOption = beginGetCredentialOption;
        this.entryGroupId = charSequence;
        this.isDefaultIconPreferredAsSingleProvider = z;
        this.affiliatedDomain = charSequence2;
    }

    @n
    public static final CredentialEntry fromCredentialEntry(android.service.credentials.CredentialEntry credentialEntry) {
        return INSTANCE.fromCredentialEntry(credentialEntry);
    }

    public final CharSequence getAffiliatedDomain() {
        return this.affiliatedDomain;
    }

    public final BeginGetCredentialOption getBeginGetCredentialOption() {
        return this.beginGetCredentialOption;
    }

    public final CharSequence getEntryGroupId() {
        return this.entryGroupId;
    }

    public String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isDefaultIconPreferredAsSingleProvider, reason: from getter */
    public final boolean getIsDefaultIconPreferredAsSingleProvider() {
        return this.isDefaultIconPreferredAsSingleProvider;
    }

    public /* synthetic */ CredentialEntry(String str, BeginGetCredentialOption beginGetCredentialOption, CharSequence charSequence, boolean z, CharSequence charSequence2, int i10, h hVar) {
        this(str, beginGetCredentialOption, charSequence, z, (i10 & 16) != 0 ? null : charSequence2);
    }
}
