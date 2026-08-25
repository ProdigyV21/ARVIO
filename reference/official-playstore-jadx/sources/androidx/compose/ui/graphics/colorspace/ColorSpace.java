package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0012\b&\u0018\u0000 @2\u00020\u0001:\u0001@B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\nJ\u0019\u0010\r\u001a\u00020\f2\b\b\u0001\u0010\u000b\u001a\u00020\u0006H&¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\f2\b\b\u0001\u0010\u000b\u001a\u00020\u0006H&¢\u0006\u0004\b\u000f\u0010\u000eJ'\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0014\u001a\u00020\u00132\b\b\u0001\u0010\u0016\u001a\u00020\u0013H'¢\u0006\u0004\b\u0014\u0010\u0017J'\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0010¢\u0006\u0004\b\u001c\u0010\u001dJ'\u0010!\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0010¢\u0006\u0004\b\u001f\u0010 J=\u0010*\u001a\u00020'2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u0000H\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)J'\u0010+\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\fH\u0007¢\u0006\u0004\b+\u0010\u0015J\u0019\u0010+\u001a\u00020\u00132\b\b\u0001\u0010\u0016\u001a\u00020\u0013H'¢\u0006\u0004\b+\u0010\u0017J\u000f\u0010,\u001a\u00020\u0002H\u0016¢\u0006\u0004\b,\u0010-J\u001a\u00100\u001a\u00020/2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\u0006H\u0016¢\u0006\u0004\b2\u00103R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00104\u001a\u0004\b5\u0010-R\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u00106\u001a\u0004\b7\u00108R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u00109\u001a\u0004\b:\u00103R\u0011\u0010<\u001a\u00020\u00068G¢\u0006\u0006\u001a\u0004\b;\u00103R\u0014\u0010=\u001a\u00020/8&X¦\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010>\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006A"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "", "", ContentDisposition.Parameters.Name, "Landroidx/compose/ui/graphics/colorspace/ColorModel;", "model", "", TtmlNode.ATTR_ID, "<init>", "(Ljava/lang/String;JILkotlin/jvm/internal/h;)V", "(Ljava/lang/String;JLkotlin/jvm/internal/h;)V", "component", "", "getMinValue", "(I)F", "getMaxValue", "r", "g", "b", "", "toXyz", "(FFF)[F", "v", "([F)[F", "v0", "v1", "v2", "", "toXy$ui_graphics_release", "(FFF)J", "toXy", "toZ$ui_graphics_release", "(FFF)F", "toZ", "x", "y", "z", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "colorSpace", "Landroidx/compose/ui/graphics/Color;", "xyzaToColor-JlNiLsg$ui_graphics_release", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "xyzaToColor", "fromXyz", "toString", "()Ljava/lang/String;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Ljava/lang/String;", "getName", "J", "getModel-xdoWZVw", "()J", "I", "getId$ui_graphics_release", "getComponentCount", "componentCount", "isWideGamut", "()Z", "isSrgb", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ColorSpace {
    public static final int MaxId = 63;
    public static final int MinId = -1;
    private final int id;
    private final long model;
    private final String name;

    public /* synthetic */ ColorSpace(String str, long j10, int i10, h hVar) {
        this(str, j10, i10);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ColorSpace colorSpace = (ColorSpace) other;
        if (this.id == colorSpace.id && p.a(this.name, colorSpace.name)) {
            return ColorModel.m3857equalsimpl0(this.model, colorSpace.model);
        }
        return false;
    }

    public final float[] fromXyz(float x, float y10, float z) {
        float[] fArr = new float[ColorModel.m3858getComponentCountimpl(this.model)];
        fArr[0] = x;
        fArr[1] = y10;
        fArr[2] = z;
        return fromXyz(fArr);
    }

    public abstract float[] fromXyz(float[] v2);

    public final int getComponentCount() {
        return ColorModel.m3858getComponentCountimpl(this.model);
    }

    /* JADX INFO: renamed from: getId$ui_graphics_release, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public abstract float getMaxValue(int component);

    public abstract float getMinValue(int component);

    /* JADX INFO: renamed from: getModel-xdoWZVw, reason: not valid java name and from getter */
    public final long getModel() {
        return this.model;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return ((ColorModel.m3859hashCodeimpl(this.model) + (this.name.hashCode() * 31)) * 31) + this.id;
    }

    /* JADX INFO: renamed from: isSrgb */
    public boolean getIsSrgb() {
        return false;
    }

    /* JADX INFO: renamed from: isWideGamut */
    public abstract boolean getIsWideGamut();

    public String toString() {
        return this.name + " (id=" + this.id + ", model=" + ((Object) ColorModel.m3860toStringimpl(this.model)) + ')';
    }

    public long toXy$ui_graphics_release(float v0, float v12, float v2) {
        float[] xyz = toXyz(v0, v12, v2);
        float f10 = xyz[0];
        float f11 = xyz[1];
        return (((long) Float.floatToRawIntBits(f10)) << 32) | (((long) Float.floatToRawIntBits(f11)) & 4294967295L);
    }

    public final float[] toXyz(float r4, float g10, float b10) {
        return toXyz(new float[]{r4, g10, b10});
    }

    public abstract float[] toXyz(float[] v2);

    public float toZ$ui_graphics_release(float v0, float v12, float v2) {
        return toXyz(v0, v12, v2)[2];
    }

    /* JADX INFO: renamed from: xyzaToColor-JlNiLsg$ui_graphics_release, reason: not valid java name */
    public long mo3867xyzaToColorJlNiLsg$ui_graphics_release(float x, float y10, float z, float a10, ColorSpace colorSpace) {
        float[] fArrFromXyz = fromXyz(x, y10, z);
        return ColorKt.Color(fArrFromXyz[0], fArrFromXyz[1], fArrFromXyz[2], a10, colorSpace);
    }

    public /* synthetic */ ColorSpace(String str, long j10, h hVar) {
        this(str, j10);
    }

    private ColorSpace(String str, long j10, int i10) {
        this.name = str;
        this.model = j10;
        this.id = i10;
        if (str.length() == 0) {
            throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
        }
        if (i10 < -1 || i10 > 63) {
            throw new IllegalArgumentException("The id must be between -1 and 63");
        }
    }

    private ColorSpace(String str, long j10) {
        this(str, j10, -1, null);
    }
}
