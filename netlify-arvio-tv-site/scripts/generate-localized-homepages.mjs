import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const siteRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const source = fs.readFileSync(path.join(siteRoot, "index.html"), "utf8");
const newCopy = JSON.parse(fs.readFileSync(path.join(siteRoot, "scripts/homepage-copy-v2.json"), "utf8"));

const locales = {
  pt: {
    directory: "pt-br",
    lang: "pt-BR",
    locale: "pt_BR",
    canonical: "https://arvio.tv/pt-br/",
    guides: "/pt-br/guias/",
    title: "ARVIO — Player gratuito para Android TV e APK",
    description: "Player gratuito e de código aberto para Android TV com Jellyfin, Plex, Emby, IPTV e addons compatíveis. Baixe o APK e conecte suas próprias fontes, Trakt e Simkl.",
    playBadge: "https://play.google.com/intl/pt-BR/badges/static/images/badges/pt-br_badge_web_generic.png",
  },
  es: {
    directory: "es",
    lang: "es",
    locale: "es_ES",
    canonical: "https://arvio.tv/es/",
    guides: "/es/guias/",
    title: "ARVIO — Reproductor gratis para Android TV y APK",
    description: "Reproductor gratuito y de código abierto para Android TV con Jellyfin, Plex, Emby, IPTV y addons compatibles. Descarga el APK y conecta tus fuentes, Trakt y Simkl.",
    playBadge: "https://play.google.com/intl/es/badges/static/images/badges/es_badge_web_generic.png",
  },
};

const copy = [
  ["Web App", "Web App", "App web"],
  ["Media kit", "Kit de imprensa", "Kit de prensa"],
  ["Guides", "Guias", "Guías"],
  ["ARVIO 2.0.", "ARVIO 2.0.", "ARVIO 2.0."],
  ["On your screen.", "Na sua tela.", "En tu pantalla."],
  ["Explore the actual app on TV, mobile, tablet and the web. Open a screenshot to see it in full.", "Explore o aplicativo real na TV, no celular, no tablet e na web. Abra uma captura para vê-la por inteiro.", "Explora la aplicación real en TV, móvil, tableta y web. Abre una captura para verla completa."],
  ["Demo setup · Phone and tablet use demonstration libraries and channels; content and subscriptions are not included.", "Configuração de demonstração · Celular e tablet usam bibliotecas e canais de demonstração; conteúdo e assinaturas não estão incluídos.", "Configuración de demostración · El móvil y la tableta usan bibliotecas y canales de demostración; no se incluyen contenidos ni suscripciones."],
  ["Screenshots show connected sources and example setups. ARVIO includes no media or subscriptions.", "As capturas mostram fontes conectadas e exemplos de configuração. O ARVIO não inclui conteúdo nem assinaturas.", "Las capturas muestran fuentes conectadas y ejemplos de configuración. ARVIO no incluye contenidos ni suscripciones."],
  ["Library", "Biblioteca", "Biblioteca"],
  ["Homeserver", "Servidor doméstico", "Servidor doméstico"],
  ["Sports", "Esportes", "Deportes"],
  ["Mobile", "Celular", "Móvil"],
  ["Tablet", "Tablet", "Tableta"],
  ["Features", "Recursos", "Funciones"],
  ["Guide", "Guia", "Guía"],
  ["Dashboard", "Painel", "Panel"],
  ["Try Premium", "Testar Premium", "Probar Premium"],
  ["Free Android media hub · Open source · No ads", "Central de mídia Android gratuita · Código aberto · Sem anúncios", "Centro multimedia Android gratuito · Código abierto · Sin anuncios"],
  ["Your media services,", "Seus serviços de mídia,", "Tus servicios multimedia,"],
  ["one organized home.", "em uma casa organizada.", "en un hogar organizado."],
  ["A free, open-source media management hub for Android. Connect your authorized Plex, Emby or Jellyfin server, live TV provider, personal integrations, Telegram files, subtitles, Trakt and Simkl in one remote-first interface.", "Uma central de gerenciamento de mídia gratuita e de código aberto para Android. Conecte seu servidor Plex, Emby ou Jellyfin autorizado, provedor de TV ao vivo, integrações pessoais, arquivos do Telegram, legendas, Trakt e Simkl em uma interface criada para o controle remoto.", "Un centro de gestión multimedia gratuito y de código abierto para Android. Conecta tu servidor Plex, Emby o Jellyfin autorizado, proveedor de TV en vivo, integraciones personales, archivos de Telegram, subtítulos, Trakt y Simkl en una interfaz pensada para el mando."],
  ["Open Dashboard", "Abrir Painel", "Abrir Panel"],
  ["Try Premium free", "Teste o Premium grátis", "Prueba Premium gratis"],
  ["Android APK is free and open source ·", "O APK Android é gratuito e de código aberto ·", "El APK de Android es gratuito y de código abierto ·"],
  ["View releases on GitHub", "Ver versões no GitHub", "Ver versiones en GitHub"],
  ["· ARVIO provides software, not media", "· O ARVIO fornece software, não conteúdo", "· ARVIO proporciona software, no contenido"],
  ["Home", "Início", "Inicio"],
  ["Live TV", "TV ao vivo", "TV en vivo"],
  ["Details", "Detalhes", "Detalles"],
  ["Collections", "Coleções", "Colecciones"],
  ["Player · 4K", "Player · 4K", "Reproductor · 4K"],
  ["Live TV playlists", "Listas de TV ao vivo", "Listas de TV en vivo"],
  ["Max resolution", "Resolução máxima", "Resolución máxima"],
  ["Home servers", "Servidores domésticos", "Servidores domésticos"],
  ["Surround audio", "Áudio surround", "Audio envolvente"],
  ["License", "Licença", "Licencia"],
  ["Open source", "Código aberto", "Código abierto"],
  ["Software, not a content service", "Software, não um serviço de conteúdo", "Software, no un servicio de contenido"],
  ["ARVIO does not host, provide, sell, bundle or redistribute movies, series, channels, playlists or streams.", "O ARVIO não hospeda, fornece, vende, inclui nem redistribui filmes, séries, canais, listas ou transmissões.", "ARVIO no aloja, proporciona, vende, incluye ni redistribuye películas, series, canales, listas o emisiones."],
  ["It is a media browser and player interface for services, servers, files and integrations configured by the user. Use only media you own or are authorized to access, and follow the terms of each connected provider.", "É uma interface de navegação e reprodução para serviços, servidores, arquivos e integrações configurados pelo usuário. Use apenas conteúdo próprio ou autorizado e respeite os termos de cada provedor conectado.", "Es una interfaz de navegación y reproducción para servicios, servidores, archivos e integraciones configurados por el usuario. Usa solo contenido propio o autorizado y respeta las condiciones de cada proveedor conectado."],
  ["Optional add-on · Open-source web app", "Complemento opcional · Web app de código aberto", "Complemento opcional · Aplicación web de código abierto"],
  ["Take your media hub beyond Android.", "Leve sua central de mídia além do Android.", "Lleva tu centro multimedia más allá de Android."],
  ["The Android APK stays completely free. Optional Premium adds the open-source ARVIO Web interface, with the same profiles, libraries, personal integrations and progress on Windows, Mac, iPhone, iPad and other browsers.", "O APK Android continua totalmente gratuito. O Premium opcional adiciona a interface ARVIO Web de código aberto, com os mesmos perfis, bibliotecas, integrações pessoais e progresso no Windows, Mac, iPhone, iPad e outros navegadores.", "El APK de Android sigue siendo completamente gratuito. Premium opcional añade la interfaz ARVIO Web de código abierto, con los mismos perfiles, bibliotecas, integraciones personales y progreso en Windows, Mac, iPhone, iPad y otros navegadores."],
  ["Smart-TV browser", "Navegador de Smart TV", "Navegador de Smart TV"],
  ["Everything stays connected through ARVIO Cloud", "Tudo permanece conectado pelo ARVIO Cloud", "Todo permanece conectado mediante ARVIO Cloud"],
  ["Play authorized media from your connected services across devices", "Reproduza conteúdo autorizado dos seus serviços conectados em vários dispositivos", "Reproduce contenido autorizado de tus servicios conectados en varios dispositivos"],
  ["Save compatible files when your provider permits downloads", "Salve arquivos compatíveis quando seu provedor permitir downloads", "Guarda archivos compatibles cuando tu proveedor permita descargas"],
  ["Browser playback and one-click VLC handoff", "Reprodução no navegador e envio ao VLC com um clique", "Reproducción en navegador y envío a VLC con un clic"],
  ["Web app source is public on GitHub", "O código do web app é público no GitHub", "El código de la aplicación web es público en GitHub"],
  ["Android and TV APK remains completely free", "O APK para Android e TV continua totalmente gratuito", "El APK para Android y TV sigue siendo completamente gratuito"],
  ["Try Premium free for 3 days", "Teste o Premium grátis por 3 dias", "Prueba Premium gratis durante 3 días"],
  ["Get Premium on Ko-fi", "Assine o Premium no Ko-fi", "Obtén Premium en Ko-fi"],
  ["View the open-source web app", "Ver o código aberto do web app", "Ver el código abierto de la aplicación web"],
  ["View web source", "Ver código do web app", "Ver código de la aplicación web"],
  ["/ month after · no payment details for trial", "/ mês depois · sem dados de pagamento no teste", "/ mes después · sin datos de pago para la prueba"],
  ["10,000+ users", "Mais de 10.000 usuários", "Más de 10.000 usuarios"],
  ["10+ contributors", "Mais de 10 colaboradores", "Más de 10 colaboradores"],
  ["Actual ARVIO Web interface", "Interface real do ARVIO Web", "Interfaz real de ARVIO Web"],
  ["Media management", "Gerenciamento de mídia", "Gestión multimedia"],
  ["Your live TV provider", "Seu provedor de TV ao vivo", "Tu proveedor de TV en vivo"],
  ["Your Telegram files", "Seus arquivos do Telegram", "Tus archivos de Telegram"],
  ["User-configured addons", "Addons configurados pelo usuário", "Addons configurados por el usuario"],
  ["Multi-profile", "Vários perfis", "Varios perfiles"],
  ["Auto-play", "Reprodução automática", "Reproducción automática"],
  ["01 / Features", "01 / Recursos", "01 / Funciones"],
  ["Every library and service,", "Cada biblioteca e serviço,", "Cada biblioteca y servicio,"],
  ["organized for TV.", "organizado para a TV.", "organizado para la TV."],
  ["LIVE TV", "TV AO VIVO", "TV EN VIVO"],
  ["Your live TV service, organized.", "Seu serviço de TV ao vivo, organizado.", "Tu servicio de TV en vivo, organizado."],
  ["Connect an authorized M3U or Xtream provider, preserve provider order, manage groups and favorites, browse EPG data, and use catch-up when the provider supports it.", "Conecte um provedor M3U ou Xtream autorizado, preserve a ordem do provedor, gerencie grupos e favoritos, consulte os dados do EPG e use catch-up quando houver suporte.", "Conecta un proveedor M3U o Xtream autorizado, conserva el orden del proveedor, gestiona grupos y favoritos, consulta los datos de EPG y usa catch-up cuando sea compatible."],
  ["EPG / Favorites / Mini-player", "EPG / Favoritos / Miniplayer", "EPG / Favoritos / Minirreproductor"],
  ["CATALOGS", "CATÁLOGOS", "CATÁLOGOS"],
  ["Your servers and libraries, in your order.", "Seus servidores e bibliotecas, na sua ordem.", "Tus servidores y bibliotecas, en tu orden."],
  ["Browse your own Plex, Emby or Jellyfin libraries alongside metadata collections and public Trakt or MDBList lists, then choose the order for every profile.", "Navegue pelas suas bibliotecas Plex, Emby ou Jellyfin junto com coleções de metadados e listas públicas do Trakt ou MDBList, e escolha a ordem para cada perfil.", "Explora tus bibliotecas Plex, Emby o Jellyfin junto con colecciones de metadatos y listas públicas de Trakt o MDBList, y elige el orden para cada perfil."],
  ["ADDONS", "ADDONS", "ADDONS"],
  ["Compatible addons, configured by you.", "Addons compatíveis, configurados por você.", "Addons compatibles, configurados por ti."],
  ["Connect compatible addon manifests for metadata, subtitles or media locations. ARVIO does not operate, endorse or bundle third-party content providers.", "Conecte manifestos de addons compatíveis para metadados, legendas ou locais de mídia. O ARVIO não opera, recomenda nem inclui provedores de conteúdo de terceiros.", "Conecta manifiestos de addons compatibles para metadatos, subtítulos o ubicaciones multimedia. ARVIO no opera, recomienda ni incluye proveedores de contenido de terceros."],
  ["Public Domain", "Domínio público", "Dominio público"],
  ["Your Telegram files, connected privately.", "Seus arquivos do Telegram, conectados de forma privada.", "Tus archivos de Telegram, conectados de forma privada."],
  ["Sign in by QR code or phone, then search video files in channels and groups accessible through your own Telegram account.", "Entre por QR code ou telefone e pesquise arquivos de vídeo em canais e grupos acessíveis pela sua própria conta do Telegram.", "Inicia sesión mediante código QR o teléfono y busca archivos de vídeo en canales y grupos accesibles desde tu propia cuenta de Telegram."],
  ["QR sign-in", "Login por QR", "Inicio por QR"],
  ["Channels", "Canais", "Canales"],
  ["Groups", "Grupos", "Grupos"],
  ["Video files", "Arquivos de vídeo", "Archivos de vídeo"],
  ["Private account", "Conta privada", "Cuenta privada"],
  ["CLOUD", "NUVEM", "NUBE"],
  ["Cloud sync without typing passwords.", "Sincronização na nuvem sem digitar senhas.", "Sincronización en la nube sin escribir contraseñas."],
  ["Pair by QR code, then sync profiles, addons, catalogs, and settings across TVs without a keyboard.", "Emparelhe por QR code e sincronize perfis, addons, catálogos e configurações entre TVs sem teclado.", "Vincula mediante código QR y sincroniza perfiles, addons, catálogos y ajustes entre televisores sin teclado."],
  ["TRACKING", "ACOMPANHAMENTO", "SEGUIMIENTO"],
  ["Trakt and Simkl that follow you.", "Trakt e Simkl que acompanham você.", "Trakt y Simkl que te siguen."],
  ["Choose either tracker for watchlists, history, progress, Continue Watching and scrobbling, or keep both services aligned across devices.", "Escolha qualquer rastreador para listas, histórico, progresso, Continuar assistindo e scrobbling, ou mantenha os dois serviços alinhados entre dispositivos.", "Elige cualquiera de los servicios para listas, historial, progreso, Seguir viendo y scrobbling, o mantén ambos sincronizados entre dispositivos."],
  ["COLLECTIONS", "COLEÇÕES", "COLECCIONES"],
  ["Discovery rows, ready to organize.", "Fileiras de descoberta, prontas para organizar.", "Filas de descubrimiento, listas para organizar."],
  ["Browse metadata collections for major services and genres, then connect your own authorized service or server separately for playback.", "Explore coleções de metadados de serviços e gêneros populares e conecte separadamente seu serviço ou servidor autorizado para reprodução.", "Explora colecciones de metadatos de servicios y géneros populares y conecta por separado tu servicio o servidor autorizado para reproducir."],
  ["10+ services / Genres / Fast focus", "Mais de 10 serviços / Gêneros / Foco rápido", "Más de 10 servicios / Géneros / Foco rápido"],
  ["SESSIONS", "SESSÕES", "SESIONES"],
  ["Profiles, watchlists, and Up Next.", "Perfis, listas e Próximos.", "Perfiles, listas y A continuación."],
  ["Continue Watching, per-profile watch state, watchlists, and auto-play countdowns keep the home screen useful without turning it into admin work.", "Continuar assistindo, estado por perfil, listas e contagens da reprodução automática mantêm a tela inicial útil sem transformá-la em trabalho administrativo.", "Seguir viendo, estado por perfil, listas y cuentas atrás de reproducción automática mantienen útil la pantalla de inicio sin convertirla en trabajo administrativo."],
  ["Profiles / Watchlists / Up Next", "Perfis / Listas / Próximos", "Perfiles / Listas / A continuación"],
  ["SUBTITLES", "LEGENDAS", "SUBTÍTULOS"],
  ["Subtitles and audio, under control.", "Legendas e áudio sob controle.", "Subtítulos y audio bajo control."],
  ["Select embedded or external tracks, filter preferred languages, preload subtitle options, find better timing matches, and optionally use your own API key for AI-assisted translation.", "Selecione faixas internas ou externas, filtre idiomas preferidos, pré-carregue opções de legendas, encontre sincronizações melhores e use opcionalmente sua própria chave de API para tradução com IA.", "Selecciona pistas internas o externas, filtra idiomas preferidos, precarga opciones de subtítulos, encuentra mejores sincronizaciones y usa opcionalmente tu propia clave API para traducción con IA."],
  ["Audio tracks", "Faixas de áudio", "Pistas de audio"],
  ["AI tools", "Ferramentas de IA", "Herramientas de IA"],
  ["SOURCES", "FONTES", "FUENTES"],
  ["Choose the version that fits.", "Escolha a versão ideal.", "Elige la versión adecuada."],
  ["Compare available versions by addon order, quality, size, codec, language and cache status, or let autoplay choose the best compatible result.", "Compare versões disponíveis por ordem de addon, qualidade, tamanho, codec, idioma e cache, ou deixe a reprodução automática escolher o melhor resultado compatível.", "Compara versiones disponibles por orden de addon, calidad, tamaño, códec, idioma y caché, o deja que la reproducción automática elija el mejor resultado compatible."],
  ["Size", "Tamanho", "Tamaño"],
  ["Language", "Idioma", "Idioma"],
  ["Autoplay", "Reprodução automática", "Reproducción automática"],
  ["PLAYER", "PLAYER", "REPRODUCTOR"],
  ["Native playback across formats.", "Reprodução nativa em vários formatos.", "Reproducción nativa en múltiples formatos."],
  ["Media3 and FFmpeg support modern video, audio and subtitle formats, with frame-rate matching, passthrough, track controls, resume points and TV-remote navigation.", "Media3 e FFmpeg suportam formatos modernos de vídeo, áudio e legendas, com ajuste de taxa de quadros, passthrough, controle de faixas, retomada e navegação pelo controle remoto.", "Media3 y FFmpeg admiten formatos modernos de vídeo, audio y subtítulos, con ajuste de frecuencia, passthrough, controles de pistas, reanudación y navegación con el mando."],
  ["Resume", "Retomar", "Reanudar"],
  ["PREMIUM WEB", "WEB PREMIUM", "WEB PREMIUM"],
  ["Browser access and permitted downloads.", "Acesso pelo navegador e downloads permitidos.", "Acceso desde navegador y descargas permitidas."],
  ["The optional open-source web interface adds Windows, Mac, iPhone and iPad access, VLC handoff, and downloads for compatible files when your connected provider permits them.", "A interface web opcional de código aberto adiciona acesso no Windows, Mac, iPhone e iPad, envio ao VLC e downloads de arquivos compatíveis quando o provedor conectado permitir.", "La interfaz web opcional de código abierto añade acceso en Windows, Mac, iPhone y iPad, envío a VLC y descargas de archivos compatibles cuando el proveedor conectado lo permita."],
  ["Downloads", "Downloads", "Descargas"],
  ["02 / Player", "02 / Player", "02 / Reproductor"],
  ["A player that", "Um player que", "Un reproductor que"],
  ["stays out of the way.", "não atrapalha.", "no estorba."],
  ["Video", "Vídeo", "Vídeo"],
  ["Audio", "Áudio", "Audio"],
  ["Containers &amp; streaming", "Contêineres e streaming", "Contenedores y streaming"],
  ["Powered by ExoPlayer (Media3) with the FFmpeg extension. Passthrough and hardware decode depend on your device and Android audio pipeline.", "Desenvolvido com ExoPlayer (Media3) e a extensão FFmpeg. Passthrough e decodificação por hardware dependem do dispositivo e do sistema de áudio do Android.", "Desarrollado con ExoPlayer (Media3) y la extensión FFmpeg. El passthrough y la decodificación por hardware dependen del dispositivo y del sistema de audio de Android."],
  ["03 / Compared", "03 / Comparação", "03 / Comparación"],
  ["Why ARVIO", "Por que o ARVIO", "Por qué ARVIO"],
  ["feels better on TV.", "funciona melhor na TV.", "se siente mejor en TV."],
  ["Other media hubs", "Outras centrais de mídia", "Otros centros multimedia"],
  ["Server‑based apps", "Apps baseados em servidor", "Apps basadas en servidor"],
  ["Stock TV app", "App padrão da TV", "App incluida en la TV"],
  ["Built for Android TV remote", "Criado para o controle da Android TV", "Creado para el mando de Android TV"],
  ["Native", "Nativo", "Nativo"],
  ["Skin‑dependent", "Depende do tema", "Depende del tema"],
  ["Yes", "Sim", "Sí"],
  ["User-configured addon manifests", "Manifestos de addons configurados pelo usuário", "Manifiestos de addons configurados por el usuario"],
  ["Supported", "Compatível", "Compatible"],
  ["No", "Não", "No"],
  ["Metadata catalogs &amp; collections", "Catálogos e coleções de metadados", "Catálogos y colecciones de metadatos"],
  ["10+ layouts", "Mais de 10 layouts", "Más de 10 diseños"],
  ["Manual setup", "Configuração manual", "Configuración manual"],
  ["Single feed", "Feed único", "Feed único"],
  ["Connected", "Conectado", "Conectado"],
  ["Varies", "Varia", "Varía"],
  ["Telegram source search", "Pesquisa de fontes no Telegram", "Búsqueda de fuentes en Telegram"],
  ["Built-in", "Integrado", "Integrado"],
  ["Trakt sync", "Sincronização Trakt", "Sincronización de Trakt"],
  ["First‑class", "Nativo", "Nativo"],
  ["Add‑on", "Complemento", "Complemento"],
  ["Authorized Live TV (M3U / Xtream)", "TV ao vivo autorizada (M3U / Xtream)", "TV en vivo autorizada (M3U / Xtream)"],
  ["Provider support", "Suporte do provedor", "Compatibilidad del proveedor"],
  ["Paid tier", "Plano pago", "Plan de pago"],
  ["No server required", "Não exige servidor", "No requiere servidor"],
  ["Needs server", "Exige servidor", "Requiere servidor"],
  ["Free &amp; ad‑free", "Gratuito e sem anúncios", "Gratuito y sin anuncios"],
  ["Freemium", "Freemium", "Freemium"],
  ["Ads", "Anúncios", "Anuncios"],
  ["Compared in good faith · Each tool has its strengths — pick what fits your couch.", "Comparado de boa-fé · Cada ferramenta tem seus pontos fortes — escolha a que combina com você.", "Comparado de buena fe · Cada herramienta tiene sus puntos fuertes: elige la que mejor te encaje."],
  ["04 / Screens", "04 / Telas", "04 / Pantallas"],
  ["Built for the", "Criado para", "Creado para"],
  ["10‑foot", "3 metros", "3 metros"],
  ["— and the 6‑inch.", "— e para 6 polegadas.", "— y para 6 pulgadas."],
  ["TV · Details", "TV · Detalhes", "TV · Detalles"],
  ["TV · Live TV", "TV · TV ao vivo", "TV · TV en vivo"],
  ["TV · Catalogs", "TV · Catálogos", "TV · Catálogos"],
  ["Built-in collections", "Coleções integradas", "Colecciones integradas"],
  ["Mobile · Home", "Celular · Início", "Móvil · Inicio"],
  ["On your phone, too", "Também no seu celular", "También en tu teléfono"],
  ["// Captured on Android TV &amp; Pixel — actual ARVIO UI", "// Capturado na Android TV e Pixel — interface real do ARVIO", "// Capturado en Android TV y Pixel — interfaz real de ARVIO"],
  ["05 / Devices", "05 / Dispositivos", "05 / Dispositivos"],
  ["Android TV", "Android TV", "Android TV"],
  ["first", "primeiro", "primero"],
  [", mobile alongside.", ", com o celular ao lado.", ", con el móvil al lado."],
  ["Primary · D‑pad tuned", "Principal · Otimizado para D-pad", "Principal · Optimizado para D-pad"],
  ["Android phone", "Celular Android", "Teléfono Android"],
  ["Companion UI", "Interface complementar", "Interfaz complementaria"],
  ["Android tablet", "Tablet Android", "Tableta Android"],
  ["06 / Premium", "06 / Premium", "06 / Premium"],
  ["ARVIO on every", "ARVIO em qualquer", "ARVIO en cualquier"],
  ["other screen.", "outra tela.", "otra pantalla."],
  ["Windows, Mac and mobile", "Windows, Mac e celular", "Windows, Mac y móvil"],
  ["Use the open-source ARVIO Web experience on Windows, Mac, iPhone, iPad, Chromebook and smart-TV browsers at web.arvio.tv. No install, APK or sideloading required.", "Use a experiência ARVIO Web de código aberto no Windows, Mac, iPhone, iPad, Chromebook e navegadores de Smart TV em web.arvio.tv. Não exige instalação, APK nem sideload.", "Usa la experiencia ARVIO Web de código abierto en Windows, Mac, iPhone, iPad, Chromebook y navegadores de Smart TV en web.arvio.tv. No requiere instalación, APK ni sideload."],
  ["Synced with the app", "Sincronizado com o app", "Sincronizado con la app"],
  ["Profiles, Library, Continue Watching, Trakt and Simkl stay in sync through ARVIO Cloud. Access authorized connected services across your screens and save compatible files when the provider allows it.", "Perfis, Biblioteca, Continuar assistindo, Trakt e Simkl permanecem sincronizados pelo ARVIO Cloud. Acesse serviços autorizados conectados nas suas telas e salve arquivos compatíveis quando o provedor permitir.", "Perfiles, Biblioteca, Seguir viendo, Trakt y Simkl permanecen sincronizados mediante ARVIO Cloud. Accede a servicios autorizados conectados en tus pantallas y guarda archivos compatibles cuando el proveedor lo permita."],
  ["Try it free for 3 days", "Teste grátis por 3 dias", "Pruébalo gratis durante 3 días"],
  ["Start after connecting ARVIO Cloud, then continue for $2.99/month through Ko‑fi. The Android/TV app remains 100% free.", "Comece após conectar o ARVIO Cloud e continue por US$ 2,99/mês pelo Ko‑fi. O app para Android/TV continua 100% gratuito.", "Empieza tras conectar ARVIO Cloud y continúa por 2,99 $/mes mediante Ko‑fi. La app para Android/TV sigue siendo 100 % gratuita."],
  ["No payment details for trial", "Sem dados de pagamento no teste", "Sin datos de pago para la prueba"],
  ["Web · Live TV", "Web · TV ao vivo", "Web · TV en vivo"],
  ["Web · Catalogs", "Web · Catálogos", "Web · Catálogos"],
  ["Web · Details", "Web · Detalhes", "Web · Detalles"],
  ["// Captured on iPad — actual ARVIO Web UI", "// Capturado no iPad — interface real do ARVIO Web", "// Capturado en iPad — interfaz real de ARVIO Web"],
  ["Browsers decode fewer formats than a native media player. Compatible media from your connected services can play in the browser; heavy 4K HEVC, Dolby Vision, REMUX files, lossless audio and some live TV providers may require the one-tap VLC handoff on iPhone, iPad, Android, macOS or Windows. Device and provider support varies.", "Navegadores decodificam menos formatos que um player nativo. Conteúdo compatível dos serviços conectados pode ser reproduzido no navegador; arquivos 4K HEVC, Dolby Vision, REMUX, áudio sem perdas e alguns provedores de TV ao vivo podem exigir o envio ao VLC com um toque no iPhone, iPad, Android, macOS ou Windows. O suporte varia conforme o dispositivo e o provedor.", "Los navegadores decodifican menos formatos que un reproductor nativo. El contenido compatible de los servicios conectados puede reproducirse en el navegador; archivos 4K HEVC, Dolby Vision, REMUX, audio sin pérdida y algunos proveedores de TV en vivo pueden requerir el envío a VLC con un toque en iPhone, iPad, Android, macOS o Windows. La compatibilidad varía según el dispositivo y el proveedor."],
  ["Ko‑fi membership", "Assinatura Ko‑fi", "Membresía Ko‑fi"],
  ["Web app source", "Código do web app", "Código de la aplicación web"],
  ["07 / Setup", "07 / Configuração", "07 / Configuración"],
  ["From install to", "Da instalação à", "De la instalación a"],
  ["your media hub", "sua central de mídia", "tu centro multimedia"],
  ["in minutes.", "em minutos.", "en minutos."],
  ["Install from Play Store", "Instale pela Play Store", "Instala desde Play Store"],
  ["Open Google Play on your Android TV, search for ARVIO, hit install. No account, no setup wizard, no upsells.", "Abra o Google Play na Android TV, procure por ARVIO e instale. Sem conta, assistente obrigatório ou ofertas adicionais.", "Abre Google Play en tu Android TV, busca ARVIO e instala. Sin cuenta, asistente obligatorio ni ventas adicionales."],
  ["Under a minute", "Menos de um minuto", "Menos de un minuto"],
  ["Connect what you already use", "Conecte o que você já usa", "Conecta lo que ya utilizas"],
  ["Add your Plex, Emby or Jellyfin server, your Telegram account, an authorized live TV provider, or a compatible addon manifest. ARVIO includes no media service or provider account.", "Adicione seu servidor Plex, Emby ou Jellyfin, sua conta do Telegram, um provedor autorizado de TV ao vivo ou um manifesto de addon compatível. O ARVIO não inclui serviço de mídia nem conta de provedor.", "Añade tu servidor Plex, Emby o Jellyfin, tu cuenta de Telegram, un proveedor autorizado de TV en vivo o un manifiesto de addon compatible. ARVIO no incluye ningún servicio multimedia ni cuenta de proveedor."],
  ["Two minutes", "Dois minutos", "Dos minutos"],
  ["Organize and use your library", "Organize e use sua biblioteca", "Organiza y usa tu biblioteca"],
  ["Arrange rows and profiles, browse metadata, open authorized media from a connected service, and keep watch state aligned through Trakt, Simkl or ARVIO Cloud.", "Organize fileiras e perfis, explore metadados, abra conteúdo autorizado de um serviço conectado e mantenha o estado alinhado pelo Trakt, Simkl ou ARVIO Cloud.", "Organiza filas y perfiles, explora metadatos, abre contenido autorizado de un servicio conectado y mantén el estado sincronizado mediante Trakt, Simkl o ARVIO Cloud."],
  ["Ready to browse", "Pronto para navegar", "Listo para explorar"],
  ["08 / Beginner Guide", "08 / Guia para iniciantes", "08 / Guía para principiantes"],
  ["Set up ARVIO", "Configure o ARVIO", "Configura ARVIO"],
  ["the clean way.", "do jeito certo.", "de forma sencilla."],
  ["Setup roadmap", "Roteiro de configuração", "Ruta de configuración"],
  ["From fresh install to a synced TV home.", "Da instalação nova a uma TV sincronizada.", "De una instalación nueva a un hogar de TV sincronizado."],
  ["Follow the path in order: create your space, connect sync, add your authorized libraries and services, then tune playback once.", "Siga as etapas em ordem: crie seu espaço, conecte a sincronização, adicione bibliotecas e serviços autorizados e ajuste a reprodução.", "Sigue los pasos en orden: crea tu espacio, conecta la sincronización, añade bibliotecas y servicios autorizados y ajusta la reproducción."],
  ["Profile and cloud sync", "Perfil e sincronização na nuvem", "Perfil y sincronización en la nube"],
  ["Servers, Telegram, catalogs, live TV, and integrations", "Servidores, Telegram, catálogos, TV ao vivo e integrações", "Servidores, Telegram, catálogos, TV en vivo e integraciones"],
  ["Playback polish and troubleshooting", "Ajustes de reprodução e solução de problemas", "Ajustes de reproducción y solución de problemas"],
  ["ARVIO provides media-management software only. It does not host, sell, bundle or provide media, provider accounts, playlists or streams. Connect only services and files you own or are authorized to access. Torrent-based sources are not supported.", "O ARVIO fornece apenas software de gerenciamento de mídia. Não hospeda, vende, inclui nem fornece conteúdo, contas de provedor, listas ou transmissões. Conecte apenas serviços e arquivos próprios ou autorizados. Fontes baseadas em torrent não são compatíveis.", "ARVIO proporciona únicamente software de gestión multimedia. No aloja, vende, incluye ni proporciona contenido, cuentas de proveedor, listas o emisiones. Conecta solo servicios y archivos propios o autorizados. Las fuentes basadas en torrent no son compatibles."],
  ["Manage settings and integrations", "Gerenciar configurações e integrações", "Gestionar ajustes e integraciones"],
  ["Open web app", "Abrir web app", "Abrir aplicación web"],
  ["Start", "Início", "Inicio"],
  ["Create your profile", "Crie seu perfil", "Crea tu perfil"],
  ["Choose a name, color, and avatar. Use separate profiles for family, kids, guests, or different watch histories.", "Escolha nome, cor e avatar. Use perfis separados para família, crianças, convidados ou históricos diferentes.", "Elige nombre, color y avatar. Usa perfiles separados para familia, niños, invitados o historiales diferentes."],
  ["Personal home screen", "Tela inicial pessoal", "Pantalla de inicio personal"],
  ["Sync", "Sincronização", "Sincronización"],
  ["Pair ARVIO Cloud", "Emparelhe o ARVIO Cloud", "Vincula ARVIO Cloud"],
  ["Go to Settings, Accounts, ARVIO Cloud, then scan the QR code with your phone. This keeps profiles, settings, catalogs, live TV state, watch state, and profile avatars aligned across your devices.", "Vá a Configurações, Contas, ARVIO Cloud e escaneie o QR code com o celular. Isso mantém perfis, configurações, catálogos, estado da TV ao vivo, histórico e avatares alinhados entre dispositivos.", "Ve a Ajustes, Cuentas, ARVIO Cloud y escanea el código QR con el teléfono. Así se mantienen perfiles, ajustes, catálogos, estado de TV en vivo, historial y avatares sincronizados entre dispositivos."],
  ["QR pair", "Pareamento por QR", "Vinculación por QR"],
  ["Cloud backup", "Backup na nuvem", "Copia en la nube"],
  ["Optional", "Opcional", "Opcional"],
  ["Connect Trakt or Simkl", "Conecte Trakt ou Simkl", "Conecta Trakt o Simkl"],
  ["Choose one or both services for synced watch history, watchlists, scrobbling, Continue Watching and tracker libraries across devices.", "Escolha um ou ambos para sincronizar histórico, listas, scrobbling, Continuar assistindo e bibliotecas entre dispositivos.", "Elige uno o ambos para sincronizar historial, listas, scrobbling, Seguir viendo y bibliotecas entre dispositivos."],
  ["Watchlists", "Listas", "Listas"],
  ["Libraries", "Bibliotecas", "Bibliotecas"],
  ["Connect Plex, Emby, or Jellyfin", "Conecte Plex, Emby ou Jellyfin", "Conecta Plex, Emby o Jellyfin"],
  ["Open Settings, Home Server. For Emby or Jellyfin, choose Add server, enter the server URL, then add your username and password or token. For Plex, choose Connect with code, optionally enter a server URL, then scan the QR code or open the auth page and confirm the code.", "Abra Configurações, Servidor doméstico. Para Emby ou Jellyfin, escolha Adicionar servidor, informe a URL e depois usuário e senha ou token. Para Plex, escolha Conectar com código, informe opcionalmente a URL do servidor e escaneie o QR code ou abra a página de autenticação para confirmar.", "Abre Ajustes, Servidor doméstico. Para Emby o Jellyfin, elige Añadir servidor, introduce la URL y después usuario y contraseña o token. Para Plex, elige Conectar con código, introduce opcionalmente la URL del servidor y escanea el código QR o abre la página de autenticación para confirmar."],
  ["Use server libraries in ARVIO", "Use bibliotecas do servidor no ARVIO", "Usa bibliotecas del servidor en ARVIO"],
  ["Connected home servers can appear as sources and catalog rows. Name each server clearly, then use Test connection to confirm the active profile can reach every server before browsing.", "Servidores conectados podem aparecer como fontes e fileiras de catálogo. Dê nomes claros e use Testar conexão para confirmar que o perfil ativo alcança cada servidor antes de navegar.", "Los servidores conectados pueden aparecer como fuentes y filas de catálogo. Pon nombres claros y usa Probar conexión para confirmar que el perfil activo accede a cada servidor antes de explorar."],
  ["Catalog rows", "Fileiras de catálogo", "Filas de catálogo"],
  ["Test connection", "Testar conexão", "Probar conexión"],
  ["Sources", "Fontes", "Fuentes"],
  ["Connect Telegram", "Conecte o Telegram", "Conecta Telegram"],
  ["Open Settings, Accounts, Telegram. On TV, scan the QR code with Telegram from your phone. On mobile, enter the phone number for your Telegram account, then confirm the code sent to Telegram. ARVIO can then search your own channels and groups for matching video files.", "Abra Configurações, Contas, Telegram. Na TV, escaneie o QR code com o Telegram do celular. No celular, informe o número da conta e confirme o código enviado pelo Telegram. O ARVIO poderá pesquisar arquivos de vídeo nos seus próprios canais e grupos.", "Abre Ajustes, Cuentas, Telegram. En TV, escanea el código QR con Telegram desde el teléfono. En móvil, introduce el número de la cuenta y confirma el código enviado por Telegram. ARVIO podrá buscar archivos de vídeo en tus propios canales y grupos."],
  ["Expand", "Expandir", "Ampliar"],
  ["Add catalogs and your live TV provider", "Adicione catálogos e seu provedor de TV ao vivo", "Añade catálogos y tu proveedor de TV en vivo"],
  ["Add public Trakt or MDBList metadata rows from Settings, Catalogs. For live TV, connect an authorized M3U or Xtream provider with an optional EPG URL. ARVIO does not supply channels, playlists or provider credentials.", "Adicione fileiras públicas de metadados do Trakt ou MDBList em Configurações, Catálogos. Para TV ao vivo, conecte um provedor M3U ou Xtream autorizado com URL de EPG opcional. O ARVIO não fornece canais, listas nem credenciais.", "Añade filas públicas de metadatos de Trakt o MDBList en Ajustes, Catálogos. Para TV en vivo, conecta un proveedor M3U o Xtream autorizado con una URL EPG opcional. ARVIO no proporciona canales, listas ni credenciales."],
  ["Add compatible sources", "Adicione fontes compatíveis", "Añade fuentes compatibles"],
  ["Connect compatible HTTP or account-backed integrations only when you are authorized to access their media. ARVIO supplies no files, links or third-party accounts. Torrent-based sources are not supported.", "Conecte integrações HTTP ou vinculadas a conta apenas quando tiver autorização para acessar o conteúdo. O ARVIO não fornece arquivos, links nem contas de terceiros. Fontes baseadas em torrent não são compatíveis.", "Conecta integraciones HTTP o vinculadas a una cuenta solo cuando tengas autorización para acceder al contenido. ARVIO no proporciona archivos, enlaces ni cuentas de terceros. Las fuentes basadas en torrent no son compatibles."],
  ["User configured", "Configurado pelo usuário", "Configurado por el usuario"],
  ["Account backed", "Vinculado a conta", "Vinculado a cuenta"],
  ["No torrent sources", "Sem fontes torrent", "Sin fuentes torrent"],
  ["Polish", "Ajustes", "Ajustes"],
  ["Tune playback", "Ajuste a reprodução", "Ajusta la reproducción"],
  ["Set subtitle language, content language, minimum quality, DNS provider, frame-rate matching, volume boost, and card layout from Settings. Player menus also handle subtitles and audio tracks.", "Defina idioma das legendas e do conteúdo, qualidade mínima, DNS, ajuste de taxa de quadros, aumento de volume e layout dos cards em Configurações. Os menus do player também controlam legendas e faixas de áudio.", "Define idioma de subtítulos y contenido, calidad mínima, DNS, ajuste de frecuencia, aumento de volumen y diseño de tarjetas en Ajustes. Los menús del reproductor también controlan subtítulos y pistas de audio."],
  ["Subtitles", "Legendas", "Subtítulos"],
  ["Frame rate", "Taxa de quadros", "Frecuencia de imagen"],
  ["Quality filters", "Filtros de qualidade", "Filtros de calidad"],
  ["Support", "Suporte", "Soporte"],
  ["Fix common issues", "Corrija problemas comuns", "Soluciona problemas comunes"],
  ["If something fails, verify the URL on your phone first, reload the playlist or source, test Home Server connections, check account pairing, clear Telegram cache if needed, then restart ARVIO and the TV.", "Se algo falhar, verifique primeiro a URL no celular, recarregue a lista ou fonte, teste as conexões de servidores, confira o pareamento, limpe o cache do Telegram se necessário e reinicie o ARVIO e a TV.", "Si algo falla, verifica primero la URL en el teléfono, recarga la lista o fuente, prueba las conexiones de servidores, revisa la vinculación, limpia la caché de Telegram si hace falta y reinicia ARVIO y la TV."],
  ["Reload", "Recarregar", "Recargar"],
  ["Connection test", "Teste de conexão", "Prueba de conexión"],
  ["Restart once", "Reiniciar uma vez", "Reiniciar una vez"],
  ["09 / Open", "09 / Aberto", "09 / Abierto"],
  ["Open source.", "Código aberto.", "Código abierto."],
  ["Built in the open.", "Desenvolvido abertamente.", "Desarrollado abiertamente."],
  ["ARVIO is open source. Source code, releases, APKs, issues, and contributions all live on GitHub.", "O ARVIO é de código aberto. Código-fonte, versões, APKs, problemas e contribuições ficam no GitHub.", "ARVIO es de código abierto. El código, las versiones, los APK, los problemas y las contribuciones están en GitHub."],
  ["GitHub repo", "Repositório GitHub", "Repositorio GitHub"],
  ["10 / Questions", "10 / Perguntas", "10 / Preguntas"],
  ["Questions before", "Perguntas antes de", "Preguntas antes de"],
  ["you install.", "instalar.", "instalar."],
  ["Is ARVIO free? Are there ads?", "O ARVIO é gratuito? Tem anúncios?", "¿ARVIO es gratuito? ¿Tiene anuncios?"],
  ["The Android/TV app is free and open source, with no ads, third-party behavioral analytics, or gated app features. Release builds can send limited crash diagnostics and one pseudonymous daily app-open event to help maintain reliability. This can be disabled per device in Settings. The one optional paid service is", "O app para Android/TV é gratuito e de código aberto, sem anúncios, análises comportamentais de terceiros ou recursos bloqueados. As versões oficiais podem enviar diagnósticos limitados de falhas e um evento diário pseudônimo de abertura para ajudar na confiabilidade. Isso pode ser desativado por dispositivo nas Configurações. O único serviço pago opcional é", "La app para Android/TV es gratuita y de código abierto, sin anuncios, análisis conductual de terceros ni funciones bloqueadas. Las versiones oficiales pueden enviar diagnósticos limitados de fallos y un evento diario seudónimo de apertura para mejorar la fiabilidad. Esto puede desactivarse por dispositivo en Ajustes. El único servicio de pago opcional es"],
  [", the browser interface for Windows, Mac, iPhone, iPad and more, at $2.99/month because it uses hosted infrastructure and bandwidth. The Android app never requires it.", ", a interface de navegador para Windows, Mac, iPhone, iPad e outros, por US$ 2,99/mês devido à infraestrutura e largura de banda. O app Android nunca exige isso.", ", la interfaz de navegador para Windows, Mac, iPhone, iPad y otros, por 2,99 $/mes debido a la infraestructura y el ancho de banda. La app Android nunca lo exige."],
  ["What data does ARVIO collect, and can I opt out?", "Quais dados o ARVIO coleta e posso desativar?", "¿Qué datos recopila ARVIO y puedo desactivarlo?"],
  ["An ARVIO Cloud account is optional. If you create one, ARVIO stores your email, a password hash, and the profiles and settings you choose to sync. Release builds can also send limited crash diagnostics and a pseudonymous app-open event at most once per day. Screenshots, view hierarchy, network breadcrumbs, and user-interaction breadcrumbs are disabled, and app diagnostic logs are sanitized. Crash transport can still involve an IP address, so reports are not described as anonymous. You can turn diagnostics and usage reporting off in Settings without affecting playback or cloud sync. Read the complete", "Uma conta ARVIO Cloud é opcional. Se criar uma, o ARVIO armazena seu e-mail, hash da senha e os perfis e configurações que você escolher sincronizar. As versões oficiais também podem enviar diagnósticos limitados de falhas e um evento pseudônimo de abertura no máximo uma vez por dia. Capturas de tela, hierarquia visual, rastros de rede e interações ficam desativados, e os logs são sanitizados. O transporte de falhas ainda pode envolver um endereço IP, portanto os relatórios não são descritos como anônimos. Você pode desativar diagnósticos e uso sem afetar reprodução ou nuvem. Leia a", "Una cuenta ARVIO Cloud es opcional. Si creas una, ARVIO almacena tu correo, un hash de contraseña y los perfiles y ajustes que decidas sincronizar. Las versiones oficiales también pueden enviar diagnósticos limitados de fallos y un evento seudónimo de apertura como máximo una vez al día. Las capturas, jerarquía visual, rastros de red e interacciones están desactivados y los registros se depuran. El transporte de fallos aún puede implicar una dirección IP, por lo que los informes no se describen como anónimos. Puedes desactivar diagnósticos y uso sin afectar reproducción ni nube. Lee la"],
  ["Privacy Policy", "Política de Privacidade", "Política de Privacidad"],
  ["or delete cloud data at", "ou exclua os dados da nuvem em", "o elimina los datos de la nube en"],
  ["What is ARVIO Web and what does it cost?", "O que é ARVIO Web e quanto custa?", "¿Qué es ARVIO Web y cuánto cuesta?"],
  ["The ARVIO media-management interface in your browser at", "A interface de gerenciamento de mídia do ARVIO no navegador em", "La interfaz de gestión multimedia de ARVIO en el navegador en"],
  ["on Windows, Mac, iPhone, iPad and more. Profiles, Library and progress sync with the app. Compatible media from your authorized connected services can be played or downloaded when the provider permits it. A one-time 3-day trial starts after you connect ARVIO Cloud. It then costs $2.99/month via", "no Windows, Mac, iPhone, iPad e outros. Perfis, Biblioteca e progresso sincronizam com o app. Conteúdo compatível dos serviços autorizados pode ser reproduzido ou baixado quando o provedor permitir. Um teste único de 3 dias começa após conectar o ARVIO Cloud. Depois custa US$ 2,99/mês pela", "en Windows, Mac, iPhone, iPad y otros. Perfiles, Biblioteca y progreso se sincronizan con la app. El contenido compatible de servicios autorizados puede reproducirse o descargarse cuando el proveedor lo permita. Una prueba única de 3 días empieza tras conectar ARVIO Cloud. Después cuesta 2,99 $/mes mediante la"],
  [". Browsers cannot decode every format, so some authorized files may require the one-tap VLC handoff.", ". Navegadores não decodificam todos os formatos, portanto alguns arquivos autorizados podem exigir envio ao VLC com um toque.", ". Los navegadores no decodifican todos los formatos, por lo que algunos archivos autorizados pueden requerir envío a VLC con un toque."],
  ["Does ARVIO provide media or subscriptions?", "O ARVIO fornece conteúdo ou assinaturas?", "¿ARVIO proporciona contenido o suscripciones?"],
  ["No. ARVIO is software for organizing and using services configured by the user. It does not host, store, sell, bundle or redistribute movies, series, channels, playlists or streams, and it does not include provider accounts. Built-in integrations are limited to metadata, subtitles and account connections. Users must have permission to access every connected service and file.", "Não. O ARVIO é software para organizar e usar serviços configurados pelo usuário. Não hospeda, armazena, vende, inclui nem redistribui filmes, séries, canais, listas ou transmissões, e não inclui contas de provedores. As integrações internas são limitadas a metadados, legendas e conexões de conta. O usuário deve ter permissão para cada serviço e arquivo conectado.", "No. ARVIO es software para organizar y usar servicios configurados por el usuario. No aloja, almacena, vende, incluye ni redistribuye películas, series, canales, listas o emisiones, y no incluye cuentas de proveedores. Las integraciones internas se limitan a metadatos, subtítulos y conexiones de cuenta. El usuario debe tener permiso para cada servicio y archivo conectado."],
  ["Which devices are supported?", "Quais dispositivos são compatíveis?", "¿Qué dispositivos son compatibles?"],
  ["Android TV and Google TV are first‑class — Chromecast with Google TV, NVIDIA Shield, Onn 4K, Walmart Onn, most Sony Bravia and TCL sets. Android phones and tablets get the companion UI. ARVIO Premium extends the experience to browsers on Windows, Mac, iPhone, iPad, Chromebook, Apple TV, webOS and more.", "Android TV e Google TV têm suporte principal — Chromecast com Google TV, NVIDIA Shield, Onn 4K, Walmart Onn e a maioria das TVs Sony Bravia e TCL. Celulares e tablets Android recebem a interface complementar. O ARVIO Premium amplia a experiência para navegadores no Windows, Mac, iPhone, iPad, Chromebook, Apple TV, webOS e outros.", "Android TV y Google TV tienen soporte principal: Chromecast con Google TV, NVIDIA Shield, Onn 4K, Walmart Onn y la mayoría de televisores Sony Bravia y TCL. Los teléfonos y tabletas Android reciben la interfaz complementaria. ARVIO Premium amplía la experiencia a navegadores en Windows, Mac, iPhone, iPad, Chromebook, Apple TV, webOS y otros."],
  ["What about codec support? Dolby? HDR?", "E o suporte a codecs, Dolby e HDR?", "¿Y la compatibilidad con códecs, Dolby y HDR?"],
  ["ExoPlayer (Media3) with the FFmpeg extension covers AV1, HEVC, VP9, H.264, AAC, AC3, EAC3, TrueHD, DTS where the device's audio pipeline allows passthrough. HDR10 and Dolby Vision depend on your hardware. Subtitles: SRT, VTT, ASS/SSA, PGS.", "ExoPlayer (Media3) com a extensão FFmpeg cobre AV1, HEVC, VP9, H.264, AAC, AC3, EAC3, TrueHD e DTS quando o sistema de áudio do dispositivo permite passthrough. HDR10 e Dolby Vision dependem do hardware. Legendas: SRT, VTT, ASS/SSA e PGS.", "ExoPlayer (Media3) con la extensión FFmpeg cubre AV1, HEVC, VP9, H.264, AAC, AC3, EAC3, TrueHD y DTS cuando el sistema de audio permite passthrough. HDR10 y Dolby Vision dependen del hardware. Subtítulos: SRT, VTT, ASS/SSA y PGS."],
  ["Do I need a media server?", "Preciso de um servidor de mídia?", "¿Necesito un servidor multimedia?"],
  ["A home server is optional. ARVIO can organize metadata, tracker lists and other services you configure, but playback always requires an authorized server, provider, file or integration connected by the user. ARVIO Cloud, Trakt and Simkl sync are optional.", "Um servidor doméstico é opcional. O ARVIO pode organizar metadados, listas de rastreadores e outros serviços configurados, mas a reprodução sempre exige servidor, provedor, arquivo ou integração autorizada conectada pelo usuário. ARVIO Cloud, Trakt e Simkl são opcionais.", "Un servidor doméstico es opcional. ARVIO puede organizar metadatos, listas de seguimiento y otros servicios configurados, pero la reproducción siempre requiere un servidor, proveedor, archivo o integración autorizada conectada por el usuario. ARVIO Cloud, Trakt y Simkl son opcionales."],
  ["How do I install the APK directly?", "Como instalo o APK diretamente?", "¿Cómo instalo el APK directamente?"],
  ["Grab the latest APK from", "Baixe o APK mais recente nas", "Descarga el APK más reciente desde las"],
  ["GitHub releases", "versões do GitHub", "versiones de GitHub"],
  [". On Android TV, enable \"Install unknown apps\" for your file manager, then sideload. The Play Store path is recommended for auto‑updates.", ". Na Android TV, ative \"Instalar apps desconhecidos\" para o gerenciador de arquivos e faça o sideload. A Play Store é recomendada para atualizações automáticas.", ". En Android TV, activa \"Instalar aplicaciones desconocidas\" para el gestor de archivos y realiza el sideload. Play Store es la opción recomendada para actualizaciones automáticas."],
  ["Available now", "Disponível agora", "Disponible ahora"],
  ["Install it", "Instale", "Instálalo"],
  ["on your", "na sua", "en tu"],
  ["TV tonight.", "TV hoje.", "TV esta noche."],
  ["Free, open source media-management software. No account required; connect only services and files you are authorized to use.", "Software gratuito e de código aberto para gerenciamento de mídia. Não exige conta; conecte apenas serviços e arquivos autorizados.", "Software gratuito y de código abierto para gestión multimedia. No requiere cuenta; conecta solo servicios y archivos autorizados."],
  ["or", "ou", "o"],
  ["Download the APK", "Baixar o APK", "Descargar el APK"],
  ["Join Discord", "Entrar no Discord", "Unirse a Discord"],
  ["Sideload note · Enable \"Install unknown apps\" on Android TV to use the APK", "Nota de sideload · Ative \"Instalar apps desconhecidos\" na Android TV para usar o APK", "Nota de sideload · Activa \"Instalar aplicaciones desconocidas\" en Android TV para usar el APK"],
  ["Open-source TV,", "TV de código aberto,", "TV de código abierto,"],
  ["made calmer.", "mais tranquila.", "más tranquila."],
  ["Product", "Produto", "Producto"],
  ["Player", "Player", "Reproductor"],
  ["Devices", "Dispositivos", "Dispositivos"],
  ["Screens", "Telas", "Pantallas"],
  ["Media guides", "Guias de mídia", "Guías multimedia"],
  ["Community", "Comunidade", "Comunidad"],
  ["Legal", "Jurídico", "Legal"],
  ["Privacy", "Privacidade", "Privacidad"],
  ["Content policy", "Política de conteúdo", "Política de contenido"],
  ["Third-party", "Terceiros", "Terceros"],
  ["© 2026 ARVIO · Open source", "© 2026 ARVIO · Código aberto", "© 2026 ARVIO · Código abierto"],
  ["Android TV · Google TV · Mobile", "Android TV · Google TV · Celular", "Android TV · Google TV · Móvil"],
];

const attributes = [
  ["Explore ARVIO Premium screenshots and features", "Explore as capturas e os recursos do ARVIO Premium", "Explora las capturas y funciones de ARVIO Premium"],
  ["ARVIO Web home in a desktop browser", "Início do ARVIO Web em um navegador de computador", "Inicio de ARVIO Web en un navegador de escritorio"],
  ["ARVIO Web live TV guide in a desktop browser", "Guia de TV ao vivo do ARVIO Web em um navegador de computador", "Guía de TV en vivo de ARVIO Web en un navegador de escritorio"],
  ["ARVIO Web collections in a desktop browser", "Coleções do ARVIO Web em um navegador de computador", "Colecciones de ARVIO Web en un navegador de escritorio"],
  ["ARVIO Web title details in a desktop browser", "Detalhes de títulos no ARVIO Web em um navegador de computador", "Detalles de títulos de ARVIO Web en un navegador de escritorio"],
  ["ARVIO home", "Início do ARVIO", "Inicio de ARVIO"],
  ["Toggle theme", "Alternar tema", "Cambiar tema"],
  ["Get ARVIO on Google Play", "Baixar ARVIO no Google Play", "Descargar ARVIO en Google Play"],
  ["Get it on Google Play", "Disponível no Google Play", "Disponible en Google Play"],
  ["Supported ARVIO Premium platforms", "Plataformas compatíveis com ARVIO Premium", "Plataformas compatibles con ARVIO Premium"],
  ["Try ARVIO Premium on Windows, Mac, iPhone or iPad", "Testar ARVIO Premium no Windows, Mac, iPhone ou iPad", "Probar ARVIO Premium en Windows, Mac, iPhone o iPad"],
  ["ARVIO Premium browser interface available on Windows, Mac, iPhone and iPad", "Interface ARVIO Premium disponível no Windows, Mac, iPhone e iPad", "Interfaz ARVIO Premium disponible en Windows, Mac, iPhone y iPad"],
  ["ARVIO Home on Android TV", "Tela inicial do ARVIO na Android TV", "Inicio de ARVIO en Android TV"],
  ["ARVIO Live TV", "TV ao vivo do ARVIO", "TV en vivo de ARVIO"],
  ["ARVIO title details", "Detalhes de título no ARVIO", "Detalles de título en ARVIO"],
  ["ARVIO collections", "Coleções do ARVIO", "Colecciones de ARVIO"],
  ["ARVIO 4K HDR player", "Player 4K HDR do ARVIO", "Reproductor 4K HDR de ARVIO"],
];

function replaceTextNodes(html, translations) {
  const protectedBlocks = [];
  html = html.replace(/<(style|script)\b[\s\S]*?<\/\1>/giu, (block) => {
    const marker = `__ARVIO_BLOCK_${protectedBlocks.length}__`;
    protectedBlocks.push(block);
    return marker;
  });

  html = html.replace(/>([^<>]+)</gu, (match, text) => {
    const value = text.replace(/\s+/gu, " ").trim().replaceAll("&amp;", "&");
    const translated = translations.get(value);
    if (!translated) return match;
    const start = text.match(/^\s*/u)?.[0] ?? "";
    const end = text.match(/\s*$/u)?.[0] ?? "";
    return `>${start}${translated}${end}<`;
  });

  return html.replace(/__ARVIO_BLOCK_(\d+)__/gu, (_, index) => protectedBlocks[Number(index)]);
}

function render(localeKey) {
  const locale = locales[localeKey];
  const valueIndex = localeKey === "pt" ? 1 : 2;
  const translations = new Map([...copy, ...newCopy].map((entry) => [entry[0], entry[valueIndex]]));
  const attributeTranslations = new Map([...attributes, ...newCopy].map((entry) => [entry[0], entry[valueIndex]]));
  let html = replaceTextNodes(source, translations);

  html = html.replace('<html lang="en">', `<html lang="${locale.lang}">`);
  html = html.replace(/<title>[^<]+<\/title>/u, `<title>${locale.title}</title>`);
  html = html.replace(/<meta name="description" content="[^"]+">/u, `<meta name="description" content="${locale.description}">`);
  html = html.replace('<link rel="canonical" href="https://arvio.tv/">', `<link rel="canonical" href="${locale.canonical}">`);
  html = html.replace('<meta property="og:site_name" content="ARVIO">', `<meta property="og:site_name" content="ARVIO">\n<meta property="og:locale" content="${locale.locale}">`);
  html = html.replace(/<meta property="og:title" content="[^"]+">/u, `<meta property="og:title" content="${locale.title}">`);
  html = html.replace(/<meta property="og:description" content="[^"]+">/u, `<meta property="og:description" content="${locale.description}">`);
  html = html.replace('<meta property="og:url" content="https://arvio.tv/">', `<meta property="og:url" content="${locale.canonical}">`);
  html = html.replace(/<meta name="twitter:title" content="[^"]+">/u, `<meta name="twitter:title" content="${locale.title}">`);
  html = html.replace(/<meta name="twitter:description" content="[^"]+">/u, `<meta name="twitter:description" content="${locale.description}">`);
  // Metadata translations must survive changes to the English SEO copy.
  html = html.replace(/(<script type="application\/ld\+json">)([\s\S]*?)(<\/script>)/gu, (_, start, json, end) => {
    const data = JSON.parse(json);
    if (data["@type"] === "SoftwareApplication") {
      data.description = locale.description;
      data.inLanguage = locale.lang;
      data.url = locale.canonical;
    }
    return start + JSON.stringify(data).replaceAll("<", "\\u003c") + end;
  });
  html = html.replace('https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png', locale.playBadge);
  html = html.replaceAll('href="/guides/"', `href="${locale.guides}"`);
  const serviceRoutes = {
    "/jellyfin-android-tv/": { pt: "/pt-br/jellyfin-android-tv/", es: "/es/jellyfin-android-tv/" },
    "/plex-emby-jellyfin/": { pt: "/pt-br/plex-emby-jellyfin/", es: "/es/plex-emby-jellyfin/" },
    "/trakt-simkl-sync/": { pt: "/pt-br/sincronizacao-trakt-simkl/", es: "/es/sincronizacion-trakt-simkl/" },
    "/live-tv-epg/": { pt: "/pt-br/tv-ao-vivo-epg/", es: "/es/tv-en-vivo-epg/" }
  };
  for (const [route, localized] of Object.entries(serviceRoutes)) {
    html = html.replaceAll(`href="${route}"`, `href="${localized[localeKey]}"`);
  }
  html = html.replaceAll('src="assets/', 'src="/assets/');
  html = html.replaceAll('url("assets/', 'url("/assets/');
  html = html.replaceAll('&quot;assets/', '&quot;/assets/');

  for (const [english, translated] of attributeTranslations) {
    html = html.replaceAll(`aria-label="${english}"`, `aria-label="${translated}"`);
    html = html.replaceAll(`alt="${english}"`, `alt="${translated}"`);
  }

  html = html.replace(/alt="ARVIO 2\.0 · ([^"]+)"/gu, (_, label) =>
    `alt="ARVIO 2.0 · ${label.split(' · ').map(part => translations.get(part) ?? part).join(' · ')}"`);

  const clientCopy = JSON.stringify(Object.fromEntries(translations)).replaceAll("<", "\\u003c");
  html = html.replace(/(<script type="application\/json" id="home-copy">)[\s\S]*?(<\/script>)/u, (_, start, end) => start + clientCopy + end);
  html = html.replaceAll('href="/premium/"', `href="/premium/?lang=${locale.lang === "es" ? "es-ES" : locale.lang}"`);
  return html.replace(/[ \t]+$/gmu, "");
}

for (const localeKey of Object.keys(locales)) {
  const locale = locales[localeKey];
  const directory = path.join(siteRoot, locale.directory);
  fs.mkdirSync(directory, { recursive: true });
  fs.writeFileSync(path.join(directory, "index.html"), render(localeKey), "utf8");
}

console.log("Generated localized homepages from the production ARVIO homepage layout.");
