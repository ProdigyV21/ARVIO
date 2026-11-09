param(
    [Parameter(Mandatory=$true)][string]$RuntimePath,
    [Parameter(Mandatory=$true)][string]$DataPath,
    [string]$Heap = '768m'
)
$ErrorActionPreference = 'Stop'
$env:XDG_CONFIG_HOME = Join-Path $DataPath 'config'
$env:XDG_DATA_HOME = Join-Path $DataPath 'data'
$env:ARVIO_CS_WORKER_TOKEN = [IO.File]::ReadAllText((Join-Path $DataPath 'worker-token.txt'))
$env:ARVIO_CS_POC_SOURCE = Join-Path $PSScriptRoot 'worker'
$env:ARVIO_CS_HEAP = $Heap
$env:ARVIO_CS_PINNED_CS3 = Join-Path $DataPath 'StreamPlay.cs3'
Push-Location $RuntimePath
try {
    & java -classpath gradle/wrapper/gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain -I (Join-Path $PSScriptRoot 'worker.init.gradle') :server:runArvioPoc -x :server:webBuild -x :server:webInstall --console=plain
    exit $LASTEXITCODE
} finally { Pop-Location }
