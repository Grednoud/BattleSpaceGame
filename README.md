# BattleSpace

**[English](#english) | [Русский](#русский)**

---

## English

### Description

BattleSpace is an educational 2D space shooter game built with [LibGDX](https://libgdx.com/). 
The player controls a spaceship, dodging enemy fire and destroying waves of enemy ships.

This project demonstrates core LibGDX concepts:
- Game loop and screen management
- Sprite rendering and animation
- Input handling (keyboard and touch)
- Audio playback (music and sound effects)
- Object pooling for performance
- Collision detection

### Requirements

- **JDK 21** or newer
- **Gradle 8.10+** (included via wrapper)

### Project Structure

```
BattleSpace/
├── core/           # Platform-independent game logic
│   ├── src/        # Main source code
│   └── test/       # Unit tests
├── desktop/        # Desktop launcher (LWJGL3)
├── android/        # Android launcher (requires Android SDK)
│   └── assets/     # Game assets (textures, sounds, fonts)
├── build.gradle.kts
└── settings.gradle.kts
```

### How to Run

#### Desktop (Recommended)

```bash
# Run the game
./gradlew desktop:run

# Build a distributable JAR
./gradlew desktop:dist
```

> **Note:** On a headless server (no display), the game window cannot open.
> Build verification: `./gradlew desktop:classes` confirms the code compiles.

#### Android

Building for Android requires the Android SDK:

1. Install [Android Studio](https://developer.android.com/studio) or standalone SDK
2. Set `ANDROID_HOME` environment variable, or create `local.properties`:
   ```properties
   sdk.dir=/path/to/your/android/sdk
   ```
3. Build:
   ```bash
   ./gradlew android:assembleDebug
   ```

### Running Tests

```bash
./gradlew test
```

### Controls

| Action       | Keyboard | Touch       |
|--------------|----------|-------------|
| Move Left    | ←        | Drag left   |
| Move Right   | →        | Drag right  |
| Shoot        | ↑        | Auto-fire   |

### Architecture Overview

```
ru.codesteps
├── BattleSpaceGame.java    # Main game class, screen management
├── base/                   # Core abstractions
│   ├── BaseScreen.java     # Screen with coordinate transformation
│   ├── BaseSprite.java     # Animated sprite base class
│   ├── BaseRectangle.java  # Rectangle with position utilities
│   ├── SpritesPool.java    # Object pool for sprites
│   └── Ship.java           # Base ship class (player/enemy)
├── screens/
│   ├── MenuScreen.java     # Main menu
│   └── GameScreen.java     # Gameplay screen
├── sprites/                # Game objects
│   ├── MainShip.java       # Player ship
│   ├── Enemy.java          # Enemy ships
│   ├── Bullet.java         # Projectiles
│   └── ...
├── pools/                  # Object pools
├── utils/                  # Utilities
└── math/                   # Math helpers
```

### Technology Stack

- **LibGDX 1.12.1** — cross-platform game framework
- **LWJGL3** — desktop OpenGL backend
- **Gradle 8.10** — build system with Kotlin DSL
- **JUnit 5** — unit testing

---

## Русский

### Описание

BattleSpace — учебная 2D-игра в жанре космического шутера на [LibGDX](https://libgdx.com/).
Игрок управляет космическим кораблём, уворачивается от вражеского огня и уничтожает волны противников.

Проект демонстрирует ключевые концепции LibGDX:
- Игровой цикл и управление экранами
- Рендеринг спрайтов и анимация
- Обработка ввода (клавиатура и касание)
- Воспроизведение аудио (музыка и звуковые эффекты)
- Пулинг объектов для производительности
- Обнаружение столкновений

### Требования

- **JDK 21** или новее
- **Gradle 8.10+** (включён через wrapper)

### Структура проекта

```
BattleSpace/
├── core/           # Платформо-независимая логика игры
│   ├── src/        # Исходный код
│   └── test/       # Модульные тесты
├── desktop/        # Лаунчер для ПК (LWJGL3)
├── android/        # Лаунчер для Android (требуется Android SDK)
│   └── assets/     # Ресурсы игры (текстуры, звуки, шрифты)
├── build.gradle.kts
└── settings.gradle.kts
```

### Как запустить

#### Desktop (Рекомендуется)

```bash
# Запуск игры
./gradlew desktop:run

# Сборка распространяемого JAR
./gradlew desktop:dist
```

> **Примечание:** На сервере без дисплея (headless) окно игры не откроется.
> Проверка сборки: `./gradlew desktop:classes` подтверждает компиляцию кода.

#### Android

Сборка для Android требует Android SDK:

1. Установите [Android Studio](https://developer.android.com/studio) или отдельный SDK
2. Установите переменную окружения `ANDROID_HOME` или создайте `local.properties`:
   ```properties
   sdk.dir=/путь/к/вашему/android/sdk
   ```
3. Соберите:
   ```bash
   ./gradlew android:assembleDebug
   ```

### Запуск тестов

```bash
./gradlew test
```

### Управление

| Действие     | Клавиатура | Касание         |
|--------------|------------|-----------------|
| Влево        | ←          | Провести влево  |
| Вправо       | →          | Провести вправо |
| Стрельба     | ↑          | Автоматически   |

### Обзор архитектуры

```
ru.codesteps
├── BattleSpaceGame.java    # Главный класс игры, управление экранами
├── base/                   # Базовые абстракции
│   ├── BaseScreen.java     # Экран с преобразованием координат
│   ├── BaseSprite.java     # Базовый класс анимированного спрайта
│   ├── BaseRectangle.java  # Прямоугольник с утилитами позиционирования
│   ├── SpritesPool.java    # Пул объектов для спрайтов
│   └── Ship.java           # Базовый класс корабля (игрок/враг)
├── screens/
│   ├── MenuScreen.java     # Главное меню
│   └── GameScreen.java     # Экран игрового процесса
├── sprites/                # Игровые объекты
│   ├── MainShip.java       # Корабль игрока
│   ├── Enemy.java          # Вражеские корабли
│   ├── Bullet.java         # Снаряды
│   └── ...
├── pools/                  # Пулы объектов
├── utils/                  # Утилиты
└── math/                   # Математические помощники
```

### Стек технологий

- **LibGDX 1.12.1** — кроссплатформенный игровой фреймворк
- **LWJGL3** — OpenGL бэкенд для десктопа
- **Gradle 8.10** — система сборки с Kotlin DSL
- **JUnit 5** — модульное тестирование

---

## License

This is an educational project. Original repository by [Grednoud](https://github.com/Grednoud).
