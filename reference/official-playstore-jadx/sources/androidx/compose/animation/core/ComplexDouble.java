package androidx.compose.animation.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÂ\u0003J\t\u0010\f\u001a\u00020\u0003HÂ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0011\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0003H\u0086\nJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0011\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0000H\u0086\nJ\u0011\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0003H\u0086\nJ\u0011\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0000H\u0086\nJ\u0011\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0003H\u0086\nJ\u0011\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0000H\u0086\nJ\u0011\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0003H\u0086\nJ\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0000H\u0086\nR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u001a"}, d2 = {"Landroidx/compose/animation/core/ComplexDouble;", "", "_real", "", "_imaginary", "(DD)V", "imaginary", "getImaginary", "()D", "real", "getReal", "component1", "component2", "copy", TtmlNode.TAG_DIV, "other", "equals", "", "hashCode", "", "minus", "plus", "times", "toString", "", "unaryMinus", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ComplexDouble {
    public static final int $stable = 8;
    private double _imaginary;
    private double _real;

    public ComplexDouble(double d4, double d10) {
        this._real = d4;
        this._imaginary = d10;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final double get_real() {
        return this._real;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final double get_imaginary() {
        return this._imaginary;
    }

    public static /* synthetic */ ComplexDouble copy$default(ComplexDouble complexDouble, double d4, double d10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            d4 = complexDouble._real;
        }
        if ((i10 & 2) != 0) {
            d10 = complexDouble._imaginary;
        }
        return complexDouble.copy(d4, d10);
    }

    public final ComplexDouble copy(double _real, double _imaginary) {
        return new ComplexDouble(_real, _imaginary);
    }

    public final ComplexDouble div(double other) {
        this._real /= other;
        this._imaginary /= other;
        return this;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComplexDouble)) {
            return false;
        }
        ComplexDouble complexDouble = (ComplexDouble) other;
        return Double.compare(this._real, complexDouble._real) == 0 && Double.compare(this._imaginary, complexDouble._imaginary) == 0;
    }

    public final double getImaginary() {
        return this._imaginary;
    }

    public final double getReal() {
        return this._real;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this._real);
        int i10 = ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31;
        long jDoubleToLongBits2 = Double.doubleToLongBits(this._imaginary);
        return i10 + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    public final ComplexDouble minus(double other) {
        this._real += -other;
        return this;
    }

    public final ComplexDouble plus(double other) {
        this._real += other;
        return this;
    }

    public final ComplexDouble times(double other) {
        this._real *= other;
        this._imaginary *= other;
        return this;
    }

    public String toString() {
        return "ComplexDouble(_real=" + this._real + ", _imaginary=" + this._imaginary + ')';
    }

    public final ComplexDouble unaryMinus() {
        double d4 = -1;
        this._real *= d4;
        this._imaginary *= d4;
        return this;
    }

    public final ComplexDouble minus(ComplexDouble other) {
        double d4 = -1;
        other._real *= d4;
        other._imaginary *= d4;
        this._real = other.getReal() + this._real;
        this._imaginary = other.getImaginary() + this._imaginary;
        return this;
    }

    public final ComplexDouble plus(ComplexDouble other) {
        this._real = other.getReal() + this._real;
        this._imaginary = other.getImaginary() + this._imaginary;
        return this;
    }

    public final ComplexDouble times(ComplexDouble other) {
        this._real = (other.getReal() * getReal()) - (other.getImaginary() * getImaginary());
        this._imaginary = (getImaginary() * other.getReal()) + (other.getImaginary() * getReal());
        return this;
    }
}
