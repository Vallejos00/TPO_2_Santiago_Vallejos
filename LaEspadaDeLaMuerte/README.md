# La Espada de la Muerte

RPG por turnos en Java (Swing) con arquitectura MVC. Segunda entrega del TP.

## Cómo correrlo en VS Code

1. Instalá la extensión **Extension Pack for Java** (si no la tenés) y tené un JDK 11 o superior
   (para ver tu versión: `java -version` en una terminal).
2. `Archivo > Abrir carpeta...` y elegí **esta carpeta** (`LaEspadaDeLaMuerte`), no la carpeta `src`.
3. Abrí `src/app/Main.java` y tocá **Run** (o `F5`).

Es importante abrir la carpeta del proyecto: el juego busca las imágenes en `src/recursos/sprites/`
y guarda la partida en `partida.sav` relativos a esa carpeta.

## Cómo se juega

- Menú: mouse o flechas ↑ ↓ + Enter.
- Orden de turnos según la **velocidad** de cada personaje/enemigo.
- En tu turno: **Atacar**, **Habilidad** (elegís cuál y, si cura, a quién), **Item** o **Defender**.
- Al ganar una batalla recibís XP (para los personajes vivos) y oro; los personajes suben de nivel.
- Entre batallas podés **guardar la partida**. Desde el menú principal podés **cargarla**.
- 4 batallas: Goblin → Orco → Mago Oscuro → La Muerte (jefe final).

## Estructura (MVC)

| Paquete        | Contenido |
|----------------|-----------|
| `modelo`       | Entidad, Personaje (+ Guerrero, Mago, Arquero, Curador), Enemigo (+ Goblin, Orco, MagoOscuro, Jefe), Partida, Party, Batalla, Accion (+ Atacar, Defender, UsarHabilidad, UsarItem), Habilidad, Item (+ PocionVida, PocionMana, Elixir), Inventario, Recompensa, GestorPartida |
| `controlador`  | ControladorPartida, ControladorBatalla, ConversorDTO |
| `vista`        | VentanaPrincipal, VistaInicio, VistaParty, VistaBatalla, VistaResultados |
| `vista.paneles`| Componentes de Swing reutilizables (cuadros, menú, barras, tarjetas, escena) |
| `dto`          | Objetos que viajan del controlador a la vista (la vista nunca toca el modelo) |
| `util`         | Estilo (colores/fuentes) y CargadorImagenes |
| `app`          | Main (arma todo y conecta vistas con controladores) |

## Imágenes

Las imágenes están en `src/recursos/sprites/`. Para cambiarlas por otras, reemplazá el archivo
**manteniendo el nombre** (PNG con fondo transparente, se escalan solas y sin suavizado):

`guerrero.png`, `mago.png`, `arquero.png`, `curador.png`, `goblin.png`, `orco.png`,
`magooscuro.png`, `jefe.png` (La Muerte)

Fondos: `fondo_inicio.png`, `fondo_batalla.png`, `fondo_party.png`.
Si falta algún sprite el juego sigue funcionando y muestra "SIN SPRITE".
