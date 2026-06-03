# EternalRelics - Complete Installation Guide

## 📥 How to Download Files

### Option 1: Download from GitHub (Recommended)

#### Clone the entire repository:
```bash
git clone https://github.com/ItzEnea/EternalRelics.git
cd EternalRelics
```

#### Or download as ZIP:
1. Go to https://github.com/ItzEnea/EternalRelics
2. Click **Code** (green button)
3. Click **Download ZIP**
4. Extract the ZIP file

### Option 2: Download Individual Files

You can download specific files directly from GitHub:

1. Navigate to the file
2. Click the **Raw** button
3. Right-click → **Save as...**

---

## 🔨 Building the Plugin

### Prerequisites
- **Java 21 JDK** installed
- **Maven 3.8.0+** installed

### Quick Build

```bash
# Navigate to project directory
cd EternalRelics

# Build the plugin
mvn clean package
```

**Output:** `target/EternalRelics-1.0.0.jar`

### Verify Maven is Installed

```bash
mvn --version
```

If not installed:

**Ubuntu/Debian:**
```bash
sudo apt-get install maven
```

**macOS:**
```bash
brew install maven
```

**Windows:**
Download from https://maven.apache.org/download.cgi

---

## 📦 Installing the Plugin

### Step 1: Build the JAR
```bash
mvn clean package -DskipTests
```

### Step 2: Copy to Server
```bash
cp target/EternalRelics-1.0.0.jar /path/to/server/plugins/
```

### Step 3: Start Server
```bash
cd /path/to/server
./start.sh
```

### Step 4: Configure
Edit: `plugins/EternalRelics/config.yml`

Restart server or run: `/reload confirm`

---

## 🎨 Installing Resource Pack

### Download Resource Pack

1. Go to: https://github.com/ItzEnea/EternalRelics/tree/main/resourcepack
2. Download all files from the resourcepack folder
3. Create structure:
   ```
   EternalRelics-ResourcePack/
   ├── assets/minecraft/
   ├── pack.mcmeta
   ├── pack.png
   └── README.md
   ```

### Create ZIP File

**Windows (Command Prompt):**
```cmd
cd EternalRelics-ResourcePack
tar.exe -a -c -f EternalRelics-ResourcePack.zip *
```

**macOS/Linux:**
```bash
cd EternalRelics-ResourcePack
zip -r EternalRelics-ResourcePack.zip *
```

### Install in Minecraft

#### Windows
1. Press `Win + R`
2. Type: `%appdata%\.minecraft\resourcepacks`
3. Paste `EternalRelics-ResourcePack.zip`
4. Open Minecraft
5. Options → Resource Packs
6. Move EternalRelics to "Selected"

#### macOS
1. Open Finder
2. Go to: `~/Library/Application Support/minecraft/resourcepacks/`
3. Paste `EternalRelics-ResourcePack.zip`
4. Open Minecraft
5. Options → Resource Packs
6. Move EternalRelics to "Selected"

#### Linux
```bash
cp EternalRelics-ResourcePack.zip ~/.minecraft/resourcepacks/
```
Then in Minecraft: Options → Resource Packs → Select

---

## 📁 File Structure After Download

```
EternalRelics/
├── src/
│   ├── main/
│   │   ├── java/dev/eternalrelics/
│   │   │   ├── EternalRelicsPlugin.java
│   │   │   ├── core/
│   │   │   │   ├── manager/
│   │   │   │   ├── item/
│   │   │   │   ├── config/
│   │   │   │   └── persistence/
│   │   │   ├── commands/
│   │   │   └── listeners/
│   │   └── resources/
│   │       ├── plugin.yml
│   │       └── config.yml
│   └── test/
├── resourcepack/
│   ├── assets/minecraft/
│   ├── models/
│   ├── pack.mcmeta
│   ├── pack.png
│   └── README.md
├── pom.xml
├── BUILD.md
├── TECHNICAL.md
├── RESOURCEPACK.md
└── README.md
```

---

## ✅ Verification

### Check Plugin is Working

1. Start server
2. Check console for:
   ```
   [EternalRelics] EternalRelics enabled successfully!
   ```

3. In-game test:
   ```
   /altar status
   /recipes
   /relic place
   ```

### Check Resource Pack

1. Enable in options
2. Check if items have custom textures
3. Hold a golden helmet to see Sun Crown texture

---

## 🚀 Quick Start Commands

```bash
# Clone and build
git clone https://github.com/ItzEnea/EternalRelics.git
cd EternalRelics
mvn clean package

# Copy to server
cp target/EternalRelics-1.0.0.jar ~/minecraft_server/plugins/

# Run server
cd ~/minecraft_server
./start.sh
```

---

## 📝 Configuration

After first run, edit: `plugins/EternalRelics/config.yml`

**Key settings:**
```yaml
crafting:
  start-hour: 16      # 4 PM CET
  end-hour: 19        # 7 PM CET

particles:
  enabled: true
  reduction-factor: 1.0  # 1.0 = normal, 0.5 = half

database:
  type: "json"  # or "sqlite"
```

---

## 🆘 Troubleshooting

### Maven build fails
```bash
# Clean cache
mvn clean install -DskipTests

# Check Java version
java --version  # Should be 21+
```

### Plugin won't start
1. Check console for errors
2. Verify Paper 1.21.1+
3. Check permissions on `/plugins` folder
4. Run: `tail -f logs/latest.log`

### Resource pack not loading
1. Verify file structure (case-sensitive!)
2. Check pack.mcmeta format
3. Confirm ZIP file isn't corrupted
4. Restart Minecraft launcher

### Items don't have textures
1. Verify resource pack is enabled
2. Check model data numbers match config
3. Confirm texture files exist
4. Check item lore shows "Legendary"

---

## 📞 Support

- **GitHub Issues:** https://github.com/ItzEnea/EternalRelics/issues
- **Documentation:** See TECHNICAL.md and RESOURCEPACK.md
- **Build Help:** See BUILD.md

---

## 📋 Checklist

- [ ] Java 21 JDK installed
- [ ] Maven installed and working
- [ ] Repository cloned/downloaded
- [ ] Plugin built successfully (`mvn clean package`)
- [ ] JAR copied to plugins folder
- [ ] Server started and plugin loaded
- [ ] Config.yml customized
- [ ] Resource pack downloaded
- [ ] Resource pack installed in Minecraft
- [ ] Resource pack enabled in options
- [ ] Test command works: `/altar status`

---

## 🎯 Next Steps

1. **Customize config.yml** - Set crafting times, cooldowns, etc.
2. **Create resource pack textures** - Design your legendary items
3. **Test abilities** - Use commands to spawn items and test
4. **Set up factions** - Use `/king`, `/void awaken` to set leaders
5. **Deploy to live server** - Follow production deployment guide

---

## 📚 Additional Resources

- **README.md** - Project overview
- **TECHNICAL.md** - Architecture and implementation details
- **RESOURCEPACK.md** - Texture pack specifications
- **BUILD.md** - Detailed build instructions
