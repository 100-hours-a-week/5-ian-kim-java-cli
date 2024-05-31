
import controller.*;
import controller.request.MenuNumberWithStockRequest;
import model.OrderItem;
import service.*;
import thread.CookStatusManager;
import view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int NUM_CHEFS = 4;
    private static final List<OrderItem> orderQueue = new ArrayList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {



        TableService tableService = new TableService();
        TableController tableController = new TableController(tableService);
        TableView tableView = new TableView(tableController);

        MenuService menuService = new MenuService();
        MenuController menuController = new MenuController(menuService);
        MenuView menuView = new MenuView(menuController);

        OrderItemService orderItemService = new OrderItemService(menuService);

        OrderService orderService = new OrderService(orderItemService, tableService, orderQueue, lock);
        OrderController orderController = new OrderController(orderService);
        OrderView orderView = new OrderView(orderController);


        PayService payService = new PayService(tableService);
        PayController payController = new PayController(payService, tableService);
        PayView payView = new PayView(payController);


        EmployeeService employeeService = new EmployeeService();
        EmployeeController employeeController = new EmployeeController(employeeService);
        EmployeeView employeeView = new EmployeeView(employeeController);


        synchronized (lock) {
            orderQueue.addAll(orderItemService.getOrderItems());
        }


        CookStatusManager cookStatusManager = new CookStatusManager(orderQueue, lock);

        MainView mainView = new MainView(tableView, orderView, menuView, payView, employeeView, cookStatusManager);



        List<Thread> chefs = new ArrayList<>();
        for (int i = 0; i < NUM_CHEFS; i++) {


            Thread chefThread = new Thread(cookStatusManager, "요리사 " + (i + 1));
            chefs.add(chefThread);
            chefThread.start();
        }



        mainView.mainStart();

    }
}
