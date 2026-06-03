# Build Instructions

## Prerequisites

- **Java 21 JDK** (not JRE)
- **Maven 3.8.0+**

## Building the Plugin

### Quick Build
```bash
mvn clean package
```

Output: `target/EternalRelics-1.0.0.jar`

### Build with Tests
```bash
mvn clean verify
```

### Build with Logging Output
```bash
mvn clean package -X
```

### Skip Tests
```bash
mvn clean package -DskipTests
```

## Installation

1. **Build the plugin:**
   ```bash
   mvn clean package
   ```

2. **Copy JAR to server:**
   ```bash
   cp target/EternalRelics-1.0.0.jar /path/to/server/plugins/
   ```

3. **Restart server:**
   ```bash
   ./start.sh
   ```

4. **Configure plugin:**
   - Edit `plugins/EternalRelics/config.yml`
   - Adjust crafting times, cooldowns, etc.
   - Run `/reload confirm`

## Maven Goals

- `mvn clean` - Clean build directory
- `mvn compile` - Compile source code
- `mvn test` - Run unit tests
- `mvn package` - Create JAR file
- `mvn deploy` - Deploy to repository (if configured)
- `mvn install` - Install to local Maven repository

## Troubleshooting

### Maven not found
```bash
# Install Maven (Ubuntu/Debian)
sudo apt-get install maven

# Install Maven (macOS)
brew install maven

# Verify installation
mvn --version
```

### Java version mismatch
```bash
# Check Java version
java --version

# Install Java 21 (Ubuntu/Debian)
sudo apt-get install openjdk-21-jdk

# Set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
```

### Compilation errors
```bash
# Clean and rebuild
mvn clean compile

# Check for missing dependencies
mvn dependency:tree
```

### JAR not created
```bash
# Verbose output
mvn clean package -X

# Check target directory
ls -la target/
```

## IDE Setup

### IntelliJ IDEA
1. Open project
2. Maven should auto-detect pom.xml
3. Right-click pom.xml → "Add as Maven Project"
4. Build → Build Project

### Eclipse
1. File → Import → Existing Maven Projects
2. Select project root
3. Eclipse auto-configures
4. Project → Build Project

### VS Code
1. Install "Extension Pack for Java"
2. Install "Maven for Java"
3. Open terminal: `Ctrl+` ` `
4. Run: `mvn clean package`

## Continuous Integration

### GitHub Actions Example
```yaml
name: Build
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - run: mvn clean package
      - uses: actions/upload-artifact@v3
        with:
          name: EternalRelics
          path: target/EternalRelics-*.jar
```

## Production Deployment

```bash
#!/bin/bash
# Build
mvn clean package -DskipTests

# Backup old version
cp /opt/server/plugins/EternalRelics-*.jar /opt/backups/

# Deploy new version
cp target/EternalRelics-1.0.0.jar /opt/server/plugins/

# Restart server
sudo systemctl restart minecraft-server

# Check logs
tail -f /opt/server/logs/latest.log | grep EternalRelics
```
