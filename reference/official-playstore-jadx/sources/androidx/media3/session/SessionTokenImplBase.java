package androidx.media3.session;

import android.content.ComponentName;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.media3.common.util.Util;
import androidx.media3.session.SessionToken;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
final class SessionTokenImplBase implements SessionToken.SessionTokenImpl {
    private final ComponentName componentName;
    private final Bundle extras;
    private final IBinder iSession;
    private final int interfaceVersion;
    private final int libraryVersion;
    private final String packageName;
    private final MediaSession.Token platformToken;
    private final String serviceName;
    private final int type;
    private final int uid;
    private static final String FIELD_UID = Util.intToStringMaxRadix(0);
    private static final String FIELD_TYPE = Util.intToStringMaxRadix(1);
    private static final String FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(2);
    private static final String FIELD_PACKAGE_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_SERVICE_NAME = Util.intToStringMaxRadix(4);
    private static final String FIELD_COMPONENT_NAME = Util.intToStringMaxRadix(5);
    private static final String FIELD_ISESSION = Util.intToStringMaxRadix(6);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(7);
    private static final String FIELD_INTERFACE_VERSION = Util.intToStringMaxRadix(8);
    private static final String FIELD_PLATFORM_TOKEN = Util.intToStringMaxRadix(9);

    private SessionTokenImplBase(int i10, int i11, int i12, int i13, String str, String str2, ComponentName componentName, IBinder iBinder, Bundle bundle, MediaSession.Token token) {
        this.uid = i10;
        this.type = i11;
        this.libraryVersion = i12;
        this.interfaceVersion = i13;
        this.packageName = str;
        this.serviceName = str2;
        this.componentName = componentName;
        this.iSession = iBinder;
        this.extras = bundle;
        this.platformToken = token;
    }

    public static SessionTokenImplBase fromBundle(Bundle bundle, MediaSession.Token token) {
        String str = FIELD_UID;
        ac.b.k(bundle.containsKey(str), "uid should be set.");
        int i10 = bundle.getInt(str);
        String str2 = FIELD_TYPE;
        ac.b.k(bundle.containsKey(str2), "type should be set.");
        int i11 = bundle.getInt(str2);
        int i12 = bundle.getInt(FIELD_LIBRARY_VERSION, 1000000);
        int i13 = bundle.getInt(FIELD_INTERFACE_VERSION, 0);
        String string = bundle.getString(FIELD_PACKAGE_NAME);
        ac.b.k(!TextUtils.isEmpty(string), "package name should be set.");
        String string2 = bundle.getString(FIELD_SERVICE_NAME, "");
        IBinder binder = bundle.getBinder(FIELD_ISESSION);
        ComponentName componentName = (ComponentName) bundle.getParcelable(FIELD_COMPONENT_NAME);
        Bundle bundle2 = bundle.getBundle(FIELD_EXTRAS);
        MediaSession.Token token2 = (MediaSession.Token) bundle.getParcelable(FIELD_PLATFORM_TOKEN);
        MediaSession.Token token3 = token2 != null ? token2 : token;
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new SessionTokenImplBase(i10, i11, i12, i13, string, string2, componentName, binder, bundle2, token3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplBase)) {
            return false;
        }
        SessionTokenImplBase sessionTokenImplBase = (SessionTokenImplBase) obj;
        return this.uid == sessionTokenImplBase.uid && this.type == sessionTokenImplBase.type && this.libraryVersion == sessionTokenImplBase.libraryVersion && this.interfaceVersion == sessionTokenImplBase.interfaceVersion && TextUtils.equals(this.packageName, sessionTokenImplBase.packageName) && TextUtils.equals(this.serviceName, sessionTokenImplBase.serviceName) && Objects.equals(this.componentName, sessionTokenImplBase.componentName) && Objects.equals(this.iSession, sessionTokenImplBase.iSession) && Objects.equals(this.platformToken, sessionTokenImplBase.platformToken);
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Object getBinder() {
        return this.iSession;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public ComponentName getComponentName() {
        return this.componentName;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Bundle getExtras() {
        return new Bundle(this.extras);
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getInterfaceVersion() {
        return this.interfaceVersion;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getLibraryVersion() {
        return this.libraryVersion;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public String getPackageName() {
        return this.packageName;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public MediaSession.Token getPlatformToken() {
        return this.platformToken;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public String getServiceName() {
        return this.serviceName;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getType() {
        return this.type;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getUid() {
        return this.uid;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.uid), Integer.valueOf(this.type), Integer.valueOf(this.libraryVersion), Integer.valueOf(this.interfaceVersion), this.packageName, this.serviceName, this.componentName, this.iSession, this.platformToken);
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public boolean isLegacySession() {
        return false;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_UID, this.uid);
        bundle.putInt(FIELD_TYPE, this.type);
        bundle.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        bundle.putString(FIELD_PACKAGE_NAME, this.packageName);
        bundle.putString(FIELD_SERVICE_NAME, this.serviceName);
        bundle.putBinder(FIELD_ISESSION, this.iSession);
        bundle.putParcelable(FIELD_COMPONENT_NAME, this.componentName);
        bundle.putBundle(FIELD_EXTRAS, this.extras);
        bundle.putInt(FIELD_INTERFACE_VERSION, this.interfaceVersion);
        MediaSession.Token token = this.platformToken;
        if (token != null) {
            bundle.putParcelable(FIELD_PLATFORM_TOKEN, token);
        }
        return bundle;
    }

    public String toString() {
        return "SessionToken {pkg=" + this.packageName + " type=" + this.type + " libraryVersion=" + this.libraryVersion + " interfaceVersion=" + this.interfaceVersion + " service=" + this.serviceName + " IMediaSession=" + this.iSession + " extras=" + this.extras + "}";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SessionTokenImplBase(int i10, int i11, int i12, int i13, String str, IMediaSession iMediaSession, Bundle bundle, MediaSession.Token token) {
        str.getClass();
        IBinder iBinderAsBinder = iMediaSession.asBinder();
        bundle.getClass();
        this(i10, i11, i12, i13, str, "", null, iBinderAsBinder, bundle, token);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionTokenImplBase(ComponentName componentName, int i10, int i11) {
        this(i10, i11, 1000000, 0, componentName.getPackageName(), componentName.getClassName(), componentName, null, Bundle.EMPTY, null);
        componentName.getClass();
    }
}
