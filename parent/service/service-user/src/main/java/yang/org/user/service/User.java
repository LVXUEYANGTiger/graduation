package yang.org.user.service;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class User {
    private double a;
    private float b;
    private char c;
    private byte d;
    private short f;
    private long l;
    private Double aa;
    private Float bb;
    private Character cc;
    private Byte dd;
    private Short ff;
    private Long ll;
    private String name;
    private String password;
    private int age;
    private boolean flag;
    private Integer ddd;
    private Boolean dddd;
    private Object object;
    private String a1a1;
    private List<String> tt;

    public User(String name, String password, Boolean flag) {
        this.name = name;
        this.password = password;
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        User user = new User("张三","123456");

        ArrayList<String> users = new ArrayList<>();
        users.add("1");
        users.add("2");
        user.setTt(users);

        /*Map<Field, Method> objectAndGetMethod = new ObjectMethods().getObjectAndGetMethod(user);
        for (Field field : objectAndGetMethod.keySet()) {
            System.out.println("filed:" + field.getName() + " method:" + objectAndGetMethod.get(field).invoke(user));
        }*/

    }

}
