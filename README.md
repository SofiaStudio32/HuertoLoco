# Huerto Loco

## Overview
"Huerto Loco" is a Java application that follows the Model-View-Controller (MVC) architecture. The application is designed to manage and interact with data related to a gardening project, providing a user-friendly interface for users to input and retrieve information.

## Project Structure
The project is organized into the following directories and files:

```
HuertoLoco
├── src
│   ├── controller
│   │   └── MainController.java
│   ├── model
│   │   └── MainModel.java
│   ├── view
│   │   └── MainView.java
│   └── App.java
├── .gitignore
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the repository**:
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory**:
   ```
   cd HuertoLoco
   ```

3. **Build the project**:
   Use Maven to build the project:
   ```
   mvn clean install
   ```

4. **Run the application**:
   Execute the main application:
   ```
   mvn exec:java -Dexec.mainClass="App"
   ```

## Features
- User-friendly interface for managing gardening data.
- MVC architecture for clear separation of concerns.
- Easy to extend and maintain.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.