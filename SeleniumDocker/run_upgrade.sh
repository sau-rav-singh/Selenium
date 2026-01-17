#!/bin/bash

# 1. Get the directory where THIS script is located
PARENT_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P)

# 2. Change the current working directory to that path
cd "$PARENT_PATH"

# 3. Now these relative calls will always find the files
echo "Starting upgrades in $PARENT_PATH..."

# Run upgrade.sh for Chrome
./upgrade.sh chrome

# Run upgrade.sh for ChromeDriver
./upgrade.sh chromedriver

# Push changes
git push origin main
