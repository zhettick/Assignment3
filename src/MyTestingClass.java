public class MyTestingClass {
    private String id;
    private String name;

    public MyTestingClass(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false; // Null or different class
        MyTestingClass that = (MyTestingClass) object;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
