package com.arflix.tv.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\rJ\u0010\u0010\u0012\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\rJL\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0011R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b(\u0010\rR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b)\u0010\r¨\u0006*"}, d2 = {"Lcom/arflix/tv/ui/components/Particle;", "", "", "x", "y", "radius", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "speed", "angle", "<init>", "(FFFJFFLkotlin/jvm/internal/h;)V", "component1", "()F", "component2", "component3", "component4-0d7_KjU", "()J", "component4", "component5", "component6", "copy-Bx497Mc", "(FFFJFF)Lcom/arflix/tv/ui/components/Particle;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getX", "getY", "getRadius", "J", "getColor-0d7_KjU", "getSpeed", "getAngle", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Particle {
    public static final int $stable = 0;
    private final float angle;
    private final long color;
    private final float radius;
    private final float speed;
    private final float x;
    private final float y;

    public /* synthetic */ Particle(float f10, float f11, float f12, long j10, float f13, float f14, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, j10, f13, f14);
    }

    /* JADX INFO: renamed from: copy-Bx497Mc$default, reason: not valid java name */
    public static /* synthetic */ Particle m6171copyBx497Mc$default(Particle particle, float f10, float f11, float f12, long j10, float f13, float f14, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = particle.x;
        }
        if ((i10 & 2) != 0) {
            f11 = particle.y;
        }
        if ((i10 & 4) != 0) {
            f12 = particle.radius;
        }
        if ((i10 & 8) != 0) {
            j10 = particle.color;
        }
        if ((i10 & 16) != 0) {
            f13 = particle.speed;
        }
        if ((i10 & 32) != 0) {
            f14 = particle.angle;
        }
        long j11 = j10;
        float f15 = f12;
        return particle.m6173copyBx497Mc(f10, f11, f15, j11, f13, f14);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getY() {
        return this.y;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getRadius() {
        return this.radius;
    }

    /* JADX INFO: renamed from: component4-0d7_KjU, reason: not valid java name and from getter */
    public final long getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getSpeed() {
        return this.speed;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getAngle() {
        return this.angle;
    }

    /* JADX INFO: renamed from: copy-Bx497Mc, reason: not valid java name */
    public final Particle m6173copyBx497Mc(float x, float y10, float radius, long color, float speed, float angle) {
        return new Particle(x, y10, radius, color, speed, angle, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Particle)) {
            return false;
        }
        Particle particle = (Particle) other;
        return Float.compare(this.x, particle.x) == 0 && Float.compare(this.y, particle.y) == 0 && Float.compare(this.radius, particle.radius) == 0 && Color.m3473equalsimpl0(this.color, particle.color) && Float.compare(this.speed, particle.speed) == 0 && Float.compare(this.angle, particle.angle) == 0;
    }

    public final float getAngle() {
        return this.angle;
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m6174getColor0d7_KjU() {
        return this.color;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.angle) + a0.c.b(this.speed, androidx.compose.foundation.c.a(a0.c.b(this.radius, a0.c.b(this.y, Float.floatToIntBits(this.x) * 31, 31), 31), 31, this.color), 31);
    }

    public String toString() {
        return "Particle(x=" + this.x + ", y=" + this.y + ", radius=" + this.radius + ", color=" + Color.m3480toStringimpl(this.color) + ", speed=" + this.speed + ", angle=" + this.angle + ")";
    }

    private Particle(float f10, float f11, float f12, long j10, float f13, float f14) {
        this.x = f10;
        this.y = f11;
        this.radius = f12;
        this.color = j10;
        this.speed = f13;
        this.angle = f14;
    }
}
