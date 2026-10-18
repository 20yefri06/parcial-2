# Examen 2 - Técnicas de Programación (Java Swing)

Proyecto Java para VS Code con:
- POO y polimorfismo
- MVC + Service
- Factory Method
- Interfaz gráfica Swing
- Agregar, retirar y listar envíos

## Requisitos
- JDK 17 o superior (JDK 21 recomendado)
- Visual Studio Code
- Extensión **Extension Pack for Java** de Microsoft

## Ejecutar en VS Code
1. Instala un JDK (si no tienes uno).
2. Instala VS Code.
3. En VS Code, instala **Extension Pack for Java**.
4. Abre la carpeta `ExamenEnviosVSCode` (Archivo > Abrir carpeta).
5. Abre `src/Main.java`.
6. Pulsa **Run** sobre el método `main`, o abre una terminal en la carpeta y ejecuta:

   ```bash
   javac -d out src/Main.java src/modelo/*.java src/factory/*.java src/service/*.java src/controlador/*.java src/vista/*.java
   java -cp out Main
   ```

   En Windows PowerShell, si el comodín de rutas te da problemas, compila desde VS Code con el botón Run o usa:
   ```powershell
   javac -d out src/Main.java src/modelo/Envio.java src/modelo/Terrestre.java src/modelo/Aereo.java src/modelo/Maritimo.java src/factory/FabricaEnvio.java src/factory/FabricaTerrestre.java src/factory/FabricaAereo.java src/factory/FabricaMaritimo.java src/service/EnvioService.java src/controlador/EnvioController.java src/vista/VentanaEnvios.java
   java -cp out Main
   ```

## Tarifas implementadas
La tarifa se calcula como:

`distanciaKm * tarifaBasePorKm + pesoKg * recargoPorKg`

- Terrestre: $1.500 por km + $2.000 por kg
- Aéreo: $5.000 por km + $4.000 por kg
- Marítimo: $800 por km + $1.000 por kg

Se usa `BigDecimal` para validar y calcular los importes con precisión decimal.

## Nota sobre el enunciado
El documento menciona “Fluvial” en el primer párrafo, pero la tabla de tarifas y la sección Factory Method nombran “Marítimo/Maritimo”. Este proyecto sigue la tabla y la sección de fábrica: **Marítimo**. Si el profesor confirma que debe ser Fluvial, se puede renombrar la clase y la opción de la interfaz.

## Estructura
- `modelo`: abstracción `Envio` y tipos concretos.
- `factory`: creador abstracto y fábricas concretas.
- `service`: almacenamiento y operaciones de negocio.
- `controlador`: valida entradas y coordina vista/servicio.
- `vista`: solo componentes y presentación Swing.
- `Main.java`: punto de entrada e inyección de dependencias.
