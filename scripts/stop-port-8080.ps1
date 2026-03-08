param(
    [int]$Port = 8080,
    [switch]$Force
)

$connections = Get-NetTCPConnection -LocalPort $Port -State Listen -ErrorAction SilentlyContinue

if (-not $connections) {
    Write-Host "No process is listening on port $Port."
    exit 0
}

$pids = $connections | Select-Object -ExpandProperty OwningProcess -Unique
$failed = $false

foreach ($pid in $pids) {
    $proc = Get-Process -Id $pid -ErrorAction SilentlyContinue

    if (-not $proc) {
        Write-Host "PID $pid is no longer running."
        continue
    }

    Write-Host "Stopping PID $pid ($($proc.ProcessName)) on port $Port..."

    try {
        if ($Force) {
            Stop-Process -Id $pid -Force -ErrorAction Stop
        } else {
            Stop-Process -Id $pid -ErrorAction Stop
        }

        Write-Host "PID $pid stopped successfully."
    }
    catch {
        Write-Host "Failed to stop PID $pid. Run PowerShell as Administrator if needed."
        Write-Host $_.Exception.Message
        $failed = $true
    }
}

if ($failed) {
    exit 1
}

Write-Host "Port $Port is now free (or no listener remains)."
exit 0

