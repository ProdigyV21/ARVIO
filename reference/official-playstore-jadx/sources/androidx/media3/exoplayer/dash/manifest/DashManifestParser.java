package androidx.media3.exoplayer.dash.manifest;

import ac.b;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import androidx.compose.material3.TextFieldImplKt;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.exoplayer.dash.manifest.SegmentBase;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.b0;
import com.google.common.collect.a0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import io.ktor.http.LinkHeader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final String TAG = "MpdParser";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final int[] DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING = {2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2};
    private static final int[] MPEG_CHANNEL_CONFIGURATION_MAPPING = {-1, 1, 2, 3, 4, 5, 6, 8, 2, 3, 4, 7, 8, 24, 8, 12, 10, 12, 14, 12, 14};

    public static final class RepresentationInfo {
        public final h1 baseUrls;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final List<Descriptor> essentialProperties;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;
        public final List<Descriptor> supplementalProperties;

        public RepresentationInfo(Format format, List<BaseUrl> list, SegmentBase segmentBase, String str, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2, List<Descriptor> list2, List<Descriptor> list3, long j10) {
            this.format = format;
            this.baseUrls = h1.n(list);
            this.segmentBase = segmentBase;
            this.drmSchemeType = str;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
            this.essentialProperties = list2;
            this.supplementalProperties = list3;
            this.revisionId = j10;
        }
    }

    public DashManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e5) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e5);
        }
    }

    private long addSegmentTimelineElementsToList(List<SegmentBase.SegmentTimelineElement> list, long j10, long j11, int i10, long j12) {
        int iCeilDivide = i10 >= 0 ? i10 + 1 : (int) Util.ceilDivide(j12 - j10, j11);
        for (int i11 = 0; i11 < iCeilDivide; i11++) {
            list.add(buildSegmentTimelineElement(j10, j11));
            j10 += j11;
        }
        return j10;
    }

    private static int checkContentTypeConsistency(int i10, int i11) {
        if (i10 == -1) {
            return i11;
        }
        if (i11 == -1) {
            return i10;
        }
        b.s(i10 == i11);
        return i10;
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        b.s(str.equals(str2));
        return str;
    }

    private static void fillInClearKeyInformation(ArrayList<DrmInitData.SchemeData> arrayList) {
        String str;
        int i10 = 0;
        while (true) {
            if (i10 >= arrayList.size()) {
                str = null;
                break;
            }
            DrmInitData.SchemeData schemeData = arrayList.get(i10);
            if (C.CLEARKEY_UUID.equals(schemeData.uuid) && (str = schemeData.licenseServerUrl) != null) {
                arrayList.remove(i10);
                break;
            }
            i10++;
        }
        if (str == null) {
            return;
        }
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            DrmInitData.SchemeData schemeData2 = arrayList.get(i11);
            if (C.COMMON_PSSH_UUID.equals(schemeData2.uuid) && schemeData2.licenseServerUrl == null) {
                arrayList.set(i11, new DrmInitData.SchemeData(C.CLEARKEY_UUID, str, schemeData2.mimeType, schemeData2.data));
            }
        }
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = arrayList.get(size);
            if (!schemeData.hasData()) {
                int i10 = 0;
                while (true) {
                    if (i10 >= arrayList.size()) {
                        break;
                    }
                    if (arrayList.get(i10).canReplace(schemeData)) {
                        arrayList.remove(size);
                        break;
                    }
                    i10++;
                }
            }
        }
    }

    private static long getFinalAvailabilityTimeOffset(long j10, long j11) {
        if (j11 != C.TIME_UNSET) {
            j10 = j11;
        }
        return j10 == Long.MAX_VALUE ? C.TIME_UNSET : j10;
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (MimeTypes.isText(str) || MimeTypes.isImage(str)) {
            return str;
        }
        if (!MimeTypes.APPLICATION_MP4.equals(str)) {
            return null;
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str2);
        return MimeTypes.TEXT_VTT.equals(mediaMimeType) ? MimeTypes.APPLICATION_MP4VTT : mediaMimeType;
    }

    private boolean isDvbProfileDeclared(String[] strArr) {
        for (String str : strArr) {
            if (str.startsWith("urn:dvb:dash:profile:dvb-dash:")) {
                return true;
            }
        }
        return false;
    }

    public static void maybeSkipTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
            int i10 = 1;
            while (i10 != 0) {
                xmlPullParser.next();
                if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
                    i10++;
                } else if (XmlPullParserUtil.isEndTag(xmlPullParser)) {
                    i10--;
                }
            }
        }
    }

    public static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        String str;
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && (str = descriptor.value) != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(str);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    public static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        String str;
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && (str = descriptor.value) != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(str);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    public static long parseDateTime(XmlPullParser xmlPullParser, String str, long j10) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j10 : Util.parseXsDateTime(attributeValue);
    }

    public static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String string = parseString(xmlPullParser, "schemeIdUri", "");
        String string2 = parseString(xmlPullParser, "value", null);
        String string3 = parseString(xmlPullParser, TtmlNode.ATTR_ID, null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(string, string2, string3);
    }

    public static int parseDolbyAC4ChannelConfiguration(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "value");
        if (attributeValue == null || attributeValue.length() != 6) {
            return -1;
        }
        int i10 = Integer.parseInt(attributeValue, 16);
        return (8388608 & i10) != 0 ? parseDolbyAc4ObjectBasedChannelConfiguration(str) : parseDolbyAc4ChannelBasedChannelConfiguration(i10);
    }

    private static int parseDolbyAc4ChannelBasedChannelConfiguration(int i10) {
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int[] iArr = DOLBY_AC4_CHANNEL_CONFIGURATION_MAPPING;
            if (i11 >= iArr.length) {
                break;
            }
            i12 += ((i10 >> i11) & 1) * iArr[i11];
            i11++;
        }
        if (i12 == 0) {
            return -1;
        }
        return i12;
    }

    private static int parseDolbyAc4ObjectBasedChannelConfiguration(String str) {
        String[] strArrSplitCodecs = Util.splitCodecs(str);
        if (strArrSplitCodecs.length == 0) {
            return -1;
        }
        List listC = b0.a('.').c(a.S(strArrSplitCodecs[0].trim()));
        if (listC.size() != 4 || !((String) listC.get(0)).equals("ac-4")) {
            return -1;
        }
        String str2 = (String) listC.get(3);
        str2.getClass();
        if (str2.equals("03")) {
            return 18;
        }
        return !str2.equals("04") ? -1 : 21;
    }

    public static int parseDolbyChannelConfiguration(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "value");
        if (attributeValue == null) {
            return -1;
        }
        String strS = a.S(attributeValue);
        strS.getClass();
        switch (strS) {
        }
        return -1;
    }

    public static int parseDtsChannelConfiguration(XmlPullParser xmlPullParser) {
        int i10 = parseInt(xmlPullParser, "value", -1);
        if (i10 <= 0 || i10 >= 33) {
            return -1;
        }
        return i10;
    }

    public static int parseDtsxChannelConfiguration(XmlPullParser xmlPullParser) {
        int iBitCount;
        String attributeValue = xmlPullParser.getAttributeValue(null, "value");
        if (attributeValue == null || (iBitCount = Integer.bitCount(Integer.parseInt(attributeValue, 16))) == 0) {
            return -1;
        }
        return iBitCount;
    }

    public static long parseDuration(XmlPullParser xmlPullParser, String str, long j10) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j10 : Util.parseXsDuration(attributeValue);
    }

    public static String parseEac3SupplementalProperties(List<Descriptor> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            String str = descriptor.schemeIdUri;
            if ("tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(str) && "JOC".equals(descriptor.value)) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(str) && MimeTypes.CODEC_E_AC3_JOC.equals(descriptor.value)) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
        }
        return MimeTypes.AUDIO_E_AC3;
    }

    public static float parseFloat(XmlPullParser xmlPullParser, String str, float f10) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? f10 : Float.parseFloat(attributeValue);
    }

    public static float parseFrameRate(XmlPullParser xmlPullParser, float f10) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue != null) {
            Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
            if (matcher.matches()) {
                int i10 = Integer.parseInt(matcher.group(1));
                return !TextUtils.isEmpty(matcher.group(2)) ? i10 / Integer.parseInt(r2) : i10;
            }
        }
        return f10;
    }

    public static int parseInt(XmlPullParser xmlPullParser, String str, int i10) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i10 : Integer.parseInt(attributeValue);
    }

    public static long parseLastSegmentNumberSupplementalProperty(List<Descriptor> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            if (a.p("http://dashif.org/guidelines/last-segment-number", descriptor.schemeIdUri)) {
                return Long.parseLong(descriptor.value);
            }
        }
        return -1L;
    }

    public static long parseLong(XmlPullParser xmlPullParser, String str, long j10) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j10 : Long.parseLong(attributeValue);
    }

    public static int parseMpegChannelConfiguration(XmlPullParser xmlPullParser) {
        int i10 = parseInt(xmlPullParser, "value", -1);
        if (i10 >= 0) {
            int[] iArr = MPEG_CHANNEL_CONFIGURATION_MAPPING;
            if (i10 < iArr.length) {
                return iArr[i10];
            }
        }
        return -1;
    }

    public static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    public static String parseText(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String text = "";
        do {
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                text = xmlPullParser.getText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return text;
    }

    public AdaptationSet buildAdaptationSet(long j10, int i10, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4) {
        return new AdaptationSet(j10, i10, list, list2, list3, list4);
    }

    public EventMessage buildEvent(String str, String str2, long j10, long j11, byte[] bArr) {
        return new EventMessage(str, str2, j11, j10, bArr);
    }

    public EventStream buildEventStream(String str, String str2, long j10, long[] jArr, EventMessage[] eventMessageArr) {
        return new EventStream(str, str2, j10, jArr, eventMessageArr);
    }

    public Format buildFormat(String str, String str2, int i10, int i11, float f10, int i12, int i13, int i14, String str3, List<Descriptor> list, List<Descriptor> list2, String str4, String str5, String str6, List<Descriptor> list3, List<Descriptor> list4) {
        String str7 = str5;
        String str8 = str4;
        String sampleMimeType = getSampleMimeType(str2, str8);
        if (MimeTypes.AUDIO_E_AC3.equals(sampleMimeType)) {
            sampleMimeType = parseEac3SupplementalProperties(list4);
            if (MimeTypes.AUDIO_E_AC3_JOC.equals(sampleMimeType)) {
                str8 = MimeTypes.CODEC_E_AC3_JOC;
            }
        }
        if (MimeTypes.isDolbyVisionCodec(str8, str7)) {
            if (str7 == null) {
                str7 = str8;
            }
            sampleMimeType = MimeTypes.VIDEO_DOLBY_VISION;
            str8 = str7;
        }
        int selectionFlagsFromRoleDescriptors = parseSelectionFlagsFromRoleDescriptors(list);
        int roleFlagsFromRoleDescriptors = parseRoleFlagsFromRoleDescriptors(list) | parseRoleFlagsFromAccessibilityDescriptors(list2) | parseRoleFlagsFromProperties(list3) | parseRoleFlagsFromProperties(list4);
        Pair<Integer, Integer> tileCountFromProperties = parseTileCountFromProperties(list3);
        Format.Builder language = new Format.Builder().setId(str).setContainerMimeType(str2).setSampleMimeType(sampleMimeType).setCodecs(str8).setPeakBitrate(i14).setSelectionFlags(selectionFlagsFromRoleDescriptors).setRoleFlags(roleFlagsFromRoleDescriptors).setLanguage(str3);
        int cea708AccessibilityChannel = -1;
        Format.Builder tileCountVertical = language.setTileCountHorizontal(tileCountFromProperties != null ? ((Integer) tileCountFromProperties.first).intValue() : -1).setTileCountVertical(tileCountFromProperties != null ? ((Integer) tileCountFromProperties.second).intValue() : -1);
        if (MimeTypes.isVideo(sampleMimeType)) {
            tileCountVertical.setWidth(i10).setHeight(i11).setFrameRate(f10);
        } else if (MimeTypes.isAudio(sampleMimeType)) {
            tileCountVertical.setChannelCount(i12).setSampleRate(i13);
        } else if (MimeTypes.isText(sampleMimeType)) {
            if (MimeTypes.APPLICATION_CEA608.equals(sampleMimeType)) {
                cea708AccessibilityChannel = parseCea608AccessibilityChannel(list2);
            } else if (MimeTypes.APPLICATION_CEA708.equals(sampleMimeType)) {
                cea708AccessibilityChannel = parseCea708AccessibilityChannel(list2);
            }
            tileCountVertical.setAccessibilityChannel(cea708AccessibilityChannel);
        } else if (MimeTypes.isImage(sampleMimeType)) {
            tileCountVertical.setWidth(i10).setHeight(i11);
        }
        return tileCountVertical.build();
    }

    public DashManifest buildMediaPresentationDescription(long j10, long j11, long j12, boolean z, long j13, long j14, long j15, long j16, ProgramInformation programInformation, UtcTimingElement utcTimingElement, ServiceDescriptionElement serviceDescriptionElement, Uri uri, List<Period> list) {
        return new DashManifest(j10, j11, j12, z, j13, j14, j15, j16, programInformation, utcTimingElement, serviceDescriptionElement, uri, list);
    }

    public Period buildPeriod(String str, long j10, List<AdaptationSet> list, List<EventStream> list2, Descriptor descriptor) {
        return new Period(str, j10, list, list2, descriptor);
    }

    public RangedUri buildRangedUri(String str, long j10, long j11) {
        return new RangedUri(str, j10, j11);
    }

    public Representation buildRepresentation(RepresentationInfo representationInfo, String str, List<Label> list, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format.Builder builderBuildUpon = representationInfo.format.buildUpon();
        if (str == null || !list.isEmpty()) {
            builderBuildUpon.setLabels(list);
        } else {
            builderBuildUpon.setLabel(str);
        }
        String str3 = representationInfo.drmSchemeType;
        if (str3 == null) {
            str3 = str2;
        }
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            fillInClearKeyInformation(arrayList3);
            filterRedundantIncompleteSchemeDatas(arrayList3);
            builderBuildUpon.setDrmInitData(new DrmInitData(str3, arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(representationInfo.revisionId, builderBuildUpon.build(), representationInfo.baseUrls, representationInfo.segmentBase, arrayList4, representationInfo.essentialProperties, representationInfo.supplementalProperties, null);
    }

    public SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j10, long j11, long j12, long j13, List<SegmentBase.SegmentTimelineElement> list, long j14, List<RangedUri> list2, long j15, long j16) {
        return new SegmentBase.SegmentList(rangedUri, j10, j11, j12, j13, list, j14, list2, Util.msToUs(j15), Util.msToUs(j16));
    }

    public SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j10, long j11, long j12, long j13, long j14, List<SegmentBase.SegmentTimelineElement> list, long j15, UrlTemplate urlTemplate, UrlTemplate urlTemplate2, long j16, long j17) {
        return new SegmentBase.SegmentTemplate(rangedUri, j10, j11, j12, j13, j14, list, j15, urlTemplate, urlTemplate2, Util.msToUs(j16), Util.msToUs(j17));
    }

    public SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j10, long j11) {
        return new SegmentBase.SegmentTimelineElement(j10, j11);
    }

    public SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j10, long j11, long j12, long j13) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j10, j11, j12, j13);
    }

    public UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x03cc A[LOOP:0: B:3:0x00a3->B:75:0x03cc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x037c A[EDGE_INSN: B:76:0x037c->B:69:0x037c BREAK  A[LOOP:0: B:3:0x00a3->B:75:0x03cc], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.media3.exoplayer.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r46, java.util.List<androidx.media3.exoplayer.dash.manifest.BaseUrl> r47, androidx.media3.exoplayer.dash.manifest.SegmentBase r48, long r49, long r51, long r53, long r55, long r57, boolean r59) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1000
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.util.List, androidx.media3.exoplayer.dash.manifest.SegmentBase, long, long, long, long, long, boolean):androidx.media3.exoplayer.dash.manifest.AdaptationSet");
    }

    public void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        maybeSkipTag(xmlPullParser);
    }

    public int parseAudioChannelConfiguration(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        int dtsChannelConfiguration;
        String string = parseString(xmlPullParser, "schemeIdUri", null);
        string.getClass();
        dtsChannelConfiguration = -1;
        switch (string) {
            case "urn:dts:dash:audio_channel_configuration:2012":
            case "tag:dts.com,2014:dash:audio_channel_configuration:2012":
                dtsChannelConfiguration = parseDtsChannelConfiguration(xmlPullParser);
                break;
            case "tag:dolby.com,2015:dash:audio_channel_configuration:2015":
                dtsChannelConfiguration = parseDolbyAC4ChannelConfiguration(xmlPullParser, str);
                break;
            case "urn:mpeg:dash:23003:3:audio_channel_configuration:2011":
                dtsChannelConfiguration = parseInt(xmlPullParser, "value", -1);
                break;
            case "tag:dolby.com,2014:dash:audio_channel_configuration:2011":
            case "urn:dolby:dash:audio_channel_configuration:2011":
                dtsChannelConfiguration = parseDolbyChannelConfiguration(xmlPullParser);
                break;
            case "urn:mpeg:mpegB:cicp:ChannelConfiguration":
                dtsChannelConfiguration = parseMpegChannelConfiguration(xmlPullParser);
                break;
            case "tag:dts.com,2018:uhd:audio_channel_configuration":
                dtsChannelConfiguration = parseDtsxChannelConfiguration(xmlPullParser);
                break;
        }
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return dtsChannelConfiguration;
    }

    public long parseAvailabilityTimeOffsetUs(XmlPullParser xmlPullParser, long j10) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "availabilityTimeOffset");
        if (attributeValue == null) {
            return j10;
        }
        if ("INF".equals(attributeValue)) {
            return Long.MAX_VALUE;
        }
        return (long) (Float.parseFloat(attributeValue) * 1000000.0f);
    }

    public List<BaseUrl> parseBaseUrl(XmlPullParser xmlPullParser, List<BaseUrl> list, boolean z) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "dvb:priority");
        int i10 = attributeValue != null ? Integer.parseInt(attributeValue) : z ? 1 : Integer.MIN_VALUE;
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "dvb:weight");
        int i11 = attributeValue2 != null ? Integer.parseInt(attributeValue2) : 1;
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "serviceLocation");
        String text = parseText(xmlPullParser, "BaseURL");
        if (UriUtil.isAbsolute(text)) {
            if (attributeValue3 == null) {
                attributeValue3 = text;
            }
            return a0.o(new BaseUrl(text, attributeValue3, i10, i11));
        }
        ArrayList arrayList = new ArrayList();
        for (int i12 = 0; i12 < list.size(); i12++) {
            BaseUrl baseUrl = list.get(i12);
            String strResolve = UriUtil.resolve(baseUrl.url, text);
            String str = attributeValue3 == null ? strResolve : attributeValue3;
            if (z) {
                i10 = baseUrl.priority;
                i11 = baseUrl.weight;
                str = baseUrl.serviceLocation;
            }
            arrayList.add(new BaseUrl(strResolve, str, i10, i11));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011e  */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v4, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.util.Pair<java.lang.String, androidx.media3.common.DrmInitData.SchemeData> parseContentProtection(org.xmlpull.v1.XmlPullParser r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseContentProtection(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    public int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        return "image".equals(attributeValue) ? 4 : -1;
    }

    public Pair<Long, EventMessage> parseEvent(XmlPullParser xmlPullParser, String str, String str2, long j10, long j11, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        long j12 = parseLong(xmlPullParser, TtmlNode.ATTR_ID, 0L);
        long j13 = parseLong(xmlPullParser, "duration", C.TIME_UNSET);
        long j14 = parseLong(xmlPullParser, "presentationTime", 0L);
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(j13, 1000L, j10);
        long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j14 - j11, 1000000L, j10);
        String string = parseString(xmlPullParser, "messageData", null);
        byte[] eventObject = parseEventObject(xmlPullParser, byteArrayOutputStream);
        Long lValueOf = Long.valueOf(jScaleLargeTimestamp2);
        if (string != null) {
            eventObject = Util.getUtf8Bytes(string);
        }
        return Pair.create(lValueOf, buildEvent(str, str2, j12, jScaleLargeTimestamp, eventObject));
    }

    public byte[] parseEventObject(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
        xmlSerializerNewSerializer.setOutput(byteArrayOutputStream, StandardCharsets.UTF_8.name());
        xmlPullParser.nextToken();
        while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    xmlSerializerNewSerializer.startDocument(null, Boolean.FALSE);
                    break;
                case 1:
                    xmlSerializerNewSerializer.endDocument();
                    break;
                case 2:
                    xmlSerializerNewSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i10 = 0; i10 < xmlPullParser.getAttributeCount(); i10++) {
                        xmlSerializerNewSerializer.attribute(xmlPullParser.getAttributeNamespace(i10), xmlPullParser.getAttributeName(i10), xmlPullParser.getAttributeValue(i10));
                    }
                    break;
                case 3:
                    xmlSerializerNewSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    xmlSerializerNewSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    xmlSerializerNewSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    xmlSerializerNewSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    xmlSerializerNewSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    xmlSerializerNewSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    xmlSerializerNewSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    xmlSerializerNewSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        xmlSerializerNewSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public EventStream parseEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        long j10;
        String str;
        String str2;
        XmlPullParser xmlPullParser2;
        String string = parseString(xmlPullParser, "schemeIdUri", "");
        String string2 = parseString(xmlPullParser, "value", "");
        long j11 = parseLong(xmlPullParser, "timescale", 1L);
        long j12 = parseLong(xmlPullParser, "presentationTimeOffset", 0L);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Event")) {
                j10 = j11;
                str = string2;
                str2 = string;
                xmlPullParser2 = xmlPullParser;
                arrayList.add(parseEvent(xmlPullParser2, str2, str, j10, j12, byteArrayOutputStream));
            } else {
                j10 = j11;
                str = string2;
                str2 = string;
                xmlPullParser2 = xmlPullParser;
                maybeSkipTag(xmlPullParser2);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "EventStream")) {
                break;
            }
            xmlPullParser = xmlPullParser2;
            string = str2;
            string2 = str;
            j11 = j10;
        }
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            Pair pair = (Pair) arrayList.get(i10);
            jArr[i10] = ((Long) pair.first).longValue();
            eventMessageArr[i10] = (EventMessage) pair.second;
        }
        return buildEventStream(str2, str, j10, jArr, eventMessageArr);
    }

    public RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    public Label parseLabel(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return new Label(xmlPullParser.getAttributeValue(null, "lang"), parseText(xmlPullParser, TextFieldImplKt.LabelId));
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x01de A[LOOP:0: B:24:0x00a6->B:81:0x01de, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x019a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.media3.exoplayer.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r41, android.net.Uri r42) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, android.net.Uri):androidx.media3.exoplayer.dash.manifest.DashManifest");
    }

    public Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, List<BaseUrl> list, long j10, long j11, long j12, long j13, boolean z) throws XmlPullParserException, IOException {
        long availabilityTimeOffsetUs;
        SegmentBase segmentBase;
        ArrayList arrayList;
        ArrayList arrayList2;
        long j14;
        Object obj;
        long j15;
        XmlPullParser xmlPullParser2;
        long j16;
        long j17;
        SegmentBase segmentTemplate;
        long j18;
        Descriptor descriptor;
        long j19;
        SegmentBase segmentBase2;
        ArrayList arrayList3;
        boolean z5;
        long j20;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        List<BaseUrl> list2;
        ArrayList arrayList7;
        DashManifestParser dashManifestParser = this;
        XmlPullParser xmlPullParser3 = xmlPullParser;
        String attributeValue = xmlPullParser3.getAttributeValue(null, TtmlNode.ATTR_ID);
        long duration = parseDuration(xmlPullParser3, TtmlNode.START, j10);
        long j21 = j12 != C.TIME_UNSET ? j12 + duration : -9223372036854775807L;
        long duration2 = parseDuration(xmlPullParser3, "duration", C.TIME_UNSET);
        ArrayList arrayList8 = new ArrayList();
        long j22 = -9223372036854775807L;
        ArrayList arrayList9 = new ArrayList();
        boolean z10 = false;
        Descriptor descriptor2 = null;
        long availabilityTimeOffsetUs2 = j11;
        ArrayList arrayList10 = arrayList8;
        SegmentBase segmentBase3 = null;
        long j23 = -9223372036854775807L;
        ArrayList arrayList11 = new ArrayList();
        while (true) {
            xmlPullParser3.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser3, "BaseURL")) {
                if (!z10) {
                    availabilityTimeOffsetUs2 = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser3, availabilityTimeOffsetUs2);
                    z10 = true;
                }
                arrayList11.addAll(dashManifestParser.parseBaseUrl(xmlPullParser3, list, z));
                arrayList2 = arrayList10;
                j18 = availabilityTimeOffsetUs2;
                arrayList = arrayList9;
                z5 = z10;
                j14 = j22;
                obj = null;
                j15 = duration2;
                arrayList7 = arrayList11;
                descriptor = descriptor2;
                xmlPullParser2 = xmlPullParser3;
                segmentBase2 = segmentBase3;
                j20 = j23;
                j17 = j21;
            } else {
                ArrayList arrayList12 = arrayList10;
                if (XmlPullParserUtil.isStartTag(xmlPullParser3, "AdaptationSet")) {
                    if (arrayList11.isEmpty()) {
                        arrayList6 = arrayList11;
                        list2 = list;
                    } else {
                        ArrayList arrayList13 = arrayList11;
                        arrayList6 = arrayList13;
                        list2 = arrayList13;
                    }
                    ArrayList arrayList14 = arrayList9;
                    long j24 = availabilityTimeOffsetUs2;
                    long j25 = j21;
                    long j26 = duration2;
                    AdaptationSet adaptationSet = dashManifestParser.parseAdaptationSet(xmlPullParser3, list2, segmentBase3, j26, j24, j23, j25, j13, z);
                    j21 = j25;
                    availabilityTimeOffsetUs = j23;
                    arrayList12.add(adaptationSet);
                    segmentBase = segmentBase3;
                    j15 = j26;
                    arrayList = arrayList14;
                    obj = null;
                    xmlPullParser2 = xmlPullParser3;
                    j16 = j24;
                    arrayList2 = arrayList12;
                    j14 = C.TIME_UNSET;
                    arrayList5 = arrayList6;
                } else {
                    ArrayList arrayList15 = arrayList11;
                    ArrayList arrayList16 = arrayList9;
                    long j27 = availabilityTimeOffsetUs2;
                    availabilityTimeOffsetUs = j23;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser3, "EventStream")) {
                        arrayList16.add(parseEventStream(xmlPullParser));
                        segmentBase = segmentBase3;
                        j15 = duration2;
                        arrayList = arrayList16;
                        arrayList2 = arrayList12;
                        j14 = C.TIME_UNSET;
                        obj = null;
                        xmlPullParser2 = xmlPullParser3;
                        j16 = j27;
                        arrayList5 = arrayList15;
                    } else {
                        if (XmlPullParserUtil.isStartTag(xmlPullParser3, "SegmentBase")) {
                            segmentTemplate = dashManifestParser.parseSegmentBase(xmlPullParser3, null);
                            j18 = j27;
                            obj = null;
                            arrayList = arrayList16;
                            arrayList2 = arrayList12;
                            j14 = C.TIME_UNSET;
                            j17 = j21;
                            j15 = duration2;
                            descriptor = descriptor2;
                            xmlPullParser2 = xmlPullParser3;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "SegmentList")) {
                            long j28 = j21;
                            long j29 = duration2;
                            long availabilityTimeOffsetUs3 = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser3, C.TIME_UNSET);
                            arrayList = arrayList16;
                            arrayList2 = arrayList12;
                            j14 = -9223372036854775807L;
                            SegmentBase.SegmentList segmentList = dashManifestParser.parseSegmentList(xmlPullParser3, null, j28, j29, j27, availabilityTimeOffsetUs3, j13);
                            j18 = j27;
                            obj = null;
                            j17 = j28;
                            j15 = j29;
                            descriptor = descriptor2;
                            xmlPullParser2 = xmlPullParser3;
                            j19 = availabilityTimeOffsetUs3;
                            segmentBase2 = segmentList;
                            arrayList3 = arrayList15;
                            z5 = z10;
                            j20 = j19;
                            arrayList7 = arrayList3;
                        } else {
                            segmentBase = segmentBase3;
                            arrayList = arrayList16;
                            arrayList2 = arrayList12;
                            j14 = C.TIME_UNSET;
                            if (XmlPullParserUtil.isStartTag(xmlPullParser3, "SegmentTemplate")) {
                                availabilityTimeOffsetUs = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser3, C.TIME_UNSET);
                                f1 f1Var = h1.f14020l;
                                obj = null;
                                segmentTemplate = dashManifestParser.parseSegmentTemplate(xmlPullParser3, null, o3.f14078o, j21, duration2, j27, availabilityTimeOffsetUs, j13);
                                j15 = duration2;
                                xmlPullParser2 = xmlPullParser3;
                                j17 = j21;
                                j18 = j27;
                                descriptor = descriptor2;
                            } else {
                                obj = null;
                                j15 = duration2;
                                xmlPullParser2 = xmlPullParser3;
                                j16 = j27;
                                j17 = j21;
                                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "AssetIdentifier")) {
                                    descriptor2 = parseDescriptor(xmlPullParser2, "AssetIdentifier");
                                    arrayList4 = arrayList15;
                                } else {
                                    maybeSkipTag(xmlPullParser2);
                                    arrayList4 = arrayList15;
                                }
                                j18 = j16;
                                descriptor = descriptor2;
                                j19 = availabilityTimeOffsetUs;
                                segmentBase2 = segmentBase;
                                arrayList3 = arrayList4;
                                z5 = z10;
                                j20 = j19;
                                arrayList7 = arrayList3;
                            }
                        }
                        j19 = availabilityTimeOffsetUs;
                        segmentBase2 = segmentTemplate;
                        arrayList3 = arrayList15;
                        z5 = z10;
                        j20 = j19;
                        arrayList7 = arrayList3;
                    }
                }
                j17 = j21;
                arrayList4 = arrayList5;
                j18 = j16;
                descriptor = descriptor2;
                j19 = availabilityTimeOffsetUs;
                segmentBase2 = segmentBase;
                arrayList3 = arrayList4;
                z5 = z10;
                j20 = j19;
                arrayList7 = arrayList3;
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "Period")) {
                return Pair.create(buildPeriod(attributeValue, duration, arrayList2, arrayList, descriptor), Long.valueOf(j15));
            }
            dashManifestParser = this;
            xmlPullParser3 = xmlPullParser2;
            j21 = j17;
            segmentBase3 = segmentBase2;
            j23 = j20;
            arrayList11 = arrayList7;
            arrayList10 = arrayList2;
            descriptor2 = descriptor;
            z10 = z5;
            duration2 = j15;
            j22 = j14;
            availabilityTimeOffsetUs2 = j18;
            arrayList9 = arrayList;
        }
    }

    public String[] parseProfiles(XmlPullParser xmlPullParser, String str, String[] strArr) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? strArr : attributeValue.split(",");
    }

    public ProgramInformation parseProgramInformation(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String strNextText = null;
        String string = parseString(xmlPullParser, "moreInformationURL", null);
        String string2 = parseString(xmlPullParser, "lang", null);
        String strNextText2 = null;
        String strNextText3 = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Title")) {
                strNextText = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Source")) {
                strNextText2 = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Copyright")) {
                strNextText3 = xmlPullParser.nextText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
            String str = strNextText2;
            String str2 = strNextText;
            String str3 = strNextText3;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ProgramInformation")) {
                return new ProgramInformation(str2, str, str3, string, string2);
            }
            strNextText = str2;
            strNextText2 = str;
            strNextText3 = str3;
        }
    }

    public RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j10;
        long j11;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split("-");
            j10 = Long.parseLong(strArrSplit[0]);
            if (strArrSplit.length == 2) {
                j11 = (Long.parseLong(strArrSplit[1]) - j10) + 1;
            }
            return buildRangedUri(attributeValue, j10, j11);
        }
        j10 = 0;
        j11 = -1;
        return buildRangedUri(attributeValue, j10, j11);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0260 A[LOOP:0: B:3:0x007b->B:56:0x0260, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0205 A[EDGE_INSN: B:57:0x0205->B:46:0x0205 BREAK  A[LOOP:0: B:3:0x007b->B:56:0x0260], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.media3.exoplayer.dash.manifest.DashManifestParser.RepresentationInfo parseRepresentation(org.xmlpull.v1.XmlPullParser r35, java.util.List<androidx.media3.exoplayer.dash.manifest.BaseUrl> r36, java.lang.String r37, java.lang.String r38, java.lang.String r39, java.lang.String r40, int r41, int r42, float r43, int r44, int r45, java.lang.String r46, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r47, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r48, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r49, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r50, androidx.media3.exoplayer.dash.manifest.SegmentBase r51, long r52, long r54, long r56, long r58, long r60, boolean r62) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 663
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseRepresentation(org.xmlpull.v1.XmlPullParser, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, androidx.media3.exoplayer.dash.manifest.SegmentBase, long, long, long, long, long, boolean):androidx.media3.exoplayer.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    public int parseRoleFlagsFromAccessibilityDescriptors(List<Descriptor> list) {
        int tvaAudioPurposeCsValue;
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            Descriptor descriptor = list.get(i11);
            if (a.p("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                tvaAudioPurposeCsValue = parseRoleFlagsFromDashRoleScheme(descriptor.value);
            } else if (a.p("urn:tva:metadata:cs:AudioPurposeCS:2007", descriptor.schemeIdUri)) {
                tvaAudioPurposeCsValue = parseTvaAudioPurposeCsValue(descriptor.value);
            }
            i10 |= tvaAudioPurposeCsValue;
        }
        return i10;
    }

    public int parseRoleFlagsFromDashRoleScheme(String str) {
        if (str == null) {
            return 0;
        }
        switch (str) {
        }
        return 0;
    }

    public int parseRoleFlagsFromProperties(List<Descriptor> list) {
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            if (a.p("http://dashif.org/guidelines/trickmode", list.get(i11).schemeIdUri)) {
                i10 = 16384;
            }
        }
        return i10;
    }

    public int parseRoleFlagsFromRoleDescriptors(List<Descriptor> list) {
        int roleFlagsFromDashRoleScheme = 0;
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            if (a.p("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                roleFlagsFromDashRoleScheme |= parseRoleFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return roleFlagsFromDashRoleScheme;
    }

    public SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j10 = parseLong(xmlPullParser, "timescale", singleSegmentBase != null ? singleSegmentBase.timescale : 1L);
        long j11 = parseLong(xmlPullParser, "presentationTimeOffset", singleSegmentBase != null ? singleSegmentBase.presentationTimeOffset : 0L);
        long j12 = singleSegmentBase != null ? singleSegmentBase.indexStart : 0L;
        long j13 = singleSegmentBase != null ? singleSegmentBase.indexLength : 0L;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] strArrSplit = attributeValue.split("-");
            j12 = Long.parseLong(strArrSplit[0]);
            j13 = (Long.parseLong(strArrSplit[1]) - j12) + 1;
        }
        long j14 = j13;
        RangedUri initialization = singleSegmentBase != null ? singleSegmentBase.initialization : null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
            } else {
                maybeSkipTag(xmlPullParser);
            }
            RangedUri rangedUri = initialization;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentBase")) {
                return buildSingleSegmentBase(rangedUri, j10, j11, j12, j14);
            }
            initialization = rangedUri;
        }
    }

    public SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList, long j10, long j11, long j12, long j13, long j14) throws XmlPullParserException, IOException {
        long j15;
        long j16 = parseLong(xmlPullParser, "timescale", segmentList != null ? segmentList.timescale : 1L);
        long j17 = parseLong(xmlPullParser, "presentationTimeOffset", segmentList != null ? segmentList.presentationTimeOffset : 0L);
        long j18 = parseLong(xmlPullParser, "duration", segmentList != null ? segmentList.duration : C.TIME_UNSET);
        long j19 = parseLong(xmlPullParser, "startNumber", segmentList != null ? segmentList.startNumber : 1L);
        long finalAvailabilityTimeOffset = getFinalAvailabilityTimeOffset(j12, j13);
        List<RangedUri> arrayList = null;
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
                j15 = j16;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                j15 = j16;
                segmentTimeline = parseSegmentTimeline(xmlPullParser, j15, j11);
            } else {
                j15 = j16;
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentURL")) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(parseSegmentUrl(xmlPullParser));
                } else {
                    maybeSkipTag(xmlPullParser);
                }
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentList")) {
                break;
            }
            j16 = j15;
        }
        if (segmentList != null) {
            if (initialization == null) {
                initialization = segmentList.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentList.segmentTimeline;
            }
            if (arrayList == null) {
                arrayList = segmentList.mediaSegments;
            }
        }
        return buildSegmentList(initialization, j15, j17, j19, j18, segmentTimeline, finalAvailabilityTimeOffset, arrayList, j14, j10);
    }

    public SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate, List<Descriptor> list, long j10, long j11, long j12, long j13, long j14) throws XmlPullParserException, IOException {
        long j15;
        DashManifestParser dashManifestParser = this;
        long j16 = parseLong(xmlPullParser, "timescale", segmentTemplate != null ? segmentTemplate.timescale : 1L);
        long j17 = parseLong(xmlPullParser, "presentationTimeOffset", segmentTemplate != null ? segmentTemplate.presentationTimeOffset : 0L);
        long j18 = parseLong(xmlPullParser, "duration", segmentTemplate != null ? segmentTemplate.duration : C.TIME_UNSET);
        long j19 = parseLong(xmlPullParser, "startNumber", segmentTemplate != null ? segmentTemplate.startNumber : 1L);
        long lastSegmentNumberSupplementalProperty = parseLastSegmentNumberSupplementalProperty(list);
        long finalAvailabilityTimeOffset = getFinalAvailabilityTimeOffset(j12, j13);
        UrlTemplate urlTemplate = dashManifestParser.parseUrlTemplate(xmlPullParser, LinkHeader.Parameters.Media, segmentTemplate != null ? segmentTemplate.mediaTemplate : null);
        UrlTemplate urlTemplate2 = dashManifestParser.parseUrlTemplate(xmlPullParser, "initialization", segmentTemplate != null ? segmentTemplate.initializationTemplate : null);
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
                j15 = j16;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                j15 = j16;
                segmentTimeline = dashManifestParser.parseSegmentTimeline(xmlPullParser, j15, j11);
            } else {
                j15 = j16;
                maybeSkipTag(xmlPullParser);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTemplate")) {
                break;
            }
            dashManifestParser = this;
            finalAvailabilityTimeOffset = finalAvailabilityTimeOffset;
            lastSegmentNumberSupplementalProperty = lastSegmentNumberSupplementalProperty;
            j19 = j19;
            j17 = j17;
            j16 = j15;
        }
        if (segmentTemplate != null) {
            if (initialization == null) {
                initialization = segmentTemplate.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentTemplate.segmentTimeline;
            }
        }
        return buildSegmentTemplate(initialization, j15, j17, j19, lastSegmentNumberSupplementalProperty, j18, segmentTimeline, finalAvailabilityTimeOffset, urlTemplate2, urlTemplate, j14, j10);
    }

    public List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser, long j10, long j11) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long jAddSegmentTimelineElementsToList = 0;
        long j12 = -9223372036854775807L;
        boolean z = false;
        int i10 = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "S")) {
                long j13 = parseLong(xmlPullParser, "t", C.TIME_UNSET);
                if (z) {
                    ArrayList arrayList2 = arrayList;
                    jAddSegmentTimelineElementsToList = addSegmentTimelineElementsToList(arrayList2, jAddSegmentTimelineElementsToList, j12, i10, j13);
                    arrayList = arrayList2;
                }
                if (j13 != C.TIME_UNSET) {
                    jAddSegmentTimelineElementsToList = j13;
                }
                j12 = parseLong(xmlPullParser, "d", C.TIME_UNSET);
                i10 = parseInt(xmlPullParser, "r", 0);
                z = true;
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        if (z) {
            addSegmentTimelineElementsToList(arrayList, jAddSegmentTimelineElementsToList, j12, i10, Util.scaleLargeTimestamp(j11, j10, 1000L));
        }
        return arrayList;
    }

    public RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, LinkHeader.Parameters.Media, "mediaRange");
    }

    public int parseSelectionFlagsFromDashRoleScheme(String str) {
        if (str == null) {
            return 0;
        }
        return (str.equals(MediaTrack.ROLE_FORCED_SUBTITLE) || str.equals("forced-subtitle")) ? 2 : 0;
    }

    public int parseSelectionFlagsFromRoleDescriptors(List<Descriptor> list) {
        int selectionFlagsFromDashRoleScheme = 0;
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            if (a.p("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                selectionFlagsFromDashRoleScheme |= parseSelectionFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return selectionFlagsFromDashRoleScheme;
    }

    public ServiceDescriptionElement parseServiceDescription(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        long j10 = -9223372036854775807L;
        long j11 = -9223372036854775807L;
        long j12 = -9223372036854775807L;
        float f10 = -3.4028235E38f;
        float f11 = -3.4028235E38f;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Latency")) {
                j10 = parseLong(xmlPullParser, "target", C.TIME_UNSET);
                j11 = parseLong(xmlPullParser, "min", C.TIME_UNSET);
                j12 = parseLong(xmlPullParser, "max", C.TIME_UNSET);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "PlaybackRate")) {
                f10 = parseFloat(xmlPullParser, "min", -3.4028235E38f);
                f11 = parseFloat(xmlPullParser, "max", -3.4028235E38f);
            }
            long j13 = j10;
            long j14 = j11;
            long j15 = j12;
            float f12 = f10;
            float f13 = f11;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ServiceDescription")) {
                return new ServiceDescriptionElement(j13, j14, j15, f12, f13);
            }
            j10 = j13;
            j11 = j14;
            j12 = j15;
            f10 = f12;
            f11 = f13;
        }
    }

    public Pair<Integer, Integer> parseTileCountFromProperties(List<Descriptor> list) {
        String str;
        for (int i10 = 0; i10 < list.size(); i10++) {
            Descriptor descriptor = list.get(i10);
            if ((a.p("http://dashif.org/thumbnail_tile", descriptor.schemeIdUri) || a.p("http://dashif.org/guidelines/thumbnail_tile", descriptor.schemeIdUri)) && (str = descriptor.value) != null) {
                String[] strArrSplit = Util.split(str, "x");
                if (strArrSplit.length != 2) {
                    continue;
                } else {
                    try {
                        return Pair.create(Integer.valueOf(Integer.parseInt(strArrSplit[0])), Integer.valueOf(Integer.parseInt(strArrSplit[1])));
                    } catch (NumberFormatException unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    public int parseTvaAudioPurposeCsValue(String str) {
        if (str == null) {
            return 0;
        }
        switch (str) {
        }
        return 0;
    }

    public UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    public UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            if (xmlPullParserNewPullParser.next() == 2 && "MPD".equals(xmlPullParserNewPullParser.getName())) {
                return parseMediaPresentationDescription(xmlPullParserNewPullParser, uri);
            }
            throw ParserException.createForMalformedManifest("inputStream does not contain a valid media presentation description", null);
        } catch (XmlPullParserException e5) {
            throw ParserException.createForMalformedManifest(null, e5);
        }
    }
}
