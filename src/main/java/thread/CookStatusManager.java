package thread;

import model.OrderItem;
import model.Table;

import java.util.List;

public class CookStatusManager implements Runnable {
    private Table table;
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
            OrderItem currentOrder = null;
            synchronized (lock) {
                if (orderQueue.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + "이 주문이 없어 대기중입니다.");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                try {
                    currentOrder = orderQueue.get(0);
                } catch(IndexOutOfBoundsException e) {
                    System.out.println(Thread.currentThread().getName() + "은 손이 남아 쉴수 있습니다");
                    continue;
                }
                if (currentOrder.getCount() <= 0) {
                    orderQueue.remove(0);
                    continue;
                }
                currentOrder.decrementCount();
                if (currentOrder.getCount() == 0) {
                    orderQueue.remove(0);
                }
            }

            if (currentOrder == null) {
                continue;
            }

            String itemName = currentOrder.getName();
            int itemCount = currentOrder.getCount();

            for (int i = 0; i < burners.length; i++) {


                synchronized (lock) {
                    if (!burners[i].equals("_")) {
                        continue;
                    }
                    burners[i] = itemName;
                    if(showCookStatus) {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + ": [" + (i + 1) + "]번 버너에서 " + itemName + " 요리 시작 (" + itemCount + "개 남음)");
                        showBurners();      //버너 상태 출력
                    }
                }

                // 요리 중인 시간을 10초로 가정
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    burners[i] = "_";
                    if (showCookStatus) {
                        System.out.println(
                                "                 "
                                        + Thread.currentThread().getName()
                                        + ": [" + (i + 1) + "]번 버너 사용완료");
                        showBurners();
                    }
                }
                if (itemCount == 0) {
                    System.out.println(itemName + " 요리 완료!");
                }
                break;
            }
            try {
                Thread.sleep(Math.round(1000 * Math.random())); // Random delay between tasks
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

        }
    }


    private void showBurners() {
        String stringToPrint
                = "                         ";
        for (int i = 0; i < burners.length; i++) {
            stringToPrint += (" " + burners[i]);
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
