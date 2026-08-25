package bc;

import java.io.IOException;
import java.io.OutputStream;
import org.fusesource.jansi.internal.Kernel32;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends a8.e {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final short[] f7359s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final short[] f7360t;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f7361m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Kernel32.CONSOLE_SCREEN_BUFFER_INFO f7362n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final short f7363o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f7364p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public short f7365q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public short f7366r;

    static {
        int i10 = Kernel32.f20998a;
        short s10 = (short) 0;
        f7359s = new short[]{0, 0, 0, s10, 0, s10, s10, s10};
        f7360t = new short[]{0, 0, 0, s10, 0, s10, s10, s10};
    }

    public g(f fVar, long j10) throws IOException {
        super(fVar, 7);
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
        console_screen_buffer_info.f20999a = new Kernel32.COORD();
        console_screen_buffer_info.f21000b = new Kernel32.COORD();
        console_screen_buffer_info.f21002d = new Kernel32.SMALL_RECT();
        this.f7362n = console_screen_buffer_info;
        this.f7365q = (short) -1;
        this.f7366r = (short) -1;
        this.f7361m = j10;
        z0();
        this.f7363o = console_screen_buffer_info.f21001c;
    }

    @Override // a8.e
    public final void P() throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        console_screen_buffer_info.f21001c = (short) ((console_screen_buffer_info.f21001c & (-256)) | this.f7363o);
        this.f7364p = false;
        x0();
    }

    @Override // a8.e
    public final void Q(String str) {
        Kernel32.SetConsoleTitle(str);
    }

    @Override // a8.e
    public final void S(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        console_screen_buffer_info.f21000b.f21004b = (short) Math.min(Math.max(0, console_screen_buffer_info.f20999a.f21004b - 1), console_screen_buffer_info.f21000b.f21004b + i10);
        y0();
    }

    @Override // a8.e
    public final void T(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        coord.f21003a = (short) 0;
        coord.f21004b = (short) Math.max((int) console_screen_buffer_info.f21002d.f21005a, coord.f21004b + i10);
        y0();
    }

    @Override // a8.e
    public final void U(int i10) throws IOException {
        z0();
        Kernel32.COORD coord = this.f7362n.f21000b;
        coord.f21003a = (short) Math.max(0, coord.f21003a - i10);
        y0();
    }

    @Override // a8.e
    public final void V(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        console_screen_buffer_info.f21002d.getClass();
        coord.f21003a = (short) Math.min((int) ((short) 0), console_screen_buffer_info.f21000b.f21003a + i10);
        y0();
    }

    @Override // a8.e
    public final void W(int i10, int i11) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        Kernel32.SMALL_RECT small_rect = console_screen_buffer_info.f21002d;
        coord.f21004b = (short) Math.max((int) small_rect.f21005a, Math.min((int) console_screen_buffer_info.f20999a.f21004b, (i10 + r3) - 1));
        Kernel32.COORD coord2 = console_screen_buffer_info.f21000b;
        small_rect.getClass();
        coord2.f21003a = (short) Math.max(0, Math.min((int) ((short) 0), i11 - 1));
        y0();
    }

    @Override // a8.e
    public final void X(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        console_screen_buffer_info.f21002d.getClass();
        coord.f21003a = (short) Math.max(0, Math.min((int) ((short) 0), i10 - 1));
        y0();
    }

    @Override // a8.e
    public final void Y(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        coord.f21004b = (short) Math.max((int) console_screen_buffer_info.f21002d.f21005a, coord.f21004b - i10);
        y0();
    }

    @Override // a8.e
    public final void Z(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        coord.f21003a = (short) 0;
        coord.f21004b = (short) Math.max((int) console_screen_buffer_info.f21002d.f21005a, coord.f21004b - i10);
        y0();
    }

    @Override // a8.e
    public final void a0() throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        short s10 = (short) ((console_screen_buffer_info.f21001c & (-241)) | (this.f7363o & 240));
        console_screen_buffer_info.f21001c = s10;
        int i10 = Kernel32.f20998a;
        console_screen_buffer_info.f21001c = s10;
        x0();
    }

    @Override // a8.e
    public final void b0() throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        short s10 = (short) ((console_screen_buffer_info.f21001c & (-16)) | (this.f7363o & 15));
        console_screen_buffer_info.f21001c = s10;
        int i10 = Kernel32.f20998a;
        console_screen_buffer_info.f21001c = s10;
        x0();
    }

    @Override // a8.e
    public final void c0(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.SMALL_RECT small_rect = console_screen_buffer_info.f21002d;
        Kernel32.SMALL_RECT small_rect2 = new Kernel32.SMALL_RECT();
        small_rect2.f21005a = small_rect.f21005a;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        small_rect2.f21005a = coord.f21004b;
        Kernel32.COORD coord2 = new Kernel32.COORD();
        coord2.f21003a = (short) 0;
        coord2.f21004b = (short) (coord.f21004b - i10);
        if (Kernel32.ScrollConsoleScreenBuffer(this.f7361m, small_rect2, small_rect2, coord2, new Kernel32.CHAR_INFO()) == 0) {
            throw new IOException(ac.b.F());
        }
    }

    @Override // a8.e
    public final void d0(int i10) throws IOException {
        z0();
        int[] iArr = new int[1];
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        if (i10 == 0) {
            short s10 = console_screen_buffer_info.f20999a.f21003a;
            Kernel32.COORD coord = console_screen_buffer_info.f21000b;
            int i11 = s10 - coord.f21003a;
            Kernel32.FillConsoleOutputAttribute(this.f7361m, console_screen_buffer_info.f21001c, i11, coord.a(), iArr);
            Kernel32.FillConsoleOutputCharacterW(this.f7361m, ' ', i11, console_screen_buffer_info.f21000b.a(), iArr);
            return;
        }
        if (i10 == 1) {
            Kernel32.COORD coordA = console_screen_buffer_info.f21000b.a();
            coordA.f21003a = (short) 0;
            Kernel32.FillConsoleOutputAttribute(this.f7361m, console_screen_buffer_info.f21001c, console_screen_buffer_info.f21000b.f21003a, coordA, iArr);
            Kernel32.FillConsoleOutputCharacterW(this.f7361m, ' ', console_screen_buffer_info.f21000b.f21003a, coordA, iArr);
            return;
        }
        if (i10 != 2) {
            return;
        }
        Kernel32.COORD coordA2 = console_screen_buffer_info.f21000b.a();
        coordA2.f21003a = (short) 0;
        Kernel32.FillConsoleOutputAttribute(this.f7361m, console_screen_buffer_info.f21001c, console_screen_buffer_info.f20999a.f21003a, coordA2, iArr);
        Kernel32.FillConsoleOutputCharacterW(this.f7361m, ' ', console_screen_buffer_info.f20999a.f21003a, coordA2, iArr);
    }

    @Override // a8.e
    public final void e0(int i10) throws IOException {
        z0();
        int[] iArr = new int[1];
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        if (i10 == 0) {
            Kernel32.SMALL_RECT small_rect = console_screen_buffer_info.f21002d;
            Kernel32.COORD coord = console_screen_buffer_info.f21000b;
            small_rect.getClass();
            int i11 = 0 - coord.f21004b;
            short s10 = console_screen_buffer_info.f20999a.f21003a;
            int i12 = (s10 - coord.f21003a) + (i11 * s10);
            Kernel32.FillConsoleOutputAttribute(this.f7361m, console_screen_buffer_info.f21001c, i12, coord.a(), iArr);
            Kernel32.FillConsoleOutputCharacterW(this.f7361m, ' ', i12, coord.a(), iArr);
            return;
        }
        if (i10 == 1) {
            Kernel32.COORD coord2 = new Kernel32.COORD();
            coord2.f21003a = (short) 0;
            short s11 = console_screen_buffer_info.f21002d.f21005a;
            coord2.f21004b = s11;
            Kernel32.COORD coord3 = console_screen_buffer_info.f21000b;
            int i13 = ((coord3.f21004b - s11) * console_screen_buffer_info.f20999a.f21003a) + coord3.f21003a;
            Kernel32.FillConsoleOutputAttribute(this.f7361m, console_screen_buffer_info.f21001c, i13, coord2, iArr);
            Kernel32.FillConsoleOutputCharacterW(this.f7361m, ' ', i13, coord2, iArr);
            return;
        }
        if (i10 != 2) {
            return;
        }
        Kernel32.COORD coord4 = new Kernel32.COORD();
        coord4.f21003a = (short) 0;
        short s12 = console_screen_buffer_info.f21002d.f21005a;
        coord4.f21004b = s12;
        int i14 = ((short) (0 - s12)) * console_screen_buffer_info.f20999a.f21003a;
        Kernel32.FillConsoleOutputAttribute(this.f7361m, console_screen_buffer_info.f21001c, i14, coord4, iArr);
        Kernel32.FillConsoleOutputCharacterW(this.f7361m, ' ', i14, coord4, iArr);
    }

    @Override // a8.e
    public final void g0(int i10) throws IOException {
        z0();
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        Kernel32.SMALL_RECT small_rect = console_screen_buffer_info.f21002d;
        Kernel32.SMALL_RECT small_rect2 = new Kernel32.SMALL_RECT();
        small_rect2.f21005a = small_rect.f21005a;
        Kernel32.COORD coord = console_screen_buffer_info.f21000b;
        small_rect2.f21005a = coord.f21004b;
        Kernel32.COORD coord2 = new Kernel32.COORD();
        coord2.f21003a = (short) 0;
        coord2.f21004b = (short) (coord.f21004b + i10);
        if (Kernel32.ScrollConsoleScreenBuffer(this.f7361m, small_rect2, small_rect2, coord2, new Kernel32.CHAR_INFO()) == 0) {
            throw new IOException(ac.b.F());
        }
    }

    @Override // a8.e
    public final void i0() throws IOException {
        if (this.f7365q == -1 || this.f7366r == -1) {
            return;
        }
        ((OutputStream) this.f183l).flush();
        Kernel32.COORD coord = this.f7362n.f21000b;
        coord.f21003a = this.f7365q;
        coord.f21004b = this.f7366r;
        y0();
    }

    @Override // a8.e
    public final void j0() throws IOException {
        z0();
        Kernel32.COORD coord = this.f7362n.f21000b;
        this.f7365q = coord.f21003a;
        this.f7366r = coord.f21004b;
    }

    @Override // a8.e
    public final void k0(int i10) throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        if (i10 == 1) {
            short s10 = console_screen_buffer_info.f21001c;
            int i11 = Kernel32.f20998a;
            console_screen_buffer_info.f21001c = s10;
            x0();
            return;
        }
        if (i10 == 4) {
            short s11 = console_screen_buffer_info.f21001c;
            int i12 = Kernel32.f20998a;
            console_screen_buffer_info.f21001c = s11;
            x0();
            return;
        }
        if (i10 == 7) {
            this.f7364p = true;
            x0();
            return;
        }
        if (i10 == 22) {
            short s12 = console_screen_buffer_info.f21001c;
            int i13 = Kernel32.f20998a;
            console_screen_buffer_info.f21001c = s12;
            x0();
            return;
        }
        if (i10 != 24) {
            if (i10 != 27) {
                return;
            }
            this.f7364p = false;
            x0();
            return;
        }
        short s13 = console_screen_buffer_info.f21001c;
        int i14 = Kernel32.f20998a;
        console_screen_buffer_info.f21001c = s13;
        x0();
    }

    @Override // a8.e
    public final void l0(int i10, boolean z) throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        short s10 = (short) (f7360t[i10] | (console_screen_buffer_info.f21001c & (-113)));
        console_screen_buffer_info.f21001c = s10;
        if (z) {
            int i11 = Kernel32.f20998a;
            console_screen_buffer_info.f21001c = s10;
        }
        x0();
    }

    @Override // a8.e
    public final void m0(int i10) throws IOException {
        if (i10 >= 16) {
            int[] iArr = d.f7355a;
            i10 = d.d(iArr[i10], 16, iArr);
        }
        l0(i10 >= 8 ? i10 - 8 : i10, i10 >= 8);
    }

    @Override // a8.e
    public final void n0(int i10, int i11, int i12) throws IOException {
        int iE = d.e(i10, i11, i12, 16);
        l0(iE >= 8 ? iE - 8 : iE, iE >= 8);
    }

    @Override // a8.e
    public final void o0(int i10, boolean z) throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        short s10 = (short) (f7359s[i10] | (console_screen_buffer_info.f21001c & (-8)));
        console_screen_buffer_info.f21001c = s10;
        if (z) {
            int i11 = Kernel32.f20998a;
            console_screen_buffer_info.f21001c = s10;
        }
        x0();
    }

    @Override // a8.e
    public final void p0(int i10) throws IOException {
        if (i10 >= 16) {
            int[] iArr = d.f7355a;
            i10 = d.d(iArr[i10], 16, iArr);
        }
        o0(i10 >= 8 ? i10 - 8 : i10, i10 >= 8);
    }

    @Override // a8.e
    public final void q0(int i10, int i11, int i12) throws IOException {
        int iE = d.e(i10, i11, i12, 16);
        o0(iE >= 8 ? iE - 8 : iE, iE >= 8);
    }

    public final void x0() throws IOException {
        ((OutputStream) this.f183l).flush();
        short s10 = this.f7362n.f21001c;
        if (this.f7364p) {
            int i10 = (s10 & 240) >> 4;
            s10 = (short) ((s10 & 65280) | ((s10 & 15) << 4) | i10);
        }
        if (Kernel32.SetConsoleTextAttribute(this.f7361m, s10) == 0) {
            throw new IOException(ac.b.F());
        }
    }

    public final void y0() throws IOException {
        if (Kernel32.SetConsoleCursorPosition(this.f7361m, this.f7362n.f21000b.a()) == 0) {
            throw new IOException(ac.b.F());
        }
    }

    public final void z0() throws IOException {
        ((OutputStream) this.f183l).flush();
        long j10 = this.f7361m;
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.f7362n;
        if (Kernel32.GetConsoleScreenBufferInfo(j10, console_screen_buffer_info) == 0) {
            throw new IOException("Could not get the screen info: " + ac.b.F());
        }
        if (this.f7364p) {
            short s10 = console_screen_buffer_info.f21001c;
            console_screen_buffer_info.f21001c = (short) ((s10 & 65280) | ((s10 & 15) << 4) | ((s10 & 240) >> 4));
        }
    }
}
