# Commander
Simple Java Command Design Pattern

# Usage
```java

private static Command increment = new Command() {

    public void restore() {
        counter--;
    }

    public void run() {
        counter++;
    }

};

public static void main(String[] args) {

    HistoryHandler handler = new HistoryHandler();

    handler.handle(increment);
    handler.handle(increment);
    handler.handle(increment);

    // counter = 3

    handler.undo();
    handler.undo();

    // counter = 1

    handler.redo();

	// counter = 2

}
```
or create your custom command / handler implementing **Command** / **CommandHandler** interfaces.

# License
MIT License
