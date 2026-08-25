package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.common.FileTypes;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.h;
import androidx.media3.extractor.amr.AmrExtractor;
import androidx.media3.extractor.avi.AviExtractor;
import androidx.media3.extractor.avif.AvifExtractor;
import androidx.media3.extractor.bmp.BmpExtractor;
import androidx.media3.extractor.flac.FlacExtractor;
import androidx.media3.extractor.flv.FlvExtractor;
import androidx.media3.extractor.heif.HeifExtractor;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.ogg.OggExtractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.extractor.wav.WavExtractor;
import androidx.media3.extractor.webp.WebpExtractor;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14, 17, 18, 19, 20, 21};
    private static final ExtensionLoader FLAC_EXTENSION_LOADER = new ExtensionLoader(new h(19));
    private static final ExtensionLoader MIDI_EXTENSION_LOADER = new ExtensionLoader(new h(20));
    private int adtsFlags;
    private int amrFlags;
    private int codecsToParseWithinGopSampleDependencies;
    private boolean constantBitrateSeekingAlwaysEnabled;
    private boolean constantBitrateSeekingEnabled;
    private int flacFlags;
    private int fragmentedMp4Flags;
    private int heifFlags;
    private int jpegFlags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private h1 tsSubtitleFormats;
    private int tsMode = 1;
    private int tsTimestampSearchBytes = TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES;
    private SubtitleParser.Factory subtitleParserFactory = new DefaultSubtitleParserFactory();
    private boolean textTrackTranscodingEnabled = true;

    public static final class ExtensionLoader {
        private final ConstructorSupplier constructorSupplier;
        private final AtomicBoolean extensionLoaded = new AtomicBoolean(false);
        private Constructor<? extends Extractor> extractorConstructor;

        public interface ConstructorSupplier {
            Constructor<? extends Extractor> getConstructor() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException;
        }

        public ExtensionLoader(ConstructorSupplier constructorSupplier) {
            this.constructorSupplier = constructorSupplier;
        }

        private Constructor<? extends Extractor> maybeLoadExtractorConstructor() {
            synchronized (this.extensionLoaded) {
                if (this.extensionLoaded.get()) {
                    return this.extractorConstructor;
                }
                try {
                    return this.constructorSupplier.getConstructor();
                } catch (ClassNotFoundException unused) {
                    this.extensionLoaded.set(true);
                    return this.extractorConstructor;
                } catch (Exception e5) {
                    throw new RuntimeException("Error instantiating extension", e5);
                }
            }
        }

        public Extractor getExtractor(Object... objArr) {
            Constructor<? extends Extractor> constructorMaybeLoadExtractorConstructor = maybeLoadExtractorConstructor();
            if (constructorMaybeLoadExtractorConstructor == null) {
                return null;
            }
            try {
                return constructorMaybeLoadExtractorConstructor.newInstance(objArr);
            } catch (Exception e5) {
                throw new IllegalStateException("Unexpected error creating extractor", e5);
            }
        }
    }

    private void addExtractorsForFileType(int i10, List<Extractor> list) {
        switch (i10) {
            case 0:
                list.add(new Ac3Extractor());
                break;
            case 1:
                list.add(new Ac4Extractor());
                break;
            case 2:
                list.add(new AdtsExtractor(this.adtsFlags | (this.constantBitrateSeekingEnabled ? 1 : 0) | (this.constantBitrateSeekingAlwaysEnabled ? 2 : 0)));
                break;
            case 3:
                list.add(new AmrExtractor(this.amrFlags | (this.constantBitrateSeekingEnabled ? 1 : 0) | (this.constantBitrateSeekingAlwaysEnabled ? 2 : 0)));
                break;
            case 4:
                Extractor extractor = FLAC_EXTENSION_LOADER.getExtractor(Integer.valueOf(this.flacFlags));
                if (extractor == null) {
                    list.add(new FlacExtractor(this.flacFlags));
                } else {
                    list.add(extractor);
                }
                break;
            case 5:
                list.add(new FlvExtractor());
                break;
            case 6:
                list.add(new MatroskaExtractor(this.subtitleParserFactory, (this.textTrackTranscodingEnabled ? 0 : 2) | this.matroskaFlags));
                break;
            case 7:
                list.add(new Mp3Extractor(this.mp3Flags | (this.constantBitrateSeekingEnabled ? 1 : 0) | (this.constantBitrateSeekingAlwaysEnabled ? 2 : 0)));
                break;
            case 8:
                list.add(new FragmentedMp4Extractor(this.subtitleParserFactory, this.fragmentedMp4Flags | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies) | (this.textTrackTranscodingEnabled ? 0 : 32)));
                list.add(new Mp4Extractor(this.subtitleParserFactory, this.mp4Flags | Mp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies) | (this.textTrackTranscodingEnabled ? 0 : 16)));
                break;
            case 9:
                list.add(new OggExtractor());
                break;
            case 10:
                list.add(new PsExtractor());
                break;
            case 11:
                if (this.tsSubtitleFormats == null) {
                    f1 f1Var = h1.f14020l;
                    this.tsSubtitleFormats = o3.f14078o;
                }
                list.add(new TsExtractor(this.tsMode, !this.textTrackTranscodingEnabled ? 1 : 0, this.subtitleParserFactory, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(this.tsFlags, this.tsSubtitleFormats), this.tsTimestampSearchBytes));
                break;
            case 12:
                list.add(new WavExtractor());
                break;
            case 14:
                list.add(new JpegExtractor(this.jpegFlags));
                break;
            case 15:
                Extractor extractor2 = MIDI_EXTENSION_LOADER.getExtractor(new Object[0]);
                if (extractor2 != null) {
                    list.add(extractor2);
                }
                break;
            case 16:
                list.add(new AviExtractor(1 ^ (this.textTrackTranscodingEnabled ? 1 : 0), this.subtitleParserFactory));
                break;
            case 17:
                list.add(new PngExtractor());
                break;
            case 18:
                list.add(new WebpExtractor());
                break;
            case 19:
                list.add(new BmpExtractor());
                break;
            case 20:
                list.add(new HeifExtractor(this.heifFlags));
                break;
            case 21:
                list.add(new AvifExtractor());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Constructor<? extends Extractor> getFlacExtractorConstructor() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        if (Boolean.TRUE.equals(Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", null).invoke(null, null))) {
            return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(Integer.TYPE);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Constructor<? extends Extractor> getMidiExtractorConstructor() throws NoSuchMethodException, ClassNotFoundException {
        return Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(Extractor.class).getConstructor(null);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors() {
        return createExtractors(Uri.EMPTY, new HashMap());
    }

    public synchronized DefaultExtractorsFactory setAdtsExtractorFlags(int i10) {
        this.adtsFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setAmrExtractorFlags(int i10) {
        this.amrFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setConstantBitrateSeekingAlwaysEnabled(boolean z) {
        this.constantBitrateSeekingAlwaysEnabled = z;
        return this;
    }

    public synchronized DefaultExtractorsFactory setConstantBitrateSeekingEnabled(boolean z) {
        this.constantBitrateSeekingEnabled = z;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFlacExtractorFlags(int i10) {
        this.flacFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFragmentedMp4ExtractorFlags(int i10) {
        this.fragmentedMp4Flags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setHeifExtractorFlags(int i10) {
        this.heifFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setJpegExtractorFlags(int i10) {
        this.jpegFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMatroskaExtractorFlags(int i10) {
        this.matroskaFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp3ExtractorFlags(int i10) {
        this.mp3Flags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp4ExtractorFlags(int i10) {
        this.mp4Flags = i10;
        return this;
    }

    @Deprecated
    public synchronized DefaultExtractorsFactory setTextTrackTranscodingEnabled(boolean z) {
        return experimentalSetTextTrackTranscodingEnabled(z);
    }

    public synchronized DefaultExtractorsFactory setTsExtractorFlags(int i10) {
        this.tsFlags = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorMode(int i10) {
        this.tsMode = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorTimestampSearchBytes(int i10) {
        this.tsTimestampSearchBytes = i10;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsSubtitleFormats(List<Format> list) {
        this.tsSubtitleFormats = h1.n(list);
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors(Uri uri, Map<String, List<String>> map) {
        ArrayList arrayList;
        try {
            int[] iArr = DEFAULT_EXTRACTOR_ORDER;
            arrayList = new ArrayList(iArr.length);
            int iInferFileTypeFromResponseHeaders = FileTypes.inferFileTypeFromResponseHeaders(map);
            if (iInferFileTypeFromResponseHeaders != -1) {
                addExtractorsForFileType(iInferFileTypeFromResponseHeaders, arrayList);
            }
            int iInferFileTypeFromUri = FileTypes.inferFileTypeFromUri(uri);
            if (iInferFileTypeFromUri != -1 && iInferFileTypeFromUri != iInferFileTypeFromResponseHeaders) {
                addExtractorsForFileType(iInferFileTypeFromUri, arrayList);
            }
            for (int i10 : iArr) {
                if (i10 != iInferFileTypeFromResponseHeaders && i10 != iInferFileTypeFromUri) {
                    addExtractorsForFileType(i10, arrayList);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return (Extractor[]) arrayList.toArray(new Extractor[0]);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized DefaultExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        this.codecsToParseWithinGopSampleDependencies = i10;
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    @Deprecated
    public synchronized DefaultExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        this.textTrackTranscodingEnabled = z;
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized DefaultExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        return this;
    }
}
