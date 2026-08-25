package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
class ConnectionRequest {
    public final Bundle connectionHints;
    public final int controllerInterfaceVersion;
    public final int libraryVersion;
    public final int maxCommandsForMediaItems;
    public final String packageName;
    public final int pid;
    private static final String FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(0);
    private static final String FIELD_PACKAGE_NAME = Util.intToStringMaxRadix(1);
    private static final String FIELD_PID = Util.intToStringMaxRadix(2);
    private static final String FIELD_CONNECTION_HINTS = Util.intToStringMaxRadix(3);
    private static final String FIELD_CONTROLLER_INTERFACE_VERSION = Util.intToStringMaxRadix(4);
    private static final String FIELD_MAX_COMMANDS_FOR_MEDIA_ITEM = Util.intToStringMaxRadix(5);

    public ConnectionRequest(String str, int i10, Bundle bundle, int i11) {
        this(MediaLibraryInfo.VERSION_INT, 8, str, i10, new Bundle(bundle), i11);
    }

    public static ConnectionRequest fromBundle(Bundle bundle) {
        int i10 = bundle.getInt(FIELD_LIBRARY_VERSION, 0);
        int i11 = bundle.getInt(FIELD_CONTROLLER_INTERFACE_VERSION, 0);
        String string = bundle.getString(FIELD_PACKAGE_NAME);
        string.getClass();
        String str = FIELD_PID;
        ac.b.j(bundle.containsKey(str));
        int i12 = bundle.getInt(str);
        Bundle bundle2 = bundle.getBundle(FIELD_CONNECTION_HINTS);
        int i13 = bundle.getInt(FIELD_MAX_COMMANDS_FOR_MEDIA_ITEM, 0);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new ConnectionRequest(i10, i11, string, i12, bundle2, i13);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        bundle.putString(FIELD_PACKAGE_NAME, this.packageName);
        bundle.putInt(FIELD_PID, this.pid);
        bundle.putBundle(FIELD_CONNECTION_HINTS, this.connectionHints);
        bundle.putInt(FIELD_CONTROLLER_INTERFACE_VERSION, this.controllerInterfaceVersion);
        bundle.putInt(FIELD_MAX_COMMANDS_FOR_MEDIA_ITEM, this.maxCommandsForMediaItems);
        return bundle;
    }

    private ConnectionRequest(int i10, int i11, String str, int i12, Bundle bundle, int i13) {
        this.libraryVersion = i10;
        this.controllerInterfaceVersion = i11;
        this.packageName = str;
        this.pid = i12;
        this.connectionHints = bundle;
        this.maxCommandsForMediaItems = i13;
    }
}
