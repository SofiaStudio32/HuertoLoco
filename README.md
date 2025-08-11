# Huerto Loco

## Descripción general
"Huerto Loco" es una aplicación Java que sigue la arquitectura Modelo-Vista-Controlador (MVC). La aplicación está diseñada para gestionar e interactuar con datos relacionados con un proyecto de jardinería, proporcionando una interfaz amigable para que los usuarios ingresen y consulten información.

## Estructura del proyecto
El proyecto está organizado en los siguientes directorios y archivos:

```
HuertoLoco
├── src
│   ├── controller
│   │   └── MainController.java
│   ├── model
│   │   └── MainModel.java
│   ├── view
│   │   └── MainView.java
│   └── main
│       └── Main.java
├── .gitignore
├── pom.xml
└── README.md
```

## Instrucciones de configuración
1. **Clona el repositorio**:
   ```
   git clone <url-del-repositorio>
   ```

2. **Navega al directorio del proyecto**:
   ```
   cd HuertoLoco
   ```

3. **Compila el proyecto**:
   Si usas Maven:
   ```
   mvn clean install
   ```
   O si prefieres compilar manualmente:
   ```
   javac -d bin -cp src src/main/Main.java src/model/MainModel.java src/view/MainView.java src/controller/MainController.java
   ```

4. **Ejecuta la aplicación**:
   Con Maven:
   ```
   mvn exec:java -Dexec.mainClass="main.Main"
   ```
   O manualmente:
   ```
   java -cp bin main.Main
   ```

## Características
- Interfaz amigable para gestionar datos de jardinería.
- Arquitectura MVC para una clara separación de responsabilidades.
- Fácil de extender y mantener.

## Contribuciones
¡Las contribuciones son bienvenidas! Por favor, abre un issue o envía un pull request para cualquier mejora o corrección de errores.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.