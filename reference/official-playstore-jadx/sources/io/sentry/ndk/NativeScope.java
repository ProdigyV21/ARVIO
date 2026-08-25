package io.sentry.ndk;

/* JADX INFO: loaded from: classes5.dex */
public final class NativeScope implements a {
    public static native void nativeAddBreadcrumb(String str, String str2, String str3, String str4, String str5, String str6);

    public static native void nativeClearAttachments();

    public static native void nativeRemoveExtra(String str);

    public static native void nativeRemoveUser();

    public static native void nativeSetExtra(String str, String str2);

    public static native void nativeSetTag(String str, String str2);

    public static native void nativeSetTrace(String str, String str2);

    public static native void nativeSetUser(String str, String str2, String str3, String str4);
}
