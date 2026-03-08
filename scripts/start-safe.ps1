param(
    [int]$Port = 8080,
    [switch]$SkipKill
)

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$repoRoot = Resolve-Path (Join-Path $scriptDir "..")

if (-not $SkipKill) {
    & (Join-Path $scriptDir "stop-port-8080.ps1") -Port $Port -Force

    if ($LASTEXITCODE -ne 0) {
        Write-Host "Could not free port $Port. Aborting startup."
        exit $LASTEXITCODE
    }
}

Push-Location $repoRoot
try {
    & ".\mvnw.cmd" spring-boot:run
    exit $LASTEXITCODE
}
finally {
    Pop-Location
}

