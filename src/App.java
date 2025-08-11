public class App {
    public static void main(String[] args) {
        // Initialize the model, view, and controller
        MainModel model = new MainModel();
        MainView view = new MainView();
        MainController controller = new MainController(model, view);
        
        // Start the application
        view.setVisible(true);
    }
}