# Juego en Red JavaFX - Diseño de Interfaces

Este proyecto consiste en el diseño y la implementación de las interfaces gráficas para un juego de mesa multijugador (basado en la mecánica de *Diamant*) utilizando **JavaFX** y **Scene Builder**. El objetivo es crear una experiencia modular que gestione todo el flujo de red, desde la conexión inicial hasta la partida en vivo.

## 🚀 Flujo de la Aplicación

La interfaz se divide en 5 vistas principales, cada una con una responsabilidad específica:

### 1. Pantalla de Inicio (Launcher)
Es el punto de entrada. Permite identificar al jugador antes de interactuar con la red.
- **Componentes:** `TextField` para el Nickname y botones de "Crear Partida", "Buscar Partidas" y "Salir".
- **Layout:** `VBox` centrado.

![pantalla1.png](doc%2Fdesigns%2Fpantalla1.png)

### 2. Creación de Sala (Host Config)
Configuración para el jugador que actuará como anfitrión (Server).
- **Componentes:** Nombre de la sala, selector de número de jugadores (2-8), opción de contraseña y botón de apertura.

![pantalla2.png](doc%2Fdesigns%2Fpantalla2.png)

### 3. Navegador de Salas (Lobby Browser)
Visualización de partidas disponibles en la red local o servidor remoto.
- **Componentes:** `TableView` con detalles de la sala y botones para refrescar o unirse.

![pantalla3.png](doc%2Fdesigns%2Fpantalla3.png)

### 4. Sala de Espera (Pre-Game Lobby)
Punto de encuentro donde los jugadores confirman su estado antes de iniciar.
- **Componentes:** `ListView` de jugadores, indicadores de "Listo" y un sistema de Chat simple (`TextArea` + `TextField`).

![pantalla4.png](doc%2Fdesigns%2Fpantalla4.png)

### 5. Tablero de Juego (Game Board)
La interfaz principal de la partida. En el ejemplo tipo *Diamant*:
- **Zona Central:** `ImageView` para cartas de tesoro/trampa.
- **Panel Lateral:** Tarjetas de estado de los jugadores.
- **Controles:** Botones para "Continuar Explorando" o "Retirarse".

![pantalla5.png](doc%2Fdesigns%2Fpantalla5.png)

## 🛠️ Especificaciones Técnicas

Para el correcto funcionamiento y mantenibilidad del proyecto, se han seguido estas pautas:

* **Modularidad:** Cada vista cuenta con su propio archivo `.fxml` independiente.
* **Identificación:** Todos los componentes interactivos tienen un `fx:id` descriptivo asignado.
* **Diseño Responsivo:** Uso de layouts como `AnchorPane` y `GridPane` para asegurar que la interfaz se adapte a diferentes tamaños de ventana.
* **Tecnologías:** JavaFX, Scene Builder y librerías de iconos (como FontAwesome).

---
*Nota: Este README sirve como guía de implementación y referencia visual para el desarrollo del frontend del juego.*