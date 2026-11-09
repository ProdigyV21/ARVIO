param(
    [Parameter(Mandatory=$true)][string]$RuntimePath,
    [Parameter(Mandatory=$true)][string]$DataPath,
    [Parameter(Mandatory=$true)][string]$AndroidSdk
)
$ErrorActionPreference = 'Stop'
$pin = '25ea36ba05195e6712d96f0290f3ff49b88cf87a'
if (!(Test-Path -LiteralPath $RuntimePath)) {
    & git clone https://github.com/IstarVin/cloudstream-desktop.git $RuntimePath
    if ($LASTEXITCODE -ne 0) { throw 'Runtime checkout failed' }
    & git -C $RuntimePath checkout --detach $pin
    if ($LASTEXITCODE -ne 0) { throw 'Pinned checkout failed' }
}
if ((& git -C $RuntimePath rev-parse HEAD) -ne $pin) { throw 'Unexpected runtime version; use the pinned isolated checkout' }
New-Item -ItemType Directory -Force -Path $DataPath,(Join-Path $DataPath 'config/cloudstream-desktop'),(Join-Path $DataPath 'data') | Out-Null
$tokenFile = Join-Path $DataPath 'worker-token.txt'
if (!(Test-Path -LiteralPath $tokenFile)) {
    [IO.File]::WriteAllText($tokenFile,[Convert]::ToHexString([Security.Cryptography.RandomNumberGenerator]::GetBytes(32)))
}
$plugin = Join-Path $DataPath 'StreamPlay.cs3'
if (!(Test-Path -LiteralPath $plugin)) {
    Invoke-WebRequest 'https://raw.githubusercontent.com/phisher98/cloudstream-extensions-phisher/6f9edcb8164b4a155e2a255b4d4aa9b67126c7ae/StreamPlay.cs3' -OutFile $plugin
}
if ((Get-FileHash -LiteralPath $plugin -Algorithm SHA256).Hash.ToLowerInvariant() -ne 'a63bd63f0203db0c62ed0e85a1d3f039a9e98d7cdc9fa79f233af6cce825f805') { throw 'StreamPlay checksum mismatch' }
# Minimal runtime compatibility patch; never edit ARVIO's Android or browser code.
$contextFile = Join-Path $RuntimePath 'compat/android-shim/src/main/kotlin/android/content/Context.kt'
$context = [IO.File]::ReadAllText($contextFile)
if (!$context.Contains('fun getSystemService(')) {
    if (!$context.Contains('abstract class Context {')) { throw 'Unexpected Context shim' }
    [IO.File]::WriteAllText($contextFile,$context.Replace('abstract class Context {',"abstract class Context {`n    open fun getSystemService(name: String): Any? = null"))
}
$converterFile = Join-Path $RuntimePath 'server/src/main/kotlin/desktop/plugins/Cs3Converter.kt'
$converter = [IO.File]::ReadAllText($converterFile).Replace('const val VERSION = 1','const val VERSION = 2').Replace('.reUseReg(false)','.reUseReg(true)')
[IO.File]::WriteAllText($converterFile,$converter)
$sdk = $AndroidSdk.Replace('\','/')
$config = @"
bind = "127.0.0.1:8951"
plugins.source = "repo"
plugins.repo_urls = ["http://127.0.0.1:8950/plugins.json"]
plugins.disabled = []
plugins.build_on_start = false
mpv.enabled = false
playback.proxy_all = false
ui.show_nsfw = false
android.sdk_path = "$sdk"
chromium.path = "/ARVIO_POC_BROWSER_DISABLED/no-browser"
network.max_requests_per_host = 2
"@
[IO.File]::WriteAllText((Join-Path $DataPath 'config/cloudstream-desktop/config.toml'),$config)
Write-Output 'Pinned runtime and StreamPlay prepared. No browser or server has been started.'
