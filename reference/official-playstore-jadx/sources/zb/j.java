package zb;

import java.io.PrintStream;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends PrintStream {
    public final void i() {
        bc.a aVar = ((bc.c) ((PrintStream) this).out).f7350t;
        if (aVar != null) {
            aVar.run();
        }
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("AnsiPrintStream{type=");
        int i10 = ((bc.c) ((PrintStream) this).out).f7353w;
        sb2.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? i10 != 5 ? "null" : "Redirected" : "Emulation" : "VirtualTerminal" : "Unsupported" : "Native");
        sb2.append(", colors=");
        int i11 = ((bc.c) ((PrintStream) this).out).x;
        sb2.append(i11 != 1 ? i11 != 2 ? i11 != 3 ? "null" : "TrueColor" : "Colors256" : "Colors16");
        sb2.append(", mode=");
        int i12 = ((bc.c) ((PrintStream) this).out).f7354y;
        sb2.append(i12 != 1 ? i12 != 2 ? i12 != 3 ? "null" : "Force" : "Default" : "Strip");
        sb2.append(", resetAtUninstall=");
        return a0.c.m("}", ((bc.c) ((PrintStream) this).out).f7352v, sb2);
    }
}
