# Carnage
A stealth-focused Minecraft backdoor plugin for Paper/Spigot 1.21+. Designed for security researchers, developers, and penetration testers who need a private command layer within a live server environment.

**Warning:** This plugin is intentionally malicious in nature. It provides hidden command execution, privilege escalation, and remote control capabilities. Install and use it only on servers you own or have explicit permission to test. The developers are **not responsible** for any misuse, damage, or legal consequences.

## Features
* Hidden chat-based command triggers
* Custom stealth items
* Remote console execution
* Player teleportation utilities
* Full vanish mode (invisible to players & tablist)
* Works on Paper, Spigot, Purpur, and most forks
* Designed for Minecraft **1.21+**

## Commands
All commands are triggered via in-game chat. They are only processed if the player is authorized via the secret password system.

### Core
```
!login <password>   # Authenticate using the secret key
!console <cmd>      # Executes a console command silently
!remote <cmd>       # Executes a remote protected command
```

### Utility
```
!gmc                # Gamemode Creative
!gms                # Gamemode Survival
!gmsp               # Gamemode Spectator
!gma                # Gamemode Adventure
```

### Items
```
!item <MATERIAL> <AMOUNT>
```

Example:
```
!item DIRT 32
```

### Movement

```
!tp <player>        # Teleport to a player
!vanish             # Fully hide yourself
!unvanish           # Reveal yourself
```

## Installation

1. Download the compiled `.jar` file.
2. Place it into the server's `/plugins` folder.
3. Restart the server.

## Compatibility

* **Minecraft:** 1.21+
* **Platforms:** Paper, Spigot, Purpur, any fork implementing modern API

## Disclaimer

This software contains backdoor functionality and is intended only for controlled environments, ethical testing, and research. Unauthorized use on public or private servers without explicit permission is illegal and strictly prohibited.

The developer assumes **no responsibility** for:

* Data loss
* Server compromise
* Damages caused by misuse
* Legal consequences arising from unethical use

Use at your own risk.

## LICENSE
```Copyright (c) 2025 Aarav Banshiwala

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to use
the Software **strictly for educational, research, or testing purposes on
servers you own or have explicit permission to use**.

You **MAY NOT**:
- Deploy this Software on servers without explicit authorization from the server owner.
- Redistribute, sell, or publicly share the Software in any form.
- Use the Software for malicious or unethical purposes.

The Software is provided "as-is", without warranty of any kind. In no event
shall the authors be liable for any claims, damages, or other liability,
whether in an action of contract, tort, or otherwise, arising from, out of,
or in connection with the Software or the use or other dealings in the Software.

By using this Software, you agree to comply with these terms.```
