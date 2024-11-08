package es.juliogtrenard.menu;

public class LazyStringArgGetter extends LazyArgGetter<String> {
    @Override
    public ArgGetter<String> instanceGetter() {
        return new StringArgGetter();
    }
}
