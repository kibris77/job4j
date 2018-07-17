package ru.job4j.pseudo;

/**
 * Класс для рисования фигуры в консоли.
 */
public class Paint {
    /**
     * Рисует переданную в метод фигуру в консоли.
     * @param shape - отбъект наследуемый от Shape.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        paint.draw(new Square());
    }
}
