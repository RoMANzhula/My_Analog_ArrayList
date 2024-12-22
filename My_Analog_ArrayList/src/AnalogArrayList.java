import java.util.Arrays;
import java.util.Iterator;

public class AnalogArrayList<T> {
    private final int DEFAULT_CAPACITY = 16; // початковий розмір масиву
    private final int REDUCTION_SIZE = 4; // розмір масиву для зменшення
    private Object[] arrayObjects = new Object[DEFAULT_CAPACITY]; // масив із початковим розміром
    private int pointer = 0; // вказівник на граничну межу масиву

    public boolean add(T elementOfArray) { // додавання елемента до масиву
        if (pointer == arrayObjects.length - 1) { // якщо досягли межі масиву, то
            Object[] newSizeArrayObjects = new Object[arrayObjects.length * 2]; // збільшуємо розмір масиву вдвічі
            System.arraycopy(arrayObjects, 0, newSizeArrayObjects, 0, pointer); // копіюємо вміст 
            // заповненого масиву в новий збільшений масив
            arrayObjects = newSizeArrayObjects; // змінюємо посилання на новий масив
        }
        arrayObjects[pointer] = elementOfArray; // останньому елементу присвоюємо значення доданого елемента
        pointer++; // інкрементуємо вказівник межі масиву
        return true;
    }

    public boolean remove(int index) { // видалення елемента масиву за індексом
        for (int i = index; i < pointer; i++) { // циклом від зазначеного індексу до межі масиву
            arrayObjects[i] = arrayObjects[i + 1]; // всі елементи праворуч від видаленого зсуваємо вліво
        }
        arrayObjects[pointer] = null; // останньому елементу присвоюємо значення null
        pointer--; // декрементуємо вказівник межі масиву

        if (arrayObjects.length > DEFAULT_CAPACITY && pointer < arrayObjects.length / REDUCTION_SIZE) { // якщо елементів 
            // у масиві менше, ніж його довжина, поділена на REDUCTION_SIZE, то зменшуємо масив удвічі (для економії пам’яті). Але 
            // не зменшуємо масив до номінального розміру.
            Object[] newSizeArrayObjects = new Object[arrayObjects.length / 2]; // зменшуємо розмір масиву удвічі
            System.arraycopy(arrayObjects, 0, newSizeArrayObjects, 0, pointer); // копіюємо вміст 
            // заповненого масиву в новий зменшений масив
            arrayObjects = newSizeArrayObjects; // змінюємо посилання на новий масив
        }
        return true;
    }

    public int size() { // метод повернення розміру масиву
        return pointer; // розмір дорівнює граничному вказівнику масиву
    }
    public T get(int index) { // повернення елемента з масиву за індексом
        T obj = null; // оголошуємо посилання типу T
        if (index > pointer || index < 0) { // якщо зазначений індекс елемента більший за межу масиву або від’ємний, то
            obj = (T) new String("IndexOutOfBoundsException"); // посиланню obj присвоюємо значення строкового літералу
        } else { // інакше
            obj = (T) arrayObjects[index]; // посиланню obj присвоюємо значення елемента масиву за індексом
        }
        return (T) obj; // повертаємо вміст посилання (з приведенням типу Generics для елемента масиву за індексом)
    }

    public void print() { // метод для виведення масиву на консоль
        Iterator<Object> iterator = Arrays.stream(arrayObjects).iterator(); // отримуємо ітератор для масиву
        while (iterator.hasNext()) { // перевіряємо, чи є ще елементи в масиві
            var object = iterator.next(); // отримуємо поточний елемент та переходимо до наступного
            if (object != null) System.out.println(object); // перевіряємо, щоб елемент не був null - виводимо цей елемент
        }
    }

    public void print2() { // другий метод для виведення вмісту масиву на консоль
        for (int i = 0; i < arrayObjects.length; i++) { // циклом проходимо по масиву arrayObjects
            if (arrayObjects[i] != null) { // якщо елемент масиву не дорівнює null, то
                System.out.println(arrayObjects[i]); // виводимо цей елемент
            }
        }
        System.out.println(); // роздільник - новий рядок
    }

    public boolean contains(T elementOfArray) { // метод для порівняння об'єктів у масиві
        int indexOf = 0; // створюємо змінну (псевдометод indexOf) та ініціалізуємо її значенням 0
        for (Object element : arrayObjects) { // циклом for-each (для кожного елемента з масиву) проходимо по масиву
            if (element.equals(elementOfArray)) { // якщо елемент масиву дорівнює порівнюваному елементу, то
                indexOf++; // інкрементуємо змінну (псевдометод), головне, щоб вона не мала від’ємного значення (тобто true)
            } else { // інакше
                indexOf = -1; // присвоюємо змінній (псевдометоду) значення -1 (тобто false)
            }
        }
        return indexOf >= 0;
    }
}
