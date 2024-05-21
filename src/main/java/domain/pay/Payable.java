package domain.pay;

import exception.PayException;

public interface Payable {
    public void pay() throws PayException;
}
