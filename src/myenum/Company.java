package myenum;

public enum Company {
    ONE(1,"吴"),TWO(2,"明"),THREE(3,"qing");
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Company(int code, String name){
        this.code = code;
        this.name = name;
    }

    public static Company getName(int index){
        Company[] values = Company.values();

        for (Company value : values) {
            if(value.code == index) {
                return value;
            }
        }
        return null;
    }
}
