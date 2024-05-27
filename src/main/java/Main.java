
import controller.*;
import service.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        TableService tableService = new TableService();
        TableController tableController = new TableController(tableService);
        TableView tableView = new TableView(tableController);

        MenuService menuService = new MenuService();
        MenuController menuController = new MenuController(menuService);
        MenuView menuView = new MenuView(menuController);

        OrderItemService orderItemService = new OrderItemService(menuService);

        OrderService orderService = new OrderService(orderItemService,tableService);
        OrderController orderController = new OrderController(orderService);
        OrderView orderView = new OrderView(orderController);


        PayService payService = new PayService(tableService);
        PayController payController = new PayController(payService, tableService);
        PayView payView = new PayView(payController);


        EmployeeService employeeService = new EmployeeService();
        EmployeeController employeeController = new EmployeeController(employeeService);
        EmployeeView employeeView = new EmployeeView(employeeController);

        MainView mainView = new MainView(tableView,orderView,menuView,payView,employeeView);
        mainView.run();

    }
}
