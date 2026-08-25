package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0012\bÇ\u0002\u0018\u00002\u00020\u0001:\u00019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007R\u0013\u0010#\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b$\u0010\u0007R\u0013\u0010%\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b&\u0010\u0007R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u0011\u0010-\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b.\u0010*R\u0011\u0010/\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b0\u0010*R\u0011\u00101\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b2\u0010*R\u0011\u00103\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b4\u0010*R\u0011\u00105\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b6\u0010*R\u0011\u00107\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b8\u0010*¨\u0006:"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveColors;", "", "<init>", "()V", "Bg", "Landroidx/compose/ui/graphics/Color;", "getBg-0d7_KjU", "()J", "J", "Panel", "getPanel-0d7_KjU", "PanelDeep", "getPanelDeep-0d7_KjU", "PanelRaised", "getPanelRaised-0d7_KjU", "RowStripe", "getRowStripe-0d7_KjU", "Divider", "getDivider-0d7_KjU", "DividerStrong", "getDividerStrong-0d7_KjU", "Fg", "getFg-0d7_KjU", "FgDim", "getFgDim-0d7_KjU", "FgMute", "getFgMute-0d7_KjU", "Accent", "getAccent-0d7_KjU", "AccentDim", "getAccentDim-0d7_KjU", "FocusBg", "getFocusBg-0d7_KjU", "FocusRing", "getFocusRing-0d7_KjU", "LiveRed", "getLiveRed-0d7_KjU", "Online", "getOnline-0d7_KjU", "BrandNews", "Lcom/arflix/tv/ui/screens/tv/live/LiveColors$Brand;", "getBrandNews", "()Lcom/arflix/tv/ui/screens/tv/live/LiveColors$Brand;", "BrandSport", "getBrandSport", "BrandMovies", "getBrandMovies", "BrandSeries", "getBrandSeries", "BrandKids", "getBrandKids", "BrandMusic", "getBrandMusic", "BrandDocs", "getBrandDocs", "BrandGeneral", "getBrandGeneral", "Brand", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveColors {
    public static final int $stable = 0;
    public static final LiveColors INSTANCE = new LiveColors();
    private static final long Bg = ColorKt.Color(4278650633L);
    private static final long Panel = ColorKt.Color(4279374617L);
    private static final long PanelDeep = ColorKt.Color(4278913807L);
    private static final long PanelRaised = ColorKt.Color(4279967013L);
    private static final long RowStripe = ColorKt.Color(4279045393L);
    private static final long Divider = ColorKt.Color(2569743670L);
    private static final long DividerStrong = ColorKt.Color(3862115650L);
    private static final long Fg = ColorKt.Color(4294309368L);
    private static final long FgDim = ColorKt.Color(4290098878L);
    private static final long FgMute = ColorKt.Color(4286414470L);
    private static final long Accent = ColorKt.Color(4283400112L);
    private static final long AccentDim = ColorKt.Color(4281685368L);
    private static final long FocusBg = ColorKt.Color(642744240);
    private static final long FocusRing = ColorKt.Color(4294967295L);
    private static final long LiveRed = ColorKt.Color(4294916912L);
    private static final long Online = ColorKt.Color(4283096704L);
    private static final Brand BrandNews = new Brand(ColorKt.Color(4287246127L), ColorKt.Color(4294830036L), null);
    private static final Brand BrandSport = new Brand(ColorKt.Color(4278935857L), ColorKt.Color(4293591025L), null);
    private static final Brand BrandMovies = new Brand(ColorKt.Color(4279900718L), ColorKt.Color(4294296171L), null);
    private static final Brand BrandSeries = new Brand(ColorKt.Color(4281996626L), ColorKt.Color(4293513983L), null);
    private static final Brand BrandKids = new Brand(ColorKt.Color(4294160698L), ColorKt.Color(4279898888L), null);
    private static final Brand BrandMusic = new Brand(ColorKt.Color(4280953454L), ColorKt.Color(4291351807L), null);
    private static final Brand BrandDocs = new Brand(ColorKt.Color(4280106810L), ColorKt.Color(4291815907L), null);
    private static final Brand BrandGeneral = new Brand(ColorKt.Color(4279970650L), ColorKt.Color(4293455867L), null);

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\bJ$\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001b\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveColors$Brand;", "", "Landroidx/compose/ui/graphics/Color;", "bg", "fg", "<init>", "(JJLkotlin/jvm/internal/h;)V", "component1-0d7_KjU", "()J", "component1", "component2-0d7_KjU", "component2", "copy--OWjLjI", "(JJ)Lcom/arflix/tv/ui/screens/tv/live/LiveColors$Brand;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getBg-0d7_KjU", "getFg-0d7_KjU", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Brand {
        public static final int $stable = 0;
        private final long bg;
        private final long fg;

        public /* synthetic */ Brand(long j10, long j11, kotlin.jvm.internal.h hVar) {
            this(j10, j11);
        }

        /* JADX INFO: renamed from: copy--OWjLjI$default, reason: not valid java name */
        public static /* synthetic */ Brand m6413copyOWjLjI$default(Brand brand, long j10, long j11, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = brand.bg;
            }
            if ((i10 & 2) != 0) {
                j11 = brand.fg;
            }
            return brand.m6416copyOWjLjI(j10, j11);
        }

        /* JADX INFO: renamed from: component1-0d7_KjU, reason: not valid java name and from getter */
        public final long getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
        public final long getFg() {
            return this.fg;
        }

        /* JADX INFO: renamed from: copy--OWjLjI, reason: not valid java name */
        public final Brand m6416copyOWjLjI(long bg, long fg) {
            return new Brand(bg, fg, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Brand)) {
                return false;
            }
            Brand brand = (Brand) other;
            return Color.m3473equalsimpl0(this.bg, brand.bg) && Color.m3473equalsimpl0(this.fg, brand.fg);
        }

        /* JADX INFO: renamed from: getBg-0d7_KjU, reason: not valid java name */
        public final long m6417getBg0d7_KjU() {
            return this.bg;
        }

        /* JADX INFO: renamed from: getFg-0d7_KjU, reason: not valid java name */
        public final long m6418getFg0d7_KjU() {
            return this.fg;
        }

        public int hashCode() {
            return Color.m3479hashCodeimpl(this.fg) + (Color.m3479hashCodeimpl(this.bg) * 31);
        }

        public String toString() {
            return androidx.fragment.app.a2.m("Brand(bg=", Color.m3480toStringimpl(this.bg), ", fg=", Color.m3480toStringimpl(this.fg), ")");
        }

        private Brand(long j10, long j11) {
            this.bg = j10;
            this.fg = j11;
        }
    }

    private LiveColors() {
    }

    /* JADX INFO: renamed from: getAccent-0d7_KjU, reason: not valid java name */
    public final long m6397getAccent0d7_KjU() {
        return Accent;
    }

    /* JADX INFO: renamed from: getAccentDim-0d7_KjU, reason: not valid java name */
    public final long m6398getAccentDim0d7_KjU() {
        return AccentDim;
    }

    /* JADX INFO: renamed from: getBg-0d7_KjU, reason: not valid java name */
    public final long m6399getBg0d7_KjU() {
        return Bg;
    }

    public final Brand getBrandDocs() {
        return BrandDocs;
    }

    public final Brand getBrandGeneral() {
        return BrandGeneral;
    }

    public final Brand getBrandKids() {
        return BrandKids;
    }

    public final Brand getBrandMovies() {
        return BrandMovies;
    }

    public final Brand getBrandMusic() {
        return BrandMusic;
    }

    public final Brand getBrandNews() {
        return BrandNews;
    }

    public final Brand getBrandSeries() {
        return BrandSeries;
    }

    public final Brand getBrandSport() {
        return BrandSport;
    }

    /* JADX INFO: renamed from: getDivider-0d7_KjU, reason: not valid java name */
    public final long m6400getDivider0d7_KjU() {
        return Divider;
    }

    /* JADX INFO: renamed from: getDividerStrong-0d7_KjU, reason: not valid java name */
    public final long m6401getDividerStrong0d7_KjU() {
        return DividerStrong;
    }

    /* JADX INFO: renamed from: getFg-0d7_KjU, reason: not valid java name */
    public final long m6402getFg0d7_KjU() {
        return Fg;
    }

    /* JADX INFO: renamed from: getFgDim-0d7_KjU, reason: not valid java name */
    public final long m6403getFgDim0d7_KjU() {
        return FgDim;
    }

    /* JADX INFO: renamed from: getFgMute-0d7_KjU, reason: not valid java name */
    public final long m6404getFgMute0d7_KjU() {
        return FgMute;
    }

    /* JADX INFO: renamed from: getFocusBg-0d7_KjU, reason: not valid java name */
    public final long m6405getFocusBg0d7_KjU() {
        return FocusBg;
    }

    /* JADX INFO: renamed from: getFocusRing-0d7_KjU, reason: not valid java name */
    public final long m6406getFocusRing0d7_KjU() {
        return FocusRing;
    }

    /* JADX INFO: renamed from: getLiveRed-0d7_KjU, reason: not valid java name */
    public final long m6407getLiveRed0d7_KjU() {
        return LiveRed;
    }

    /* JADX INFO: renamed from: getOnline-0d7_KjU, reason: not valid java name */
    public final long m6408getOnline0d7_KjU() {
        return Online;
    }

    /* JADX INFO: renamed from: getPanel-0d7_KjU, reason: not valid java name */
    public final long m6409getPanel0d7_KjU() {
        return Panel;
    }

    /* JADX INFO: renamed from: getPanelDeep-0d7_KjU, reason: not valid java name */
    public final long m6410getPanelDeep0d7_KjU() {
        return PanelDeep;
    }

    /* JADX INFO: renamed from: getPanelRaised-0d7_KjU, reason: not valid java name */
    public final long m6411getPanelRaised0d7_KjU() {
        return PanelRaised;
    }

    /* JADX INFO: renamed from: getRowStripe-0d7_KjU, reason: not valid java name */
    public final long m6412getRowStripe0d7_KjU() {
        return RowStripe;
    }
}
