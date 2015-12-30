{ time -p for i in {1..10}; do ng Zon >/dev/null; done; } 2>&1 | grep real | awk '{print $2/10}'
