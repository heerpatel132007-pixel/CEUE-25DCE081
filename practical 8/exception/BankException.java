package exception;

class BankException extends Exception {
    BankException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends BankException {
    long shortfall;

    InsufficientFundsException(String message, long shortfall) {
        super(message);
        this.shortfall = shortfall;
    }
}

class AccountNotFoundException extends BankException {
    AccountNotFoundException(String message) {
        super(message);
    }
}

class InvalidAmountException extends BankException {
    InvalidAmountException(String message) {
        super(message);
    }
}