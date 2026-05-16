import Foundation

enum MediaKind: String {
    case movie = "Movie"
    case series = "TV Series"
}

struct MediaItem: Identifiable, Hashable {
    let id = UUID()
    let title: String
    let subtitle: String
    let year: String
    let duration: String
    let rating: String
    let kind: MediaKind
    let progress: Double
    let palette: [String]
}

let featuredItems: [MediaItem] = [
    MediaItem(title: "Echoes of Dawn", subtitle: "S1 • E6 • New episode", year: "2026", duration: "45m", rating: "8.7", kind: .series, progress: 0.72, palette: ["#d99b4f", "#4b2b19"]),
    MediaItem(title: "Neon Paradox", subtitle: "Cyber noir thriller", year: "2025", duration: "2h 10m", rating: "8.2", kind: .movie, progress: 0.0, palette: ["#2276ff", "#141b3c"]),
    MediaItem(title: "Beyond the Wilds", subtitle: "S2 • E3", year: "2026", duration: "50m", rating: "8.0", kind: .series, progress: 0.28, palette: ["#9cbf6a", "#1d2d21"]),
    MediaItem(title: "The Architect", subtitle: "Premium action cinema", year: "2025", duration: "1h 58m", rating: "7.9", kind: .movie, progress: 0.0, palette: ["#d64a36", "#241112"])
]

let continueWatchingItems: [MediaItem] = [
    MediaItem(title: "Night Watch", subtitle: "Resume episode", year: "2026", duration: "47m left", rating: "8.4", kind: .series, progress: 0.41, palette: ["#315f8d", "#081826"]),
    MediaItem(title: "Deep Current", subtitle: "S1 • E4", year: "2026", duration: "19m left", rating: "8.1", kind: .series, progress: 0.64, palette: ["#1a9ec0", "#06242c"]),
    MediaItem(title: "The Long Orbit", subtitle: "Continue movie", year: "2025", duration: "54m left", rating: "7.8", kind: .movie, progress: 0.52, palette: ["#7961c8", "#18152d"])
]
