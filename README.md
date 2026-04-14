# Domingo Calculator

A Java Swing-based GUI calculator that demonstrates core **Object-Oriented Programming (OOP)** principles through a clean four-class design.

---

## Project Structure

```
CalculatorOperations.java  – interface (contract)
CalculationBase.java       – abstract base class (shared utility)
CalculationEngine.java     – concrete implementation (arithmetic logic)
GUICalculator.java         – graphical front-end (user interface)
```

---

## How to Run

```bash
javac *.java
java GUICalculator
```

---

## Class-by-Class Explanation

### `CalculatorOperations` (Interface)
Declares the four arithmetic operations (`add`, `subtract`, `multiply`, `divide`) without providing any implementation. Any class that `implements` this interface is guaranteed to support all four operations.

### `CalculationBase` (Base Class)
A concrete base class that provides a single protected utility method, `normalizeZero`, which converts `-0.0` to `0.0`. Placing shared helper logic here avoids repeating it in every subclass.

### `CalculationEngine` (Concrete Implementation)
Extends `CalculationBase` (to reuse `normalizeZero`) and implements `CalculatorOperations` (to fulfil the arithmetic contract). Each arithmetic method delegates to Java's built-in operators, wraps the result with `normalizeZero`, and throws an `ArithmeticException` when a division-by-zero is attempted.

### `GUICalculator` (Graphical Front-End)
Extends `javax.swing.JFrame` to become a window. It owns a `CalculationEngine` instance and translates button clicks into calls on that engine. The inner class `GradientPanel` extends `JPanel` and overrides `paintComponent` to draw a custom gradient background.

---

## OOP Principles Demonstrated

### 1 – Abstraction
`CalculatorOperations` is a Java **interface**: it exposes *what* the calculator can do (add, subtract, multiply, divide) while hiding *how* it is done. Client code that holds a `CalculatorOperations` reference does not need to know anything about the underlying implementation.

```java
// Contract – no implementation details
public interface CalculatorOperations {
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);
}
```

### 2 – Encapsulation
Each class bundles related **data** and **behaviour** together and hides internal details:

* `CalculationBase` keeps `normalizeZero` `protected` – subclasses can use it, but outside callers cannot.
* `GUICalculator` keeps display state (`firstNumber`, `currentOperator`, `startNewInput`) as `private` fields and exposes only the necessary public interface.

```java
// Private state – only accessible through the class's own methods
private double firstNumber = 0;
private String currentOperator = "";
private boolean startNewInput = true;
```

### 3 – Inheritance
`CalculationEngine` extends `CalculationBase`, inheriting the `normalizeZero` helper without rewriting it. `GUICalculator` extends `JFrame`, inheriting a fully functional window. `GradientPanel` extends `JPanel`, inheriting all panel behaviour and only overriding what needs to change.

```java
// CalculationEngine inherits normalizeZero() from CalculationBase
public class CalculationEngine extends CalculationBase implements CalculatorOperations { … }

// GradientPanel inherits everything from JPanel
private static class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics graphics) { … }
}
```

### 4 – Polymorphism
`CalculationEngine` is both a `CalculationBase` *and* a `CalculatorOperations`. The `@Override` annotations on every implemented method confirm that the compiler enforces the correct signatures at compile time. `GradientPanel.paintComponent` overrides the parent implementation so the Swing framework automatically calls the custom gradient-drawing code at paint time without knowing anything about `GradientPanel` specifically.

```java
// Overriding an interface method – runtime polymorphism
@Override
public double add(double a, double b) {
    return normalizeZero(a + b);
}

// Overriding a superclass method – runtime polymorphism
@Override
protected void paintComponent(Graphics graphics) { … }
```

---

## Summary Table

| OOP Principle  | Where Applied                                                         |
|----------------|-----------------------------------------------------------------------|
| Abstraction    | `CalculatorOperations` interface                                      |
| Encapsulation  | Private/protected fields and methods in every class                   |
| Inheritance    | `CalculationEngine` → `CalculationBase`; `GUICalculator` → `JFrame`; `GradientPanel` → `JPanel` |
| Polymorphism   | `@Override` on all interface/superclass methods                       |
