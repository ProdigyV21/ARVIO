param(
    [Parameter(Mandatory = $true)][string]$OutputDirectory,
    [string]$RepositoryRoot = (Split-Path -Parent $PSScriptRoot)
)
$ErrorActionPreference = 'Stop'

# A mechanical export, not an image publication or Community Apps submission.
# Never replace an existing feed or source checkout: use a new empty directory
# and review the resulting Git diff before synchronizing the public feed.
$sourceRoot = (Resolve-Path -LiteralPath $RepositoryRoot).Path
$outputRoot = [System.IO.Path]::GetFullPath($OutputDirectory)
if ($outputRoot.TrimEnd([System.IO.Path]::DirectorySeparatorChar) -eq $sourceRoot.TrimEnd([System.IO.Path]::DirectorySeparatorChar)) {
    throw 'The template feed output must not be the application source repository.'
}
if (Test-Path -LiteralPath $outputRoot) {
    if (-not (Test-Path -LiteralPath $outputRoot -PathType Container) -or
        @(Get-ChildItem -LiteralPath $outputRoot -Force).Count -ne 0) {
        throw 'Export requires a new or empty output directory; existing files are preserved.'
    }
} else {
    $null = New-Item -ItemType Directory -Path $outputRoot
}
$null = & (Join-Path $sourceRoot 'scripts/check-unraid.ps1') -RepositoryRoot $sourceRoot
$null = New-Item -ItemType Directory -Path (Join-Path $outputRoot 'templates')
Copy-Item -LiteralPath (Join-Path $sourceRoot 'LICENSE') -Destination (Join-Path $outputRoot 'LICENSE')
Copy-Item -LiteralPath (Join-Path $sourceRoot 'ca_profile.xml') -Destination (Join-Path $outputRoot 'ca_profile.xml')
Copy-Item -LiteralPath (Join-Path $sourceRoot 'templates/arvio-web.xml') -Destination (Join-Path $outputRoot 'templates/arvio-web.xml')
Copy-Item -LiteralPath (Join-Path $sourceRoot 'unraid/feed-README.md') -Destination (Join-Path $outputRoot 'README.md')
& (Join-Path $sourceRoot 'scripts/check-unraid.ps1') -RepositoryRoot $outputRoot -RequireTemplateFeed
Write-Output "Prepared template-only feed at $outputRoot. No Git repository, image or external submission was created."
