
import controller.MenuController;
import controller.TableController;
import service.MenuService;
import service.TableService;
import view.MainView;
import view.MenuView;
import view.OrderView;
import view.TableView;

public class Main {
    public static void main(String[] args) {
        TableService tableService = new TableService();
        TableController tableController = new TableController(tableService);
        TableView tableView = new TableView(tableController);

        MenuService menuService = new MenuService();
        MenuController menuController = new MenuController(menuService);
        MenuView menuView = new MenuView(menuController);

        OrderView orderView = new OrderView();

        MainView mainView = new MainView(tableView,orderView,menuView);
        mainView.run();
//        PayController payController = new PayController(tableController);
//        EmployeeController employeeController = new EmployeeController();
//        MenuController menuController = new MenuController();
//        OrderItemController orderItemController = new OrderItemController(menuController);
//        OrderController orderController = new OrderController(tableController, orderItemController);
//
////        menuController.defaultMenu();

    }
}
