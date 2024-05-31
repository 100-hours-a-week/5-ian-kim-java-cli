package thread;

import model.OrderItem;

import java.util.List;

public class CookStatusManager implements Runnable {
    private final List<OrderItem> orderQueue;
    private final String[] burners = new String[]{"_", "_", "_", "_"};
    private final Object lock;
    private boolean showCookStatus = false;

    public CookStatusManager(List<OrderItem> orderQueue, Object lock) {
        this.orderQueue = orderQueue;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            OrderItem currentOrder = getNextOrder();
            if (currentOrder == null) {
                continue;
            }

            String itemName = currentOrder.getName();
            int itemCount = currentOrder.getCount();

            int burnerIndex = findEmptyBurner();
            if (burnerIndex == -1) {
                continue;
            }

            startCooking(itemName, itemCount, burnerIndex);

            // 요리 시간은 10초로 가정
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            finishCooking(itemName, itemCount, burnerIndex);
        }
    }

    private OrderItem getNextOrder() {
        synchronized (lock) {
            while (orderQueue.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            OrderItem currentOrder = orderQueue.get(0);
            if (currentOrder.getCount() <= 0) {
                orderQueue.remove(0);
                return null;
            }
            currentOrder.decrementCount();
            if (currentOrder.getCount() == 0) {
                orderQueue.remove(0);
            }
            return currentOrder;
        }
    }

    private int findEmptyBurner() {
        synchronized (lock) {
            for (int i = 0; i < burners.length; i++) {
                if (burners[i].equals("_")) {
                    return i;
                }
            }
            return -1; // 비어있는 버너가 없다면 -1 반환
        }
    }

    private void startCooking(String itemName, int itemCount, int burnerIndex) {
        synchronized (lock) {
            burners[burnerIndex] = itemName;
            if (showCookStatus) {
                System.out.println(Thread.currentThread().getName() + ": [" + (burnerIndex + 1) + "]번 버너에서 " + itemName + " 요리 시작 (" + itemCount + "개 남음)");
                showBurners(); // 버너의 상태를 출력
            }
        }
    }

    private void finishCooking(String itemName, int itemCount, int burnerIndex) {
        synchronized (lock) {
            burners[burnerIndex] = "_";
            if (showCookStatus) {
                System.out.println("                 " + Thread.currentThread().getName() + ": [" + (burnerIndex + 1) + "]번 버너 사용완료");
                showBurners();
            }
            if (itemCount == 0) {
                System.out.println(itemName + " 요리 완료!");
            }
        }
    }

    private void showBurners() {
        StringBuilder stringToPrint = new StringBuilder("                         ");
        for (String burner : burners) {
            stringToPrint.append(" ").append(burner);
        }
        System.out.println(stringToPrint);
    }

    public void showCookStatus() {
        this.showCookStatus = true;
    }

    public void hideCookStatus() {
        this.showCookStatus = false;
    }
}
