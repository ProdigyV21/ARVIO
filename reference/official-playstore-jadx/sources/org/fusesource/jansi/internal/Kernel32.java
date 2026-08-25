package org.fusesource.jansi.internal;

import ac.b;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Kernel32 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f20998a = 0;

    public static class CHAR_INFO {
        static {
            b.K();
            init();
        }

        private static native void init();
    }

    public static class CONSOLE_SCREEN_BUFFER_INFO {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public COORD f20999a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public COORD f21000b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public short f21001c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public SMALL_RECT f21002d;

        static {
            b.K();
            init();
        }

        private static native void init();
    }

    public static class COORD {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public short f21003a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public short f21004b;

        static {
            b.K();
            init();
        }

        private static native void init();

        public final COORD a() {
            COORD coord = new COORD();
            coord.f21003a = this.f21003a;
            coord.f21004b = this.f21004b;
            return coord;
        }
    }

    public static class SMALL_RECT {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public short f21005a;

        static {
            b.K();
            init();
        }

        private static native void init();
    }

    static {
        if (b.K()) {
            init();
        }
    }

    public static native int FillConsoleOutputAttribute(long j10, short s10, int i10, COORD coord, int[] iArr);

    public static native int FillConsoleOutputCharacterW(long j10, char c10, int i10, COORD coord, int[] iArr);

    public static native int FormatMessageW(int i10, long j10, int i11, int i12, byte[] bArr, int i13, long[] jArr);

    public static native int GetConsoleMode(long j10, int[] iArr);

    public static native int GetConsoleScreenBufferInfo(long j10, CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info);

    public static native int GetLastError();

    public static native long GetStdHandle(int i10);

    public static native int ScrollConsoleScreenBuffer(long j10, SMALL_RECT small_rect, SMALL_RECT small_rect2, COORD coord, CHAR_INFO char_info);

    public static native int SetConsoleCursorPosition(long j10, COORD coord);

    public static native int SetConsoleMode(long j10, int i10);

    public static native int SetConsoleTextAttribute(long j10, short s10);

    public static native int SetConsoleTitle(String str);

    private static native void init();
}
